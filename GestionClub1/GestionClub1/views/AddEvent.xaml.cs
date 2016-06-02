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
using BuddySDK;
using BuddySDK.Models;
using System.IO;
using System.Windows.Media.Imaging;
using Microsoft.Phone.Tasks;
using System.Windows.Media;

namespace GestionClub1.views
{
    public partial class AddEvent : PhoneApplicationPage
    {
        public AddEvent()
        {
            InitializeComponent();
        }

        private void location_Click(object sender, EventArgs e)
        {
            NavigationService.Navigate(new Uri("/views/GMap.xaml", UriKind.Relative));
        }

        public Stream ImageToArray()
        {
           
                BitmapImage image1 = image.Source as BitmapImage;
            image1.CreateOptions = BitmapCreateOptions.None;

            WriteableBitmap wbmp = new WriteableBitmap(image1);
            MemoryStream ms = new MemoryStream();
            wbmp.SaveJpeg(ms, wbmp.PixelWidth, wbmp.PixelHeight, 0, 100);

            return ms;
        }

       

        private async void add_Click(object sender, EventArgs e)
        {
            
            var options = new
            {
                data = new BuddyFile(ImageToArray(), "My File Name", "image/jpg"),
                location = new BuddyGeoLocation(47.1, -122.3),
                caption = "This is an awesome picture caption!",
                tag = "Some useful tag",
                watermark = "",
                readPermissions = "App",
                writePermissions = "User",
                title = "Some Title",
                useExifData = true
            };

            var result1 = await Buddy.PostAsync<Picture>("/pictures", options);

            String url = Data.ip+ "RestExample/api/Evenement/add";

            var values = new List<KeyValuePair<string, string>>
            {
                new KeyValuePair<string, string> ("description",description.Text ),
                new KeyValuePair<string, string> ("date", date.ValueString),
                new KeyValuePair<string, string> ("heure",heure.ValueString),
                new KeyValuePair<string, string> ("lieux",lieu.Text),
                new KeyValuePair<string, string> ("latitude",latitude.Text),
                new KeyValuePair<string, string> ("longitude",longitude.Text),
                new KeyValuePair<string, string> ("nom",nom.Text),
                new KeyValuePair<string, string> ("idClub",Data.user.LastName),
                new KeyValuePair<string, string> ("image",result1.Value.SignedUrl.OriginalString),

                //new KeyValuePair<string, String> ("image",Convert.ToBase64String(ImageToArray())),
            };
            var httpClient = new HttpClient(new HttpClientHandler());
            HttpResponseMessage response = await httpClient.PostAsync(url, new FormUrlEncodedContent(values));
            response.EnsureSuccessStatusCode();
            var responseString = await response.Content.ReadAsStringAsync();
            MessageBox.Show("Evenement Ajouter");
        }

        PhotoChooserTask photoChooserTask;
        private void image_Tap(object sender, System.Windows.Input.GestureEventArgs e)
        {
            photoChooserTask = new PhotoChooserTask();
            photoChooserTask.Completed += new EventHandler<PhotoResult>(photoChooserTask_Completed);
            photoChooserTask.Show();
        }

        void photoChooserTask_Completed(object sender, PhotoResult e)
        {
            if (e.TaskResult == TaskResult.OK)
            {
                BitmapImage image1 = new BitmapImage();
                image1.SetSource(e.ChosenPhoto);
                image.Source = image1;
            }
        }
        protected override void OnNavigatedTo(NavigationEventArgs e)
        {
            longitude.Text = Data.longitude;
            latitude.Text = Data.latitude;
            //Data.longitude = "0";
            //Data.latitude = "0";
            base.OnNavigatedTo(e);
        }


        
       

        private void nom_GotFocus(object sender, RoutedEventArgs e)
        {
            if (nom.Text == "Nom d'évènement")
            {
                nom.Text = "";
                SolidColorBrush Brush1 = new SolidColorBrush();
                Brush1.Color = Colors.Black;
                nom.Foreground = Brush1;
            }
        }

        private void nom_LostFocus(object sender, RoutedEventArgs e)
        {
            if (nom.Text == String.Empty)
            {
                nom.Text = "Nom d'évènement";
                SolidColorBrush Brush2 = new SolidColorBrush();
                Brush2.Color = Colors.Gray;
                nom.Foreground = Brush2;
            }
        }

        private void description_GotFocus(object sender, RoutedEventArgs e)
        {
            if (description.Text == "Description")
            {
                description.Text = "";
                SolidColorBrush Brush1 = new SolidColorBrush();
                Brush1.Color = Colors.Black;
                description.Foreground = Brush1;
            }
        }

        private void description_LostFocus(object sender, RoutedEventArgs e)
        {
            if (description.Text == String.Empty)
            {
                description.Text = "Description";
                SolidColorBrush Brush2 = new SolidColorBrush();
                Brush2.Color = Colors.Gray;
                description.Foreground = Brush2;
            }
        }

        private void lieu_GotFocus(object sender, RoutedEventArgs e)
        {
            if (lieu.Text == "Lieux")
            {
                lieu.Text = "";
                SolidColorBrush Brush1 = new SolidColorBrush();
                Brush1.Color = Colors.Black;
                lieu.Foreground = Brush1;
            }
        }

        private void lieu_LostFocus(object sender, RoutedEventArgs e)
        {
            if (lieu.Text == String.Empty)
            {
                lieu.Text = "Lieux";
                SolidColorBrush Brush2 = new SolidColorBrush();
                Brush2.Color = Colors.Gray;
                lieu.Foreground = Brush2;
            }
        }
    }
}