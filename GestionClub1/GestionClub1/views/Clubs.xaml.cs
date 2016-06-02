using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Navigation;
using Microsoft.Phone.Controls;
using Microsoft.Phone.Shell;
using System.Net.Http;
using GestionClub1.models;
using Newtonsoft.Json;

namespace GestionClub1.views
{
    public partial class Clubs : PhoneApplicationPage
    {
        public Clubs()
        {
            InitializeComponent();
            Init();
        }
        private async void Init()
        {
            Random r = new Random();
            String url = Data.ip + "RestExample/api/club/getAll/" + r.Next();
            HttpClient http = new HttpClient();
            HttpResponseMessage msg = await http.GetAsync(url);
            string webresponse = await msg.Content.ReadAsStringAsync();
            try
            {
                GestionClub1.models.Clubs root = JsonConvert.DeserializeObject<GestionClub1.models.Clubs>(webresponse);
                List<GestionClub1.models.Club> c = root.users.OfType<GestionClub1.models.Club>().ToList();
                Li.DataContext = null;
                Li.DataContext = c;
                //Data.listP = root.item;
            }
            catch (Exception e1)
            {
                MessageBox.Show("Server Might be Down");
            }
        }
        protected override void OnNavigatedTo(NavigationEventArgs e)
        {
            Init();
            base.OnNavigatedTo(e);
        }

        private void Li_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {

        }
    }
}