namespace Love.Washing.WebUI.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class AddBlogUrl : DbMigration
    {
        public override void Up()
        {
            CreateTable(
                "dbo.BackgroundQuestions",
                c => new
                    {
                        ID = c.Int(nullable: false, identity: true),
                        Title = c.String(nullable: false),
                        Content = c.String(),
                        PaperID = c.Int(nullable: false),
                        SortOrder = c.Int(nullable: false),
                        CreatedTime = c.Int(nullable: false),
                        ModifiedTime = c.Int(nullable: false),
                        CreatedPerson = c.String(),
                        ModifiedPerson = c.String(),
                    })
                .PrimaryKey(t => t.ID)
                .ForeignKey("dbo.ExamPapers", t => t.PaperID, cascadeDelete: true)
                .Index(t => t.PaperID);
            
            CreateTable(
                "dbo.ExamPapers",
                c => new
                    {
                        ID = c.Int(nullable: false, identity: true),
                        Title = c.String(nullable: false),
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
                "dbo.DictionaryGroups",
                c => new
                    {
                        ID = c.Int(nullable: false, identity: true),
                        GroupName = c.String(),
                        CreatedTime = c.Int(nullable: false),
                        ModifiedTime = c.Int(nullable: false),
                        CreatedPerson = c.String(),
                        ModifiedPerson = c.String(),
                    })
                .PrimaryKey(t => t.ID);
            
            CreateTable(
                "dbo.DictionaryItems",
                c => new
                    {
                        ID = c.Int(nullable: false, identity: true),
                        GroupID = c.Int(nullable: false),
                        ItemKey = c.String(),
                        ItemName = c.String(),
                        CreatedTime = c.Int(nullable: false),
                        ModifiedTime = c.Int(nullable: false),
                        CreatedPerson = c.String(),
                        ModifiedPerson = c.String(),
                    })
                .PrimaryKey(t => t.ID)
                .ForeignKey("dbo.DictionaryGroups", t => t.GroupID, cascadeDelete: true)
                .Index(t => t.GroupID);
            
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
            
            CreateTable(
                "dbo.Questions",
                c => new
                    {
                        ID = c.Int(nullable: false, identity: true),
                        Num = c.Int(nullable: false),
                        QuestionGroupID = c.Int(nullable: false),
                        BackgroundID = c.Int(),
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
                        Answer = c.String(),
                    })
                .PrimaryKey(t => t.ID)
                .ForeignKey("dbo.ExamPapers", t => t.PaperID, cascadeDelete: true)
                .Index(t => t.PaperID);
            
            CreateTable(
                "dbo.QuestionItems",
                c => new
                    {
                        ID = c.Int(nullable: false, identity: true),
                        Key = c.String(),
                        Content = c.String(),
                        QuestionID = c.Int(nullable: false),
                        CreatedTime = c.Int(nullable: false),
                        ModifiedTime = c.Int(nullable: false),
                        CreatedPerson = c.String(),
                        ModifiedPerson = c.String(),
                    })
                .PrimaryKey(t => t.ID)
                .ForeignKey("dbo.Questions", t => t.QuestionID, cascadeDelete: true)
                .Index(t => t.QuestionID);
            
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
                    })
                .PrimaryKey(t => t.ID)
                .ForeignKey("dbo.ExamPapers", t => t.PaperID, cascadeDelete: true)
                .Index(t => t.PaperID);
            
        }
        
        public override void Down()
        {
            DropForeignKey("dbo.QuestionGroups", "PaperID", "dbo.ExamPapers");
            DropForeignKey("dbo.QuestionItems", "QuestionID", "dbo.Questions");
            DropForeignKey("dbo.Questions", "PaperID", "dbo.ExamPapers");
            DropForeignKey("dbo.DictionaryItems", "GroupID", "dbo.DictionaryGroups");
            DropForeignKey("dbo.BackgroundQuestions", "PaperID", "dbo.ExamPapers");
            DropIndex("dbo.QuestionGroups", new[] { "PaperID" });
            DropIndex("dbo.QuestionItems", new[] { "QuestionID" });
            DropIndex("dbo.Questions", new[] { "PaperID" });
            DropIndex("dbo.DictionaryItems", new[] { "GroupID" });
            DropIndex("dbo.BackgroundQuestions", new[] { "PaperID" });
            DropTable("dbo.QuestionGroups");
            DropTable("dbo.QuestionItems");
            DropTable("dbo.Questions");
            DropTable("dbo.Knowledges");
            DropTable("dbo.DictionaryItems");
            DropTable("dbo.DictionaryGroups");
            DropTable("dbo.ExamPapers");
            DropTable("dbo.BackgroundQuestions");
        }
    }
}
