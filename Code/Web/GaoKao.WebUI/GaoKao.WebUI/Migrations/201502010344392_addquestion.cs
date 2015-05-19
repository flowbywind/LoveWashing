namespace GaoKao.WebUI.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class addquestion : DbMigration
    {
        public override void Up()
        {
            CreateTable(
                "dbo.Questions",
                c => new
                    {
                        ID = c.Int(nullable: false, identity: true),
                        Num = c.Int(nullable: false),
                        QuestionGroupID = c.Int(nullable: false),
                        BackgroundID = c.Int(nullable: false),
                        PaperID = c.Int(nullable: false),
                        KnowledgeID = c.Int(nullable: false),
                        CourseID = c.Int(nullable: false),
                        QuestionType = c.String(),
                        Score = c.Single(nullable: false),
                        MissScore = c.Single(nullable: false),
                        Difficulty = c.Single(nullable: false),
                        Title = c.String(),
                        Analysis = c.String(),
                        CreatedTime = c.Int(nullable: false),
                        ModifiedTime = c.Int(nullable: false),
                        CreatedPerson = c.String(),
                        ModifiedPerson = c.String(),
                        State = c.Int(nullable: false),
                    })
                .PrimaryKey(t => t.ID);
            
        }
        
        public override void Down()
        {
            DropTable("dbo.Questions");
        }
    }
}
