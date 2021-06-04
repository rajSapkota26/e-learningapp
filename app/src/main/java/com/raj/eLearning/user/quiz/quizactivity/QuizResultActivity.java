package com.raj.eLearning.user.quiz.quizactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.raj.eLearning.R;
import com.raj.eLearning.database.SqliteService;
import com.raj.eLearning.database.UpdateWallet;
import com.raj.eLearning.model.ScoreBoard;
import com.raj.eLearning.model.User;
import com.raj.eLearning.model.Wallet;

public class QuizResultActivity extends AppCompatActivity {
private TextView score,coin;
    private SharedPreferences sharedPreferences;
    private SqliteService service;
    int coinget;
    int correctAnswer;
    Wallet wallet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_result);
        score=findViewById(R.id.score);
        coin=findViewById(R.id.earnedCoins);
         correctAnswer=getIntent().getIntExtra("correct",0);
        int totalQuestion=getIntent().getIntExtra("total",0);

        score.setText(correctAnswer+"/10");
        coinget=correctAnswer*100;

        coin.setText(String.valueOf(coinget));

        //fetch user
        sharedPreferences = getSharedPreferences("e-learning", MODE_PRIVATE);
        //database
        service=new SqliteService(this);

        //add coin to wallet
        updateWallet(coinget);

    }

    private void updateWallet(int coin) {
      //fetch user
        User user= service.getUserByUserName(sharedPreferences.getString("currentUser", null));
        new UpdateWallet(QuizResultActivity.this).updateCoin(coin);


        //set scoreboard
        ScoreBoard scoreBoard=new ScoreBoard();
        scoreBoard.setUserId(String.valueOf(user.getId()));
        scoreBoard.setScore((correctAnswer));

        //update coin and add score
         service.insertScore(scoreBoard);


    }

    public void reStartQuiz(View view) {
        startActivity(new Intent(QuizResultActivity.this, QuizActivity.class));
        finish();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(QuizResultActivity.this, QuizActivity.class));
        finish();
    }
}