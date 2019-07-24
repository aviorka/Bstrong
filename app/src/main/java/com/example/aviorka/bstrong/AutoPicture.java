package com.example.aviorka.bstrong;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

//AutoPicture activity
//Automatic Image Swipe Timer
//2 button :button 1 send to login ,button 2 send to Join
public class AutoPicture extends AppCompatActivity {

    private RelativeLayout mylay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_picture);

        //Two button for login & join
        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        Button btnJoinUs = (Button) findViewById(R.id.btnJoinUs);

        mylay = (RelativeLayout) findViewById(R.id.myLayout);

        //Start Login activity
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });

        //Start Join Us activity
        btnJoinUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), JoinUs.class);
                startActivity(intent);
            }
        });


        Timer timer = new Timer();
        MyTimer mt = new MyTimer();

        //Displays an image for 4 seconds
        timer.schedule(mt, 2000, 2000);
    }

    //Runnable classn for runnig images from images array
    class MyTimer extends TimerTask {

        public void run() {
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    // An array of images
                    int Images[] = {R.drawable.image1, R.drawable.image2, R.drawable.image3,
                            R.drawable.image4, R.drawable.image5, R.drawable.image6,
                            R.drawable.image7, R.drawable.image8, R.drawable.image9,
                            R.drawable.image10};
                    //run on random images
                    mylay.setBackgroundResource(Images[getRandomNumber()]);
                }

                //Return random number
                private int getRandomNumber() {
                    return new Random().nextInt(10);
                }
            });
        }
    }
}
