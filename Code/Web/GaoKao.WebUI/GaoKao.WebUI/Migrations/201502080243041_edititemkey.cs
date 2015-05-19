namespace GaoKao.WebUI.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class edititemkey : DbMigration
    {
        public override void Up()
        {
            AlterColumn("dbo.DictionaryItems", "ItemKey", c => c.String());
        }
        
        public override void Down()
        {
            AlterColumn("dbo.DictionaryItems", "ItemKey", c => c.Int(nullable: false));
        }
    }
}
