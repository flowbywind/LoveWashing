namespace GaoKao.WebUI.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class addquestionForeignKey : DbMigration
    {
        public override void Up()
        {
            //DropForeignKey("dbo.Questions", "ExamPaper_ID", "dbo.ExamPapers");
            //DropIndex("dbo.Questions", new[] { "ExamPaper_ID" });
            //RenameColumn(table: "dbo.Questions", name: "ExamPaper_ID", newName: "PaperID");
            //AddForeignKey("dbo.Questions", "PaperID", "dbo.ExamPapers", "ID", cascadeDelete: true);
            //CreateIndex("dbo.Questions", "PaperID");
        }
        
        public override void Down()
        {
            //DropIndex("dbo.Questions", new[] { "PaperID" });
            //DropForeignKey("dbo.Questions", "PaperID", "dbo.ExamPapers");
            //RenameColumn(table: "dbo.Questions", name: "PaperID", newName: "ExamPaper_ID");
            //CreateIndex("dbo.Questions", "ExamPaper_ID");
            //AddForeignKey("dbo.Questions", "ExamPaper_ID", "dbo.ExamPapers", "ID");
        }
    }
}
