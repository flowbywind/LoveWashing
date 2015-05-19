namespace GaoKao.WebUI.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class addbackgroundnull : DbMigration
    {
        public override void Up()
        {
            AlterColumn("dbo.Questions", "BackgroundID", c => c.Int());
        }
        
        public override void Down()
        {
            AlterColumn("dbo.Questions", "BackgroundID", c => c.Int(nullable: false));
        }
    }
}
