package com.example.geolocalisation;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    public void onRequestPermissionResult(int requestCode, String permissions[], int[] grantResults){
        switch (requestCode){
            case 100 :{
                //cas de notre demande d'autorisation
                initLocation();

                if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    // L'autorisation a été donnée
                } else {
                    // refusée
                }
                return;
            }
        }
    }


    LocationManager mLocationManager = null;

    //
    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //L'autorisation n'st pas acceptée

            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)) {


            } else {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);
            }
        } else {
            // deja acceptée , on peut faire une action ici
            initLocation();
            }
        }


   //Méthode initLocation
    @SuppressLint("MissingPermission")
    private void initLocation(){
        mLocationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            Toast.makeText(MainActivity.this, location.toString(), Toast.LENGTH_LONG).show();
            //location.toString();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }


    };

        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

}


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkPermission();


    }
}
