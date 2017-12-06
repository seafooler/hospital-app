package com.example.seafooler.hospitalapp.common;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.seafooler.hospitalapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by seafooler on 17-12-6.
 */

public class GameAdpater extends BaseAdapter {
    private Context mContext;

    private Map<String, Bitmap> bms;

    private ArrayList<String> apkNames = new ArrayList<>();

    public GameAdpater(Context c, Map<String,Bitmap> bms) {
        mContext = c;
        this.bms = bms;
        // Extract all the apk names
        for (String key : bms.keySet()) {
            apkNames.add(key);
        }
    }

    public int getCount() {
        return apkNames.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adpater
    public View getView(int position, View convertView, ViewGroup parent) {
        /*ImageView imageView;
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
        imageView.setImageBitmap();
        imageView.setImageBitmap(bms[position]);
        return imageView;*/

        final String apkName = apkNames.get(position);

        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.game_unit, null);
        }

        final ImageView ivApkCover = convertView.findViewById(R.id.apk_cover);
        final TextView tvApkName = convertView.findViewById(R.id.apk_name);

        ivApkCover.setImageBitmap(bms.get(apkName));
        tvApkName.setText(apkName);

        return convertView;

    }

    //references to our images
    /*private Integer[] mThumbIds = {
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
    };*/

}
