//package com.sz.ktv.ui.fragment.adapter;
//
//import java.util.List;
//
//import android.app.Activity;
//import android.text.TextUtils;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.ImageView;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import com.sz.ktv.R;
//import com.sz.ktv.bean.SongBean;
//import com.sz.ktv.db.DataBase;
//import com.sz.ktv.intf.GridAdatper;
//import com.sz.ktv.ui.fragment.HomeGeMingFragment;
//import com.sz.ktv.util.GetHeadUtil;
//
//public class CopyOfAdapterGeming implements GridAdatper {
//
//	
//	private List<SongBean> list;
//	private Activity mActivity ;
//	
//	private GemingClickListener listener; 
//	
//	
//	public void setGemingClickListener(GemingClickListener mListener)
//	{
//		this.listener = mListener;
//	}
//	
//	public interface GemingClickListener{
//		public void gemingSingerClick(SongBean song,View v);
//		public void gemingSongClick(SongBean song,View v,TextView tips);
//		public void gemingSongYuLanClick(SongBean song);
//	}
//	public CopyOfAdapterGeming(Activity activity , List<SongBean> mList){
//		this.list = mList;
//		mActivity = activity;
//	}
//	
//	public void setData( List<SongBean> mList)
//	{
//		this.list = mList;
//	}
//	@Override
//	public View getView(int index) {
//		final View view = mActivity.getLayoutInflater().inflate(R.layout.geming_item,
//				null);
//		final TextView gemingTipsTv = (TextView)view.findViewById(R.id.geming_tips);
//	    final	RelativeLayout leftLayout = (RelativeLayout)view.findViewById(R.id.geming_left_layout);
//		final RelativeLayout rightLayout = (RelativeLayout)view.findViewById(R.id.geming_right_layout);
//		TextView tvSongLanguage = (TextView)view.findViewById(R.id.geming_song_language);
//		
//		TextView tvYulan = (TextView)view.findViewById(R.id.geming_yulan);
//		
//		ImageView iv = (ImageView) view.findViewById(R.id.geming_singer_img);
//		TextView gsingerName = (TextView) view.findViewById(R.id.geming_singer_name);
//		TextView gsongName = (TextView) view.findViewById(R.id.geming_song_name);
//		
//		final SongBean song = list.get(index);
//		
//		String singerName = song.getSongSinger();
//		String songName = song.getSongName();
//		String singerId  = DataBase.singerPicMap.get(singerName);
//		String songLanguage = song.getSongLaunguageString();
//		
//		tvSongLanguage.setText(songLanguage);
//		
//		iv.setBackgroundDrawable(GetHeadUtil.getSongSinerHead(mActivity, singerId));
//		
//		gsingerName.setText(singerName);
//		gsongName.setText(songName);
//		view.setTag(song);
//		tvYulan.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				if(null != listener){
//					listener.gemingSongYuLanClick(song);
//					}
//			}
//		});
//		
//		leftLayout.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				if(null != listener)
//				{
//					if(TextUtils.isEmpty(HomeGeMingFragment.currentSinger)){
//					   listener.gemingSingerClick(song,view);
//					}else {
//						listener.gemingSongClick(song,view,gemingTipsTv);
//					}
//				}
//			}
//		});
//		
//		rightLayout.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				if(null != listener){
//				listener.gemingSongClick(song,view,gemingTipsTv);
//				}
//			}
//		});
//		return view;
//	}
//
//	@Override
//	public int getCount() {
//		if(null == list || list.size() <=0)
//		{
//			return 0;
//		}
//		return list.size();
//	}
//
//	@Override
//	public void update() {
//
//	}
//
//}
