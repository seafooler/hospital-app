package com.example.seafooler.hospitalapp;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;

import com.example.seafooler.hospitalapp.common.IsActivityInStack;

public class CompActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏状态栏
        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //隐藏标题栏
//        getSupportActionBar().hide();

        setContentView(R.layout.activity_comp);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK )
        {
            if (IsActivityInStack.isActivityExsit(this, MainActivity.class)) {
                Intent mainIntent = new Intent(CompActivity.this,
                        MainActivity.class);
                CompActivity.this.startActivity(mainIntent);
            }
            CompActivity.this.finish();

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
