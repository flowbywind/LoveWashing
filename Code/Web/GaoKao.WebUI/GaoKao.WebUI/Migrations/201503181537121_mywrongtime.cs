namespace GaoKao.WebUI.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class mywrongtime : DbMigration
    {
        public override void Up()
        {
        
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
