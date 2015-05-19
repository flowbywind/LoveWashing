namespace GaoKao.WebUI.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class addquestionitem : DbMigration
    {
        public override void Up()
        {
            CreateTable(
                "dbo.QuestionItems",
                c => new
                    {
                        ID = c.Int(nullable: false, identity: true),
                        Key = c.String(),
                        Content = c.String(),
                        QuestionID = c.String(),
                    })
                .PrimaryKey(t => t.ID);
            
        }
        
        public override void Down()
        {
            DropTable("dbo.QuestionItems");
        }
    }
}
