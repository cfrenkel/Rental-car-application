package com.example.this_user.ourproject5778_9075_4711_02.controller.activities;

import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.this_user.ourproject5778_9075_4711_02.R;

import java.net.URI;

public class VideoActivity extends AppCompatActivity {

     VideoView videoView;
     MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);


        videoView = (VideoView) findViewById(R.id.video);
        mediaController = new MediaController(this);
        videoPlay();
    }

    /**
     * show the media
     */
    void videoPlay ()
    {
        String path = "android.resource://"+getPackageName()+"/"+R.raw.rentalcar;
        Uri uri = Uri.parse(path);
        videoView.setVideoURI(uri);
        videoView.setMediaController(mediaController);

        mediaController.setAnchorView(videoView);
        videoView.start();
    }
}
