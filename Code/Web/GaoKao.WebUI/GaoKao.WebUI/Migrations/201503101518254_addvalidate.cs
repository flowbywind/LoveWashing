namespace GaoKao.WebUI.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class addvalidate : DbMigration
    {
        public override void Up()
        {
            AlterColumn("dbo.ExamPapers", "Title", c => c.String(nullable: false));
            AlterColumn("dbo.BackgroundQuestions", "Title", c => c.String(nullable: false));
        }
        
        public override void Down()
        {
            AlterColumn("dbo.BackgroundQuestions", "Title", c => c.String());
            AlterColumn("dbo.ExamPapers", "Title", c => c.String());
        }
    }
}
