package com.raj.eLearning.user.quiz.quizactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.raj.eLearning.R;
import com.raj.eLearning.database.SqliteService;
import com.raj.eLearning.database.UpdateWallet;
import com.raj.eLearning.user.quiz.SpinWheel.LuckyWheelView;
import com.raj.eLearning.user.quiz.SpinWheel.model.LuckyItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SpinnerActivity extends AppCompatActivity {

    private LuckyWheelView wheelView;
    private ImageView spinBtn;
    private SharedPreferences sharedPreferences;
    private SqliteService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);
      init();

    }
public void init(){
    //hide action bar
    getSupportActionBar().hide();
    //window full screen
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    wheelView = findViewById(R.id.wheelview);
    spinBtn = findViewById(R.id.spinBtn);
    wheelView.setData(getdataItem());
    wheelView.setRound(5);
    spinBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Random r = new Random();
            int randomNumber = r.nextInt(8);
            wheelView.startLuckyWheelWithTargetIndex(randomNumber);
        }
    });

    //fetch user
    sharedPreferences = getSharedPreferences("e-learning", MODE_PRIVATE);
    //database
    service=new SqliteService(this);
    wheelView.setLuckyRoundItemSelectedListener(new LuckyWheelView.LuckyRoundItemSelectedListener() {
        @Override
        public void LuckyRoundItemSelected(int index) {
            updateCash(index);
        }
    });
}

    public List<LuckyItem> getdataItem() {
        List<LuckyItem> item = new ArrayList<>();
        LuckyItem luckyItem1 = new LuckyItem();

        luckyItem1.topText = "5";
        luckyItem1.secondaryText = "COINS";
        luckyItem1.textColor = Color.parseColor("#212121");
        luckyItem1.color = Color.parseColor("#eceff1");
        item.add(luckyItem1);

        LuckyItem luckyItem2 = new LuckyItem();
        luckyItem2.topText = "10";
        luckyItem2.secondaryText = "COINS";
        luckyItem2.color = Color.parseColor("#00cf00");
        luckyItem2.textColor = Color.parseColor("#ffffff");
        item.add(luckyItem2);

        LuckyItem luckyItem3 = new LuckyItem();
        luckyItem3.topText = "15";
        luckyItem3.secondaryText = "COINS";
        luckyItem3.textColor = Color.parseColor("#212121");
        luckyItem3.color = Color.parseColor("#eceff1");
        item.add(luckyItem3);

        LuckyItem luckyItem4 = new LuckyItem();
        luckyItem4.topText = "20";
        luckyItem4.secondaryText = "COINS";
        luckyItem4.color = Color.parseColor("#7f00d9");
        luckyItem4.textColor = Color.parseColor("#ffffff");
        item.add(luckyItem4);

        LuckyItem luckyItem5 = new LuckyItem();
        luckyItem5.topText = "25";
        luckyItem5.secondaryText = "COINS";
        luckyItem5.textColor = Color.parseColor("#212121");
        luckyItem5.color = Color.parseColor("#eceff1");
        item.add(luckyItem5);

        LuckyItem luckyItem6 = new LuckyItem();
        luckyItem6.topText = "30";
        luckyItem6.secondaryText = "COINS";
        luckyItem6.color = Color.parseColor("#dc0000");
        luckyItem6.textColor = Color.parseColor("#ffffff");
        item.add(luckyItem6);

        LuckyItem luckyItem7 = new LuckyItem();
        luckyItem7.topText = "35";
        luckyItem7.secondaryText = "COINS";
        luckyItem7.textColor = Color.parseColor("#212121");
        luckyItem7.color = Color.parseColor("#eceff1");
        item.add(luckyItem7);

        LuckyItem luckyItem8 = new LuckyItem();
        luckyItem8.topText = "0";
        luckyItem8.secondaryText = "COINS";
        luckyItem8.color = Color.parseColor("#008bff");
        luckyItem8.textColor = Color.parseColor("#ffffff");
        item.add(luckyItem8);
        return item;
    }

    private void updateCash(int index) {

        int cash = 0;
        switch (index) {
            case 0:
                cash = 5;
                break;
            case 1:
                cash = 10;
                break;
            case 2:
                cash = 15;
                break;
            case 3:
                cash = 20;
                break;
            case 4:
                cash = 25;
                break;
            case 5:
                cash = 30;
                break;
            case 6:
                cash = 35;
                break;
            case 7:
                cash = 0;
                break;

        }

        //update coin here
        new  UpdateWallet(SpinnerActivity.this).updateCoin(cash);

        Toast.makeText(this, cash+" Cash is updated to your account", Toast.LENGTH_SHORT).show();

        //redirect
        startActivity(new Intent(SpinnerActivity.this,QuizActivity.class));
        finish();
    }
}
