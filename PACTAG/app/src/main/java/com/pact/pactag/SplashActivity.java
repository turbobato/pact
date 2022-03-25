package com.pact.pactag;

import android.content.Intent;
import android.database.sqlite.SQLiteAccessPermException;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends MainActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
            }
        },2000);
    }
}
