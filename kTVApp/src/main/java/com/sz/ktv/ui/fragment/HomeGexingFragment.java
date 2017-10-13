package com.sz.ktv.ui.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.hjy.cache.AsyncImageLoader;
import com.hjy.cache.ImageCacheManager;
import com.sz.ktv.MainActivity;
import com.sz.ktv.R;
import com.sz.ktv.db.DataBase;
import com.sz.ktv.db.Singer;
import com.sz.ktv.db.Song;
import com.sz.ktv.db.ktv.SingerUtil;
import com.sz.ktv.db.ktv.SongPage;
import com.sz.ktv.intf.ScrollChangeListener;
import com.sz.ktv.msg.AsyncMessage;
import com.sz.ktv.msg.SingerTypeMessage;
import com.sz.ktv.msg.SongTypeMessage;
import com.sz.ktv.ui.base.BaseFragment;
import com.sz.ktv.ui.fragment.adapter.AdapterGexing;
import com.sz.ktv.ui.fragment.adapter.GexingAdapter;
import com.sz.ktv.ui.fragment.adapter.ImageDownloader;
import com.sz.ktv.util.FragmentFactory;
import com.sz.ktv.view.MySingerGridLayout;
import com.sz.ktv.view.MySingerGridLayout.OnItemClickListener;
import com.sz.ktv.view.cycle.MyCycleViewPager;
import com.sz.ktv.view.cycle.MyCycleViewPager.OnMyPageChangeListener;
import com.sz.ktv.view.widget.KtvBottomPageWidget;
import com.sz.ktv.view.widget.KtvBottomPageWidget.BackClickListener;

public class HomeGexingFragment extends BaseFragment implements
		OnClickListener, OnItemClickListener, ScrollChangeListener,
		BackClickListener {

	private Activity activity;
	private MyCycleViewPager  scrollLayout;
	
	MySingerGridLayout firstPage;
	MySingerGridLayout secodePage;
	MySingerGridLayout thirdPage;

	AdapterGexing firstAdapter;
	AdapterGexing secondAdapter;
	AdapterGexing thirdAdapter;

	private TextView gexingTitle;

	private Button daluNan;
	private Button daluNv;
	private Button gangtaiNan;
	private Button gangtaiNv;
	private Button haiwaiNan;
	private Button haiwaiNv;
	private Button yueduiZuhe;
	private Button allGexing;
	private Button gexingQita;

	List<Singer> firstPageList = new ArrayList<Singer>();
	List<Singer> secondPageList = new ArrayList<Singer>();
	List<Singer> thirdPageList = new ArrayList<Singer>();

//	PageSingerUtil page = null;
	public static final int PAGE_SIZE = 12;
	List<Singer> all = new ArrayList<Singer>();
	private static final int COLUMS = 6;
	private static final int ROWS = 2;
	private int allPage = 0;
	private KtvBottomPageWidget bottomPage;
	private String preTitle = "点歌->歌星点歌->";
	private String lastTitle = "全部";
	private HashMap<Integer, MySingerGridLayout> viewMap = new HashMap<Integer, MySingerGridLayout>();

	public static final int TYPE_SINGER_DALUNAN = 10;
	public static final int TYPE_SINGER_DALUNV = 11;
	public static final int TYPE_SINGER_GANGYAINAN = 12;
	public static final int TYPE_SINGER_GANGTAINV = 13;
	public static final int TYPE_SINGER_HAIWAINAN = 14;
	public static final int TYPE_SINGER_HAIWAINV = 15;
	public static final int TYPE_SINGER_LUEDUI = 16;
	public static final int TYPE_SINGER_QUANBU = 17;

	public static final int TYPE_SINGER_SHOUPIN = 18;
	
	public static final int RETURN_TYPE_DAOHANG = 10;
	
//	public HomeGexingFragment()
//	{
//		try {
//			initEventBus();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	
//	}
	
	@Override
	public void onHiddenChanged(boolean hidden) {
		super.onHiddenChanged(hidden);
		MainActivity.gexingHidden = hidden;
		if(hidden)
		{
//			refreshAllSinger();
		}
		 
	}
	
	@Override
	public void onDestroyView() {
		super.onDestroyView();
		System.out.println("销毁！！！！！");
		allGexing = null;
		scrollLayout = null;
//	page = null;
	all = null;
	firstPageList = null;
	firstPage = null;
	thirdPageList = null;
	secondPageList = null;
	viewMap.clear();
	viewMap = null;
	
	}
	 
//	static final HomeGexingFragment INSTANCE = new HomeGexingFragment();
//
//	public static HomeGexingFragment getInstance() {
//		return HomeGexingFragment.INSTANCE;
//	}
//	
	 
	@Override
	public void onResume() {
		super.onResume();
	 
	}
	
	 private AsyncImageLoader imageLoader;
	  
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = null;
		try {
			view = inflater.inflate(R.layout.gexing_layout, container, false);
		} catch (Exception e) {
			e.printStackTrace();
		}

//		page = new PageSingerUtil(PAGE_SIZE, DataBase.singerList);
		activity = getActivity();
		
		initView(view);
		initData();
		initEventBus();
		  ImageCacheManager cacheMgr = new ImageCacheManager(getActivity());
	        imageLoader = new AsyncImageLoader(getActivity(), cacheMgr.getMemoryCache(), cacheMgr.getPlacardFileCache());
	        
		return view;
	}

	private void initEventBus() {
		try {
			EventBus.getDefault().register(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		EventBus.getDefault().unregister(this);
	}

	@Subscribe(threadMode = ThreadMode.ASYNC)
	public void onAsyncEventBus(SingerTypeMessage msg) {

		Message message = handler.obtainMessage();
		message.obj = msg;
		message.what = MSG_REFRESH_SINGER;
		handler.sendMessage(message);
	}

	private static final int MSG_ADD_NEW_LIST = 123;
	Handler myHandler = new Handler()
	{
		public void dispatchMessage(Message msg) {
			int what = msg.what;
			switch (what) {
			case MSG_ADD_NEW_LIST:
				List<Singer> list = (List<Singer>)msg.obj;
				addNewView(list);
				break;
			case MSG_INIT_SONG:
				
				SongPage songPage =(SongPage)msg.obj;
				initSingerView(songPage);
				
				break;
			case MSG_ON_RIGHT_LEFT:
				int prePage = msg.arg1;
				int nextPage = msg.arg2;
				SongPage songPage1 = (SongPage)msg.obj;
				updateViews(prePage, songPage1, nextPage);
				
				break;
			default:
				break;
			}
		};
		
	};
	
	int currentpage = 1;
	private static final int MSG_INIT_SONG = 10;
	private int type = -1;
	String singerKey = "";
	
	private void initData() {

		new Thread(){
			public void run() {
				 long time1 = System.currentTimeMillis();
				SingerUtil.getInstance().initSingerPageByKey(type,singerKey);
				allPage = SingerUtil.getInstance().getTotalPages();
				SongPage songPage = SingerUtil.getInstance().getDefaultSingerList(10);
				long time2 = System.currentTimeMillis();
				System.out.println("initdata 查询: "+ (time2 -time1) );
				Message msg = myHandler.obtainMessage();
				msg.obj = songPage;
				msg.what=MSG_INIT_SONG;
				myHandler.sendMessage(msg);
			};
		}.start();
//		all = page.getAllData();
//		allPage = page.getTotalPages();
//
//		firstPageList = page.getPreSingerData(1);
//		firstAdapter.setData(firstPageList);
//		firstPage.reLoadView();
//
//		secondPageList = page.getPreSingerData(2);
//		secondAdapter.setData(secondPageList);
//		secodePage.reLoadView();
//
//		thirdPageList = page.getNextSingerData(1);
//		thirdAdapter.setData(thirdPageList);
//		thirdPage.reLoadView();
//
//		firstPage.setOnItemClickListener(this);
//		secodePage.setOnItemClickListener(this);
//		thirdPage.setOnItemClickListener(this);
//
		currentpage = 1;
		bottomPage.setAllPage(allPage);
		bottomPage.setCurrentPage(currentpage);
	}

	ArrayList<View> pages = new ArrayList<View>();
	MyPageAdapter pageAdapter = null;
	private static final int DEFAULT_INIT_SIZE = 10;
	private int defaultPages = DEFAULT_INIT_SIZE;
	
	protected void initSingerView(SongPage singerPag) {
		
		List<List<Singer>> lists = singerPag.getDefaultSingerList();
		
		if(null == lists || lists.size() <=0)
		{
			pages = new ArrayList<View>();
			View view1 = LayoutInflater.from(getActivity()).inflate(
					R.layout.page_singer_item, null);
			pages.add(view1);
			pageAdapter = new MyPageAdapter(pages);
			scrollLayout.setAdapter(pageAdapter);
			return ;
		}
		if (null != pages && pages.size() > 0) {
//			scrollLayout.removeAllViews();
			pages.clear();
			pages = new ArrayList<View>();
//			pageAdapter.notifyDataSetChanged();
//			pageAdapter = null;
//			scrollLayout.setAdapter(null);
		}
		
		for (int i = 0; i < defaultPages; i++) {
			View view1 = LayoutInflater.from(getActivity()).inflate(
					R.layout.page_singer_item, null);
			pages.add(view1);

		}
		pageAdapter = new MyPageAdapter(pages);
		scrollLayout.setAdapter(pageAdapter);
		scrollLayout.setOnMyPageChangeListener(onPageChangeListener);
//		cycleViewPager.setOnPageChangeListener(pageChangeListener);

		int pagesss = pages.size();
		for (int i = 0; i < pagesss; i++) {
			List<Singer> list = lists.get(i);
			View view0 = pages.get(i);
			initViwePage(list, view0);
		}
		
		currentpage = 1;
		bottomPage.setAllPage(allPage);
		bottomPage.setCurrentPage(currentpage);
	}
	
	private void addNextPage()
	{
		new Thread(){
			public void run() {
				if(isOver)
				{
					isOver = false;
					int size = pages.size();
					System.out.println(" 当前大小："+ size);
					int nextPage = size+1;
					if(nextPage>allPage)
					{
						return ;
					}
					List<Singer> addList = SingerUtil.getInstance().getCurrentSingerPage(nextPage);
					Message msg = myHandler.obtainMessage();
					msg.obj = addList;
					msg.what = MSG_ADD_NEW_LIST;
					myHandler.sendMessage(msg);
				}
			};
		}.start();
	}
	
private boolean isOver = true;

	protected void addNewView(List<Singer> list) {

		View view1 = LayoutInflater.from(getActivity()).inflate(
				R.layout.page_singer_item, null);
		pages.add(pages.size()-1, view1);
		initViwePage(list, view1);
		pageAdapter.notifyDataSetChanged();
		isOver = true;
	}
	
	OnMyPageChangeListener onPageChangeListener= new OnMyPageChangeListener() {
		
		@Override
		public void onPageSelected(int position, boolean prePage, boolean nextPage) {
			// TODO Auto-generated method stub
			System.out.println(position +  " "+ prePage + "  "+ nextPage);
			if(prePage)
			{
				currentpage--;
			}
			if(nextPage)
			{
				currentpage++;
				
			}
			
			if (currentpage <= 0) {
				currentpage = allPage;
			}
			if (currentpage > allPage) {
				currentpage = 1;
			}
			bottomPage.setCurrentPage(currentpage);
			addNextPage();
		}
		
		@Override
		public void onPageScrolled(int position, float positionOffset,
				int positionOffsetPixels) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onPageScrollStateChanged(int state) {
			// TODO Auto-generated method stub
			
		}
	};
	
	
	ImageDownloader mDownloader;  
	private void initViwePage(List<Singer> pre, View view0) {
		GridView grid = (GridView) view0.findViewById(R.id.grid_view);
		grid.setSelector(new ColorDrawable(Color.TRANSPARENT));
		GexingAdapter adapter = new GexingAdapter(pre, getActivity(),imageLoader);
		grid.setAdapter(adapter);

	}
	
	public class MyPageAdapter extends PagerAdapter {

		ArrayList<View> pages;

		public MyPageAdapter(ArrayList<View> pageList) {
			pages = pageList;
		}

		@Override
		public int getCount() {
			if (null == pages || pages.size() <= 0) {
				return 0;
			}
			return pages.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			if (null == pages || pages.size() <= 0) {
				return;
			}
			try {
				((MyCycleViewPager) container).removeView(pages.get(position));
			} catch (Exception e) {
			}
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			try {
				if (null == pages || pages.size() <= 0) {
					return null;
				}
				((MyCycleViewPager) container).addView(pages.get(position));
				return pages.get(position);
			} catch (Exception e) {
			}
			
			return null;
		}

	}

	private void reloadAll() {
//		scrollLayout.removeAllViews();
//
//		firstPage.setTag("0");
//		secodePage.setTag("1");
//		thirdPage.setTag("2");
//
//		viewMap.put(0, firstPage);
//		viewMap.put(1, secodePage);
//		viewMap.put(2, thirdPage);
//
//		scrollLayout.addView(firstPage);
//		scrollLayout.addView(secodePage);
//		scrollLayout.addView(thirdPage);

		initData();
	}

	private void initView(View view) {

		gexingTitle = (TextView) view.findViewById(R.id.gexing_title);
		gexingTitle.setText("" + preTitle + "" + lastTitle);

		scrollLayout = (MyCycleViewPager ) view.findViewById(R.id.scroll_layout);
//		firstPage = new MySingerGridLayout(activity);
//		firstPage.setColums(COLUMS);
//		firstPage.setRows(ROWS);
//
//		secodePage = new MySingerGridLayout(activity);
//		secodePage.setColums(COLUMS);
//		secodePage.setRows(ROWS);
//
//		thirdPage = new MySingerGridLayout(activity);
//		thirdPage.setColums(COLUMS);
//		thirdPage.setRows(ROWS);
//
//		scrollLayout.setScrollChangeListener(this);
//
//		firstAdapter = new AdapterGexing(activity, firstPageList);
//		secondAdapter = new AdapterGexing(activity, secondPageList);
//		thirdAdapter = new AdapterGexing(activity, thirdPageList);
//
//		firstPage.setGridAdapter(firstAdapter);
//		secodePage.setGridAdapter(secondAdapter);
//		thirdPage.setGridAdapter(thirdAdapter);
//
//		firstPage.setTag("0");
//		secodePage.setTag("1");
//		thirdPage.setTag("2");
//
//		viewMap.put(0, firstPage);
//		viewMap.put(1, secodePage);
//		viewMap.put(2, thirdPage);
//
//		scrollLayout.addView(firstPage);
//		scrollLayout.addView(secodePage);
//		scrollLayout.addView(thirdPage);

		bottomPage = (KtvBottomPageWidget) view
				.findViewById(R.id.ktv_bottom_dots);
		bottomPage.setType(KtvBottomPageWidget.TYPE_NO_DOTS);
		bottomPage.setBackClickListener(this);

		gexingTitle = (TextView) view.findViewById(R.id.gexing_title);
		daluNan = (Button) view.findViewById(R.id.gexing_dalu_nan);
		daluNv = (Button) view.findViewById(R.id.gexing_dalu_nv);
		gangtaiNan = (Button) view.findViewById(R.id.gexing_gangtai_nan);
		gangtaiNv = (Button) view.findViewById(R.id.gexing_gangtai_nv);
		haiwaiNan = (Button) view.findViewById(R.id.gexing_haiwai_nan);
		haiwaiNv = (Button) view.findViewById(R.id.gexing_haiwai_nv);
		allGexing = (Button) view.findViewById(R.id.gexing_quanbu);
		yueduiZuhe = (Button) view.findViewById(R.id.gexing_luedui_zuhe);
		gexingQita = (Button) view.findViewById(R.id.gexing_qita);

		gexingQita.setOnClickListener(this);
		yueduiZuhe.setOnClickListener(this);
		daluNan.setOnClickListener(this);
		daluNv.setOnClickListener(this);
		gangtaiNan.setOnClickListener(this);
		gangtaiNv.setOnClickListener(this);
		haiwaiNan.setOnClickListener(this);
		haiwaiNv.setOnClickListener(this);
		allGexing.setOnClickListener(this);

	}

	private static final int MSG_REFRESH_SINGER = 100;
	private int returnType = -1; 
	
	Handler handler = new Handler() {
		public void dispatchMessage(android.os.Message msg) {
			switch (msg.what) {
			case MSG_REFRESH_SINGER:
				SingerTypeMessage singerMsg = (SingerTypeMessage) msg.obj;
				int type2 = singerMsg.getSingerType();
				returnType = singerMsg.getReturnType();
				String msgStr = singerMsg.getMsg();
				
				switch (type2) {
				case TYPE_SINGER_SHOUPIN:
					refreshAllSingerByShouPin(msgStr);
					break;
				case TYPE_SINGER_DALUNAN:
					refreshAllSingerByType(DataBase.SINGER_TYPE_GANGTAI_NAN);
					break;
				case TYPE_SINGER_DALUNV:
					refreshAllSingerByType(DataBase.SINGER_TYPE_GANGTAI_NV);
					break;
				case TYPE_SINGER_GANGYAINAN:
					refreshAllSingerByType(DataBase.SINGER_TYPE_GANGTAI_NAN);
					break;
				case TYPE_SINGER_GANGTAINV:
					refreshAllSingerByType(DataBase.SINGER_TYPE_GANGTAI_NV);
					break;
				case TYPE_SINGER_HAIWAINAN:
					refreshAllSingerByType(DataBase.SINGER_TYPE_HAIWAI_NAN);
					break;
				case TYPE_SINGER_HAIWAINV:
					refreshAllSingerByType(DataBase.SINGER_TYPE_HAIWAI_NV);
					break;
				case TYPE_SINGER_LUEDUI:
					refreshAllSingerByType(DataBase.SINGER_TYPE_LUEDUI_ZUHE);
					break;
				case TYPE_SINGER_QUANBU:
					refreshAllSinger();
					break;

				default:
					break;
				}
				break;

			default:
				break;
			}
		};
	};

	private void refreshAllSingerByShouPin(String  shouPin) {
//		List<Singer> daluNanList = DataBase.getAllSingerListByShouPin(shouPin);
//		page = new PageSingerUtil(PAGE_SIZE, daluNanList);
		type = SingerUtil.TYPE_SINGER_SOUPIN;
		singerKey = shouPin;
		reloadAll();
		 
	}
	
	private void refreshAllSingerByType(int type2) {
//		List<Singer> daluNanList = DataBase.getAllSingerList(type+"");
//		page = new PageSingerUtil(PAGE_SIZE, daluNanList);
		
		type = SingerUtil.TYPE_SINGER_CLASS;
		singerKey = type2+"";
		
		reloadAll();
		switch (type2) {
		case DataBase.SINGER_TYPE_DALU_NAN:
			lastTitle = "大陆男星";
			setTile();
			break;
		case DataBase.SINGER_TYPE_DALU_NV:
			lastTitle = "大陆女星";
			setTile();
			break;
		case DataBase.SINGER_TYPE_GANGTAI_NAN:
			lastTitle = "港台男星";
			setTile();
			break;
		case DataBase.SINGER_TYPE_GANGTAI_NV:
			lastTitle = "港台女星";
			setTile();
			break;
		case DataBase.SINGER_TYPE_HAIWAI_NAN:
			lastTitle = "海外男星";
			setTile();
			break;
		case DataBase.SINGER_TYPE_HAIWAI_NV:
			lastTitle = "海外女星";
			setTile();
			break;
		case DataBase.SINGER_TYPE_LUEDUI_ZUHE:
			lastTitle = "乐队组合";
			setTile();
			break;
		default:
			break;
		}
	}

	private void refreshAllSinger() {
		List<Singer> allList = DataBase.singerList;

//		page = new PageSingerUtil(PAGE_SIZE, allList);
		reloadAll();
		lastTitle = "全部";
		setTile();
	}

	private void setTile() {
		gexingTitle.setText(preTitle + "" + lastTitle);
	}

	@Override
	public void onClick(View v) {

		int id = v.getId();
		switch (id) {
		case R.id.gexing_dalu_nan:
//			List<Singer> daluNanList = DataBase.getAllSingerList(
//					DataBase.SINGER_TYPE_DALU_NAN, null);
//			page = new PageSingerUtil(PAGE_SIZE, daluNanList);
//			reloadAll();
			refreshAllSingerByType(DataBase.SINGER_TYPE_DALU_NAN);
			break;
		case R.id.gexing_dalu_nv:
//			List<Singer> daluNvList = DataBase.getAllSingerList(
//					DataBase.SINGER_TYPE_DALU_NV, null);
//			page = new PageSingerUtil(PAGE_SIZE, daluNvList);
//			reloadAll();
			refreshAllSingerByType(DataBase.SINGER_TYPE_DALU_NV);
			break;
		case R.id.gexing_gangtai_nan:
//			List<Singer> gangtaiNanList = DataBase.getAllSingerList(
//					DataBase.SINGER_TYPE_GANGTAI_NAN, null);
//			page = new PageSingerUtil(PAGE_SIZE, gangtaiNanList);
//			reloadAll();
			refreshAllSingerByType(DataBase.SINGER_TYPE_GANGTAI_NAN);

			break;
		case R.id.gexing_gangtai_nv:
//			List<Singer> gangtaiNvList = DataBase.getAllSingerList(
//					DataBase.SINGER_TYPE_GANGTAI_NV, null);
//			page = new PageSingerUtil(PAGE_SIZE, gangtaiNvList);
//			reloadAll();
			refreshAllSingerByType(DataBase.SINGER_TYPE_GANGTAI_NV);

			break;
		case R.id.gexing_haiwai_nan:
//			List<Singer> haiwaiNanList = DataBase.getAllSingerList(
//					DataBase.SINGER_TYPE_HAIWAI_NAN, null);
//			page = new PageSingerUtil(PAGE_SIZE, haiwaiNanList);
//			reloadAll();
			refreshAllSingerByType(DataBase.SINGER_TYPE_HAIWAI_NAN);
			break;
		case R.id.gexing_haiwai_nv:
//			List<Singer> haiwaiNvList = DataBase.getAllSingerList(
//					DataBase.SINGER_TYPE_HAIWAI_NV, null);
//			page = new PageSingerUtil(PAGE_SIZE, haiwaiNvList);
//			reloadAll();
			refreshAllSingerByType(DataBase.SINGER_TYPE_HAIWAI_NV);
			break;
		case R.id.gexing_luedui_zuhe:
//			List<Singer> haiwaiLueduiList = DataBase.getAllSingerList(
//					DataBase.SINGER_TYPE_LUEDUI_ZUHE, null);
//			page = new PageSingerUtil(PAGE_SIZE, haiwaiLueduiList);
//			reloadAll();
			refreshAllSingerByType(DataBase.SINGER_TYPE_LUEDUI_ZUHE);
			break;
		case R.id.gexing_qita:
//			List<Singer> haiwaiQitaList = DataBase.getAllSingerList(
//					DataBase.SINGER_TYPE_QITA+"");
//			page = new PageSingerUtil(PAGE_SIZE, haiwaiQitaList);
			refreshAllSingerByType(DataBase.SINGER_TYPE_QITA);
//			reloadAll();
			break;

		case R.id.gexing_quanbu:
//			List<Singer> allList = DataBase.singerList;
			refreshAllSingerByType(-1);
//			page = new PageSingerUtil(PAGE_SIZE, allList);
//			reloadAll();
//			refreshAllSinger();
			break;

		default:
			break;
		}
	}

	@Override
	public void onLeft(int prePage, int currentPage, int nextPage) {

		currentpage--;

		if (currentpage <= 0) {
			currentpage = allPage;
		}
		starLoadThread(prePage,nextPage);
//		updateViews(prePage, currentPage, nextPage);

	}
	private int lastPreLoad = allPage;
	 private int lastNextLoad = 1;
	 public static final int LOADSIZE = 10;
	 LoadThread loadThread = null;
	 private static final int MSG_ON_RIGHT_LEFT = 11;
	 
	 class LoadThread extends Thread 
	 {
		
		 int star = 0;
		 boolean run = true;
		 int prePage;
		 int nextPage;
		 
		 public void stopRun(boolean flag)
		 {
			 run = !flag;
		 }
		 public LoadThread(int pre,int startPage,int next) {
			 
			 star = startPage;
			 prePage = pre;
			 nextPage = next;
			 
		}
		 @Override
		public void run() {
			 	super.run();
			 	if(run)
			 	{
			 		SongPage songPage = SingerUtil.getInstance().getSingerPage(currentpage);
			 		Message msg = myHandler.obtainMessage();
			 		msg.obj = songPage;
			 		msg.arg1 = prePage;
			 		msg.arg2 = nextPage;
			 		msg.what=MSG_ON_RIGHT_LEFT;
			 		myHandler.sendMessage(msg);
			 	}
			 	
		}
	 }

		private void clearLoadThread(){
			
			type = -1;
			singerKey="";
			
			lastNextLoad = 1;
			lastPreLoad = allPage;
			if(null != loadThread && !loadThread.isInterrupted())
			{
				loadThread.stopRun(true);
				loadThread.interrupt();
				loadThread = null;
			}
			
		};
	private void starLoadThread(int prePage, int nextPage) {
		 
		if(loadThread!= null)
		{
			loadThread.stopRun(true);
			loadThread.interrupt();
			loadThread = null;
		}
		loadThread = new LoadThread(prePage,currentpage,nextPage);
		loadThread.start();
	}

	private void updateViews(int prePage, SongPage songPage, int nextPage) {

		bottomPage.setCurrentPage(currentpage);
		MySingerGridLayout prePageView = viewMap.get(prePage);
		MySingerGridLayout nextPageView = viewMap.get(nextPage);

		List<Singer> prePageList = songPage.getPreSingerPageList();
		List<Singer> nextPageList = songPage.getNextSingerPageList();
		
		AdapterGexing prePgeAdapter = (AdapterGexing) prePageView
				.getGridAdapter();
		AdapterGexing nextPgeAdapter = (AdapterGexing) nextPageView
				.getGridAdapter();

		prePgeAdapter.setData(prePageList);
		prePageView.reLoadView();

		nextPgeAdapter.setData(nextPageList);
		nextPageView.reLoadView();

		firstPage.setOnItemClickListener(this);
		secodePage.setOnItemClickListener(this);
		thirdPage.setOnItemClickListener(this);
	}

	@Override
	public void onRight(int prePage, int currentPage, int nextPage) {

		currentpage++;
		if (currentpage > allPage) {
			currentpage = 1;
		}
		starLoadThread(prePage,nextPage);
//		updateViews(prePage, currentPage, nextPage);
	}

	@Override
	public void onItemClick(View v, int index) {
		final Singer singer = (Singer) v.getTag();

		EventBus.getDefault().post(
				new AsyncMessage(FragmentFactory.FRAGMENT_HOME_GEMING));
		SongTypeMessage msg = new SongTypeMessage();
		msg.setKeyWords(singer.getSinger_name());
		msg.setType(HomeGeMingFragment.TYPE_SONG_SINGER);
		msg.setReturnType(HomeGeMingFragment.RETURN_TYPE_SINGER);
		EventBus.getDefault().post(msg);

		// activity.runOnUiThread(new Runnable() {
		//
		// @Override
		// public void run() {
		// Toast.makeText(activity, singer.getSingerName(), 1).show();
		//
		// }
		// });
	}

	@Override
	public void back() {

		if(SetGongBoFragment.isGongboSelectSongFlag)
		{
			gotoGongBoSetting();
			SetGongBoFragment.isGongboSelectSongFlag = false;
			
			return ;
		}
		
		if(returnType==RETURN_TYPE_DAOHANG)
		{
			
			returnType = -1;
			EventBus.getDefault().post(
					new AsyncMessage(FragmentFactory.FRAGMENT_HOME_DAOHANG));
		}else {
		backToHome();
		returnType = -1;
		}
	}


	@Override
	public void nextStart() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void preStart() {
		// TODO Auto-generated method stub
		
	}

}
