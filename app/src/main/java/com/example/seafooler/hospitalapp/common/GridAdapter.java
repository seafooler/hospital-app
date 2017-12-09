package com.example.seafooler.hospitalapp.common;

import android.content.Context;
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
    List<String> thumbNames = new ArrayList<>();

    public GridAdapter(Context mContext, List<MediaBean> mChildList, String funcName, String mediaDirPath) {
        this.mContext = mContext;
        this.mChildList = mChildList;
        this.funcName = funcName;
        this.mediaDirPath = mediaDirPath;
        String fullName,fileName,suffix;
        //Add all the thumb files
        File mediaDir = new File(mediaDirPath);
        File[] mediaFiles = mediaDir.listFiles();
        for (int i=0; i<mediaFiles.length; i++) {
            fullName = mediaFiles[i].getName();
            fileName = fullName.substring(0, fullName.lastIndexOf("."));
            suffix = fullName.substring(fullName.lastIndexOf(".")+1);
            if (suffix.equals("jpeg")) {
                thumbNames.add(fileName);
            } else if (suffix.equals("png")){
                thumbNames.add(fileName);
            } else if (suffix.equals("jpg")){
                thumbNames.add(fileName);
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
        /*if (funcName.equals("mov")) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.movie_list_item, null);
            final ImageView ivApkCover = convertView.findViewById(R.id.apk_cover);
            //ImageView ivMovieItemThumb = ViewHolder.get(view, R.id.iv_game_item_thumb);
            //ivMovieItemThumb.setImageBitmap(getItem(position).getThumbImg());
            TextView tvMovieItem = ViewHolder.get(view, R.id.tv_movie_item);
            tvMovieItem.setText(getItem(position).getMediaName());
            view.setTag(getItem(position));
        } else {
            view = LayoutInflater.from(mContext).inflate(R.layout.pic_list_item, null);
            TextView tvPicItem = ViewHolder.get(view, R.id.tv_pic_item);
            tvPicItem.setText(getItem(position).getMediaName());
            view.setTag(getItem(position));
        }
        return view;*/

        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.media_list_item, null);
        }

        final ImageView ivMediaCover = convertView.findViewById(R.id.iv_movie_item_thumb);
        final TextView tvMediaName = convertView.findViewById(R.id.tv_movie_item);

        if (funcName.equals("mov")) {
            String fullName = getItem(position).getMediaName();
            String mediaPartPath = getItem(position).getPath().substring(0, fullName.lastIndexOf("."));
            String mediaThumbPath;
            File mediaDir = new File(mediaDirPath);
            File[] mediaFiles = mediaDir.listFiles();
            for (int i=0; i<mediaFiles.length; i++) {

            }

        }


        ivMediaCover.setImageBitmap(apkMap.get(apkName));
        tvMediaName.setText(getItem(position).getMediaName());

        return convertView;

    }
}
