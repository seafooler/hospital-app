package com.example.seafooler.hospitalapp.common;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.provider.MediaStore;

/**
 * Created by seafooler on 17-12-6.
 */

public class MediaBean {
    private String path;
    private String mediaName;
    private Bitmap thumbImg;
    public String getPath() {
        return path;
    } //Absolute path
    public void setPath(String path) {
        this.path = path;
    }
    public String getMediaName() {
        return mediaName;
    }
    public void setMediaName(String mediaName) {
        this.mediaName = mediaName;
    }
    public Bitmap getThumbImg() {
        return path !=null ? getVideoThumbNail(path) : null;
    }
    public void setThumbImg() {
        this.thumbImg = thumbImg;
        this.thumbImg = thumbImg;
    }

    @Override
    public String toString() {
        return "MediaBean [path=" + path +", mediaName=" + mediaName + ",thumbImg=" + thumbImg +"]";
    }

    /**
     * 根据播放路径设置缩略图
     * @param filePath 视频资源的路径
     * @return 返回缩略图的Bitmap对象
     */

    public Bitmap getVideoThumbNail(String filePath) {
        Bitmap bitmap = null;
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try{
            retriever.setDataSource(filePath);
            bitmap = retriever.getFrameAtTime();
        } catch(IllegalArgumentException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            try {
                retriever.release();
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }
}
