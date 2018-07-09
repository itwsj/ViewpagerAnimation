#**ViewpagerAnimation**
 
最近了解了下ViewpagerAnimation相关的动画效果，整理了几个常见动画。

主要在页面滑动回调做下处理

	  1 	//给viewpager设置一个PagerTransformer
	  2 	        vp.setPageTransformer(true, new PageTransformer() {
	  3 	            /**
	  4 	             * 页面滑动时回调的方法,
	  5 	             * @param page 当前滑动的view
	  6 	             * @param position 当从右向左滑的时候,左边page的position是[0一-1]变化的
	  7 	             * 右边page的position是[1一0]变化的,再次滑动的时候,刚才变化到-1(即已经画出视野的page)將从-1变化到-2,
	  8 	             * 而当前可见的page和右边滑过来的page的position将再次从[0一-1]变化 和 [1一0]变化   但是我们关心是position是[-1一1]变化的
	  9 	             * page,所以处理动画的时候需要我们过滤一下
	 10 	             */
	 11 	            @Override
	 12 	            public void transformPage(View page, float position) {
	 13 	//                rollingPage(page,position);
	 14 	//                sink3D(page,position);
	 15 	//                raised3D(page,position);
	 16 	                imitateQQ(page,position);
	 17 	            }
	 18 	        });


##raised3D 效果：

![](https://i.imgur.com/QDdevAd.gif)
	
	  1    /**
	  2      * 动画效果2  凸起的3D效果
	  3      */
	  4     public void raised3D(View view,float position){
	  5         if(position>=-1&&position<=1){
	  6             view.setPivotX(position<0?view.getWidth():0);//设置要旋转的Y轴的位置
	  7             view.setRotationY(90*position);//开始设置属性动画值
	  8         }
	  9     }


##sink3D 效果：

![](https://i.imgur.com/IwD1uFd.gif)

	  1     /**
	  2      * 动画效果1  凹陷的3D效果
	  3      */
	  4     public void sink3D(View view,float position){
	  5         if(position>=-1&&position<=1){
	  6             view.setPivotX(position<0?view.getWidth():0);
	  7             view.setRotationY(-90*position);
	  8         }
	  9     }

##imitateQQ 效果：

![](https://i.imgur.com/g2W9eEP.gif)

	  1    /**
	  2      * 动画效果4  仿QQ的缩放动画效果
	  3      */
	  4     public void imitateQQ(View view,float position){
	  5         if(position>=-1&&position<=1){
	  6             view.setPivotX(position>0?0:view.getWidth()/2);
	  7             //view.setPivotY(view.getHeight()/2);
	  8             view.setScaleX((float)((1-Math.abs(position)<0.5)?0.5:(1-Math.abs(position))));
	  9             view.setScaleY((float)((1-Math.abs(position)<0.5)?0.5:(1-Math.abs(position))));
	 10         }
	 11     }

##rollingPage  效果：

![](https://i.imgur.com/pWF0sm4.gif)


	  1     /**
	  2      * 动画效果5  仿掌阅的翻书动画效果
	  3      * 分析翻书的效果,可以分解为两部分:1.左边的view绕着左边的轴旋转,同时x方向上有缩放的效果
	  4      * 要注意的是因为是viewpager左边的view在滑动的时候是要向左边移动的,但我们要的翻书效果在翻页完成前
	  5      * 是一直在读者视角内的,所以左边的view在滑动的时候要进行向右的平移
	  6      * 2.右边的view从可见的时候开始就一直在左view的下方,但是作为viewpager他是从右边慢慢滑到当前的位置的
	  7      * 所以要达到这个效果就需要进行一个x方向的平移动画
	  8      */
	  9     public void rollingPage(View view,float position){
	 10         if(position>=-1&&position<=1){
	 11             view.setPivotX(0);
	 12             if(position<0){
	 13                 view.setTranslationX(-position*view.getWidth());
	 14                 view.setRotationY(90*position);
	 15                 view.setScaleX(1-Math.abs(position));
	 16             }
	 17             else{
	 18                 view.setTranslationX(-position*view.getWidth());
	 19             }
	 20 
	 21         }
	 22     }