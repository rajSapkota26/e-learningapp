package com.raj.eLearning.database;

import android.content.Context;
import android.content.SharedPreferences;

import com.raj.eLearning.model.User;
import com.raj.eLearning.model.Wallet;

public class UpdateWallet {
    private Context context;
    private SharedPreferences sharedPreferences;
    private SqliteService service;
    private Wallet wallet;
    int oldcoin;

    public UpdateWallet(Context context) {
        this.context = context;
    }

    public void updateCoin(int coin) {
        //fetch user
        sharedPreferences = context.getSharedPreferences("e-learning", context.MODE_PRIVATE);
        //database
        service = new SqliteService(context);
        //fetch user
        User user = service.getUserByUserName(sharedPreferences.getString("currentUser", null));

        wallet = service.getCoin(user.getId());


        if(wallet == null) {
            //set wallet
            wallet = new Wallet();
            wallet.setCoin(coin);
            wallet.setUserId(String.valueOf(user.getId()));
            service.insertWallet(wallet);
        } else {
            //set wallet
            oldcoin=wallet.getCoin();
            wallet = new Wallet();
            wallet.setCoin(oldcoin+coin);
            wallet.setUserId(String.valueOf(user.getId()));
            //update
            service.updateWallet(wallet);
        }

    }


}
