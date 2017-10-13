package com.sz.ktv.ui.fragment;

import org.greenrobot.eventbus.EventBus;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.sz.ktv.R;
import com.sz.ktv.db.DataBase;
import com.sz.ktv.db.ktv.SongUtil;
import com.sz.ktv.msg.AsyncMessage;
import com.sz.ktv.ui.base.BaseFragment;
import com.sz.ktv.util.FragmentFactory;
import com.sz.ktv.view.widget.KtvBottomPageWidget;
import com.sz.ktv.view.widget.KtvBottomPageWidget.BackClickListener;

public class HomePaiHangFragment extends BaseFragment implements
		OnClickListener, BackClickListener {

	KtvBottomPageWidget bottomPage;
	private ImageView paihangZong;
	private ImageView paihangMonth;
	private ImageView paihangWeek;
	private ImageView paihangNewSong;
	private ImageView paihangGuoyu;
	private ImageView paihangMin;
	private ImageView paihangYueyu;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = null;
		try {
			view = inflater.inflate(R.layout.paihang_layout, container, false);
		} catch (Exception e) {
			e.printStackTrace();
		}

		initView(view);
		return view;
	}

	private void initView(View view) {
		bottomPage = (KtvBottomPageWidget) view.findViewById(R.id.paihang_back);
		bottomPage.setBackClickListener(this);
		bottomPage.setType(KtvBottomPageWidget.TYPE_NO_DOTS_NO_PAGENUM);

		paihangZong = (ImageView) view.findViewById(R.id.paihang_zong);
		paihangMonth = (ImageView) view.findViewById(R.id.paihang_month);
		paihangWeek = (ImageView) view.findViewById(R.id.paihang_zhou);
		paihangNewSong = (ImageView) view.findViewById(R.id.paihang_xin);
		paihangGuoyu = (ImageView) view.findViewById(R.id.paihang_guo);
		paihangMin = (ImageView) view.findViewById(R.id.paihang_min);
		paihangYueyu = (ImageView) view.findViewById(R.id.paihang_yue);
		paihangZong.setOnClickListener(this);
		paihangMonth.setOnClickListener(this);
		paihangWeek.setOnClickListener(this);
		paihangNewSong.setOnClickListener(this);
		paihangGuoyu.setOnClickListener(this);
		paihangMin.setOnClickListener(this);
		paihangYueyu.setOnClickListener(this);

	}

	@Override
	public void back() {
		backToHome();
	}

	@Override
	public void onClick(View v) {

		int id = v.getId();
		switch (id) {
		case R.id.paihang_zong:
			gotoGeming(SongUtil.SORT_TYPE_2);
			break;
		case R.id.paihang_month:
			gotoGeming(SongUtil.SORT_TYPE_3);
			break;
		case R.id.paihang_zhou:
			gotoGeming(SongUtil.SORT_TYPE_4);
			break;
		case R.id.paihang_xin:
			gotoGeming(SongUtil.SORT_TYPE_5);
			break;
		case R.id.paihang_guo:
			SongUtil.SONG_SELECT_TYPE = SongUtil.SORT_TYPE_2;
			
			DataBase.keyType = SongUtil.TYPE_SONG_LANGUAGE;
			DataBase.keySong = DataBase.SONG_TYPE_GUOYU;
			HomeGeMingFragment.returnType = HomeGeMingFragment.RETURN_TYPE_PAIHANG;
			EventBus.getDefault().post(new AsyncMessage(FragmentFactory.FRAGMENT_HOME_GEMING));
			break;
		case R.id.paihang_yue:
			SongUtil.SONG_SELECT_TYPE = SongUtil.SORT_TYPE_2;
			
			DataBase.keyType = SongUtil.TYPE_SONG_LANGUAGE;
			DataBase.keySong = DataBase.SONG_TYPE_YUEYU;
			HomeGeMingFragment.returnType = HomeGeMingFragment.RETURN_TYPE_PAIHANG;
			EventBus.getDefault().post(new AsyncMessage(FragmentFactory.FRAGMENT_HOME_GEMING));
			
			break;
		case R.id.paihang_min:
			SongUtil.SONG_SELECT_TYPE = SongUtil.SORT_TYPE_2;
			
			DataBase.keyType = SongUtil.TYPE_SONG_LANGUAGE;
			DataBase.keySong = DataBase.SONG_TYPE_MINNANYU;
			HomeGeMingFragment.returnType = HomeGeMingFragment.RETURN_TYPE_PAIHANG;
			EventBus.getDefault().post(new AsyncMessage(FragmentFactory.FRAGMENT_HOME_GEMING));
			
			break;

		default:
			break;
		}
	}

	private void gotoGeming(int type) {
		switch (type) {
		case SongUtil.SORT_TYPE_1:
			SongUtil.SONG_SELECT_TYPE = type;
			break;
		case SongUtil.SORT_TYPE_2:
			SongUtil.SONG_SELECT_TYPE = type;
			break;
		case SongUtil.SORT_TYPE_3:
			SongUtil.SONG_SELECT_TYPE = type;
			break;
		case SongUtil.SORT_TYPE_4:
			SongUtil.SONG_SELECT_TYPE = type;
			break;
		case SongUtil.SORT_TYPE_5:
			SongUtil.SONG_SELECT_TYPE = type;
			break;
		default:
			break;
		}
		
		DataBase.keySong="";
		DataBase.keyType=-1;
		HomeGeMingFragment.returnType = HomeGeMingFragment.RETURN_TYPE_PAIHANG;
		EventBus.getDefault().post(new AsyncMessage(FragmentFactory.FRAGMENT_HOME_GEMING));
	}

}
