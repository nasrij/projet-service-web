using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GestionClub1.models
{
    

        public class Clubs
        {
            public int nb { get; set; }
            public Club[] users { get; set; }
        }

        public class Club
        {
            public string image { get; set; }
            public string description { get; set; }
            public int id { get; set; }
            public string nom { get; set; }
            public string slogan { get; set; }
        }

   
}
