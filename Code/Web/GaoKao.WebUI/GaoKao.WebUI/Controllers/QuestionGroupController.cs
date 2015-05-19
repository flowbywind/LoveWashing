using System.Linq;
using System.Web.Mvc;
using System.Data.Entity;
using GaoKao.WebUI.Models;
using PagedList;
using System.Data;
namespace GaoKao.WebUI.Controllers
{
    [Authorize]
    public class QuestionGroupController : Controller
    {
        private ApplicationDbContext db = new ApplicationDbContext();

        // GET: QuestionGroup
        public ActionResult Index(int paperId,int? pageNumber)
        {
            try
            {
                ViewBag.PaperID = paperId;
                var list =  db.QuestionGroup.Where(m=>m.PaperID==paperId).ToList();
                if (paperId == 0)
                {
                    return Content("id=0");
                }
                if (list.Count == 0)
                {
                    return View(list.ToPagedList(pageNumber??1,10));
                }
                var examPaper = db.ExamPaper.Single(c => c.ID == paperId);
                list.ForEach(b => b.ExamPaper = examPaper);
               
                return View(list.ToPagedList(pageNumber??1,10));
            }
            catch (System.Exception ex)
            {
                return Content(ex.Message);
            }
        }

        // GET: QuestionGroup/Details/5
        public ActionResult Details(System.Int32? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(404);
            }

            QuestionGroup questionGroup = db.QuestionGroup.Single(m => m.ID == id);
            if (questionGroup == null)
            {
                return new HttpStatusCodeResult(404);
            }

            return View(questionGroup);
        }

        // GET: QuestionGroup/Create
        public ActionResult Create(int paperId)
        {
            var paper = db.ExamPaper.Single(a => a.ID == paperId);
            if (paper == null)
            {
                return new HttpStatusCodeResult(404);
            }
            QuestionGroup group = new QuestionGroup()
            {
                PaperID = paper.ID,
                ExamPaper = paper
            };
            ViewBag.PaperID = paperId;
            return View(group);
        }

        // POST: QuestionGroup/Create
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create(QuestionGroup questionGroup)
        {
            if (this.User == null)
            {
                RedirectToAction("Login", "Account", new { msg = "请先登录在操作" });
            }
            if (ModelState.IsValid)
            {
                questionGroup.CreatedTime = System.DateTime.Now.ToUnixTime();
                questionGroup.CreatedPerson = base.User.Identity.Name;
                questionGroup.ModifiedTime = System.DateTime.Now.ToUnixTime();
                questionGroup.ModifiedPerson = User.Identity.Name;
                questionGroup.ExamPaper = null;
                db.QuestionGroup.Add(questionGroup);
                db.SaveChanges();
                return RedirectToAction("Index",new { paperId=questionGroup.PaperID });
            }
            return View(questionGroup);
        }

        // GET: QuestionGroup/Edit/5
        public ActionResult Edit(int paperId,System.Int32? id)
        {
            var examPaper = db.ExamPaper.Single(a => a.ID == paperId);
            if (id == null)
            {
                return new HttpStatusCodeResult(404);
            }
            if (examPaper == null)
            {
                return Content("未找到试题信息");
            }
            QuestionGroup questionGroup = db.QuestionGroup.SingleOrDefault(m => m.ID == id);
            if (questionGroup == null)
            {
                return new HttpStatusCodeResult(404);
            }
            questionGroup.ExamPaper = examPaper;
            ViewBag.PaperID = paperId;
            return View(questionGroup);
        }

        // POST: QuestionGroup/Edit/5
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit(QuestionGroup questionGroup)
        {
            if (ModelState.IsValid)
            {
                if (this.User == null)
                {
                    return Content("请登陆后操作");
                }
                questionGroup.ModifiedPerson = this.User.Identity.Name;
                questionGroup.ModifiedTime = System.DateTime.Now.ToUnixTime();
                questionGroup.ExamPaper.ID = questionGroup.PaperID;
                db.Entry<QuestionGroup>(questionGroup).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index",new  {paperId=questionGroup.PaperID });
            }
            return View(questionGroup);
        }

        // GET: QuestionGroup/Delete/5
        [ActionName("Delete")]
        public ActionResult Delete(int paperId,System.Int32? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(404);
            }
            var examPaper = db.ExamPaper.Single(a => a.ID == paperId);
            QuestionGroup questionGroup = db.QuestionGroup.Single(m => m.ID == id);
            questionGroup.ExamPaper = examPaper;
            if (questionGroup == null)
            {
                return new HttpStatusCodeResult(404);
            }
            ViewBag.PaperID = paperId;
            return View(questionGroup);
        }

        // POST: QuestionGroup/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(System.Int32 id)
        {
            QuestionGroup questionGroup = db.QuestionGroup.Single(m => m.ID == id);
            db.QuestionGroup.Remove(questionGroup);
            db.SaveChanges();

            return RedirectToAction("Index",new {paperId=questionGroup.PaperID});
        }
    }
}
