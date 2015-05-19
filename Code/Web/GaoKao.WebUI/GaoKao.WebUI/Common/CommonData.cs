using System;
using System.Collections.Generic;
using System.Web.Mvc;
using System.Linq;
namespace GaoKao.WebUI.Models
{
    public static class CommonData
    {
        /// <summary>
        ///区域城市
        /// </summary>
        public static List<SelectListItem> City
        {
            get
            {
                return new List<SelectListItem> {
                     new SelectListItem() { Text="北京" , Value="1"  }
                };
            }
        }

        /// <summary>
        /// 课程类型 文科 理科
        /// </summary>
        public static List<SelectListItem> CourseType
        {
            get
            {
                return new List<SelectListItem>
                {
                    new SelectListItem() { Text="理科", Value="1" } ,
                    new SelectListItem() {Text="文科", Value="2"}
                };
            }
        }

        /// <summary>
        /// 课程
        /// </summary>
        public static List<SelectListItem> Course
        {
            get
            {
                return new List<SelectListItem>() {
                     new SelectListItem() {  Text="语文", Value="1" },
                     new SelectListItem() {  Text="数学", Value="2" },
                     new SelectListItem() {  Text="英语",Value="3"},
                     new SelectListItem() { Text="物理",Value="4"},
                     new SelectListItem() { Text="化学",Value="5"},
                     new SelectListItem() { Text="生物",Value="6"},
                     new SelectListItem() { Text="政治",Value="7"},
                     new SelectListItem() {  Text="历史",Value="8"},
                     new SelectListItem() { Text="地理",Value="9"}
                    };
            }
        }

        /// <summary>
        /// 试卷类型
        /// </summary>
        public static List<SelectListItem> PaperType
        {
            get
            {
                return new List<SelectListItem>()
                {
                     new SelectListItem() { Text="真题", Value="1" } , 
                     new SelectListItem() { Text="模拟题" , Value="2"}
                };
            }
        }

        /// <summary>
        /// 试卷状态
        /// </summary>
        public static List<SelectListItem> PaperState
        {
            get
            {
                return new List<SelectListItem>() {
                    new SelectListItem() { Text="草稿", Value="1" } ,
                    new SelectListItem() { Text="发布", Value="2" }
                };
            }
        }

        /// <summary>
        /// 获取考点
        /// </summary>
        /// <param name="Id">考点ID</param>
        /// <returns>考点列表</returns>
        public static List<SelectListItem> GetKnowledge(int? Id)
        {
            var list = new List<SelectListItem>() { };
            if (!Id.HasValue)
            {
                return list;
            }
            ApplicationDbContext db = new ApplicationDbContext();
            if (Id <= 0) { return list; }
            var model = db.Knowledge.SingleOrDefault(b => b.ID == Id.Value);
            if (model == null)
            {
                return list;
            }
            list.Add(new SelectListItem() { Text = model.Content, Value = model.ID.ToString() });
            return list;
        }

        /// <summary>
        /// 获取试卷的题目组 即大题
        /// </summary>
        /// <param name="paperId">试卷ID</param>
        /// <returns>题目组</returns>
        public static List<SelectListItem> GetQuestionGroup(int? paperId)
        {
            var result = new List<SelectListItem>();
            if (paperId.HasValue)
            {
                ApplicationDbContext db = new ApplicationDbContext();
                var list = db.QuestionGroup.Where(a => a.PaperID == paperId.Value).ToList();
                if (list.Count > 0)
                {
                    list.ForEach(a =>
                    {
                        result.Add(new SelectListItem() { Text = a.Title, Value = a.ID.ToString() });
                    });
                }
            }
            return result;
        }

        /// <summary>
        /// 获取字典数据列表
        /// </summary>
        /// <param name="group"></param>
        /// <returns></returns>
        public static List<SelectListItem> GetQuestionEnum(EnumDictionaryGroup group)
        {
            var result = new List<SelectListItem>();
            ApplicationDbContext db = new ApplicationDbContext();
            var dicGroup = db.DictionaryGroup.SingleOrDefault(a=>a.ID==(int)group);
            if (dicGroup != null)
            {
                var dicItem = db.DictionaryItem.Where(a => a.GroupID == dicGroup.ID).ToList();
                if (dicItem.Count > 0)
                {
                    dicItem.ForEach(a=>{
                      result.Add(new SelectListItem() { Text=a.ItemName,Value=a.ItemKey.ToString() });
                    });
                }
            }
            return result;
        }

        /// <summary>
        /// 获取试卷的背景题
        /// </summary>
        /// <param name="paperId">试题ID</param>
        /// <returns></returns>
        public static List<SelectListItem> GetQuestionBackground(int? paperId)
        {
            var result = new List<SelectListItem>();
            if (paperId.HasValue)
            {
                ApplicationDbContext db = new ApplicationDbContext();
                var list = db.BackgroundQuestion.Where(a => a.PaperID == paperId.Value).ToList();
                if (list.Count > 0)
                {
                    result.Add(new SelectListItem() { Text="请选择",Value="" });
                    list.ForEach(a =>
                    {
                        result.Add(new SelectListItem() { Text = a.Title, Value = a.ID.ToString() });
                    });
                }
            }
            return result;
        }
    }
}