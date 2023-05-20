package com.example.carapp.Utilities;

import static android.location.LocationManager.GPS_PROVIDER;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class  GPS implements LocationListener {
    public static int MY_PERMISSIONS_REQUEST_LOCATION=1;

    private double lat= 31.771959;
    private double lag= 35.217018;

    private LocationManager locationManager;

    public GPS(AppCompatActivity activity){
        locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
        if(ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_LOCATION
            );
        }
        else{
            locationManager.requestLocationUpdates(GPS_PROVIDER,0,0,this);
        }
    }
    @Override
    public void onLocationChanged(@NonNull Location location) {

        lat=location.getLatitude();
        lag=location.getLongitude();
        locationManager.removeUpdates(this);
    }

    public double getLat() {
        return lat;
    }

    public void sampleLocation(AppCompatActivity activity){
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Location location = locationManager.getLastKnownLocation(GPS_PROVIDER);
            if (location != null)
                onLocationChanged(location);
        }
        else {
            Location newLocation = new Location(GPS_PROVIDER);
            newLocation.setLatitude(lat);
            newLocation.setLongitude(lag);
            onLocationChanged(newLocation);
        }
    }



    public double getLag() {
        return lag;
    }


    private void stopLocationTrack() {
        locationManager.removeUpdates(this);
    }
}