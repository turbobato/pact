package com.pact.pactag;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.pact.pactag.model.PatchSender;

public class Add_lightActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_light);
        Button boutton_allumee = (Button) findViewById(R.id.button_eclairage_allumee);
        Button boutton_eteindre = (Button) findViewById(R.id.button_eclairage_eteindre);
        boutton_allumee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PatchSender patch = new PatchSender();
                String url = "http://192.168.1.160:8055/items/Lamp/1";

                patch.setLuxValue(url,"http://192.168.1.104/lampLight",255);

            }
        });
        boutton_eteindre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PatchSender patch = new PatchSender();
                String url = "http://192.168.1.160:8055/items/Lamp/1";

                patch.setLuxValue(url,"http://192.168.1.104/lampLight",0);

            }
        });
    }
}