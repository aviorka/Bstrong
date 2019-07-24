package com.example.aviorka.bstrong;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
/*
 * SplashScreen
 * Use thread for waiting until the app build
 * Show the logo
 * sleep for 5 sec
 */
public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_screen);
        try {
            //אאThread for the splash screen sleep 5 secound and start AutoPicture activity
            Thread myThred = new Thread() {
                @Override
                public void run() {
                    try {
                        sleep(5000);
                        Intent intent = new Intent(SplashScreen.this, AutoPicture.class);
                        startActivity(intent);
                        finish();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            myThred.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
