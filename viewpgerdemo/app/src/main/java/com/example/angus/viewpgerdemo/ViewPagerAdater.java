package com.example.angus.viewpgerdemo;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by Angus on 2018/7/6.
 * itwsj0218@gmail.com
 */

public class ViewPagerAdater extends PagerAdapter {
    private ArrayList<ImageView> mImageViews;

    public ViewPagerAdater(ArrayList<ImageView> mImageViews){
        this.mImageViews = mImageViews;
    }

    @Override
    public int getCount() {
        return mImageViews.size();
    }
    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    // 初始化界面数据,类似getView
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = mImageViews.get(position);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

}
