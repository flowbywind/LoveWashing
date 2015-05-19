using System.Linq;
using PagedList;
using System.Web.Mvc;
using System.Data.Entity;
using GaoKao.WebUI.Models;
using System.Data;
using GaoKao.WebUI.Models.ViewModel;
namespace GaoKao.WebUI.Controllers
{
    [Authorize]
    public class KnowledgeController : Controller
    {
        private ApplicationDbContext db = new ApplicationDbContext();

        // GET: Knowledge
        public ActionResult Index()
        {
            return View();
        }
        public ActionResult List() 
        {
            return View();
        }
        public ActionResult GetList() {
            var list = db.Knowledge.ToList().Select(item => new KnowledgeViewModel {
                Content = item.Content,
                CourseName = ((EnumCourse)item.CourseID).GetEnumDescription(),
                CourseType = ((EnumCourseType)item.CourseType).GetEnumDescription(),
                District = ((EnumCity)item.District).GetEnumDescription(),
                ID = item.ID,
                ParentId = item.ParentId,
                SortOrder = item.SortOrder,
                State = ((EnumPaperState)item.State).GetEnumDescription(),
                Frequency = item.Frequency,
                ModifiedTime = item.ModifiedTime.UnixToDateTime()
            });
            var griddata = new { Rows = list };
            return Json(griddata, JsonRequestBehavior.AllowGet);
        }
        // GET: Knowledge/Details/5
        public ActionResult Details(System.Int32? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(404);
            }

            Knowledge knowledge = db.Knowledge.Single(m => m.ID == id);
            if (knowledge == null)
            {
                return new HttpStatusCodeResult(404);
            }

            return View(knowledge);
        }

        // GET: Knowledge/Create
        public ActionResult Create(int? parentId)
        {
            ViewBag.ParentId = parentId??0;
            if (!parentId.HasValue) {
                ViewBag.Disabled = "";
                return View(new Knowledge() { });
            }
            var model = db.Knowledge.SingleOrDefault(m => m.ID == parentId);
            if (model == null) {
                ViewBag.Disabeld = "";
                return View(new Knowledge() { });
            }
            ViewBag.Disabled = "disabled";
            return View(new Knowledge() { ParentId=model.ID,
                                          CourseID=model.CourseID,
                                          CourseType=model.CourseType,
                                          District=model.District});
        }

        // POST: Knowledge/Create
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create(Knowledge knowledge)
        {
            if (User == null || User.Identity == null) {
                return RedirectToAction("Login", "Account", new { returnUrl=this.Url.Action("Create") });
            }
            if (ModelState.IsValid)
            {
                //获取父课程 课程类型 地区
                var parent = db.Knowledge.SingleOrDefault(m=>m.ID==knowledge.ParentId);
                if (parent != null) {
                    knowledge.CourseID = parent.CourseID;
                    knowledge.CourseType = parent.CourseType;
                    knowledge.District = parent.District;
                }
                knowledge.CreatedTime = System.DateTime.Now.ToUnixTime();
                knowledge.CreatedPerson = User.Identity.Name;
                knowledge.ModifiedTime = System.DateTime.Now.ToUnixTime();
                knowledge.ModifiedPerson = User.Identity.Name;
                db.Knowledge.Add(knowledge);
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            return View(knowledge);
        }

        // GET: Knowledge/Edit/5
        public ActionResult Edit(System.Int32? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(404);
            }
            Knowledge knowledge = db.Knowledge.SingleOrDefault(m => m.ID == id);
            if (knowledge == null) {
                return new HttpStatusCodeResult(404);
            }
            ViewBag.ParentId = knowledge.ParentId;
            var model = db.Knowledge.SingleOrDefault(m => m.ID == knowledge.ParentId);
            if (model == null) {
                //判断是否有子节点 如果有也不可修改科目等
                var isHaveChild = db.Knowledge.Any(a => a.ParentId == id);
                if (isHaveChild) {
                    ViewBag.Disabled = "disabled";
                }
                else {
                    ViewBag.Disabled = "";
                }
                return View(knowledge);
            }
            ViewBag.Disabled = "disabled";
            return View(knowledge);
        }

        // POST: Knowledge/Edit/5
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit(Knowledge knowledge)
        {
            if (User.Identity.IsAuthenticated == false) {
                return RedirectToAction("Login", "Account", new { returnUrl = this.Url.Action("Create") });
            }
            if (ModelState.IsValid)
            {
                var parent = db.Knowledge.SingleOrDefault(m=>m.ID==knowledge.ParentId);
                if (parent != null) {
                    knowledge.District = parent.District;
                    knowledge.CourseID = parent.CourseID;
                    knowledge.CourseType = parent.CourseType;
                }
                knowledge.ModifiedTime = System.DateTime.Now.ToUnixTime();
                knowledge.ModifiedPerson = User.Identity.Name;
                db.Entry<Knowledge>(knowledge).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            return View(knowledge);
        }

        // GET: Knowledge/Delete/5
        [ActionName("Delete")]
        public ActionResult Delete(System.Int32? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(404);
            }
            Knowledge knowledge = db.Knowledge.SingleOrDefault(m => m.ID == id);
            if (knowledge == null)
            {
                return new HttpStatusCodeResult(404);
            }
            return View(knowledge);
        }

        // POST: Knowledge/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(System.Int32 id)
        {
            var isHaveChild = db.Knowledge.Any(m=>m.ParentId==id);
            if (isHaveChild) {
                return Json(new { success = false, msg = "有子考点，无法删除考点" });
            }
            Knowledge knowledge = db.Knowledge.SingleOrDefault(m => m.ID == id);
            if (knowledge == null) {
                return Json(new { success=false,msg="不存在此条记录,删除失败"});
            }
            db.Knowledge.Remove(knowledge);
            db.SaveChanges();
            return Json(new { success=true,msg="成功删除" });
            //return RedirectToAction("Index");
        }
    }
}
