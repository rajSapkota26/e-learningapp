package com.raj.eLearning.user.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.raj.eLearning.R;
import com.raj.eLearning.database.SqliteService;
import com.raj.eLearning.iswifi.CheckingConnection;
import com.raj.eLearning.model.User;

public class UserProfileActivity extends AppCompatActivity {
    private TextView name,userName,email,contact,dob;
    private SharedPreferences sharedPreferences;
    private SqliteService service;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        if (! new CheckingConnection(this).getConnectionStatus(this)){

        }else {
            init();
        }
    }
    public void init(){
        name=findViewById(R.id.pfu_name);
        userName=findViewById(R.id.pfu_username);
        email=findViewById(R.id.pfu_email);
        contact=findViewById(R.id.pfu_phone);
        dob=findViewById(R.id.pdu_dob);

        //fetch username
        sharedPreferences =getSharedPreferences("e-learning",MODE_PRIVATE);
        String username1 = sharedPreferences.getString("currentUser", null);

        service = new SqliteService(this);
        //get current use
        user = service.getUserByUserName(username1);
        name.setText("full name \n"+user.getFullname());
        userName.setText("user name \n"+user.getUsername());
        email.setText("Email \n"+user.getEmail());
        contact.setText("Contact\n"+user.getPhone());
        dob.setText("Date of birth \n"+user.getDateOfBirth());
    }
}