package com.sz.ktv.view;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.sz.ktv.R;
import com.sz.ktv.db.DataBase;
import com.sz.ktv.db.Song;
import com.sz.ktv.view.adapter.YiDianAdapter;

public class HomeYiDianWindow extends PopupWindow implements OnClickListener {

	TextView yidianYiXuan;
	TextView yidianYiBo;
	TextView yidianDaLuan;

	ListView yidianList;

	Button yidianPrePage;
	Button yidianNextPage;

	LinkedList<Song> songList = new LinkedList<Song>();
	YiDianAdapter adapter;
    private  static int  currentPage =1;
    public static final int PAGE_SIZE = 8;
    private Context mContext ;
    private int  allSize  = 0;
    
    public static final int MSG_FRRESH_ADAPTER = 10;
    
    Handler handler = new Handler(){
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
	public HomeYiDianWindow(Activity context) {
		super(context);
		mContext = context; 
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		conentView = inflater.inflate(R.layout.home_yidian_layout, null);
		int h = context.getWindowManager().getDefaultDisplay().getHeight();
		int w = context.getWindowManager().getDefaultDisplay().getWidth();
		// 设置SelectPicPopupWindow的View
		this.setContentView(conentView);
		// 设置SelectPicPopupWindow弹出窗体的宽
		this.setWidth(w/4+80);
		// 设置SelectPicPopupWindow弹出窗体的高
		this.setHeight(3*h/4-50); // LayoutParams.WRAP_CONTENT
		// 设置SelectPicPopupWindow弹出窗体可点击
		this.setFocusable(true);
		this.setOutsideTouchable(true);
		// 刷新状态
		this.update();

		yidianYiXuan = (TextView) conentView.findViewById(R.id.yidian_yixuan);
		yidianYiBo = (TextView) conentView.findViewById(R.id.yidian_yibo);
		yidianDaLuan = (TextView) conentView.findViewById(R.id.yidian_daluan);

		yidianPrePage = (Button) conentView.findViewById(R.id.yidian_pre_page);
		yidianNextPage = (Button) conentView
				.findViewById(R.id.yidian_next_page);

		yidianList = (ListView) conentView.findViewById(R.id.yidian_list);

		yidianYiXuan.setOnClickListener(this);
		yidianYiBo.setOnClickListener(this);
		yidianDaLuan.setOnClickListener(this);

		yidianPrePage.setOnClickListener(this);
		yidianNextPage.setOnClickListener(this);

		

		yidianYiXuan.setTextColor(Color.RED);

		initData();
		
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
		adapter = new YiDianAdapter(mContext, crrentPageSongList,currentPage,handler);
		
		yidianList.setAdapter(adapter);
		adapter.notifyDataSetChanged();
		
	}

	private int totalPages = 0;
	private int type = 1;
	
	private void initTotalPage( )
	{
		if(type == 1)
		{
			songList = DataBase.getInstance().getYiDianList();
			allSize = songList.size();
		}else if(type == 2)
		{
			songList = DataBase.getInstance().getYiBoSongList();
			allSize = songList.size();
		}
		
		
		if ((allSize % PAGE_SIZE) == 0) {
			totalPages = allSize / PAGE_SIZE;
		} else {
			totalPages = allSize / PAGE_SIZE + 1;
		}
	}
	private void initData() {
		 initTotalPage();
		 
		if(allSize>PAGE_SIZE)
		{
			List<Song> crrentPageSongList = new ArrayList<Song>();
			crrentPageSongList.clear();
			crrentPageSongList = songList.subList(0,PAGE_SIZE);
			adapter = new YiDianAdapter(mContext, crrentPageSongList,currentPage,handler);
		}else {
			adapter = new YiDianAdapter(mContext, songList,currentPage,handler);
		
		}
		currentPage = 1;
		yidianList.setAdapter(adapter);
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
		adapter = new YiDianAdapter(mContext, crrentPageSongList,currentPage,handler);
		
		yidianList.setAdapter(adapter);
		adapter.notifyDataSetChanged();
		
	}

	private void prePage()
	{
		 initTotalPage();
		 
		songList = DataBase.getInstance().getYiDianList();
		allSize = songList.size();
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
		adapter = new YiDianAdapter(mContext, crrentPageSongList,currentPage,handler);
		
		yidianList.setAdapter(adapter);
		adapter.notifyDataSetChanged();
	}
	private View conentView;

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.yidian_yixuan:
			yidianYiXuan.setTextColor(Color.RED);
			yidianYiBo.setTextColor(Color.WHITE);
			type=1;
			initData();
			break;
		case R.id.yidian_yibo:
			yidianYiXuan.setTextColor(Color.WHITE);
			yidianYiBo.setTextColor(Color.RED);
			type=2;
			initData();
			break;
		case R.id.yidian_daluan:
			DataBase.getInstance().yidianDaLuan();
			handler.sendEmptyMessage(MSG_FRRESH_ADAPTER);
			
			break;

		case R.id.yidian_pre_page:
			prePage();
			break;
		case R.id.yidian_next_page:
			
			nextPage();
			
			break;

		default:
			break;
		}
	}

	public void showPopupWindow(View parent) {
		if (!this.isShowing()) {
			this.showAtLocation(parent, Gravity.RIGHT, 60,
					 parent.getHeight()-20);
		} else {
			this.dismiss();
		}
	}
}
