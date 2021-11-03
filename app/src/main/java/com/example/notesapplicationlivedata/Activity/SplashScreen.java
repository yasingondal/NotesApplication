package com.example.notesapplicationlivedata.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.notesapplicationlivedata.MainActivity;
import com.example.notesapplicationlivedata.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //For hiding Top ActionBar
        getSupportActionBar().hide();


        // handler we use for threads means this function(postDelayed) will work as thread..
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //splash Activity code will run after 3 secs
                startActivity(new Intent(SplashScreen.this, MainActivity.class));

                //we use finish to avoid coming back of user to splash Screen after user moves.
                finish();

            }
        },3000);


    }
}