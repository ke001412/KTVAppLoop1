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
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.sz.ktv.R;
import com.sz.ktv.db.DataBase;
import com.sz.ktv.db.Song;
import com.sz.ktv.db.ktv.SongNextBean;
import com.sz.ktv.db.ktv.SongPage;
import com.sz.ktv.db.ktv.SongUtil;
import com.sz.ktv.intf.ScrollChangeListener;
import com.sz.ktv.msg.AsyncMessage;
import com.sz.ktv.msg.SongTypeMessage;
import com.sz.ktv.ui.base.BaseFragment;
import com.sz.ktv.ui.fragment.adapter.AdapterGeming;
import com.sz.ktv.ui.fragment.adapter.GemingAdapter;
import com.sz.ktv.ui.inter.GemingClickListener;
import com.sz.ktv.util.AnimUtil;
import com.sz.ktv.util.FragmentFactory;
import com.sz.ktv.view.MyGridLayout;
import com.sz.ktv.view.MyGridLayout.OnItemClickListener;
import com.sz.ktv.view.cycle.CycleViewPager;
import com.sz.ktv.view.cycle.CycleViewPager.OnPageChangeListener;
import com.sz.ktv.view.dialog.KtvConfirmDialog;
import com.sz.ktv.view.widget.KtvBottomPageWidget;
import com.sz.ktv.view.widget.KtvBottomPageWidget.BackClickListener;

public class Copy_4_of_HomeGeMingFragment extends BaseFragment implements
		OnClickListener, BackClickListener, OnItemClickListener,
		ScrollChangeListener, GemingClickListener {

	public static final int TYPE_LEIBIE_SRGQ = 1;
	public static final int TYPE_LEIBIE_GMGQ = 2;
	public static final int TYPE_LEIBIE_XQXS = 3;
	public static final int TYPE_LEIBIE_HJGQ = 4;
	public static final int TYPE_LEIBIE_ETGQ = 5;
	public static final int TYPE_LEIBIE_HCGQ = 6;
	public static final int TYPE_LEIBIE_XQGQ = 7;
	public static final int TYPE_LEIBIE_XSXP = 8;

	public static final int TYPE_LIUXING_ZGHSY = 9;
	public static final int TYPE_LIUXING_WSGS = 10;
	public static final int TYPE_LIUXING_ZGHGQ = 11;
	public static final int TYPE_LIUXING_ZMHS = 12;
	public static final int TYPE_LIUXING_ZGYC = 13;
	public static final int TYPE_LIUXING_ZGZQY = 14;
	public static final int TYPE_LIUXING_MXXDD = 15;
	public static final int TYPE_LIUXING_MMGW = 16;

	public static final int TYPE_WUQU_MY = 17;
	public static final int TYPE_WUQU_HEZ = 18;
	public static final int TYPE_WUQU_QQ = 19;
	public static final int TYPE_WUQU_DSG = 20;
	public static final int TYPE_WUQU_PLS = 21;
	public static final int TYPE_WUQU_CS = 22;
	public static final int TYPE_WUQU_JTB = 23;
	public static final int TYPE_WUQU_TG = 24;

	public static final int TYPE_DY_DZ = 34;
	public static final int TYPE_DY_XJ = 35;
	public static final int TYPE_DY_GZ = 36;
	public static final int TYPE_DY_FJ = 37;
	public static final int TYPE_DY_KH = 38;
	public static final int TYPE_DY_ZZ = 39;
	public static final int TYPE_DY_DH = 40;
	public static final int TYPE_DY_QT = 41;

	public static final int TYPE_SONG_SINGER = 25;

	public static final int TYPE_SONG_GUOYU = 26;
	public static final int TYPE_SONG_YUEYU = 27;
	public static final int TYPE_SONG_MINNANYU = 28;
	public static final int TYPE_SONG_YINGYU = 29;
	public static final int TYPE_SONG_RIYU = 30;
	public static final int TYPE_SONG_HANYU = 31;
	public static final int TYPE_SONG_QITA = 32;
	public static final int TYPE_SONG_QUANBU = 33;

	public static final int TYPE_SONG_SHOUPIN = 42;
	public static final int TYPE_SONG_ZISHU = 43;

	private Activity activity;

	public static final int RETURN_TYPE_WUQU = 1;
	public static final int RETURN_TYPE_REMEN = 2;
	public static final int RETURN_TYPE_LEIBIE = 3;
	public static final int RETURN_TYPE_SINGER = 4;
	public static final int RETURN_TYPE_SONG = 5;
	public static final int RETURN_TYPE_DAOHANG = 6;

	KtvBottomPageWidget bottomPage;
	Button gemingGuoyu;
	Button gemingYueyu;
	Button gemingMinnanyu;
	Button gemingYingyu;
	Button gemingRiyu;
	Button gemingHanyu;
	Button gemingQita;
	Button gemingQuanbu;

	// ScrollLayout scrollLayout;

	MyGridLayout firstPage;
	MyGridLayout secodePage;
	MyGridLayout thirdPage;

	AdapterGeming firstAdapter;
	AdapterGeming secondAdapter;
	AdapterGeming thirdAdapter;

	List<Song> firstPageList = new ArrayList<Song>();
	List<Song> secondPageList = new ArrayList<Song>();
	List<Song> thirdPageList = new ArrayList<Song>();

	// PageSongUtil page = null;
	public static final int PAGE_SIZE = 9;
	// List<SongBean> all = new ArrayList<SongBean>();
	private static final int COLUMS = 3;
	private static final int ROWS = 3;
	private int allPage = 0;
	int currentpage = 1;
	private HashMap<Integer, MyGridLayout> viewMap = new HashMap<Integer, MyGridLayout>();
	TextView gemingTitle;
	public String gTitle;
	public String lastTile = "全部";

	public static int returnType = -1;
 
	private boolean loadFlag = true;

	CycleViewPager cycleViewPager;

	NextSongThread nextSongThread;
	View view = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		gTitle = "点歌->歌名点歌->" + lastTile;
		activity = getActivity();

		try {
			view = inflater.inflate(R.layout.copygeming_layout, container,
					false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// page = new PageSongUtil(PAGE_SIZE, DataBase.songList);
		// DataBase.keyType = SongUtil.TYPE_SONG_NAME;
		loadFlag = true;
		initView(view);
		initData();
		initEventBus();
		nextSongThread = new NextSongThread();

		return view;
	}
 
	private void initEventBus() {
		EventBus.getDefault().register(this);

	}

	static final Copy_4_of_HomeGeMingFragment INSTANCE = new Copy_4_of_HomeGeMingFragment();

	public static Copy_4_of_HomeGeMingFragment getInstance() {
		return Copy_4_of_HomeGeMingFragment.INSTANCE;
	}

	@Subscribe(threadMode = ThreadMode.ASYNC)
	public void onAsyncEventBus(SongTypeMessage msg) {

		Message message = handler.obtainMessage();
		message.obj = msg;
		message.what = MSG_CHANGE_SONG_TYPE;
		handler.sendMessage(message);
	}

	private static final int MSG_CHANGE_SONG_TYPE = 100;
	private static final int MSG_SEARCH_SONG_BY_NAME = 101;

	Handler handler = new Handler() {
		public void dispatchMessage(Message msg) {
			int what = msg.what;
			SongTypeMessage songTypeMsg = (SongTypeMessage) msg.obj;
			switch (what) {
			case MSG_CHANGE_SONG_TYPE:
				int type = songTypeMsg.getType();
				returnType = songTypeMsg.getReturnType();
				String keyString = songTypeMsg.getKeyWords();
				String preTile = songTypeMsg.getPreTileText();

				gTitle = preTile;
				lastTile = "全部";

				setTitle();

				switch (DataBase.keyType) {
				case TYPE_SONG_ZISHU:

					refreshAllSongBySongZiShu(keyString);
					break;
				case TYPE_SONG_SHOUPIN:
					String songShouPin = keyString;
					DataBase.keyType = SongUtil.TYPE_SONG_NAME;
					// gTitle = "点歌->歌星反查->" + songSingerName + "->";
					// lastTile = "全部";
					// setTitle();
					refreshAllSongBySongShouPin(songShouPin);
					break;
				case TYPE_SONG_GUOYU:

					refreshAllSongBySongLanguage(DataBase.SONG_TYPE_GUOYU);

					break;
				case TYPE_SONG_YUEYU:
					refreshAllSongBySongLanguage(DataBase.SONG_TYPE_YUEYU);
					break;
				case TYPE_SONG_MINNANYU:
					refreshAllSongBySongLanguage(DataBase.SONG_TYPE_MINNANYU);
					break;
				case TYPE_SONG_YINGYU:
					refreshAllSongBySongLanguage(DataBase.SONG_TYPE_YINGYU);
					break;
				case TYPE_SONG_RIYU:
					refreshAllSongBySongLanguage(DataBase.SONG_TYPE_RIYU);
					break;
				case TYPE_SONG_HANYU:
					refreshAllSongBySongLanguage(DataBase.SONG_TYPE_HANYU);
					break;
				case TYPE_SONG_QITA:
					refreshAllSongBySongLanguage(DataBase.SONG_TYPE_QITA);
					break;
				case TYPE_SONG_QUANBU:
					refresAllSongBySongLanAll();
					break;

				case TYPE_SONG_SINGER:

					String songSingerName = keyString;
					currentSinger = songSingerName;

					gTitle = "点歌->歌星反查->" + songSingerName + "->";
					lastTile = "全部";
					setTitle();
					refreshAllSongBySingerName(songSingerName);
					break;
				case TYPE_LEIBIE_SRGQ:
					refreshAllSongBySongType(keyString);
					break;
				case TYPE_LEIBIE_GMGQ:
					refreshAllSongBySongType(keyString);
					break;
				case TYPE_LEIBIE_XQXS:
					refreshAllSongBySongType(keyString);
					break;
				case TYPE_LEIBIE_HJGQ:
					refreshAllSongBySongType(keyString);
					break;
				case TYPE_LEIBIE_ETGQ:
					refreshAllSongBySongType(keyString);
					break;
				case TYPE_LEIBIE_HCGQ:
					refreshAllSongBySongType(keyString);
					break;
				case TYPE_LEIBIE_XQGQ:
					refreshAllSongBySongType(keyString);
					break;
				case TYPE_LEIBIE_XSXP:
					refreshAllSongBySongType(keyString);
					break;
				case TYPE_LIUXING_ZGHSY:
					refreshAllSongBySongLiuxing(keyString);
					break;
				case TYPE_LIUXING_WSGS:
					refreshAllSongBySongLiuxing(keyString);
					break;
				case TYPE_LIUXING_ZGHGQ:
					refreshAllSongBySongLiuxing(keyString);
					break;
				case TYPE_LIUXING_ZMHS:
					refreshAllSongBySongLiuxing(keyString);
					break;
				case TYPE_LIUXING_ZGYC:
					refreshAllSongBySongLiuxing(keyString);
					break;
				case TYPE_LIUXING_ZGZQY:
					refreshAllSongBySongLiuxing(keyString);
					break;
				case TYPE_LIUXING_MXXDD:
					refreshAllSongBySongLiuxing(keyString);
					break;
				case TYPE_LIUXING_MMGW:
					refreshAllSongBySongLiuxing(keyString);
					break;
				case TYPE_WUQU_MY:
					refreshAllSongBySongWuqu(keyString);
					break;
				case TYPE_WUQU_HEZ:
					refreshAllSongBySongWuqu(keyString);
					break;
				case TYPE_WUQU_QQ:
					refreshAllSongBySongWuqu(keyString);
					break;
				case TYPE_WUQU_DSG:
					refreshAllSongBySongWuqu(keyString);
					break;
				case TYPE_WUQU_PLS:
					refreshAllSongBySongWuqu(keyString);
					break;
				case TYPE_WUQU_CS:
					refreshAllSongBySongWuqu(keyString);
					break;
				case TYPE_WUQU_JTB:
					refreshAllSongBySongWuqu(keyString);
					break;
				case TYPE_WUQU_TG:
					refreshAllSongBySongWuqu(keyString);
					break;
				case TYPE_DY_DZ:
					refreshAllSongBySongDianying(keyString);
					break;
				case TYPE_DY_XJ:
					refreshAllSongBySongDianying(keyString);
					break;
				case TYPE_DY_GZ:
					refreshAllSongBySongDianying(keyString);
					break;
				case TYPE_DY_FJ:
					refreshAllSongBySongDianying(keyString);
					break;
				case TYPE_DY_KH:
					refreshAllSongBySongDianying(keyString);
					break;
				case TYPE_DY_ZZ:
					refreshAllSongBySongDianying(keyString);
					break;
				case TYPE_DY_DH:
					refreshAllSongBySongDianying(keyString);
					break;
				case TYPE_DY_QT:
					refreshAllSongBySongDianying(keyString);
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

	/**
	 * 歌曲分类
	 * 
	 * @param type
	 */
	public void refreshAllSongBySongType(String classType) {
		// List<SongBean> freshList =
		// DataBase.getAllSongByFenLei(SongUtil.keyType);
		// page = new PageSongUtil(PAGE_SIZE, freshList);
		DataBase.keyType = SongUtil.TYPE_SONG_CLASS;
		DataBase.keySong = classType;
		reloadAll();
	}

	/**
	 * 舞曲
	 * 
	 * @param type
	 */
	public void refreshAllSongBySongWuqu(String dType) {
		// List<SongBean> freshList = DataBase.getAllSongByWuQugFenLei(type);
		// page = new PageSongUtil(PAGE_SIZE, freshList);
		DataBase.keyType = SongUtil.TYPE_SONG_DANCE;
		DataBase.keySong = dType;
		reloadAll();
	}

	/**
	 * 电影分类
	 * 
	 * @param type
	 */
	public void refreshAllSongBySongDianying(String filemType) {
		// List<SongBean> freshList = DataBase.getAllSongByDianYingFenLei(type);
		// page = new PageSongUtil(PAGE_SIZE, freshList);
		DataBase.keyType = SongUtil.TYPE_SONG_FILEM;
		DataBase.keySong = filemType;
		reloadAll();
	}

	/**
	 * 流行分类
	 * 
	 * @param type
	 */
	public void refreshAllSongBySongLiuxing(String pType) {
		// List<SongBean> freshList = DataBase.getAllSongByLiuXingFenLei(type);
		// page = new PageSongUtil(PAGE_SIZE, freshList);
		DataBase.keyType = SongUtil.TYPE_SONG_POPULAR;
		DataBase.keySong = pType;
		reloadAll();
	}

	/**
	 * 按照歌星搜索歌曲
	 * 
	 * @param type
	 */
	public void refreshAllSongBySingerName(String singerName) {
		// List<SongBean> freshList =
		// DataBase.getAllSongBySingerName(singerName);
		// page = new PageSongUtil(PAGE_SIZE, freshList);
		DataBase.keySong = singerName;
		DataBase.keyType = SongUtil.TYPE_SONG_SINGER;
		reloadAll();
	}

	/**
	 * 按照首拼搜索歌曲
	 * 
	 * @param type
	 */
	public void refreshAllSongBySongShouPin(String shouPin) {
		// List<SongBean> freshList = DataBase.getAllSongBySongShouPin(shouPin);
		// page = new PageSongUtil(PAGE_SIZE, freshList);
		DataBase.keyType = SongUtil.TYPE_SONG_FRIST_NAME;
		DataBase.keySong = shouPin;

		reloadAll();

		// initDataByShouPin(shouPin);

	}

	private void initDataByShouPin(final String shoupin) {
		new Thread() {
			public void run() {
				SongUtil.getInstance().initSongPageByKey(
						SongUtil.TYPE_SONG_NAME, shoupin);
				allPage = SongUtil.getInstance().getTotalPages();
				SongPage songPage = SongUtil.getInstance().getDefaultSongList(
						DEFAULT_INIT_SIZE);// SongUtil.getInstance().getSongPage(1);
				Message msg = myHandler.obtainMessage();
				msg.obj = songPage;
				msg.what = MSG_INIT_SONG;
				myHandler.sendMessage(msg);
			};
		}.start();
	}

	/**
	 * 按照首拼搜索歌曲
	 * 
	 * @param type
	 */
	public void refreshAllSongBySongZiShu(String zishu) {
//		List<Song> freshList = DataBase.getAllSongByWordsCount(zishu);
		// page = new PageSongUtil(PAGE_SIZE, freshList);
		DataBase.keyType = SongUtil.TYPE_SONG_COUNT;
		DataBase.keySong = zishu;
		reloadAll();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		EventBus.getDefault().unregister(this);

	}

	MyPageAdapter pageAdapter = null;
 

	public void method2() {
		// 最后插入页码的地方
		if (!isOver) {
			return;
		}
		isOver = false;
		int tempPage = 1;
		nextPageAddNum = nextPageAddNum + 1;

		nextPage = nextPage + 1;
		lastPage = lastPage + 1;
		tempPage = allPage - lastPage;

		int all = pages.size();
		System.out.println(" allPage: " + all);

		if (all >= allPage) {
			return;
		}
		SongNextBean bean = new SongNextBean();
		if (nextPage == tempPage) {
			List<Song> listSong1 = SongUtil.getInstance().getCurrentSongPage(
					nextPage);
			bean.setList1(listSong1);

		} else {
			List<Song> listSong1 = SongUtil.getInstance().getCurrentSongPage(
					nextPage);
			List<Song> listSong2 = SongUtil.getInstance().getCurrentSongPage(
					tempPage);
			bean.setList1(listSong1);
			bean.setList2(listSong2);
		}

		Message msgs = new Message();
		msgs.obj = bean;
		msgs.what = MSG_FRESH;
		myHandler.sendMessage(msgs);
	}

	class NextSongThread extends Thread {
		@Override
		public void run() {
			super.run();
			System.out.println("开始更新下个数据");
			method2();
			System.out.println("更新数据结束..");
		}
	};

	OnPageChangeListener pageChangeListener = new OnPageChangeListener() {

		@Override
		public void onPageSelected(final int position, boolean prePage,
				boolean nextPage) {

			if (prePage) {
				prePageFlag = true;
				nextPageFlag = false;

				currentpage = currentpage - 1;
			}

			if (nextPage) {
				// 下一页
				nextPageFlag = true;
				prePageFlag = false;

				currentpage = currentpage + 1;
			}

			if (currentpage <= 0) {
				currentpage = allPage;
			}
			if (currentpage > allPage) {
				currentpage = 1;
			}
			bottomPage.setCurrentPage(currentpage);

			if (shuldAddFlag) {
				if (null != nextSongThread) {
					nextSongThread.interrupt();
					nextSongThread = null;
				}
				int pagess = pages.size();
				if (pagess < allPage) {
					nextSongThread = new NextSongThread();
					nextSongThread.start();
				}
			}

		}

		@Override
		public void onPageScrolled(int position, float positionOffset,
				int positionOffsetPixels) {

		}

		@Override
		public void onPageScrollStateChanged(int state) {

		}
	};

	private boolean shuldAddFlag = true;

	private void initView(View view) {

		cycleViewPager = (CycleViewPager) view
				.findViewById(R.id.geming_scroll_layout);
		 
		gemingTitle = (TextView) view.findViewById(R.id.geming_title);
		gemingTitle.setText(gTitle);

		bottomPage = (KtvBottomPageWidget) view.findViewById(R.id.geming_back);
		bottomPage.setType(KtvBottomPageWidget.TYPE_DOTS);
		bottomPage.setBackClickListener(this);

		gemingGuoyu = (Button) view.findViewById(R.id.geming_guoyu);
		gemingYueyu = (Button) view.findViewById(R.id.geming_yueyu);
		gemingMinnanyu = (Button) view.findViewById(R.id.geming_minnanyu);
		gemingYingyu = (Button) view.findViewById(R.id.geming_yingyu);
		gemingRiyu = (Button) view.findViewById(R.id.geming_riyu);
		gemingHanyu = (Button) view.findViewById(R.id.geming_hanyu);
		gemingQita = (Button) view.findViewById(R.id.geming_qita);
		gemingQuanbu = (Button) view.findViewById(R.id.geming_quanbu);

		gemingGuoyu.setOnClickListener(this);
		gemingYueyu.setOnClickListener(this);
		gemingMinnanyu.setOnClickListener(this);
		gemingYingyu.setOnClickListener(this);
		gemingRiyu.setOnClickListener(this);
		gemingHanyu.setOnClickListener(this);
		gemingQita.setOnClickListener(this);
		gemingQuanbu.setOnClickListener(this);

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
			((CycleViewPager) container).removeView(pages.get(position));
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			if (null == pages || pages.size() <= 0) {
				return null;
			}
			((CycleViewPager) container).addView(pages.get(position));
			return pages.get(position);
		}

	}

	public static final int MSG_INIT_SONG = 0x1001;
	public static final int MSG_ON_RIGHT_LEFT = 0x1002;

	Handler myHandler = new Handler() {
		public void dispatchMessage(Message msg) {
			int what = msg.what;
			switch (what) {
			case MSG_FRESH:
				// List<Song>list = (List<Song>)msg.obj;
				// View view = pages.get(viewPageNum);
				// initViwePage(list, view);
				// List<Song>list = (List<Song>)msg.obj;
				SongNextBean song = (SongNextBean) msg.obj;

				addNewView(song, nextPageAddNum);

				break;
			case MSG_ON_RIGHT_LEFT:
				int prePage = msg.arg1;
				int nextPage = msg.arg2;
				SongPage songPage1 = (SongPage) msg.obj;
				updateViews(prePage, songPage1, nextPage);

				break;
			case MSG_INIT_SONG:

				SongPage songPage = (SongPage) msg.obj;
				initSongView(songPage);
				break;

			default:
				break;
			}
		};

	};

	private static final int DEFAULT_INIT_SIZE = 10;

	private int defaultPages = DEFAULT_INIT_SIZE;
	// 最后初始化的页数
	int defaultLast = defaultPages / 2;
	// page的位置 默认是在3
	private int viewPageNum = defaultLast - 1;
	// 判断向左向右
	boolean nextPageFlag = false;
	boolean prePageFlag = false;

	private static final int MSG_FRESH = 12324;

	private int nextPageAddNum = defaultLast - 1;
	// 需要缓存的下个页数
	private int nextPage = defaultLast;
	// 需要缓存的上个页数
	private int lastPage = defaultLast - 1;
	boolean isOver = true;

	
	private void initData() {
		
		currentpage = 1;
		defaultPages = DEFAULT_INIT_SIZE;
		nextPageAddNum = defaultLast - 1;
		// 需要缓存的下个页数
		nextPage = defaultLast;
		// 需要缓存的上个页数
		lastPage = defaultLast - 1;

		new Thread() {
			public void run() {
				SongUtil.getInstance().initSongPageByKey(DataBase.keyType,
						DataBase.keySong);
				allPage = SongUtil.getInstance().getTotalPages();
				if (allPage > DEFAULT_INIT_SIZE) {
					defaultPages = DEFAULT_INIT_SIZE;
					shuldAddFlag = true;
				} else {
					defaultPages = allPage;
					shuldAddFlag = false;
				}
				// SongPage songPage = SongUtil.getInstance().getSongPage(1);
				SongPage songPage = SongUtil.getInstance().getDefaultSongList(
						defaultPages);
				Message msg = myHandler.obtainMessage();
				msg.obj = songPage;
				msg.what = MSG_INIT_SONG;
				myHandler.sendMessage(msg);
			};
		}.start();
	}

	protected void addNewView(SongNextBean bean, int nextPageAddNum2) {

		int all = pages.size();
		System.out.println(" allPage>>>>>: " + all);

		if (all >= allPage) {
			return;
		}

		List<Song> list1 = bean.getList1();
		List<Song> list2 = bean.getList2();
		if (null != list1 && list1.size() > 0) {
			View view1 = LayoutInflater.from(getActivity()).inflate(
					R.layout.page_item, null);
			pages.add(nextPageAddNum2, view1);

			initViwePage(list1, view1);
		}
		if (null != list2 && list2.size() > 0) {
			View view2 = LayoutInflater.from(getActivity()).inflate(
					R.layout.page_item, null);
			pages.add(nextPageAddNum2 + 1, view2);

			initViwePage(list2, view2);

			nextPageAddNum2 = nextPageAddNum2 + 1;
		}

		pageAdapter.notifyDataSetChanged();
		isOver = true;
		int size = pages.size();
		System.out.println("总页数:" + size);
	}

	protected void addNewView(List<Song> list, int nextPageAddNum2) {

		View view1 = LayoutInflater.from(getActivity()).inflate(
				R.layout.page_item, null);
		pages.add(nextPageAddNum2, view1);
		initViwePage(list, view1);
		pageAdapter.notifyDataSetChanged();
		isOver = true;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		System.out.println("  革命销毁");

	}

	ArrayList<View> pages = new ArrayList<View>();

	public void initSongView(SongPage songPage) {

		try {
			List<List<Song>> lists = songPage.getDefaultSongList();
			if (null != pages && pages.size() > 0) {
				cycleViewPager.removeAllViews();
				pages.clear();
				pages = new ArrayList<View>();
				pageAdapter.notifyDataSetChanged();
				pageAdapter = null;
				cycleViewPager.setAdapter(null);
			}
			for (int i = 0; i < defaultPages; i++) {
				View view1 = LayoutInflater.from(getActivity()).inflate(
						R.layout.page_item, null);
				pages.add(view1);

			}
			// if(null == pageAdapter)
			// {
			pageAdapter = new MyPageAdapter(pages);
			cycleViewPager.setAdapter(pageAdapter);
			// }else {
			// pageAdapter.notifyDataSetChanged();
			// }
			cycleViewPager.setOnPageChangeListener(pageChangeListener);

			int pagesss = pages.size();
			for (int i = 0; i < pagesss; i++) {
				List<Song> list = lists.get(i);
				View view0 = pages.get(i);
				initViwePage(list, view0);
			}
			bottomPage.setAllPage(allPage);
			bottomPage.setCurrentPage(currentpage);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void initViwePage(List<Song> pre, View view0) {
		GridView grid = (GridView) view0.findViewById(R.id.grid_view);
		grid.setSelector(new ColorDrawable(Color.TRANSPARENT));
		GemingAdapter adapter = new GemingAdapter(pre, getActivity());
		adapter.setGemingClickListener(this);
		grid.setAdapter(adapter);

	}

	private void reloadAll() {
	 
		initData();
	}

	private void clear() {
		DataBase.keySong = "";
		DataBase.keyType = -1;

		firstPageList = null;
		secodePage = null;
		thirdPage = null;

		// scrollLayout = null;
		secondPageList = null;
		thirdPageList = null;
		// scrollLayout = null;
		secodePage = null;
		SongUtil.getInstance().clear();
	}

	@Override
	public void back() {

		clear();

		currentSinger = "";
		if (SetGongBoFragment.isGongboSelectSongFlag) {
			gotoGongBoSetting();
			SetGongBoFragment.isGongboSelectSongFlag = false;

			return;
		}
		if (returnType == RETURN_TYPE_LEIBIE) {
			EventBus.getDefault().post(
					new AsyncMessage(FragmentFactory.FRAGMENT_HOME_LEIBIE));
			returnType = -1;
		} else if (returnType == RETURN_TYPE_REMEN) {
			EventBus.getDefault().post(
					new AsyncMessage(FragmentFactory.FRAGMENT_HOME_REMEN));
			returnType = -1;
		} else if (RETURN_TYPE_WUQU == returnType) {
			EventBus.getDefault().post(
					new AsyncMessage(FragmentFactory.FRAGMENT_HOME_WUQU));
			returnType = -1;
		} else if (RETURN_TYPE_SINGER == returnType) {
			EventBus.getDefault().post(
					new AsyncMessage(FragmentFactory.FRAGMENT_HOME_GEXING));
			returnType = -1;
		} else if (RETURN_TYPE_SONG == returnType) {
			EventBus.getDefault().post(
					new AsyncMessage(FragmentFactory.FRAGMENT_HOME_GEMING));
			returnType = -1;
		} else if (RETURN_TYPE_DAOHANG == returnType) {
			EventBus.getDefault().post(
					new AsyncMessage(FragmentFactory.FRAGMENT_HOME_DAOHANG));
			returnType = -1;
		} else {
			boolean songXiuGaiFlag = HomeSystemSetFragment.songUpdate;
			if (songXiuGaiFlag) {
				gotoSetting();
			} else {
				backToHome();
			}
			gTitle = "点歌->歌名点歌->";
			lastTile = "全部";
			setTitle();
		}

	}

	public void setTitle() {

		gemingTitle.setText(gTitle + lastTile);
	}

	private void refreshAllSongBySongLanguage(String languageType) {

		DataBase.keyType = SongUtil.TYPE_SONG_LANGUAGE;
 
		DataBase.keySong = languageType;
		reloadAll();

		if (languageType.equals(DataBase.SONG_TYPE_GUOYU)) {
			gTitle = "点歌->歌名点歌->";
			lastTile = "国语";
			setTitle();

		} else if (languageType.equals(DataBase.SONG_TYPE_YUEYU)) {
			gTitle = "点歌->歌名点歌->";
			lastTile = "粤语";
			setTitle();

		} else if (languageType.equals(DataBase.SONG_TYPE_MINNANYU)) {
			gTitle = "点歌->歌名点歌->";
			lastTile = "闽南语";
			setTitle();

		} else if (languageType.equals(DataBase.SONG_TYPE_YINGYU)) {
			gTitle = "点歌->歌名点歌->";
			lastTile = "英语";
			setTitle();

		} else if (languageType.equals(DataBase.SONG_TYPE_RIYU)) {
			gTitle = "点歌->歌名点歌->";
			lastTile = "日语";
			setTitle();

		} else if (languageType.equals(DataBase.SONG_TYPE_HANYU)) {
			gTitle = "点歌->歌名点歌->";
			lastTile = "韩语";
			setTitle();

		} else if (languageType.equals(DataBase.SONG_TYPE_QITA)) {
			gTitle = "点歌->歌名点歌->";
			lastTile = "其他";
			setTitle();
		}
	}
 

	private void refresAllSongBySongLanAll() {
		List<Song> allList = DataBase.songLocalList;
		// page = new PageSongUtil(PAGE_SIZE, allList);
		// key = "";
		reloadAll();

		gTitle = "点歌->歌名点歌->";
		lastTile = "全部";
		setTitle();
	}

	@Override
	public void onClick(View v) {
		currentSinger = "";

		int id = v.getId();
		switch (id) {
		case R.id.geming_guoyu:
			 
			refreshAllSongBySongLanguage(DataBase.SONG_TYPE_GUOYU);
			break;
		case R.id.geming_yueyu:
			 
			refreshAllSongBySongLanguage(DataBase.SONG_TYPE_YUEYU);

			break;
		case R.id.geming_minnanyu:
			 
			refreshAllSongBySongLanguage(DataBase.SONG_TYPE_MINNANYU);

			break;
		case R.id.geming_yingyu:
			 
			refreshAllSongBySongLanguage(DataBase.SONG_TYPE_YINGYU);

			break;
		case R.id.geming_riyu:
			 
			refreshAllSongBySongLanguage(DataBase.SONG_TYPE_RIYU);
			break;
		case R.id.geming_hanyu:
			 
			refreshAllSongBySongLanguage(DataBase.SONG_TYPE_HANYU);
			break;
		case R.id.geming_qita:
			 
			refreshAllSongBySongLanguage(DataBase.SONG_TYPE_QITA);

			break;
		case R.id.geming_quanbu:
			 
			DataBase.keySong = "";
			refresAllSongBySongLanAll();
			break;

		default:
			break;
		}
	}

	  
	@Override
	public void onResume() {
		super.onResume();

	}

	@Override
	public void onHiddenChanged(boolean hidden) {
		super.onHiddenChanged(hidden);

		currentSinger = "";
	 
		if (hidden) {
		 
			gTitle = "点歌->歌名点歌->";
			lastTile = "全部";
			// setTitle();
		} else {
			// initData();
		}
	}

	private void updateViews(int prePage, SongPage songPage, int nextPage) {

		bottomPage.setCurrentPage(currentpage);
		MyGridLayout prePageView = viewMap.get(prePage);
		MyGridLayout nextPageView = viewMap.get(nextPage);

		int prePageNo = currentpage - 1;
		int nextPageNo = currentpage + 1;

		if (nextPageNo > allPage) {
			nextPageNo = 1;
		}
		if (prePageNo <= 0) {
			prePageNo = allPage;
		}
		List<Song> prePageList = songPage.getPrePageList();
		List<Song> nextPageList = songPage.getNextPageList();

		AdapterGeming prePgeAdapter = (AdapterGeming) prePageView
				.getGridAdapter();
		AdapterGeming nextPgeAdapter = (AdapterGeming) nextPageView
				.getGridAdapter();

		prePgeAdapter.setData(prePageList);
		prePageView.reLoadView();

		nextPgeAdapter.setData(nextPageList);
		nextPageView.reLoadView();

		firstAdapter.setGemingClickListener(this);
		secondAdapter.setGemingClickListener(this);
		thirdAdapter.setGemingClickListener(this);
	}

	@Override
	public void onItemClick(View v, int index) {

	}

	public static String currentSinger = "";

	@Override
	public void gemingSingerClick(Song song, View v) {

		if(TextUtils.isEmpty(currentSinger))
		{
		currentSinger = song.getSong_singer_name();
//		SongTypeMessage msg = new SongTypeMessage();
//		msg.setKeyWords(currentSinger);
//		msg.setType(TYPE_SONG_SINGER);
//		msg.setReturnType(RETURN_TYPE_SONG);
//		EventBus.getDefault().post(msg);
		refreshAllSongBySingerName(currentSinger);
		}
	}

	public static Song songBean;

	@Override
	public void gemingSongClick(final Song song, final View v, TextView tips) {

		getActivity().runOnUiThread(new Runnable() {

			@Override
			public void run() {
				Toast.makeText(getActivity(), song.getSong_name(), 0).show();

			}
		});
		boolean songXiuGaiFlag = HomeSystemSetFragment.songUpdate;
		if (songXiuGaiFlag) {
			songBean = song;
			EventBus.getDefault().post(
					new AsyncMessage(FragmentFactory.FRAGMENT_SET_SONG_XIUGAI));
			HomeSystemSetFragment.songUpdate = false;
			return;
		}

		if (SetGongBoFragment.isGongboSelectSongFlag) {
			boolean ifGongboAdd = DataBase.isGongBoSongExist(activity, song);

			if (ifGongboAdd) {
				tips.setVisibility(View.VISIBLE);
				tips.setText("已经添加");
				tips.invalidate();
				return;
			}
			DataBase.writeGongBoSongToFile(activity, song);
			tips.setVisibility(View.VISIBLE);
			tips.setText("操作成功");
			tips.invalidate();

			return;
		}

		boolean isExist = DataBase.getInstance().checkSongExist(song);
		String downFlag = song.getSong_download_flag();
		if (!"1".equals(downFlag)) {
			KtvConfirmDialog dialog = new KtvConfirmDialog(activity, "云端歌曲!") {
				@Override
				public void yesClick() {
					super.yesClick();
					dismiss();
					AnimUtil.addYiDianSong(v, song);
				}

				@Override
				public void noClick() {
					super.noClick();
					dismiss();
				}
			};
			dialog.show();
			dialog.setCanceledOnTouchOutside(false);

			return;
		}
		if (isExist) {
			KtvConfirmDialog dialog = new KtvConfirmDialog(activity,
					"已经存在列表,是否继续点播?") {
				@Override
				public void yesClick() {
					super.yesClick();
					try {
						AnimUtil.addYiDianSong(v, song);
						// AnimationUtil.getInstance().playReadingAnimation(activity,
						// v);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				@Override
				public void noClick() {
					super.noClick();
				}
			};
			dialog.show();
			dialog.setCanceledOnTouchOutside(false);

		} else {
			try {
				AnimUtil.addYiDianSong(v, song);
				// AnimationUtil.getInstance().playReadingAnimation(activity,
				// v);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 点击预览按钮
	 */
	@Override
	public void gemingSongYuLanClick(Song song) {
		Toast.makeText(getActivity(), "预览歌曲->" + song.getSong_name(), 0).show();
		// KtvPreviewDialog ktvPre = new KtvPreviewDialog(getActivity(), song){
		//
		// };
		// ktvPre.show();
	}

	@Override
	public void nextStart() {
		System.out.println("NEXT start ++++++++++++++++++++++++++++++++++");

	}

	@Override
	public void preStart() {
		System.out.println("PREstart ++++++++++++++++++++++++++++++++++");

	}

	@Override
	public void onLeft(int prePage, int currentPage, int nextPage) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRight(int prePage, int currentPage, int nextPage) {
		// TODO Auto-generated method stub
		
	}

}
