package com.example.seafooler.hospitalapp.common;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.seafooler.hospitalapp.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fanhao on 17-12-5.
 * Merged by seafooler on 17-12-8.
 */

public class GridAdapter extends BaseAdapter {
    private Context mContext;
    private List<MediaBean> mChildList;
    private String funcName;
    private String mediaDirPath;
    List<String> thumbPngNames = new ArrayList<>();
    List<String> thumbJpgNames = new ArrayList<>();
    List<String> thumbJpegNames = new ArrayList<>();

    public GridAdapter(Context mContext, List<MediaBean> mChildList, String funcName, String mediaDirPath) {
        this.mContext = mContext;
        this.mChildList = mChildList;
        this.funcName = funcName;
        this.mediaDirPath = mediaDirPath;
        String fullName,fileName,suffix;
        //Add all the thumb files
        File mediaDir = new File(mediaDirPath);
        File[] mediaFiles = mediaDir.listFiles();


        int lastIndexofDot = -1;

        for (int i=0; i<mediaFiles.length; i++) {
            fullName = mediaFiles[i].getName();
            lastIndexofDot = fullName.lastIndexOf(".");
            if (lastIndexofDot!=-1) {
                fileName = fullName.substring(0, lastIndexofDot);
                suffix = fullName.substring(fullName.lastIndexOf(".") + 1);
                if (suffix.equals("jpeg")) {
                    thumbJpegNames.add(fileName);
                } else if (suffix.equals("png")) {
                    thumbPngNames.add(fileName);
                } else if (suffix.equals("jpg")) {
                    thumbJpgNames.add(fileName);
                }
            }
        }

    }

    @Override
    public int getCount() {
        return mChildList.size();
    }

    @Override
    public MediaBean getItem(int position) {
        return mChildList.get(position);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.media_list_item, null);
        }

        final ImageView ivMediaCover = convertView.findViewById(R.id.iv_media_item_thumb);
        final TextView tvMediaName = convertView.findViewById(R.id.tv_media_item);
        final String mediaName = getItem(position).getMediaName();

        if (thumbPngNames.contains(mediaName)) {
            ivMediaCover.setImageBitmap(BitmapFactory.decodeFile(mediaDirPath+"/"+mediaName+".png"));
        } else if (thumbJpegNames.contains(mediaName)) {
            ivMediaCover.setImageBitmap(BitmapFactory.decodeFile(mediaDirPath+"/"+mediaName+".jpeg"));
        } else if (thumbJpgNames.contains(mediaName)) {
            ivMediaCover.setImageBitmap(BitmapFactory.decodeFile(mediaDirPath+"/"+mediaName+".jpg"));
        } else {
            if (funcName.equals("pic")) {
                ivMediaCover.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(),R.drawable.default_famous));
            } else {
                ivMediaCover.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(),R.drawable.default_movie));
            }
        }
        tvMediaName.setText(mediaName);
        convertView.setTag(getItem(position));

        return convertView;

    }
}
