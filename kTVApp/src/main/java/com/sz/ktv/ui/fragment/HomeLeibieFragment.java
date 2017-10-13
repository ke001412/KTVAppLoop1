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

public class HomeLeibieFragment extends BaseFragment implements
		OnClickListener, BackClickListener {

	KtvBottomPageWidget bottomPage;

	RelativeLayout leibieShenri;
	RelativeLayout leibieGeming;
	RelativeLayout leibieXiqu;
	RelativeLayout leibieHuaijiu;
	RelativeLayout leibieErtong;
	RelativeLayout leibieHechang;
	RelativeLayout leibieXiqing;
	RelativeLayout leibieXiangsheng;

	@Override
	public void onResume() {
		super.onResume();
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = null;
		try {
			view = inflater.inflate(R.layout.leibie_layout, container, false);
		} catch (Exception e) {
			e.printStackTrace();
		}

		initView(view);
		System.out.println("显示界面.....");
		return view;
	}

	private void initView(View view) {

		bottomPage = (KtvBottomPageWidget) view.findViewById(R.id.leibie_back);
		bottomPage.setBackClickListener(this);
		bottomPage.setType(KtvBottomPageWidget.TYPE_NO_DOTS_NO_PAGENUM);

		leibieShenri = (RelativeLayout) view.findViewById(R.id.leibie_shengri);
		leibieGeming = (RelativeLayout) view.findViewById(R.id.leibie_geming);
		leibieXiqu = (RelativeLayout) view.findViewById(R.id.leibie_xiqu);
		leibieHuaijiu = (RelativeLayout) view.findViewById(R.id.leibie_huaijiu);
		leibieErtong = (RelativeLayout) view.findViewById(R.id.leibie_ertong);
		leibieHechang = (RelativeLayout) view.findViewById(R.id.leibie_hechang);
		leibieXiqing = (RelativeLayout) view.findViewById(R.id.leibie_xiqing);
		leibieXiangsheng = (RelativeLayout) view
				.findViewById(R.id.leibie_xiangsheng);

		leibieShenri.setOnClickListener(this);
		leibieGeming.setOnClickListener(this);
		leibieXiqu.setOnClickListener(this);
		leibieHuaijiu.setOnClickListener(this);
		leibieErtong.setOnClickListener(this);
		leibieHechang.setOnClickListener(this);
		leibieXiqing.setOnClickListener(this);
		leibieXiangsheng.setOnClickListener(this);

	}
public static void gotoGeming(int type) {
		
		String keyString = "";
		String preTitle="";
		
		switch (type) {
		case HomeGeMingFragment.TYPE_LEIBIE_SRGQ:
			keyString=DataBase.SONG_TYPE_SHENGRI;
			preTitle="点歌->类别->生日歌曲->";
			break;
		case HomeGeMingFragment.TYPE_LEIBIE_GMGQ:
			keyString=DataBase.SONG_TYPE_GEMING;
			preTitle="点歌->类别->革命歌曲->";
			break;
		case HomeGeMingFragment.TYPE_LEIBIE_XQXS:
			keyString=DataBase.SONG_TYPE_XIQU;
			preTitle="点歌->类别->戏曲欣赏->";
			break;
		case HomeGeMingFragment.TYPE_LEIBIE_HJGQ:
			keyString=DataBase.SONG_TYPE_HUAIJIU;
			preTitle="点歌->类别->怀旧歌曲->";
			break;
		case HomeGeMingFragment.TYPE_LEIBIE_ETGQ:
			keyString=DataBase.SONG_TYPE_ERTONG;
			preTitle="点歌->类别->儿童歌曲->";
			break;
		case HomeGeMingFragment.TYPE_LEIBIE_HCGQ:
			keyString=DataBase.SONG_TYPE_HECHANGE;
			preTitle="点歌->类别->合唱歌曲->";
			break;
		case HomeGeMingFragment.TYPE_LEIBIE_XQGQ:
			keyString=DataBase.SONG_TYPE_XIQING;
			preTitle="点歌->类别->喜庆歌曲->";
			break;
		case HomeGeMingFragment.TYPE_LEIBIE_XSXP:
			keyString=DataBase.SONG_TYPE_XIANGSHENG;
			preTitle="点歌->类别->相声小品->";
			break;
		default:
			break;
		}
		SongTypeMessage message = new SongTypeMessage();
		message.setType(type);
		message.setKeyWords(keyString);
		message.setPreTileText(preTitle);
		message.setReturnType(HomeGeMingFragment.RETURN_TYPE_LEIBIE);
		
		DataBase.keySong=keyString;
		DataBase.keyType=SongUtil.TYPE_SONG_CLASS;
		HomeGeMingFragment.returnType = HomeGeMingFragment.RETURN_TYPE_LEIBIE;
		
		EventBus.getDefault().post(new AsyncMessage(FragmentFactory.FRAGMENT_HOME_GEMING));
//		EventBus.getDefault().post(message);
	}
	@Override
	public void onClick(View v) {

		int id = v.getId();
		switch (id) {
		case R.id.leibie_shengri:
			gotoGeming(HomeGeMingFragment.TYPE_LEIBIE_SRGQ);
			break;
		case R.id.leibie_geming:
			gotoGeming(HomeGeMingFragment.TYPE_LEIBIE_GMGQ);
			break;
		case R.id.leibie_xiqu:
			gotoGeming(HomeGeMingFragment.TYPE_LEIBIE_XQXS);
			break;
		case R.id.leibie_huaijiu:
			gotoGeming(HomeGeMingFragment.TYPE_LEIBIE_HJGQ);
			break;
		case R.id.leibie_ertong:
			gotoGeming(HomeGeMingFragment.TYPE_LEIBIE_ETGQ);
			break;
		case R.id.leibie_hechang:
			gotoGeming(HomeGeMingFragment.TYPE_LEIBIE_HCGQ);
			break;
		case R.id.leibie_xiqing:
			gotoGeming(HomeGeMingFragment.TYPE_LEIBIE_XQGQ);
			break;
		case R.id.leibie_xiangsheng:
			gotoGeming(HomeGeMingFragment.TYPE_LEIBIE_XSXP);
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
