package com.sz.ktv.util;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class AnimationUtil {
	
	private ViewGroup anim_mask_layout;//动画层
	
	 
    /**  
      * 单例对象实例  
      */    
   private static AnimationUtil instance = null;    
   
    public static AnimationUtil getInstance() {    
         if (instance == null) {                              //line 12    
           instance = new AnimationUtil();          //line 13    
        }    
        return instance;    
   }  
 
private AnimationUtil()
	{
	}
	   /**
     * 抛物线 动画
     *
     */
    @TargetApi(11)
    public void playReadingAnimation(Activity activity, View startView) {
//        final ImageView moveingIV = (ImageView) getLayoutInflater().inflate(R.layout.animator_image, null);
       
    	Drawable dr = new BitmapDrawable(BitmapUtil.getViewBitmap(startView));
//      一、创造出执行动画的主题---imageview
        //代码new一个imageview，图片资源是上面的imageview的图片
        // (这个图片就是执行动画的图片，从开始位置出发，经过一个抛物线（贝塞尔曲线），移动到购物车里)
    	RelativeLayout endView = Global.yidianLayout;
    
        final ImageView moveingIV = new ImageView(activity);
//        goods.setImageResource(R.drawable.add_usb);
        moveingIV.setImageDrawable(dr);
    	anim_mask_layout = createAnimLayout(activity);
    	  RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(startView.getWidth()/2,startView.getHeight()/2);
    	  
        anim_mask_layout.addView(moveingIV,params);//把动画图片添加到动画层
        //获取起点坐标
        int[] start_location = new int[2];
        startView.getLocationInWindow(start_location);
        final int x1 = start_location[0]+startView.getWidth();
        final int y1 = start_location[1]+startView.getHeight();
        //设置动画图片的起始位置
        addViewToAnimLayout(moveingIV,
                start_location);

        //获取终点坐标，最近拍摄的坐标
        int[] location = new int[2];
        endView.getLocationInWindow(location);
        final int x2 = location[0]+20;
        final int y2 = location[1]+20;
        //此处控制偏移量
        int offsetX;
        int offsetY;
        offsetX = 0;//未做偏移，需要的自己计算
        offsetY = 0;
        //抛物线动画，原理：两个位移动画，一个横向匀速移动，一个纵向变速移动，两个动画同时执行，就有了抛物线的效果。
        ObjectAnimator translateAnimationX = ObjectAnimator.ofFloat(moveingIV, "translationX", 0, -(x1 - x2) - offsetX);
        translateAnimationX.setDuration(800);
        translateAnimationX.setInterpolator(new LinearInterpolator());
        ObjectAnimator translateAnimationY = ObjectAnimator.ofFloat(moveingIV, "translationY", 0, y2 - y1 + offsetY);
        translateAnimationY.setDuration(800);
        translateAnimationY.setInterpolator(new AccelerateInterpolator());
        //到终点后的缩小动画
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(moveingIV, "scaleX", 1, 0);
        scaleX.setDuration(200);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(moveingIV, "scaleY", 1, 0);
        scaleY.setDuration(200);
        scaleY.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                anim_mask_layout.removeView(moveingIV); //动画结束后移除动画图片
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        AnimatorSet animatorSet = new AnimatorSet();//设置动画播放顺序
        animatorSet.play(translateAnimationX).with(translateAnimationY);
        animatorSet.play(scaleX).with(scaleY).after(translateAnimationX);
        animatorSet.start();
    }

    /**
     *  创建动画层
     * @param activity 
     *
     */
    private ViewGroup createAnimLayout(Activity activity) {
//        ViewGroup rootView = (ViewGroup) activity.getWindow().getDecorView();
    	ViewGroup 	rootView = Global.homeLayout;
    	
        LinearLayout animLayout = new LinearLayout(activity);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        animLayout.setLayoutParams(lp);
        animLayout.setId(Integer.MAX_VALUE);
        animLayout.setBackgroundResource(android.R.color.transparent);
        rootView.addView(animLayout);
        return animLayout;
    }

    private void addViewToAnimLayout(final View view,
                                            int[] location) {
        int x = location[0];
        int y = location[1];
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = x;
        lp.topMargin = y;
        view.setLayoutParams(lp);
    }
}
