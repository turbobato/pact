package com.example.pact.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.pact.R;
import com.example.pact.model.Plant;

import java.util.ArrayList;
import java.util.List;

public class Creation_PlantActivity extends AppCompatActivity {
    private Button mReturnButton3;
    private Button mValider;
    private EditText mEditNom;
    private Plant plant;
    private Spinner mSelDiag;
    private Spinner mSelHum;
    private Spinner mSelLum;
    private Spinner mSelType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation_plant);
        mReturnButton3 = findViewById(R.id.return3);
        mValider = findViewById(R.id.valider);
        mEditNom = findViewById(R.id.editNom);
        mSelDiag = (Spinner) findViewById(R.id.select_diag);
        mSelHum = (Spinner) findViewById(R.id.select_hum);
        mSelLum = (Spinner) findViewById(R.id.select_lum);
        mSelType = (Spinner) findViewById(R.id.type);
        List boxList = new ArrayList();
        boxList.add("non specifié ");
        boxList.add("1");
        boxList.add("2");
        boxList.add("3");
        List typeList = new ArrayList();
        typeList.add("non identifié");
        typeList.add("Tomate");
        typeList.add("haricot");
        typeList.add("patate");


        mValider.setEnabled(false);


        mReturnButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gameActivityIntent = new Intent(Creation_PlantActivity.this, List_PlantActivity.class);
                startActivity(gameActivityIntent);


            }


        });





        ArrayAdapter adapter = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                boxList
        );
        ArrayAdapter adapter2 = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                typeList
        );



        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mSelDiag.setAdapter(adapter);
        mSelType.setAdapter(adapter2);
        mSelLum.setAdapter(adapter);
        mSelHum.setAdapter(adapter);
        mEditNom.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mValider.setEnabled(!editable.toString().isEmpty());

            }
        });
        Plant.setNomPlant(mEditNom.getText().toString());
        mValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gameActivityIntent = new Intent(Creation_PlantActivity.this, List_PlantActivity.class);
                startActivity(gameActivityIntent);


            }


        });





    }















}