using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using Abp.EntityFramework;

namespace EasyLife.EntityFramework.Repositories
{
    public class TagRepository : EasyLifeRepositoryBase<Tag, int>, ITagRepository
    {
        public TagRepository(IDbContextProvider<EasyLifeDbContext> dbContextProvider)
            : base(dbContextProvider)
        {
        }

        /// <summary>
        /// 根据商家获取所有的标签
        /// </summary>
        /// <param name="merchantId"></param>
        /// <returns></returns>
        public List<Tag> GetAllByMerchantID(int? merchantId)
        {
            var query = GetAll();
            if (merchantId.HasValue)
                query = query.Where(a => a.Id == merchantId);
            return query.OrderByDescending(a => a.CreationTime).ToList();
        }
    }
}
