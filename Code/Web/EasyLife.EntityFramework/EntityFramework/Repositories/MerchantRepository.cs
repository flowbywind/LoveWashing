using System;
using Abp.Domain.Entities;
using Abp.EntityFramework;
using Abp.EntityFramework.Repositories;

namespace EasyLife.EntityFramework.Repositories
{
    public class MerchantRepository : EasyLifeRepositoryBase<Merchant, int>, IMerchantRepository
    {
        public MerchantRepository(IDbContextProvider<EasyLifeDbContext> dbContextProvider)
            : base(dbContextProvider)
        {
        }
    }
}
