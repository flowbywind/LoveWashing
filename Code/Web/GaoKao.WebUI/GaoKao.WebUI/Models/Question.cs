using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
namespace GaoKao.WebUI.Models
{
    /// <summary>
    /// 问题表
    /// </summary>
    public class Question {
        /// <summary>
        /// 主键
        /// </summary>
        [Key]
        public int ID { get; set; }
        /// <summary>
        /// 试题编号
        /// </summary>
        [Display(Name = "试题编号")]
        public int Num { get; set; }
        /// <summary>
        /// 题目所属组 如基础知识 选择题  阅读理解  古诗文 计算题 等
        /// </summary>
        [Display(Name = "所属大题")]
        public int QuestionGroupID { get; set; }
        /// <summary>
        /// 背景题ID
        /// </summary>
        [Display(Name = "背景题")]
        public int? BackgroundID { get; set; }
        /// <summary>
        /// 试卷ID
        /// </summary>
        [Display(Name = "所属试卷")]
        public int PaperID { get; set; }
        /// <summary>
        /// 考点
        /// </summary>
        [Display(Name = "所属考点")]
        public int KnowledgeID { get; set; }
        /// <summary>
        /// 课程ID
        /// </summary>
        [Display(Name = "所属课程")]
        public int CourseID { get; set; }
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
        /// 创建时间
        /// </summary>
        public int CreatedTime { get; set; }
        /// <summary>
        /// 修改时间
        /// </summary>
        [Display(Name="修改时间")]
        public int ModifiedTime { get; set; }
        /// <summary>
        /// 创建人
        /// </summary>
        [Display(Name="创建人")]
        public string CreatedPerson { get; set; }
        /// <summary>
        /// 修改人
        /// </summary>
        [Display(Name="修改人")]
        public string ModifiedPerson { get; set; }
        /// <summary>
        /// 状态 0 草稿 1题目校验通过  2发布 3失效
        /// </summary>
        [Display(Name = "状态")]
        public int State { get; set; }
        /// <summary>
        /// 答案
        /// </summary>
        [Display(Name="答案")]
        public string Answer { get; set; }
        /// <summary>
        /// 试卷
        /// </summary>
        [ForeignKey("PaperID")]
        public ExamPaper ExamPaper { get; set; }
        /// <summary>
        /// 问题选项
        /// </summary>
        public IList<QuestionItem> QuestionItems { get; set; }
    }
}
