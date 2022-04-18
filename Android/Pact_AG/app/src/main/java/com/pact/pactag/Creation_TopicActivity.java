package com.pact.pactag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import forumapp.ForumPost;



import com.pact.pactag.fragments.ForumFragment;

public class Creation_TopicActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_creation_topic);
        Button mButtonRetour = (Button) findViewById(R.id.buttonRetour);
        Button mButtonCreer = (Button) findViewById(R.id.buttonCreer);
        TextView textView = (TextView) findViewById(R.id.textView8);

        final EditText editTopic = (EditText)findViewById(R.id.editTopic);



        mButtonRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

            }
        });

        mButtonCreer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nom_topic = editTopic.getText().toString();
                ForumPost post = new ForumPost(nom_topic, "Maxime","http://192.168.1.160:8055/items/Messages" );
                post.postMessage();
                finish();


            }
        });




    }
}