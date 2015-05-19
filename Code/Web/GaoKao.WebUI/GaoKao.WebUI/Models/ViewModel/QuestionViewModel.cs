using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace GaoKao.WebUI.Models.ViewModel
{
    public class QuestionViewModel
    {
        /// <summary>
        /// 主键
        /// </summary>
        public int ID { get; set; }
        /// <summary>
        /// 试题编号
        /// </summary>
        [Display(Name = "试题编号")]
        public int Num { get; set; }
        /// <summary>
        /// 背景题
        /// </summary>
        [Display(Name="背景题")]
        public string BackgroundName { get; set; }
        /// <summary>
        /// 试卷名称
        /// </summary>
        [Display(Name="试卷名称")]
        public string PaperName { get; set; }
        /// <summary>
        /// 知识点
        /// </summary>
        [Display(Name="知识点")]
        public string KnowledgeName { get; set; }
        /// <summary>
        /// 课程ID
        /// </summary>
        public int CourseID { get; set; }
        /// <summary>
        /// 课程名称
        /// </summary>
        [Display(Name="课程名称")]
        public string CourseName { get; set; }
        /// <summary>
        /// 题目类型 单项选择题 多项选择题 不定项选择题 主观题
        /// </summary>
        [Display(Name = "试题类型")]
        public string QuestionType { get; set; }
        /// <summary>
        /// 分数
        /// </summary>
        [Display(Name = "分数")]
        public float Score { get; set; }
        /// <summary>
        /// 少选得分
        /// </summary>
        [Display(Name = "少选得分")]
        public float MissScore { get; set; }
        /// <summary>
        /// 难度系数
        /// </summary>
        [Display(Name = "难度系数")]
        public float Difficulty { get; set; }
        /// <summary>
        /// 问题标题
        /// </summary>
        [Display(Name = "题干")]
        public string Title { get; set; }
        /// <summary>
        /// 试题解析
        /// </summary>
        [Display(Name = "试题解析")]
        public string Analysis { get; set; }
        /// <summary>
        /// 修改时间
        /// </summary>
        public int ModifiedTime { get; set; }
        /// <summary>
        /// 修改人
        /// </summary>
        public string ModifiedPerson { get; set; }
        /// <summary>
        /// 状态 0 草稿 1题目校验通过  2发布 3失效
        /// </summary>
        [Display(Name = "状态")]
        public string Status { get; set; }
        public int State { get; set; }
    }
}