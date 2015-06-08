using System.Web.Mvc;

namespace EasyLife.Web.Controllers
{
    public class AboutController : EasyLifeControllerBase
    {
        public ActionResult Index()
        {
            return View();
        }
	}
}