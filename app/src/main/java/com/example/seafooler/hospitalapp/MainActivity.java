package com.example.seafooler.hospitalapp;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    /*private ImageView ivGame;
    private ImageView ivVideo;
    private ImageView ivComp;
    private ImageView ivFamous;
    private ImageView ivMall;
    private ImageView ivCheck;*/

    private LinearLayout llGame;
    private TextImageButton tibGame;
    private LinearLayout llVideo;
    private TextImageButton tibVideo;
    private LinearLayout llComp;
    private TextImageButton tibComp;
    private LinearLayout llFamous;
    private TextImageButton tibFamous;
    private LinearLayout llMall;
    private TextImageButton tibMall;
    private LinearLayout llCheck;
    private TextImageButton tibCheck;


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
        /*ivGame = findViewById(R.id.game);
        ivVideo = findViewById(R.id.video);
        ivComp = findViewById(R.id.comp);
        ivFamous = findViewById(R.id.famous);
        ivMall = findViewById(R.id.mall);
        ivCheck = findViewById(R.id.check);*/

        tibGame = new TextImageButton(this, R.drawable.game, R.string.game);
        tibVideo = new TextImageButton(this, R.drawable.video, R.string.video);
        tibComp = new TextImageButton(this, R.drawable.comp, R.string.comp);
        tibFamous = new TextImageButton(this, R.drawable.famous, R.string.famous);
        tibMall = new TextImageButton(this, R.drawable.mall, R.string.mall);
        tibCheck = new TextImageButton(this, R.drawable.check, R.string.check);

        llGame = findViewById(R.id.game);
        llVideo = findViewById(R.id.video);
        llComp = findViewById(R.id.comp);
        llFamous = findViewById(R.id.famous);
        llMall = findViewById(R.id.mall);
        llCheck = findViewById(R.id.check);

        llGame.addView(tibGame);
        llVideo.addView(tibVideo);
        llComp.addView(tibComp);
        llFamous.addView(tibFamous);
        llMall.addView(tibMall);
        llCheck.addView(tibCheck);

        tibGame.setText("游戏");
        tibVideo.setText("视频点播");
        tibComp.setText("单位介绍");
        tibFamous.setText("名人汇");
        tibMall.setText("商城");
        tibCheck.setText("费用查询");

        tibGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gameIntent = new Intent(MainActivity.this,
                        InstallGameActivity.class);
                MainActivity.this.startActivity(gameIntent);
                MainActivity.this.finish();
            }
        });

        tibVideo.setOnClickListener(new View.OnClickListener() {
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

        tibComp.setOnClickListener(new View.OnClickListener() {
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
