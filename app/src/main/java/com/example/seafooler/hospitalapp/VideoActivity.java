package com.example.seafooler.hospitalapp;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity {

    private String videoDirPath;
    private VideoView videoView;
    private String TAG = "VideoActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        videoDirPath = getIntent().getStringExtra("videoDirPath");
        setContentView(R.layout.activity_video);

        //初始化控件
        videoView = findViewById(R.id.public_videoView);
        videoView.setVideoPath(videoDirPath);

        //首先拼出在资源文件夹下的视频文件路径string字符串

        //这里用相对布局包裹videoview实现视频全局播放
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT
        );
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

        videoView.setLayoutParams(layoutParams);
        videoView.setMediaController(new MediaController(this));


        /**
         * 视频或者音频到结尾时出发的方法
         */
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                Log.i(TAG,"通知:完成");
            }
        });
        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
                Log.i(TAG,"通知:播放中出现错误");
                return false;
            }
        });
        videoView.start();

    }
}
