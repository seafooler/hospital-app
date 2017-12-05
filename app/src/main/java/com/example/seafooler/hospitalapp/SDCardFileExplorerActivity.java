package com.example.seafooler.hospitalapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SDCardFileExplorerActivity extends AppCompatActivity {

    private TextView tvPath;
    private ListView lvFiles;
    private Button btnParent;

    //记录当前的父文件夹
    File currentParent;

    //记录当前路径下的所有文件夹的文件数组
    File[] currentFiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sdcard_file_explorer);
        lvFiles = (ListView) this.findViewById(R.id.files);
        tvPath = (TextView) this.findViewById(R.id.tvpath);
        btnParent = (Button)this.findViewById(R.id.btnParent);

        //获取系统的SDCard的目录
        File root = new File("/mnt/sdcard");

        //如果SD卡存在的话
        if(root.exists()) {
            currentParent = root;
            currentFiles = root.listFiles();
            //使用当前目录下的全部文件、文件夹来填充listView
            inflateListView(currentFiles);
        }

//        lvFile.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//            }
//        });
    }


    /**
     * 根据文件夹填充ListView
     * @param files
     */
    private void inflateListView(File[] files) {
        List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
        for (int i=0; i< files.length; i++) {
            Map<String, Object> listItem = new HashMap<String, Object>();
            if (files[i].isDirectory()) {
                //如果是文件夹就显示图片为文件夹的图片
                listItem.put("icon", R.drawable.folder);
            } else {
                listItem.put("icon", R.drawable.file);
            }
        }
    }

    /**
     * 展现所有的Apk
     */
    private class ApkAdapter extends BaseAdapter {
        public int getCount() {
            return
        }
    }
}
