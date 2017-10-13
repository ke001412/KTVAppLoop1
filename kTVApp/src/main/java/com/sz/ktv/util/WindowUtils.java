package com.sz.ktv.util;

import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;

import com.hisilicon.android.mediaplayer.HiMediaPlayer;
import com.hisilicon.android.mediaplayer.HiMediaPlayer.OnErrorListener;
import com.hisilicon.android.mediaplayer.HiMediaPlayer.OnFastBackwordCompleteListener;
import com.hisilicon.android.mediaplayer.HiMediaPlayer.OnPreparedListener;
import com.hisilicon.android.videoplayer.view.HisiVideoView;
import com.sz.ktv.R;
import com.sz.ktv.db.Song;

/**
 * 弹窗辅助类
 *
 * @ClassName WindowUtils
 *
 *
 */
public class WindowUtils {

    private static final String LOG_TAG = "WindowUtils";
    private static View mView = null;
    private static WindowManager mWindowManager = null;
    private static Context mContext = null;

    public static Boolean isShown = false;

	static Song song = null;
	private static HisiVideoView videoView;
    
    

	 static  OnFastBackwordCompleteListener mFastBackwordCompleteListener = new OnFastBackwordCompleteListener()
	    {
	        public void onFastBackwordComplete(HiMediaPlayer mp)
	        {
	             
	        }
	    };

	    static   OnErrorListener mOnErrorListener = new OnErrorListener()
	    {
	        public boolean onError(HiMediaPlayer mp, int what, int extra)
	        {
	             
	            return false;
	        }
	    };
	   
		
		
	    static OnPreparedListener mPreparedListener = new OnPreparedListener()
	    {
	        public void onPrepared(HiMediaPlayer mp)
	        {
	        	 
	            start();
	        }
	    };
	    private  static  void start()
	    {
	        videoStart();
	        
	    }

	    private  static  void videoStart()
	    {
	    	 
//	    	videoView.setVideoScale(200, 200);
	        videoView.start();
	       
	    }
	    private  static   HiMediaPlayer.OnCompletionListener mCompletionListener =
	        new HiMediaPlayer.OnCompletionListener() {
	        public void onCompletion(HiMediaPlayer mp) { 
	        	 
	        
	        }
	    };

	    
    /**
     * 显示弹出框
     *
     * @param context
     * @param view
     */
    public static void showPopupWindow(final Context context,Song msong) {
        if (isShown) {
//            //LogUtil.i(LOG_TAG, "return cause already shown");
            return;
        }
        song = msong;
        isShown = true;
//        //LogUtil.i(LOG_TAG, "showPopupWindow");

        // 获取应用的Context
        mContext = context.getApplicationContext();
        // 获取WindowManager
        mWindowManager = (WindowManager) mContext
                .getSystemService(Context.WINDOW_SERVICE);

        mView = setUpView(context);

        final WindowManager.LayoutParams params = new WindowManager.LayoutParams();

        // 类型
        params.type = LayoutParams.TYPE_SYSTEM_OVERLAY;

        // WindowManager.LayoutParams.TYPE_SYSTEM_ALERT

        // 设置flag

        int flags = WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM;
        // | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        // 如果设置了WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE，弹出的View收不到Back键的事件
        params.flags = LayoutParams.FLAG_NOT_TOUCH_MODAL
				| LayoutParams.FLAG_NOT_FOCUSABLE
				| LayoutParams.FLAG_LAYOUT_NO_LIMITS;
        // 不设置这个弹出框的透明遮罩显示为黑色
        params.format = PixelFormat.TRANSPARENT;
        // FLAG_NOT_TOUCH_MODAL不阻塞事件传递到后面的窗口
        // 设置 FLAG_NOT_FOCUSABLE 悬浮窗口较小时，后面的应用图标由不可长按变为可长按
        // 不设置这个flag的话，home页的划屏会有问题

        params.width = 500;//LayoutParams.WRAP_CONTENT;
        params.height = 390;//LayoutParams.WRAP_CONTENT;

        params.gravity = Gravity.CENTER;

        mWindowManager.addView(mView, params);

//        //LogUtil.i(LOG_TAG, "add view");

    }

    /**
     * 隐藏弹出框
     */
    public static void hidePopupWindow() {
//        //LogUtil.i(LOG_TAG, "hide " + isShown + ", " + mView);
        if (isShown && null != mView) {
//            //LogUtil.i(LOG_TAG, "hidePopupWindow");
            mWindowManager.removeView(mView);
            isShown = false;
        }

    }

    private static View setUpView(final Context context) {

//        //LogUtil.i(LOG_TAG, "setUp view");

        View view = LayoutInflater.from(context).inflate(R.layout.ktv_preview_layout,
                null);
        
        videoView = (HisiVideoView)view.findViewById(R.id.vide_view);
		 
		 videoView.setOnPreparedListener(mPreparedListener);
	        videoView.setOnCompletionListener(mCompletionListener);
	        videoView.setOnErrorListener(mOnErrorListener);
	        videoView.setOnFastBackwordCompleteListener(mFastBackwordCompleteListener);
	        videoView.setZOrderOnTop(true);
	        videoView.setZOrderMediaOverlay(true);
//        Button positiveBtn = (Button) view.findViewById(R.id.positiveBtn);
//        positiveBtn.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//             
//                WindowUtils.hidePopupWindow();
//
//            }
//        });

//        Button negativeBtn = (Button) view.findViewById(R.id.negativeBtn);
//        negativeBtn.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                //LogUtil.i(LOG_TAG, "cancel on click");
//                WindowUtils.hidePopupWindow();
//
//            }
//        });

        // 点击窗口外部区域可消除
        // 这点的实现主要将悬浮窗设置为全屏大小，外层有个透明背景，中间一部分视为内容区域
        // 所以点击内容区域外部视为点击悬浮窗外部
        final View popupWindowView = view.findViewById(R.id.pre_layout);// 非透明的内容区域

        view.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                //LogUtil.i(LOG_TAG, "onTouch");
                int x = (int) event.getX();
                int y = (int) event.getY();
                Rect rect = new Rect();
                popupWindowView.getGlobalVisibleRect(rect);
                if (!rect.contains(x, y)) {
                    WindowUtils.hidePopupWindow();
                }

                //LogUtil.i(LOG_TAG, "onTouch : " + x + ", " + y + ", rect: "
//                        + rect);
                return false;
            }
        });

        // 点击back键可消除
//        view.setOnKeyListener(new OnKeyListener() {
//
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                switch (keyCode) {
//                case KeyEvent.KEYCODE_BACK:
//                    WindowUtils.hidePopupWindow();
//                    return true;
//                default:
//                    return false;
//                }
//            }
//        });

        return view;

    }
}
