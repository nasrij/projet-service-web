using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Navigation;
using Microsoft.Phone.Controls;
using Microsoft.Phone.Shell;
using Microsoft.Phone.Maps.Controls;
using System.Windows.Shapes;
using System.Windows.Media;
using System.Device.Location;
using GestionClub1.models;

namespace GestionClub1.views
{
    public partial class GMap : PhoneApplicationPage
    {
        public GMap()
        {
            InitializeComponent();
            Mymap.Center = new GeoCoordinate(35.855080, 10.527662);
            Mymap.ZoomLevel = 7;


        }
        private String longitude = "0";
        private String latitude = "0"; 
        MapLayer myLocationLayer = null;
        private void Mymap_Tap(object sender, System.Windows.Input.GestureEventArgs e)
        {
            if (myLocationLayer != null)
                Mymap.Layers.Remove(myLocationLayer);
            Point p = e.GetPosition(Mymap);
            longitude = p.Y.ToString();
            latitude = p.X.ToString();
            GeoCoordinate s = Mymap.ConvertViewportPointToGeoCoordinate(p);
            Grid g = CreatePushpin();
            MapOverlay myLocationOverlay = new MapOverlay();
            myLocationOverlay.Content = g;
            myLocationOverlay.PositionOrigin = new Point(0, 0);
            myLocationOverlay.GeoCoordinate = s;

            myLocationLayer = new MapLayer();
            myLocationLayer.Add(myLocationOverlay);

            Mymap.Layers.Add(myLocationLayer);


        }

        private Grid CreatePushpin()
        {
            //Creating a Grid element.
            Grid MyGrid = new Grid();
            MyGrid.RowDefinitions.Add(new RowDefinition());
            MyGrid.RowDefinitions.Add(new RowDefinition());
            MyGrid.Background = new SolidColorBrush(Colors.Transparent);
            //Creating a Rectangle
            Rectangle MyRectangle = new Rectangle();
            MyRectangle.Fill = new SolidColorBrush(Colors.Black);
            MyRectangle.Height = 20;
            MyRectangle.Width = 20;
            MyRectangle.SetValue(Grid.RowProperty, 0);
            MyRectangle.SetValue(Grid.ColumnProperty, 0);
            //Adding the Rectangle to the Grid
            MyGrid.Children.Add(MyRectangle);
            return MyGrid;
        }

        private void save_Click(object sender, EventArgs e)
        {
            Data.latitude = latitude;
            Data.longitude = longitude;
            NavigationService.GoBack();
        }
    }
}