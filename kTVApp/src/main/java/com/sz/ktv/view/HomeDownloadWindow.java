package com.sz.ktv.view;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
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
import com.sz.ktv.msg.SongTypeMessage;
import com.sz.ktv.ui.service.DownLoadMsg;
import com.sz.ktv.view.adapter.DowLoadAdapter;
import com.sz.ktv.view.adapter.YiDianAdapter;

public class HomeDownloadWindow extends PopupWindow implements OnClickListener {

	TextView yunDownload;
	TextView yunDownloadOver;

	ListView yidianList;

	Button yidianPrePage;
	Button yidianNextPage;

	List<Song> songList = new ArrayList<Song>();
	DowLoadAdapter adapter;
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
			case MSG_INIT_DATA:
				
				break;
			default:
				break;
			}
    	};
    };
    
    private static final int MSG_INIT_DATA = 0x1001;
    
    @Subscribe(threadMode = ThreadMode.ASYNC)
	public void onAsyncEventBus(DownLoadMsg msg) {
    	
		Message message = handler.obtainMessage();
		message.obj = msg;
		message.what = MSG_FRRESH_ADAPTER;
		handler.sendMessage(message);
	}
    
	public HomeDownloadWindow(Activity context) {
		super(context);
		EventBus.getDefault().register(this);
		mContext = context; 
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		conentView = inflater.inflate(R.layout.home_download_layout, null);
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

		yunDownload = (TextView) conentView.findViewById(R.id.yun_download);
		yunDownloadOver = (TextView) conentView.findViewById(R.id.yun_download_over);

		yidianPrePage = (Button) conentView.findViewById(R.id.yidian_pre_page);
		yidianNextPage = (Button) conentView
				.findViewById(R.id.yidian_next_page);

		yidianList = (ListView) conentView.findViewById(R.id.yidian_list);

		yunDownload.setOnClickListener(this);
		yunDownloadOver.setOnClickListener(this);

		yidianPrePage.setOnClickListener(this);
		yidianNextPage.setOnClickListener(this);

		

		yunDownload.setTextColor(Color.RED);

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
		adapter = new DowLoadAdapter(mContext, crrentPageSongList,currentPage,handler);
		
		yidianList.setAdapter(adapter);
		adapter.notifyDataSetChanged();
		
	}

	private int totalPages = 0;
	private int type = 1;
	
	private void initTotalPage( )
	{
		if(type == 1)
		{
			songList = DataBase.songDownloaddList;
			allSize = songList.size();
		}else if(type == 2)
		{
			songList = DataBase.songDownSuccessloaddList;
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
			adapter = new DowLoadAdapter(mContext, crrentPageSongList,currentPage,handler);
		}else {
			adapter = new DowLoadAdapter(mContext, songList,currentPage,handler);
		
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
		adapter = new DowLoadAdapter(mContext, crrentPageSongList,currentPage,handler);
		
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
		adapter = new DowLoadAdapter(mContext, crrentPageSongList,currentPage,handler);
		
		yidianList.setAdapter(adapter);
		adapter.notifyDataSetChanged();
	}
	private View conentView;

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.yun_download:
			yunDownload.setTextColor(Color.RED);
			yunDownloadOver.setTextColor(Color.WHITE);
			type=1;
			initData();
			break;
		case R.id.yun_download_over:
			yunDownload.setTextColor(Color.WHITE);
			yunDownloadOver.setTextColor(Color.RED);
			type=2;
			initData();
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
