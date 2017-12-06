package com.example.seafooler.hospitalapp.common;

import android.os.Environment;
import android.util.Log;

import java.io.File;

/**
 * Created by seafooler on 17-12-6.
 */

public class SDCardUtil {
    /**
     * 检查SD卡是否可用
     * getExternalStorageState获取状态
     * Environment.MEDIA_MOUNTED表示：当前sd可用
     */
    public static boolean checkSdCard() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
            //SD卡可用
            return true;
        else
            //SD卡不可用
            return false;
    }


    /**
     * 获取SD卡的文件路径
     * getExternalStorageDirectory获取路径
     */
    public static String getSdPath() {
        return Environment.getExternalStorageDirectory()+"/";
    }

    /**
     * 创建一个文件夹
     */
    public static void createFileDir(String fileDir) {
        String stringPath = getSdPath()+fileDir;
        File path = new File(stringPath);
        if (!path.exists()) {
            path.mkdir();
            Log.i("SDCardUtil", "Mkdir "+stringPath+ " successfully");
        } else {
            Log.i("SDCardUtil", stringPath+" already exists");
        }
    }

    /**
     * 检查路径文件/目录是否存在
     */
    public static boolean checkFileExists(String filePath) {
        File path = new File(filePath);
        if (path.exists()) {
            return true;
        } else {
            return false;
        }
    }
}
