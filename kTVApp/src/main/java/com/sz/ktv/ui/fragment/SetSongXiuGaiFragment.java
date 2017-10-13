package com.sz.ktv.ui.fragment;

import org.greenrobot.eventbus.EventBus;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.sz.ktv.R;
import com.sz.ktv.db.DataBase;
import com.sz.ktv.db.Song;
import com.sz.ktv.db.ktv.SongUtil;
import com.sz.ktv.msg.AsyncMessage;
import com.sz.ktv.ui.base.BaseFragment;
import com.sz.ktv.util.FragmentFactory;
import com.sz.ktv.view.songxg.SongXGDianYingWindow;
import com.sz.ktv.view.songxg.SongXGFenLeiWindow;
import com.sz.ktv.view.songxg.SongXGLiuXingWindow;
import com.sz.ktv.view.songxg.SongXGWuQuWindow;
import com.sz.ktv.view.songxg.SongXGYinGuiWindow;
import com.sz.ktv.view.songxg.SongXGYinLiangWindow;
import com.sz.ktv.view.songxg.SongXGYuBieWindow;
import com.sz.ktv.view.songxg.SongXGZiShuWindow;
import com.sz.ktv.view.widget.InputDialog;
import com.sz.ktv.view.widget.KtvBottomPageWidget;
import com.sz.ktv.view.widget.InputDialog.Builder;
import com.sz.ktv.view.widget.InputDialog.OnDialogButtonClickListener;
import com.sz.ktv.view.widget.KtvBottomPageWidget.BackClickListener;
import com.sz.ktv.view.widget.UnderlineTextView;

public class SetSongXiuGaiFragment extends BaseFragment implements
		OnClickListener, BackClickListener {

	KtvBottomPageWidget bottomPage;
	UnderlineTextView tvGeming;
	UnderlineTextView tvBianhao;
	UnderlineTextView tvYubie;
	UnderlineTextView tvShoupin;
	UnderlineTextView tvZishu;
	UnderlineTextView tvSinger;
	UnderlineTextView tvYingui;
	UnderlineTextView tvBanchang;
	UnderlineTextView tvYuanchang;
	UnderlineTextView tvFenlei;
	UnderlineTextView tvWuqu;
	UnderlineTextView tvDianying;
	UnderlineTextView tvLiuxing;
	UnderlineTextView tvGengxingtime;
	UnderlineTextView tvSongfile;

	Button songXGYes;

	private String songName = "";
	private String songSinger = "";
	private String songLan = "";
	private String songFirstName = "";
	private String songWordsCount = "";
	private String songTrack;
	private String songLeftVolume;
	private String songRightVolume;
	private String songClassType;
	private String songDanceType;
	private String songFilemType;
	private String songPopularType;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = null;
		try {
			view = inflater.inflate(R.layout.system_set_song_xiugai_layout,
					container, false);
		} catch (Exception e) {
			e.printStackTrace();
		}

		initView(view);

		return view;
	}

	private Song song;

	@Override
	public void onResume() {
		super.onResume();
		song = HomeGeMingFragment.songBean;
		initData();
	}

	@Override
	public void onHiddenChanged(boolean hidden) {
		// TODO Auto-generated method stub
		super.onHiddenChanged(hidden);
		if (!hidden) {
			song = HomeGeMingFragment.songBean;
			initData();
		}
	}

	private void initData() {
		 if(null != song)
		 { 
//			 tvGeming.setText(""+song.getSongName());
//			 tvBianhao.setText(""+song.getSongNo());
//			 tvYubie.setText(""+song.getSongLaunguageString());
//			 tvShoupin.setText(""+song.getSongFirst());
//			 tvZishu.setText(""+song.getSongCount());
//			 tvSinger.setText(""+song.getSongSinger());
//			 tvYingui.setText(""+song.getSongTrack());
//			 tvBanchang.setText(""+song.getSongLeftVolum());
//			 tvYuanchang.setText(""+song.getSongRightVolum());
//			 tvFenlei.setText(""+song.getSongType());
//			 tvWuqu.setText(""+song.getSongDanceType());
//			 tvDianying.setText(""+song.getSongFilmType());
//			 tvLiuxing.setText(""+song.getSongPopularType());
//			 tvGengxingtime.setText("#");
//			 tvSongfile.setText(""+song.getSongFileName());
			 tvGeming.setText(""+song.getSong_name());
			 tvBianhao.setText(""+song.getSong_no());
			 tvYubie.setText(""+song.getSongLaunguageString());
			 tvShoupin.setText(""+song.getSong_first_name());
			 tvZishu.setText(""+song.getSong_words_count());
			 tvSinger.setText(""+song.getSong_singer_name());
			 tvYingui.setText(""+song.getSong_track());
			 tvBanchang.setText(""+song.getSong_left_volume());
			 tvYuanchang.setText(""+song.getSong_right_volume());
			 tvFenlei.setText(""+song.getSong_class());
			 tvWuqu.setText(""+song.getSong_dance_type());
			 tvDianying.setText(""+song.getSong_film_type());
			 tvLiuxing.setText(""+song.getSong_popular_type());
			 tvGengxingtime.setText("#");
			 tvSongfile.setText(""+song.getSong_file_name());
			 
			 
			 songName=song.getSong_name();
			  songSinger=song.getSong_singer_name();
			  songLan=song.getSong_language();
			 	songFirstName=song.getSong_first_name();
			 	songWordsCount=song.getSong_words_count();
				  songTrack=song.getSong_track();
				  songLeftVolume=song.getSong_left_volume();
				  songRightVolume = song.getSong_right_volume();
				  songClassType = song.getSong_class();
				  songDanceType = song.getSong_dance_type();
				  songFilemType = song.getSong_film_type();
				  songPopularType = song.getSong_popular_type();
				
		 }
}

	private void initView(View view) {
		bottomPage = (KtvBottomPageWidget) view.findViewById(R.id.xiugai_back);
		bottomPage.setBackClickListener(this);
		bottomPage.setType(KtvBottomPageWidget.TYPE_NO_DOTS_NO_PAGENUM);

		tvGeming = (UnderlineTextView) view.findViewById(R.id.song_xiugai_name);
		tvBianhao = (UnderlineTextView) view.findViewById(R.id.song_xiugai_no);
		tvYubie = (UnderlineTextView) view.findViewById(R.id.song_xiugai_yubie);
		tvShoupin = (UnderlineTextView) view
				.findViewById(R.id.song_xiugai_shoupin);
		tvZishu = (UnderlineTextView) view.findViewById(R.id.song_xiugai_zishu);
		tvSinger = (UnderlineTextView) view
				.findViewById(R.id.song_xiugai_singer);
		tvYingui = (UnderlineTextView) view
				.findViewById(R.id.song_xiugai_yingui);
		tvBanchang = (UnderlineTextView) view
				.findViewById(R.id.song_xiugai_banchang);
		tvYuanchang = (UnderlineTextView) view
				.findViewById(R.id.song_xiugai_yuanchang);
		tvFenlei = (UnderlineTextView) view
				.findViewById(R.id.song_xiugai_fenlei);
		tvWuqu = (UnderlineTextView) view.findViewById(R.id.song_xiugai_wuqu);
		tvDianying = (UnderlineTextView) view
				.findViewById(R.id.song_xiugai_dianying);
		tvLiuxing = (UnderlineTextView) view
				.findViewById(R.id.song_xiugai_liuxing);
		tvGengxingtime = (UnderlineTextView) view
				.findViewById(R.id.song_xiugai_updatetime);
		tvSongfile = (UnderlineTextView) view
				.findViewById(R.id.song_xiugai_songfile);

		songXGYes = (Button) view.findViewById(R.id.song_xiugai_yes);
		songXGYes.setOnClickListener(this);

		tvGeming.setOnClickListener(this);
		tvBianhao.setOnClickListener(this);
		tvYubie.setOnClickListener(this);
		tvShoupin.setOnClickListener(this);
		tvZishu.setOnClickListener(this);
		tvSinger.setOnClickListener(this);
		tvYingui.setOnClickListener(this);
		tvBanchang.setOnClickListener(this);
		tvYuanchang.setOnClickListener(this);
		tvFenlei.setOnClickListener(this);
		tvWuqu.setOnClickListener(this);
		tvDianying.setOnClickListener(this);
		tvLiuxing.setOnClickListener(this);
		tvGengxingtime.setOnClickListener(this);
		tvSongfile.setOnClickListener(this);
	}

	@Override
	public void back() {
		EventBus.getDefault().post(
				new AsyncMessage(FragmentFactory.FRAGMENT_HOME_SYSTEM_SET));
	}
	
	ProgressDialog dialog = null;
	private void updateSongFile()
	{
		
		final  String geming = tvGeming.getText().toString();
		final    String yubie = tvYubie.getText().toString();
		final    String shoupin = tvShoupin.getText().toString();
		final    String zishu = tvZishu.getText().toString();
		final    String singer = tvSinger.getText().toString();
		final    String yingui = tvYingui.getText().toString();
		final    String banchang = tvBanchang.getText().toString();
		final    String yuanchang = tvYuanchang.getText().toString();
		final    String fenlei = tvFenlei.getText().toString();
		final    String wuqu = tvWuqu.getText().toString();
		final    String dianying = tvDianying.getText().toString();
		final    String liuxing = tvLiuxing.getText().toString();
		  
		 
		 
		dialog = new ProgressDialog(getActivity());  
		dialog.setMessage("正在更新文件...");
		dialog.setCancelable(false);
		dialog.show(); 
		new Thread(){
			public void run() {
				try { 
					song.setSong_singer_name(singer);
					song.setSong_name(geming);
					song.setSong_dance_type(songDanceType);
					song.setSong_class(songClassType);
					song.setSong_film_type(songFilemType);
					song.setSong_first_name(shoupin);
					song.setSong_language(songLan);
					song.setSong_left_volume(banchang);
					song.setSong_right_volume(yuanchang);
					song.setSong_popular_type(songPopularType);
					song.setSong_track(songTrack);
					song.setSong_words_count(songWordsCount);

					DataBase.getInstance().updateSongDefaultFile(song);
					gotoSetting();
					
				} catch (Exception e) {
					 
				}finally{
					getActivity().runOnUiThread(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							dialog.dismiss();
						}
					});
					
				}
			};
		}.start();
	}
	@Override
	public void onClick(View v) {

		int id = v.getId();
		switch (id) {
		case R.id.song_xiugai_yes:
			
			updateSongFile();
			break;
		case R.id.song_xiugai_name:
			InputDialog.Builder noBuilder = new Builder(getActivity(),R.style.input_dialog_style);
			noBuilder.setInputHint(songName);
			noBuilder.setOnDialogButtonClickListener(new OnDialogButtonClickListener() {
				
				@Override
				public void onDialogButtonClick(String checkCode) {
					
					tvGeming.setText(""+checkCode);
				}
			});
			noBuilder.show();
			

			break;
		case R.id.song_xiugai_no:
			// 不支持修改
			break;
		case R.id.song_xiugai_yubie:
			SongXGYuBieWindow yuyanWindow = new SongXGYuBieWindow(getActivity()) {
				@Override
				public void yuyanClick(String showText, String yuyan) {
					super.yuyanClick(showText, yuyan);
					tvYubie.setText(showText);
					songLan = yuyan;
				}
			};
			yuyanWindow.showPopupWindow(tvYubie);
			break;
		case R.id.song_xiugai_shoupin:

			InputDialog.Builder spBuilder = new Builder(getActivity(),R.style.input_dialog_style);
			spBuilder.setInputHint(songWordsCount);
			spBuilder.setOnDialogButtonClickListener(new OnDialogButtonClickListener() {
				
				@Override
				public void onDialogButtonClick(String checkCode) {
					
					tvShoupin.setText(""+checkCode);
				}
			});
			spBuilder.show();
			
			
			break;
		case R.id.song_xiugai_zishu:
			SongXGZiShuWindow zishuWindow = new SongXGZiShuWindow(getActivity()) {
				@Override
				public void zishuClick(String showText, int yuyan) {
					super.zishuClick(showText, yuyan);
					tvZishu.setText(showText + "");
					 songWordsCount= yuyan+"";
				}
			};
			zishuWindow.showPopupWindow(tvZishu);
			break;
		case R.id.song_xiugai_singer:

			InputDialog.Builder gxBuilder = new Builder(getActivity(),R.style.input_dialog_style);
			gxBuilder.setInputHint(songSinger);
			gxBuilder.setOnDialogButtonClickListener(new OnDialogButtonClickListener() {
				
				@Override
				public void onDialogButtonClick(String checkCode) {
					
					tvSinger.setText(""+checkCode);
				}
			});
			gxBuilder.show();
			
			break;
		case R.id.song_xiugai_yingui:
			SongXGYinGuiWindow yinguiWindow = new SongXGYinGuiWindow(
					getActivity()) {
				@Override
				public void yinguiClick(String showText, int yuyan) {
					super.yinguiClick(showText, yuyan);
					tvYingui.setText(showText);
					songTrack=""+yuyan;
				}
			};
			yinguiWindow.showPopupWindow(tvYingui);

			break;
		case R.id.song_xiugai_banchang:
			SongXGYinLiangWindow banchangWindow = new SongXGYinLiangWindow(
					getActivity()) {
				@Override
				public void yinliangClick(int yuyan) {
					super.yinliangClick(yuyan);
					tvBanchang.setText(yuyan + "");
					songLeftVolume=""+yuyan;
				}
			};
			banchangWindow.showPopupWindow(tvBanchang);
			break;
		case R.id.song_xiugai_yuanchang:
			SongXGYinLiangWindow yuanchangWindow = new SongXGYinLiangWindow(
					getActivity()) {
				@Override
				public void yinliangClick(int yuyan) {
					super.yinliangClick(yuyan);
					tvYuanchang.setText(yuyan + "");
					songRightVolume=""+yuyan;
				}
			};
			yuanchangWindow.showPopupWindow(tvYuanchang);
			break;
		case R.id.song_xiugai_fenlei:
			SongXGFenLeiWindow fenleiWindow = new SongXGFenLeiWindow(
					getActivity()) {
				@Override
				public void fenleiClick(String showText, String yuyan) {
					super.fenleiClick(showText, yuyan);
					tvFenlei.setText(showText);
					songClassType=""+yuyan;
				}
			};
			fenleiWindow.showPopupWindow(tvFenlei);

			break;
		case R.id.song_xiugai_wuqu:
			SongXGWuQuWindow wuquWindow = new SongXGWuQuWindow(getActivity()) {
				@Override
				public void wuquClick(String showText, String yuyan) {
					super.wuquClick(showText, yuyan);
					tvWuqu.setText(showText);
					songDanceType=""+yuyan;
				}
			};

			wuquWindow.showPopupWindow(tvWuqu);

			break;
		case R.id.song_xiugai_dianying:
			SongXGDianYingWindow dianyingWindow = new SongXGDianYingWindow(
					getActivity()) {
				@Override
				public void dianyingClick(String showText, String yuyan) {
					super.dianyingClick(showText, yuyan);
					tvDianying.setText(showText);
					songFilemType=""+yuyan;
				}

			};
			dianyingWindow.showPopupWindow(tvDianying);

			break;
		case R.id.song_xiugai_liuxing:
			SongXGLiuXingWindow liuxingWindow = new SongXGLiuXingWindow(
					getActivity()) {
				@Override
				public void liuxingClick(String showText, String yuyan) {
					super.liuxingClick(showText, yuyan);
					tvLiuxing.setText(showText);
					songPopularType = yuyan;
				}
			};
			liuxingWindow.showPopupWindow(tvLiuxing);

			break;
		case R.id.song_xiugai_updatetime:
			// 不支持修改
			break;
		case R.id.song_xiugai_songfile:
			// 不支持修改
			break;

		default:
			break;
		}

	}

}
