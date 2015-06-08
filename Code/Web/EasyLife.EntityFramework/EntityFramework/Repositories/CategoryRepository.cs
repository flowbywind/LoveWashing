using Abp.Domain.Entities;
using Abp.EntityFramework;
using Abp.EntityFramework.Repositories;

namespace EasyLife.EntityFramework.Repositories
{
    public  class CategoryRepository:EasyLifeRepositoryBase<Category,int>,ICategoryRepository
    {

        public CategoryRepository(IDbContextProvider<EasyLifeDbContext> dbContextProvider)
            : base(dbContextProvider) 
        { 
        }
    }
}
