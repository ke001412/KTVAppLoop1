package com.sz.ktv;

import java.io.File;
import java.util.List;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hisilicon.android.mediaplayer.HiMediaPlayer;
import com.hisilicon.android.mediaplayer.HiMediaPlayer.OnErrorListener;
import com.hisilicon.android.mediaplayer.HiMediaPlayer.OnFastBackwordCompleteListener;
import com.hisilicon.android.videoplayer.view.HisiVideoView;
import com.sz.ktv.application.MyApplication;
import com.sz.ktv.db.DataBase;
import com.sz.ktv.db.Song;
import com.sz.ktv.db.ktv.SongUtil;
import com.sz.ktv.msg.AsyncMessage;
import com.sz.ktv.msg.YiDianMessage;
import com.sz.ktv.msg.ZimuMessage;
import com.sz.ktv.net.service.ConnectService;
import com.sz.ktv.ui.activity.KTVVideoPlayActivity;
import com.sz.ktv.ui.base.BaseFragmentActivity;
import com.sz.ktv.ui.fragment.HomeGeMingFragment;
import com.sz.ktv.util.FragmentFactory;
import com.sz.ktv.util.Global;
import com.sz.ktv.util.LanguageUtil;
import com.sz.ktv.util.ZiMuUtil;
import com.sz.ktv.view.HomeCloudyWindow;
import com.sz.ktv.view.HomeCloudyWindow.PopuwindowCloudyClickListener;
import com.sz.ktv.view.HomeMoreWindow;
import com.sz.ktv.view.HomeMoreWindow.PopuwindowClickListener;
import com.sz.ktv.view.HomeMsgWindow;
import com.sz.ktv.view.HomeQiFenWindow;
import com.sz.ktv.view.HomeQiFenWindow.PopuwindowQiFenClickListener;
import com.sz.ktv.view.HomeDownloadWindow;
import com.sz.ktv.view.HomeSearchWindow;
import com.sz.ktv.view.HomeWiFiDianGeWindow;
import com.sz.ktv.view.HomeYiDianWindow;
import com.sz.ktv.view.MoreTiaoYinWindow;
import com.sz.ktv.view.QiFenGaoGuaiWindow;
import com.sz.ktv.view.QiFenXianHuaWindow;
import com.sz.ktv.view.dialog.LanguageSetDialog;
import com.sz.ktv.view.listener.FlowerListener;
import com.sz.ktv.view.listener.GaoGuaiListener;
import com.sz.ktv.view.listener.YuYanListener;
import com.sz.ktv.view.listener.YunFuWquListener;
import com.sz.ktv.view.widget.ScrollTextView;

public class MainActivity extends BaseFragmentActivity implements
		OnClickListener, PopuwindowClickListener,PopuwindowQiFenClickListener,PopuwindowCloudyClickListener{

	private Fragment currentFragment;
	
	
//	private SurfaceView backgroundSur;
//	private SurfaceView bottomSur;

//	private SurfaceView bottomSurfaceView;
//	private HiMediaPlayer bottomMedia;
	HisiVideoView bottomVideoView;
//	private MediaPlayer bottomMedia;
	private static String currentPath = "";
	private int position = 0;

	private TextView homeYiDianCountTv;
	
	RelativeLayout shoyeLayout;
	RelativeLayout qiegeLayout;
	RelativeLayout chongboLayout;
	RelativeLayout banchangLayout;
	RelativeLayout yinjianLayout;
	RelativeLayout zantingLayout;

	RelativeLayout yinjiaLayout;
	RelativeLayout fangyinLayout;
	RelativeLayout qifenLayout;
	RelativeLayout gengduoLayout;
	RelativeLayout daohangLayout;
	RelativeLayout yidianLayout;

	ImageView banchangImg;
	ImageView fangyinImg;

	TextView banchangText;
	TextView fangyinText;

	View homeBottomLayout;

	ScrollTextView scrollText;

	String zimuTemp = "";
	String zimuGequName="";
	
	LinearLayout homeTopMsg;
	LinearLayout homeTopYun;
	LinearLayout homeTopWifiDianGe;
	
	RelativeLayout topLayout;
	
	static {
		currentPath = MyApplication.getInstance().getSavePath();
	}
	private static final int MSG_SWITCH_FRAGMENT = 10;
	private static final int MSG_ZIMU_SET = MSG_SWITCH_FRAGMENT + 1;
	private static final int MSG_YIDIAN_REFRESH = MSG_ZIMU_SET+1;
	
	Handler myHandler = new Handler() {
		public void dispatchMessage(android.os.Message msg) {

			int what = msg.what;
			switch (what) {
			case MSG_YIDIAN_REFRESH:
				int size = DataBase.getInstance().getYiDianSongListSize();
				homeYiDianCountTv.setText(""+size);
				System.out.println("更新已经点的歌曲。。。。。。。。");
				if(null == currentSong)
				{
				  nextSong();
				}
				break;
			case MSG_SWITCH_FRAGMENT:
				int id = msg.arg1;
//				if (getSupportFragmentManager().getFragments() != null
//						&& getSupportFragmentManager().getFragments().size() > 0) {
//						getSupportFragmentManager().getFragments().clear();
//				}
//				Fragment fragmentTemp = fragmentManager.findFragmentByTag(currentFragmentId+"");
//				fragmentTemp.onDestroyView();
				
				Fragment fragment = FragmentFactory.createFragment(id);
				switchFragment(fragment,id);
				break;
			case MSG_ZIMU_SET:
				zimuTemp = (String) msg.obj;
				String zimuText = zimuTemp + "  " + zimuGequName;
				scrollText.setText(zimuText);
				scrollText.invalidate();
				break;
			default:
				break;
			}
		};
	};

	private String defaultZimuContent;
	private String currentSongName;
	private RelativeLayout home_container;
	private TextView tvSearch;
	
	private View homeTop;
	private View homeBottom;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

//		HomeGexingFragment.getInstance();
//		HomeGeMingFragment.getInstance();
//		
		setContentView(R.layout.activity_main);
		Global.currentActivity = MainActivity.this;
		initData();
		initView();
		
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
		
		initEventBus();
		
		currentFragmentId = FragmentFactory.FRAGMENT_HOME_CENTER;
		
		defaultZimuContent = ZiMuUtil.getZimuContent();
		currentSongName = "";
		
//		zimuGequName = "当前播放：" + currentSongName;
		scrollText.setText(defaultZimuContent + "  "+zimuGequName);
		scrollText.invalidate();
		try {
			home_container = (RelativeLayout)findViewById(R.id.home_container_);
			homeTop = findViewById(R.id.home_top_layout);
			homeBottom = findViewById(R.id.home_bottom_container);
			
//			mst.adjustView(homeTop);
//			mst.adjustView(homeBottom);
			
			Global.homeLayout = home_container;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		currentFragment = FragmentFactory
				.createFragment(FragmentFactory.FRAGMENT_HOME_CENTER);
		FragmentTransaction transaction = getSupportFragmentManager()
				.beginTransaction();
		transaction.add(R.id.home_center_content, currentFragment);
		transaction.commit();
		
	}

	private void initEventBus() {
		EventBus.getDefault().register(this);

	}

	@Subscribe(threadMode = ThreadMode.ASYNC)
	public void onAsyncEventBus(AsyncMessage msg) {
		int message = msg.getMessage();
		Message messages = myHandler.obtainMessage();
		messages.arg1 = message;
		messages.what = MSG_SWITCH_FRAGMENT;
		myHandler.sendMessage(messages);

	}

	@Subscribe(threadMode = ThreadMode.ASYNC)
	public void onAsyncEventBus(ZimuMessage msg) {
		String zimuMsg = msg.getMessage();
		Message messages = myHandler.obtainMessage();
		messages.obj = zimuMsg;
		messages.what = MSG_ZIMU_SET;
		myHandler.sendMessage(messages);

	}
	
	@Subscribe(threadMode = ThreadMode.ASYNC)
	public void onAsyncEventBus(YiDianMessage msg) {
		Message messages = myHandler.obtainMessage();
		messages.what = MSG_YIDIAN_REFRESH;
		myHandler.sendMessage(messages);

	}
 
	  
	  
	  Uri mUri = null;
		private void initData() {
			String songPath = DataBase.MNT_SDA_SONG_PATH+"caiyunzhuiyue.MKV";//m_currentWorkingPath+"/3.mp4";
			File file =new File(songPath);
			if(null == file || !file.exists())
			{
				return ;
			}
			mUri = Uri.parse(songPath);
		 
		}
		HiMediaPlayer.OnVideoSizeChangedListener mSizeChangedListener =
		        new HiMediaPlayer.OnVideoSizeChangedListener() {
		            public void onVideoSizeChanged(HiMediaPlayer mp, int w, int h) {
//		               int mVideoWidth = mp.getVideoWidth();
//		               int mVideoHeight = mp.getVideoHeight();
//		            	int ww = bottomSurfaceView.getWidth();
//		            	int hh = bottomSurfaceView.getHeight();
//		                if (mVideoWidth != 0 && mVideoHeight != 0) {
//		                   bottomSurfaceView.getHolder().setFixedSize(ww, hh);
//		                   bottomMedia.setVideoRange(0, 0, ww, hh);
//		                }
		            }
		    };
		 
		  
		  
		   
			    HiMediaPlayer.OnPreparedListener mPreparedListener = new HiMediaPlayer.OnPreparedListener() {
			        public void onPrepared(HiMediaPlayer mp) {
			        	 System.out.println("开始播放...");
			        	 start();
			        }
			    };

			    private HiMediaPlayer.OnCompletionListener mCompletionListener =
			        new HiMediaPlayer.OnCompletionListener() {
			        public void onCompletion(HiMediaPlayer mp) { 
			        	currentSong = null;
			        	nextSong();
			        }
			    };
			   
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
			    
			    private HiMediaPlayer.OnBufferingUpdateListener mBufferingUpdateListener =
			        new HiMediaPlayer.OnBufferingUpdateListener() {
			        public void onBufferingUpdate(HiMediaPlayer mp, int percent) {
			        	System.out.println(" ###### 进度！！########## "+ percent);
			        }
			    };
 
		
	  Runnable checkPoisitionRun = new Runnable() {
		
		@Override
		public void run() { 
			
		}
	};
	  
	int currentDuatrion = 0;
	
//	SurfaceHolder mBottomSurfaceHolder ;
	
	 
	private void initView() {

		
		homeYiDianCountTv = (TextView)findViewById(R.id.home_yidian_count);
		homeYiDianCountTv.setText("0");
		
		topLayout = (RelativeLayout)findViewById(R.id.home_top_layout);
		
		homeTopMsg = (LinearLayout)findViewById(R.id.home_top_msg);
		homeTopYun = (LinearLayout)findViewById(R.id.home_top_cloud_server);
		homeTopWifiDianGe = (LinearLayout)findViewById(R.id.home_top_wifi_chose_song);
		
		homeTopWifiDianGe.setOnClickListener(this);
		homeTopYun.setOnClickListener(this);
		homeTopMsg.setOnClickListener(this);
		
//		backgroundSur = (SurfaceView) findViewById(R.id.home_media_backgournd);
 
		bottomVideoView = (HisiVideoView)findViewById(R.id.home_bottom_center_video);
		bottomVideoView.setOnPreparedListener(mPreparedListener);
		bottomVideoView.setOnCompletionListener(mCompletionListener);
		bottomVideoView.setOnErrorListener(mOnErrorListener);
		bottomVideoView.setOnFastBackwordCompleteListener(mFastBackwordCompleteListener);
//		bottomVideoView.setVideoScale(125, 145);
//		LayoutParams lp = bottomSurfaceView.getLayoutParams();
//        lp.width = 50;
//         lp.height =50;
//         bottomSurfaceView.setLayoutParams(lp);
	
		shoyeLayout = (RelativeLayout) findViewById(R.id.home_shouye);
		qiegeLayout = (RelativeLayout) findViewById(R.id.home_qiege);
		chongboLayout = (RelativeLayout) findViewById(R.id.home_chongbo);
		banchangLayout = (RelativeLayout) findViewById(R.id.home_banchang);
		yinjianLayout = (RelativeLayout) findViewById(R.id.home_yinjian);
		zantingLayout = (RelativeLayout) findViewById(R.id.home_zanting);

		yinjiaLayout = (RelativeLayout) findViewById(R.id.home_yinjia);
		fangyinLayout = (RelativeLayout) findViewById(R.id.home_fangyin);
		qifenLayout = (RelativeLayout) findViewById(R.id.home_qifen);
		gengduoLayout = (RelativeLayout) findViewById(R.id.home_gengduo);
		daohangLayout = (RelativeLayout) findViewById(R.id.home_daohang);
		yidianLayout = (RelativeLayout) findViewById(R.id.home_yidian);
		Global.yidianLayout = yidianLayout;
		
		banchangImg = (ImageView) findViewById(R.id.home_banchang_img);
		fangyinImg = (ImageView) findViewById(R.id.home_fanyin_img);

		banchangText = (TextView) findViewById(R.id.home_banchang_text);
		fangyinText = (TextView) findViewById(R.id.home_fangyin_text);

		shoyeLayout.setOnClickListener(this);
		qiegeLayout.setOnClickListener(this);
		chongboLayout.setOnClickListener(this);
		banchangLayout.setOnClickListener(this);
		banchangLayout.setTag("bc");
		yinjianLayout.setOnClickListener(this);
		zantingLayout.setOnClickListener(this);

		yinjiaLayout.setOnClickListener(this);
		fangyinLayout.setOnClickListener(this);
		fangyinLayout.setTag("fy");

		qifenLayout.setOnClickListener(this);
		gengduoLayout.setOnClickListener(this);
		daohangLayout.setOnClickListener(this);
		yidianLayout.setOnClickListener(this);

		homeBottomLayout = (View) findViewById(R.id.home_bottom_layout);

		scrollText = (ScrollTextView) findViewById(R.id.scroll_text);
//		scrollText.setSpeed(1500);
		tvSearch = (TextView)findViewById(R.id.home_top_search);
		tvSearch.setOnClickListener(this);
	 
		bottomVideoView.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
		 
				if(null !=bottomVideoView)
				{
					currentPos = bottomVideoView.getCurrentPosition();
					bottomVideoView.pause();
				}
				Intent intent = new Intent(MainActivity.this,KTVVideoPlayActivity.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable(KTVVideoPlayActivity.KTV_SONG, currentSong);
				bundle.putInt(KTVVideoPlayActivity.KTV_CURRENT_POS, currentPos);
				intent.putExtras(bundle);
				startActivity(intent);
				
				return false;
			}
		});
	}
 
	private void switchFragment(Fragment targetFragment,int fragmentId) {
		FragmentTransaction transaction = getSupportFragmentManager()
				.beginTransaction();
//		     Fragment2 fragment2 = new Fragment2();  
		     transaction.replace(R.id.home_center_content, targetFragment);  
		     transaction.commit(); 

//		 
//		if (!targetFragment.isAdded()) {
//			transaction.hide(currentFragment)
//					.add(R.id.home_center_content, targetFragment,""+fragmentId).commit();
//
//		} else {
//			
//			transaction.hide(currentFragment).show(targetFragment).commit();
//
//		}
// 
//		
		currentFragment = targetFragment;
//		
		currentFragmentId = fragmentId;
		
	}

  
	private void nextSong()
	{
		try {
			if(null != currentSong)
			{
				return ;
			}
			List<Song> yidianList =null;
			Song song = null;
			if(DataBase.isLoginSuccess)
			{
				//如果登录了，首先播放已点歌曲
			
		    yidianList = DataBase.getInstance().getYiDianList();
				
			if(yidianList.size()<=0){
				 
				yidianList = DataBase.getGongBoSongList(this);
				song = DataBase.getInstance().getGongBoSong();
				
			}else {

				song = DataBase.getInstance().getYiDianSong();
			}
			if(null == song)
			{
				scrollText.setText("");
				return ;
			}
			 
			
//			song.setSong_file_name("60077211.mkv");
			String fileName = song.getSong_file_name();
			String songName = song.getSong_name();
			//当前正在播放的歌曲...
			
			String path =DataBase.MNT_SDA_SONG_PATH+fileName;
			File file = new File(path);
			 
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					int size = DataBase.getInstance().getYiDianSongListSize();
					homeYiDianCountTv.setText(""+size);
				}
			});
		 
			
			//如果歌曲不存在则停止播放
			if(  file.exists())
			{
				 
				currentSong = song; 
				System.out.println("已经点歌播放路径: "+ path);
				bottomVideoView.setVideoPath(path);
//				start();
				zimuGequName = getString(R.string.ktv_current_play) + "：" +songName;
				scrollText.setText(defaultZimuContent + "  "+zimuGequName);
				scrollText.invalidate();
			}
			}else {
				//没有登录则播放公播歌曲
				List<Song> gongboList = DataBase.getGongBoSongList(this);
				if(null == gongboList || gongboList.size()<=0)
				{
					return ;
				}
				song = DataBase.getInstance().getGongBoSong();
				if(null == song)
				{
					scrollText.setText("");
					return ;
				}
//				song.setSong_file_name("60077211.mkv");
				zimuGequName = "当前播放：" + song.getSong_name();
				String fileName = song.getSong_file_name();
				String path =DataBase.MNT_SDA_SONG_PATH+fileName;
				File file = new File(path);
				runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						int size =  DataBase.getGongBoSongList(MainActivity.this).size();
						homeYiDianCountTv.setText(""+size);
					}
				});
				
				//如果歌曲不存在则停止播放
				if(  file.exists())
				{
					currentSong = song; 
					
					//设置播放的URL
					 
					bottomVideoView.setVideoPath(path);
//					start();
					 
					scrollText.setText(defaultZimuContent + "  "+zimuGequName);
					scrollText.invalidate();
				}
			
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	 private void start()
	    {
	        videoStart();
	        
	    }

	    private void videoStart()
	    {
	    	bottomVideoView.start();
	    	bottomVideoView.seekTo(currentPos);
//	    	bottomVideoView = (HisiVideoView) findViewById(R.id.ktv_play_backgournd);
	    	int width = bottomVideoView.getWidth();
	        int height = bottomVideoView.getHeight();
	        
	    }

	@Override
	protected void onDestroy() {
		super.onDestroy();
//		if (bottomMedia.isPlaying()) {
//			bottomMedia.stop();
//		}
		bottomVideoView.stopPlayback();
	Log.d("wuming", "MainActivity onDestroy().");
		//@wuming - stop net Service
		Intent intent = new Intent(MainActivity.this, ConnectService.class);
		stopService(intent);
		Log.d("wuming", "stop ConnectService.");

		EventBus.getDefault().unregister(this);
	}

	public static int currentPos = 0;
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
//		backgroundMedia.start();
		LanguageUtil.setDefaultLanguage(this);
		if(null != currentSong)
		{
			String fileName = currentSong.getSong_file_name();
			//当前正在播放的歌曲...
			String path =DataBase.MNT_SDA_SONG_PATH+fileName;
			File file = new File(path);
			if(file.exists())
			{
				bottomVideoView.setVideoPath(path);
				
			}
			
			
//			bottomVideoView.start();
//			bottomMedia.seekTo(currentPos);
//			bottomMedia.start();
		}
//		videoView.seekTo(currentPos);
//		videoView.start();

		Log.d("wuming", "MainActivity onResume().");
		//@wuming - start net Service
		Intent intent = new Intent(MainActivity.this, ConnectService.class);
		startService(intent);
		Log.d("wuming", "start ConnectService.");
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
//		bottomMedia.pause();
		bottomVideoView.pause();
	}
	
	public static boolean gexingHidden = true;
	public static Song currentSong = null;
	private HomeMoreWindow moreWindow  ;
	HomeQiFenWindow qifen;
	
	@Override
	public void onClick(View v) {
		super.onClick(v);
		int id = v.getId();
		switch (id) {
		case R.id.home_top_search:
			if(gexingHidden)
			{ 
			gotoGeming();
			}
			HomeSearchWindow search = new HomeSearchWindow(MainActivity.this);
			search.showPopupWindow(homeBottomLayout);
			
			break;
		case R.id.home_bottom_center_video:
//			int currentPos =  videoView.getCurrentPosition();//bottomMedia.getCurrentPosition();
////			bottomMedia.pause();
//			 
//			
//			Intent intent = new Intent(MainActivity.this,KTVVideoPlayActivity.class);
//			Bundle bundle = new Bundle();
//			bundle.putSerializable(KTVVideoPlayActivity.KTV_SONG, currentSong);
//			bundle.putInt(KTVVideoPlayActivity.KTV_CURRENT_POS, currentPos);
//			intent.putExtras(bundle);
//			startActivity(intent);
 
			
			FragmentFactory.mFragments.clear();
			break;
		case R.id.home_top_wifi_chose_song:
//			HomeWifiDianGeDialog wifiDialog = new HomeWifiDianGeDialog(MainActivity.this);
//			wifiDialog.show();
//			
			HomeWiFiDianGeWindow wifiWindow = new HomeWiFiDianGeWindow(MainActivity.this);
			wifiWindow.showPopupWindow(topLayout);
			break;
		case R.id.home_top_cloud_server:
			
			HomeCloudyWindow yun = new HomeCloudyWindow(MainActivity.this);
			yun.showPopupWindow(topLayout);
			yun.setPopuwindowQiFenClickListener(this);
			
			break;
		case R.id.home_top_msg:
			HomeMsgWindow msg = new HomeMsgWindow(MainActivity.this);
			msg.showPopupWindow(topLayout);
			
			break;
		case R.id.home_shouye:
			EventBus.getDefault().post(
					new AsyncMessage(FragmentFactory.FRAGMENT_HOME_CENTER));
			break;
		case R.id.home_qiege:
			currentSong = null;
			nextSong();
//			try {
//				videoView.stopPlayback();
////				nextSong();
//				videoView.start();
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
//			try {
//				bottomMedia.stop();
//				bottomMedia.reset();
//				nextSong();
//				bottomMedia.prepare();
//				bottomMedia.start();
//			} catch (Exception e) {
//				Write.debug("切歌异常..."+e.getMessage());
//			}
		
			break;
		case R.id.home_zanting:
			if(bottomVideoView.isPlaying())
			{
			bottomVideoView.pause();
			}else {
				bottomVideoView.start();
			}
			break;
		case R.id.home_chongbo:
			bottomVideoView.seekTo(0);
			bottomVideoView.start();
			break;
		case R.id.home_banchang:
			String tag = (String) banchangLayout.getTag();
			if ("bc".equals(tag)) {
				banchangText.setText(getString(R.string.home_button_yuanchang));
				banchangImg
						.setBackgroundResource(R.drawable.home_yuanchang_button);
				banchangLayout.setTag("yc");
			} else {
				banchangImg
						.setBackgroundResource(R.drawable.home_banchang_button);
				banchangLayout.setTag("bc");
				banchangText.setText(getString(R.string.home_button_banchang));

			}

			break;
		case R.id.home_yinjian:

			break;

		case R.id.home_yinjia:

			break;
		case R.id.home_fangyin:
			String tagF = (String) fangyinLayout.getTag();
			if ("fy".equals(tagF)) {
				fangyinText.setText(getString(R.string.home_button_jingyin));
				fangyinImg
						.setBackgroundResource(R.drawable.home_jingyin_button);
				fangyinLayout.setTag("jy");
			} else {
				fangyinImg.setBackgroundResource(R.drawable.home_fanyin_button);
				fangyinLayout.setTag("fy");
				fangyinText.setText(getString(R.string.home_button_fangyin));
			}
			break;
		case R.id.home_qifen:
			qifen = new HomeQiFenWindow(MainActivity.this);
			qifen.showPopupWindow(homeBottomLayout);
			 qifen.setPopuwindowQiFenClickListener(this);
			break;
		case R.id.home_gengduo:
			moreWindow= new HomeMoreWindow(MainActivity.this);
			moreWindow.showPopupWindow(homeBottomLayout);
			moreWindow.setPopuwindowClickListener(this);
			break;
		case R.id.home_daohang:
			EventBus.getDefault().post(
					new AsyncMessage(FragmentFactory.FRAGMENT_HOME_DAOHANG));
			break;
		case R.id.home_yidian:
			
			HomeYiDianWindow yidianWindow = new HomeYiDianWindow(MainActivity.this);
			yidianWindow.showPopupWindow(homeBottomLayout);
			
			break;

		default:
			break;
		}
	}

	@Override
	public void tiaoyinSet() {
		moreWindow = null;
		MoreTiaoYinWindow window = new MoreTiaoYinWindow(MainActivity.this);
		window.showPopupWindow(homeBottomLayout);
		
	}

	public static boolean homeWifiClick = false;
	
	@Override
	public void wifiSet() {
		moreWindow = null;
		
		homeWifiClick = true;
		EventBus.getDefault().post(
				new AsyncMessage(FragmentFactory.FRAGMENT_SET_WIFI_CONFIG));
		
	}

 
	@Override
	public void yuyanSet() {
		moreWindow = null;
		LanguageSetDialog dialog = new LanguageSetDialog(MainActivity.this);
		dialog.show();
		dialog.setCanceledOnTouchOutside(true);
		dialog.setPopuwindowYuYanClickListener(new YuYanListener(MainActivity.this));
		
	}

	@Override
	public void qiFenZhangShengClick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void qiFenKouShaoClick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void qiFenXianHuaClick() {
		QiFenXianHuaWindow qifenXianHua = new QiFenXianHuaWindow(MainActivity.this);
		qifenXianHua.showPopupWindow(homeBottomLayout);
		qifenXianHua.setPopuwindowflowerClickListener(new FlowerListener(MainActivity.this));
		 qifen.dismiss();
	}

	@Override
	public void qiFenHuanHuClick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void qiFenZhuFuYuClick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void qiFenGaoGuaiClick() {
		QiFenGaoGuaiWindow qifenGaoGuai = new QiFenGaoGuaiWindow(MainActivity.this);
		qifenGaoGuai.showPopupWindow(homeBottomLayout);
		qifenGaoGuai.setPopuwindowGaoGuaiClickListener(new GaoGuaiListener(MainActivity.this));
		 qifen.dismiss();
		
	}
@Override
public void onBackPressed() {
	super.onBackPressed();
	exitConfirm();
}

ProgressDialog dialog = null;

private void exitConfirm()
{
	dialog = new ProgressDialog(this);  
	dialog.setMessage("正在保存新增歌曲文件...");
	dialog.setCancelable(false);
	dialog.show(); 
	new Thread(){
		public void run() {
			DataBase.getInstance().saveNewSong();
			DataBase.getInstance().saveAllSong(MainActivity.this);
			runOnUiThread(new  Runnable() {
				public void run() {
					dialog.dismiss();
					Toast.makeText(MainActivity.this, "保存成功!", 1).show();
					finish();
				}
			});
		};
	}.start();
	
}

@Override
public boolean onKeyDown(int keyCode, KeyEvent event) {
	
	if (keyCode == KeyEvent.KEYCODE_BACK )  
		         { 

		exitConfirm();
		         }
		
	return false;
}
	@Override
	public void yunGequ() {
		
		SongUtil.SONG_SELECT_TYPE = SongUtil.SORT_TYPE_6;
		DataBase.keySong="";
		DataBase.keyType=-1;
		HomeGeMingFragment.returnType = -1;
		EventBus.getDefault().post(new AsyncMessage(FragmentFactory.FRAGMENT_HOME_GEMING));
		
	}

	@Override
	public void yunXizai() {
		 
		HomeDownloadWindow yidianWindow = new HomeDownloadWindow(MainActivity.this);
		yidianWindow.showPopupWindow(homeBottomLayout);
		
	}

	@Override
	public void yunGengxin() {
		// TODO Auto-generated method stub
		
	}
}
