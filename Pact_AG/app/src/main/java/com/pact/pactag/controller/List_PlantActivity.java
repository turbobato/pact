package com.pact.pactag.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pact.R;
import com.example.pact.model.Plant;

import java.util.List;

public class List_PlantActivity extends AppCompatActivity {
    private ImageButton mReturnButton2;
    private Button mButtonAjout;
    private List<Plant> mPlantList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_plant);
        mReturnButton2 = findViewById(R.id.return2);
        mButtonAjout =  findViewById(R.id.button_ajout);


        mReturnButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gameActivityIntent = new Intent(List_PlantActivity.this, MainActivity.class);
                startActivity(gameActivityIntent);


            }


        });
        mButtonAjout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gameActivityIntent = new Intent(List_PlantActivity.this, Creation_PlantActivity.class);
                startActivity(gameActivityIntent);








            }


        });




    }
}