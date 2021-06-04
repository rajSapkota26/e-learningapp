package com.raj.eLearning.user.quiz.quizactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.WindowManager;

import com.raj.eLearning.R;
import com.raj.eLearning.iswifi.CheckingConnection;
import com.raj.eLearning.user.quiz.fragments.HomeFragment;
import com.raj.eLearning.user.quiz.fragments.LeaderBoardFragment;
import com.raj.eLearning.user.quiz.fragments.ProfileFragment;
import com.raj.eLearning.user.quiz.fragments.WalletFragment;

import me.ibrahimsn.lib.OnItemSelectedListener;
import me.ibrahimsn.lib.SmoothBottomBar;

public class QuizActivity extends AppCompatActivity {
    private SmoothBottomBar smoothBottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        if (!new CheckingConnection(this).getConnectionStatus(this)) {

        } else {
            init();
        }


    }

    public void init() {
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        smoothBottomBar = findViewById(R.id.bottomBar);
        loadFragments(new HomeFragment());
        smoothBottomBar.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public boolean onItemSelect(int i) {
                switch (i) {
                    case 0:
                        loadFragments(new HomeFragment());
                        break;
                    case 1:
                        loadFragments(new LeaderBoardFragment());

                        break;
                    case 2:
                        loadFragments(new WalletFragment());

                        break;
                    case 3:
                        loadFragments(new ProfileFragment());
                        break;
                }
                return false;
            }
        });
    }

    public void loadFragments(Fragment f) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content, f);
        transaction.commit();
    }

}