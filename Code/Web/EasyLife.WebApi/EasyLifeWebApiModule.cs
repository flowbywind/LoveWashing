using System.Reflection;
using Abp.Application.Services;
using Abp.Modules;
using Abp.WebApi;
using Abp.WebApi.Controllers.Dynamic.Builders;

namespace EasyLife
{
    [DependsOn(typeof(AbpWebApiModule), typeof(EasyLifeApplicationModule))]
    public class EasyLifeWebApiModule : AbpModule
    {
        public override void Initialize()
        {
            IocManager.RegisterAssemblyByConvention(Assembly.GetExecutingAssembly());

            DynamicApiControllerBuilder
                .ForAll<IApplicationService>(typeof(EasyLifeApplicationModule).Assembly, "app")
                .Build();
        }
    }
}
