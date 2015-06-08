using Abp.Domain.Entities;
using Abp.Domain.Entities.Auditing;
using System;
using System.ComponentModel.DataAnnotations.Schema;

namespace EasyLife
{
    [Table("city")]
    public class City : Entity, IFullAudited
    {
        public virtual string city_name { get; set; }
        public virtual string pin_yin { get; set; }
        public virtual string hot { get; set; }
        public virtual int parent_id { get; set; }
        public virtual int first_char { get; set; }
        public virtual long? CreatorUserId { get; set; }

        public virtual DateTime CreationTime { get; set; }

        public virtual DateTime? LastModificationTime { get; set; }

        public virtual long? LastModifierUserId { get; set; }

        public virtual long? DeleterUserId { get; set; }

        public virtual DateTime? DeletionTime { get; set; }

        public virtual bool IsDeleted { get; set; }
    }
}
