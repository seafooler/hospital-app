package com.example.seafooler.hospitalapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.example.seafooler.hospitalapp.common.GameAdpater;
import com.example.seafooler.hospitalapp.common.SDCardUtil;

public class InstallGameActivity extends AppCompatActivity {

    private String TAG = "InstallGa meActivity";
    private String gamePath;
    private File[] gameFiles;
    private Map<String, Bitmap> bitmapMaps;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.install_game);
        gamePath = createGameDir();

        File gameDir = new File("gamePath");
        try {
            gameFiles = gameDir.listFiles();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "Error:"+e.toString());
        }

        bitmapMaps = inflateBitmapMap(gameFiles);


        GridView gridView = findViewById(R.id.gamesGridView);

        gridView.setAdapter(new GameAdpater(this, bitmapMaps));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(InstallGameActivity.this, ""+position, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private String createGameDir() {
        if (SDCardUtil.checkSdCard()) {
            SDCardUtil.createFileDir("game");
        } else {
            Log.e(TAG, "SDCard doesn not exist");
        }
        if (SDCardUtil.checkFileExists(SDCardUtil.getSdPath()+"game")) {
            Log.i(TAG, "Game文件夹创建成功");
            return SDCardUtil.getSdPath()+"game";
        } else {
            Log.e(TAG, "Game文件夹创建失败");
            return null;
        }
    }

    /**
     * Generate Bitmap arrays from game folder
     * @param gameFiles
     * @return Map<String, Bitmap>
     */
    private Map<String, Bitmap> inflateBitmapMap(File[] gameFiles) {
        Map<String, Bitmap> apkBitmapMap = new ArrayMap<>();
        List<String> bitmapPngNames = new ArrayList<>();
        List<String> bitmapJpgNames = new ArrayList<>();
        List<String> bitmapJpegNames = new ArrayList<>();
        List<String> apkNames = new ArrayList<>() ;
        String fileName;
        String suffix;
        String iterApkName;
        FileInputStream stream;
        Bitmap tmpBitmap;
        Drawable tmpDrawable;

        //add filename to separate lists
        for (int i=0; i< gameFiles.length; i++) {
            fileName = gameFiles[i].getName();
            suffix = fileName.substring(fileName.lastIndexOf(".")+1);

            if (suffix.equals("apk")) {
                apkNames.add(fileName);
            } else if (suffix.equals("png")){
                bitmapPngNames.add(fileName);
            } else if (suffix.equals("jpg")){
                bitmapJpgNames.add(fileName);
            } else if (suffix.equals("jpeg")){
                bitmapJpgNames.add(fileName);
            }
        }

        Iterator<String> iterator = apkNames.iterator();

        while(iterator.hasNext()) {
            iterApkName = iterator.next();
            if (bitmapPngNames.contains(iterApkName)) {
                //read the bitmap from png file
                try {
                    stream = new FileInputStream(iterApkName + ".png");
                    tmpBitmap = BitmapFactory.decodeStream(stream);
                    apkBitmapMap.put(iterApkName,tmpBitmap);
                    continue;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (bitmapJpgNames.contains(iterApkName)) {
                //read the bitmap from jpg file
                try {
                    stream = new FileInputStream(iterApkName + ".jpg");
                    tmpBitmap = BitmapFactory.decodeStream(stream);
                    apkBitmapMap.put(iterApkName,tmpBitmap);
                    continue;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (bitmapJpegNames.contains(iterApkName)) {
                //read the bitmap from jpeg file
                try {
                    stream = new FileInputStream(iterApkName + ".jpeg");
                    tmpBitmap = BitmapFactory.decodeStream(stream);
                    apkBitmapMap.put(iterApkName,tmpBitmap);
                    continue;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            //read the bitmap from drawable: default_game
            tmpDrawable = getResources().getDrawable(R.drawable.default_game);
            tmpBitmap = ((BitmapDrawable)tmpDrawable).getBitmap();
            apkBitmapMap.put(iterApkName,tmpBitmap);
        }

        return apkBitmapMap;

    }



/**
 * 展现所有的Apk
 *//*
*/
/*
    private class ApkAdapter extends BaseAdapter {
        public int getCount() {
            return
        }

    }*/

}