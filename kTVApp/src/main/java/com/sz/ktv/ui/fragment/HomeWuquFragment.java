package com.sz.ktv.ui.fragment;

import org.greenrobot.eventbus.EventBus;

import android.os.Bundle;
import android.os.Message;
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

public class HomeWuquFragment extends BaseFragment implements
		BackClickListener, OnClickListener {

	KtvBottomPageWidget bottomPage;
	RelativeLayout wuquManyao;
	RelativeLayout wuquHuaerzi;
	RelativeLayout wuquQiaqia;
	RelativeLayout wuquDishigao;
	RelativeLayout wuquPulushi;
	RelativeLayout wuquChuanshao;
	RelativeLayout wuquJiteba;
	RelativeLayout wuquTange;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = null;
		try {
			view = inflater.inflate(R.layout.wuqu_layout, container, false);
		} catch (Exception e) {
			e.printStackTrace();
		}

		initView(view);
		return view;
	}

	private void initView(View view) {
		bottomPage = (KtvBottomPageWidget) view.findViewById(R.id.wuqu_back);
		bottomPage.setBackClickListener(this);
		bottomPage.setType(KtvBottomPageWidget.TYPE_NO_DOTS_NO_PAGENUM);

		wuquManyao = (RelativeLayout) view.findViewById(R.id.wuqu_manyao);
		wuquHuaerzi = (RelativeLayout) view.findViewById(R.id.wuqu_huaerzi);
		wuquQiaqia = (RelativeLayout) view.findViewById(R.id.wuqu_qiaqia);
		wuquDishigao = (RelativeLayout) view.findViewById(R.id.wuqu_dishigao);
		wuquPulushi = (RelativeLayout) view.findViewById(R.id.wuqu_pulushi);
		wuquChuanshao = (RelativeLayout) view
				.findViewById(R.id.wuqu_chuangshao);
		wuquJiteba = (RelativeLayout) view.findViewById(R.id.wuqu_jiteba);
		wuquTange = (RelativeLayout) view.findViewById(R.id.wuqu_tange);

		wuquManyao.setOnClickListener(this);
		wuquHuaerzi.setOnClickListener(this);
		wuquQiaqia.setOnClickListener(this);
		wuquDishigao.setOnClickListener(this);
		wuquPulushi.setOnClickListener(this);
		wuquChuanshao.setOnClickListener(this);
		wuquJiteba.setOnClickListener(this);
		wuquTange.setOnClickListener(this);

	}

	public static void gotoGeming(int type) {
		
		String keyString = "";
		String preTitle="";
		
		switch (type) {
		case HomeGeMingFragment.TYPE_WUQU_MY:
			keyString=DataBase.SONG_WUQU_MANYAO;
			preTitle="点歌->舞曲->慢摇->";
			break;
		case HomeGeMingFragment.TYPE_WUQU_HEZ:
			keyString=DataBase.SONG_WUQU_HUAERZI;
			preTitle="点歌->舞曲->华尔兹->";
			break;
		case HomeGeMingFragment.TYPE_WUQU_QQ:
			keyString=DataBase.SONG_WUQU_QIAQIA;
			preTitle="点歌->舞曲->恰恰->";
			break;
		case HomeGeMingFragment.TYPE_WUQU_DSG:
			keyString=DataBase.SONG_WUQU_DISHIGAO;
			preTitle="点歌->舞曲->迪士高->";
			break;
		case HomeGeMingFragment.TYPE_WUQU_PLS:
			keyString=DataBase.SONG_WUQU_PULUSHI;
			preTitle="点歌->舞曲->普鲁士->";
			break;
		case HomeGeMingFragment.TYPE_WUQU_CS:
			keyString=DataBase.SONG_WUQU_CHUANGSHAO;
			preTitle="点歌->舞曲->串烧->";
			break;
		case HomeGeMingFragment.TYPE_WUQU_JTB:
			keyString=DataBase.SONG_WUQU_JITEBA;
			preTitle="点歌->舞曲->吉特巴->";
			break;
		case HomeGeMingFragment.TYPE_WUQU_TG:
			keyString=DataBase.SONG_WUQU_TANGE;
			preTitle="点歌->舞曲->探戈->";
			break;
		default:
			break;
		}
		SongTypeMessage message = new SongTypeMessage();
		message.setType(type);
		message.setKeyWords(keyString);
		message.setPreTileText(preTitle);
		message.setReturnType(HomeGeMingFragment.RETURN_TYPE_WUQU);
		
		DataBase.keySong=keyString;
		DataBase.keyType=SongUtil.TYPE_SONG_DANCE;
		HomeGeMingFragment.returnType = HomeGeMingFragment.RETURN_TYPE_WUQU;
		
		EventBus.getDefault().post(new AsyncMessage(FragmentFactory.FRAGMENT_HOME_GEMING));
//		EventBus.getDefault().post(message);
	}

	@Override
	public void onClick(View v) {

		int id = v.getId();
		switch (id) {
		case R.id.wuqu_manyao:
			gotoGeming(HomeGeMingFragment.TYPE_WUQU_MY);
			break;
		case R.id.wuqu_huaerzi:
			gotoGeming(HomeGeMingFragment.TYPE_WUQU_HEZ);
			break;
		case R.id.wuqu_qiaqia:
			gotoGeming(HomeGeMingFragment.TYPE_WUQU_QQ);
			break;
		case R.id.wuqu_dishigao:
			gotoGeming(HomeGeMingFragment.TYPE_WUQU_DSG);
			break;
		case R.id.wuqu_pulushi:
			gotoGeming(HomeGeMingFragment.TYPE_WUQU_PLS);
			break;
		case R.id.wuqu_chuangshao:
			gotoGeming(HomeGeMingFragment.TYPE_WUQU_CS);
			break;
		case R.id.wuqu_jiteba:
			gotoGeming(HomeGeMingFragment.TYPE_WUQU_JTB);
			break;
		case R.id.wuqu_tange:
			gotoGeming(HomeGeMingFragment.TYPE_WUQU_TG);
			break;

		default:
			break;
		}
	}

	@Override
	public void back() {

		backToHome();
	}

}
