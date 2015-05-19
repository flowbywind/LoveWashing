namespace GaoKao.WebUI.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class adddictiongroup : DbMigration
    {
        public override void Up()
        {
            CreateTable(
                "dbo.DictionaryGroups",
                c => new
                    {
                        ID = c.Int(nullable: false, identity: true),
                        GroupName = c.String(),
                    })
                .PrimaryKey(t => t.ID);
            
            CreateTable(
                "dbo.DictionaryItems",
                c => new
                    {
                        ID = c.Int(nullable: false, identity: true),
                        GroupID = c.Int(nullable: false),
                        ItemKey = c.Int(nullable: false),
                        ItemName = c.String(),
                    })
                .PrimaryKey(t => t.ID)
                .ForeignKey("dbo.DictionaryGroups", t => t.GroupID, cascadeDelete: true)
                .Index(t => t.GroupID);
            
        }
        
        public override void Down()
        {
            DropIndex("dbo.DictionaryItems", new[] { "GroupID" });
            DropForeignKey("dbo.DictionaryItems", "GroupID", "dbo.DictionaryGroups");
            DropTable("dbo.DictionaryItems");
            DropTable("dbo.DictionaryGroups");
        }
    }
}
