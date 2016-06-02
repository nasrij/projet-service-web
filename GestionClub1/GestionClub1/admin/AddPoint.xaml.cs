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
using System.Windows.Media;

namespace GestionClub1.admin
{
    public partial class AddPoint : PhoneApplicationPage
    {
        public AddPoint()
        {
            InitializeComponent();
            Init();
        }

        private async void Init()
        {
            Random random = new Random();
            String url = Data.ip + "RestExample/api/club/getAll/" + random.Next();
            HttpClient http = new HttpClient();
            HttpResponseMessage msg = await http.GetAsync(url);
            string webresponse = await msg.Content.ReadAsStringAsync();
            try
            {
                Clubs root = JsonConvert.DeserializeObject<Clubs>(webresponse);
                List<Club> c = root.users.OfType<Club>().ToList();
                clubs.DataContext = c;
                //Data.listP = root.item;
            }
            catch (Exception e1)
            {
                MessageBox.Show("Server Might be Down");
            }
        }
        private void point_GotFocus(object sender, RoutedEventArgs e)
        {
            if (point.Text == "Nombre de Point")
            {
                point.Text = "";
                SolidColorBrush Brush1 = new SolidColorBrush();
                Brush1.Color = Colors.Black;
                point.Foreground = Brush1;
            }
        }

        private void point_LostFocus(object sender, RoutedEventArgs e)
        {
            if (point.Text == String.Empty)
            {
                point.Text = "Nombre de Point";
                SolidColorBrush Brush2 = new SolidColorBrush();
                Brush2.Color = Colors.Gray;
                point.Foreground = Brush2;
            }
        }

        

        private void cause_GotFocus(object sender, RoutedEventArgs e)
        {
            if (cause.Text == "Cause")
            {
                cause.Text = "";
                SolidColorBrush Brush1 = new SolidColorBrush();
                Brush1.Color = Colors.Black;
                cause.Foreground = Brush1;
            }
        }

        private void cause_LostFocus(object sender, RoutedEventArgs e)
        {
            if (cause.Text == String.Empty)
            {
                cause.Text = "Cause";
                SolidColorBrush Brush2 = new SolidColorBrush();
                Brush2.Color = Colors.Gray;
                cause.Foreground = Brush2;
            }
        }

        private async void add_Click(object sender, EventArgs e)
        {

            String url = Data.ip + "RestExample/api/point/add";
            string dateTime = DateTime.Now.ToString("yyyy-MM-dd");

            var values = new List<KeyValuePair<string, string>>
            {
                new KeyValuePair<string, string> ("cause",cause.Text ),
                new KeyValuePair<string, string> ("points", point.Text),
                new KeyValuePair<string, string> ("date",dateTime),
                new KeyValuePair<string, string> ("idClub",((Club)clubs.SelectedItem).id.ToString()),

                //new KeyValuePair<string, String> ("image",Convert.ToBase64String(ImageToArray())),
            };
            var httpClient = new HttpClient(new HttpClientHandler());
            HttpResponseMessage response = await httpClient.PostAsync(url, new FormUrlEncodedContent(values));
            response.EnsureSuccessStatusCode();
            MessageBox.Show("Point Ajouter ");
        }
    }
    }
