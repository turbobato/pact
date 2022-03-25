package com.pact.pactag.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.bumptech.glide.Glide;
import com.pact.pactag.MainActivity;
import com.pact.pactag.PlantModel;
import com.pact.pactag.R;

import java.util.ArrayList;

public class PlantAdapter extends Adapter<PlantAdapter.ViewHolder> {

   private int layoutId;
    private ArrayList<PlantModel> plantList;
    private final MainActivity context;

    public PlantAdapter(int layoutId, ArrayList<PlantModel> plantList, MainActivity context) {

        this.layoutId = layoutId;
        this.plantList = plantList;
        this.context = context;
    }

    @NonNull
    public PlantAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PlantAdapter.ViewHolder view = new PlantAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(this.layoutId, parent, false));

        return view;
    }


    public void onBindViewHolder(@NonNull PlantAdapter.ViewHolder holder, int position) {

        PlantModel currentPlant = (PlantModel)this.plantList.get(position);

        //file/depedencies => glide git hub nvll bibli
        Glide.with(this.context).load(Uri.parse(currentPlant.mUrlImage)).into(holder.plantImage);
        if (holder.plantName != null) {
            holder.plantName.setText(currentPlant.mName);
        }

        if (holder.plantDescription != null) {
            holder.plantDescription.setText(currentPlant.mDescription);
        }

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

    public int getItemCount() {
        return this.plantList.size();
    }
}
