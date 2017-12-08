package com.example.seafooler.hospitalapp;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.seafooler.hospitalapp.common.GridAdapter;
import com.example.seafooler.hospitalapp.common.InflateMediaBean;
import com.example.seafooler.hospitalapp.common.MediaBean;

import java.util.List;

public class CommonActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private String mediaDirPath;
    private String mediaType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_common);

        mediaDirPath = getIntent().getStringExtra("mediaDirPath");
        mediaType = getIntent().getStringExtra("mediaType");

        List<MediaBean> mMediaBean = InflateMediaBean.inflate(mediaDirPath, mediaType);

        GridAdapter mGridAdapter = new GridAdapter(getBaseContext(), mMediaBean, mediaType);
        GridView mMediaGridView = findViewById(R.id.gv_media);
        mMediaGridView.setVisibility(View.VISIBLE);
        mMediaGridView.setAdapter(mGridAdapter);
        mMediaGridView.setOnItemClickListener(this);
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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        MediaBean bean = (MediaBean) view.getTag();
        /*//根据pathway进行分解
        String pathway = bean.getPath();
        String pathway2;
        int i = pathway.indexOf(".");
        pathway2 = pathway.substring(i);
        Intent intent = new Intent();
        if (pathway2.equals(".mp4")) {
            intent.setClass(CommonActivity.this, VideoActivity.class);
            intent.putExtra("videoDirPath", pathway);
        } else {
            intent.setClass(CommonActivity.this, PictureActivity.class);
            intent.putExtra("picDirPath", pathway);
        }
        startActivity(intent);*/

        String path = bean.getPath();
        Intent intent = new Intent();

        if (mediaType.equals("pic")) {
            //名家汇
            intent.setClass(CommonActivity.this, PictureActivity.class);
            intent.putExtra("picDirPath", path);
        } else {
            //视频
            intent.setClass(CommonActivity.this, VideoActivity.class);
            intent.putExtra("videoDirPath", path);
        }
        startActivity(intent);
    }
}
