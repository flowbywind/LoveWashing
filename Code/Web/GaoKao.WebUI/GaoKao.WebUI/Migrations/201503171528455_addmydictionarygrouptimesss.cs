namespace GaoKao.WebUI.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class addmydictionarygrouptimesss : DbMigration
    {
        public override void Up()
        {
            AddColumn("dbo.DictionaryGroups", "CreatedTime", c => c.Int(nullable: false));
            AddColumn("dbo.DictionaryGroups", "ModifiedTime", c => c.Int(nullable: false));
            AddColumn("dbo.DictionaryGroups", "CreatedPerson", c => c.String());
            AddColumn("dbo.DictionaryGroups", "ModifiedPerson", c => c.String());
            AddColumn("dbo.DictionaryItems", "CreatedTime", c => c.Int(nullable: false));
            AddColumn("dbo.DictionaryItems", "ModifiedTime", c => c.Int(nullable: false));
            AddColumn("dbo.DictionaryItems", "CreatedPerson", c => c.String());
            AddColumn("dbo.DictionaryItems", "ModifiedPerson", c => c.String());
        }
        
        public override void Down()
        {
            DropColumn("dbo.DictionaryItems", "ModifiedPerson");
            DropColumn("dbo.DictionaryItems", "CreatedPerson");
            DropColumn("dbo.DictionaryItems", "ModifiedTime");
            DropColumn("dbo.DictionaryItems", "CreatedTime");
            DropColumn("dbo.DictionaryGroups", "ModifiedPerson");
            DropColumn("dbo.DictionaryGroups", "CreatedPerson");
            DropColumn("dbo.DictionaryGroups", "ModifiedTime");
            DropColumn("dbo.DictionaryGroups", "CreatedTime");
        }
    }
}
