using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Navigation;
using Microsoft.Phone.Controls;
using Microsoft.Phone.Shell;
using BuddySDK;
using BuddySDK.Models;
using GestionClub1.models;
using System.Threading.Tasks;

namespace GestionClub1.views
{
    public partial class splash : PhoneApplicationPage
    {
        public splash()
        {
            InitializeComponent();
            getUser();
        }

        private async void getUser()
        {
            var result = await Buddy.GetAsync<User>("/users/me");
            if (result.IsSuccess)
            {
                Data.user = result.Value;
                await Task.Delay(TimeSpan.FromSeconds(2));
                progress.IsIndeterminate = false;
                NavigationService.Navigate(new Uri("/views/index.xaml", UriKind.Relative));
            }
            else
            {
                await Task.Delay(TimeSpan.FromSeconds(2));
                progress.IsIndeterminate = false;
                NavigationService.Navigate(new Uri("/views/connection.xaml", UriKind.Relative));

            }

        }
    }
}