package com.example.kirand.demoapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kirand.demoapp.Adapters.BikeStationsAdapter;
import com.example.kirand.demoapp.DataClasses.BikeStation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class BikeStationsActivity extends AppCompatActivity{

    //BikeStationsAdapter1 bikeStationsAdapter;
    ArrayList<BikeStation> bikeStationArrayList = new ArrayList<>();
    
    RecyclerView bikeStationsRecyclerView;
    String bikeStationsJSONString = "";
    RecyclerView recyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager layoutManager;
    LinearLayout progressLinearLayout;
    ProgressBar progressBar;
    TextView errorTextView;
    Button refreshButton;
    FloatingActionButton fabMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike_stations);


        fabMap = (FloatingActionButton) findViewById(R.id.fabMap);
        fabMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                Intent intent = new Intent(BikeStationsActivity.this,BikeStationsMapLocationActivity.class);
                intent.putExtra("BikeStationData",bikeStationArrayList);
                startActivity(intent);
            }
        });

        bikeStationsRecyclerView    = (RecyclerView) findViewById(R.id.bikeStationsRecyclerView);
        progressLinearLayout        = (LinearLayout) findViewById(R.id.progressLinearLayout);
        progressBar                 = (ProgressBar) findViewById(R.id.progressBar);
        errorTextView               = (TextView) findViewById(R.id.errorTextView);
        refreshButton               = (Button) findViewById(R.id.refreshButton);

        bikeStationsJSONString = getString(R.string.static_bike_station_data);

        try {
            //JSONObject jsonObject = new JSONObject(bikeStationsJSONString);
            JSONArray bikeStationsArray = new JSONArray(bikeStationsJSONString);

            for (int i = 0; i < bikeStationsArray.length(); i++) {
                BikeStation bikeStation = new BikeStation();

                JSONObject bikeStationJson = bikeStationsArray.getJSONObject(i);

                bikeStation.setNumber(Integer.parseInt(bikeStationJson.getString("number")));
                bikeStation.setName(bikeStationJson.getString("name"));
                bikeStation.setAddress(bikeStationJson.getString("address"));

                JSONObject stationPosition = bikeStationJson.getJSONObject("position");
                bikeStation.setLatitude(Double.parseDouble(stationPosition.getString("lat")));
                bikeStation.setLongitude(Double.parseDouble(stationPosition.getString("lng")));

                bikeStationArrayList.add(bikeStation);
            }

            recyclerView = (RecyclerView) findViewById(R.id.bikeStationsRecyclerView);
            recyclerView.setHasFixedSize(true);

            layoutManager = new LinearLayoutManager(this);

            recyclerView.setLayoutManager(layoutManager);

            mAdapter = new BikeStationsAdapter(this, bikeStationArrayList);

            recyclerView.setAdapter(mAdapter);

            progressLinearLayout.setVisibility(View.GONE);
            bikeStationsRecyclerView.setVisibility(View.VISIBLE);
            fabMap.setVisibility(View.VISIBLE);
        } catch (JSONException e) {
            e.printStackTrace();
            progressLinearLayout.setVisibility(View.VISIBLE);
            errorTextView.setVisibility(View.VISIBLE);
            refreshButton.setVisibility(View.VISIBLE);
            bikeStationsRecyclerView.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);

            errorTextView.setText(e.toString());
        }

    }

    @Override
    public void onBackPressed()
    {
         finish();
    }



    public void showBikeStationsData(){

        if (bikeStationsJSONString != "" && bikeStationsJSONString != null){

            try {
                //JSONObject jsonObject = new JSONObject(bikeStationsJSONString);
                JSONArray bikeStationsArray = new JSONArray(bikeStationsJSONString);

                for (int i = 0; i < bikeStationsArray.length(); i++) {
                    BikeStation bikeStation = new BikeStation();

                    JSONObject bikeStationJson = bikeStationsArray.getJSONObject(i);

                    bikeStation.setNumber(Integer.parseInt(bikeStationJson.getString("number")));
                    bikeStation.setName(bikeStationJson.getString("name"));
                    bikeStation.setAddress(bikeStationJson.getString("address"));

                    JSONObject stationPosition = bikeStationJson.getJSONObject("position");
                    bikeStation.setLatitude(Double.parseDouble(stationPosition.getString("lat")));
                    bikeStation.setLongitude(Double.parseDouble(stationPosition.getString("lng")));

                    bikeStationArrayList.add(bikeStation);
                }

                recyclerView = (RecyclerView) findViewById(R.id.bikeStationsRecyclerView);
                recyclerView.setHasFixedSize(true);

                layoutManager = new LinearLayoutManager(this);

                recyclerView.setLayoutManager(layoutManager);

                mAdapter = new BikeStationsAdapter(this, bikeStationArrayList);

                recyclerView.setAdapter(mAdapter);

                progressLinearLayout.setVisibility(View.GONE);
                bikeStationsRecyclerView.setVisibility(View.VISIBLE);
                fabMap.setVisibility(View.VISIBLE);
            } catch (JSONException e) {
                e.printStackTrace();
                progressLinearLayout.setVisibility(View.VISIBLE);
                errorTextView.setVisibility(View.VISIBLE);
                refreshButton.setVisibility(View.VISIBLE);
                bikeStationsRecyclerView.setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);

                errorTextView.setText(e.toString());
            }
        }

    }



}
