using FluentMigrator.VersionTableInfo;

namespace EasyLife.DbMigrations
{
    [VersionTableMetaData]
    public class VersionTable : DefaultVersionTableMetaData
    {
        public override string TableName
        {
            get
            {
                return "MyAppVersionInfo";
            }
        }
    }
}
