package com.sz.ktv.ui.fragment;

import java.util.List;

import org.greenrobot.eventbus.EventBus;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sz.ktv.R;
import com.sz.ktv.application.MyApplication;
import com.sz.ktv.db.DataBase;
import com.sz.ktv.db.Song;
import com.sz.ktv.msg.AsyncMessage;
import com.sz.ktv.ui.base.BaseFragment;
import com.sz.ktv.util.FragmentFactory;
import com.sz.ktv.util.MemorySpaceCheck;
import com.sz.ktv.view.widget.KtvBottomPageWidget;
import com.sz.ktv.view.widget.KtvBottomPageWidget.BackClickListener;

public class SetUPanAddSongFragment extends BaseFragment implements OnClickListener,
		BackClickListener {

	KtvBottomPageWidget bottomPage ;
	TextView allSizeTv;
	TextView lastSizeTv;
	TextView lastSongCountTV;
	
	RelativeLayout singleAdd;
	RelativeLayout moreAdd;
	
	
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = null;
		try {
			view = inflater.inflate(R.layout.system_upan_addsong_layout, container, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		initView(view);
		return view;
	}
	
	private void initView(View view) {
		bottomPage = (KtvBottomPageWidget)view.findViewById(R.id.sys_u_addsong_back);
		bottomPage.setBackClickListener(this);
		bottomPage.setType(KtvBottomPageWidget.TYPE_NO_DOTS_NO_PAGENUM);
		
		allSizeTv = (TextView)view.findViewById(R.id.u_pan_all_size);
		lastSizeTv = (TextView)view.findViewById(R.id.u_pan_last_size);
		lastSongCountTV = (TextView)view.findViewById(R.id.u_pan_can_song_count);
		
		singleAdd = (RelativeLayout)view.findViewById(R.id.u_pan_add_danqu);
		moreAdd = (RelativeLayout)view.findViewById(R.id.u_pan_add_piliang);
		
		singleAdd.setOnClickListener(this);
		moreAdd.setOnClickListener(this);
		
		String all = MemorySpaceCheck.getTotalSize(DataBase.MNT_SDA_DIR);
		String lastSize = MemorySpaceCheck.getAvailableSize(DataBase.MNT_SDA_DIR);
		
		allSizeTv.setText("总空间大小:"+all);
		lastSizeTv.setText("剩余空间大小:"+lastSize);
		lastSongCountTV.setText("大约可以录入["+MemorySpaceCheck.lastSongNum+"]首歌曲");
	}

	@Override
	public void back() {
		EventBus.getDefault().post(
				new AsyncMessage(FragmentFactory.FRAGMENT_HOME_SYSTEM_SET));
	}

	@Override
	public void onClick(View v) { 
		int id= v.getId();
		switch (id) {
		case R.id.u_pan_add_danqu:
			EventBus.getDefault().post(
					new AsyncMessage(FragmentFactory.FRAGMENT_ADD_LIST));
			break;
		case R.id.u_pan_add_piliang:
			uPanMoreAdd();
			break;
		default:
			break;
		}
	}
	ProgressDialog dialog = null;
	
	private void uPanMoreAdd() {
		dialog = new ProgressDialog(getActivity());  
		dialog.setMessage("正在批量添加歌曲...");
		dialog.setCancelable(false);
		dialog.show(); 
		
		 new Thread(){
			 public void run() {
				List<Song> list = DataBase.getInstance().loadUPanSongList();
				if(null == list || list.size()<=0)
				{
					getActivity().runOnUiThread(new Runnable() {
						
						@Override
						public void run() {
							dialog.dismiss();
							Toast.makeText(getActivity(), "没有找到歌曲文件!", 1).show();
						}
					});
					return ;
				}
				 DataBase.getInstance().saveUpanFile();
				 DataBase.getInstance().copyUpanSongFile();
				 getActivity().runOnUiThread(new Runnable() {
						
						@Override
						public void run() {
							dialog.dismiss();
							Toast.makeText(getActivity(), "歌曲批量增加完成!", 1).show();
						}
					});
			 };
		 }.start();
		
	}

}
