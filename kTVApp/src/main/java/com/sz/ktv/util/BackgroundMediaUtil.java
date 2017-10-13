package com.sz.ktv.util;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.MediaController;
import android.widget.VideoView;

public class BackgroundMediaUtil implements SurfaceHolder.Callback,MediaPlayer.OnErrorListener,MediaPlayer.OnCompletionListener {
	
	  
	  private static  MediaPlayer	mediaPlayer;
	  
	  private Uri mUri;
	  private int mPositionWhenPaused = -1;

	  private MediaController mMediaController;
	  
	  public   void playVideo(Context context, VideoView videoView,String path)
	  {
		  mUri = Uri.parse(path);

		//设置视频控制器
		    videoView.setMediaController(new MediaController(context));
		 
		    //播放完成回调
		    videoView.setOnCompletionListener( this);
		 
		    //设置视频路径
		    videoView.setVideoURI(mUri);
		 
		    //开始播放视频
		    videoView.start();

	  }
	public   void playBackgroundMedia(Context context , SurfaceView surfaceView, final String path)
	{
		
		  SurfaceHolder myHolder = surfaceView.getHolder();//得到SurfaceView的控制接口
	        myHolder.addCallback(this);//得到当前的回调接口
	        Uri uri = Uri.parse(path);
	        mediaPlayer = MediaPlayer.create(context,uri);
       
	}
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		try {
			mediaPlayer.setDisplay(holder);//视频显示在SurfaceView上
			mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);//媒体声音类型
			mediaPlayer.setLooping(true);
			mediaPlayer.start();
			mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.seekTo(0);
                    mp.start();
                }
            });
        } catch (IllegalArgumentException e) {
        } catch (SecurityException e) {
        } catch (IllegalStateException e) {
        }
		
	}
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onCompletion(MediaPlayer mp) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean onError(MediaPlayer mp, int what, int extra) {
		// TODO Auto-generated method stub
		return false;
	}
	 
}
