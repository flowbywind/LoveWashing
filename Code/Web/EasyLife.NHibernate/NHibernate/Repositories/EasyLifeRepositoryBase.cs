using Abp.Domain.Entities;
using Abp.NHibernate;
using Abp.NHibernate.Repositories;

namespace EasyLife.NHibernate.Repositories
{
    /// <summary>
    /// Base class for all repositories in this application
    /// </summary>
    /// <typeparam name="TEntity">Entity type</typeparam>
    /// <typeparam name="TPrimaryKey">Type of the primary key</typeparam>
    public abstract class EasyLifeRepositoryBase<TEntity, TPrimaryKey> : NhRepositoryBase<TEntity, TPrimaryKey>
        where TEntity : class, IEntity<TPrimaryKey>
    {
        protected EasyLifeRepositoryBase(ISessionProvider sessionProvider) : base(sessionProvider)
        {
        }

        //add common methods for all repositories
    }

    /// <summary>
    /// A shortcut of EasyLifeRepositoryBase for entities with integer Id.
    /// </summary>
    /// <typeparam name="TEntity">Entity type</typeparam>
    public abstract class EasyLifeRepositoryBase<TEntity> : EasyLifeRepositoryBase<TEntity, int>
        where TEntity : class, IEntity<int>
    {
        protected EasyLifeRepositoryBase(ISessionProvider sessionProvider) : base(sessionProvider)
        {
        }

        //do not add any method here, add to the class above (since this inherits it)
    }
}
