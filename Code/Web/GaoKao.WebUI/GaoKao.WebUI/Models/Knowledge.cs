using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.ComponentModel.DataAnnotations;
namespace GaoKao.WebUI.Models
{
    /// <summary>
    /// 考点表
    /// </summary>
    public class Knowledge {
        /// <summary>
        /// 主键
        /// </summary>
        [Key]
        public int ID { get; set; }
        /// <summary>
        /// 父ID
        /// </summary>
        [Display(Name = "父考点")]
        public int ParentId { get; set; }
        /// <summary>
        /// 课程类型 1理科 2文科
        /// </summary>
        [Display(Name = "课程类型")]
        public int CourseType { get; set;}
        /// <summary>
        /// 考点名称
        /// </summary>
        [Display(Name = "考点名称")]
        public string Content { get; set; }
        /// <summary>
        /// 课程ID
        /// </summary>
        [Display(Name = "课程ID")]
        public int CourseID { get; set; }
        /// <summary>
        /// 状态
        /// </summary>
        [Display(Name = "状态")]
        public int State { get; set; }
        /// <summary>
        /// 考频
        /// </summary>
        [Display(Name = "考频")]
        public int Frequency { get; set; }
        /// <summary>
        /// 地区
        /// </summary>
        [Display(Name = "地区")]
        public int District { get; set; }
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
