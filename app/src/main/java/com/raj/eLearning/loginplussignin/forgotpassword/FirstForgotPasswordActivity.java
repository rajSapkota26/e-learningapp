package com.raj.eLearning.loginplussignin.forgotpassword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.raj.eLearning.R;
import com.raj.eLearning.database.SqliteService;
import com.raj.eLearning.iswifi.CheckingConnection;
import com.raj.eLearning.loginplussignin.login.LogInActivity;
import com.raj.eLearning.model.User;

public class FirstForgotPasswordActivity extends AppCompatActivity {
    private TextInputLayout emailLayout;
    private User user;
    private String email;
    private SharedPreferences sharedPreferences;
    private SqliteService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_forgot_password);
        if (! new CheckingConnection(this).getConnectionStatus(this)){

        }else {
            init();
        }
    }

    public void init() {
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        emailLayout = findViewById(R.id.signup_email_forgot);

        //fetch user
        sharedPreferences = getSharedPreferences("e-learning", MODE_PRIVATE);
        //database
        service=new SqliteService(this);
    }

    public void secondForgotPassword(View view) {
        email = emailLayout.getEditText().getText().toString().trim();

        User user= service.getUserByUserName(sharedPreferences.getString("currentUser", null));
       if (email.equals(user.getEmail())){
           Intent intent = new Intent(getApplicationContext(), SecondForgotPasswordActivity.class);

           //fetch user from database

           //pass user phone and email to next intent
           intent.putExtra("phone", user.getPhone());
           intent.putExtra("email", user.getEmail());
           intent.putExtra("userId", user.getId());

           startActivity(intent);
       }else {
           Toast.makeText(this, "Email didn't match", Toast.LENGTH_SHORT).show();
       }

    }

    public void goToLogInFromForgotPassword(View view) {
        startActivity(new Intent(getApplicationContext(), LogInActivity.class));
        finish();
    }
}