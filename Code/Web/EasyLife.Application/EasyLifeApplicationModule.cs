using System.Reflection;
using Abp.Modules;

namespace EasyLife
{
    [DependsOn(typeof(EasyLifeCoreModule))]
    public class EasyLifeApplicationModule : AbpModule
    {
        public override void Initialize()
        {
            IocManager.RegisterAssemblyByConvention(Assembly.GetExecutingAssembly());
        }
    }
}
