using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GestionClub1.models
{

    public class Evenements
    {
        public int nb { get; set; }
        public Evenement[] users { get; set; }
    }

    public class Evenement
    {
        public string date { get; set; }
        public string image { get; set; }
        public string heure { get; set; }
        public string latitude { get; set; }
        public string lieux { get; set; }
        public string description { get; set; }
        public int id { get; set; }
        public string nom { get; set; }
        public string longitude { get; set; }
        public int idClub { get; set; }
    }

}
