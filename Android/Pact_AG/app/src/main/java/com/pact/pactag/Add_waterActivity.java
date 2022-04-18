package com.pact.pactag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.pact.pactag.model.PatchSender;

public class Add_waterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_water);
        Button button_arrosage =(Button) findViewById(R.id.button_confirm_water_manual);
        Button button_eteindre =(Button) findViewById(R.id.button_eteindre);
        button_arrosage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PatchSender patch = new PatchSender();
                String url = "http://192.168.1.160:8055/items/Pump/1";

                patch.setState(url,"http://192.168.1.104/pumpOn",true);

            }
        });
        button_eteindre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PatchSender patch = new PatchSender();
                String url = "http://192.168.1.160:8055/items/Pump/1";

                patch.setState(url,"http://192.168.1.104/pumpOFF",false);

            }
        });


    }
}