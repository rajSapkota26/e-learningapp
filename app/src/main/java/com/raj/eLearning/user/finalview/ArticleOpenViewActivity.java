package com.raj.eLearning.user.finalview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.raj.eLearning.R;
import com.raj.eLearning.iswifi.CheckingConnection;
import com.raj.eLearning.iswifi.LoadingContent;

public class ArticleOpenViewActivity extends AppCompatActivity {
    private WebView webView;
    private LoadingContent progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_open_view);

        if (! new CheckingConnection(this).getConnectionStatus(this)){

        }else {
            init();
        }

    }

    void init() {
        progressDialog= new LoadingContent(ArticleOpenViewActivity.this);
        progressDialog.showDialogue();

        getSupportActionBar().hide();
        webView = findViewById(R.id.my_web);
        loadNews();
    }

    public boolean loadNews() {

        webView.setWebViewClient(new WebViewClient(){


            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
               progressDialog.hideDialogue();
            }
        });

        webView.loadUrl(getIntent().getStringExtra("file"));

        return true;
    }

}