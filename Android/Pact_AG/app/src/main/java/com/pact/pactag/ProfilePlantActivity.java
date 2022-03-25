package com.pact.pactag;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class ProfilePlantActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_plants);
    }

    private void getIncomingIntent(){


        if(getIntent().hasExtra("image_url") && getIntent().hasExtra("image_name")){

            String imageUrl = getIntent().getStringExtra("image_url");
            String imageName = getIntent().getStringExtra("image_name");

            setImage(imageUrl, imageName);
        }
    }


    private void setImage(String imageUrl, String imageName){

        TextView name = findViewById(R.id.profile_plant_name);
        name.setText(imageName);

        ImageView image = findViewById(R.id.profile_plant_image);
        Glide.with(this)
                .asBitmap()
                .load(imageUrl)
                .into(image);
    }



}
