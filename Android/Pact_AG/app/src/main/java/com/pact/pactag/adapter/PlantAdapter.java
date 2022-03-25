package com.pact.pactag.adapter;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.pact.pactag.MainActivity;
import com.pact.pactag.PlantModel;
import com.pact.pactag.ProfilePlantActivity;
import com.pact.pactag.R;

import java.util.ArrayList;

public class PlantAdapter extends RecyclerView.Adapter<PlantAdapter.ViewHolder>{

    int layoutId ;
    ArrayList<PlantModel> plantList ;
    MainActivity context;

    public PlantAdapter(int layoutId, ArrayList plantList, MainActivity context) {

        this.layoutId = layoutId;
        this.plantList = plantList ;
        this.context = context ;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        PlantAdapter.ViewHolder view = new PlantAdapter.ViewHolder(LayoutInflater.
                from(parent.getContext()).
                inflate(layoutId, parent, false));

        return view;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,
                                 @SuppressLint("RecyclerView") int position) {
        Log.d(TAG, "onBindViewHolder: called ");
        PlantModel currentPlant = plantList.get(position);
        Glide.with(context).load(Uri.parse(currentPlant.mUrlImage)).into(holder.plantImage);
        if (holder.plantName != null) {
            holder.plantName.setText(currentPlant.mName);
        }

        if (holder.plantDescription != null) {
            holder.plantDescription.setText(currentPlant.mDescription);
        }

        holder.plantImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on an image: " + plantList.get(position).mName);

                Intent intent = new Intent(context, ProfilePlantActivity.class);
                intent.putExtra("image_url", plantList.get(position).mUrlImage);
                intent.putExtra("image_name", plantList.get(position).mName);
                intent.putExtra("image_description", plantList.get(position).mDescription);
                intent.putExtra("image_notif", plantList.get(position).mNotif);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return plantList.size();
    }

    public class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {

        ImageView plantImage;
        TextView plantName;
        TextView plantDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.plantImage = (ImageView)this.itemView.findViewById(R.id.image_item);
            this.plantName = (TextView)this.itemView.findViewById(R.id.name_item);
            this.plantDescription = (TextView)this.itemView.findViewById(R.id.description_item);
        }
    }
}
