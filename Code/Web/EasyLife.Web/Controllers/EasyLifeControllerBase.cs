using Abp.Web.Mvc.Controllers;

namespace EasyLife.Web.Controllers
{
    /// <summary>
    /// Derive all Controllers from this class.
    /// </summary>
    public abstract class EasyLifeControllerBase : AbpController
    {
        protected EasyLifeControllerBase()
        {
            LocalizationSourceName = EasyLifeConsts.LocalizationSourceName;
        }
    }
}