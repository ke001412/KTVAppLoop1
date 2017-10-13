package com.sz.ktv.ui.fragment;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.greenrobot.eventbus.EventBus;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

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
import com.sz.ktv.view.widget.InputDialog.Builder;
import com.sz.ktv.view.widget.InputDialog.OnDialogButtonClickListener;
import com.sz.ktv.view.widget.KtvBottomPageWidget;
import com.sz.ktv.view.widget.KtvBottomPageWidget.BackClickListener;
import com.sz.ktv.view.widget.UnderlineTextView;

/**
 * 单个加歌界面
 * @author zhuxl
 *
 */
public class SetSongAddFragment extends BaseFragment implements
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
			view = inflater.inflate(R.layout.system_set_add_layout,
					container, false);
		} catch (Exception e) {
			e.printStackTrace();
		}

		initView(view);
		initSongName();
		return view;
	}

	private Song song = new Song();

	@Override
	public void onResume() {
		super.onResume();
		 
	}

	@Override
	public void onHiddenChanged(boolean hidden) {
		super.onHiddenChanged(hidden);
		if(!hidden)
		{
			initSongName();
		}
	 
	}

	private static final int MSG_INIT_1=1;
	
	Handler myHandler = new Handler(){
		public void dispatchMessage(android.os.Message msg) {
			int what = msg.what;
			switch (what) {
			case MSG_INIT_1:
				String fileName = currentFile.getName();
				String prefix = fileName
						.substring(fileName.lastIndexOf(".") + 1);
				tvBianhao.setText(newSongName);
				tvSongfile.setText(newSongName+"."+prefix);
				tvGengxingtime.setText("#");
				break;

			default:
				break;
			}
		};
	};
	File currentFile ;
	String newSongName = "";
	private void initSongName() {
			currentFile = SetUPanAddListFragment.currentFile;
			new Thread(){
				public void run() {
					newSongName =SongUtil.getInstance().getNextSongName();
					myHandler.sendEmptyMessage(MSG_INIT_1);
					
				};
			}.start();
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
//		tvBianhao.setOnClickListener(this);
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
//		tvSongfile.setOnClickListener(this);
	}

	@Override
	public void back() {
		EventBus.getDefault().post(
				new AsyncMessage(FragmentFactory.FRAGMENT_ADD_LIST));
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
		  
		 
		if(TextUtils.isEmpty(geming) || TextUtils.isEmpty(yubie)|| TextUtils.isEmpty(shoupin)
				|| TextUtils.isEmpty(zishu)|| TextUtils.isEmpty(singer)|| TextUtils.isEmpty(yingui)
				|| TextUtils.isEmpty(banchang)|| TextUtils.isEmpty(yuanchang)|| TextUtils.isEmpty(fenlei)
				|| TextUtils.isEmpty(wuqu)|| TextUtils.isEmpty(dianying)|| TextUtils.isEmpty(liuxing)
				){
			Toast.makeText(getActivity(),"请填写完整的歌曲信息!", 1).show();
			return ;
		}
			
		
		dialog = new ProgressDialog(getActivity());  
		dialog.setMessage("正在增加歌曲文件...");
		dialog.setCancelable(false);
		dialog.show(); 
		new Thread(){
			public void run() {
				try { 
					song.setSong_no(newSongName);
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
					song.setSong_time("#");
					song.setSong_file_name(tvSongfile.getText().toString());
					song.setSong_words_count(songWordsCount);
					song.setSong_download_flag("3");
					
					DataBase.getInstance().addNewSongList(song);
					String toPath = DataBase.MNT_SDA_SONG_PATH+"/"+tvSongfile.getText().toString();
					if(currentFile.exists()){
					copyFile(currentFile.getPath(), toPath);
					}
					
				} catch (Exception e) {
					 e.printStackTrace();
				}finally{
					getActivity().runOnUiThread(new Runnable() {
						
						@Override
						public void run() {
							 
							Toast.makeText(getActivity(), "歌曲添加成功!", 1).show();
							dialog.dismiss();
							EventBus.getDefault().post(
									new AsyncMessage(FragmentFactory.FRAGMENT_ADD_LIST));
						}
					});
					
				}
			};
		}.start();
	}
	
	
	 /**
		 * 
		 * 拷贝assets from to
		 * **/
		public static void copyFile(String from, String to) {
			InputStream inStream  = null;
			OutputStream fs = null; 
			try {
				int byteread = 0;
				inStream= new FileInputStream(new File(from));
				fs = new BufferedOutputStream(new FileOutputStream(to));
				byte[] buffer = new byte[2048];
				while ((byteread = inStream.read(buffer)) != -1) {
					fs.write(buffer, 0, byteread);
				}
			} catch (IOException e) {
				 
			}finally{
				 
					try {
						if(fs!=null){
							fs.close();
						}
						if(inStream != null ){
							inStream.close();
						}
						
					} catch (IOException e) {
						 
					}
			}
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
