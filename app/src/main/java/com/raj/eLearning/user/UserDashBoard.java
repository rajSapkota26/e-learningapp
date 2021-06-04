package com.raj.eLearning.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.raj.eLearning.MainActivity;
import com.raj.eLearning.R;
import com.raj.eLearning.user.activities.ArticlesActivity;
import com.raj.eLearning.user.activities.EbookActivity;
import com.raj.eLearning.user.activities.InterviewActivity;
import com.raj.eLearning.user.activities.ResourcesActivity;
import com.raj.eLearning.user.activities.VideoLActivity;
import com.raj.eLearning.user.quiz.quizactivity.QuizActivity;

public class UserDashBoard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dash_board);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public void onClickQuiz(View view) {
        startActivity(new Intent(getApplicationContext(), QuizActivity.class));
    }

    public void onClickVideo(View view) {
        startActivity(new Intent(getApplicationContext(), VideoLActivity.class));
    }

    public void onClickEbooks(View view) {
        startActivity(new Intent(getApplicationContext(), EbookActivity.class));
    }

    public void onClickResources(View view) {
        startActivity(new Intent(getApplicationContext(), ResourcesActivity.class));
    }

    public void onClickInterview(View view) {
        startActivity(new Intent(getApplicationContext(), InterviewActivity.class));
    }

    public void onClickArticle(View view) {
        startActivity(new Intent(getApplicationContext(), ArticlesActivity.class));
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}