package com.example.seafooler.hospitalapp;

import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.seafooler.hospitalapp.utils.SDCardUtil;

public class InstallGameActivity extends AppCompatActivity {

    private ListView lvFiles;
    private TextView txPath;
    private String TAG = "InstallGameActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.install_game);
        txPath = findViewById(R.id.tv_path);
        if (SDCardUtil.checkSdCard()) {
            txPath.setText("SDCard exists:"+SDCardUtil.getSdPath());
            SDCardUtil.createFileDir("game");
        } else {
            txPath.setText("SDCard doesn not exist");
        }
        if (SDCardUtil.checkFileExists(SDCardUtil.getSdPath()+"game")) {
            Log.i(TAG, "Game创建成功");
        } else {
            Log.i(TAG, "Game创建失败");
        }

    }


    //    private TextView tvPath;
//    private ListView lvFiles;
//    private Button btnParent;

//    //记录当前的父文件夹
//    File currentParent;
//
//    //记录当前路径下的所有文件夹的文件数组
//    File[] currentFiles;
//
//    File root;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_sdcard_file_explorer);
//        lvFiles = this.findViewById(R.id.files);
//        tvPath = this.findViewById(R.id.tv_path);
//        btnParent = this.findViewById(R.id.btnParent);
//
//        //获取系统的SDCard的目录
//        try {
//             root = new File("/sdcard/Movies");
//        } catch (Exception e) {
//            e.printStackTrace();
//            Log.e("SDCard", "Error:"+e.toString());
//        }
//
//        //如果SD卡存在的话
//        if(root.exists()) {
//            currentParent = root;
//            Log.i("SDCard", root.toString());
//            try {
//                currentFiles = root.listFiles();
//            } catch (Exception e) {
//                e.printStackTrace();
//                Log.e("SDCard", "Error:"+e.toString());
//            }
//            //使用当前目录下的全部文件、文件夹来填充listView
//            inflateListView(currentFiles);
//            Log.i("SDCard", "SD卡存在");
//        }
//
//        Log.i("SDCard", "SD卡不存在");
//
//        lvFiles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
//                if (currentFiles[position].isFile()) {
//                    return;
//                }
//
//                //获取用户点击的文件夹下的所有文件
//                File[] tem = currentFiles[position].listFiles();
//                if (tem == null || tem.length == 0) {
//                    Toast.makeText(InstallGameActivity.this, "当前路径不可访问或者该路径下没有文件", Toast.LENGTH_LONG).show();
//                } else {
//                    //获取用户点击的列表项对应的文件夹，设置为挡墙的父文件夹
//                    currentParent = currentFiles[position];
//                    //保存当前的父文件夹内的全部文件和文件夹
//                    currentFiles = tem;
//                    //再次更新ListView
//                    inflateListView(currentFiles);
//                }
//            }
//        });
//
//        //获取上一级目录
//        btnParent.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                try {
//                    if (!currentParent.getCanonicalPath().equals("/mnt/sdcard")) {
//                        //获取上一级目录
//                        currentParent = currentParent.getParentFile();
//                        //列出当前目录下的所有文件
//                        currentFiles = currentParent.listFiles();
//                        //再次更新ListView
//                        inflateListView(currentFiles);
//                    }
//                } catch (Exception e) {
//                    //TODO: handle exception
//                }
//            }
//        });
//    }
//
//
//
//
//    /**
//     * 根据文件夹填充ListView
//     * @param files
//     */
//    private void inflateListView(File[] files) {
//        Log.i("SDCard", "11111111");
//        if (files == null) {
//            Log.i("SDCard", "files is null");
//
//        }
//        Log.i("SDCard", "files.length:"+files.length);
//
//        List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
//        for (int i=0; i< files.length; i++) {
//            Map<String, Object> listItem = new HashMap<String, Object>();
//            if (files[i].isDirectory()) {
//                //如果是文件夹就显示图片为文件夹的图片
//                listItem.put("icon", R.drawable.folder);
//            } else {
//                listItem.put("icon", R.drawable.file);
//            }
//            Log.i("SDCard", "2222222");
//
//
//            //添加一个文件名称
//            listItem.put("filename", files[i].getName());
//            File myFile = new File(files[i].getName());
//
//            //获取文件的最后修改日期
//            long modTime = myFile.lastModified();
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-d HH:mm:ss");
//            System.out.println(dateFormat.format(new Date(modTime)));
//
//            //添加一个最后修改日期
//            listItem.put("modify", "修改日期："+dateFormat.format(new Date(modTime)));
//            listItems.add(listItem);
//        }
//        Log.i("SDCard", "33333333");
//
//        //定义一个SimpleAdapter
//        SimpleAdapter adapter = new SimpleAdapter(
//                InstallGameActivity.this, listItems, R.layout.list_item,
//                new String[]{"filename", "icon", "modify"}, new int[] {R.id.file_name, R.id.icon, R.id.file_modify}
//        );
//
//        //填充数据集
//        lvFiles.setAdapter(adapter);
//        try{
//            tvPath.setText("当前路径为：" + currentParent.getCanonicalPath());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//    }

//    /**
//     * 展现所有的Apk
//     */
//    private class ApkAdapter extends BaseAdapter {
//        public int getCount() {
//            return
//        }
//    }
}
