namespace GaoKao.WebUI.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class InitialCreate : DbMigration
    {
        public override void Up()
        {
            CreateTable(
                "dbo.ExamPapers",
                c => new
                    {
                        ID = c.Int(nullable: false, identity: true),
                        Title = c.String(),
                        CourseID = c.Int(nullable: false),
                        Difficulty = c.Single(nullable: false),
                        CourseType = c.Int(nullable: false),
                        PaperType = c.Int(nullable: false),
                        State = c.Int(nullable: false),
                        Score = c.Int(nullable: false),
                        District = c.Int(nullable: false),
                        ExamMinute = c.Int(nullable: false),
                        SortOrder = c.Int(nullable: false),
                        CreatedTime = c.Int(nullable: false),
                        ModifiedTime = c.Int(nullable: false),
                        CreatedPerson = c.String(),
                        ModifiedPerson = c.String(),
                    })
                .PrimaryKey(t => t.ID);
            
            CreateTable(
                "dbo.QuestionGroups",
                c => new
                    {
                        ID = c.Int(nullable: false, identity: true),
                        Title = c.String(),
                        Detail = c.String(),
                        PaperID = c.Int(nullable: false),
                        Number = c.Int(nullable: false),
                        SortOrder = c.Int(nullable: false),
                        CreatedTime = c.Int(nullable: false),
                        ModifiedTime = c.Int(nullable: false),
                        CreatedPerson = c.String(),
                        ModifiedPerson = c.String(),
                        ExamPaper_ID = c.Int(),
                    })
                .PrimaryKey(t => t.ID)
                .ForeignKey("dbo.ExamPapers", t => t.ExamPaper_ID)
                .Index(t => t.ExamPaper_ID);
            
            CreateTable(
                "dbo.BackgroundQuestions",
                c => new
                    {
                        ID = c.Int(nullable: false, identity: true),
                        Title = c.String(),
                        Content = c.String(),
                        PaperID = c.Int(nullable: false),
                        SortOrder = c.Int(nullable: false),
                        CreatedTime = c.Int(nullable: false),
                        ModifiedTime = c.Int(nullable: false),
                        CreatedPerson = c.String(),
                        ModifiedPerson = c.String(),
                        ExamPaper_ID = c.Int(),
                    })
                .PrimaryKey(t => t.ID)
                .ForeignKey("dbo.ExamPapers", t => t.ExamPaper_ID)
                .Index(t => t.ExamPaper_ID);
            
        }
        
        public override void Down()
        {
            DropIndex("dbo.BackgroundQuestions", new[] { "ExamPaper_ID" });
            DropIndex("dbo.QuestionGroups", new[] { "ExamPaper_ID" });
            DropForeignKey("dbo.BackgroundQuestions", "ExamPaper_ID", "dbo.ExamPapers");
            DropForeignKey("dbo.QuestionGroups", "ExamPaper_ID", "dbo.ExamPapers");
            DropTable("dbo.BackgroundQuestions");
            DropTable("dbo.QuestionGroups");
            DropTable("dbo.ExamPapers");
        }
    }
}
