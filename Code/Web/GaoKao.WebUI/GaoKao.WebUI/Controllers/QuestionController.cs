using System.Linq;
using PagedList;
using System.Web.Mvc;
using System.Data.Entity;
using GaoKao.WebUI.Models;
using System.Data;
using System;
using System.IO;
using System.Drawing;
using GaoKao.WebUI.Models.ViewModel;
namespace GaoKao.WebUI.Controllers
{
    public class QuestionController : Controller
    {
        private ApplicationDbContext db = new ApplicationDbContext();

        // GET: Question
        public ActionResult Index(int? pageNumber)
        {
            var list = from q in db.Question
                       join p in db.ExamPaper on q.PaperID equals p.ID into li
                       from x in li.DefaultIfEmpty()
                       select new QuestionViewModel()
             {
                 ID = q.ID,
                 CourseID=x.CourseID,
                 Difficulty = q.Difficulty,
                 MissScore = q.MissScore,
                 ModifiedPerson = q.ModifiedPerson,
                 ModifiedTime = q.ModifiedTime,
                 Num = q.Num,
                 Score = q.Score,
                 Title = q.Title,
                 PaperName = x.Title,
                 State=q.State
             };
            var result = list.ToList();
            result.ForEach(a => {
                a.CourseName=((EnumCourse)a.CourseID).GetEnumDescription();
                a.Status = ((EnumPaperState)a.State).GetEnumDescription();
            });
            return View(result.ToPagedList(pageNumber ?? 1, 10));
        }

        // GET: Question/Details/5
        public ActionResult Details(System.Int32? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(404);
            }

            Question question = db.Question.Single(m => m.ID == id);
            if (question == null)
            {
                return new HttpStatusCodeResult(404);
            }
            byte[] arr = Convert.FromBase64String("");
            MemoryStream ms = new MemoryStream(arr);
            Bitmap bmp = new Bitmap(ms);

            bmp.Save(".jpg", System.Drawing.Imaging.ImageFormat.Jpeg);
            //bmp.wi
            //bmp.Save(txtFileName + ".bmp", ImageFormat.Bmp);
            //bmp.Save(txtFileName + ".gif", ImageFormat.Gif);
            //bmp.Save(txtFileName + ".png", ImageFormat.Png);
            //ms.Close();
            return View(question);
        }

        // GET: Question/Create
        public ActionResult Create(int paperId)
        {
            var paper = db.ExamPaper.SingleOrDefault(a => a.ID == paperId);
            var model = new Question()
            {
                ExamPaper = paper,
                PaperID = paperId
            };
            return View(model);
        }

        // POST: Question/Create
        [HttpPost]
        [ValidateInput(false)]
        public ActionResult Create(Question question)
        {
            if (User == null || User.Identity == null)
            {
                return RedirectToAction("Login", "Account", new { msg = "ÇëÏÈµÇÂ¼" });
            }
            if (ModelState.IsValid)
            {
                string questionKeyA = Request.Form["QuestionKey_A"];
                string questionKeyB = Request.Form["QuestionKey_B"];
                string questionKeyC = Request.Form["QuestionKey_C"];
                string questionKeyD = Request.Form["QuestionKey_D"];
                string answer = string.Empty;
                if (questionKeyA == "on")
                {
                    answer = "A,";
                }
                if (questionKeyB == "on")
                {
                    answer += "B,";
                }
                if (questionKeyC == "on")
                {
                    answer += "C,";
                }
                if (questionKeyD == "on")
                {
                    answer += "D,";
                }
                if (answer != "")
                {
                    answer = answer.Remove(answer.Length - 1, 1);
                }
                question.Answer = answer;
                question.ExamPaper = null;
                question.CreatedTime = DateTime.Now.ToUnixTime();
                question.ModifiedTime = DateTime.Now.ToUnixTime();
                question.CreatedPerson = User.Identity.Name;
                question.ModifiedPerson = User.Identity.Name;
                db.Question.Add(question);
                db.SaveChanges();
                string questionItemA = Request.Form["QuestionItemA"];
                string questionItemB = Request.Form["QuestionItemB"];
                string questionItemC = Request.Form["QuestionItemC"];
                string questionItemD = Request.Form["QuestionItemD"];

                QuestionItem itemA = new QuestionItem() { Content = questionItemA, QuestionID = question.ID, Key = "A" ,CreatedTime=DateTime.Now.ToUnixTime(), ModifiedTime=DateTime.Now.ToUnixTime(), CreatedPerson=User.Identity.Name, ModifiedPerson=User.Identity.Name };
                QuestionItem itemB = new QuestionItem() { Content = questionItemB, QuestionID = question.ID, Key = "B", CreatedTime = DateTime.Now.ToUnixTime(), ModifiedTime = DateTime.Now.ToUnixTime(), CreatedPerson = User.Identity.Name, ModifiedPerson = User.Identity.Name };
                QuestionItem itemC = new QuestionItem() { Content = questionItemC, QuestionID = question.ID, Key = "C", CreatedTime = DateTime.Now.ToUnixTime(), ModifiedTime = DateTime.Now.ToUnixTime(), CreatedPerson = User.Identity.Name, ModifiedPerson = User.Identity.Name };
                QuestionItem itemD = new QuestionItem() { Content = questionItemD, QuestionID = question.ID, Key = "D", CreatedTime = DateTime.Now.ToUnixTime(), ModifiedTime = DateTime.Now.ToUnixTime(), CreatedPerson = User.Identity.Name, ModifiedPerson = User.Identity.Name };
                db.QuestionItem.Add(itemA);
                db.QuestionItem.Add(itemB);
                db.QuestionItem.Add(itemC);
                db.QuestionItem.Add(itemD);
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            return View(question);
        }

        // GET: Question/Edit/5
        public ActionResult Edit(System.Int32? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(404);
            }
            Question question = db.Question.Single(m => m.ID == id);
            question.ExamPaper = db.ExamPaper.SingleOrDefault(m => m.ID == question.PaperID);
            question.QuestionItems = db.QuestionItem.Where(a => a.QuestionID == id.Value).ToList();
            Knowledge knowledge = db.Knowledge.Where(m => m.ID == question.KnowledgeID).FirstOrDefault();
            ViewBag.KnowledgeName = knowledge == null ? "" : knowledge.Content;
            if (question == null)
            {
                return new HttpStatusCodeResult(404);
            }
            return View(question);
        }

        // POST: Question/Edit/5
        [HttpPost]
        [ValidateInput(false)]
        public ActionResult Edit(Question question)
        {
            if (User == null || User.Identity == null)
            {
                return RedirectToAction("Login", "Account", new { msg = "ÇëÏÈµÇÂ¼" });
            }
            if (ModelState.IsValid)
            {
                string questionKeyA = Request.Form["QuestionKey_A"];
                string questionKeyB = Request.Form["QuestionKey_B"];
                string questionKeyC = Request.Form["QuestionKey_C"];
                string questionKeyD = Request.Form["QuestionKey_D"];
                string answer = string.Empty;
                if (questionKeyA == "on")
                {
                    answer = "A,";
                }
                if (questionKeyB == "on")
                {
                    answer += "B,";
                }
                if (questionKeyC == "on")
                {
                    answer += "C,";
                }
                if (questionKeyD == "on")
                {
                    answer += "D,";
                }
                if (answer != "")
                {
                    answer = answer.Remove(answer.Length - 1, 1);
                }
                question.Answer = answer;
                question.ExamPaper = null;
                question.ModifiedTime = DateTime.Now.ToUnixTime();
                question.ModifiedPerson = User.Identity.Name;
                db.Entry<Question>(question).State = EntityState.Modified;
                db.SaveChanges();
                string questionItemA = Request.Form["QuestionItemA"];
                string questionItemB = Request.Form["QuestionItemB"];
                string questionItemC = Request.Form["QuestionItemC"];
                string questionItemD = Request.Form["QuestionItemD"];
                var itemA = db.QuestionItem.Where(m => m.QuestionID == question.ID && m.Key == "A").First();
                if (itemA != null)
                {
                    itemA.Content = questionItemA;
                    itemA.ModifiedTime = DateTime.Now.ToUnixTime();
                    itemA.ModifiedPerson = User.Identity.Name;
                }
                var itemB = db.QuestionItem.Where(m => m.QuestionID == question.ID && m.Key == "B").First();
                if (itemB != null)
                {
                    itemB.Content = questionItemB;
                    itemB.ModifiedTime = DateTime.Now.ToUnixTime();
                    itemB.ModifiedPerson = User.Identity.Name;
                }
                var itemC = db.QuestionItem.Where(m => m.QuestionID == question.ID && m.Key == "C").First();
                if (itemC != null)
                {
                    itemC.Content = questionItemC;
                    itemC.ModifiedTime = DateTime.Now.ToUnixTime();
                    itemC.ModifiedPerson = User.Identity.Name;
                }
                var itemD = db.QuestionItem.Where(m => m.QuestionID == question.ID && m.Key == "D").First();
                if (itemD != null)
                {
                    itemD.Content = questionItemD;
                    itemD.ModifiedPerson = User.Identity.Name;
                    itemD.ModifiedTime = DateTime.Now.ToUnixTime();
                }
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            return View(question);
        }

        // GET: Question/Delete/5
        [ActionName("Delete")]
        public ActionResult Delete(System.Int32? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(404);
            }

            Question question = db.Question.Single(m => m.ID == id);
            if (question == null)
            {
                return new HttpStatusCodeResult(404);
            }

            return View(question);
        }

        // POST: Question/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(System.Int32 id)
        {
            Question question = db.Question.Single(m => m.ID == id);
            db.Question.Remove(question);
            db.SaveChanges();

            return RedirectToAction("Index");
        }
    }
}
