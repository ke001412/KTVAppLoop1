package com.sz.ktv.util;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;

public class ViewAnimationUtil {

	public static void startViewAnimation(final View view)
	{
		
		flipit(view);
		
	}
	
	 private static Interpolator accelerator = new AccelerateInterpolator();
	    private static Interpolator decelerator = new DecelerateInterpolator();
	    private static void flipit(final View view) {
	        ObjectAnimator visToInvis = ObjectAnimator.ofFloat(view, "rotationY", 0f, 90f);
	        visToInvis.setDuration(1500);
	        visToInvis.setInterpolator(accelerator);
	        final ObjectAnimator invisToVis = ObjectAnimator.ofFloat(view, "rotationY",
	        		270f, 360f);
	        invisToVis.setDuration(1500);
	        invisToVis.setInterpolator(decelerator);
	        visToInvis.addListener(new AnimatorListenerAdapter() {
	            @Override
	            public void onAnimationEnd(Animator anim) {
	            	view.setVisibility(View.GONE);
	                invisToVis.start();
	                view.setVisibility(View.VISIBLE);
	            }
	        });
	        visToInvis.start();
	    }
	    
	
}
