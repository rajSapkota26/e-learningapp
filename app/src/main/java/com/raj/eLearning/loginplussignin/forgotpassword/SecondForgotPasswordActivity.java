package com.raj.eLearning.loginplussignin.forgotpassword;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.raj.eLearning.R;
import com.raj.eLearning.iswifi.CheckingConnection;
import com.raj.eLearning.loginplussignin.login.LogInActivity;

public class SecondForgotPasswordActivity extends AppCompatActivity {
    private TextView emailTv, phoneTv;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_forgot_password);
        if (! new CheckingConnection(this).getConnectionStatus(this)){

        }else {
            init();
        }
    }

    public void init() {
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        emailTv = findViewById(R.id.email_forgot);
        phoneTv = findViewById(R.id.phone_forgot);
        userId=getIntent().getIntExtra("userId",0);
        emailTv.setText(getIntent().getStringExtra("email"));
        phoneTv.setText(getIntent().getStringExtra("phone"));
    }

    public void callToThirdForgotPasswordSceen(View view) {

        nextStep(view,phoneTv.getText().toString());

    }

    public void thirdForgotPasswordSceen(View view) {
        nextStep(view,emailTv.getText().toString());
    }

    public void nextStep(View view,String s){
        String randomcode=String.valueOf( (100000)+(int)(Math.random()*100000)) ;
        Intent intent = new Intent(getApplicationContext(), ThirdForgotPasswordActivity.class);
        intent.putExtra("selectFeature",s);
        intent.putExtra("otpcode",randomcode);
        intent.putExtra("userId", userId);
        Log.d("userDetails", String.valueOf(userId));
        Log.d("code", randomcode);

        //send otp here
        inboxstyle_Notification(getApplicationContext(),"E-Learning App",randomcode);

        startActivity(intent);
        finish();
    }

    private void inboxstyle_Notification(Context context,String channelid,String yourcode) {

        NotificationCompat.Builder builder = new NotificationCompat.Builder( context ,channelid);

        builder.setSmallIcon(R.drawable.add_icon)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.add_icon))
                .setContentTitle("Your OTP code")
                .setStyle(new NotificationCompat.InboxStyle().addLine(yourcode).addLine("Submit this code")
                        .setBigContentTitle("Your OTP code").setSummaryText("Inbox"))
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL);
        Log.d("code", yourcode);
        Uri path = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        builder.setSound(path);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelid,"Develop By Song Ran", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
            builder.setChannelId("E-Learning App");
        }
        notificationManager.notify(1, builder.build());
    }



    public void goToLogInFromForgotPassword(View view) {
        startActivity(new Intent(getApplicationContext(), LogInActivity.class));
        finish();
    }
}