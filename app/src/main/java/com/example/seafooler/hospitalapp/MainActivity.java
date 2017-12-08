package com.example.seafooler.hospitalapp;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView ivGame;
    private ImageView ivVideo;
    private ImageView ivComp;
    private ImageView ivFamous;
    private ImageView ivMall;
    private ImageView ivCheck;

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
        ivGame = findViewById(R.id.game);
        ivVideo = findViewById(R.id.video);
        ivComp = findViewById(R.id.comp);
        ivFamous = findViewById(R.id.famous);
        ivMall = findViewById(R.id.mall);
        ivCheck = findViewById(R.id.check);

        ivGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gameIntent = new Intent(MainActivity.this,
                        InstallGameActivity.class);
                MainActivity.this.startActivity(gameIntent);
                MainActivity.this.finish();
            }
        });

        ivVideo.setOnClickListener(new View.OnClickListener() {
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

        ivFamous.setOnClickListener(new View.OnClickListener() {
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
