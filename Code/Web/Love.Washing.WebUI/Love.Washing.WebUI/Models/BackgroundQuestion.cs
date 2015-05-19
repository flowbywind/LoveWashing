using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Love.Washing.WebUI.Models
{
    public class BackgroundQuestion {

        /// <summary>
        /// 主键
        /// </summary>
        [Key]
        public int ID { get; set; }
        /// <summary>
        /// 背景题标题
        /// </summary>
        [Display(Name = "背景题标题")]
        [Required(AllowEmptyStrings=false,ErrorMessage="标题不得为空")]
        public string Title { get; set; }
        /// <summary>
        /// 背景题内容
        /// </summary>
        [Display(Name = "背景题内容")]
        public string Content { get; set; }
        /// <summary>
        /// 试卷ID
        /// </summary>
        [Display(Name = "试卷")]
        public int PaperID { get; set; }
        /// <summary>
        /// 排序
        /// </summary>
        [Display(Name = "排序")]
        public int SortOrder { get; set; }
        /// <summary>
        /// 创建时间
        /// </summary>
        [Display(Name ="创建时间")]
        public int CreatedTime { get; set; }
        /// <summary>
        /// 修改时间
        /// </summary>
        [Display(Name ="修改时间")]
        public int ModifiedTime { get; set; }
        /// <summary>
        /// 创建人
        /// </summary>
        [Display(Name ="创建人")]
        public string CreatedPerson { get; set; }
        /// <summary>
        /// 修改人
        /// </summary>
        [Display(Name ="修改人")]
        public string ModifiedPerson { get; set; }
        /// <summary>
        /// 试卷
        /// </summary>
        [ForeignKey("PaperID")]
        public ExamPaper ExamPaper { get; set; }
    }
}
