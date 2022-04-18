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
public class AdapterBox extends RecyclerView.Adapter<AdapterBox.ViewHolder> {
    ArrayList list_nom, list_id;

    Context context;

    // Constructor for initialization
    public AdapterBox(Context context, ArrayList list_nom, ArrayList list_id  ) {
        this.context = context;
        this.list_nom = list_nom;
        this.list_id = list_id;

    }

    @NonNull
    @Override
    public AdapterBox.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflating the Layout(Instantiates list_item.xml
        // layout file into View object)
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_box, parent, false);

        // Passing view to ViewHolder
        AdapterBox.ViewHolder viewHolder = new AdapterBox.ViewHolder(view);
        return viewHolder;
    }

    // Binding data to the into specified position
    @Override
    public void onBindViewHolder(@NonNull AdapterBox.ViewHolder holder, int position) {
        // TypeCast Object to int type

        holder.text1.setText((String) list_nom.get(position));
        holder.text2.setText((String) list_id.get(position));


    }

    @Override
    public int getItemCount() {
        // Returns number of items
        // currently available in Adapter
        return list_nom.size();
    }

    // Initializing the Views
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView text1;
        TextView text2;


        public ViewHolder(View view) {
            super(view);
            this.text1 = (TextView) view.findViewById(R.id.name_box);
            this.text2 = (TextView) view.findViewById(R.id.id_box);

        }
    }

}
