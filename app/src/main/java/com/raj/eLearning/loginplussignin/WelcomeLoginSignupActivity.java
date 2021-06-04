package com.raj.eLearning.loginplussignin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.raj.eLearning.MainActivity;
import com.raj.eLearning.R;
import com.raj.eLearning.loginplussignin.login.LogInActivity;
import com.raj.eLearning.loginplussignin.signup.FirstSignUpActivity;
import com.raj.eLearning.owner.HowWeWorkActivity;

public class WelcomeLoginSignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_login_signup);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public void callLoginScreen(View view) {
        startActivity(new Intent(getApplicationContext(), LogInActivity.class));

    }

    public void callSignUpScreen(View view) {
        startActivity(new Intent(getApplicationContext(), FirstSignUpActivity.class));

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }

    public void howWeWork(View view) {
        startActivity(new Intent(getApplicationContext(), HowWeWorkActivity.class));
    }
}