using Abp.Domain.Repositories;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace EasyLife
{
    public interface ICityRepository : IRepository<City, int>
    {
    }
}
