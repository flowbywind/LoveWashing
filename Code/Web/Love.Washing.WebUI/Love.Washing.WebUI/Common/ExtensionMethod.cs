using System;
using System.ComponentModel;
using System.Reflection;
using System.Linq.Expressions;
using System.Web.Mvc;
using System.Web.Mvc.Html;
using System.Collections;
using System.Collections.Generic;

namespace Love.Washing.WebUI
{
    public static class ExtensionMethod
    {
        /// <summary>
        /// 时间类型转化为时间戳
        /// </summary>
        /// <param name="dt"></param>
        /// <returns></returns>
        public static int ToUnixTime(this DateTime dt)
        { 
            return (Int32)(DateTime.UtcNow.Subtract(new DateTime(1970, 1, 1))).TotalSeconds; 
        }
        /// <summary>
        /// 时间戳转日期类型
        /// </summary>
        /// <param name="unixTimeStamp"></param>
        /// <returns></returns>
        public static DateTime UnixToDateTime(this int unixTimeStamp)
        {
            DateTime dt = new DateTime(1970, 1, 1, 0, 0, 0, 0, System.DateTimeKind.Utc);
            dt = dt.AddSeconds(unixTimeStamp).ToLocalTime();
            return dt;
        }
        /// <summary>
        /// 获取枚举的描述
        /// </summary>
        /// <param name="target"></param>
        /// <returns></returns>
        public static string GetEnumDescription(this System.Enum target)
        {
            Type t = target.GetType();
            FieldInfo[] fis = t.GetFields();
            foreach (var info in fis)
            {
                if (info.Name != target.ToString()) continue;
                var descript = info.GetCustomAttribute<DescriptionAttribute>(true);
                if (descript != null)
                {
                    return descript.Description;
                }
            } 
            return "";
        }
        /// <summary>
        /// 返回集合中指定expression的display name
        /// </summary>
        /// <typeparam name="TModelItem">对象集合的类型</typeparam>
        /// <typeparam name="TValue">expression运行后的结果</typeparam>
        /// <param name="htmlHelper"></param>
        /// <param name="expression"></param>
        /// <returns></returns>
        //public static string DisplayNameFor<TModelItem, TValue>(
        //    this IHtmlHelper<PagedList.IPagedList<TModelItem>> htmlHelper,
        //    Expression<Func<TModelItem,TValue>> expression)
        //{
        //    return htmlHelper.DisplayNameForInnerType<TModelItem, TValue>(expression);
        //}
        public static MvcHtmlString DisplayNameFor<TModelItem,TValue>(this HtmlHelper<PagedList.IPagedList<TModelItem>> htmlHelper, Expression<Func<TModelItem,TValue>> expression)
        {
            var h = htmlHelper as HtmlHelper<IEnumerable<TModelItem>>;
            return  h.DisplayNameFor<TModelItem, TValue>(expression);
            //return    htmlHelper.DisplayNameFor<TModelItem, TValue>(expression);
            //return htmlHelper.DisplayNameFor<TModelItem,TValue>(expression);
            //return "";
            //htmlHelper.DisplayNameFor
        }

    }
}