using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Navigation;
using Microsoft.Phone.Controls;
using Microsoft.Phone.Shell;
using System.Windows.Media.Imaging;
using Microsoft.Phone.Tasks;
using System.Windows.Media;
using BuddySDK;
using System.IO;
using BuddySDK.Models;
using GestionClub1.models;
using System.Threading.Tasks;
using System.Net.Http;
using Newtonsoft.Json;

namespace GestionClub1.views
{
    public partial class connection : PhoneApplicationPage
    {
        public connection()
        {
            InitializeComponent();
            Init();
        }

        private async void Init()
        {
            Random random = new Random();
            String url = Data.ip + "RestExample/api/club/getAll/"+random.Next();
            HttpClient http = new HttpClient();
            HttpResponseMessage msg = await http.GetAsync(url);
            string webresponse = await msg.Content.ReadAsStringAsync();
            try
            {
                GestionClub1.models.Clubs root = JsonConvert.DeserializeObject<GestionClub1.models.Clubs>(webresponse);
                List<GestionClub1.models.Club> c = root.users.OfType<GestionClub1.models.Club>().ToList();
                clubs.DataContext = c;
                //Data.listP = root.item;
            }
            catch (Exception e1)
            {
                MessageBox.Show("Server Might be Down");
            }
        }

        private void login_LostFocus(object sender, RoutedEventArgs e)
        {
            if (login.Text == String.Empty)
            {
                login.Text = "email ou pseudo";
                SolidColorBrush Brush2 = new SolidColorBrush();
                Brush2.Color = Colors.Gray;
                login.Foreground = Brush2;
            }
        }

        private void login_GotFocus(object sender, RoutedEventArgs e)
        {
            if (login.Text == "email ou pseudo")
            {
                login.Text = "";
                SolidColorBrush Brush1 = new SolidColorBrush();
                Brush1.Color = Colors.Black;
                login.Foreground = Brush1;
            }
        }

        private void pass_PasswordChanged(object sender, RoutedEventArgs e)
        {
            if (pass.Password.Length == 0)
                TextBlockEnterPassword.Visibility = Visibility.Visible;
            else
                TextBlockEnterPassword.Visibility = Visibility.Collapsed;

        }

        private void connecter_Click(object sender, RoutedEventArgs e)
        {

        }

        private void pass_GotFocus(object sender, RoutedEventArgs e)
        {
            if (pass.Password == "Password")
            {
                pass.Password = "";
                SolidColorBrush Brush1 = new SolidColorBrush();
                Brush1.Color = Colors.Black;
                pass.Foreground = Brush1;
            }
        }

        private void pass_LostFocus(object sender, RoutedEventArgs e)
        {
            if (pass.Password == String.Empty)
            {
                login.Text = "password";
                SolidColorBrush Brush2 = new SolidColorBrush();
                Brush2.Color = Colors.Gray;
                pass.Foreground = Brush2;
            }
        }

        private void tel_LostFocus(object sender, RoutedEventArgs e)
        {
            if (tel.Text == String.Empty)
            {
                tel.Text = "Télephone";
                SolidColorBrush Brush2 = new SolidColorBrush();
                Brush2.Color = Colors.Gray;
                tel.Foreground = Brush2;
            }
        }

        private void tel_GotFocus(object sender, RoutedEventArgs e)
        {
            if (tel.Text == "Télephone")
            {
                tel.Text = "";
                SolidColorBrush Brush1 = new SolidColorBrush();
                Brush1.Color = Colors.Black;
                tel.Foreground = Brush1;
            }
        }

        private void email_GotFocus(object sender, RoutedEventArgs e)
        {
            if (email.Text == "E-mail")
            {
                email.Text = "";
                SolidColorBrush Brush1 = new SolidColorBrush();
                Brush1.Color = Colors.Black;
                email.Foreground = Brush1;
            }
        }

        private void email_LostFocus(object sender, RoutedEventArgs e)
        {
            if (email.Text == String.Empty)
            {
                email.Text = "E-mail";
                SolidColorBrush Brush2 = new SolidColorBrush();
                Brush2.Color = Colors.Gray;
                email.Foreground = Brush2;
            }
        }

        private void pass1_PasswordChanged(object sender, RoutedEventArgs e)
        {
            if (pass1.Password.Length == 0)
                TextBlockEnterPassword1.Visibility = Visibility.Visible;
            else
                TextBlockEnterPassword1.Visibility = Visibility.Collapsed;

        }

        private void cpass_PasswordChanged(object sender, RoutedEventArgs e)
        {
            if (cpass.Password.Length == 0)
                TextBlockEnterPassword2.Visibility = Visibility.Visible;
            else
                TextBlockEnterPassword2.Visibility = Visibility.Collapsed;

        }

        private async void Inscription_Click(object sender, RoutedEventArgs e)
        {
            string s;
            if (pass1.Password == cpass.Password)
            {
                if (r1.IsChecked == true)
                {
                    s = r1.Content.ToString();
                }
                else
                {
                    s = r2.Content.ToString();
                }



                var options = new
                {
                    data = new BuddyFile(ImageToArray(), login1.Text, "image/jpg"),
                    caption = "This is an awesome picture caption!",
                    tag = "Some useful tag"
                };


                
                if (r1.IsChecked == true)
                {
                   var result = await Buddy.CreateUserAsync(email.Text, pass1.Password, login1.Text, ((Club)clubs.SelectedItem).id.ToString(), email.Text, BuddySDK.Models.User.UserGender.Male, null, tel.Text);
                    var result1 = await Buddy.PostAsync<User>("/users/me/profilepicture", options);

                    if (!result.IsSuccess)
                    {
                        MessageBox.Show("Utilisateur Existe deja");
                        pass1.Password = "";
                        cpass.Password = "";
                        //Frame.GoBack();
                    }
                    else
                    {
                        MessageBox.Show("Utilisateur Ajouter");
                        Data.user = result.Value;
                        NavigationService.Navigate(new Uri("/views/index.xaml", UriKind.Relative));
                    }
                }
                else
                {
                   var result = await Buddy.CreateUserAsync(email.Text, pass1.Password, login1.Text, s, email.Text, BuddySDK.Models.User.UserGender.Female, null, tel.Text);
                    var result1 = await Buddy.PostAsync<User>("/users/me/profilepicture", options);

                    if (!result.IsSuccess)
                    {
                        MessageBox.Show("Utilisateur Existe deja");
                        pass1.Password = "";
                        cpass.Password = "";
                        //Frame.GoBack();
                    }
                    else
                    {
                        MessageBox.Show("Utilisateur Ajouter");
                        Data.user = result.Value;
                        NavigationService.Navigate(new Uri("/views/index.xaml", UriKind.Relative));
                    }
                }




               
            }
            else
                MessageBox.Show("Mot de Passe incorrect");
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

        private async void LoginButton_Click(object sender, RoutedEventArgs e)
        {
            if (login.Text == "admin" && pass.Password == "admin")
            {
                var result = await Buddy.LoginUserAsync("admin@admin", "admin");
                NavigationService.Navigate(new Uri("/admin/index.xaml", UriKind.Relative));
            }
            else
            {
                var result = await Buddy.LoginUserAsync(login.Text, pass.Password);

                if (!result.IsSuccess)
                {
                    MessageBox.Show("Mot de Passe incorrect");
                }
                else
                {
                    Data.user = result.Value;
                    NavigationService.Navigate(new Uri("/views/index.xaml", UriKind.Relative));
                }
            }

        }

        private void login1_LostFocus(object sender, RoutedEventArgs e)
        {
            if (login1.Text == String.Empty)
            {
                login1.Text = "Pseudo";
                SolidColorBrush Brush2 = new SolidColorBrush();
                Brush2.Color = Colors.Gray;
                login1.Foreground = Brush2;
            }
        }

        private void login1_GotFocus(object sender, RoutedEventArgs e)
        {
            if (login1.Text == "Pseudo")
            {
                login1.Text = "";
                SolidColorBrush Brush1 = new SolidColorBrush();
                Brush1.Color = Colors.Black;
                login1.Foreground = Brush1;
            }
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
    }

}