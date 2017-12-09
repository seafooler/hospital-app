package com.example.seafooler.hospitalapp;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.example.seafooler.hospitalapp.common.GameAdapter;
import com.example.seafooler.hospitalapp.common.IsActivityInStack;
import com.example.seafooler.hospitalapp.common.SDCardUtil;

public class InstallGameActivity extends AppCompatActivity {

    private String TAG = "InstallGameActivity";
    private String gamePath;
    private File[] gameFiles;
    private Map<String, Bitmap> bitmapMaps;
    private ArrayList<String> apkNames = new ArrayList<>() ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //隐藏状态栏
        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.install_game);
        gamePath = createGameDir();

        File gameDir = new File(gamePath);
        try {
            gameFiles = gameDir.listFiles();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "Error:"+e.toString());
        }

        bitmapMaps = inflateBitmapMap(gameFiles);


        GridView gridView = findViewById(R.id.gamesGridView);

        gridView.setAdapter(new GameAdapter(this, bitmapMaps, apkNames));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
//                Toast.makeText(InstallGameActivity.this, ""+apkNames.get(position), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(new File(gamePath+apkNames.get(position)+".apk")), "application/vnd.android.package-archive");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK )
        {
            if (IsActivityInStack.isActivityExsit(this, MainActivity.class)) {
                Intent mainIntent = new Intent(InstallGameActivity.this,
                        MainActivity.class);
                InstallGameActivity.this.startActivity(mainIntent);
            }
            InstallGameActivity.this.finish();
        }
        return true;
    }


    /**
     * create the game directory under /storage/sdcard/
     * @return String path
     */
    private String createGameDir() {
        if (SDCardUtil.checkSdCard()) {
            SDCardUtil.createFileDir("game");
        } else {
            Log.e(TAG, "SDCard doesn not exist");
        }
        if (SDCardUtil.checkFileExists(SDCardUtil.getSdPath()+"game")) {
            Log.i(TAG, "Game文件夹创建成功");
            return SDCardUtil.getSdPath()+"game/";
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
        String fullName;
        String fileName;
        String suffix;
        String iterApkName;
        FileInputStream stream;
        Bitmap tmpBitmap;
        Drawable tmpDrawable;

        //add filename to separate lists
        for (int i=0; i< gameFiles.length; i++) {
            fullName = gameFiles[i].getName();
            fileName = fullName.substring(0, fullName.lastIndexOf("."));
            suffix = fullName.substring(fullName.lastIndexOf(".")+1);
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
                    stream = new FileInputStream( gamePath + iterApkName + ".png");
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
                    stream = new FileInputStream(gamePath + iterApkName + ".jpg");
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
                    stream = new FileInputStream( gamePath + iterApkName + ".jpeg");
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

}