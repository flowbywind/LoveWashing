namespace GaoKao.WebUI.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class addforeignkeys : DbMigration
    {
        public override void Up()
        {
            DropForeignKey("dbo.QuestionGroups", "ExamPaper_ID", "dbo.ExamPapers");
            DropForeignKey("dbo.BackgroundQuestions", "ExamPaper_ID", "dbo.ExamPapers");
            DropIndex("dbo.QuestionGroups", new[] { "ExamPaper_ID" });
            DropIndex("dbo.BackgroundQuestions", new[] { "ExamPaper_ID" });
            AddForeignKey("dbo.QuestionGroups", "PaperID", "dbo.ExamPapers", "ID", cascadeDelete: true);
            AddForeignKey("dbo.BackgroundQuestions", "PaperID", "dbo.ExamPapers", "ID", cascadeDelete: true);
            CreateIndex("dbo.QuestionGroups", "PaperID");
            CreateIndex("dbo.BackgroundQuestions", "PaperID");
        }
        
        public override void Down()
        {
            DropIndex("dbo.BackgroundQuestions", new[] { "PaperID" });
            DropIndex("dbo.QuestionGroups", new[] { "PaperID" });
            DropForeignKey("dbo.BackgroundQuestions", "PaperID", "dbo.ExamPapers");
            DropForeignKey("dbo.QuestionGroups", "PaperID", "dbo.ExamPapers");
            CreateIndex("dbo.BackgroundQuestions", "ExamPaper_ID");
            CreateIndex("dbo.QuestionGroups", "ExamPaper_ID");
            AddForeignKey("dbo.BackgroundQuestions", "ExamPaper_ID", "dbo.ExamPapers", "ID");
            AddForeignKey("dbo.QuestionGroups", "ExamPaper_ID", "dbo.ExamPapers", "ID");
        }
    }
}
