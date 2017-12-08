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

import java.util.List;

/**
 * Created by fanhao on 17-12-5.
 * Merged by seafooler on 17-12-8.
 */

public class GridAdapter extends BaseAdapter {
    private Context mContext;
    private List<MediaBean> mChildList;
    private String funcName;

    public GridAdapter(Context mContext, List<MediaBean> mChildList, String funcName) {
        this.mContext = mContext;
        this.mChildList = mChildList;
        this.funcName = funcName;
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
    public View getView(int position, View view, ViewGroup parent) {
        if (funcName.equals("mov")) {
            view = LayoutInflater.from(mContext).inflate(R.layout.movie_list_item, null);
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
        return view;
    }
}
