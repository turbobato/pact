package com.pact.pactag.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.pact.pactag.MainActivity;
import com.pact.pactag.PlantModel;
import com.pact.pactag.R;
import com.pact.pactag.adapter.PlantAdapter;
import com.pact.pactag.adapter.PlantItemDecoration;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private final MainActivity context ;

    public HomeFragment(MainActivity context) { this.context = context; }


    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        ArrayList<PlantModel> plantList = new ArrayList();

        plantList.add(new PlantModel("Orchidée", "une fleur blanche", "https://cdn.pixabay.com/photo/2021/10/08/16/22/plant-6691763_1280.jpg", true));
        plantList.add(new PlantModel("Cactus", "une plante qui nécessite peu d'entretien", "https://cdn.pixabay.com/photo/2015/04/10/17/03/pots-716579_1280.jpg", true));
        plantList.add(new PlantModel("Carottes", "les carottes sont bonnes pour le cerveau", "https://cdn.pixabay.com/photo/2016/08/03/01/09/carrot-1565597_1280.jpg", false));
        plantList.add(new PlantModel("Orchidée", "une fleur blanche", "https://cdn.pixabay.com/photo/2021/10/08/16/22/plant-6691763_1280.jpg", false));
        plantList.add(new PlantModel("Cactus", "une plante qui nécessite peu d'entretien", "https://cdn.pixabay.com/photo/2015/04/10/17/03/pots-716579_1280.jpg", false));




        RecyclerView horizontalRecyclerView = view.findViewById(R.id.horizontal_recycler_view);
        horizontalRecyclerView.setAdapter(new PlantAdapter(R.layout.item_horizontal_plant, plantList,this.context));

        RecyclerView verticalRecyclerView = view.findViewById(R.id.vertical_recycler_view);
        verticalRecyclerView.setAdapter(new PlantAdapter(R.layout.item_vertical_plant,plantList, this.context));
        verticalRecyclerView.addItemDecoration(new PlantItemDecoration(20));

        return view ;
    }
}
