package com.example.seafooler.hospitalapp.common;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.seafooler.hospitalapp.R;

import java.util.List;

/**
 * Created by seafooler on 17-12-6.
 */

public class GameAdpater extends BaseAdapter {
    private Context mContext;

    private Bitmap[] bms;

    public GameAdpater(Context c, Bitmap[] bms) {
        mContext = c;
        this.bms = bms;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adpater
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            //if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(150,150));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8 ,8 ,8, 8);
        } else {
            imageView = (ImageView)convertView;
        }

//        imageView.setImageResource(mThumbIds[position]);
        imageView.setImageBitmap(bms[position]);
        return imageView;
    }

    //references to our images
    private Integer[] mThumbIds = {
            R.drawable.game, R.drawable.game,
            R.drawable.game, R.drawable.game,
            R.drawable.game, R.drawable.game,
            R.drawable.game, R.drawable.game,
            R.drawable.game, R.drawable.game,
            R.drawable.game, R.drawable.game,
            R.drawable.game, R.drawable.game,
            R.drawable.game, R.drawable.game,
            R.drawable.game, R.drawable.game,
            R.drawable.game, R.drawable.game,
            R.drawable.game, R.drawable.game
    };

}
