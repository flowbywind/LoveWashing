using System.Data.Entity;
using System.Reflection;
using Abp.EntityFramework;
using Abp.Modules;
using EasyLife.EntityFramework;

namespace EasyLife
{
    [DependsOn(typeof(AbpEntityFrameworkModule), typeof(EasyLifeCoreModule))]
    public class EasyLifeDataModule : AbpModule
    {
        //public override void PreInitialize()
        //{
        //    Configuration.DefaultNameOrConnectionString = "Default";
        //}

        public override void Initialize()
        {
            IocManager.RegisterAssemblyByConvention(Assembly.GetExecutingAssembly());
            //Database.SetInitializer<EasyLifeDbContext>(null);
        }
    }
}
