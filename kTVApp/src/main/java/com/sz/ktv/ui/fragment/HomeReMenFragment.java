package com.sz.ktv.ui.fragment;

import org.greenrobot.eventbus.EventBus;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;

import com.sz.ktv.R;
import com.sz.ktv.db.DataBase;
import com.sz.ktv.db.ktv.SongUtil;
import com.sz.ktv.msg.AsyncMessage;
import com.sz.ktv.msg.SongTypeMessage;
import com.sz.ktv.ui.base.BaseFragment;
import com.sz.ktv.util.FragmentFactory;
import com.sz.ktv.view.widget.KtvBottomPageWidget;
import com.sz.ktv.view.widget.KtvBottomPageWidget.BackClickListener;

public class HomeReMenFragment extends BaseFragment implements OnClickListener,
		BackClickListener {

	KtvBottomPageWidget bottomPage;

	RelativeLayout remenZghsy;
	RelativeLayout remenWsgs;
	RelativeLayout remenZghgq;
	RelativeLayout remenZmhs;
	RelativeLayout remenZgyc;
	RelativeLayout remenZgzqy;
	RelativeLayout remenMxxdd;
	RelativeLayout remenMmgw;
	 
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = null;
		try {
			view = inflater.inflate(R.layout.remen_layout, container, false);
		} catch (Exception e) {
			e.printStackTrace();
		}

		initView(view);
		return view;
	}

	private void initView(View view) {
		bottomPage = (KtvBottomPageWidget) view.findViewById(R.id.remen_back);
		bottomPage.setBackClickListener(this);
		bottomPage.setType(KtvBottomPageWidget.TYPE_NO_DOTS_NO_PAGENUM);

		remenZghsy = (RelativeLayout) view.findViewById(R.id.remen_zghsy);
		remenWsgs = (RelativeLayout) view.findViewById(R.id.remen_wsgs);
		remenZghgq = (RelativeLayout) view.findViewById(R.id.remen_zghgq);
		remenZmhs = (RelativeLayout) view.findViewById(R.id.remen_zmhs);
		remenZgyc = (RelativeLayout) view.findViewById(R.id.remen_zgyc);
		remenZgzqy = (RelativeLayout) view.findViewById(R.id.remen_zgzqy);
		remenMxxdd = (RelativeLayout) view.findViewById(R.id.remen_mxxdd);
		remenMmgw = (RelativeLayout) view.findViewById(R.id.remen_mmgw);

		remenZghsy.setOnClickListener(this);
		remenWsgs.setOnClickListener(this);
		remenZghgq.setOnClickListener(this);
		remenZmhs.setOnClickListener(this);
		remenZgyc.setOnClickListener(this);
		remenZgzqy.setOnClickListener(this);
		remenMxxdd.setOnClickListener(this);
		remenMmgw.setOnClickListener(this);
	}

	@Override
	public void back() {
		backToHome();
	}

public static void gotoGeming(int type) {
		
		String keyString = "";
		String preTitle="";
		
		switch (type) {
		case HomeGeMingFragment.TYPE_LIUXING_ZGHSY:
			keyString=DataBase.SONG_LIUXING_ZGHSY;
			preTitle="点歌->热门->中国好声音->";
			break;
		case HomeGeMingFragment.TYPE_LIUXING_WSGS:
			keyString=DataBase.SONG_LIUXING_WSGS;
			preTitle="点歌->热门->我是歌手->";
			break;
		case HomeGeMingFragment.TYPE_LIUXING_ZGHGQ
		:
			keyString=DataBase.SONG_LIUXING_ZGHGQ;
			preTitle="点歌->热门->中国好歌曲->";
			break;
		case HomeGeMingFragment.TYPE_LIUXING_ZMHS:
			keyString=DataBase.SONG_LIUXING_ZMHS;
			preTitle="点歌->热门->最美和声->";
			break;
		case HomeGeMingFragment.TYPE_LIUXING_ZGYC:
			keyString=DataBase.SONG_LIUXING_ZGYC;
			preTitle="点歌->热门->中国音超->";
			break;
		case HomeGeMingFragment.TYPE_LIUXING_ZGZQY:
			keyString=DataBase.SONG_LIUXING_ZGZQY;
			preTitle="点歌->热门->中国最强音->";
			break;
		case HomeGeMingFragment.TYPE_LIUXING_MXXDD:
			keyString=DataBase.SONG_LIUXING_MXXDD;
			preTitle="点歌->热门->梦想星搭档->";
			break;
		case HomeGeMingFragment.TYPE_LIUXING_MMGW:
			keyString=DataBase.SONG_LIUXING_MMGW;
			preTitle="点歌->热门->蒙面歌王->";
			break;
		default:
			break;
		}
		SongTypeMessage message = new SongTypeMessage();
		message.setType(type);
		message.setKeyWords(keyString);
		message.setPreTileText(preTitle);
		message.setReturnType(HomeGeMingFragment.RETURN_TYPE_REMEN);
		
		DataBase.keySong=keyString;
		DataBase.keyType=SongUtil.TYPE_SONG_POPULAR;
		HomeGeMingFragment.returnType = HomeGeMingFragment.RETURN_TYPE_REMEN;
		
		EventBus.getDefault().post(new AsyncMessage(FragmentFactory.FRAGMENT_HOME_GEMING));
//		EventBus.getDefault().post(message);
	}
	@Override
	public void onClick(View v) {

		int id = v.getId();
		switch (id) {
		case R.id.remen_zghsy:
			gotoGeming(HomeGeMingFragment.TYPE_LIUXING_ZGHSY);
			break;
		case R.id.remen_wsgs:
			gotoGeming(HomeGeMingFragment.TYPE_LIUXING_WSGS);
			break;
		case R.id.remen_zghgq:
			gotoGeming(HomeGeMingFragment.TYPE_LIUXING_ZGHGQ);
			break;
		case R.id.remen_zmhs:
			gotoGeming(HomeGeMingFragment.TYPE_LIUXING_ZMHS);
			break;
		case R.id.remen_zgyc:
			gotoGeming(HomeGeMingFragment.TYPE_LIUXING_ZGYC);
			break;
		case R.id.remen_zgzqy:
			gotoGeming(HomeGeMingFragment.TYPE_LIUXING_ZGZQY);
			break;
		case R.id.remen_mxxdd:
			gotoGeming(HomeGeMingFragment.TYPE_LIUXING_MXXDD);
			break;
		case R.id.remen_mmgw:
			gotoGeming(HomeGeMingFragment.TYPE_LIUXING_MMGW);
			break;

		default:
			break;
		}

	}

}
