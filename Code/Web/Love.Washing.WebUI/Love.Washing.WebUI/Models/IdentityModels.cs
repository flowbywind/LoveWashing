using System;
using System.Collections.Generic;
using System.Linq;
using System.Transactions;
using System.Web;
using System.Web.Mvc;
using System.Web.Security;
using DotNetOpenAuth.AspNet;
using Microsoft.Web.WebPages.OAuth;
using WebMatrix.WebData;
using System.Data.Entity;

namespace Love.Washing.WebUI.Models
{
    // Add profile data for application users by adding properties to the ApplicationUser class
    //public class ApplicationUser : IdentityUser
    //{

    //}

    public class ApplicationDbContext :DbContext
    {
        private static bool _created = false;

        public ApplicationDbContext()
            : base("DefaultConnection")
        {            
            // Create the database and schema if it doesn't exist
            // This is a temporary workaround to create database until Entity Framework database migrations 
            // are supported in ASP.NET 5
            //if (!_created)
            //{
            //    Database.
            //    Database.AsRelational().ApplyMigrations();
            //    _created = true;
            //}
        }
        public DbSet<ExamPaper> ExamPaper { get; set; }
        public DbSet<QuestionGroup> QuestionGroup { get; set; }

        public DbSet<BackgroundQuestion> BackgroundQuestion { get; set; }

        public DbSet<Knowledge> Knowledge { get; set; }


        //protected override void OnConfiguring(DbContextOptions options)
        //{
        //    options.UseSqlServer("Server=(localdb)\\mssqllocaldb;Database=MyGaoKaoDB;Trusted_Connection=True;MultipleActiveResultSets=true");
        //}

        //protected override void OnModelCreating(ModelBuilder builder)
        //{
        //    base.OnModelCreating(builder);
        //    // Customize the ASP.NET Identity model and override the defaults if needed.
        //    // For example, you can rename the ASP.NET Identity table names and more.
        //    // Add your customizations after calling base.OnModelCreating(builder);
        //}
    }
}