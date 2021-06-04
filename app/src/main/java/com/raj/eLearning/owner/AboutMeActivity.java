package com.raj.eLearning.owner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.raj.eLearning.MainActivity;
import com.raj.eLearning.R;
import com.raj.eLearning.iswifi.LoadingContent;

public class AboutMeActivity extends AppCompatActivity {
    private LoadingContent progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);

        //hide action bar
        getSupportActionBar().hide();
        //window full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        WebView myWebView = findViewById(R.id.webview);
        progressDialog= new LoadingContent(this);
        progressDialog.showDialogue();
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new WebViewClient(){


            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressDialog.hideDialogue();
            }
        });

        myWebView.loadUrl("https://rajsapkota26.github.io/technoabinash.com/");
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}