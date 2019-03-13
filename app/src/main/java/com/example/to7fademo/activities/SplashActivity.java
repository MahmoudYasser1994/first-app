package com.example.to7fademo.activities;

import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.to7fademo.R;
import com.example.to7fademo.SharedPreference.SharedPreferencesConfig;

public class SplashActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 2000;

    SharedPreferencesConfig preferencesConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_splash);
        preferencesConfig = new SharedPreferencesConfig (getApplicationContext ( ));


        new Handler ( ).postDelayed (new Runnable ( ) {
            @Override
            public void run() {

                if (preferencesConfig.readLoginStatus ()) {

                    Intent myintent = new Intent (SplashActivity.this, FirstActivity.class);
                    startActivity (myintent);
                    finish ( );
                }
                else{
                    Intent myintent = new Intent (SplashActivity.this, LoginActivity.class);
                    startActivity (myintent);
                    finish ( );

                }
            }
        }, SPLASH_DISPLAY_LENGTH);


    }
}
