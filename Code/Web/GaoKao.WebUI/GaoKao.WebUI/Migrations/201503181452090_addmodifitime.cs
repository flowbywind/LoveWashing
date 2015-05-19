namespace GaoKao.WebUI.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class addmodifitime : DbMigration
    {
        public override void Up()
        {
            AddColumn("dbo.QuestionItems", "CreatedTime", c => c.Int(nullable: false));
            AddColumn("dbo.QuestionItems", "ModifiedTime", c => c.Int(nullable: false));
            AddColumn("dbo.QuestionItems", "CreatedPerson", c => c.String());
            AddColumn("dbo.QuestionItems", "ModifiedPerson", c => c.String());
        }
        
        public override void Down()
        {
            DropColumn("dbo.QuestionItems", "ModifiedPerson");
            DropColumn("dbo.QuestionItems", "CreatedPerson");
            DropColumn("dbo.QuestionItems", "ModifiedTime");
            DropColumn("dbo.QuestionItems", "CreatedTime");
        }
    }
}
