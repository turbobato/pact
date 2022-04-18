package com.pact.pactag.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pact.pactag.MainActivity;
import com.pact.pactag.R;
import com.pact.pactag.adapter.Adapter;
import com.pact.pactag.adapter.AdapterBox;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BoxesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BoxesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private MainActivity context ;






    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BoxesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BoxesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BoxesFragment newInstance(String param1, String param2) {
        BoxesFragment fragment = new BoxesFragment();
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
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_boxes, container, false);
        ArrayList list_nom = new ArrayList();
        ArrayList list_id = new ArrayList<>();
        list_nom.add("box A");
        list_id.add("1");
        list_nom.add("box b");
        list_id.add("2");
        list_nom.add("box c");
        list_id.add("3");


        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView3);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.context);
        recyclerView.setLayoutManager(linearLayoutManager);
        AdapterBox adapter = new AdapterBox(getActivity(), list_nom, list_id);
        recyclerView.setAdapter(adapter);
        return rootView;

    }
}