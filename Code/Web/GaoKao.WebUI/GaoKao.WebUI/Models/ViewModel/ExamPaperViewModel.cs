using System;
using System.ComponentModel.DataAnnotations;

namespace GaoKao.WebUI.Models.ViewModel
{
    public class ExamPaperViewModel
    {
        [Key]
        public int ID { get; set; }
        /// <summary>
        /// 试卷名称
        /// </summary>
        [Display(Name = "试卷名称")]
        public string Title { get; set; }
        /// <summary>
        /// 课程ID
        /// </summary>
        [Display(Name = "课程")]
        public string CourseName { get; set; }
        /// <summary>
        /// 难度
        /// </summary>
        [Display(Name = "难度系数")]
        public float Difficulty { get; set; }
        /// <summary>
        /// 课程类型 1理科 2文科
        /// </summary>
        [Display(Name = "课程类型")]
        public string CourseType { get; set; }
        /// <summary>
        /// 判断模拟 和真题  1：模拟2：真题
        /// </summary>
        [Display(Name = "试卷类型")]
        public String PaperType { get; set; }
        /// <summary>
        /// 0 草稿 1 发布
        /// </summary>
        [Display(Name = "试卷状态")]
        public String State { get; set; }
        /// <summary>
        /// 分数
        /// </summary>
        [Display(Name = "分数")]
        public int Score { get; set; }
        /// <summary>
        /// 地区
        /// </summary>
        [Display(Name = "地区")]
        public String District { get; set; }
        /// <summary>
        /// 考试时长
        /// </summary>
        [Display(Name = "考试时长")]
        public int ExamMinute { get; set; }
        /// <summary>
        /// 排序
        /// </summary>
        [Display(Name = "排序")]
        public int SortOrder { get; set; }
    }
}