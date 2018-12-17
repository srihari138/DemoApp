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
import org.w3c.dom.Text;

public class BikeStationDetailActivity extends AppCompatActivity {

    BikeStation bikeStation;

    ImageView bikeStationImageView;
    TextView BikeStationNameTextView, BikeStationNumberTextView, BikeStationAddressTextView;
    String bikeStationsJSONString = "";
    String saddress, snumber, slat, slgn, sname;
    int position;
    Button bikebutton;

    String bikeStationName, bikeStationAddress, banking, status, bikeStands, availableBikeStands, availableBikes, last_Update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike_station_detail);

        bikeStationImageView = (ImageView) findViewById(R.id.bikeStationImageView);
        BikeStationNameTextView = (TextView) findViewById(R.id.BikeStationNameTextView);
        BikeStationNumberTextView = (TextView) findViewById(R.id.textViewNumber);
        BikeStationAddressTextView = (TextView) findViewById(R.id.textViewAddress);
        bikebutton = (Button) findViewById(R.id.buttoncall);


        Bundle bundle = getIntent().getExtras();
        position = Integer.parseInt(bundle.get("Position").toString());
        saddress = bundle.get("address").toString();
        slat = bundle.get("lat").toString();
        slgn = bundle.get("lgn").toString();
        snumber = bundle.get("number").toString();
        sname = bundle.get("BikeStationName").toString();


        bikebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"calling...",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:7406224515"));
                startActivity(intent);
            }
        });
        bikeStationsJSONString = getString(R.string.static_bike_station_data);
        BikeStationNameTextView.setText(sname);
        BikeStationNumberTextView.setText(snumber);
        BikeStationAddressTextView.setText(saddress);

        int id = getResources().getIdentifier("com.example.kirand.demoapp:drawable/bike_station" + position, null, null);
        bikeStationImageView.setBackground(getResources().getDrawable(id));



    }
}
