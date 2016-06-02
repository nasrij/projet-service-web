using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GestionClub1.models
{

    public class ClubClassement
    {
        public Result result { get; set; }
    }

    public class Result
    {
        public int nb { get; set; }
        public object[][] clubs { get; set; }
    }

}
