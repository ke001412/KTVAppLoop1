package com.sz.ktv.view.adapter;

import java.util.ArrayList;
import java.util.List;

import org.greenrobot.eventbus.EventBus;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.sz.ktv.R;
import com.sz.ktv.db.DataBase;
import com.sz.ktv.db.Song;
import com.sz.ktv.msg.YiDianMessage;
import com.sz.ktv.util.Global;
import com.sz.ktv.view.HomeYiDianWindow;

public class DowLoadAdapter extends BaseAdapter {

	Context context ;
	List<Song> songList = new ArrayList<Song>() ;
	private int currentpage = 1;
	
	private Handler handler; 
	
	public DowLoadAdapter(Context mContext, List<Song> mSongList,int mCurrentPage,Handler mHandler)
	{
		 
		this.context = mContext;
		this.songList = mSongList;
		currentpage = mCurrentPage;
		initData();
		handler = mHandler;
	}
	private void initData() { }
	@Override
	public int getCount() {
		 
		return songList.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View view = Global.currentActivity.getLayoutInflater().inflate(R.layout.download_item_layout,
				null);
		 TextView tvNum = (TextView)view.findViewById(R.id.yidian_item_num);
		 String num =  ( (currentpage-1)*HomeYiDianWindow.PAGE_SIZE +position+1)+"";
		 tvNum.setText(""+num+".");
		 TextView tvSongName= (TextView)view.findViewById(R.id.yidian_item_song_name);
		 TextView tvSingerName = (TextView)view.findViewById(R.id.yidian_item_song_singer);
		final Song song = songList.get(position);
		 if(null != song)
		 {
			 String songName = song.getSong_name();
			 String singerName = song.getSong_singer_name();
			 tvSongName.setText(songName);
			 tvSingerName.setText(singerName);
		 }
		 
			 
		return view;
	}
}
