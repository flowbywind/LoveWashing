using Abp.EntityFramework;
using System.Data.Common;
using System.Data.Entity;
using Abp.EntityFramework;
using EasyLife;


namespace EasyLife.EntityFramework
{
    public class EasyLifeDbContext : AbpDbContext
    {
        //TODO: Define an IDbSet for each Entity...
        public virtual IDbSet<Category> Category { get; set; }

        public virtual IDbSet<City> City { get; set; }

        public virtual IDbSet<Merchant> Merchant { get; set; }

        public virtual IDbSet<Tag> Tag { get; set; }
        public virtual IDbSet<Member> Member { get; set; }

        //Example:
        //public virtual IDbSet<User> Users { get; set; }

        /* NOTE: 
         *   Setting "Default" to base class helps us when working migration commands on Package Manager Console.
         *   But it may cause problems when working Migrate.exe of EF. If you will apply migrations on command line, do not
         *   pass connection string name to base classes. ABP works either way.
         */
        public EasyLifeDbContext()
            : base("Default")
        {

        }

        /* NOTE:
         *   This constructor is used by ABP to pass connection string defined in EasyLifeDataModule.PreInitialize.
         *   Notice that, actually you will not directly create an instance of EasyLifeDbContext since ABP automatically handles it.
         */
        public EasyLifeDbContext(string nameOrConnectionString)
            : base(nameOrConnectionString)
        {

        }

        //This constructor is used in tests
        public EasyLifeDbContext(DbConnection connection)
            : base(connection, true)
        {

        }
    }
}
