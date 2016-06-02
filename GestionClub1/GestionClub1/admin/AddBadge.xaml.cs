using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Navigation;
using Microsoft.Phone.Controls;
using Microsoft.Phone.Shell;
using System.Windows.Media;
using Microsoft.Phone.Tasks;
using System.Windows.Media.Imaging;
using BuddySDK;
using System.IO;
using BuddySDK.Models;
using GestionClub1.models;
using System.Net.Http;

namespace GestionClub1.admin
{
    public partial class AddBadge : PhoneApplicationPage
    {
        public AddBadge()
        {
            InitializeComponent();
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
            if (result1.IsSuccess)
            {
                String url = Data.ip + "RestExample/api/badge/add";

                var values = new List<KeyValuePair<string, string>>
            {                
                new KeyValuePair<string, string> ("nom",nom.Text),
                new KeyValuePair<string, string> ("image",result1.Value.SignedUrl.OriginalString),

                //new KeyValuePair<string, String> ("image",Convert.ToBase64String(ImageToArray())),
            };
                var httpClient = new HttpClient(new HttpClientHandler());
                HttpResponseMessage response = await httpClient.PostAsync(url, new FormUrlEncodedContent(values));
                response.EnsureSuccessStatusCode();
                MessageBox.Show("Badge Ajouter ");
            }
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


        private void nom_GotFocus(object sender, RoutedEventArgs e)
        {
            if (nom.Text == "Nom Badge")
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
                nom.Text = "Nom Badge";
                SolidColorBrush Brush2 = new SolidColorBrush();
                Brush2.Color = Colors.Gray;
                nom.Foreground = Brush2;
            }
        }

    }
}