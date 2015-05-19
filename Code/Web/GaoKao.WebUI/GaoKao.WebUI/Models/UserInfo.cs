using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.ComponentModel.DataAnnotations;
namespace GaoKao.WebUI.Models
{
    /// <summary>
    /// 用户信息
    /// </summary>
    public class UserInfo
    {
        [Key]
        public int ID { get;set;}
        /// <summary>
        /// 设备ID
        /// </summary>
        public string EquimentId { get; set; }
        /// <summary>
        /// 头像URL
        /// </summary>
        public string HeadUrl { get; set; }
        /// <summary>
        /// 积分
        /// </summary>
        public int Score { get; set; }
        /// <summary>
        /// 答题数量
        /// </summary>
        public int AnswerCount { get; set; }

    }
}
