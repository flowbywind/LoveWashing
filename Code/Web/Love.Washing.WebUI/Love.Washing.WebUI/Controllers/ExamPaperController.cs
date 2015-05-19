using System.Linq;
using System.Web.Mvc;
using System.Data.Entity;
using Love.Washing.WebUI.Models;
using System;
using Love.Washing.WebUI.Models.ViewModel;
using PagedList;
using System.Data;

namespace Love.Washing.WebUI.Controllers
{
    [Authorize]
    public class ExamPaperController : Controller
    {
        private ApplicationDbContext db = new ApplicationDbContext();

        // GET: ExamPaper
        public ActionResult Index(int? pageNumber,int? pageSize)
        {
            var list= db.ExamPaper.ToList().Select(item => new ExamPaperViewModel
            {
                Title=item.Title,
                CourseName = ((EnumCourse)item.CourseID).GetEnumDescription(),
                CourseType = ((EnumCourseType)item.CourseType).GetEnumDescription(),
                Difficulty = item.Difficulty,
                District = ((EnumCity)item.District).GetEnumDescription(),
                ExamMinute = item.ExamMinute,
                ID = item.ID,
                PaperType = ((EnumPaperType)item.PaperType).GetEnumDescription(),
                Score = item.Score,
                SortOrder = item.SortOrder,
                State = ((EnumPaperState)item.State).GetEnumDescription()
            });
            return View(list.ToPagedList(pageNumber??1,pageSize??10));
        }

        // GET: ExamPaper/Details/5
        public ActionResult Details(System.Int32? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(404);
            }

            ExamPaper examPaper = db.ExamPaper.Single(m => m.ID == id);
            if (examPaper == null)
            {
                return new HttpStatusCodeResult(404);
            }

            return View(examPaper);
        }

        // GET: ExamPaper/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: ExamPaper/Create
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create(ExamPaper examPaper)
        {
            if (User == null || User.Identity == null)
            {
                return RedirectToAction("Login", "Account", new { returnUrl = this.Url.Action("Create") });
            }
            if (ModelState.IsValid)
            {
                examPaper.CreatedPerson = User.Identity.Name;
                examPaper.CreatedTime = DateTime.Now.ToUnixTime();
                examPaper.ModifiedPerson = User.Identity.Name;
                examPaper.ModifiedTime = DateTime.Now.ToUnixTime();
                db.ExamPaper.Add(examPaper);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            return View(examPaper);
        }

        // GET: ExamPaper/Edit/5
        public ActionResult Edit(System.Int32? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(404);
            }

            ExamPaper examPaper = db.ExamPaper.Single(m => m.ID == id);
            if (examPaper == null)
            {
                return new HttpStatusCodeResult(404);
            }

            return View(examPaper);
        }

        // POST: ExamPaper/Edit/5
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit(ExamPaper examPaper)
        {
            if (ModelState.IsValid)
            {
                examPaper.ModifiedPerson = User.Identity.Name;
                examPaper.ModifiedTime = DateTime.Now.ToUnixTime();
                //db.ChangeTracker.Entry(examPaper).State = EntityState.Modified;
                db.Entry<ExamPaper>(examPaper).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            return View(examPaper);
        }

        // GET: ExamPaper/Delete/5
        [ActionName("Delete")]
        public ActionResult Delete(System.Int32? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(404);
            }

            ExamPaper examPaper = db.ExamPaper.Single(m => m.ID == id);
            if (examPaper == null)
            {
                return new HttpStatusCodeResult(404);
            }

            return View(examPaper);
        }

        // POST: ExamPaper/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(System.Int32 id)
        {
            ExamPaper examPaper = db.ExamPaper.Single(m => m.ID == id);
            db.ExamPaper.Remove(examPaper);
            db.SaveChanges();

            return RedirectToAction("Index");
        }
    }
}
