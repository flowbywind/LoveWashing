namespace GaoKao.WebUI.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class addquestionpaper : DbMigration
    {
        public override void Up()
        {
            AddForeignKey("dbo.Questions", "PaperID", "dbo.ExamPapers", "ID");
            CreateIndex("dbo.Questions", "PaperID");
        }
        
        public override void Down()
        {
            DropForeignKey("dbo.Questions", "PaperID", "dbo.ExamPapers");
        }
    }
}
