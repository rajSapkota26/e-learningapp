package com.raj.eLearning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.ShareActionProvider;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.raj.eLearning.database.ParaMeters;
import com.raj.eLearning.database.SqliteService;
import com.raj.eLearning.imageslider.ImageSliderAdapter;
import com.raj.eLearning.imageslider.SliderItem;
import com.raj.eLearning.iswifi.CheckingConnection;
import com.raj.eLearning.loginplussignin.WelcomeLoginSignupActivity;
import com.raj.eLearning.loginplussignin.login.LogInActivity;
import com.raj.eLearning.model.User;
import com.raj.eLearning.owner.AboutMeActivity;
import com.raj.eLearning.user.UserDashBoard;
import com.raj.eLearning.user.activities.ArticlesActivity;
import com.raj.eLearning.user.activities.EbookActivity;
import com.raj.eLearning.user.activities.InterviewActivity;
import com.raj.eLearning.user.quiz.quizactivity.QuizActivity;
import com.raj.eLearning.user.activities.RateUsActivity;
import com.raj.eLearning.user.activities.ResourcesActivity;
import com.raj.eLearning.user.activities.UserProfileActivity;
import com.raj.eLearning.user.activities.VideoLActivity;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SliderView sliderView;
    private ImageSliderAdapter adapter;
    private List<SliderItem> sliderItem;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    //variables
    private ImageView menuIcon;
    private static final float END_SCALE = 0.7f;
    private LinearLayout contentView;
    private ShareActionProvider shareActionProvider;
    private SharedPreferences sharedPreferences;
    private SqliteService service;

    //loginorlogout state
    private boolean isLoggedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (!new CheckingConnection(this).getConnectionStatus(this)) {

        } else {
            init();
        }


    }


    public void gotoWelcomeLogSign(View view) {
        checkUser(UserDashBoard.class);
//         startActivity(new Intent(MainActivity.this, UserDashBoard.class));
    }


    public void init() {

        //hide action bar
        getSupportActionBar().hide();
        //window full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //image slider
        sliderView = findViewById(R.id.imageSlider);
        sliderItem = getAllItem();

        //slider image adapter
        adapter = (new ImageSliderAdapter(getApplicationContext(), sliderItem));
        sliderView.setIndicatorAnimation(IndicatorAnimationType.DROP); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.startAutoCycle();
        sliderView.setSliderAdapter(adapter);
        //drawer layout
        drawerLayout = findViewById(R.id.main_drawer_layout);
        navigationView = findViewById(R.id.main_navigation_view);
        menuIcon = findViewById(R.id.menuIcon);

        contentView = findViewById(R.id.content_view);
        //navigation virew setting
        naviagtionDrawer();
        //fetch user
        sharedPreferences = getSharedPreferences("e-learning", MODE_PRIVATE);
        //database
        service = new SqliteService(this);


    }

    //navigation view drawer methods
    private void naviagtionDrawer() {
        navigationView.bringToFront();
//        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);
//        navigationView.setCheckedItem(R.id.nav_articles);
        menuIcon.setOnClickListener(view -> {
            if (drawerLayout.isDrawerVisible(GravityCompat.START))
                drawerLayout.closeDrawer(GravityCompat.START);
            else drawerLayout.openDrawer(GravityCompat.START);
            isUserExist();
        });
        animateNavigationDrawer();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.nav_articles:
                        startActivity(new Intent(MainActivity.this, ArticlesActivity.class));
                        break;
                    case R.id.nav_interview:
                        startActivity(new Intent(MainActivity.this, InterviewActivity.class));
                        break;
                    case R.id.nav_resources:
                        startActivity(new Intent(MainActivity.this, ResourcesActivity.class));
                        break;
                    case R.id.nav_ebook:
                        checkUser(EbookActivity.class);
                        break;
                    case R.id.nav_video:
                        checkUser(VideoLActivity.class);
                        break;
                    case R.id.nav_quiz:
                        checkUser(QuizActivity.class);
                        break;
                    case R.id.nav_logIn:
                        checkUser(LogInActivity.class);
                        break;
                    case R.id.nav_Profile:
                        checkUser(UserProfileActivity.class);
                        break;
                    case R.id.nav_logOut:
                        //remove user from shared preference then move to main activity
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove("currentUser");
                        editor.apply();
                        Toast.makeText(MainActivity.this, "Logged Out..", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, MainActivity.class));
                        break;
                    case R.id.nav_share_share:

                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.putExtra(Intent.EXTRA_TEXT, "Share this app");
                        intent.setType("text/plain");
                        startActivity(Intent.createChooser(intent, "Send To"));

                        break;
                    case R.id.nav_share_rateus:
                        startActivity(new Intent(MainActivity.this, RateUsActivity.class));
                        break;
                    case R.id.nav_about:
                        startActivity(new Intent(MainActivity.this, AboutMeActivity.class));
                        break;
                }
                return false;
            }
        });
    }

    public void checkUser(Class activity) {
        if (!isUserExist()) {
            Toast.makeText(this, "Please login first", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, WelcomeLoginSignupActivity.class));
            finish();
        } else {
            startActivity(new Intent(MainActivity.this, activity));
            finish();
        }
    }

    public boolean isUserExist() {
        boolean f = false;
        User user = service.getUserByUserName(sharedPreferences.getString("currentUser", null));
        if (user == null) {
            navigationView.getMenu().findItem(R.id.nav_logIn).setVisible(true);
            navigationView.getMenu().findItem(R.id.nav_logOut).setVisible(false);
            return f;
        }

        if (user != null) {
            navigationView.getMenu().findItem(R.id.nav_logOut).setVisible(true);
            navigationView.getMenu().findItem(R.id.nav_logIn).setVisible(false);
            f = true;
        }
        return f;
    }


    private void animateNavigationDrawer() {
        //Add any color or remove it to use the default one!
        //To make it transparent use Color.Transparent in side setScrimColor();Color.TRANSPARENT
        drawerLayout.setScrimColor(getResources().getColor(R.color.slider));
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                // Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);

                // Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);
            }
        });
    }

    private List<SliderItem> getAllItem() {
        List<SliderItem> l = new ArrayList<>();

        l.add(new SliderItem(ParaMeters.S_FIRST, "you get Video Lecture "));
        l.add(new SliderItem(ParaMeters.S_SECOND, "Important videos classes"));
        l.add(new SliderItem(ParaMeters.S_THIRD, "Some Software Imformations"));
        l.add(new SliderItem(ParaMeters.S_FOUR, "Think better improve yourself"));
        l.add(new SliderItem(ParaMeters.S_FIVE, "Doing by Learning Approach"));
        l.add(new SliderItem(ParaMeters.S_SIX, "Quiz helps you to improve your skill"));
        l.add(new SliderItem(ParaMeters.S_SEVEN, "We provides multiple EBooks"));
        l.add(new SliderItem(ParaMeters.S_EIGHT, "Coding and Learning"));
        l.add(new SliderItem(ParaMeters.S_NINE, "Important Atricles Include"));
        l.add(new SliderItem(ParaMeters.S_TEN, "Multiple idea generate to improve you"));
        l.add(new SliderItem(ParaMeters.S_ELEVEN, "Learn your better "));
        l.add(new SliderItem(ParaMeters.S_TWELVE, "you can play a quiz"));

        return l;
    }

}
