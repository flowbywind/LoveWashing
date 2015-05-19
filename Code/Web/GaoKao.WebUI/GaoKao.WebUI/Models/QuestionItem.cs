using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.ComponentModel.DataAnnotations;
namespace GaoKao.WebUI.Models
{
    public class QuestionItem {
        /// <summary>
        /// 主键
        /// </summary>
        [Key]
        public int ID { get; set; }
        /// <summary>
        /// 选项key A/B/C/D
        /// </summary>
        [Display(Name="选项KEY")]
        public string Key { get; set; }
        /// <summary>
        /// 选项内容
        /// </summary>
        [Display(Name="选项内容")]
        public string Content { get; set; }
        /// <summary>
        /// 问题ID
        /// </summary>
        [Display(Name="所属问题")]
        public int QuestionID { get; set; }

        /// <summary>
        /// 创建时间
        /// </summary>
        [Display(Name="创建时间")]
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
