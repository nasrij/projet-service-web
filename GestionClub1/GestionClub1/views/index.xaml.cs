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
using BuddySDK;

namespace GestionClub1.views
{
    public partial class index : PhoneApplicationPage
    {
        public index()
        {
            InitializeComponent();
            logo.DataContext = Data.user.ProfilePictureUrl;
            FullName.Text = Data.user.FirstName;
            DrawerLayout.InitializeDrawerLayout();
            if (Data.user != null)
            {
                DC.Text = "Disconnect";
            }
            else
            {
                DC.Text = "Connect";
            }
            Init();
        }
        private async void Init()
        {
            Random r = new Random();
            String url = Data.ip + "RestExample/api/Evenement/getAll/" + r.Next();
            HttpClient http = new HttpClient();
            HttpResponseMessage msg = await http.GetAsync(url);
            string webresponse = await msg.Content.ReadAsStringAsync();
            try
            {
                Evenements root = JsonConvert.DeserializeObject<Evenements>(webresponse);
                List<Evenement> c = root.users.OfType<Evenement>().ToList();
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

        private void DrawerIcon_Tapped(object sender, System.Windows.Input.GestureEventArgs e)
        {
            if (DrawerLayout.IsDrawerOpen)
                DrawerLayout.CloseDrawer();
            else
            {
                DrawerLayout.OpenDrawer();

            }
        }


        private void acceuil_Tap(object sender, System.Windows.Input.GestureEventArgs e)
        {
            NavigationService.Navigate(new Uri("/views/index.xaml", UriKind.Relative));
        }

        private void Profil_Tap(object sender, System.Windows.Input.GestureEventArgs e)
        {

        }

        private void Classement_Tap(object sender, System.Windows.Input.GestureEventArgs e)
        {
            NavigationService.Navigate(new Uri("/views/Classement.xaml", UriKind.Relative));

        }

        private void MyEvent_Tap(object sender, System.Windows.Input.GestureEventArgs e)
        {

        }

        private async void Connect_Tap(object sender, System.Windows.Input.GestureEventArgs e)
        {
            var result2 = await Buddy.LogoutUserAsync();
            NavigationService.Navigate(new Uri("/views/connection.xaml", UriKind.Relative));
        }

        private void About_Tap(object sender, System.Windows.Input.GestureEventArgs e)
        {

        }

        private void clubs_Tap(object sender, System.Windows.Input.GestureEventArgs e)
        {
            NavigationService.Navigate(new Uri("/views/Clubs.xaml", UriKind.Relative));

        }

        private void ApplicationBarIconButton_Click(object sender, EventArgs e)
        {
            if(Data.user != null)
                NavigationService.Navigate(new Uri("/views/AddEvent.xaml", UriKind.Relative));
            else
                NavigationService.Navigate(new Uri("/views/connection.xaml", UriKind.Relative));

        }

        private void Li_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {

        }
    }
}