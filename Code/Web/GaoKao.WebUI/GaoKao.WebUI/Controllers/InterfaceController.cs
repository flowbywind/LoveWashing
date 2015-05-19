using GaoKao.WebUI.Models;
using GaoKao.WebUI.Models.ViewModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using System.IO;

namespace GaoKao.WebUI.Controllers
{
    public class InterfaceController : Controller
    {
        //
        // GET: /Interface/
        private ApplicationDbContext db = new ApplicationDbContext();

        public ActionResult Index()
        {
            return View();
        }
        /// <summary>
        /// 更新试卷考题等资源包
        /// </summary>
        /// <param name="lastUpdateTime"></param>
        /// <returns></returns>
        public ActionResult UpdateResourceKit(int lastUpdateTime)
        {
            db.Configuration.ProxyCreationEnabled = false;
            ResourceDataViewModel model = new ResourceDataViewModel();
            model.ExamPaper = db.ExamPaper.Where(a => a.ModifiedTime > lastUpdateTime && a.State == (int)EnumPaperState.Publish).ToList();
            model.Knowledge = db.Knowledge.Where(a => a.ModifiedTime > lastUpdateTime && a.State == (int)EnumPaperState.Publish).ToList();
            model.QuestionGroup = db.QuestionGroup.Where(a => a.ModifiedTime > lastUpdateTime).ToList();
            model.BackgroundQuestion = db.BackgroundQuestion.Where(a => a.ModifiedTime > lastUpdateTime).ToList();
            model.Question = db.Question.Where(a => a.ModifiedTime > lastUpdateTime && a.State == (int)EnumPaperState.Publish).ToList();
            model.QuestionItem = db.QuestionItem.Where(a => a.ModifiedTime > lastUpdateTime).ToList();
            model.DictionaryGroup = db.DictionaryGroup.Where(a => a.ModifiedTime > lastUpdateTime).ToList();
            model.DictionaryItem = db.DictionaryItem.Where(a => a.ModifiedTime > lastUpdateTime).ToList();
            return Json(model, JsonRequestBehavior.AllowGet);
            return Json(model, JsonRequestBehavior.AllowGet);
        }
        /// <summary>
        /// 更新图片
        /// </summary>
        /// <param name="lastUpdateTime"></param>
        /// <returns></returns>
        public ActionResult UpdatePicture(int lastUpdateTime)
        {
            List<string> downloadfileList = new List<string>();
            DateTime dt = lastUpdateTime.UnixToDateTime();
            string path = Server.MapPath("../UEditor/upload/image/");
            DirectoryInfo dir = new DirectoryInfo(path);
            if (!dir.Exists)
            {
                return Json(downloadfileList, JsonRequestBehavior.AllowGet);
            }
            DirectoryInfo[] dirs = dir.GetDirectories();
            foreach (DirectoryInfo di in dirs)
            {
                if (di.LastWriteTime >= dt)
                {
                    var files = di.GetFiles();
                    foreach (var f in files)
                    {
                        if (f.LastWriteTime > dt)
                        {
                            string filePath = path + di.Name + "/" + f.Name;
                            filePath = filePath.Replace(Server.MapPath("../"),"http://"+Request.Headers["Host"]+"/").Replace("\\","/");
                            downloadfileList.Add(filePath);
                        }
                    }
                }
            }
            return Json(downloadfileList, JsonRequestBehavior.AllowGet);
        }
    }
}
