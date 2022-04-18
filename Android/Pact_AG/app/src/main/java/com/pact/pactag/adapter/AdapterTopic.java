package com.pact.pactag.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pact.pactag.R;

import java.util.ArrayList;

// Extends the Adapter class to RecyclerView.Adapter
// and implement the unimplemented methods
public class AdapterTopic extends RecyclerView.Adapter<AdapterTopic.ViewHolder> {
    ArrayList nom_topic, imageUtilisateur;
    ArrayList nb_reponse;
    Context context;

    // Constructor for initialization
    public AdapterTopic(Context context, ArrayList nom_topic, ArrayList imageUtilisateur, ArrayList nb_reponse) {
        this.context = context;
        this.nom_topic = nom_topic;
        this.imageUtilisateur = imageUtilisateur;
        this.nb_reponse = nb_reponse;
    }

    @NonNull
    @Override
    public AdapterTopic.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflating the Layout(Instantiates list_item.xml
        // layout file into View object)
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_topic, parent, false);

        // Passing view to ViewHolder
        AdapterTopic.ViewHolder viewHolder = new AdapterTopic.ViewHolder(view);
        return viewHolder;
    }

    // Binding data to the into specified position
    @Override
    public void onBindViewHolder(@NonNull AdapterTopic.ViewHolder holder, int position) {
        // TypeCast Object to int type
        int res = (int) imageUtilisateur.get(position);
        holder.images.setImageResource(res);
        holder.text1.setText((String) nom_topic.get(position));
        holder.text2.setText((String) nb_reponse.get(position));

    }

    @Override
    public int getItemCount() {
        // Returns number of items
        // currently available in Adapter
        return imageUtilisateur.size();
    }

    // Initializing the Views
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView images;
        TextView text1;
        TextView text2;

        public ViewHolder(View view) {
            super(view);
            this.images = (ImageView) view.findViewById(R.id.imageUtilisateur);
            this.text1 = (TextView) view.findViewById(R.id.nom_topic);
            this.text2 = (TextView) view.findViewById(R.id.nb_reponse);
        }
    }
}