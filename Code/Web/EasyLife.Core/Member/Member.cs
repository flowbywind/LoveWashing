using Abp.Domain.Entities;
using Abp.Domain.Entities.Auditing;
using System;
using System.ComponentModel.DataAnnotations.Schema;

namespace EasyLife
{
    [Table("member")]
    public class Member : Entity, IFullAudited
    {
        public virtual string member_name { get; set; }
        public virtual string member_sex { get; set; }
        public virtual string member_birthday { get; set; }
        public virtual string member_phone { get; set; }
        public virtual string member_address { get; set; }

        public virtual Status status { get; set; }

        [ForeignKey("merchant_id")]
        public virtual Merchant Merchant { get; set; }

        public virtual int? merchant_id { get; set; }
        public virtual long? CreatorUserId { get; set; }

        public virtual DateTime CreationTime { get; set; }

        public virtual DateTime? LastModificationTime { get; set; }

        public virtual long? LastModifierUserId { get; set; }

        public virtual long? DeleterUserId { get; set; }

        public virtual DateTime? DeletionTime { get; set; }

        public virtual bool IsDeleted { get; set; }
    }
}
