package com.raj.eLearning.user.finalview;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

import com.raj.eLearning.R;
import com.raj.eLearning.iswifi.CheckingConnection;
import com.raj.eLearning.iswifi.LoadingContent;

public class VideoOpenViewActivity extends AppCompatActivity {

    private String vdoLink;
    private VideoView video;
    private MediaController videoPlayer;
    private LoadingContent progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_open_view);

        if (! new CheckingConnection(this).getConnectionStatus(this)){

        }else {
            init();
        }

    }

    void init() {
        progressDialog= new LoadingContent(this);
        progressDialog.showDialogue();

        //hide action bar
        getSupportActionBar().hide();
        //window full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        video = findViewById(R.id.video_view);
        vdoLink = getIntent().getStringExtra("vdo");
        videoPlayer = new MediaController(VideoOpenViewActivity.this);


        initPlayer(vdoLink);

    }

    private void initPlayer(String link) {

        Uri uri = Uri.parse(link);
        video.setVideoURI(uri);
        videoPlayer.setMediaPlayer(video);
        video.setMediaController(videoPlayer);
        video.requestFocus();
        video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                progressDialog.hideDialogue();
                video.start();
            }
        });


    }


}