using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace Love.Washing.WebUI.Models.ViewModel {
    public class KnowledgeViewModel {
        [Key]
        public int ID { get; set; }
        /// <summary>
        /// 考点
        /// </summary>
        [Display(Name = "考点名称")]
        public string Content { get; set; }
        /// <summary>
        /// 父ID
        /// </summary>
        [Display(Name="父ID")]
        public int ParentId { get; set; }
        /// <summary>
        /// 课程ID
        /// </summary>
        [Display(Name = "课程")]
        public string CourseName { get; set; }
        /// <summary>
        /// 课程类型 1理科 2文科
        /// </summary>
        [Display(Name = "课程类型")]
        public string CourseType { get; set; }
        /// <summary>
        /// 0 草稿 1 发布
        /// </summary>
        [Display(Name = "试卷状态")]
        public String State { get; set; }
        /// <summary>
        /// 地区
        /// </summary>
        [Display(Name = "地区")]
        public String District { get; set; }
        /// <summary>
        /// 排序
        /// </summary>
        [Display(Name = "排序")]
        public int SortOrder { get; set; }
        /// <summary>
        /// 考频
        /// </summary>
        [Display(Name = "考频")]
        public int Frequency { get; set; }
        [Display(Name = "修改时间")]
        public DateTime ModifiedTime { get; set; }

    }
}