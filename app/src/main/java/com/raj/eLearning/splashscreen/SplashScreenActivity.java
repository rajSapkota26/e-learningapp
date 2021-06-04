package com.raj.eLearning.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;


import com.raj.eLearning.MainActivity;
import com.raj.eLearning.R;
import com.raj.eLearning.onboardingscreen.OnBoardingActivity;

public class SplashScreenActivity extends AppCompatActivity {
    //define SharedPreferences
private SharedPreferences onBoardingScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        //hide action bar
        getSupportActionBar().hide();

        //hide status bar
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //connect parse server
//        connectServer();


        //screen delay for 5 sec
    new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                //check user enter first time or not
                onBoardingScreen=   getSharedPreferences("onBoardingScreen", MODE_PRIVATE);
                boolean isFirstTime = onBoardingScreen.getBoolean("firstTime", true);
                // if user enter first time then show onboarding screen
                if (isFirstTime) {
                    SharedPreferences.Editor editor=onBoardingScreen.edit();
                    editor.putBoolean("firstTime",false);
                    editor.commit();
                    Intent intent = new Intent(SplashScreenActivity.this, OnBoardingActivity.class);
                    startActivity(intent);
                    finish();
                }//if user not in first time directly go to main activity
                else {
                    Intent intent1 = new Intent(SplashScreenActivity.this, MainActivity.class);
                    startActivity(intent1);
                    finish();
                }
                }
            }, 5000);

    }

}