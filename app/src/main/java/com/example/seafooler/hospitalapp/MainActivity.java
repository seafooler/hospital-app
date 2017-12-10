package com.example.seafooler.hospitalapp;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.seafooler.hospitalapp.common.SDCardUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String relativeMovieDirPath = "movie";
    private String relativePicDirPath = "pic";

    private String movieDirPath;
    private String picDirPath;

    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏状态栏
        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //隐藏标题栏
//        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);

        // 添加Activity到堆栈

        Log.i(TAG, "Create the Mainactivity");

        RelativeLayout game = findViewById(R.id.game);
        RelativeLayout video = findViewById(R.id.video);
        RelativeLayout comp = findViewById(R.id.comp);
        RelativeLayout famous = findViewById(R.id.famous);
        RelativeLayout mall = findViewById(R.id.mall);
        RelativeLayout check = findViewById(R.id.check);

        movieDirPath = SDCardUtil.getSdPath() + relativeMovieDirPath;
        picDirPath = SDCardUtil.getSdPath() + relativePicDirPath;

        game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gameIntent = new Intent(MainActivity.this,
                        InstallGameActivity.class);
                MainActivity.this.startActivity(gameIntent);
//                MainActivity.this.finish();
            }
        });

        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent commomIntent = new Intent(MainActivity.this,
                        CommonActivity.class);
                commomIntent.putExtra("mediaDirPath", movieDirPath);
                commomIntent.putExtra("mediaType", "mov");
                MainActivity.this.startActivity(commomIntent);
//                MainActivity.this.finish();
            }
        });

        famous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent commomIntent = new Intent(MainActivity.this,
                        CommonActivity.class);
                commomIntent.putExtra("mediaDirPath", picDirPath);
                commomIntent.putExtra("mediaType", "pic");
                MainActivity.this.startActivity(commomIntent);
//                MainActivity.this.finish();
            }
        });

        mall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mallIntent = new Intent(MainActivity.this,
                        MallActivity.class);
                MainActivity.this.startActivity(mallIntent);
//                MainActivity.this.finish();
            }
        });

        comp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent compIntent = new Intent(MainActivity.this,
                        CompActivity.class);
                MainActivity.this.startActivity(compIntent);
//                MainActivity.this.finish();
            }
        });

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent checkIntent = new Intent(MainActivity.this,
                        CheckActivity.class);
                MainActivity.this.startActivity(checkIntent);
//                MainActivity.this.finish();
            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK )
        {
            this.finish();
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