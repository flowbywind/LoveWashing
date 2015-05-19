using System;
using System.Collections.Generic;
using System.Linq;
using System.Transactions;
using System.Web;
using System.Web.Mvc;
using System.Web.Security;
using DotNetOpenAuth.AspNet;
using Microsoft.Web.WebPages.OAuth;
using WebMatrix.WebData;
using System.Data.Entity;

namespace GaoKao.WebUI.Models
{
    public class ApplicationDbContext : DbContext
    {
        private static bool _created = false;
        public DbSet<ExamPaper> ExamPaper { get; set; }
        public DbSet<QuestionGroup> QuestionGroup { get; set; }
        public DbSet<BackgroundQuestion> BackgroundQuestion { get; set; }
        public DbSet<Knowledge> Knowledge { get; set; }
        public DbSet<Question> Question { get; set; }
        public DbSet<QuestionItem> QuestionItem { get; set; }
        public DbSet<DictionaryGroup> DictionaryGroup { get; set; }
        public DbSet<DictionaryItem> DictionaryItem { get; set; }
        public ApplicationDbContext()
            : base("DefaultConnection")
        {
        }
    }
}