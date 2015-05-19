using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.ComponentModel.DataAnnotations;
namespace Love.Washing.WebUI.Models
{
    /// <summary>
    /// 试卷表
    /// </summary>
    public class ExamPaper {
        [Key]
        public int ID { get; set; }
        /// <summary>
        /// 试卷名称
        /// </summary>
        [Display(Name = "试卷名称")]
        [Required(AllowEmptyStrings=false,ErrorMessage="试卷名称不得为空")]
        public string Title { get; set; }
        /// <summary>
        /// 课程ID
        /// </summary>
        [Display(Name = "课程")]
        public int CourseID { get; set; }
        /// <summary>
        /// 难度
        /// </summary>
        [Display(Name = "难度系数")]
        [RegularExpression(@"^\d+(\.\d+)?$", ErrorMessage ="请输入整数或小数")]
        public float Difficulty { get; set; }
        /// <summary>
        /// 课程类型 1理科 2文科
        /// </summary>
        [Display(Name = "课程类型")]
        public int CourseType { get; set; }
        /// <summary>
        /// 判断模拟 和真题  1：模拟2：真题
        /// </summary>
        [Display(Name = "试卷类型")]
        public int PaperType { get; set; }
        /// <summary>
        /// 0 草稿 1 发布
        /// </summary>
        [Display(Name = "试卷状态")]
        public int State { get; set; }
        /// <summary>
        /// 分数
        /// </summary>
        [Display(Name = "分数")]
        public int Score { get; set; }
        /// <summary>
        /// 地区
        /// </summary>
        [Display(Name = "地区")]
        public int District { get; set; }
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
        [Display(Name = "创建时间")]
        public int CreatedTime { get; set; }
        [Display(Name = "修改时间")]
        public int ModifiedTime { get; set; }
        [Display(Name = "创建人")]
        public string CreatedPerson { get; set; }
        [Display(Name = "修改人")]
        public string ModifiedPerson { get; set; }
      

    }
}
