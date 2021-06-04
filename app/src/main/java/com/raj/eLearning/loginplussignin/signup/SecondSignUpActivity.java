package com.raj.eLearning.loginplussignin.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.raj.eLearning.R;
import com.raj.eLearning.iswifi.CheckingConnection;
import com.raj.eLearning.loginplussignin.WelcomeLoginSignupActivity;

import java.util.Calendar;

public class SecondSignUpActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    DatePicker datePicker;
    RadioButton selected;
    String dob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_sign_up);
        if (! new CheckingConnection(this).getConnectionStatus(this)){

        }else {
            init();
        }
    }

    public void thirdSigupScreen(View view) {
        if (!validateGender() || !validateAge() ) {
            return;
        }

         selected=findViewById(radioGroup.getCheckedRadioButtonId());
         dob=String.valueOf(datePicker.getYear()+ "-" +datePicker.getMonth()+ "-" +datePicker.getDayOfMonth());

        Intent intent=new Intent(getApplicationContext(), ThirdSignUpActivity.class);
        intent.putExtra("fullName",getIntent().getStringExtra("full"));
        intent.putExtra("userName",getIntent().getStringExtra("user"));
        intent.putExtra("email",getIntent().getStringExtra("mail"));
        intent.putExtra("password",getIntent().getStringExtra("pass"));
        intent.putExtra("gender", selected.getText().toString());
        intent.putExtra("dateOfBirth",dob);
        startActivity(intent);
    }

    private boolean validateGender() {
        if (radioGroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please Select Gender", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    private boolean validateAge() {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int userAge = datePicker.getYear();
        int isAgeValid = currentYear - userAge;

        if (isAgeValid < 14) {
            Toast.makeText(this, "You are not eligible to apply", Toast.LENGTH_SHORT).show();
            return false;
        } else
            return true;
    }
    public void init(){
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        radioGroup = findViewById(R.id.signup_gender);
        datePicker = findViewById(R.id.sign_dob);
    }





    public void goBackToHome(View view) {
        startActivity(new Intent(getApplicationContext(), WelcomeLoginSignupActivity.class));
        finish();
    }
}