package com.mygame.mygame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread thread = new Thread(){
            @Override
            public void run() {

                try {

                    sleep(1500);

                }catch (Exception e){

                    e.printStackTrace();

                } finally {

                    Intent mainIntent = new Intent(SplashActivity.this, AdvertisementActivity.class);
                    startActivity(mainIntent);

                }
            }
        };
        thread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
