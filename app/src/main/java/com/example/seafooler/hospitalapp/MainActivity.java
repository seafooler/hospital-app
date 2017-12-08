package com.example.seafooler.hospitalapp;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private String movieDirPath = "/storage/sdcard/movie";
    private String picDirPath = "/storage/sdcard/pic";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏状态栏
        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //隐藏标题栏
//      getSupportActionBar().hide();

        setContentView(R.layout.activity_main);

        RelativeLayout game = findViewById(R.id.game);
        RelativeLayout video = findViewById(R.id.video);
        RelativeLayout comp = findViewById(R.id.comp);
        RelativeLayout famous = findViewById(R.id.famous);
        RelativeLayout mall = findViewById(R.id.mall);
        RelativeLayout check = findViewById(R.id.check);

        game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gameIntent = new Intent(MainActivity.this,
                        InstallGameActivity.class);
                MainActivity.this.startActivity(gameIntent);
                MainActivity.this.finish();
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
                MainActivity.this.finish();
            }
        });

        comp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent commomIntent = new Intent(MainActivity.this,
                        CommonActivity.class);
                commomIntent.putExtra("mediaDirPath", picDirPath);
                commomIntent.putExtra("mediaType", "pic");
                MainActivity.this.startActivity(commomIntent);
                MainActivity.this.finish();
            }
        });

        mall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mallIntent = new Intent(MainActivity.this,
                        MallActivity.class);
                MainActivity.this.startActivity(mallIntent);
                MainActivity.this.finish();
            }
        });

        comp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent compIntent = new Intent(MainActivity.this,
                        CompActivity.class);
                MainActivity.this.startActivity(compIntent);
                MainActivity.this.finish();
            }
        });

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent checkIntent = new Intent(MainActivity.this,
                        CheckActivity.class);
                MainActivity.this.startActivity(checkIntent);
                MainActivity.this.finish();
            }
        });

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
