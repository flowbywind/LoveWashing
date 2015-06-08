using Abp.Domain.Entities;
using Abp.Domain.Entities.Auditing;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;

namespace EasyLife
{
    [Table("merchant")]
    public class Merchant : Entity,IFullAudited
    {
        public virtual string merchant_name { get; set; }

        public virtual ICollection<Tag> Tags { get; set; }
        public virtual ICollection<Member> Members { get; set; }

        public virtual string bank { get; set; }
        public virtual string account { get; set; }

        [ForeignKey("city_id")]
        public virtual City City { get; set; }

        public virtual int? city_id { get; set; }

        [ForeignKey("cat_id")]
        public virtual Category Category { get; set; }

        public virtual int? cat_id { get; set; }
        public virtual string contact_name { get; set; }
        public virtual string phone { get; set; }
        public virtual string email { get; set; }
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
