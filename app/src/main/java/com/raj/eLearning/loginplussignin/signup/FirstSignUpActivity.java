package com.raj.eLearning.loginplussignin.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.google.android.material.textfield.TextInputLayout;
import com.raj.eLearning.R;
import com.raj.eLearning.iswifi.CheckingConnection;
import com.raj.eLearning.loginplussignin.WelcomeLoginSignupActivity;
import com.raj.eLearning.loginplussignin.login.LogInActivity;

public class FirstSignUpActivity extends AppCompatActivity {
    TextInputLayout fullName, userName, email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_sign_up);
        if (! new CheckingConnection(this).getConnectionStatus(this)){

        }else {
            init();
        }
    }


    public void secondSignupScreen(View view) {
        if (!validateFullName() || !validateUsername() || !validateEmail() || !validatePassword()) {
            return;
        }
        Intent intent=new Intent(getApplicationContext(), SecondSignUpActivity.class);
        intent.putExtra("full",fullName.getEditText().getText().toString().trim());
        intent.putExtra("user",userName.getEditText().getText().toString().trim());
        intent.putExtra("mail",email.getEditText().getText().toString().trim());
        intent.putExtra("pass",password.getEditText().getText().toString().trim());
        startActivity(intent);
    }


    public void init() {
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        fullName = findViewById(R.id.signUp_fullname);
        userName = findViewById(R.id.signup_username);
        email = findViewById(R.id.signup_email);
        password = findViewById(R.id.signup_password);
    }

    private boolean validateFullName() {
        String val = fullName.getEditText().getText().toString().trim();
        if (val.isEmpty()) {
            fullName.setError("Field can not be empty");
            return false;
        } else {
            fullName.setError(null);
            fullName.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateUsername() {
        String val = userName.getEditText().getText().toString().trim();
        String checkspaces = "\\A\\w{4,20}\\z";

        if (val.isEmpty()) {
            userName.setError("Field can not be empty");
            return false;
        } else if (val.length() > 20) {
            userName.setError("Username is too large!");
            return false;
        } else if (!val.matches(checkspaces)) {
            userName.setError("No White spaces are allowed!");
            return false;
        } else {
            userName.setError(null);
            userName.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateEmail() {
        String val = email.getEditText().getText().toString().trim();
        String checkEmail = "[a-zA-Z0-9._-]+@[a-z]+.+[a-z]+";

        if (val.isEmpty()) {
            email.setError("Field can not be empty");
            return false;
        } else if (!val.matches(checkEmail)) {
            email.setError("Invalid Email!");
            return false;
        } else {
            email.setError(null);
            email.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePassword() {
        String val = password.getEditText().getText().toString().trim();
        String checkPassword = "^" +
                "(?=.*[a-zA-Z0-9])" +      //any letter or number
                "(?=\\S+$)" +           //no white spaces
                ".{4,25}" +               //at least 4 characters
                "$";

        if (val.isEmpty()) {
            password.setError("Field can not be empty");
            return false;
        } else if (!val.matches(checkPassword)) {
            password.setError("Password should contain 4 characters!");
            return false;
        } else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }

    public void goBackToHome(View view) {
        startActivity(new Intent(getApplicationContext(), WelcomeLoginSignupActivity.class));
        finish();
    }

    public void goToLogin(View view) {
        startActivity(new Intent(getApplicationContext(), LogInActivity.class));
        finish();
    }
}