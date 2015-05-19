using System.Linq;
using PagedList;
using System.Web.Mvc;
using GaoKao.WebUI.Models;
using System.Data.Entity;
using System;
using System.Data;

namespace GaoKao.WebUI.Controllers
{
    [Authorize]
    public class BackgroundQuestionController : Controller
    {
        private ApplicationDbContext db = new ApplicationDbContext();

        // GET: BackgroundQuestion
        public ActionResult Index(int paperId, int? pageNumber )
        {
            try
            {
                ViewBag.PaperID = paperId;
                var list = db.BackgroundQuestion.ToList();
                var examPaper = db.ExamPaper.Single(m => m.ID == paperId);
                if (examPaper == null)
                {
                    return Content("Œ¥’“µΩ ‘æÌ–≈œ¢");
                }
                list.ForEach(a => a.ExamPaper = examPaper);
                return View(list.ToPagedList(pageNumber ?? 1, 10));
            }
            catch (Exception ex)
            {
                return Content(ex.Message);
            }
        }

        // GET: BackgroundQuestion/Details/5
        public ActionResult Details(System.Int32? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(404);
            }

            BackgroundQuestion backgroundQuestion = db.BackgroundQuestion.Single(m => m.ID == id);
            if (backgroundQuestion == null)
            {
                return new HttpStatusCodeResult(404);
            }

            return View(backgroundQuestion);
        }

        [ValidateInput(false)]
        // GET: BackgroundQuestion/Create
        public ActionResult Create(int paperId)
        {
            var examPaper = db.ExamPaper.Single(m => m.ID == paperId);
            if (examPaper == null)
            {
                return Content("Œ¥’“µΩ ‘æÌ–≈œ¢£¨«Î÷ÿ ‘");
            }
            var model = new BackgroundQuestion()
            {
                PaperID = paperId,
                ExamPaper = examPaper
            };
            ViewBag.PaperID = paperId;
            return View(model);
        }

        // POST: BackgroundQuestion/Create
        [HttpPost]
        [ValidateAntiForgeryToken]
        //[ValidateInput(false)]
        public ActionResult Create(BackgroundQuestion backgroundQuestion)
        {
            if (User == null ||User.Identity==null)
            {
                RedirectToAction("Login", "Account", new { msg = "«Îœ»µ«¬º" });
            }
            if (ModelState.IsValid)
            {
                backgroundQuestion.CreatedTime = DateTime.Now.ToUnixTime();
                backgroundQuestion.CreatedPerson = User.Identity.Name;
                backgroundQuestion.ModifiedTime = DateTime.Now.ToUnixTime();
                backgroundQuestion.ModifiedPerson = User.Identity.Name;
                backgroundQuestion.ExamPaper = null;
                //backgroundQuestion.ExamPaper.ID = backgroundQuestion.PaperID;
                db.BackgroundQuestion.Add(backgroundQuestion);
                db.SaveChanges();
                return RedirectToAction("Index",new  {paperId=backgroundQuestion.PaperID });
            }
            return View(backgroundQuestion);
        }

        // GET: BackgroundQuestion/Edit/5
        public ActionResult Edit(System.Int32? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(404);
            }
            BackgroundQuestion backgroundQuestion = db.BackgroundQuestion.Single(m => m.ID == id);
            if (backgroundQuestion == null)
            {
                return new HttpStatusCodeResult(404);
            }

            var examPaper = db.ExamPaper.Single(m => m.ID == backgroundQuestion.PaperID);
            if (examPaper == null)
            {
                return Content("Œ¥’“µΩ ‘æÌ–≈œ¢");
            }
            //backgroundQuestion.ExamPaper = examPaper;
            ViewBag.PaperID = backgroundQuestion.PaperID;
            return View(backgroundQuestion);
        }

        // POST: BackgroundQuestion/Edit/5
        [HttpPost]
        [ValidateAntiForgeryToken]
        [ValidateInput(false)]
        public ActionResult Edit(BackgroundQuestion backgroundQuestion)
        {
            if (User == null)
            {
                RedirectToAction("Login", "Account",new {msg="«Îœ»µ«¬º" });
            }
            if (ModelState.IsValid)
            {
                backgroundQuestion.ModifiedTime = DateTime.Now.ToUnixTime();
                backgroundQuestion.ModifiedPerson = User.Identity.Name;
                backgroundQuestion.ExamPaper.ID = backgroundQuestion.PaperID;
                db.Entry<BackgroundQuestion>(backgroundQuestion).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index",new {paperId=backgroundQuestion.PaperID });
            }

            return View(backgroundQuestion);
        }

        // GET: BackgroundQuestion/Delete/5
        [ActionName("Delete")]
        public ActionResult Delete(int paperId,System.Int32? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(404);
            }

            BackgroundQuestion backgroundQuestion = db.BackgroundQuestion.Single(m => m.ID == id);
            if (backgroundQuestion == null)
            {
                return new HttpStatusCodeResult(404);
            }
            var examPaper = db.ExamPaper.Single(m => m.ID == paperId);
            backgroundQuestion.ExamPaper = examPaper;
            ViewBag.PaperID = paperId;
            return View(backgroundQuestion);
        }

        // POST: BackgroundQuestion/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(int paperId,System.Int32 id)
        {
            BackgroundQuestion backgroundQuestion = db.BackgroundQuestion.Single(m => m.ID == id);
            db.BackgroundQuestion.Remove(backgroundQuestion);
            db.SaveChanges();
            ViewBag.PaperID = paperId;
            return RedirectToAction("Index",new  {paperId=paperId });
        }
    }
}
