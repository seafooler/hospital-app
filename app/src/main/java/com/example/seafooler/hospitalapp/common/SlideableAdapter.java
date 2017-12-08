package com.example.seafooler.hospitalapp.common;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;

/**
 * Created by seafooler on 17-12-8.
 */

public class SlideableAdapter extends PagerAdapter {
    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
