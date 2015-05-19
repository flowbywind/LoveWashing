using System;
using System.ComponentModel;

namespace Love.Washing.WebUI
{

    /// <summary>
    /// 课程
    /// </summary>
    public enum EnumCourse
    {

        [Description("语文")]
        Chinese = 1,
        [DescriptionAttribute("数学")]
        Math = 2,
        [DescriptionAttribute("英语")]
        English = 3,
        [DescriptionAttribute("物理")]
        Physics = 4,
        [Description("化学")]
        Chemistry = 5,
        [Description("生物")]
        Biology = 6,
        [Description("政治")]
        Politics = 7,
        [Description("历史")]
        History = 8,
        [Description("地理")]
        Geography = 9
    }
    /// <summary>
    /// 课程类型
    /// </summary>
    public enum EnumCourseType
    {
        [Description("理科")]
        Science = 1,
        [Description("文科")]
        Art = 2
    }
    /// <summary>
    /// 城市
    /// </summary>
    public enum EnumCity
    {
        [Description("北京")]
        Beijing = 1
    }
    /// <summary>
    /// 试题类型
    /// </summary>
    public enum EnumPaperType
    {
        [Description("真题")]
        Real = 1,
        [Description("模拟题")]
        Simulate = 2
    }
    /// <summary>
    /// 试卷状态
    /// </summary>
    public enum EnumPaperState
    {
        [Description("草稿")]
        Draft = 1,
        [Description("发布")]
        Publish = 2
    }
    /// <summary>
    /// 字典表的对应值
    /// </summary>
    public enum EnumDictionaryGroup
    {
        [Description("试题类型")]
        QuestionType = 1,
        [Description("试题分数")]
        QuestionScore = 2,
        [Description("试题少选得分")]
        QuestionMissScore = 3,
        [Description("难度系数")]
        QuestionDifficulty = 4
    }

}