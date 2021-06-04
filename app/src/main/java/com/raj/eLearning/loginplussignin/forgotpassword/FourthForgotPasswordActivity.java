package com.raj.eLearning.loginplussignin.forgotpassword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.google.android.material.textfield.TextInputLayout;
import com.raj.eLearning.R;
import com.raj.eLearning.database.SqliteService;
import com.raj.eLearning.iswifi.CheckingConnection;
import com.raj.eLearning.loginplussignin.login.LogInActivity;
import com.raj.eLearning.model.User;

public class FourthForgotPasswordActivity extends AppCompatActivity {
    private TextInputLayout pass,conformpass;
    private int userId;
    private User user;
    private SharedPreferences sharedPreferences;
    private SqliteService service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth_forgot_password);
        if (! new CheckingConnection(this).getConnectionStatus(this)){

        }else {
            init();
        }
    }

    public void init(){
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        pass=findViewById(R.id.new_forgot_password);
        conformpass=findViewById(R.id.new_conform_forgot_password);
        userId=getIntent().getIntExtra("userId",0);
        //fetch user
        sharedPreferences = getSharedPreferences("e-learning", MODE_PRIVATE);
        //database
        service=new SqliteService(this);
    }

    public void goToLogInFromForgotPassword(View view) {
        startActivity(new Intent(getApplicationContext(), LogInActivity.class));
        finish();

    }

    public void goToLastOfForgotPassword(View view) {
        if (!validatePassword()){
            return;
        }
        String password=pass.getEditText().getText().toString().trim();
        //update user in database
        //userId,password
        user =service.getUserById(userId);
        user.setPassword(password);
        service.updateUser(user);

        Log.d("userDetails", password+String.valueOf(userId));
        startActivity(new Intent(getApplicationContext(), FifthForgotPasswordActivity.class));
        finish();
    }

    private boolean validatePassword() {
        String val = pass.getEditText().getText().toString().trim();
        String val2 = conformpass.getEditText().getText().toString().trim();
        String checkPassword = "^" +
                "(?=.*[a-zA-Z0-9])" +      //any letter or number
                "(?=\\S+$)" +           //no white spaces
                ".{4,25}" +               //at least 4 characters
                "$";

        if (val.isEmpty()) {
            pass.setError("Field can not be empty");
            return false;
        } else if (val2.isEmpty()){
            conformpass.setError("Field can not be empty");
            return false;
        } else if (!val.matches(checkPassword)||!val2.matches(checkPassword)) {
            pass.setError("Password should contain 4 characters!");
            conformpass.setError("Password should contain 4 characters!");
            return false;
        }else if (!val.equals(val2)){
            conformpass.setError("Password and conform password not match");
            return false;
        }
        else {
            pass.setError(null);
            pass.setErrorEnabled(false);
            return true;
        }
    }
}