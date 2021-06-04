package com.raj.eLearning.user.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.RatingBar;
import android.widget.Toast;

import com.raj.eLearning.MainActivity;
import com.raj.eLearning.R;

public class RateUsActivity extends AppCompatActivity {
private RatingBar ratingBar;
    float ratingNumber;
    float getrating;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_us);
        //hide action bar
        getSupportActionBar().hide();
        //window full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ratingBar=findViewById(R.id.ratingBar);

         ratingNumber = ratingBar.getNumStars();

    }

    public void returnHome(View view) {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    public void rateNow(View view) {
//        ratingNumber = ratingBar.getNumStars();

         getrating = ratingBar.getRating();
        Toast.makeText(this, "Thanks for your "+ratingNumber+" rating", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}