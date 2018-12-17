package com.example.kirand.demoapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kirand.demoapp.BikeStationDetailActivity;
import com.example.kirand.demoapp.BikeStationsActivity;
import com.example.kirand.demoapp.DataClasses.BikeStation;
import com.example.kirand.demoapp.R;

import java.util.List;

/**
 * Created by kirand on 4/11/2018.
 */

public class BikeStationsAdapter extends RecyclerView.Adapter<BikeStationsAdapter.ViewHolder>
{
    private Context context;
    private List<BikeStation> bikeStationArrayList;

    public BikeStationsAdapter(Context context, List bikeStationArrayList) {
        this.context = context;
        this.bikeStationArrayList = bikeStationArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.bike_stations_row_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.itemView.setTag(bikeStationArrayList.get(position));

        final BikeStation bikeStation= bikeStationArrayList.get(position);

        holder.bikeStationNameTextView.setText(bikeStation.getName());
        holder.bikeStationAddressTextView.setText(bikeStation.getAddress());
        int id = context.getResources().getIdentifier("com.example.kirand.demoapp:drawable/bike_station" + position, null, null);
        holder.bikeStationImageView.setBackground(context.getResources().getDrawable(id));


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, BikeStationDetailActivity.class);
                intent.putExtra("Position",position);
                intent.putExtra("BikeStationName",bikeStation.getName());
                intent.putExtra("address",bikeStation.getAddress());
                intent.putExtra("lat",bikeStation.getLatitude());
                intent.putExtra("lgn",bikeStation.getLongitude());
                intent.putExtra("number",bikeStation.getNumber());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return  bikeStationArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView bikeStationNameTextView,bikeStationAddressTextView;
        public ImageView bikeStationImageView;

        public ViewHolder(View itemView) {
            super(itemView);

            bikeStationNameTextView     = (TextView) itemView.findViewById(R.id.bikeStationNameTextView);
            bikeStationAddressTextView  = (TextView) itemView.findViewById(R.id.bikeStationAddressTextView);
            bikeStationImageView        = (ImageView) itemView.findViewById(R.id.bikeStationImageView);



        }
    }

}
