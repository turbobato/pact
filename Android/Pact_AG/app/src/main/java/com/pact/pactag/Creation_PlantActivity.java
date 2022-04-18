package com.pact.pactag;

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

import com.pact.pactag.R;

import java.util.ArrayList;
import java.util.List;

public class Creation_PlantActivity extends AppCompatActivity {
    private Button mReturnButton3;
    private Button mValider;
    private EditText mEditNom;


    private Spinner mSelType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation_plant);
        mReturnButton3 = findViewById(R.id.return3);
        mValider = findViewById(R.id.valider);
        mEditNom = findViewById(R.id.editNom);

        mSelType = (Spinner) findViewById(R.id.type);

        List typeList = new ArrayList();
        typeList.add("non identifié");
        typeList.add("Tomate");
        typeList.add("haricot");
        typeList.add("patate");


        mValider.setEnabled(false);












        ArrayAdapter adapter2 = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                typeList
        );



        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        mSelType.setAdapter(adapter2);

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







    }















}