package com.raj.eLearning.loginplussignin.forgotpassword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.raj.eLearning.R;
import com.raj.eLearning.iswifi.CheckingConnection;
import com.raj.eLearning.loginplussignin.login.LogInActivity;

public class ThirdForgotPasswordActivity extends AppCompatActivity {
    private TextView select_forgot_otp;
    private PinView pin_view;
    private String otp,getOtp;
    private long userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_forgot_password);
        if (! new CheckingConnection(this).getConnectionStatus(this)){

        }else {
            init();
        }
    }

    public void init() {
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        select_forgot_otp = findViewById(R.id.select_forgot_otp);
        select_forgot_otp.setText("Enter OTP code send on : " + getIntent().getStringExtra("selectFeature"));
        getOtp=getIntent().getStringExtra("otpcode");
        Log.d("code", getOtp);
        pin_view = findViewById(R.id.pin_view);
        userId=getIntent().getIntExtra("userId",0);

    }

    public void goToHomeFromOTP(View view) {
        startActivity(new Intent(getApplicationContext(), LogInActivity.class));
        finish();
    }

    public void fourthScreenFromOTP(View view) {
        otp=pin_view.getText().toString();
        Log.d("otpcode", otp);
        //check both otp are correct or not
      if (!otp.equals(getOtp)) {
          Toast.makeText(this, "Verification code doesn't match", Toast.LENGTH_SHORT).show();
          Log.d("code", getOtp);
          Log.d("code", otp);
          return;
      }
      Intent intent=new Intent(getApplicationContext(), FourthForgotPasswordActivity.class);
      intent.putExtra("userId",userId);
        Log.d("userDetails", String.valueOf(userId));
        startActivity(intent);
        finish();
    }


}