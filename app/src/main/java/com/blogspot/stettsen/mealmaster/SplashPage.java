package com.blogspot.stettsen.mealmaster;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SplashPage extends AppCompatActivity {

    private static String FILE = "CookBookDB.json";
    AnimationDrawable watermelonAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_page);
        TextView progressStatus = findViewById(R.id.progressStatus);
        ProgressBar progressBar = findViewById(R.id.splashScreenProgressBar);
        ImageView dancing_Watermelon = (ImageView) findViewById(R.id.dancingWatermelon);
        dancing_Watermelon.setBackgroundResource(R.drawable.dancing_watermelon);
        watermelonAnimation = (AnimationDrawable) dancing_Watermelon.getBackground();
        watermelonAnimation.start();
        boolean done = false;

        Thread newThread = new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    sleep(4000);
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        newThread.start();
        LoadOnStartAsyncTask task = new LoadOnStartAsyncTask(FILE, progressBar, getApplicationContext(), progressStatus);
        task.execute();
    }
}