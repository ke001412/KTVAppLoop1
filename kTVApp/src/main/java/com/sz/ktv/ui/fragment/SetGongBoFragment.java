package com.sz.ktv.ui.fragment;

import java.util.ArrayList;
import java.util.List;

import org.greenrobot.eventbus.EventBus;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.sz.ktv.R;
import com.sz.ktv.db.DataBase;
import com.sz.ktv.db.Song;
import com.sz.ktv.msg.AsyncMessage;
import com.sz.ktv.ui.base.BaseFragment;
import com.sz.ktv.ui.fragment.adapter.GongBoAdapter;
import com.sz.ktv.util.FragmentFactory;
import com.sz.ktv.view.widget.KtvBottomPageWidget;
import com.sz.ktv.view.widget.KtvBottomPageWidget.BackClickListener;

public class SetGongBoFragment extends BaseFragment implements OnClickListener,
		BackClickListener {

	KtvBottomPageWidget bottomPage;
	private ListView gongboList;
	private TextView gongboPageShow;
	private Button gongboGeming;
	private Button gongboGexing;
	private Button gongboPrePage;
	private Button gongboNextPage;
	private Button gongboDaLuan;
	public static boolean openFlag = false; 
	
	GongBoAdapter adapter;
	List<Song> list = new ArrayList<Song>();
	List<Song> currentList = new ArrayList<Song>();

	private static int currentPage = 1;
	public static final int PAGE_SIZE = 12;
	private Activity activity;
	private int allSize = 0;
	private int totalPages = 0;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		activity =  getActivity();
		View view = null;
		try {
			view = inflater.inflate(R.layout.system_gongbo_layout, container,
					false);
		} catch (Exception e) {
			e.printStackTrace();
		}

		initView(view);
		initCurrentPage();
		return view;
	}

	private void initView(View view) {
		bottomPage = (KtvBottomPageWidget) view
				.findViewById(R.id.sys_gongbo_back);
		bottomPage.setBackClickListener(this);
		bottomPage.setType(KtvBottomPageWidget.TYPE_NO_DOTS_NO_PAGENUM);

		gongboList = (ListView) view.findViewById(R.id.gongbo_list);
		gongboGeming = (Button) view.findViewById(R.id.gongbo_bottom_geming);
		gongboGexing = (Button) view.findViewById(R.id.gongbo_bottom_gexing);
		gongboPrePage = (Button) view.findViewById(R.id.gongbo_bottom_pre);
		gongboNextPage = (Button) view.findViewById(R.id.gongbo_bottom_next);
		gongboDaLuan = (Button) view.findViewById(R.id.gongbo_bottom_daluan);
		gongboPageShow = (TextView) view.findViewById(R.id.gongbo_page_show);

		gongboGeming.setOnClickListener(this);
		gongboGexing.setOnClickListener(this);
		gongboPrePage.setOnClickListener(this);

		gongboNextPage.setOnClickListener(this);
		gongboDaLuan.setOnClickListener(this);

	}

	public static final int MSG_FRRESH_ADAPTER = 10;

	Handler handler = new Handler() {
		public void dispatchMessage(android.os.Message msg) {
			switch (msg.what) {
			case MSG_FRRESH_ADAPTER:
				initCurrentPage();
				break;

			default:
				break;
			}
		};
	};

	@Override
	public void back() {
		EventBus.getDefault().post(
				new AsyncMessage(FragmentFactory.FRAGMENT_HOME_SYSTEM_SET));
		isGongboSelectSongFlag = false;
	}

	public static boolean isGongboSelectSongFlag = false;
	List<Song> songList = new ArrayList<Song>();

	@Override
	public void onClick(View v) {

		int id = v.getId();
		switch (id) {
		case R.id.gongbo_bottom_geming:
			isGongboSelectSongFlag = true;
			
			gotoGeming();
		
			break;
		case R.id.gongbo_bottom_gexing:
			isGongboSelectSongFlag = true;
			gotoGeXing();
			break;
		case R.id.gongbo_bottom_pre:
			prePage();
			break;
		case R.id.gongbo_bottom_next:
			nextPage();
			break;
		case R.id.gongbo_bottom_daluan:
			DataBase.daluanGongboList();
			handler.sendEmptyMessage(MSG_FRRESH_ADAPTER);
			break;

		default:
			break;
		}

	}

	@Override
	public void onHiddenChanged(boolean hidden) {
		super.onHiddenChanged(hidden);
		if (!hidden) {
			initCurrentPage();
		}
	}

	private void initTotalPage() {
		songList = DataBase.getGongBoSongList(activity);
		allSize = songList.size();

		if ((allSize % PAGE_SIZE) == 0) {
			totalPages = allSize / PAGE_SIZE;
		} else {
			totalPages = allSize / PAGE_SIZE + 1;
		}
	}
	protected void initCurrentPage() {
		initTotalPage();
		
		int pre = currentPage-1;
		int currentAll = currentPage*PAGE_SIZE;
		if(currentAll>=allSize)
		{
			currentAll =allSize;
		}
		
		List<Song> crrentPageSongList = new ArrayList<Song>();
		crrentPageSongList.clear();
		crrentPageSongList.addAll(songList.subList(pre*PAGE_SIZE,currentAll));
		adapter = new GongBoAdapter(activity, crrentPageSongList,currentPage,handler);
		
		gongboList.setAdapter(adapter);
		adapter.notifyDataSetChanged();
		
	}
	
	private void nextPage()
	{
		
		 initTotalPage();
		 
		if(allSize<=0)
		{
			return ;
		}
		int prePage = currentPage;
		 currentPage =currentPage+1;
		int nextCount = currentPage*PAGE_SIZE;
		if(nextCount >= allSize)
		{
			nextCount = allSize;
			currentPage = totalPages;
			prePage = currentPage-1;
		}
		List<Song> crrentPageSongList = new ArrayList<Song>();
		crrentPageSongList.clear();
		crrentPageSongList.addAll(songList.subList(prePage*PAGE_SIZE,nextCount));
		adapter = new GongBoAdapter(activity, crrentPageSongList,currentPage,handler);
		
		gongboList.setAdapter(adapter);
		adapter.notifyDataSetChanged();
		
	}

	private void prePage()
	{
		 initTotalPage();
		 
		if(allSize<=0)
		{
			return ;
		}
		 
		 currentPage =currentPage-1;
		  
		int nextCount = currentPage*PAGE_SIZE;
		if(nextCount <= 0)
		{
			currentPage =1;
			return ;
		}
		
		List<Song> crrentPageSongList = new ArrayList<Song>();
		crrentPageSongList.clear();
		crrentPageSongList.addAll(songList.subList((currentPage-1)*PAGE_SIZE,nextCount));
		adapter = new GongBoAdapter(activity, crrentPageSongList,currentPage,handler);
		
		gongboList.setAdapter(adapter);
		adapter.notifyDataSetChanged();
	}
}
