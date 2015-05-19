using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Web;

namespace GaoKao.WebUI.Models
{
    /// <summary>
    /// 字典表
    /// </summary>
    public class DictionaryGroup
    {
        /// <summary>
        /// 主键
        /// </summary>
        public int ID { get; set; }
        /// <summary>
        /// 字典表组名
        /// </summary>
        public string GroupName { get; set; }
        /// <summary>
        /// 创建时间
        /// </summary>
        [Display(Name = "创建时间")]
        public int CreatedTime { get; set; }
        /// <summary>
        /// 修改时间
        /// </summary>
        [Display(Name = "修改时间")]
        public int ModifiedTime { get; set; }
        /// <summary>
        /// 创建人
        /// </summary>
        [Display(Name = "创建人")]
        public string CreatedPerson { get; set; }
        /// <summary>
        /// 修改人
        /// </summary>
        [Display(Name = "修改人")]
        public string ModifiedPerson { get; set; }
    }

    /// <summary>
    /// 字典选项
    /// </summary>
    public class DictionaryItem {
        /// <summary>
        /// 主键
        /// </summary>
        [Key]
        [Display(Name="主键")]
        public int ID { get; set; }
        /// <summary>
        /// 组ID
        /// </summary>
        [Display(Name="组ID")]
        public int GroupID { get; set; }
        /// <summary>
        /// 选项ID
        /// </summary>
        [Display(Name="选项Key")]
        public string ItemKey { get; set; }
        /// <summary>
        /// 选项名
        /// </summary>
        [Display(Name="选项内容")]
        public string ItemName { get; set; }
        /// <summary>
        /// 外键
        /// </summary>
        [ForeignKey("GroupID")]
        public DictionaryGroup DictionaryGroup { get; set; }
        /// <summary>
        /// 创建时间
        /// </summary>
        [Display(Name = "创建时间")]
        public int CreatedTime { get; set; }
        /// <summary>
        /// 修改时间
        /// </summary>
        [Display(Name = "修改时间")]
        public int ModifiedTime { get; set; }
        /// <summary>
        /// 创建人
        /// </summary>
        [Display(Name = "创建人")]
        public string CreatedPerson { get; set; }
        /// <summary>
        /// 修改人
        /// </summary>
        [Display(Name = "修改人")]
        public string ModifiedPerson { get; set; }
    }
}