package com.example.seafooler.hospitalapp;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.seafooler.hospitalapp.common.IsActivityInStack;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.utils.StringUtils;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

public class VideoActivity extends AppCompatActivity {

   /* private String movieDirPath;
    private VideoView videoView;
    private String TAG = "VideoActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //隐藏状态栏
        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        movieDirPath = getIntent().getStringExtra("videoDirPath");
        setContentView(R.layout.activity_video);

        //初始化控件
        videoView = findViewById(R.id.public_videoView);
        videoView.setVideoPath(movieDirPath);

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


        *//**
         * 视频或者音频到结尾时出发的方法
         *//*
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

    }*/


    private String movieDirPath;
    VideoView videoView;
    private RelativeLayout mRelatviLayout;
    LinearLayout linearLayout;
    private View mVolumeBrightnessLayout;
    private ImageView mOperationBg;
    private ImageView mOperationPercent;
    private AudioManager mAudioManager;

    private int mMaxVolume;

    private int mVolume = -1;

    private float mBrightness = -1f;
    private GestureDetector mGestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题栏
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //去掉状态栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        movieDirPath = getIntent().getStringExtra("videoDirPath");

        setContentView(R.layout.activity_video);
        //getSupportActionBar().hide();
        Vitamio.initialize(this);
        if (Vitamio.initialize(this)) {
            String pathway = getIntent().getStringExtra("videoDirPath");
            videoView = (VideoView) findViewById(R.id.video);
            mRelatviLayout = (RelativeLayout) findViewById(R.id.rela);
            //linearLayout = (LinearLayout) findViewById(R.id.line);
            //mVolumeBrightnessLayout = findViewById(R.id.operation_volume_brightness);
            //mOperationBg = (ImageView) findViewById(R.id.operation_bg);
            mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            mMaxVolume = mAudioManager
                    .getStreamMaxVolume(AudioManager.STREAM_MUSIC);
            MediaController controller = new MediaController(this, true, mRelatviLayout);
            videoView.setVideoPath(pathway);
            videoView.requestFocus();
            videoView.setMediaController(controller);
            //mGestureDetector = new GestureDetector(this, new MyGestureListener());
            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    //设置全屏模式，videoView要用一个单独的布局包裹才有效果
                    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                            RelativeLayout.LayoutParams.MATCH_PARENT,
                            RelativeLayout.LayoutParams.MATCH_PARENT);
                    //消除边框
                    layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                    layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                    layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                    layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                    videoView.setLayoutParams(layoutParams);
                    videoView.start();//视频准备后自动播放
                }
            });

        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK ) //Back to CommonActivity
        {
            if (! IsActivityInStack.isActivityExsit(this, CommonActivity.class)) {
                Intent commonIntent = new Intent(VideoActivity.this,
                        CommonActivity.class);
                commonIntent.putExtra("mediaDirPath", movieDirPath);
                commonIntent.putExtra("mediaType", "mov");
                VideoActivity.this.startActivity(commonIntent);
            }
            VideoActivity.this.finish();

        }
        return true;
    }

    @Override
    protected void onResume() {
        /**
         * 设定为横屏
         */
        if(getRequestedOrientation()!= ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        super.onResume();
    }

}
