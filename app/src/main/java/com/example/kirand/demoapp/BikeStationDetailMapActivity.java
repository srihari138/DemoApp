package com.example.kirand.demoapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kirand.demoapp.DataClasses.BikeStation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BikeStationDetailMapActivity extends AppCompatActivity {

    ArrayList<BikeStation> bikeStationArrayList = new ArrayList<>();
    String bikeStationsJSONString = "";
    ImageView bikeStationImageView;
    TextView BikeStationNameTextView, BikeStationNumberTextView, BikeStationAddressTextView;
    int position;
    Button bikebutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike_station_detail_map);

        bikeStationImageView = (ImageView) findViewById(R.id.bikeStationImageView);
        BikeStationNameTextView = (TextView) findViewById(R.id.BikeStationNameTextView);
        BikeStationNumberTextView = (TextView) findViewById(R.id.textViewNumber);
        BikeStationAddressTextView = (TextView) findViewById(R.id.textViewAddress);
        bikebutton = (Button) findViewById(R.id.buttoncall);

        Bundle bundle = getIntent().getExtras();
        position = Integer.parseInt(bundle.get("BikeStationPosition").toString());
        Toast.makeText(getApplicationContext(), "" + position, Toast.LENGTH_SHORT).show();

        bikebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"calling...",Toast.LENGTH_SHORT).show();

                Toast.makeText(getApplicationContext(),"calling...",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:7406224515"));
                startActivity(intent);
            }
        });
        bikeStationsJSONString = getString(R.string.static_bike_station_data);

        JSONArray bikeStationsArray = null;
        try {
            bikeStationsArray = new JSONArray(bikeStationsJSONString);
            JSONObject bikeStationJson = bikeStationsArray.getJSONObject(position);
            BikeStationNameTextView.setText(bikeStationJson.getString("name"));
            BikeStationNumberTextView.setText(bikeStationJson.getString("number"));
            BikeStationAddressTextView.setText(bikeStationJson.getString("address"));

             int id = getResources().getIdentifier("com.example.kirand.demoapp:drawable/bike_station" + position, null, null);
             bikeStationImageView.setBackground(getResources().getDrawable(id));

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
