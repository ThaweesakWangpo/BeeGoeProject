package com.example.beegoeproject;

import androidx.fragment.app.FragmentActivity;

import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private GPSTracker gpsTracker;
    private Location mLocation;
    double latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        gpsTracker = new GPSTracker(getApplicationContext());

        mLocation = gpsTracker.getLocation();

        //Check gpsTracker value
        Log.i("KUGmndjnfdf1254554", String.valueOf(mLocation));



        latitude = mLocation.getLatitude();
        longitude = mLocation.getLongitude();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng user = new LatLng(latitude,longitude);
        mMap.addMarker(new MarkerOptions().position(user).title("You are here"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(user,16));

        if(Sound.click==false){

            LatLng dog = new LatLng(Sound.latitudeDOg,Sound.longitudeDog);
            mMap.addMarker(new MarkerOptions().position(dog).title("Your Dog are here"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(dog,16));
            Sound.click=true;
        }
    }
}
