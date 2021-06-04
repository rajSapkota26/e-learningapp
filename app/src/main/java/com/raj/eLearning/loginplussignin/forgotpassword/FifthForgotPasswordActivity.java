package com.raj.eLearning.loginplussignin.forgotpassword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.raj.eLearning.R;
import com.raj.eLearning.loginplussignin.login.LogInActivity;

public class FifthForgotPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth_forgot_password);

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public void goToLogInFromForgotPassword(View view) {
        startActivity(new Intent(getApplicationContext(), LogInActivity.class));
        finish();
    }

    public void goForLoginsuccess(View view) {
        startActivity(new Intent(getApplicationContext(), LogInActivity.class));
        finish();
    }
}