using System.Reflection;
using Abp.Modules;

namespace EasyLife
{
    public class EasyLifeCoreModule : AbpModule
    {
        public override void Initialize()
        {
            IocManager.RegisterAssemblyByConvention(Assembly.GetExecutingAssembly());
        }
    }
}
