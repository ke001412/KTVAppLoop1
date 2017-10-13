//package com.sz.ktv.ui.fragment;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//import org.greenrobot.eventbus.EventBus;
//import org.greenrobot.eventbus.Subscribe;
//import org.greenrobot.eventbus.ThreadMode;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.sz.ktv.R;
//import com.sz.ktv.bean.SongBean;
//import com.sz.ktv.db.DataBase;
//import com.sz.ktv.intf.ScrollChangeListener;
//import com.sz.ktv.msg.AsyncMessage;
//import com.sz.ktv.msg.SongTypeMessage;
//import com.sz.ktv.ui.base.BaseFragment;
//import com.sz.ktv.ui.fragment.adapter.AdapterGeming;
//import com.sz.ktv.ui.fragment.adapter.AdapterGeming.GemingClickListener;
//import com.sz.ktv.util.AnimUtil;
//import com.sz.ktv.util.FragmentFactory;
//import com.sz.ktv.util.PageSongUtil;
//import com.sz.ktv.view.MyGridLayout;
//import com.sz.ktv.view.MyGridLayout.OnItemClickListener;
//import com.sz.ktv.view.ScrollLayout;
//import com.sz.ktv.view.dialog.KtvConfirmDialog;
//import com.sz.ktv.view.dialog.KtvPreviewDialog;
//import com.sz.ktv.view.widget.KtvBottomPageWidget;
//import com.sz.ktv.view.widget.KtvBottomPageWidget.BackClickListener;
//
//public class CopyOfHomeGeMingFragment extends BaseFragment implements
//		OnClickListener, BackClickListener, OnItemClickListener,
//		ScrollChangeListener, GemingClickListener {
//
//	public static final int TYPE_LEIBIE_SRGQ = 1;
//	public static final int TYPE_LEIBIE_GMGQ = 2;
//	public static final int TYPE_LEIBIE_XQXS = 3;
//	public static final int TYPE_LEIBIE_HJGQ = 4;
//	public static final int TYPE_LEIBIE_ETGQ = 5;
//	public static final int TYPE_LEIBIE_HCGQ = 6;
//	public static final int TYPE_LEIBIE_XQGQ = 7;
//	public static final int TYPE_LEIBIE_XSXP = 8;
//
//	public static final int TYPE_LIUXING_ZGHSY = 9;
//	public static final int TYPE_LIUXING_WSGS = 10;
//	public static final int TYPE_LIUXING_ZGHGQ = 11;
//	public static final int TYPE_LIUXING_ZMHS = 12;
//	public static final int TYPE_LIUXING_ZGYC = 13;
//	public static final int TYPE_LIUXING_ZGZQY = 14;
//	public static final int TYPE_LIUXING_MXXDD = 15;
//	public static final int TYPE_LIUXING_MMGW = 16;
//
//	public static final int TYPE_WUQU_MY = 17;
//	public static final int TYPE_WUQU_HEZ = 18;
//	public static final int TYPE_WUQU_QQ = 19;
//	public static final int TYPE_WUQU_DSG = 20;
//	public static final int TYPE_WUQU_PLS = 21;
//	public static final int TYPE_WUQU_CS = 22;
//	public static final int TYPE_WUQU_JTB = 23;
//	public static final int TYPE_WUQU_TG = 24;
//
//	public static final int TYPE_DY_DZ = 34;
//	public static final int TYPE_DY_XJ = 35;
//	public static final int TYPE_DY_GZ = 36;
//	public static final int TYPE_DY_FJ = 37;
//	public static final int TYPE_DY_KH = 38;
//	public static final int TYPE_DY_ZZ = 39;
//	public static final int TYPE_DY_DH = 40;
//	public static final int TYPE_DY_QT = 41;
//
//	public static final int TYPE_SONG_SINGER = 25;
//
//	public static final int TYPE_SONG_GUOYU = 26;
//	public static final int TYPE_SONG_YUEYU = 27;
//	public static final int TYPE_SONG_MINNANYU = 28;
//	public static final int TYPE_SONG_YINGYU = 29;
//	public static final int TYPE_SONG_RIYU = 30;
//	public static final int TYPE_SONG_HANYU = 31;
//	public static final int TYPE_SONG_QITA = 32;
//	public static final int TYPE_SONG_QUANBU = 33;
//
//	public static final int TYPE_SONG_SHOUPIN = 42;
//	public static final int TYPE_SONG_ZISHU = 43;
//	
//	private Activity activity;
//
//	public static final int RETURN_TYPE_WUQU = 1;
//	public static final int RETURN_TYPE_REMEN = 2;
//	public static final int RETURN_TYPE_LEIBIE = 3;
//	public static final int RETURN_TYPE_SINGER = 4;
//	public static final int RETURN_TYPE_SONG = 5;
//	public static final int RETURN_TYPE_DAOHANG = 6;
//
//	KtvBottomPageWidget bottomPage;
//	Button gemingGuoyu;
//	Button gemingYueyu;
//	Button gemingMinnanyu;
//	Button gemingYingyu;
//	Button gemingRiyu;
//	Button gemingHanyu;
//	Button gemingQita;
//	Button gemingQuanbu;
//
//	ScrollLayout scrollLayout;
//
//	MyGridLayout firstPage;
//	MyGridLayout secodePage;
//	MyGridLayout thirdPage;
//
//	AdapterGeming firstAdapter;
//	AdapterGeming secondAdapter;
//	AdapterGeming thirdAdapter;
//
//	List<SongBean> firstPageList = new ArrayList<SongBean>();
//	List<SongBean> secondPageList = new ArrayList<SongBean>();
//	List<SongBean> thirdPageList = new ArrayList<SongBean>();
//
//	PageSongUtil page = null;
//	public static final int PAGE_SIZE = 9;
//	List<SongBean> all = new ArrayList<SongBean>();
//	private static final int COLUMS = 3;
//	private static final int ROWS = 3;
//	private int allPage = 0;
//	int currentpage = 1;
//	private HashMap<Integer, MyGridLayout> viewMap = new HashMap<Integer, MyGridLayout>();
//	TextView gemingTitle;
//	public String gTitle;
//	public String lastTile = "全部";
//
//	public int returnType = -1;
//
////	public HomeGeMingFragment()
////	{
////		initEventBus();
////	}
//	
//	@Override
//	public View onCreateView(LayoutInflater inflater, ViewGroup container,
//			Bundle savedInstanceState) {
//
//		gTitle = "点歌->歌名点歌->" + lastTile;
//		activity = getActivity();
//		View view = null;
//		try {
//			view = inflater.inflate(R.layout.geming_layout, container, false);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		page = new PageSongUtil(PAGE_SIZE, DataBase.songList);
//
//		initView(view);
//		initData();
//		initEventBus();
//		return view;
//	}
//
//	private void initEventBus() {
//		EventBus.getDefault().register(this);
//
//	}
//	
//	static final CopyOfHomeGeMingFragment INSTANCE = new CopyOfHomeGeMingFragment();
//
//	public static CopyOfHomeGeMingFragment getInstance() {
//		return CopyOfHomeGeMingFragment.INSTANCE;
//	}
//
//	@Subscribe(threadMode = ThreadMode.ASYNC)
//	public void onAsyncEventBus(SongTypeMessage msg) {
//
//		Message message = handler.obtainMessage();
//		message.obj = msg;
//		message.what = MSG_CHANGE_SONG_TYPE;
//		handler.sendMessage(message);
//	}
//
//	private static final int MSG_CHANGE_SONG_TYPE = 100;
//	private static final int MSG_SEARCH_SONG_BY_NAME = 101;
//
//	Handler handler = new Handler() {
//		public void dispatchMessage(Message msg) {
//			int what = msg.what;
//			SongTypeMessage songTypeMsg = (SongTypeMessage) msg.obj;
//			switch (what) {
//			case MSG_CHANGE_SONG_TYPE:
//				int type = songTypeMsg.getType();
//				returnType = songTypeMsg.getReturnType();
//				String keyString = songTypeMsg.getKeyWords();
//				String preTile = songTypeMsg.getPreTileText();
//
//				gTitle = preTile;
//				lastTile = "全部";
//
//				setTitle();
//
//				switch (type) {
//				case TYPE_SONG_ZISHU:
//					
//					refreshAllSongBySongZiShu(keyString);
//					break;
//				case TYPE_SONG_SHOUPIN:
//					String songShouPin = keyString;
//
////					gTitle = "点歌->歌星反查->" + songSingerName + "->";
////					lastTile = "全部";
////					setTitle();
//					refreshAllSongBySongShouPin(songShouPin);
//					break;
//				case TYPE_SONG_GUOYU:
//					refreshAllSongBySongLanguage(DataBase.SONG_TYPE_GUOYU);
//					break;
//				case TYPE_SONG_YUEYU:
//					refreshAllSongBySongLanguage(DataBase.SONG_TYPE_YUEYU);
//					break;
//				case TYPE_SONG_MINNANYU:
//					refreshAllSongBySongLanguage(DataBase.SONG_TYPE_MINNANYU);
//					break;
//				case TYPE_SONG_YINGYU:
//					refreshAllSongBySongLanguage(DataBase.SONG_TYPE_YINGYU);
//					break;
//				case TYPE_SONG_RIYU:
//					refreshAllSongBySongLanguage(DataBase.SONG_TYPE_RIYU);
//					break;
//				case TYPE_SONG_HANYU:
//					refreshAllSongBySongLanguage(DataBase.SONG_TYPE_HANYU);
//					break;
//				case TYPE_SONG_QITA:
//					refreshAllSongBySongLanguage(DataBase.SONG_TYPE_QITA);
//					break;
//				case TYPE_SONG_QUANBU:
//					refresAllSongBySongLanAll();
//					break;
//
//				case TYPE_SONG_SINGER:
//
//					String songSingerName = keyString;
//					currentSinger = songSingerName;
//					
//					gTitle = "点歌->歌星反查->" + songSingerName + "->";
//					lastTile = "全部";
//					setTitle();
//					refreshAllSongBySingerName(songSingerName);
//					break;
//				case TYPE_LEIBIE_SRGQ:
//					refreshAllSongBySongType(keyString);
//					break;
//				case TYPE_LEIBIE_GMGQ:
//					refreshAllSongBySongType(keyString);
//					break;
//				case TYPE_LEIBIE_XQXS:
//					refreshAllSongBySongType(keyString);
//					break;
//				case TYPE_LEIBIE_HJGQ:
//					refreshAllSongBySongType(keyString);
//					break;
//				case TYPE_LEIBIE_ETGQ:
//					refreshAllSongBySongType(keyString);
//					break;
//				case TYPE_LEIBIE_HCGQ:
//					refreshAllSongBySongType(keyString);
//					break;
//				case TYPE_LEIBIE_XQGQ:
//					refreshAllSongBySongType(keyString);
//					break;
//				case TYPE_LEIBIE_XSXP:
//					refreshAllSongBySongType(keyString);
//					break;
//				case TYPE_LIUXING_ZGHSY:
//					refreshAllSongBySongLiuxing(keyString);
//					break;
//				case TYPE_LIUXING_WSGS:
//					refreshAllSongBySongLiuxing(keyString);
//					break;
//				case TYPE_LIUXING_ZGHGQ:
//					refreshAllSongBySongLiuxing(keyString);
//					break;
//				case TYPE_LIUXING_ZMHS:
//					refreshAllSongBySongLiuxing(keyString);
//					break;
//				case TYPE_LIUXING_ZGYC:
//					refreshAllSongBySongLiuxing(keyString);
//					break;
//				case TYPE_LIUXING_ZGZQY:
//					refreshAllSongBySongLiuxing(keyString);
//					break;
//				case TYPE_LIUXING_MXXDD:
//					refreshAllSongBySongLiuxing(keyString);
//					break;
//				case TYPE_LIUXING_MMGW:
//					refreshAllSongBySongLiuxing(keyString);
//					break;
//				case TYPE_WUQU_MY:
//					refreshAllSongBySongWuqu(keyString);
//					break;
//				case TYPE_WUQU_HEZ:
//					refreshAllSongBySongWuqu(keyString);
//					break;
//				case TYPE_WUQU_QQ:
//					refreshAllSongBySongWuqu(keyString);
//					break;
//				case TYPE_WUQU_DSG:
//					refreshAllSongBySongWuqu(keyString);
//					break;
//				case TYPE_WUQU_PLS:
//					refreshAllSongBySongWuqu(keyString);
//					break;
//				case TYPE_WUQU_CS:
//					refreshAllSongBySongWuqu(keyString);
//					break;
//				case TYPE_WUQU_JTB:
//					refreshAllSongBySongWuqu(keyString);
//					break;
//				case TYPE_WUQU_TG:
//					refreshAllSongBySongWuqu(keyString);
//					break;
//				case TYPE_DY_DZ:
//					refreshAllSongBySongDianying(keyString);
//					break;
//				case TYPE_DY_XJ:
//					refreshAllSongBySongDianying(keyString);
//					break;
//				case TYPE_DY_GZ:
//					refreshAllSongBySongDianying(keyString);
//					break;
//				case TYPE_DY_FJ:
//					refreshAllSongBySongDianying(keyString);
//					break;
//				case TYPE_DY_KH:
//					refreshAllSongBySongDianying(keyString);
//					break;
//				case TYPE_DY_ZZ:
//					refreshAllSongBySongDianying(keyString);
//					break;
//				case TYPE_DY_DH:
//					refreshAllSongBySongDianying(keyString);
//					break;
//				case TYPE_DY_QT:
//					refreshAllSongBySongDianying(keyString);
//					break;
//
//				default:
//					break;
//				}
//				break;
//
//			default:
//				break;
//			}
//		};
//	};
//
//	/**
//	 * 歌曲分类
//	 * 
//	 * @param type
//	 */
//	public void refreshAllSongBySongType(String type) {
//		List<SongBean> freshList = DataBase.getAllSongByFenLei(type);
//		page = new PageSongUtil(PAGE_SIZE, freshList);
//		reloadAll();
//	}
//
//	/**
//	 * 舞曲
//	 * 
//	 * @param type
//	 */
//	public void refreshAllSongBySongWuqu(String type) {
//		List<SongBean> freshList = DataBase.getAllSongByWuQugFenLei(type);
//		page = new PageSongUtil(PAGE_SIZE, freshList);
//		reloadAll();
//	}
//
//	/**
//	 * 电影分类
//	 * 
//	 * @param type
//	 */
//	public void refreshAllSongBySongDianying(String type) {
//		List<SongBean> freshList = DataBase.getAllSongByDianYingFenLei(type);
//		page = new PageSongUtil(PAGE_SIZE, freshList);
//		reloadAll();
//	}
//
//	/**
//	 * 流行分类
//	 * 
//	 * @param type
//	 */
//	public void refreshAllSongBySongLiuxing(String type) {
//		List<SongBean> freshList = DataBase.getAllSongByLiuXingFenLei(type);
//		page = new PageSongUtil(PAGE_SIZE, freshList);
//		reloadAll();
//	}
//
//	/**
//	 * 按照歌星搜索歌曲
//	 * 
//	 * @param type
//	 */
//	public void refreshAllSongBySingerName(String singerName) {
//		List<SongBean> freshList = DataBase.getAllSongBySingerName(singerName);
//		page = new PageSongUtil(PAGE_SIZE, freshList);
//		reloadAll();
//	}
//	
//	/**
//	 * 按照首拼搜索歌曲
//	 * 
//	 * @param type
//	 */
//	public void refreshAllSongBySongShouPin(String shouPin) {
//		List<SongBean> freshList = DataBase.getAllSongBySongShouPin(shouPin);
//		page = new PageSongUtil(PAGE_SIZE, freshList);
//		reloadAll();
//	}
//	/**
//	 * 按照首拼搜索歌曲
//	 * 
//	 * @param type
//	 */
//	public void refreshAllSongBySongZiShu(String zishu) {
//		List<SongBean> freshList = DataBase.getAllSongBySongShouZiShu(zishu);
//		page = new PageSongUtil(PAGE_SIZE, freshList);
//		reloadAll();
//	}
//
//	@Override
//	public void onDestroy() {
//		super.onDestroy();
//		EventBus.getDefault().unregister(this);
//
//	}
//
//	private void initView(View view) {
//		gemingTitle = (TextView) view.findViewById(R.id.geming_title);
//		gemingTitle.setText(gTitle);
//
//		bottomPage = (KtvBottomPageWidget) view.findViewById(R.id.geming_back);
//		bottomPage.setType(KtvBottomPageWidget.TYPE_DOTS);
//		bottomPage.setBackClickListener(this);
//
//		scrollLayout = (ScrollLayout) view
//				.findViewById(R.id.geming_scroll_layout);
//
//		firstPage = new MyGridLayout(activity);
//		firstPage.setColums(COLUMS);
//		firstPage.setRows(ROWS);
//
//		secodePage = new MyGridLayout(activity);
//		secodePage.setColums(COLUMS);
//		secodePage.setRows(ROWS);
//
//		thirdPage = new MyGridLayout(activity);
//		thirdPage.setColums(COLUMS);
//		thirdPage.setRows(ROWS);
//
//		scrollLayout.setScrollChangeListener(this);
//
//		firstAdapter = new AdapterGeming(activity, firstPageList);
//		secondAdapter = new AdapterGeming(activity, secondPageList);
//		thirdAdapter = new AdapterGeming(activity, thirdPageList);
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
//
//		gemingGuoyu = (Button) view.findViewById(R.id.geming_guoyu);
//		gemingYueyu = (Button) view.findViewById(R.id.geming_yueyu);
//		gemingMinnanyu = (Button) view.findViewById(R.id.geming_minnanyu);
//		gemingYingyu = (Button) view.findViewById(R.id.geming_yingyu);
//		gemingRiyu = (Button) view.findViewById(R.id.geming_riyu);
//		gemingHanyu = (Button) view.findViewById(R.id.geming_hanyu);
//		gemingQita = (Button) view.findViewById(R.id.geming_qita);
//		gemingQuanbu = (Button) view.findViewById(R.id.geming_quanbu);
//
//		gemingGuoyu.setOnClickListener(this);
//		gemingYueyu.setOnClickListener(this);
//		gemingMinnanyu.setOnClickListener(this);
//		gemingYingyu.setOnClickListener(this);
//		gemingRiyu.setOnClickListener(this);
//		gemingHanyu.setOnClickListener(this);
//		gemingQita.setOnClickListener(this);
//		gemingQuanbu.setOnClickListener(this);
//
//	}
//
//	private void initData() {
//
//		all = page.getAllData();
//		allPage = page.getTotalPages();
//
//		firstPageList = page.getPreSongData(1);
//		firstAdapter.setData(firstPageList);
//		firstPage.reLoadView();
//
//		secondPageList = page.getPreSongData(2);
//		secondAdapter.setData(secondPageList);
//		secodePage.reLoadView();
//
//		thirdPageList = page.getNextSongData(1);
//		thirdAdapter.setData(thirdPageList);
//		thirdPage.reLoadView();
//
//		firstAdapter.setGemingClickListener(this);
//		secondAdapter.setGemingClickListener(this);
//		thirdAdapter.setGemingClickListener(this);
//
//		// firstPage.setOnItemClickListener(this);
//		// secodePage.setOnItemClickListener(this);
//		// thirdPage.setOnItemClickListener(this);
//
//		currentpage = 1;
//		bottomPage.setAllPage(allPage);
//		bottomPage.setCurrentPage(currentpage);
//	}
//
//	private void reloadAll() {
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
//
//		initData();
//	}
//
//	@Override
//	public void back() {
//
//		currentSinger = "";
//		if(SetGongBoFragment.isGongboSelectSongFlag)
//		{
//			gotoGongBoSetting();
//			SetGongBoFragment.isGongboSelectSongFlag = false;
//			
//			return ;
//		}
//		if (returnType == RETURN_TYPE_LEIBIE) {
//			EventBus.getDefault().post(
//					new AsyncMessage(FragmentFactory.FRAGMENT_HOME_LEIBIE));
//			returnType = -1;
//		} else if (returnType == RETURN_TYPE_REMEN) {
//			EventBus.getDefault().post(
//					new AsyncMessage(FragmentFactory.FRAGMENT_HOME_REMEN));
//			returnType = -1;
//		} else if (RETURN_TYPE_WUQU == returnType) {
//			EventBus.getDefault().post(
//					new AsyncMessage(FragmentFactory.FRAGMENT_HOME_WUQU));
//			returnType = -1;
//		} else if (RETURN_TYPE_SINGER == returnType) {
//			EventBus.getDefault().post(
//					new AsyncMessage(FragmentFactory.FRAGMENT_HOME_GEXING));
//			returnType = -1;
//		} else if (RETURN_TYPE_SONG == returnType) {
//			EventBus.getDefault().post(
//					new AsyncMessage(FragmentFactory.FRAGMENT_HOME_GEMING));
//			returnType = -1;
//		} else if (RETURN_TYPE_DAOHANG == returnType) {
//			EventBus.getDefault().post(
//					new AsyncMessage(FragmentFactory.FRAGMENT_HOME_DAOHANG));
//			returnType = -1;
//		} else {
//			boolean songXiuGaiFlag = HomeSystemSetFragment.songUpdate;
//			if(songXiuGaiFlag)
//			{
//				gotoSetting();
//			}else {
//			backToHome();
//			}
//			gTitle = "点歌->歌名点歌->";
//			lastTile = "全部";
//			setTitle();
//		}
//
//	}
//
//	public void setTitle() {
//
//		gemingTitle.setText(gTitle + lastTile);
//	}
//
//	private void refreshAllSongBySongLanguage(String type) {
//
//		List<SongBean> guoyueList = DataBase.getAllSongByLanguage(type);
//		page = new PageSongUtil(PAGE_SIZE, guoyueList);
//		reloadAll();
//		if (type.equals(DataBase.SONG_TYPE_GUOYU)) {
//			gTitle = "点歌->歌名点歌->";
//			lastTile = "国语";
//			setTitle();
//
//		} else if (type.equals(DataBase.SONG_TYPE_YUEYU)) {
//			gTitle = "点歌->歌名点歌->";
//			lastTile = "粤语";
//			setTitle();
//
//		} else if (type.equals(DataBase.SONG_TYPE_MINNANYU)) {
//			gTitle = "点歌->歌名点歌->";
//			lastTile = "闽南语";
//			setTitle();
//
//		} else if (type.equals(DataBase.SONG_TYPE_YINGYU)) {
//			gTitle = "点歌->歌名点歌->";
//			lastTile = "英语";
//			setTitle();
//
//		} else if (type.equals(DataBase.SONG_TYPE_RIYU)) {
//			gTitle = "点歌->歌名点歌->";
//			lastTile = "日语";
//			setTitle();
//
//		} else if (type.equals(DataBase.SONG_TYPE_HANYU)) {
//			gTitle = "点歌->歌名点歌->";
//			lastTile = "韩语";
//			setTitle();
//
//		} else if (type.equals(DataBase.SONG_TYPE_QITA)) {
//			gTitle = "点歌->歌名点歌->";
//			lastTile = "其他";
//			setTitle();
//		}
//	}
//
//	private void refresAllSongBySongLanAll() {
//		List<SongBean> allList = DataBase.songList;
//		page = new PageSongUtil(PAGE_SIZE, allList);
//		reloadAll();
//		gTitle = "点歌->歌名点歌->";
//		lastTile = "全部";
//		setTitle();
//	}
//
//	@Override
//	public void onClick(View v) {
//		currentSinger = "";
//
//		int id = v.getId();
//		switch (id) {
//		case R.id.geming_guoyu:
//			// List<SongBean> guoyueList = DataBase
//			// .getAllSongByLanguage(DataBase.SONG_TYPE_GUOYU);
//			// page = new PageSongUtil(PAGE_SIZE, guoyueList);
//			// reloadAll();
//			//
//			// gTitle = "点歌->歌名点歌->";
//			// lastTile = "国语";
//			// setTitle();
//			refreshAllSongBySongLanguage(DataBase.SONG_TYPE_GUOYU);
//			break;
//		case R.id.geming_yueyu:
//			// List<SongBean> yueyuList = DataBase
//			// .getAllSongByLanguage(DataBase.SONG_TYPE_YUEYU);
//			// page = new PageSongUtil(PAGE_SIZE, yueyuList);
//			// reloadAll();
//			//
//			// gTitle = "点歌->歌名点歌->";
//			// lastTile = "粤语";
//			// setTitle();
//			refreshAllSongBySongLanguage(DataBase.SONG_TYPE_YUEYU);
//
//			break;
//		case R.id.geming_minnanyu:
//			// List<SongBean> minnanList = DataBase
//			// .getAllSongByLanguage(DataBase.SONG_TYPE_MINNANYU);
//			// page = new PageSongUtil(PAGE_SIZE, minnanList);
//			// reloadAll();
//			//
//			// gTitle = "点歌->歌名点歌->";
//			// lastTile = "闽南语";
//			// setTitle();
//			refreshAllSongBySongLanguage(DataBase.SONG_TYPE_MINNANYU);
//
//			break;
//		case R.id.geming_yingyu:
//			// List<SongBean> yingyuList = DataBase
//			// .getAllSongByLanguage(DataBase.SONG_TYPE_YINGYU);
//			// page = new PageSongUtil(PAGE_SIZE, yingyuList);
//			// reloadAll();
//			//
//			// gTitle = "点歌->歌名点歌->";
//			// lastTile = "英语";
//			// setTitle();
//			refreshAllSongBySongLanguage(DataBase.SONG_TYPE_YINGYU);
//
//			break;
//		case R.id.geming_riyu:
//			// List<SongBean> riyuList = DataBase
//			// .getAllSongByLanguage(DataBase.SONG_TYPE_RIYU);
//			// page = new PageSongUtil(PAGE_SIZE, riyuList);
//			// reloadAll();
//			//
//			// gTitle = "点歌->歌名点歌->";
//			// lastTile = "日语";
//			// setTitle();
//			refreshAllSongBySongLanguage(DataBase.SONG_TYPE_RIYU);
//			break;
//		case R.id.geming_hanyu:
//			// List<SongBean> hanyuList = DataBase
//			// .getAllSongByLanguage(DataBase.SONG_TYPE_HANYU);
//			// page = new PageSongUtil(PAGE_SIZE, hanyuList);
//			// reloadAll();
//			//
//			// gTitle = "点歌->歌名点歌->";
//			// lastTile = "韩语";
//			// setTitle();
//			refreshAllSongBySongLanguage(DataBase.SONG_TYPE_HANYU);
//			break;
//		case R.id.geming_qita:
//			// List<SongBean> qitaList = DataBase
//			// .getAllSongByLanguage(DataBase.SONG_TYPE_QITA);
//			// page = new PageSongUtil(PAGE_SIZE, qitaList);
//			// reloadAll();
//			//
//			// gTitle = "点歌->歌名点歌->";
//			// lastTile = "其他";
//			// setTitle();
//			refreshAllSongBySongLanguage(DataBase.SONG_TYPE_QITA);
//
//			break;
//		case R.id.geming_quanbu:
//			// List<SongBean> allList = DataBase.songList;
//			// page = new PageSongUtil(PAGE_SIZE, allList);
//			// reloadAll();
//			//
//			// gTitle = "点歌->歌名点歌->";
//			// lastTile = "全部";
//			// setTitle();
//			refresAllSongBySongLanAll();
//			break;
//
//		default:
//			break;
//		}
//	}
//
//	@Override
//	public void onLeft(int prePage, int currentPage, int nextPage) {
//		currentpage--;
//
//		if (currentpage <= 0) {
//			currentpage = allPage;
//		}
//
//		updateViews(prePage, currentPage, nextPage);
//
//	}
//
//	@Override
//	public void onRight(int prePage, int currentPage, int nextPage) {
//		currentpage++;
//		if (currentpage > allPage) {
//			currentpage = 1;
//		}
//		updateViews(prePage, currentPage, nextPage);
//
//	}
//
//	@Override
//	public void onHiddenChanged(boolean hidden) {
//		super.onHiddenChanged(hidden);
//
//		currentSinger = "";
//
//		if (hidden) {
//			List<SongBean> allList = DataBase.songList;
//			page = new PageSongUtil(PAGE_SIZE, allList);
//			reloadAll();
//
//			gTitle = "点歌->歌名点歌->";
//			lastTile = "全部";
//			setTitle();
//		}
//	}
//
//	private void updateViews(int prePage, int currentPage, int nextPage) {
//
//		bottomPage.setCurrentPage(currentpage);
//		MyGridLayout prePageView = viewMap.get(prePage);
//		MyGridLayout nextPageView = viewMap.get(nextPage);
//
//		List<SongBean> prePageList = page.getPreSongData(currentpage);
//		List<SongBean> nextPageList = page.getNextSongData(currentpage);
//		AdapterGeming prePgeAdapter = (AdapterGeming) prePageView
//				.getGridAdapter();
//		AdapterGeming nextPgeAdapter = (AdapterGeming) nextPageView
//				.getGridAdapter();
//
//		prePgeAdapter.setData(prePageList);
//		prePageView.reLoadView();
//
//		nextPgeAdapter.setData(nextPageList);
//		nextPageView.reLoadView();
//
//		firstAdapter.setGemingClickListener(this);
//		secondAdapter.setGemingClickListener(this);
//		thirdAdapter.setGemingClickListener(this);
//	}
//
//	@Override
//	public void onItemClick(View v, int index) {
//
//	}
//
//	public static String currentSinger = "";
//
//	@Override
//	public void gemingSingerClick(SongBean song, View v) {
//
//		currentSinger = song.getSongSinger();
//		SongTypeMessage msg = new SongTypeMessage();
//		msg.setKeyWords(currentSinger);
//		msg.setType(TYPE_SONG_SINGER);
//		msg.setReturnType(RETURN_TYPE_SONG);
//		EventBus.getDefault().post(msg);
//		// Message msg = handler.obtainMessage();
//		//
//		// msg.obj = song.getSongSinger();
//		// msg.what = MSG_SEARCH_SONG_BY_NAME;
//		// handler.sendMessage(msg);
//	}
//
//	public static SongBean songBean;
//	
//	@Override
//	public void gemingSongClick(SongBean song, final View v,TextView tips) {
//		
//		boolean songXiuGaiFlag = HomeSystemSetFragment.songUpdate;
//		if(songXiuGaiFlag)
//		{
//			songBean = song;
//			EventBus.getDefault().post(
//					new AsyncMessage(FragmentFactory.FRAGMENT_SET_SONG_XIUGAI));
//			HomeSystemSetFragment.songUpdate = false;
//			return ;
//		}
//		
//		
//		if(SetGongBoFragment.isGongboSelectSongFlag)
//		{
//			boolean ifGongboAdd = DataBase.isGongBoSongExist(activity, song);
//			
//			if(ifGongboAdd)
//			{
//				tips.setVisibility(View.VISIBLE);
//				tips.setText("已经添加");
//				tips.invalidate();
//				return ;
//			}
//			DataBase.writeGongBoSongToFile(activity, song);
//			tips.setVisibility(View.VISIBLE);
//			tips.setText("操作成功");
//			tips.invalidate();
//			
//			return ;
//		}
//		
//		boolean isExist = DataBase.getInstance().checkSongExist(song);
//		if (isExist) {
//			KtvConfirmDialog dialog = new KtvConfirmDialog(activity,
//					"已经存在列表,是否继续点播?") {
//				@Override
//				public void yesClick() {
//					super.yesClick();
//					try {
//						AnimUtil.addYiDianSong(v);
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//				}
//
//				@Override
//				public void noClick() {
//					super.noClick();
//				}
//			};
//			dialog.show();
//			dialog.setCanceledOnTouchOutside(false);
//
//		} else {
//			try {
//				AnimUtil.addYiDianSong(v);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//
//	}
//
//	/**
//	 * 点击预览按钮
//	 */
//	@Override
//	public void gemingSongYuLanClick(SongBean song) {
//	   Toast.makeText(getActivity(), "预览歌曲->"+song.getSongName(),0).show();
//	   KtvPreviewDialog ktvPre = new KtvPreviewDialog(getActivity(), song){
//		   
//	   };
//	   ktvPre.show();
//	}
//
//}
