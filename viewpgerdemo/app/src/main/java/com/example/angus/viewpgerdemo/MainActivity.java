package com.example.angus.viewpgerdemo;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.PageTransformer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by Angus on 2018/7/6.
 * itwsj0218@gmail.com
 */
public class MainActivity extends AppCompatActivity {
    private int[] mImageIds = new int[] { R.mipmap.guide1,
            R.mipmap.guide2, R.mipmap.guide3};
    private ArrayList<ImageView> mImageViews;
    private ImageView mRedPoint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager vp= findViewById(R.id.vp);

        mImageViews = new ArrayList<ImageView>();// 集合存储图片
        for (int i = 0; i < mImageIds.length; i++) {
            ImageView image = new ImageView(this);
            image.setBackgroundResource(mImageIds[i]);
            mImageViews.add(image);
        }

        //给viewpager设置一个PagerTransformer
        vp.setPageTransformer(true, new PageTransformer() {
            /**
             * 页面滑动时回调的方法,
             * @param page 当前滑动的view
             * @param position 当从右向左滑的时候,左边page的position是[0一-1]变化的
             * 右边page的position是[1一0]变化的,再次滑动的时候,刚才变化到-1(即已经画出视野的page)將从-1变化到-2,
             * 而当前可见的page和右边滑过来的page的position将再次从[0一-1]变化 和 [1一0]变化   但是我们关心是position是[-1一1]变化的
             * page,所以处理动画的时候需要我们过滤一下
             */
            @Override
            public void transformPage(View page, float position) {
//                rollingPage(page,position);
//                sink3D(page,position);
//                raised3D(page,position);
                imitateQQ(page,position);
            }
        });
        ViewPagerAdater viewPagerAdater =new ViewPagerAdater(mImageViews);
        vp.setAdapter(viewPagerAdater);

    }

    /**
     * 动画效果1  凹陷的3D效果
     */
    public void sink3D(View view,float position){
        if(position>=-1&&position<=1){
            view.setPivotX(position<0?view.getWidth():0);
            view.setRotationY(-90*position);
        }
    }
    /**
     * 动画效果2  凸起的3D效果
     */
    public void raised3D(View view,float position){
        if(position>=-1&&position<=1){
            view.setPivotX(position<0?view.getWidth():0);//设置要旋转的Y轴的位置
            view.setRotationY(90*position);//开始设置属性动画值
        }
    }

    /**
     * 动画效果4  仿QQ的缩放动画效果
     */
    public void imitateQQ(View view,float position){
        if(position>=-1&&position<=1){
            view.setPivotX(position>0?0:view.getWidth()/2);
            //view.setPivotY(view.getHeight()/2);
            view.setScaleX((float)((1-Math.abs(position)<0.5)?0.5:(1-Math.abs(position))));
            view.setScaleY((float)((1-Math.abs(position)<0.5)?0.5:(1-Math.abs(position))));
        }
    }
    /**
     * 动画效果5  仿掌阅的翻书动画效果
     * 分析翻书的效果,可以分解为两部分:1.左边的view绕着左边的轴旋转,同时x方向上有缩放的效果
     * 要注意的是因为是viewpager左边的view在滑动的时候是要向左边移动的,但我们要的翻书效果在翻页完成前
     * 是一直在读者视角内的,所以左边的view在滑动的时候要进行向右的平移
     * 2.右边的view从可见的时候开始就一直在左view的下方,但是作为viewpager他是从右边慢慢滑到当前的位置的
     * 所以要达到这个效果就需要进行一个x方向的平移动画
     */
    public void rollingPage(View view,float position){
        if(position>=-1&&position<=1){
            view.setPivotX(0);
            if(position<0){
                view.setTranslationX(-position*view.getWidth());
                view.setRotationY(90*position);
                view.setScaleX(1-Math.abs(position));
            }
            else{
                view.setTranslationX(-position*view.getWidth());
            }

        }
    }


}
