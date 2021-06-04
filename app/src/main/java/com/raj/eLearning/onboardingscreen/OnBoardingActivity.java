package com.raj.eLearning.onboardingscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.raj.eLearning.MainActivity;
import com.raj.eLearning.R;
import com.ramotion.paperonboarding.PaperOnboardingFragment;
import com.ramotion.paperonboarding.PaperOnboardingPage;

import java.util.ArrayList;

public class OnBoardingActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);
        fragmentManager = getSupportFragmentManager();

        PaperOnboardingFragment onBoardingFragment = PaperOnboardingFragment.newInstance(getDataForOnBoarding());
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.onboardingFragmentContainer, onBoardingFragment);
        fragmentTransaction.commit();
        onBoardingFragment.setOnRightOutListener(() -> {

            startActivity(new Intent(OnBoardingActivity.this, MainActivity.class));
            finish();
        }
        );

    }

    private ArrayList<PaperOnboardingPage> getDataForOnBoarding() {
        ArrayList<PaperOnboardingPage> elements = new ArrayList<>();
        PaperOnboardingPage slider1 = new PaperOnboardingPage("Eductaion",
                "We provide you a better education from online.",
                Color.parseColor("#FF9800"), R.drawable.slider1, R.drawable.dots);
        PaperOnboardingPage slider2 = new PaperOnboardingPage("Learn",
                "You can learn a unique and good knowledge from here.",
                Color.parseColor("#03DAC5"), R.drawable.slider2, R.drawable.dots);
        PaperOnboardingPage slider3 = new PaperOnboardingPage("Ideas",
                "Think a better idea and try to implement.",
                Color.parseColor("#FF5252"), R.drawable.slider3, R.drawable.dots);
        PaperOnboardingPage slider4 = new PaperOnboardingPage("Success",
                "Finally you get a success in a life.",
                Color.parseColor("#FF4081"), R.drawable.slider, R.drawable.dots);
        elements.add(slider1);
        elements.add(slider2);
        elements.add(slider3);
        elements.add(slider4);
        return elements;
    }
}