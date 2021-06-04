package com.raj.eLearning.loginplussignin.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.hbb20.CountryCodePicker;
import com.raj.eLearning.R;
import com.raj.eLearning.database.SqliteService;
import com.raj.eLearning.iswifi.CheckingConnection;
import com.raj.eLearning.loginplussignin.WelcomeLoginSignupActivity;
import com.raj.eLearning.loginplussignin.login.LogInActivity;
import com.raj.eLearning.model.User;

public class ThirdSignUpActivity extends AppCompatActivity {
    private String fullname, username, email, password, dateOfBirth, gender, phone;
    private CountryCodePicker countryCodePicker;
    private TextInputLayout phoneNumber;
    private User user;
    SqliteService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_sign_up);
        if (!new CheckingConnection(this).getConnectionStatus(this)) {

        } else {
            init();
        }
    }


    public void Signup(View view) {
        phone ="+"+ countryCodePicker.getSelectedCountryCode() + phoneNumber.getEditText().getText().toString();
       if ( registerUser()) {
           Intent intent = new Intent(getApplicationContext(), LogInActivity.class);
           intent.putExtra("fromSignIn", 560);
           intent.putExtra("userName", username);
           intent.putExtra("password", password);
           startActivity(intent);
           finish();
       }
       else {
           Toast.makeText(this, "Something went wrong..", Toast.LENGTH_SHORT).show();
       }
    }

    public void init() {
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        fullname = getIntent().getStringExtra("fullName");
        username = getIntent().getStringExtra("userName");
        email = getIntent().getStringExtra("email");
        password = getIntent().getStringExtra("password");
        gender = getIntent().getStringExtra("gender");
        dateOfBirth = getIntent().getStringExtra("dateOfBirth");
        phoneNumber = findViewById(R.id.signup_phone);
        countryCodePicker = findViewById(R.id.countryCode);
        service = new SqliteService(this);
    }

    public boolean registerUser() {
        boolean f = false;

        user = new User(fullname, username, email, password, dateOfBirth, gender, phone);
        Toast.makeText(this, user.toString(), Toast.LENGTH_SHORT).show();
        //save register data to database
        User user1 = service.getUserByUserName(username);
        User user2 = service.getUserByEmail(email);
        if (user1 == null && user2 == null) {
            service.insertUser(user);
            f = true;
        } else {
            Toast.makeText(this, "This user already exist..", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), FirstSignUpActivity.class));
        }
        return f;

    }

    public void goBackToHome(View view) {
        startActivity(new Intent(getApplicationContext(), WelcomeLoginSignupActivity.class));
        finish();
    }
}