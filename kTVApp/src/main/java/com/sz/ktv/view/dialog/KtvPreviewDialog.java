package com.sz.ktv.view.dialog;

import java.io.File;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;

import com.hisilicon.android.mediaplayer.HiMediaPlayer;
import com.hisilicon.android.mediaplayer.HiMediaPlayer.OnErrorListener;
import com.hisilicon.android.mediaplayer.HiMediaPlayer.OnFastBackwordCompleteListener;
import com.hisilicon.android.mediaplayer.HiMediaPlayer.OnPreparedListener;
import com.hisilicon.android.videoplayer.util.Constants;
import com.hisilicon.android.videoplayer.view.HisiVideoView;
import com.sz.ktv.R;
import com.sz.ktv.db.DataBase;
import com.sz.ktv.db.Song;

public class KtvPreviewDialog extends Dialog implements OnClickListener,OnDismissListener {
 
	Song song = null;
	HisiVideoView videoView;
	RelativeLayout songChage;
	RelativeLayout songShoucang;
	RelativeLayout songDiange;
	
	public KtvPreviewDialog(Context context,Song msg) {
		super(context, R.style.loaddialog);
		song = msg;
	}

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ktv_preview_layout);
		layoutView();
	}

	private void layoutView() {
		videoView = (HisiVideoView)findViewById(R.id.vide_view);
		videoView.setZOrderOnTop(true);
		 videoView.setOnPreparedListener(mPreparedListener);
	        videoView.setOnCompletionListener(mCompletionListener);
	        videoView.setOnErrorListener(mOnErrorListener);
	        videoView.setOnFastBackwordCompleteListener(mFastBackwordCompleteListener);
	        videoView.setZOrderOnTop(true);
	        videoView.setVideoScale(400,400);
	        
		songChage = (RelativeLayout)findViewById(R.id.song_charu);
		songShoucang = (RelativeLayout)findViewById(R.id.song_shoucang);
		songDiange = (RelativeLayout)findViewById(R.id.pre_center);
		
		songChage.setOnClickListener(this);
		songShoucang.setOnClickListener(this);
		songDiange.setOnClickListener(this);
		
		setOnDismissListener(this);
		
		if(null == song)
		{
			return ;
		}
		initData();
	}
	
	
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
	   
		
		
	    OnPreparedListener mPreparedListener = new OnPreparedListener()
	    {
	        public void onPrepared(HiMediaPlayer mp)
	        {
	        	  
//	        	videoView.setZOrderOnTop(true);
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
//	    	videoView.setVideoScale(200, 200);
	    	videoView.setZOrderOnTop(true);
	        videoView.start();
	       
	    }
	    private HiMediaPlayer.OnCompletionListener mCompletionListener =
	        new HiMediaPlayer.OnCompletionListener() {
	        public void onCompletion(HiMediaPlayer mp) { 
	        	 
	        
	        }
	    };

	    
	    
	    
	String  defaultSongPath ;
	private void initData() {
		defaultSongPath = DataBase.MNT_SDA_SONG_PATH + song.getSong_file_name() ;
		File file = new File(defaultSongPath);
		if(!file.exists())
		{
			return ;
		}
		 videoView.setVideoPath(defaultSongPath);
	}

	
	
	
	@Override
	public void onClick(View v) {
 
		int id = v.getId();
		switch (id) {
		case R.id.song_charu:
			dismiss();
			break;
		case R.id.song_shoucang:
			dismiss();
			break;
		case R.id.pre_center:
			dismiss();
			break;
		default:
			break;
		}

	
		 
	}

	
public void yesClick()
{
	}

public void noClick()
{
	}
	 
	@Override
	public void dismiss() {
		super.dismiss();
	}


	@Override
	public void onDismiss(DialogInterface arg0) {
			videoView.stopPlayback();
	}
}
