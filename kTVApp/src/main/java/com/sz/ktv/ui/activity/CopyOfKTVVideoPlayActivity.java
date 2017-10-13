//package com.sz.ktv.ui.activity;
//
//import java.io.File;
//import java.util.List;
//
//import org.greenrobot.eventbus.EventBus;
//import org.greenrobot.eventbus.Subscribe;
//import org.greenrobot.eventbus.ThreadMode;
//
//import android.content.Intent;
//import android.media.AudioManager;
//import android.media.MediaPlayer;
//import android.net.Uri;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.view.KeyEvent;
//import android.view.SurfaceHolder;
//import android.view.SurfaceView;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.Button;
//import android.widget.SeekBar;
//
//import com.sz.ktv.MainActivity;
//import com.sz.ktv.R;
//import com.sz.ktv.application.MyApplication;
//import com.sz.ktv.bean.SongBean;
//import com.sz.ktv.db.DataBase;
//import com.sz.ktv.db.Song;
//import com.sz.ktv.msg.ZimuMessage;
//import com.sz.ktv.ui.base.BaseActivity;
//import com.sz.ktv.util.ZiMuUtil;
//import com.sz.ktv.view.widget.ScrollTextView;
//
//public class CopyOfKTVVideoPlayActivity extends BaseActivity implements
//		OnClickListener, SurfaceHolder.Callback {
//
//	ScrollTextView topTitle;
//	SurfaceView ktvSurfaceView;
//	Button volumLeft;
//	Button volumRight;
//	Song song;
//	private MediaPlayer ktvMedia;
//	private static String currentPath;
//	public static final String KTV_SONG = "ktv_song_play";
//	public static final String KTV_CURRENT_POS = "ktv_song_curr_pos";
//	private SeekBar ktvSeekBar;
//	private static final int PROGRESS_CHANGED = 10;
//	private boolean flag = true;
//	String zimuTemp = "";
//	String zimuGequName;
//	private String defaultZimuContent;
//	private String currentSongName;
//	int currentPos = 0;
//	
//	static {
//		currentPath = MyApplication.getInstance().getSavePath()+"/mkv/";
//	}
//
//	
//	private static final int MSG_ZIMU_SET = 11;
// 
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.ktv_play_layout);
//
//		Intent intent = getIntent();
//		Bundle bundle = intent.getExtras();
//		if (null != bundle) {
//			song = (Song) bundle.getSerializable(KTV_SONG);
//			currentPos = (int)bundle.getInt(KTV_CURRENT_POS);
//		}
//		initView();
//		EventBus.getDefault().register(this);
//
//		defaultZimuContent = ZiMuUtil.getZimuContent();
//		currentSongName = "";
//		
//		zimuGequName = getString(R.string.ktv_current_play) + "：" + currentSongName;
//		topTitle.setText(defaultZimuContent + "  "+zimuGequName);
//		
//	}
//	@Subscribe(threadMode = ThreadMode.ASYNC)
//	public void onAsyncEventBus(ZimuMessage msg) {
//		String zimuMsg = msg.getMessage();
//		Message messages = myHandler.obtainMessage();
//		messages.obj = zimuMsg;
//		messages.what = MSG_ZIMU_SET;
//		myHandler.sendMessage(messages);
//
//	}
//	private void initView() {
//
//		topTitle = (ScrollTextView) findViewById(R.id.ktv_play_scroll_text);
//		ktvSurfaceView = (SurfaceView) findViewById(R.id.ktv_play_backgournd);
//		volumLeft = (Button) findViewById(R.id.ktv_play_left);
//		volumRight = (Button) findViewById(R.id.ktv_play_right);
//		ktvSeekBar = (SeekBar) findViewById(R.id.ktv_play_seekbar);
//
//		volumLeft.setOnClickListener(this);
//		volumRight.setOnClickListener(this);
//
//		SurfaceHolder myHolder = ktvSurfaceView.getHolder();// 
//		myHolder.addCallback(this);
//		
//		initData();
//		VideoThreed videoThreed = new VideoThreed();
//		videoThreed.start();
//	}
//
//	private void initData() {
//
//		Uri uri = null;
//		
//		if (null != song) {
//			String fileName = song.getSongFileName();
//			currentSongName = ""+fileName;
//			String songPath = currentPath + "/" + fileName;
//			File file =new File(songPath);
//			if(null == file || !file.exists())
//			{
//				return ;
//			}
//			uri = Uri.parse(songPath);
//			ktvMedia = MediaPlayer.create(KTVVideoPlayActivity.this, uri);
//			
//		} else {
//			 
//		}
//	
//		
//		ktvMedia.seekTo(currentPos);
//		ktvSeekBar.setMax(ktvMedia.getDuration());
//		ktvSeekBar.setProgress(currentPos);
//		zimuGequName = "当前播放：" + currentSongName;
//		topTitle.setText(defaultZimuContent + "  "+zimuGequName);
//		
//
//	}
//
//	private void nextSong()
//	{
//		try {
//			List<SongBean> yidianList =null;
//			
//			if(DataBase.isLoginSuccess)
//			{
//				//如果登录了，首先播放已点歌曲
//			
//		    yidianList = DataBase.getInstance().getYiDianList();
//				
//			if(yidianList.size()<=0){
//				 
//				yidianList = DataBase.getGongBoSongList(this);
//			}
//			SongBean song = yidianList.get(0);
//		 
//			zimuGequName = "当前播放：" + song.getSongName();
//			String fileName = song.getSongFileName();
//			
//			topTitle.setText(defaultZimuContent + "  "+zimuGequName);
//			 
//			topTitle.invalidate();
//			
//			String path = currentPath +  "/mkv/"+fileName;
//			File file =new File(path);
//			if(null == file || !file.exists())
//			{
//				return ;
//			}
//			Uri uri = Uri.parse(path);
//			ktvMedia = MediaPlayer.create(this, uri);
// 
//			yidianList.remove(0);
//			
//			
//			}else {
//				//没有登录则播放公播歌曲
//				List<SongBean> gongboList = DataBase.getGongBoSongList(this);
//				SongBean song = gongboList.get(0);
//				 
//				
//				zimuGequName = "当前播放：" + song.getSongName();
//				String fileName = song.getSongFileName();
//				
//				topTitle.setText(defaultZimuContent + "  "+zimuGequName);
//				topTitle.invalidate();
//				
//				String path = currentPath + "/mkv/"+fileName;
//				Uri uri = Uri.parse(path);
//				ktvMedia = MediaPlayer.create(this, uri);
// 
//				gongboList.remove(0);
//				
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//	}
//	
//	public boolean onKeyDown(int keyCode, KeyEvent event) {
//		 
//        if (keyCode == KeyEvent.KEYCODE_BACK
//                 && event.getRepeatCount() == 0) {
//        	if(null != ktvMedia )
//        	{
//             MainActivity.currentPos = ktvMedia.getCurrentPosition();
//             if (ktvMedia.isPlaying()) {
//     			ktvMedia.stop();
//     			ktvMedia.release();
//     		}
//        	}
//             finish();
//             return true;
//         }
//         return super.onKeyDown(keyCode, event);
//     }
//	// 更新UI
//	Handler myHandler = new Handler() {
//		public void handleMessage(Message msg) {
//			switch (msg.what) {
//			case PROGRESS_CHANGED:
//				try {
//					ktvSeekBar.setProgress(ktvMedia.getCurrentPosition());
//				} catch (Exception e) {
//				}
//				
//				break;
//			case MSG_ZIMU_SET:
//				zimuTemp = (String) msg.obj;
//				String zimuText = zimuTemp + "  " + zimuGequName;
//				topTitle.setText(zimuText);
//				topTitle.invalidate();
//				break;
//			}
//		}
//	};
//
//	// 视频进度条更新
//	class VideoThreed extends Thread {
//		public void run() {
//			while (!Thread.currentThread().isInterrupted()) {
//				if (!flag) {
//					return;
//				}
//				Message message = new Message();
//				message.what = PROGRESS_CHANGED;
//				myHandler.sendMessage(message);
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//					Thread.currentThread().interrupt();
//				}
//			}
//		}
//	}
//
//	@Override
//	protected void onResume() {
//		super.onResume();
////		ktvMedia.start();
//	}
//
//	@Override
//	protected void onDestroy() {
//		super.onDestroy();
//		
//		flag = false;
//		Thread.currentThread().interrupt();
//		EventBus.getDefault().unregister(this);
//	}
//
//	@Override
//	protected void onPause() {
//		super.onPause();
////		ktvMedia.pause();
//
//	}
//
//	@Override
//	public void onClick(View v) {
//
//		int id = v.getId();
//		switch (id) {
//		case R.id.ktv_left:
//			try {
//				int current = ktvMedia.getCurrentPosition();
//				current = current - 3000;
//				ktvMedia.seekTo(current);
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
//			
//
//			break;
//		case R.id.ktv_right:
//			try {
//				int currentr = ktvMedia.getCurrentPosition();
//				currentr = currentr + 3000;
//				ktvMedia.seekTo(currentr);
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
//			
//
//			break;
//
//		default:
//			break;
//		}
//
//	}
//
//	@Override
//	public void surfaceCreated(SurfaceHolder holder) {
//		try {
//			if(null != ktvMedia)
//			{
//			ktvMedia.setDisplay(holder);//
//			ktvMedia.setAudioStreamType(AudioManager.STREAM_MUSIC);//
//			ktvMedia.setLooping(true);
//			ktvMedia.start();
//			ktvMedia.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//				@Override
//				public void onCompletion(MediaPlayer mp) {
//					mp.stop();
//					mp.release();
//					nextSong();
//					mp.seekTo(0);
//					mp.start();
//				}
//			});
//			
//			}
//
//		} catch (IllegalArgumentException e) {
//		} catch (SecurityException e) {
//		} catch (IllegalStateException e) {
//		}
//
//	}
//
//	@Override
//	public void surfaceChanged(SurfaceHolder holder, int format, int width,
//			int height) {
//
//	}
//
//	@Override
//	public void surfaceDestroyed(SurfaceHolder holder) {
//
//	}
//
//}
