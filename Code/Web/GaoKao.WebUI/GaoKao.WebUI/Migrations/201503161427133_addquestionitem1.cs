namespace GaoKao.WebUI.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class addquestionitem1 : DbMigration
    {
        public override void Up()
        {
            AddForeignKey("dbo.QuestionItems", "QuestionID", "dbo.Questions", "ID", cascadeDelete: true);
            CreateIndex("dbo.QuestionItems", "QuestionID");
        }
        
        public override void Down()
        {
            DropIndex("dbo.QuestionItems", new[] { "QuestionID" });
            DropForeignKey("dbo.QuestionItems", "QuestionID", "dbo.Questions");
        }
    }
}
