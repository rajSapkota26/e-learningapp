package com.raj.eLearning.user.finalview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.WindowManager;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.raj.eLearning.R;
import com.raj.eLearning.iswifi.CheckingConnection;
import com.raj.eLearning.iswifi.LoadingContent;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class BookOpenViewActivity extends AppCompatActivity {
    private PDFView study1;
    private String pdfLink;
    private LoadingContent progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_open_view);
        if (! new CheckingConnection(this).getConnectionStatus(this)){

        }else {
            init();
        }
    }

    void init() {
        progressDialog= new LoadingContent(BookOpenViewActivity.this);

        progressDialog.showDialogue();
        Intent intent = getIntent();
        pdfLink = intent.getStringExtra("pdfLink");
        study1 = findViewById(R.id.pdfView);
        //hide action bar
        getSupportActionBar().hide();
        //window full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);



            new pdfDownload().execute(pdfLink);



    }

    private class pdfDownload extends AsyncTask<String, Void, InputStream> {

        @Override
        protected InputStream doInBackground(String... strings) {
            InputStream inputStream=null;
            try {
                URL url=new URL(strings[0]);
                HttpURLConnection connection= (HttpURLConnection) url.openConnection();
                if (connection.getResponseCode()==200){
                    inputStream=new BufferedInputStream(connection.getInputStream());
                }
            }catch (Exception e){
                e.printStackTrace();
            }

            return  inputStream;
        }
        @Override
        protected void onPreExecute() {


        }
        @Override
        protected void onPostExecute(InputStream inputStream) {

             study1.fromStream(inputStream).onLoad(new OnLoadCompleteListener() {
                 @Override
                 public void loadComplete(int nbPages) {
                     progressDialog.hideDialogue();
                 }
             }).load();

        }
    }

}