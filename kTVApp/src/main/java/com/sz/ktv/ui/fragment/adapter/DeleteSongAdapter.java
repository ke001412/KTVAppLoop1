package com.sz.ktv.ui.fragment.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract.CommonDataKinds.Note;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sz.ktv.R;
import com.sz.ktv.db.Song;
import com.sz.ktv.ui.fragment.SetSongDelteFragment;
import com.sz.ktv.ui.fragment.SetSongXiuGaiFragment;
import com.sz.ktv.util.Global;
import com.sz.ktv.view.TextViewDel;

public class DeleteSongAdapter extends BaseAdapter {

	Context context;
	List<Song> songList = new ArrayList<Song>();
	private Handler mHandler;
	
	public DeleteSongAdapter(Context mContext, List<Song> mWifiList,Handler myHandler) {
		 
		this.context = mContext;
		this.songList = mWifiList;
		mHandler = myHandler;
	}
 

	@Override
	public int getCount() {
		 
		return   songList.size();
	}

	@Override
	public Object getItem(int position) {
		return songList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
  
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = Global.currentActivity.getLayoutInflater().inflate(
				R.layout.song_delete_item_layout, null);
	  final  Song song = songList.get(position);
		TextView showNo = (TextView)view.findViewById(R.id.show_no);
		TextView showSongName = (TextView)view.findViewById(R.id.show_name);
		TextView showSongSiner = (TextView)view.findViewById(R.id.show_singer);
		final TextViewDel showDel = (TextViewDel)view.findViewById(R.id.show_delete);
		
		if(selectAll || selectCurrentAll)
		{
			song.setSong_del(true);
		} 
		if(seletcAllCancle)
		{
			song.setSong_del(false);
		}
		boolean falg = song.isSong_del;
		
		 if(falg)
		 {
		 //添加删除线  
			 showDel.setDeleteFlag(true);
			 
		 }else {
			 showDel.setDeleteFlag(false);
		 }
		
		if(null != song)
		{
			String songName = song.getSong_name();
			String songSiner = song.getSong_singer_name();
			
			showNo.setText( (position+1)+".");
			showSongName.setText(songName);
			showSongSiner.setText(songSiner);
		}
	 	view.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				boolean falg = song.isSong_del;
				 if(falg)
				 {
					 showDel.setDeleteFlag(false);
					 song.setSong_del(false);
				 }else {
					 showDel.setDeleteFlag(true);
					 song.setSong_del(true);
				 }
				 System.out.println("删除歌曲:"+ falg  +"   "+song.getSong_name());
				 Message msg = mHandler.obtainMessage();
				 msg.obj = song;
				 msg.what = SetSongDelteFragment.MSG_SELECT_DEL;
				 mHandler.sendMessage(msg);
				
			}
		}) ;
		
//	 	 Message msg = mHandler.obtainMessage();
//		 msg.obj = song;
//		 msg.what = SetSongDelteFragment.MSG_SELECT_DEL;
//		 mHandler.sendMessage(msg);
		 
		return view;
	}

	private boolean selectCurrentAll = false;
	private boolean selectAll = false;
	
	public void setSelectCurrentAll(boolean b) {
		selectCurrentAll = b;
		notifyDataSetChanged();
	}
	
	public void setSelectAll(boolean b) {
		selectAll = b;
		notifyDataSetChanged();
	}
	private boolean seletcAllCancle =false;
	
	public void setSelectAllCancle(boolean b)
	{
		seletcAllCancle = b;
	}
 
}
