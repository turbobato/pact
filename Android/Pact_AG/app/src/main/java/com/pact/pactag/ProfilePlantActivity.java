package com.pact.pactag;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.pact.pactag.model.PatchSender;

import org.json.JSONObject;

public class ProfilePlantActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_profile_plants);
        Button buttonArrosage = (Button) findViewById(R.id.buttonArrosage);
        Button buttonLamp = (Button) findViewById(R.id.buttonLamp);
        ImageButton button_refresh = (ImageButton) findViewById(R.id.button_refresh);
        TextView value_hum = (TextView) findViewById(R.id.valeur_hum);
        TextView value_lum = (TextView) findViewById(R.id.valeur_lum);
        TextView value_temp = (TextView) findViewById(R.id.valeur_temp);
        buttonArrosage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ProfilePlantActivity.this, Add_waterActivity.class);
                startActivity(intent);




            }
        });
        buttonLamp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ProfilePlantActivity.this, Add_lightActivity.class);
                startActivity(intent);




            }
        });
        button_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)  {
                String s = "";
                String lum_s ="";
                String temp_s ="";
                GetRequest getRequest = new GetRequest();
                try {
                    JSONObject  json =  getRequest.sendGet("http://192.168.1.160:8055/items/Light_Sensor/1");
                    JSONObject  json2 =  getRequest.sendGet("http://192.168.1.160:8055/items/Humidity_sensor/1");
                    JSONObject  json3 =  getRequest.sendGet("http://192.168.1.160:8055/items/Temperature_sensor/1");
                    double value = json.getJSONObject("data").getDouble("value");
                    double value2 = json2.getJSONObject("data").getDouble("value");
                    double value3 = json2.getJSONObject("data").getDouble("value");
                     s = ""+value;
                     lum_s = ""+ value2;
                     temp_s =""+ value3;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
                value_hum.setText(s);
                value_lum.setText(lum_s);
                value_temp.setText(temp_s);


            }
        });
    }







}
