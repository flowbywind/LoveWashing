using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Love.Washing.WebUI.Models
{
    /// <summary>
    /// 试题分组
    /// </summary>
    public class QuestionGroup {
        /// <summary>
        /// 主键
        /// </summary>
        [Key]
        public int ID { get; set; }
        /// <summary>
        /// 题目标题
        /// </summary>
        [Display(Name = "大题标题")]
        public string Title { get; set; }
        /// <summary>
        /// 题目描述
        /// </summary>
        [Display(Name="题目描述")]
        public string Detail { get; set; }
        /// <summary>
        /// 试卷ID
        /// </summary>
        [Display(Name="试卷")]
        public int PaperID { get; set; }
        /// <summary>
        /// 题目数量
        /// </summary>
        [Display(Name="题目数量")]
        public int Number { get; set; }
        /// <summary>
        /// 排序
        /// </summary>
        [Display(Name="排序")]
        public int SortOrder { get; set; }
        /// <summary>
        /// 创建时间
        /// </summary>
        public int CreatedTime { get; set; }
        /// <summary>
        /// 修改时间
        /// </summary>
        public int ModifiedTime { get; set; }
        /// <summary>
        /// 创建人
        /// </summary>
        public string CreatedPerson { get; set; }
        /// <summary>
        /// 修改人
        /// </summary>
        public string ModifiedPerson { get; set; }
        [Display(Name ="试卷")]
        [ForeignKey("PaperID")]
        public ExamPaper ExamPaper { get; set;}
    }
}
