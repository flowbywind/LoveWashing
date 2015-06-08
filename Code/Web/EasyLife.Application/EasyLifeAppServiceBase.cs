using Abp.Application.Services;

namespace EasyLife
{
    /// <summary>
    /// Derive your application services from this class.
    /// </summary>
    public abstract class EasyLifeAppServiceBase : ApplicationService
    {
        protected EasyLifeAppServiceBase()
        {
            LocalizationSourceName = EasyLifeConsts.LocalizationSourceName;
        }
    }
}