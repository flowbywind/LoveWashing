using Love.Washing.WebUI.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace Love.Washing.WebUI.Controllers
{
    public class BaseController : Controller
    {
        //
        // GET: /Base/
        public ApplicationDbContext db = new ApplicationDbContext();

    }
}
