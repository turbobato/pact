package com.pact.pactag.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageButton;

import com.pact.pactag.Creation_PlantActivity;
import com.pact.pactag.MainActivity;
import com.pact.pactag.R;
import com.pact.pactag.adapter.Adapter;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PlantsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlantsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private MainActivity context ;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ArrayList courseImg = new ArrayList<>(Arrays.asList(R.drawable.porchidee, R.drawable.cactus2,
            R.drawable.carotte, R.drawable.plante,
            R.drawable.porchidee, R.drawable.carotte,
            R.drawable.plante, R.drawable.cactus2));
    ArrayList courseName = new ArrayList<>(Arrays.asList("Orchidée", "Cactus", "Carotte", "Connais pas", "Nom de merde",
            "Tomates", "Nouvelles carottes", "patates"));

    public PlantsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PlantsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PlantsFragment newInstance(String param1, String param2) {
        PlantsFragment fragment = new PlantsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_plants, container, false);
        // Inflate the layout for this fragment
        Button mButtonAjout = (Button) rootView.findViewById(R.id.button_ajout);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.context);
        recyclerView.setLayoutManager(linearLayoutManager);
        Adapter adapter = new Adapter(getActivity(), courseImg, courseName);
        recyclerView.setAdapter(adapter);
        mButtonAjout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Creation_PlantActivity.class);
                startActivity(intent);

            }
        });

        return rootView;
    }


}
