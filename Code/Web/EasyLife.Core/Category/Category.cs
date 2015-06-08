using Abp.Domain.Entities;
using Abp.Domain.Entities.Auditing;
using System;
using System.ComponentModel.DataAnnotations.Schema;

namespace EasyLife
{
    [Table("category")]
    public class Category : Entity, IFullAudited
    {
        public virtual string cat_name { get; set; }

        public virtual string cat_code { get; set; }

        public virtual Status status { get; set; }

        public virtual long? CreatorUserId { get; set; }

        public virtual DateTime CreationTime { get; set; }

        public virtual DateTime? LastModificationTime { get; set; }

        public virtual long? LastModifierUserId { get; set; }

        public virtual long? DeleterUserId { get; set; }

        public virtual DateTime? DeletionTime { get; set; }

        public virtual bool IsDeleted { get; set; }
    }
}
