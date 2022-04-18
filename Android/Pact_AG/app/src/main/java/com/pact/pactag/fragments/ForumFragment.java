package com.pact.pactag.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.pact.pactag.Creation_PlantActivity;
import com.pact.pactag.Creation_TopicActivity;
import com.pact.pactag.MainActivity;
import com.pact.pactag.R;
import com.pact.pactag.adapter.Adapter;
import com.pact.pactag.adapter.AdapterTopic;

import java.util.ArrayList;
import java.util.Arrays;

import forumapp.ForumPost;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ForumFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ForumFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private MainActivity context ;
    ArrayList imageUtilisateur = new ArrayList<>();
    ArrayList nom_topic = new ArrayList<>();
    ArrayList nb_reponse = new ArrayList<>();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ForumFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ForumFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ForumFragment newInstance(String param1, String param2) {
        ForumFragment fragment = new ForumFragment();
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
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        View rootView = inflater.inflate(R.layout.fragment_forum, container, false);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView2);
        ImageButton mButtonCharge =(ImageButton) rootView.findViewById(R.id.button_charge);

        Button mButtonTopic = (Button) rootView.findViewById(R.id.buttonTopic);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.context);
        recyclerView.setLayoutManager(linearLayoutManager);
        mButtonCharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ForumPost post = new ForumPost("bonjour", "Guilhem","http://192.168.1.160:8055/items/Messages" );
                ArrayList<ForumPost> list_post = post.getMessages();
                 int n = list_post.size();

                 imageUtilisateur = new ArrayList<>();
                 nom_topic = new ArrayList<>();
                 nb_reponse = new ArrayList<>();


                 for(int i =0; i < n; i++ ){

                     imageUtilisateur.add(R.drawable.icon_users2);
                     String user =list_post.get(i).getUser();


                     nom_topic.add(list_post.get(i).getMessage());
                     nb_reponse.add(user);

                 }

                AdapterTopic adapter = new AdapterTopic(getActivity(), nom_topic, imageUtilisateur, nb_reponse);
                recyclerView.setAdapter(adapter);



            }
        });
        mButtonTopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Creation_TopicActivity.class);
                startActivity(intent);

            }
        });





        return rootView;
    }
}