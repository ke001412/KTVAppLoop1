package com.sz.ktv.ui.fragment.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.sz.ktv.R;
import com.sz.ktv.db.DataBase;
import com.sz.ktv.db.Song;
import com.sz.ktv.ui.fragment.SetGongBoFragment;
import com.sz.ktv.util.Global;

public class GongBoAdapter extends BaseAdapter {

	Context context;
	List<Song> songList = new ArrayList<Song>();
	List<Song> oneList = new ArrayList<Song>();
	List<Song> twoList = new ArrayList<Song>();
	Handler myHandler;

	private static final int ITEM_SIZE = 6;

	public GongBoAdapter(Context mContext, List<Song> mWifiList,
			int mCurrentPage, Handler handler) {
		myHandler = handler;
		this.context = mContext;
		this.songList = mWifiList;
		initData();
	}

	private void initData() {
		if (null != songList) {
			int size = songList.size();
			if (size > ITEM_SIZE) {
				oneList.addAll(songList.subList(0, ITEM_SIZE));
				twoList.addAll(songList.subList(ITEM_SIZE, size));

			} else {
				oneList.addAll(songList);
			}
		}
	}

	@Override
	public int getCount() {
		int sizeOne = oneList.size();
		int sizeTwo = twoList.size();
		return sizeOne >= sizeTwo ? sizeOne : sizeTwo;
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
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = Global.currentActivity.getLayoutInflater().inflate(
				R.layout.system_set_gongbo_item_layout, null);
		
	 	Song one = null;
		Song two = null;

		int oneS = oneList.size();
		int twoS = twoList.size();
		if (position <= oneS - 1) {
			one = oneList.get(position);

		}

		if (position <= twoS - 1) {
			two = twoList.get(position);

		}
		
		TextView tvGongboOneNo = (TextView) view
				.findViewById(R.id.gongbo_one_no);
		TextView tvGongboTwoNo = (TextView) view
				.findViewById(R.id.gongbo_two_no);

		TextView tvGongboOneSong = (TextView) view
				.findViewById(R.id.gongbo_one_songname);
		TextView tvGongboTwoSong = (TextView) view
				.findViewById(R.id.gongbo_two_songname);

		Button tvGongboOneDel = (Button) view
				.findViewById(R.id.gongbo_one_shanchu);
		tvGongboOneDel.setTag(one);
		
		Button tvGongboTwoDel = (Button) view
				.findViewById(R.id.gongbo_two_shanchu);
		tvGongboTwoDel.setTag(two);
		
		Button tvGongboOneDing = (Button) view
				.findViewById(R.id.gongbo_one_ding);
		tvGongboOneDing.setTag(one);
		
		Button tvGongboTwoDing = (Button) view
				.findViewById(R.id.gongbo_two_ding);
		tvGongboTwoDing.setTag(two);
		
		tvGongboOneDel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Song song = (Song)v.getTag();
				DataBase.delteGongboSong(song);
				myHandler.sendEmptyMessage(SetGongBoFragment.MSG_FRRESH_ADAPTER);

			}
		});
		tvGongboTwoDel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
			 
				Song song = (Song)v.getTag();
				DataBase.delteGongboSong(song);
				myHandler.sendEmptyMessage(SetGongBoFragment.MSG_FRRESH_ADAPTER);
			}
		});
		tvGongboOneDing.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				 
				Song song = (Song)v.getTag();
				DataBase.zhidingGongBo(song);
				myHandler.sendEmptyMessage(SetGongBoFragment.MSG_FRRESH_ADAPTER);
			}
		});
		tvGongboTwoDing.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				Song song = (Song)v.getTag();
				DataBase.zhidingGongBo(song);
				myHandler.sendEmptyMessage(SetGongBoFragment.MSG_FRRESH_ADAPTER);

			}
		});
		

		if (null != one) {
			tvGongboOneNo.setText("" + (position + 1) + ".");
			tvGongboOneSong.setText(one.getSong_name());
		}
		if (null != two) {
			tvGongboTwoNo.setText("" + (ITEM_SIZE + position + 1) + ".");
			tvGongboTwoSong.setText(two.getSong_name());
			tvGongboTwoDing.setVisibility(View.VISIBLE);
			tvGongboTwoDel.setVisibility(View.VISIBLE);
			
		}else {
			tvGongboTwoDing.setVisibility(View.GONE);
			tvGongboTwoDel.setVisibility(View.GONE);
		}
		return view;
	}

 
}
