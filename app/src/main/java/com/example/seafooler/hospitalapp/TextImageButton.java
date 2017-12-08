package com.example.seafooler.hospitalapp;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by seafooler on 17-12-8.
 */

public class TextImageButton extends LinearLayout {

    private ImageView buttonImage = null;
    private TextView buttonText = null;

    public TextImageButton(Context context, int imageResId, int textResId) {
        super(context);

        buttonImage = new ImageView(context);
        buttonText = new TextView(context);

        setImageResource(imageResId);
        buttonImage.setPadding(0,0,0,0);

        setText(textResId);
        setTextColor(0xFF0000);
        buttonText.setPadding(0,0,0,0);

        //设置本布局的属性
        setClickable(true);
        setFocusable(true);
        setBackgroundResource(R.drawable.default_game);
        setOrientation(LinearLayout.VERTICAL);

        //首先添加Image,然后才添加Text
        //添加顺序将会影响布局效果
        addView(buttonImage);
        addView(buttonText);
    }

    /**
     *
     * @param resId
     */
    public void setImageResource(int resId) {
       buttonImage.setImageResource(resId);
    }

    /**
     *
     * @param redId
     */
    public void setText(int redId) {
        buttonText.setText(redId);
    }

    /**
     *
     * @param text
     */
    public void setText(CharSequence text) {
        buttonText.setText(text);
    }

    public void setTextColor(int color) {
        buttonText.setTextColor(color);
    }
}
