package com.example.seafooler.hospitalapp;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.seafooler.hospitalapp.common.InflateMediaBean;
import com.example.seafooler.hospitalapp.common.IsActivityInStack;
import com.example.seafooler.hospitalapp.common.MediaBean;
import com.example.seafooler.hospitalapp.common.SlideableAdapter;

import java.util.List;

public class PictureActivity extends AppCompatActivity {

    private ViewPager viewPager;

    private ImageView[] mImageViews;
    private String picDirPath;

    private List<MediaBean> mPictureBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //隐藏状态栏
        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_picture);
        ViewGroup group = findViewById(R.id.viewGroup);
        viewPager = findViewById(R.id.viewPager);


        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        picDirPath = getIntent().getStringExtra("picDirPath");

        //Inflate the bean list with InflateMediaBean
        mPictureBean = InflateMediaBean.inflate(picDirPath, "pic", 1);

        //
        mImageViews = new ImageView[mPictureBean.size()];
        for (int i=0; i< mPictureBean.size(); i++) {
            MediaBean bean = mPictureBean.get(i);
            String pathBean = bean.getPath();
            ImageView imageView = new ImageView(this);
            mImageViews[i] = imageView;
            Bitmap bm = BitmapFactory.decodeFile(pathBean);
            bm = enlargePicture(bm); //Enlarge the image to full screen
            imageView.setImageBitmap(bm);
        }

        //设置Adapter
        viewPager.setAdapter(new MyAdapter());
        //viewPager.setOnPageChangeListener(this);
        //设置ViewPager的默认项, 设置为长度的100倍，这样子开始就能往左滑动
        viewPager.setCurrentItem((mImageViews.length) * 100);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK ) //Back to CommonActivity
        {
            if (! IsActivityInStack.isActivityExsit(this, CommonActivity.class)) {
                Intent commonIntent = new Intent(PictureActivity.this,
                        CommonActivity.class);
                commonIntent.putExtra("mediaDirPath", picDirPath);
                commonIntent.putExtra("mediaType", "pic");
                PictureActivity.this.startActivity(commonIntent);
            }
            PictureActivity.this.finish();

        }
        return true;
    }

    @Override
    protected void onResume() {
        /**
         * 设定为横屏
         */
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        super.onResume();
    }


    /**
     * Enlarge the bitmap to full screen
     * @param originalBitmap
     * @return
     */
    private Bitmap enlargePicture(Bitmap originalBitmap) {
        Bitmap resizedBitmap;
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenWidth = dm.widthPixels;
        int screenHeight = dm.heightPixels;
        float w = screenWidth / originalBitmap.getWidth();
        float h = screenHeight / originalBitmap.getHeight();
        if (w <= 0 ){
            w =1;
        }
        if (h <= 0) {
            h = 1;
        }
        if(w >= h) { //enlarge according to the smaller ratio
            w = h;
        }
        Matrix mat = new Matrix();
        mat.postScale(w, w);
        resizedBitmap = Bitmap.createBitmap(originalBitmap,0, 0, originalBitmap.getWidth(),
                originalBitmap.getHeight(), mat, true);
        return resizedBitmap;
    }

    public class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(View container, int position, Object object) {
            //((ViewPager) container).removeView(mImageViews[position % mImageViews.length]);

        }

        /**
         * 载入图片进去，用当前的position 除以 图片数组长度取余数是关键
         */
        @Override
        public Object instantiateItem(View container, int position) {
            try {
                ((ViewPager) container).addView(mImageViews[position % mImageViews.length], 0);
            }catch(Exception e){
            }
            return mImageViews[position % mImageViews.length];
        }


    }

    public void onPageScrollStateChanged(int arg0) {

    }


    public void onPageScrolled(int arg0, float arg1, int arg2) {

    }


    public void onPageSelected(int arg0) {
    }
}
