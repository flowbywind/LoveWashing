using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace GaoKao.WebUI.Models.ViewModel
{
    public class ResourceDataViewModel
    {
        public IList<ExamPaper> ExamPaper { get; set; }
        public IList<QuestionGroup> QuestionGroup { get; set; }
        public IList<BackgroundQuestion> BackgroundQuestion { get; set; }
        public IList<Question> Question { get; set; }
        public IList<QuestionItem> QuestionItem { get; set; }
        public IList<Knowledge> Knowledge { get; set; }
        public IList<DictionaryGroup> DictionaryGroup { get; set; }
        public IList<DictionaryItem> DictionaryItem { get; set; }
    }
}