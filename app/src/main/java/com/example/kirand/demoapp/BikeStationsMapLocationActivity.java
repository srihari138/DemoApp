package com.example.kirand.demoapp;

import android.content.Intent;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.kirand.demoapp.DataClasses.BikeStation;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BikeStationsMapLocationActivity extends FragmentActivity implements OnMapReadyCallback{

    private GoogleMap mMap;
    String bikeStationsJSONString = "";
    JSONArray bikeStationsArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike_stations_map_location);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        bikeStationsJSONString = getString(R.string.static_bike_station_data);

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

        try {
            //JSONObject jsonObject = new JSONObject(bikeStationsJSONString);
            bikeStationsArray = new JSONArray(bikeStationsJSONString);

            for (int i = 0; i < bikeStationsArray.length(); i++) {
                BikeStation bikeStation = new BikeStation();

                JSONObject bikeStationJson = bikeStationsArray.getJSONObject(i);

                bikeStation.setNumber(Integer.parseInt(bikeStationJson.getString("number")));
                bikeStation.setName(bikeStationJson.getString("name"));
                bikeStation.setAddress(bikeStationJson.getString("address"));

                JSONObject stationPosition = bikeStationJson.getJSONObject("position");
                bikeStation.setLatitude(Double.parseDouble(stationPosition.getString("lat")));
                bikeStation.setLongitude(Double.parseDouble(stationPosition.getString("lng")));

                //bikeStationArrayList.add(bikeStation);

                LatLng bikeStation1 = new LatLng(Double.parseDouble(stationPosition.getString("lat")), Double.parseDouble(stationPosition.getString("lng")));
                Marker marker = mMap.addMarker(new MarkerOptions().position(bikeStation1).title(bikeStationJson.getString("name")));
                marker.showInfoWindow();
                marker.setTag(i);
            }
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(53.349562, -6.278198), 14.0f));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Toast.makeText(getApplicationContext(),""+marker.getTag()+"is the marker position",Toast.LENGTH_SHORT);
                try {

                    Intent intent = new Intent(BikeStationsMapLocationActivity.this, BikeStationDetailMapActivity.class);
                    intent.putExtra("BikeStationPosition", marker.getTag().toString());
                    startActivity(intent);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            }
        });
    }

}

