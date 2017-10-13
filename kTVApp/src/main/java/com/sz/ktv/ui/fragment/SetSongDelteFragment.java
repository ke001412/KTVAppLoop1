package com.sz.ktv.ui.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.greenrobot.eventbus.EventBus;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.sz.ktv.db.ktv.SongUtil;
import com.sz.ktv.msg.AsyncMessage;
import com.sz.ktv.ui.base.BaseFragment;
import com.sz.ktv.ui.fragment.adapter.DeleteSongAdapter;
import com.sz.ktv.util.FragmentFactory;
import com.sz.ktv.view.songxg.SongXGDianYingWindow;
import com.sz.ktv.view.songxg.SongXGFenLeiWindow;
import com.sz.ktv.view.songxg.SongXGLiuXingWindow;
import com.sz.ktv.view.songxg.SongXGWuQuWindow;
import com.sz.ktv.view.songxg.SongXGYuBieWindow;
import com.sz.ktv.view.songxg.SongXGZiShuWindow;
import com.sz.ktv.view.widget.InputDialog;
import com.sz.ktv.view.widget.InputDialog.Builder;
import com.sz.ktv.view.widget.InputDialog.OnDialogButtonClickListener;
import com.sz.ktv.view.widget.KtvBottomPageWidget;
import com.sz.ktv.view.widget.KtvBottomPageWidget.BackClickListener;
import com.sz.ktv.view.widget.UnderlineTextView;

public class SetSongDelteFragment extends BaseFragment implements
		OnClickListener, BackClickListener {

	KtvBottomPageWidget bottomPage;
	ListView songDelList;
	Button btPre;
	Button btNext;
	Button btAllSelect;
	Button btCurrentSelect;
	Button btLook;
	Button btCancle;
	Button btDelete;

	UnderlineTextView etSongNo;
	UnderlineTextView etSongName;
	UnderlineTextView etSingerName;
	UnderlineTextView etSongShouPin;
	UnderlineTextView etsongLanguage;
	UnderlineTextView etSongZiShu;
	UnderlineTextView etSongClassType;
	UnderlineTextView etSongWuQu;
	UnderlineTextView etSongDianYing;
	UnderlineTextView etSongReMen;
	UnderlineTextView etSongDianJiLv;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = null;
		try {
			view = inflater.inflate(R.layout.system_song_delete_layout,
					container, false);
		} catch (Exception e) {
			e.printStackTrace();
		}

		initView(view);
		return view;
	}

	private DeleteSongAdapter adapter; 
	List<Song> songList ;
	TextView showPage;
	
	private void initView(View view) {
		bottomPage = (KtvBottomPageWidget) view
				.findViewById(R.id.sys_song_delete_back);
		bottomPage.setBackClickListener(this);
		bottomPage.setType(KtvBottomPageWidget.TYPE_NO_DOTS_NO_PAGENUM);
   
		showPage = (TextView)view.findViewById(R.id.song_del_page_show);
		
		songDelList = (ListView) view.findViewById(R.id.song_del_list);
		btPre = (Button) view.findViewById(R.id.song_del_button_pre);
		btNext = (Button) view.findViewById(R.id.song_del_button_next);
		btAllSelect = (Button) view
				.findViewById(R.id.song_del_button_select_all);
		btCurrentSelect = (Button) view
				.findViewById(R.id.song_del_button_select_current_all);
		btLook = (Button) view.findViewById(R.id.song_del_button_look);
		btCancle = (Button) view.findViewById(R.id.song_del_button_cancle);
		btDelete = (Button) view.findViewById(R.id.song_del_button_shanchu);

		etSongNo = (UnderlineTextView) view.findViewById(R.id.song_del_no);
		etSongName = (UnderlineTextView) view
				.findViewById(R.id.song_del_geming);
		etSingerName = (UnderlineTextView) view
				.findViewById(R.id.song_del_gexing);
		etSongShouPin = (UnderlineTextView) view
				.findViewById(R.id.song_del_shoupin);
		etsongLanguage = (UnderlineTextView) view
				.findViewById(R.id.song_del_yubie);
		etSongZiShu = (UnderlineTextView) view
				.findViewById(R.id.song_del_zishu);
		etSongClassType = (UnderlineTextView) view
				.findViewById(R.id.song_del_leibie);
		etSongWuQu = (UnderlineTextView) view.findViewById(R.id.song_del_wuqu);
		etSongDianYing = (UnderlineTextView) view
				.findViewById(R.id.song_del_dianying);
		etSongReMen = (UnderlineTextView) view
				.findViewById(R.id.song_del_remen);
		etSongDianJiLv = (UnderlineTextView) view
				.findViewById(R.id.song_del_dianjilv);

		btPre.setOnClickListener(this);
		btNext.setOnClickListener(this);
		btAllSelect.setOnClickListener(this);
		btCurrentSelect.setOnClickListener(this);
		btLook.setOnClickListener(this);
		btCancle.setOnClickListener(this);
		btDelete.setOnClickListener(this);

		etSongNo.setOnClickListener(this);
		etSongName.setOnClickListener(this);
		etSingerName.setOnClickListener(this);
		etSongShouPin.setOnClickListener(this);
		etsongLanguage.setOnClickListener(this);
		etSongZiShu.setOnClickListener(this);
		etSongClassType.setOnClickListener(this);
		etSongWuQu.setOnClickListener(this);
		etSongDianYing.setOnClickListener(this);
		etSongReMen.setOnClickListener(this);
		etSongDianJiLv.setOnClickListener(this);

	}

	@Override
	public void onHiddenChanged(boolean hidden) {
		super.onHiddenChanged(hidden);
		if(!hidden)
		{
			seletAllSong();
		}
	}
	@Override
	public void back() {
		
		if(delMap.size() <=0)
		{
			EventBus.getDefault().post(
					new AsyncMessage(FragmentFactory.FRAGMENT_HOME_SYSTEM_SET));
			key="";
			type=SongUtil.TYPE_ALL;
			return ;
		}
		dialog = new ProgressDialog(getActivity());  
		dialog.setMessage("正在更新文件...");
		dialog.setCancelable(false);
		dialog.show(); 
		new Thread(){
			public void run() {
				try {
					DataBase.delSongAndWriteFile(delMap);
					delMap.clear();
					seletAllSong();
					getActivity().runOnUiThread(new Runnable() {
						
						@Override
						public void run() {
							dialog.dismiss();
						}
					});
					
					EventBus.getDefault().post(
							new AsyncMessage(FragmentFactory.FRAGMENT_HOME_SYSTEM_SET));
					key="";
					type=SongUtil.TYPE_ALL;
					
				} catch (Exception e) {
					 
				}finally{
					getActivity().runOnUiThread(new Runnable() {
						
						@Override
						public void run() {
							dialog.dismiss();
						}
					});
					
				}
			};
		}.start();
	
		
	}

	private Map<String, Song> delMap = new HashMap<String, Song>();
	
	Handler myHandler = new Handler()
	{
		public void dispatchMessage(Message msg) {
			int what = msg.what;
			switch (what) {
			case MSG_INIT_SONG:
				 
				songList = (List<Song>)msg.obj;
				if(songList == null || songList.size() <=0)
				{
					songDelList.setVisibility(View.GONE);
					return ;
				}else {
					songDelList.setVisibility(View.VISIBLE);
				}
				
				initSongData();
				adapter = new DeleteSongAdapter(getActivity(), songList,myHandler);
				showPage.setText("[ "+currentPage+"/"+allPage+" ]");
				songDelList.setAdapter(adapter);
				if(selectAll)
				{
					adapter.setSelectAll(true);
				}
				break;
			case MSG_SELECT_DEL:
				Song song =( Song)msg.obj;
				 
				String songNo = song.getSong_no();
				boolean delFlag = song.isSong_del;
				if(delFlag)
				{
					delMap.put(songNo, song);
					
				}else{
					delMap.remove(songNo);
				}
				System.out.println("DeLMAp"+delMap.toString());
				break;
			default:
				break;
			}
		};
	};
	 
	@Override
	public void onResume() {
		super.onResume();
		seletAllSong();
	};

	protected void initSongData() {
		 if(null != songList)
		 {
			 int size = songList.size();
			 for(int i=0;i<size;i++)
			 {
				 Song song = songList.get(i);
				 String songNo = song.getSong_no();
				 
				 Song tempSong = delMap.get(songNo);
				 if(null != tempSong)
				 {
					 song.setSong_del(tempSong.isSong_del);
					 continue;
				 }
				 if(selectCurrentAll)
				 {
					 song.setSong_del(true);
					 delMap.put(songNo, song);
				 }else {
					 song.setSong_del(false);
					 delMap.remove(songNo);
				 }
				 
				 if(selectAll)
				 {
					 delMap.put(songNo, song);
					 song.setSong_del(true);
				 }else {
					 delMap.remove(songNo);
					 song.setSong_del(false);
				 }
			 }
			 selectCurrentAll = false;
		 }
		
	}

	private int allPage; 
	private static final int MSG_INIT_SONG = 10;
	public static final int MSG_SELECT_DEL = 11;
	
	int currentPage = 1;
	private int type = SongUtil.TYPE_ALL ;
	private String key="";
	
	public void seletAllSong(){
		
		new Thread(){
			public void run() {
				System.out.println("type="+ type +" = "+ key);
				SongUtil.getInstance().initSongDeletePageByKey(type,key);
				allPage = SongUtil.getInstance().getTotalPages();
				List<Song> songPage = SongUtil.getInstance().getDeleteSongList(currentPage);
				Message msg = myHandler.obtainMessage();
				msg.obj = songPage;
				msg.what=MSG_INIT_SONG;
				myHandler.sendMessage(msg);
			};
		}.start();
	}
	
	boolean selectAll = false;;
	boolean selectCurrentAll = false;
	
	public void setAllClear()
	{
		etSongNo.setText("");
		etSongName.setText("");
		etSingerName.setText("");
		etSongShouPin.setText("");
		etsongLanguage.setText("");
		etSongZiShu.setText("");
		etSongClassType.setText("");
		etSongWuQu.setText("");
		etSongDianYing.setText("");
		etSongReMen.setText("");
		etSongDianJiLv.setText("");
	}
	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.song_del_no:

			InputDialog.Builder noBuilder = new Builder(getActivity(),R.style.input_dialog_style);
			noBuilder.setOnDialogButtonClickListener(new OnDialogButtonClickListener() {
				
				@Override
				public void onDialogButtonClick(String checkCode) {
					
					setAllClear();
					currentPage = 1;
					etSongNo.setText(checkCode);
					type = SongUtil.TYPE_SONG_NO;
					key = checkCode;
					seletAllSong();
				}
			});
			noBuilder.show();
			 
			
			break;
		case R.id.song_del_geming:
			
			InputDialog.Builder gmBuilder = new Builder(getActivity(),R.style.input_dialog_style);
			gmBuilder.setOnDialogButtonClickListener(new OnDialogButtonClickListener() {
				
				@Override
				public void onDialogButtonClick(String checkCode) {
				
					setAllClear();
					currentPage = 1;
					etSongName.setText(checkCode);
					type = SongUtil.TYPE_SONG_NAME;
					key = checkCode;
					seletAllSong();
					
				}
			});
			gmBuilder.show();
			 
			break;
		case R.id.song_del_gexing:
			 InputDialog.Builder builder = new Builder(getActivity(),R.style.input_dialog_style);
			 builder.setOnDialogButtonClickListener(new OnDialogButtonClickListener() {
				
				@Override
				public void onDialogButtonClick(String checkCode) {
					
					setAllClear();
					currentPage = 1;
					etSingerName.setText(checkCode);
					type = SongUtil.TYPE_SONG_SINGER;
					key = checkCode;
					seletAllSong();
					
				}
			});
			 builder.show();
			break;
		case R.id.song_del_shoupin:

			 InputDialog.Builder spBuilder = new Builder(getActivity(),R.style.input_dialog_style);
			 spBuilder.setOnDialogButtonClickListener(new OnDialogButtonClickListener() {
				
				@Override
				public void onDialogButtonClick(String checkCode) {
					setAllClear();
					currentPage = 1;
					etSongShouPin.setText(checkCode);
					type = SongUtil.TYPE_SONG_FRIST_NAME;
					key = checkCode;
					seletAllSong();
				}
			});
			 spBuilder.show();
			 
			break;
		case R.id.song_del_yubie:
			SongXGYuBieWindow yuyanWindow = new SongXGYuBieWindow(getActivity()) {
				@Override
				public void yuyanClick(String showText, String yuyan) {
					super.yuyanClick(showText, yuyan);
					setAllClear();
					currentPage = 1;
					etsongLanguage.setText(showText);
					type = SongUtil.TYPE_SONG_LANGUAGE;
					key = yuyan;
					seletAllSong();
				}
			};
			yuyanWindow.showPopupWindow(etsongLanguage);
			break;
		case R.id.song_del_zishu:
			SongXGZiShuWindow zishuWindow = new SongXGZiShuWindow(getActivity()) {
				@Override
				public void zishuClick(String showText, int yuyan) {
					super.zishuClick(showText, yuyan);
					setAllClear();
					currentPage = 1;
					
					etSongZiShu.setText(showText + "");
					type = SongUtil.TYPE_SONG_COUNT;
					key = yuyan+"";
					seletAllSong();
				}
			};
			zishuWindow.showPopupWindow(etSongZiShu);
			break;
		case R.id.song_del_leibie:
			SongXGFenLeiWindow fenleiWindow = new SongXGFenLeiWindow(
					getActivity()) {
				@Override
				public void fenleiClick(String showText, String yuyan) {
					super.fenleiClick(showText, yuyan);
					setAllClear();
					currentPage = 1;
					
					etSongClassType.setText(showText);
					type = SongUtil.TYPE_SONG_CLASS;
					key = yuyan;
					seletAllSong();
				}
			};
			fenleiWindow.showPopupWindow(etSongClassType);
			break;
		case R.id.song_del_wuqu:
			SongXGWuQuWindow wuquWindow = new SongXGWuQuWindow(getActivity()) {
				@Override
				public void wuquClick(String showText, String yuyan) {
					super.wuquClick(showText, yuyan);
					setAllClear();
					currentPage = 1;
					
					etSongWuQu.setText(showText);
					type = SongUtil.TYPE_SONG_DANCE;
					key = yuyan;
					seletAllSong();
				}
			};

			wuquWindow.showPopupWindow(etSongWuQu);
			break;
		case R.id.song_del_dianying:
			SongXGDianYingWindow dianyingWindow = new SongXGDianYingWindow(
					getActivity()) {
				@Override
				public void dianyingClick(String showText, String yuyan) {
					super.dianyingClick(showText, yuyan);
					setAllClear();
					currentPage = 1;
					
					etSongDianYing.setText(showText);
					type = SongUtil.TYPE_SONG_FILEM;
					key = yuyan;
					seletAllSong();
				}

			};
			dianyingWindow.showPopupWindow(etSongDianYing);
			break;
		case R.id.song_del_remen:
			SongXGLiuXingWindow liuxingWindow = new SongXGLiuXingWindow(
					getActivity()) {
				@Override
				public void liuxingClick(String showText, String yuyan) {
					super.liuxingClick(showText, yuyan);
					setAllClear();
					currentPage = 1;
					
					etSongReMen.setText(showText);
					type = SongUtil.TYPE_SONG_POPULAR;
					key = yuyan;
					seletAllSong();
				}
			};
			liuxingWindow.showPopupWindow(etSongReMen);
			break;
		case R.id.song_del_dianjilv:

			break;

		case R.id.song_del_button_pre:
			if(currentPage>1)
			{
				currentPage = currentPage-1;
				seletAllSong();
			}
			break;
		case R.id.song_del_button_next:
			if(currentPage<allPage)
			{
				currentPage = currentPage+1;
				seletAllSong();
			}
			break;
		case R.id.song_del_button_select_all:
			
			selectAll = true;
			adapter.setSelectAll(true);
			
			
			break;
		case R.id.song_del_button_select_current_all:
			selectCurrentAll = true;
			adapter.setSelectCurrentAll(true);
			
			break;
		case R.id.song_del_button_cancle:
			
			selectAll = false;
			selectCurrentAll = false;
			adapter.setSelectCurrentAll(false);
			adapter.setSelectAll(false);
			adapter.setSelectAllCancle(true);
			
			break;
		case R.id.song_del_button_shanchu:
			DataBase.delSongListByMap(delMap);
			seletAllSong();
			break;
		case R.id.song_del_button_look:

			break;

		default:
			break;
		}
	}
	ProgressDialog dialog = null;
	
	private List<Song> getSongDeleteListByMap() {
		if(delMap.size()<=0)
		{
			return null;
		}
		List<Song> list = new ArrayList<Song>();
		for (String key : delMap.keySet()) {
			  Song song = (Song)delMap.get(key);
			  list.add(song);
			  }
		return list;
	}

}
