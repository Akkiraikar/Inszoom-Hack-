package com.example.insind788.myfirstattempt;

/**
 * Created by insind788 on 7/2/2015.
 */

import android.location.Address;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.location.Geocoder;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class AndroidGPSTrackingActivity extends FragmentActivity implements OnMapReadyCallback {

    GPSTracker gps;
    double latitude;
    double longitude;
    Geocoder geocoder;
    double[][] array2d = new double[2][2];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public void Locate(View v)
    {   double d2r = Math.PI / 180;
        double dLong = (array2d[1][0] - longitude) * d2r;
        double dLat;
        dLat = (array2d[1][1] - latitude) * d2r;
        double a = Math.pow(Math.sin(dLat / 2.0), 2) + Math.cos(latitude * d2r) * Math.cos(35 * d2r) * Math.pow(Math.sin(dLong / 2.0), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = 6367000 * c;
        Toast.makeText(getApplicationContext(), "Approx Distance is \n: " + Math.round(d) + " KM" , Toast.LENGTH_LONG).show();
    }

    @Override
    public void onMapReady(GoogleMap map) {
        gps = new GPSTracker(AndroidGPSTrackingActivity.this);
        if(gps.canGetLocation()) {
             latitude = gps.getLatitude();
             longitude = gps.getLongitude();
             getAddress(map);
        }else
        {
            gps.showSettingsAlert();
        }
    }

    public void getAddress(GoogleMap map) {
                array2d[0][0] = latitude;
                array2d[0][1] = longitude;
                array2d[1][0] = 39;
                array2d[1][1] = 116;

        for (int i = 0; i < array2d.length - 1; i++)
            for (int j = 0; j < array2d.length; j++) {
                LatLng sydney = new LatLng(array2d[j][i], array2d[j][i + 1]);
                map.addMarker(new MarkerOptions().position(sydney).title(new LatLng(array2d[j][i], array2d[j][i + 1]).toString()));
                map.moveCamera(CameraUpdateFactory.newLatLng(sydney));

                List<Address> yourAddresses;
                geocoder = new Geocoder(this, Locale.getDefault());
                try {
                    yourAddresses = geocoder.getFromLocation(array2d[j][i], array2d[j][i + 1], 1);
                    if (yourAddresses.size() > 0) {
                        String yourAddress = yourAddresses.get(0).getAddressLine(0) + yourAddresses.get(0).getAddressLine(1);
                        if (array2d[j][i] == latitude) {
                            Toast.makeText(getApplicationContext(), "Your Location is - \nAddress: " + yourAddress, Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Your Friend's Location is - \nAddress: " + yourAddress, Toast.LENGTH_LONG).show();
                        }
                    }
                } catch (IOException e) {
                    Log.e("LocationSampleActivity", "IO Exception in getFromLocation()");
                    e.printStackTrace();
                }

            }
    }

    public void Close(View v)
    {
        finish();
    }

}