using System.Web.Mvc;

namespace EasyLife.Web.Controllers
{
    public class HomeController : EasyLifeControllerBase
    {
        public ActionResult Index()
        {
            return View();
        }
	}
}