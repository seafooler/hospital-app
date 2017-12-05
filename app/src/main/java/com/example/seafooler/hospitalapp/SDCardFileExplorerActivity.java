package com.example.seafooler.hospitalapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import java.io.File;

public class SDCardFileExplorerActivity extends AppCompatActivity {

    private Textview tvPath;
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
        lvFiles = this.findViewById(R.id.files);
    }
}
