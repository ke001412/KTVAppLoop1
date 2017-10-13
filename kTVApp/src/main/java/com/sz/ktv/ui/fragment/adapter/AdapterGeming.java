package com.sz.ktv.ui.fragment.adapter;

import java.io.File;
import java.util.List;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sz.ktv.R;
import com.sz.ktv.db.DataBase;
import com.sz.ktv.db.Song;
import com.sz.ktv.intf.GridAdatper;
import com.sz.ktv.ui.fragment.HomeGeMingFragment;
import com.sz.ktv.ui.inter.GemingClickListener;
import com.sz.ktv.util.GetHeadUtil;

public class AdapterGeming implements GridAdatper {

	
	private List<Song> list;
	private Activity mActivity ;
	
	private GemingClickListener listener; 
	
	
	public void setGemingClickListener(GemingClickListener mListener)
	{
		this.listener = mListener;
	}
	
//	public interface GemingClickListener{
//		public void gemingSingerClick(Song song,View v);
//		public void gemingSongClick(Song song,View v,TextView tips);
//		public void gemingSongYuLanClick(Song song);
//	}
	public AdapterGeming(Activity activity , List<Song> mList){
		this.list = mList;
		mActivity = activity;
	}
	
	public void setData( List<Song> mList)
	{
		this.list = mList;
	}
	@Override
	public View getView(int index) {
		final View view = mActivity.getLayoutInflater().inflate(R.layout.geming_item,
				null);
		final TextView gemingTipsTv = (TextView)view.findViewById(R.id.geming_tips);
	    final	RelativeLayout leftLayout = (RelativeLayout)view.findViewById(R.id.geming_left_layout);
		final RelativeLayout rightLayout = (RelativeLayout)view.findViewById(R.id.geming_right_layout);
		TextView tvSongLanguage = (TextView)view.findViewById(R.id.geming_song_language);
		
		TextView tvYulan = (TextView)view.findViewById(R.id.geming_yulan);
		
		ImageView iv = (ImageView) view.findViewById(R.id.geming_singer_img);
		TextView gsingerName = (TextView) view.findViewById(R.id.geming_singer_name);
		TextView gsongName = (TextView) view.findViewById(R.id.geming_song_name);
		
		final Song song = list.get(index);
		
		String singerName = song.getSong_singer_name();
		String songName = song.getSong_name();
		String headPath  = DataBase.singerPicMap.get(singerName);
//		Drawable drawable = DataBase.singerDrawableMap.get(singerName);
		String songLanguage = song.getSongLaunguageString();
		
		tvSongLanguage.setText(songLanguage);
		Glide.with(mActivity).load(new File(headPath)).into(iv);
//		iv.setBackgroundDrawable(GetHeadUtil.getSongSinerHead(mActivity, singerId));
//		iv.setBackgroundDrawable(drawable);
		gsingerName.setText(singerName);
		gsongName.setText(songName);
		view.setTag(song);
		tvYulan.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(null != listener){
					listener.gemingSongYuLanClick(song);
					}
			}
		});
		
		leftLayout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(null != listener)
				{
					if(TextUtils.isEmpty(HomeGeMingFragment.currentSinger)){
					   listener.gemingSingerClick(song,view);
					}else {
						listener.gemingSongClick(song,view,gemingTipsTv);
					}
				}
			}
		});
		
		rightLayout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(null != listener){
				listener.gemingSongClick(song,view,gemingTipsTv);
				}
			}
		});
		return view;
	}

	@Override
	public int getCount() {
		if(null == list || list.size() <=0)
		{
			return 0;
		}
		return list.size();
	}

	@Override
	public void update() {

	}

}
