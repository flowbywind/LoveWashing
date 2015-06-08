using System;
using Abp.Domain.Entities;
using Abp.EntityFramework;
using Abp.EntityFramework.Repositories;

namespace EasyLife.EntityFramework.Repositories
{
    public class CityRepository : EasyLifeRepositoryBase<City, int>, ICityRepository
    {
        public CityRepository(IDbContextProvider<EasyLifeDbContext> dbContextProvider)
            : base(dbContextProvider)
        {
        }
    }
}
