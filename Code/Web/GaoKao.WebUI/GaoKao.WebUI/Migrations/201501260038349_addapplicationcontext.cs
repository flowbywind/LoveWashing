namespace GaoKao.WebUI.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class addapplicationcontext : DbMigration
    {
        public override void Up()
        {
            CreateTable(
                "dbo.Knowledges",
                c => new
                    {
                        ID = c.Int(nullable: false, identity: true),
                        ParentId = c.Int(nullable: false),
                        CourseType = c.Int(nullable: false),
                        Content = c.String(),
                        CourseID = c.Int(nullable: false),
                        State = c.Int(nullable: false),
                        Frequency = c.Int(nullable: false),
                        District = c.Int(nullable: false),
                        SortOrder = c.Int(nullable: false),
                        CreatedTime = c.Int(nullable: false),
                        ModifiedTime = c.Int(nullable: false),
                        CreatedPerson = c.String(),
                        ModifiedPerson = c.String(),
                    })
                .PrimaryKey(t => t.ID);
            
        }
        
        public override void Down()
        {
            DropTable("dbo.Knowledges");
        }
    }
}
