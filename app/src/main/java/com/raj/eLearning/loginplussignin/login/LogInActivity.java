package com.raj.eLearning.loginplussignin.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.google.android.material.textfield.TextInputLayout;
import com.raj.eLearning.MainActivity;
import com.raj.eLearning.R;
import com.raj.eLearning.database.SqliteService;
import com.raj.eLearning.iswifi.CheckingConnection;
import com.raj.eLearning.loginplussignin.forgotpassword.FirstForgotPasswordActivity;
import com.raj.eLearning.loginplussignin.signup.FirstSignUpActivity;
import com.raj.eLearning.model.User;
import com.raj.eLearning.user.UserDashBoard;

public class LogInActivity extends AppCompatActivity {
    private TextInputLayout usernameLayout, passwordLayout;
    private String username,password;
    SqliteService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        if (! new CheckingConnection(this).getConnectionStatus(this)){

        }else {
            init();
        }

    }
    public void loginAccount(View view) {
        username=usernameLayout.getEditText().getText().toString().trim();
        password=passwordLayout.getEditText().getText().toString().trim();
        //check this user exist or not in data base

         User user= service.getUserByUserName(username);
         if (user ==null){
           usernameLayout.setError("this user not exist ");
             return ;
         }
          SharedPreferences sharedPreferences=getSharedPreferences("e-learning",MODE_PRIVATE);
          SharedPreferences.Editor editor=sharedPreferences.edit();
          editor.putString("currentUser",username);
          editor.apply();

        //after checking move dashboard activity
        startActivity(new Intent(getApplicationContext(), UserDashBoard.class));
    }
    public void init() {
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        usernameLayout=findViewById(R.id.login_username);
        passwordLayout=findViewById(R.id.login_password);
        service=new SqliteService(this);
        if (getIntent().getIntExtra("fromSignIn",0)==560){
            usernameLayout.getEditText().setText(getIntent().getStringExtra("userName"));
            passwordLayout.getEditText().setText(getIntent().getStringExtra("password"));

        }

    }

    public void movetoPasswordReset(View view) {
        startActivity(new Intent(getApplicationContext(), FirstForgotPasswordActivity.class));
    }

    public void callSignUpScreenfromLogin(View view) {
        startActivity(new Intent(getApplicationContext(), FirstSignUpActivity.class));
        finish();
    }

    public void backToHome(View view) {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }


}