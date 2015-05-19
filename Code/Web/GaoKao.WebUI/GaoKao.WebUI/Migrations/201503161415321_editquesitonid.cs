namespace GaoKao.WebUI.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class editquesitonid : DbMigration
    {
        public override void Up()
        {
            AlterColumn("dbo.QuestionItems", "QuestionID", c => c.Int(nullable: false));
        }
        
        public override void Down()
        {
            AlterColumn("dbo.QuestionItems", "QuestionID", c => c.String());
        }
    }
}
