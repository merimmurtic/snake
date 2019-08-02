package com.mygame.mygame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AdvertisementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertisement);
        Thread thread = new Thread() {
            @Override
            public void run() {

                try {

                    sleep(3000);

                } catch (Exception e) {

                    e.printStackTrace();

                } finally {

                    Intent mainIntent = new Intent(AdvertisementActivity.this, MainActivity.class);
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