package com.raj.eLearning.user.quiz.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.raj.eLearning.R;
import com.raj.eLearning.database.SqliteService;
import com.raj.eLearning.model.User;
import com.raj.eLearning.model.Wallet;


public class WalletFragment extends Fragment {
    private Button send;
    private SharedPreferences sharedPreferences;
    private SqliteService service;
    TextView currentCoins;
    User user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wallet, container, false);
        send = view.findViewById(R.id.sendRequest);
        currentCoins = view.findViewById(R.id.currentCoins);

        //fetch username
        sharedPreferences = getContext().getSharedPreferences("e-learning", getContext().MODE_PRIVATE);
        String username = sharedPreferences.getString("currentUser", null);

        service = new SqliteService(getContext());
      //get current use
        user = service.getUserByUserName(username);


     //check current wallet is empty or not
        Wallet wallet = service.getCoin(user.getId());
        if (wallet != null) {

            currentCoins.setText(""+wallet.getCoin());
        } else {
            currentCoins.setText("0");
        }


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Check Balance after 24 Hours", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}