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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by seafooler on 17-12-6.
 */

public class GameAdapter extends BaseAdapter {
    private Context mContext;

    private Map<String, Bitmap> apkMap;

    private ArrayList<String> apkNames = new ArrayList<>();

    public GameAdapter(Context c, Map<String,Bitmap> apkMap, ArrayList<String> apkNames) {
        mContext = c;
        this.apkMap = apkMap;
        this.apkNames = apkNames;
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

        final String apkName = apkNames.get(position);

        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.apk_list_item, null);
        }

        final ImageView ivApkCover = convertView.findViewById(R.id.iv_apk_item_thumb);
        final TextView tvApkName = convertView.findViewById(R.id.tv_apk_item);

        ivApkCover.setImageBitmap(apkMap.get(apkName));
        tvApkName.setText(apkName);

        return convertView;

    }

}
