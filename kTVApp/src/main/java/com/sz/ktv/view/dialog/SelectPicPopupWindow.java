package com.sz.ktv.view.dialog;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;

import com.hisilicon.android.mediaplayer.HiMediaPlayer;
import com.hisilicon.android.mediaplayer.HiMediaPlayer.OnErrorListener;
import com.hisilicon.android.mediaplayer.HiMediaPlayer.OnFastBackwordCompleteListener;
import com.hisilicon.android.mediaplayer.HiMediaPlayer.OnPreparedListener;
import com.hisilicon.android.videoplayer.util.Constants;
import com.hisilicon.android.videoplayer.view.HisiVideoView;
import com.sz.ktv.MainActivity;
import com.sz.ktv.R;
import com.sz.ktv.db.DataBase;
import com.sz.ktv.db.Song;

public class SelectPicPopupWindow extends Activity implements
		OnClickListener{

	HisiVideoView videoView;
	public static final String KTV_SONG = "ktv_song_play";
	public static final String KTV_CURRENT_POS = "ktv_song_curr_pos";
	private boolean flag = false;
	String zimuTemp = "";
	String zimuGequName;
	int currentPos = 0;
	private Song currentSong ;
	 
	
	private static final int MSG_ZIMU_SET = 11;
	 int width;
	 int height ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
        requestWindowFeature( Window.FEATURE_NO_TITLE );
       getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN,
                              WindowManager.LayoutParams.FLAG_FULLSCREEN );
       
		setContentView(R.layout.ktv_preview_layout);
	    
        ctrlBarHandler = new Handler();
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		 if(null != bundle)
		 {
			  currentSong = (Song)bundle.getSerializable(SelectPicPopupWindow.KTV_SONG) ;
			 currentPos = bundle.getInt(SelectPicPopupWindow.KTV_CURRENT_POS);
			 
		 }
		 initView();
		WindowManager wm = this.getWindowManager();
		width= wm.getDefaultDisplay().getWidth();
		height = wm.getDefaultDisplay().getHeight();
	}
	
	  private Handler ctrlBarHandler = null;
	 private MyThread mThread = null;
	    class MyThread implements Runnable
	    {
	        public void run()
	        { 
	            
	            myHandler.sendEmptyMessage(Constants.showMediaController);
	        }
	    }
	private void initView() {

		
		videoView = (HisiVideoView) findViewById(R.id.vide_view);
        videoView.setOnPreparedListener(mPreparedListener);
        videoView.setOnCompletionListener(mCompletionListener);
        videoView.setOnErrorListener(mOnErrorListener);
        videoView.setOnFastBackwordCompleteListener(mFastBackwordCompleteListener);
        videoView.setVideoScale(200, 200);
		initData();
		 
		
	}
	
	String defaultSongPath  = null;
	private void initData() {
		if(null ==currentSong)
		{
			return ;
		}
		defaultSongPath = DataBase.MNT_SDA_SONG_PATH + currentSong.getSong_file_name() ;//"60077211.mkv";
		File file =new File(defaultSongPath);
		if(null == file || !file.exists())
		{
			return ;
		}
		 videoView.setVideoPath(defaultSongPath);
	    
	        mThread  = new MyThread();
	}
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		 
        if (keyCode == KeyEvent.KEYCODE_BACK
                 && event.getRepeatCount() == 0) {
        	if(null != videoView )
        	{
             
             if (videoView.isPlaying()) {
            	  
     		}
        	}
             finish();
             return true;
         }
         return super.onKeyDown(keyCode, event);
     }
	 
	Handler myHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case Constants.showMediaController:
				if(flag)
				{
					ctrlBarHandler.removeCallbacks(mThread);
					return ;
					
				}
				try {
					int currentPos = videoView.getCurrentPosition();
					System.out.println("当前精度更新: "+ currentPos);
					
					msg = obtainMessage(Constants.showMediaController);
					sendMessageDelayed(msg, 1000);
					
				} catch (Exception e) {
				}
				break;
			case MSG_ZIMU_SET:
			 
				break;
			case MSG_NEXT_SONG:
				nextSong();
				break;
			}
		}
	};
 
 
	@Override
	protected void onResume() {
		super.onResume();
//		ktvMedia.start();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
//		release(true);
		flag = false;
		stop(); 
		 
	}

	
	@Override
	protected void onPause() {
		super.onPause();
 
	}

	@Override
	public void onClick(View v) {

		int id = v.getId();
		switch (id) {
		case R.id.ktv_play_left:
			try {
				int current = videoView.getCurrentPosition();
				current = current - 3000;
				videoView.seekTo(current);
				 
			} catch (Exception e) {
			}

			break;
		case R.id.ktv_play_right:
			try {
				int currentr = videoView.getCurrentPosition();
				currentr = currentr + 3000;
				videoView.seekTo(currentr);
				 
			} catch (Exception e) {
			}

			break;

		default:
			break;
		}

	}

	 

	 @Override
	public void onBackPressed() {
		super.onBackPressed();
		stop();
	}
	 
	 private void stop()
	 {
		 
			videoView.stopPlayback();
			finish();
	 }

			    private static final int MSG_NEXT_SONG = 1100;
			    
			    OnFastBackwordCompleteListener mFastBackwordCompleteListener = new OnFastBackwordCompleteListener()
			    {
			        public void onFastBackwordComplete(HiMediaPlayer mp)
			        {
			             
			        }
			    };

			    OnErrorListener mOnErrorListener = new OnErrorListener()
			    {
			        public boolean onError(HiMediaPlayer mp, int what, int extra)
			        {
			             
			            return false;
			        }
			    };
			    
			    private boolean isThreadStart = false;
				private void startThread()
			    {
			        if (!isThreadStart)
			        {
						System.out.println("启动了...");
			            myHandler.sendEmptyMessage(Constants.showMediaController);
			            ctrlBarHandler.post(mThread);
			            isThreadStart = true;
			        }
			        
			    }
				
				
			    OnPreparedListener mPreparedListener = new OnPreparedListener()
			    {
			        public void onPrepared(HiMediaPlayer mp)
			        {
			        	  isThreadStart = false;
			        	  flag = false;
			            start();
			        }
			    };
			    private void start()
			    {
			        videoStart();
			        
			    }

			    private void videoStart()
			    {
			    	  videoView = (HisiVideoView) findViewById(R.id.vide_view);
			        videoView.start();
			    
			    }
			    private HiMediaPlayer.OnCompletionListener mCompletionListener =
			        new HiMediaPlayer.OnCompletionListener() {
			        public void onCompletion(HiMediaPlayer mp) { 
			        	myHandler.sendEmptyMessage(MSG_NEXT_SONG);
			        
			        }
			    };

			    
			    private void nextSong()
				{
					try { } catch (Exception e) {
						e.printStackTrace();
					}
					
				}
}
