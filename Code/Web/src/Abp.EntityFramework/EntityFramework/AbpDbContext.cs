﻿using System.Data.Common;
using System.Data.Entity;
using System.Data.Entity.Core.Objects;
using System.Data.Entity.Infrastructure;
using System.Data.Entity.Validation;
using System.Threading;
using System.Threading.Tasks;
using Abp.Configuration.Startup;
using Abp.Domain.Entities;
using Abp.Domain.Entities.Auditing;
using Abp.Domain.Uow;
using Abp.Events.Bus.Entities;
using Abp.Extensions;
using Abp.Runtime.Session;
using Abp.Timing;
using EntityFramework.DynamicFilters;

namespace Abp.EntityFramework
{
    /// <summary>
    /// Base class for all DbContext classes in the application.
    /// </summary>
    public abstract class AbpDbContext : DbContext, IShouldInitialize
    {
        /// <summary>
        /// Used to get current session values.
        /// </summary>
        public IAbpSession AbpSession { get; set; }

        /// <summary>
        /// Used to trigger entity change events.
        /// </summary>
        public IEntityChangedEventHelper EntityChangedEventHelper { get; set; }

        /// <summary>
        /// Constructor.
        /// Uses <see cref="IAbpStartupConfiguration.DefaultNameOrConnectionString"/> as connection string.
        /// </summary>
        protected AbpDbContext()
        {
            AbpSession = NullAbpSession.Instance;
            EntityChangedEventHelper = NullEntityChangedEventHelper.Instance;
        }

        /// <summary>
        /// Constructor.
        /// </summary>
        protected AbpDbContext(string nameOrConnectionString)
            : base(nameOrConnectionString)
        {
            AbpSession = NullAbpSession.Instance;
            EntityChangedEventHelper = NullEntityChangedEventHelper.Instance;
        }

        /// <summary>
        /// Constructor.
        /// </summary>
        protected AbpDbContext(DbCompiledModel model)
            : base(model)
        {
            AbpSession = NullAbpSession.Instance;
            EntityChangedEventHelper = NullEntityChangedEventHelper.Instance;
        }

        /// <summary>
        /// Constructor.
        /// </summary>
        protected AbpDbContext(DbConnection existingConnection, bool contextOwnsConnection)
            : base(existingConnection, contextOwnsConnection)
        {
            AbpSession = NullAbpSession.Instance;
            EntityChangedEventHelper = NullEntityChangedEventHelper.Instance;
        }

        /// <summary>
        /// Constructor.
        /// </summary>
        protected AbpDbContext(string nameOrConnectionString, DbCompiledModel model)
            : base(nameOrConnectionString, model)
        {
            AbpSession = NullAbpSession.Instance;
            EntityChangedEventHelper = NullEntityChangedEventHelper.Instance;
        }

        /// <summary>
        /// Constructor.
        /// </summary>
        protected AbpDbContext(ObjectContext objectContext, bool dbContextOwnsObjectContext)
            : base(objectContext, dbContextOwnsObjectContext)
        {
            AbpSession = NullAbpSession.Instance;
            EntityChangedEventHelper = NullEntityChangedEventHelper.Instance;
        }

        /// <summary>
        /// Constructor.
        /// </summary>
        protected AbpDbContext(DbConnection existingConnection, DbCompiledModel model, bool contextOwnsConnection)
            : base(existingConnection, model, contextOwnsConnection)
        {
            AbpSession = NullAbpSession.Instance;
            EntityChangedEventHelper = NullEntityChangedEventHelper.Instance;
        }

        public virtual void Initialize()
        {
            Database.Initialize(false);
            this.SetFilterScopedParameterValue(AbpDataFilters.MustHaveTenant, AbpDataFilters.Parameters.TenantId, AbpSession.TenantId ?? 0);
            this.SetFilterScopedParameterValue(AbpDataFilters.MayHaveTenant, AbpDataFilters.Parameters.TenantId, AbpSession.TenantId);
        }

        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            base.OnModelCreating(modelBuilder);
            modelBuilder.Filter(AbpDataFilters.SoftDelete, (ISoftDelete d) => d.IsDeleted, false);
            modelBuilder.Filter(AbpDataFilters.MustHaveTenant, (IMustHaveTenant t, int tenantId) => t.TenantId == tenantId, 0);
            modelBuilder.Filter(AbpDataFilters.MayHaveTenant, (IMayHaveTenant t, int? tenantId) => t.TenantId == tenantId, 0);
        }

        public override int SaveChanges()
        {
            ApplyAbpConcepts();
            return base.SaveChanges();
        }

        public override Task<int> SaveChangesAsync(CancellationToken cancellationToken)
        {
            ApplyAbpConcepts();
            return base.SaveChangesAsync(cancellationToken);
        }

        private void ApplyAbpConcepts()
        {
            foreach (var entry in ChangeTracker.Entries())
            {
                switch (entry.State)
                {
                    case EntityState.Added:
                        SetCreationAuditProperties(entry);
                        CheckAndSetTenantIdProperty(entry);
                        EntityChangedEventHelper.TriggerEntityCreatedEvent(entry.Entity);
                        break;
                    case EntityState.Modified:
                        PreventSettingCreationAuditProperties(entry);
                        CheckAndSetTenantIdProperty(entry);
                        SetModificationAuditProperties(entry);

                        if (entry.Entity is ISoftDelete && entry.Entity.As<ISoftDelete>().IsDeleted)
                        {
                            if (entry.Entity is IDeletionAudited)
                            {
                                SetDeletionAuditProperties(entry.Entity.As<IDeletionAudited>());
                            }

                            EntityChangedEventHelper.TriggerEntityDeletedEvent(entry.Entity);
                        }
                        else
                        {
                            EntityChangedEventHelper.TriggerEntityUpdatedEvent(entry.Entity);
                        }

                        break;
                    case EntityState.Deleted:
                        PreventSettingCreationAuditProperties(entry);
                        HandleSoftDelete(entry);
                        EntityChangedEventHelper.TriggerEntityDeletedEvent(entry.Entity);
                        break;
                }
            }
        }

        private void CheckAndSetTenantIdProperty(DbEntityEntry entry)
        {
            if (entry.Entity is IMustHaveTenant)
            {
                CheckAndSetMustHaveTenant(entry);
            }
            else if (entry.Entity is IMayHaveTenant)
            {
                CheckMayHaveTenant(entry);
            }
        }

        private void CheckAndSetMustHaveTenant(DbEntityEntry entry)
        {
            var tenantEntity = entry.Cast<IMustHaveTenant>().Entity;

            if (!this.IsFilterEnabled(AbpDataFilters.MustHaveTenant))
            {
                if (AbpSession.TenantId != null && tenantEntity.TenantId == 0)
                {
                    tenantEntity.TenantId = AbpSession.GetTenantId();
                }

                return;
            }

            if (AbpSession.TenantId == null)
            {
                throw new DbEntityValidationException("Can not save a IMustHaveTenant entity while MustHaveTenant filter is enabled and there is no tenant logged in!");
            }

            if (tenantEntity.TenantId == 0)
            {
                tenantEntity.TenantId = AbpSession.GetTenantId();
            }
            else if (tenantEntity.TenantId != AbpSession.GetTenantId())
            {
                throw new DbEntityValidationException("Can not set IMustHaveTenant.TenantId to a different value than current tenant's Id while MustHaveTenant filter is enabled!");
            }
        }

        private void CheckMayHaveTenant(DbEntityEntry entry)
        {
            if (!this.IsFilterEnabled(AbpDataFilters.MayHaveTenant))
            {
                return;
            }

            if (entry.Cast<IMayHaveTenant>().Entity.TenantId != AbpSession.TenantId)
            {
                throw new DbEntityValidationException("Can not set TenantId to a different value from current session (IAbpSession.TenantId) while MayHaveTenant filter is enabled!");
            }
        }

        private void SetCreationAuditProperties(DbEntityEntry entry)
        {
            if (entry.Entity is IHasCreationTime)
            {
                entry.Cast<IHasCreationTime>().Entity.CreationTime = Clock.Now;
            }

            if (entry.Entity is ICreationAudited)
            {
                entry.Cast<ICreationAudited>().Entity.CreatorUserId = AbpSession.UserId;
            }
        }

        private void PreventSettingCreationAuditProperties(DbEntityEntry entry)
        {
            //TODO@Halil: Implement this when tested well (Issue #49)
            //if (entry.Entity is IHasCreationTime && entry.Cast<IHasCreationTime>().Property(e => e.CreationTime).IsModified)
            //{
            //    throw new DbEntityValidationException(string.Format("Can not change CreationTime on a modified entity {0}", entry.Entity.GetType().FullName));
            //}

            //if (entry.Entity is ICreationAudited && entry.Cast<ICreationAudited>().Property(e => e.CreatorUserId).IsModified)
            //{
            //    throw new DbEntityValidationException(string.Format("Can not change CreatorUserId on a modified entity {0}", entry.Entity.GetType().FullName));
            //}
        }

        private void SetModificationAuditProperties(DbEntityEntry entry)
        {
            if (entry.Entity is IModificationAudited)
            {
                var auditedEntry = entry.Cast<IModificationAudited>();

                auditedEntry.Entity.LastModificationTime = Clock.Now;
                auditedEntry.Entity.LastModifierUserId = AbpSession.UserId;
            }
        }

        private void HandleSoftDelete(DbEntityEntry entry)
        {
            if (!(entry.Entity is ISoftDelete))
            {
                return;
            }

            var softDeleteEntry = entry.Cast<ISoftDelete>();

            softDeleteEntry.State = EntityState.Unchanged;
            softDeleteEntry.Entity.IsDeleted = true;

            if (entry.Entity is IDeletionAudited)
            {
                SetDeletionAuditProperties(entry.Cast<IDeletionAudited>().Entity);
            }
        }

        private void SetDeletionAuditProperties(IDeletionAudited entity)
        {
            entity.DeletionTime = Clock.Now;
            entity.DeleterUserId = AbpSession.UserId;
        }
    }
}
