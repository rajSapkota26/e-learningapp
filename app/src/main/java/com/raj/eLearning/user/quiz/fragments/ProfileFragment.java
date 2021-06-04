package com.raj.eLearning.user.quiz.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.raj.eLearning.R;
import com.raj.eLearning.database.SqliteService;
import com.raj.eLearning.model.User;


public class ProfileFragment extends Fragment {
private TextView name,userName,email,contact,dob;
    private SharedPreferences sharedPreferences;
    private SqliteService service;
    private User user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.fragment_profile, container, false);
       name=view.findViewById(R.id.pf_name);
       userName=view.findViewById(R.id.pf_username);
       email=view.findViewById(R.id.pf_email);
       contact=view.findViewById(R.id.pf_phone);
       dob=view.findViewById(R.id.pd_dob);


        //fetch username
        sharedPreferences = getContext().getSharedPreferences("e-learning", getContext().MODE_PRIVATE);
        String username1 = sharedPreferences.getString("currentUser", null);

        service = new SqliteService(getContext());
        //get current use
        user = service.getUserByUserName(username1);
        name.setText("full name \n"+user.getFullname());
        userName.setText("user name \n"+user.getUsername());
        email.setText("Email \n"+user.getEmail());
        contact.setText("Contact\n"+user.getPhone());
        dob.setText("Date of birth \n"+user.getDateOfBirth());
        return view;
    }
}