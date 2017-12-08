package com.example.seafooler.hospitalapp.common;

import android.util.Log;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fanhao on 17-12-5
 * Merged by seafooler on 17-12-8.
 */

public class InflateMediaBean {

    private static String TAG = "InflateMediaBean";

    public InflateMediaBean() {

    }

    /**
     * inflate the List<MediaBean> with the files under the path
     * @param mediaDirPath
     * @param mediaType can only handle two types currently: video and picture
     * @return List<MediaBean>
     */
    public static List<MediaBean> inflate (String mediaDirPath, final String mediaType) {
        final List<MediaBean> mMediaBean = new ArrayList<>();
        File dirMedia = new File(mediaDirPath);

        dirMedia.listFiles(new FileFilter() {
            @Override
            public boolean accept(File iterFile) {
                String iterFileFullName = iterFile.getName();
                if (mediaType.equals("pic")) {
                    if (iterFile.isDirectory()) {
                        MediaBean picSubDir = new MediaBean();
                        picSubDir.setMediaName(iterFileFullName);
                        picSubDir.setPath(iterFile.getAbsolutePath());
                        mMediaBean.add(picSubDir);
                        return true;
                    } else {
                        String fileSuffix;
                        int posDot = iterFileFullName.indexOf(".");
                        if (posDot != -1) {
                            fileSuffix = iterFileFullName.substring(posDot);
                            if (fileSuffix.equalsIgnoreCase(".png") || fileSuffix.equalsIgnoreCase(".jpe") || fileSuffix.equalsIgnoreCase("jpeg")) {
                                MediaBean pic = new MediaBean();
                                pic.setMediaName(iterFileFullName);
                                pic.setPath(iterFile.getAbsolutePath());
                                Log.i(TAG, "最后的大小" + "ScannerAnsyTask---图片名称--name--" + pic.getPath());
                                mMediaBean.add(pic);
                                return true;
                            } else {
                                Log.e(TAG, "图片格式不支持");
                                return false;
                            }
                        } else {
                            Log.e(TAG, "文件名中不含“.”");
                            return false;
                        }
                    }
                } else if (mediaType.equals("mov")) {
                    String fileSuffix;
                    int posDot = iterFileFullName.indexOf(".");
                    if (posDot != -1) {
                        fileSuffix = iterFileFullName.substring(posDot);
                        if (fileSuffix.equalsIgnoreCase(".mp4") || fileSuffix.equalsIgnoreCase(".3gp") || fileSuffix.equalsIgnoreCase(".wmv")
                                || fileSuffix.equalsIgnoreCase(".ts") || fileSuffix.equalsIgnoreCase(".rmvb")
                                || fileSuffix.equalsIgnoreCase(".mov") || fileSuffix.equalsIgnoreCase(".m4v")
                                || fileSuffix.equalsIgnoreCase(".avi") || fileSuffix.equalsIgnoreCase(".m3u8")
                                || fileSuffix.equalsIgnoreCase(".3gpp") || fileSuffix.equalsIgnoreCase(".3gpp2")
                                || fileSuffix.equalsIgnoreCase(".mkv") || fileSuffix.equalsIgnoreCase(".flv")
                                || fileSuffix.equalsIgnoreCase(".divx") || fileSuffix.equalsIgnoreCase(".f4v")
                                || fileSuffix.equalsIgnoreCase(".rm") || fileSuffix.equalsIgnoreCase(".asf")
                                || fileSuffix.equalsIgnoreCase(".ram") || fileSuffix.equalsIgnoreCase(".mpg")
                                || fileSuffix.equalsIgnoreCase(".v8") || fileSuffix.equalsIgnoreCase(".swf")
                                || fileSuffix.equalsIgnoreCase(".m2v") || fileSuffix.equalsIgnoreCase(".asx")
                                || fileSuffix.equalsIgnoreCase(".ra") || fileSuffix.equalsIgnoreCase(".ndivx")
                                || fileSuffix.equalsIgnoreCase(".xvid")) {
                            MediaBean video = new MediaBean();
                            video.setMediaName(iterFileFullName);
                            video.setPath(iterFile.getAbsolutePath());
                            Log.i(TAG, "最后的大小" + "ScannerAnsyTask---视频名称--name--" + video.getPath());
                            mMediaBean.add(video);
                            return true;
                        } else {
                            Log.e(TAG, "视频格式不支持");
                            return false;
                        }
                    } else {
                        Log.e(TAG, "文件名中不包含“.”");
                        return false;
                    }
                } else {
                    Log.e(TAG, "InflateMediaBean类只支持视频和图片的处理操作");
                    return false;
                }

                /*String fileSuffix;
                int posDot = iterFileFullName.indexOf(".");

                if (posDot != -1) {
                    fileSuffix = iterFileFullName.substring(posDot);
                    if (mediaType.equals("mov")) {
                        if (fileSuffix.equalsIgnoreCase(".mp4") || fileSuffix.equalsIgnoreCase(".3gp") || fileSuffix.equalsIgnoreCase(".wmv")
                                || fileSuffix.equalsIgnoreCase(".ts") || fileSuffix.equalsIgnoreCase(".rmvb")
                                || fileSuffix.equalsIgnoreCase(".mov") || fileSuffix.equalsIgnoreCase(".m4v")
                                || fileSuffix.equalsIgnoreCase(".avi") || fileSuffix.equalsIgnoreCase(".m3u8")
                                || fileSuffix.equalsIgnoreCase(".3gpp") || fileSuffix.equalsIgnoreCase(".3gpp2")
                                || fileSuffix.equalsIgnoreCase(".mkv") || fileSuffix.equalsIgnoreCase(".flv")
                                || fileSuffix.equalsIgnoreCase(".divx") || fileSuffix.equalsIgnoreCase(".f4v")
                                || fileSuffix.equalsIgnoreCase(".rm") || fileSuffix.equalsIgnoreCase(".asf")
                                || fileSuffix.equalsIgnoreCase(".ram") || fileSuffix.equalsIgnoreCase(".mpg")
                                || fileSuffix.equalsIgnoreCase(".v8") || fileSuffix.equalsIgnoreCase(".swf")
                                || fileSuffix.equalsIgnoreCase(".m2v") || fileSuffix.equalsIgnoreCase(".asx")
                                || fileSuffix.equalsIgnoreCase(".ra") || fileSuffix.equalsIgnoreCase(".ndivx")
                                || fileSuffix.equalsIgnoreCase(".xvid")) {
                            MediaBean video = new MediaBean();
                            video.setMediaName(iterFileFullName);
                            video.setPath(iterFile.getAbsolutePath());
                            Log.i(TAG, "最后的大小" + "ScannerAnsyTask---视频名称--name--" + video.getPath());
                            mMediaBean.add(video);
                            return true;
                        } else {
                            Log.e(TAG, "视频格式不支持");
                            return false;
                        }
                    } else if (mediaType.equals("pic")) {
                        if (fileSuffix.equalsIgnoreCase(".png") || fileSuffix.equalsIgnoreCase(".jpe") || fileSuffix.equalsIgnoreCase("jpeg")) {
                            MediaBean pic = new MediaBean();
                            pic.setMediaName(iterFileFullName);
                            pic.setPath(iterFile.getAbsolutePath());
                            Log.i(TAG, "最后的大小" + "ScannerAnsyTask---图片名称--name--" + pic.getPath());
                            mMediaBean.add(pic);
                            return true;
                        } else {
                            Log.e(TAG, "图片格式不支持");
                            return false;
                        }
                    } else {
                        Log.e(TAG, "InflateMediaBean类只支持视频和图片的处理操作");
                        return false;
                    }
                } else {
                    Log.e(TAG, "文件名中不包含“.”");
                    return false;
                }*/
            }
        });
        return mMediaBean;
    }
}
