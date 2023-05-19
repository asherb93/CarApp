package com.example.carapp.Fragments;

import static android.content.Context.LOCATION_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.carapp.R;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapFragment extends Fragment implements OnMapReadyCallback   {

    private GoogleMap mMap;
    private SupportMapFragment mapFragment;

    private LocationManager locationManager;
    private LocationListener locationListener;



    public MapFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_map, container, false);
        mapFragment = (SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.map_fragment);
        mapFragment.getMapAsync(this);


        return view;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        LatLng latlng=new LatLng(31.771959,35.217018);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng,12));



    }



    public void clickOnMap(){
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull LatLng latLng) {
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                markerOptions.title(latLng.latitude + " : " + latLng.longitude);
                mMap.clear();
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                        latLng,10
                ));
                mMap.addMarker(markerOptions);

            }
        });

    }

    public void showLocation(double latitude, double longitude){
        if(mMap != null){
            MarkerOptions markerOptions = new MarkerOptions();
            LatLng dest = new LatLng(latitude,longitude);
            markerOptions.position(dest);
            markerOptions.title(dest.latitude + " : " + dest.longitude);
            mMap.clear(); // what
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                    dest,16f
            ));
            mMap.addMarker(markerOptions);
        }
    }


}