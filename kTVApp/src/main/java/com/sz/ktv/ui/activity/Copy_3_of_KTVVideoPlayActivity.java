package com.sz.ktv.ui.activity;

import java.io.File;
import java.io.IOException;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SeekBar;

import com.hisilicon.android.mediaplayer.HiMediaPlayer;
import com.sz.ktv.MainActivity;
import com.sz.ktv.R;
import com.sz.ktv.db.DataBase;
import com.sz.ktv.db.Song;
import com.sz.ktv.view.widget.ScrollTextView;

public class Copy_3_of_KTVVideoPlayActivity extends Activity implements
		OnClickListener, SurfaceHolder.Callback {

	SurfaceView ktvSurfaceView;
	Button volumLeft;
	Button volumRight;
	private HiMediaPlayer ktvMedia;
	private static String currentPath;
	public static final String KTV_SONG = "ktv_song_play";
	public static final String KTV_CURRENT_POS = "ktv_song_curr_pos";
//	private SeekBar ktvSeekBar;
	private static final int PROGRESS_CHANGED = 10;
	private boolean flag = true;
	String zimuTemp = "";
	String zimuGequName;
	private String defaultZimuContent;
	private String currentSongName;
	int currentPos = 0;
	private SeekBar ktvSeekBar;
	private ScrollTextView scrollView;
	private Song currentSong ;
	
    private String m_currentWorkingPath = android.os.Environment
        .getExternalStorageDirectory().getPath()
        + File.separator;
	
	private static final int MSG_ZIMU_SET = 11;
	 int width;
	 int height ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
        requestWindowFeature( Window.FEATURE_NO_TITLE );
       getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN,
                              WindowManager.LayoutParams.FLAG_FULLSCREEN );
       
		setContentView(R.layout.ktv_play_layout);

		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		 if(null != bundle)
		 {
			  currentSong = (Song)bundle.getSerializable(Copy_3_of_KTVVideoPlayActivity.KTV_SONG) ;
			 currentPos = bundle.getInt(Copy_3_of_KTVVideoPlayActivity.KTV_CURRENT_POS);
			 
		 }
		 if(null != currentSong)
		 {
			 
		 }
//		 else {
//			 currentSong = new Song();
//			 currentSong.setSong_name("彩云追月");
//		 }
		 
//		 currentSong.setSong_file_name("caiyunzhuiyue.MKV");
		initView();
	 
		WindowManager wm = this.getWindowManager();
		width= wm.getDefaultDisplay().getWidth();

		height = wm.getDefaultDisplay().getHeight();
	}
 
	private void initView() {

		scrollView = (ScrollTextView)findViewById(R.id.ktv_play_scroll_text);
		ktvSeekBar = (SeekBar)findViewById(R.id.ktv_play_seekbar);
		ktvSurfaceView = (SurfaceView) findViewById(R.id.ktv_play_backgournd);
		volumLeft = (Button) findViewById(R.id.ktv_play_left);
		volumRight = (Button) findViewById(R.id.ktv_play_right);

		volumLeft.setOnClickListener(this);
		volumRight.setOnClickListener(this);

		SurfaceHolder myHolder = ktvSurfaceView.getHolder();// 
		myHolder.addCallback(this);
	
		initData();
		VideoThreed videoThreed = new VideoThreed();
		videoThreed.start();
	}
	Uri uri = null;
	private void initData() {
		String songPath = DataBase.MNT_SDA_SONG_PATH+"60077211.mkv";//+currentSong.getSong_file_name();//"/mnt/sda/sda5/caiyunzhuiyue.MKV";//m_currentWorkingPath+"/3.mp4";
		File file =new File(songPath);
		if(null == file || !file.exists())
		{
			return ;
		}
		uri = Uri.parse(songPath);
		scrollView.setText("");
	}
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		 
        if (keyCode == KeyEvent.KEYCODE_BACK
                 && event.getRepeatCount() == 0) {
        	if(null != ktvMedia )
        	{
             
             if (ktvMedia.isPlaying()) {
     			ktvMedia.stop();
     			ktvMedia.release();
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
			case PROGRESS_CHANGED:
				try {
					int currentPos = ktvMedia.getCurrentPosition();
					System.out.println("当前精度更新: "+ currentPos);
					ktvSeekBar.setProgress(currentPos);
					ktvSeekBar.invalidate();
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

	 
	class VideoThreed extends Thread {
		public void run() {
			while (flag) {
				System.out.println("更新进度---");
				Message message = new Message();
				message.what = PROGRESS_CHANGED;
				myHandler.sendMessage(message);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		}
	}
 
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
		release(true);
		Thread.currentThread().interrupt();
		 
	}

	@Override
	protected void onPause() {
		super.onPause();
//		ktvMedia.pause();

	}

	@Override
	public void onClick(View v) {

		int id = v.getId();
		switch (id) {
		case R.id.ktv_play_left:
			try {
				int current = ktvMedia.getCurrentPosition();
				current = current - 3000;
				ktvMedia.seekTo(current);
				 
			} catch (Exception e) {
			}

			break;
		case R.id.ktv_play_right:
			try {
				int currentr = ktvMedia.getCurrentPosition();
				currentr = currentr + 3000;
				ktvMedia.seekTo(currentr);
				 
			} catch (Exception e) {
			}

			break;

		default:
			break;
		}

	}

	private void playVideo(Uri mUri)
	{
		try {

			if(currentSong == null)
			{
				return ;
			}
			ktvMedia =new HiMediaPlayer();  
			ktvMedia.setOnPreparedListener(mPreparedListener);
			ktvMedia.setOnVideoSizeChangedListener(mSizeChangedListener);
			ktvMedia.setOnBufferingUpdateListener(mBufferingUpdateListener);
			ktvMedia.setOnCompletionListener(mCompletionListener);
			ktvMedia.setDisplay(mSurfaceHolder);//
			
			ktvMedia.setDataSource(Copy_3_of_KTVVideoPlayActivity.this, mUri);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ktvMedia.prepareAsync();
		ktvMedia.start();
		ktvMedia.seekTo(currentPos);
		
	}
	
	SurfaceHolder mSurfaceHolder;
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		mSurfaceHolder = holder;
		try {
			try {
				release(true);
				playVideo(uri);
				
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (IllegalArgumentException e) {
		} catch (SecurityException e) {
		} catch (IllegalStateException e) {
		}

	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int w,
			int h) {
//		ktvSurfaceView.getHolder().setFixedSize(w, h);
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
			
	}
	  HiMediaPlayer.OnVideoSizeChangedListener mSizeChangedListener =
		        new HiMediaPlayer.OnVideoSizeChangedListener() {
		            public void onVideoSizeChanged(HiMediaPlayer mp, int w, int h) {
		               int mVideoWidth = mp.getVideoWidth();
		               int mVideoHeight = mp.getVideoHeight();
		                if (mVideoWidth != 0 && mVideoHeight != 0) {
//		                   ktvSurfaceView.getHolder().setFixedSize(mVideoWidth, mVideoHeight);
		                }
		            }
		    };
		    
		    private void release(boolean cleartargetstate) {
		        if (ktvMedia != null) {
		        	MainActivity.currentPos = ktvMedia.getCurrentPosition();
		            ktvMedia.reset();
		            ktvMedia.release();
		            ktvMedia = null;
		             
		        }
		    }
		  
		  
		   
			    HiMediaPlayer.OnPreparedListener mPreparedListener = new HiMediaPlayer.OnPreparedListener() {
			        public void onPrepared(HiMediaPlayer mp) {
			        	 
			        	ktvMedia.start();
			        	ktvSeekBar.setMax(ktvMedia.getDuration());
						ktvSeekBar.setProgress(0);
			        	
			        }
			    };

			    private static final int MSG_NEXT_SONG = 1100;
			    
			    private HiMediaPlayer.OnCompletionListener mCompletionListener =
			        new HiMediaPlayer.OnCompletionListener() {
			        public void onCompletion(HiMediaPlayer mp) { 
			        	myHandler.sendEmptyMessage(MSG_NEXT_SONG);
			        
			        }
			    };

			    private HiMediaPlayer.OnErrorListener mErrorListener =
			        new HiMediaPlayer.OnErrorListener() {
			        public boolean onError(HiMediaPlayer mp, int framework_err, int impl_err) { 
			        	return true;
			        }
			    };

			    private HiMediaPlayer.OnBufferingUpdateListener mBufferingUpdateListener =
			        new HiMediaPlayer.OnBufferingUpdateListener() {
			        public void onBufferingUpdate(HiMediaPlayer mp, int percent) {
			        	System.out.println("ktv进度---- "+ percent);
			        }
			    };
			    
			    
			    private void nextSong()
				{
					try {
						List<Song> yidianList =null;
						
						if(DataBase.isLoginSuccess)
						{
							//如果登录了，首先播放已点歌曲
						
					    yidianList = DataBase.getInstance().getYiDianList();
							
						if(yidianList.size()<=0){
							 
							yidianList = DataBase.getGongBoSongList(this);
						}
						if(null == yidianList || yidianList.size()<=0)
						{
							return ;
						}
						Song song = yidianList.get(0);
						//当前正在播放的歌曲...
						currentSong = song; 
						zimuGequName = getString(R.string.ktv_current_play) + "：" + song.getSong_name();
						String fileName = song.getSong_file_name();
						
						String path =DataBase.MNT_SDA_SONG_PATH+fileName;
						
						File file = new File(path);
						//如果歌曲不存在则停止播放
						if(null == file || !file.exists())
						{
							yidianList.remove(0);
						}else {
							Uri uri = Uri.parse(path);
							release(true);
							playVideo(uri);
						}
						}else {
							//没有登录则播放公播歌曲
							List<Song> gongboList = DataBase.getGongBoSongList(this);
							if(null == gongboList || gongboList.size()<=0)
							{
								return ;
							}
							Song song = gongboList.get(0);
							
							currentSong = song; 
							
							zimuGequName = "当前播放：" + song.getSong_name();
							String fileName = song.getSong_file_name();
							 scrollView.setText(zimuGequName);
							
							String path =DataBase.MNT_SDA_SONG_PATH+fileName;
							//设置播放的URL
							Uri uri = Uri.parse(path);
							gongboList.remove(0);
							release(true);
							playVideo(uri);
							
						}
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
}
