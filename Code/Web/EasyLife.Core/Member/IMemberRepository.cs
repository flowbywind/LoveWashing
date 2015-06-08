using Abp.Domain.Repositories;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace EasyLife
{
    public interface IMemberRepository : IRepository<Member, int>
    {
        List<Member> GetAllByMerchantID(int? merchantId);
    }
}
