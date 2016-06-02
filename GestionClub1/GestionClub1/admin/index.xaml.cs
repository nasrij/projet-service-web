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

namespace GestionClub1.admin
{
    public partial class index : PhoneApplicationPage
    {
        public index()
        {
            InitializeComponent();
            DrawerLayout.InitializeDrawerLayout();
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

        private void clubs_Tap(object sender, System.Windows.Input.GestureEventArgs e)
        {
            NavigationService.Navigate(new Uri("/admin/AddClub.xaml", UriKind.Relative));
        }

        private void badge_Tap(object sender, System.Windows.Input.GestureEventArgs e)
        {
            NavigationService.Navigate(new Uri("/admin/AddBadge.xaml", UriKind.Relative));
        }

        private void point_Tap(object sender, System.Windows.Input.GestureEventArgs e)
        {
            NavigationService.Navigate(new Uri("/admin/AddPoint.xaml", UriKind.Relative));
        }

        private void About_Tap(object sender, System.Windows.Input.GestureEventArgs e)
        {

        }

        private async void Connect_Tap(object sender, System.Windows.Input.GestureEventArgs e)
        {
            var result2 = await Buddy.LogoutUserAsync();
            NavigationService.Navigate(new Uri("/views/connection.xaml", UriKind.Relative));


        }
    }
}