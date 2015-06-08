using Abp.Web.Mvc.Views;

namespace EasyLife.Web.Views
{
    public abstract class EasyLifeWebViewPageBase : EasyLifeWebViewPageBase<dynamic>
    {

    }

    public abstract class EasyLifeWebViewPageBase<TModel> : AbpWebViewPage<TModel>
    {
        protected EasyLifeWebViewPageBase()
        {
            LocalizationSourceName = EasyLifeConsts.LocalizationSourceName;
        }
    }
}