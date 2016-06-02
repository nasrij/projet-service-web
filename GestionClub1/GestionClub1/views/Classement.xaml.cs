using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Navigation;
using Microsoft.Phone.Controls;
using Microsoft.Phone.Shell;
using GestionClub1.models;
using System.Net.Http;
using Newtonsoft.Json;

namespace GestionClub1.views
{
    public partial class Classement : PhoneApplicationPage
    {
        public Classement()
        {
            InitializeComponent();
            Init();
        }

        private async void Init()
        {
            Random r = new Random();
            String url = Data.ip + "RestExample/api/point/clubs/" + r.Next();
            HttpClient http = new HttpClient();
            HttpResponseMessage msg = await http.GetAsync(url);
            string webresponse = await msg.Content.ReadAsStringAsync();
            try
            {
                ClubClassement root = JsonConvert.DeserializeObject<ClubClassement>(webresponse);
                if(root.result.nb>=1)
                {
                    image1.DataContext = (String)root.result.clubs[0][2];
                    nom1.Text = (String)root.result.clubs[0][1];
                    point1.Text = ((Int64)root.result.clubs[0][3]).ToString() + " Point"; 
                     if(root.result.nb>=2)
                    {
                        image2.DataContext = (String)root.result.clubs[1][2];
                        nom2.Text = (String)root.result.clubs[1][1];
                        point2.Text = ((Int64)root.result.clubs[1][3]).ToString() + " Point";
                        if (root.result.nb>=3)
                        {
                            image3.DataContext = (String)root.result.clubs[2][2];
                            nom3.Text = (String)root.result.clubs[2][1];
                            point3.Text = ((Int64)root.result.clubs[1][3]).ToString()+" Point";
                        }
                    }
                }
               
                
            }
            catch (Exception e1)
            {
                MessageBox.Show("Server Might be Down");
            }
        }
    }
}