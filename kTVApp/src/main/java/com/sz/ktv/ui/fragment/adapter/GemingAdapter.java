package com.sz.ktv.ui.fragment.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sz.ktv.R;
import com.sz.ktv.db.DataBase;
import com.sz.ktv.db.Song;
import com.sz.ktv.ui.fragment.HomeGeMingFragment;
import com.sz.ktv.ui.inter.GemingClickListener;
import com.sz.ktv.util.Global;

import java.io.File;
import java.util.List;

/**
 * 新的adapter
 * @author zhuxl
 *
 */
public class GemingAdapter extends BaseAdapter {

	List<Song> songList = null;
	Context mContext;
	private LayoutInflater mInflater;
	int width;
	int height;

	public GemingAdapter(List<Song> songLists, Context contexts) {
		songList = songLists;
		mContext = contexts;
		mInflater = LayoutInflater.from(mContext);
		WindowManager wm = (WindowManager) contexts
				.getSystemService(Context.WINDOW_SERVICE);

		width = wm.getDefaultDisplay().getWidth();
		height = wm.getDefaultDisplay().getHeight();

	}
	private GemingClickListener listener; 
	public void setGemingClickListener(GemingClickListener mListener)
	{
		this.listener = mListener;
	}
	@Override
	public int getCount() {
		return songList.size();
	}

	@Override
	public Object getItem(int position) {
		return songList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
 
	@Override
	public View getView(int position, View view, ViewGroup parent) {
		final View view2 = Global.currentActivity.getLayoutInflater().inflate(R.layout.geming_item,
				 parent, false);
		 
		if (view == null) {
			view = view2 ;//mInflater.inflate(R.layout.page_grid_item, parent, false);
			ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
			layoutParams.height = (parent.getHeight() - 60) / 3;// 计算每一行的高度
			view.setLayoutParams(layoutParams);
			
		}
		if (null != view && view.getHeight() == 0) {// 第一次调用getView时，parent的高度还是0,所以这里需要判断一下，并且重新设置，否则第一个子项显示不出来
			ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
			layoutParams.height = (parent.getHeight() - 60) / 3;
			view.setLayoutParams(layoutParams);
		}
		final TextView gemingTipsTv = (TextView) view
				.findViewById(R.id.geming_tips);
		final RelativeLayout leftLayout = (RelativeLayout) view
				.findViewById(R.id.geming_left_layout);
		final RelativeLayout rightLayout = (RelativeLayout) view
				.findViewById(R.id.geming_right_layout);
		TextView tvSongLanguage = (TextView) view
				.findViewById(R.id.geming_song_language);

		TextView tvYulan = (TextView) view.findViewById(R.id.geming_yulan);

		ImageView iv = (ImageView) view.findViewById(R.id.geming_singer_img);
		TextView gsingerName = (TextView) view
				.findViewById(R.id.geming_singer_name);
		TextView gsongName = (TextView) view
				.findViewById(R.id.geming_song_name);

		final Song song = songList.get(position);

		String singerName = song.getSong_singer_name();
		String songName = song.getSong_name();
//		String singerId = DataBase.singerPicMap.get(singerName);
		String headPath  = DataBase.singerPicMap.get(singerName);
		if(!TextUtils.isEmpty(headPath))
		{
		File file = new File(headPath);
//		 Glide.with(mContext).load(file).into(iv);
		}
//		Drawable drawable = DataBase.singerDrawableMap.get(singerName);
//		if (null == drawable) {
//			iv.setBackgroundResource(R.drawable.default_singer);
//		} else {
//			iv.setBackgroundDrawable(drawable);
//		}
	 
		String songLanguage = song.getSongLaunguageString();

		gsongName.setText(songName);
		gsongName.invalidate();
		gsingerName.setText(singerName);
		tvSongLanguage.setText(songLanguage);
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
					   listener.gemingSingerClick(song,view2);
					}else {
						listener.gemingSongClick(song,view2,gemingTipsTv);
					}
				}
			}
		});
		
		rightLayout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(null != listener){
				listener.gemingSongClick(song,view2,gemingTipsTv);
				}
			}
		});

		return view;
	}

}
