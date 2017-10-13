package com.sz.ktv.ui.fragment;

import org.greenrobot.eventbus.EventBus;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.sz.ktv.R;
import com.sz.ktv.msg.AsyncMessage;
import com.sz.ktv.ui.base.BaseFragment;
import com.sz.ktv.util.FragmentFactory;
import com.sz.ktv.view.dialog.KtvConfirmDialog;
import com.sz.ktv.view.widget.KtvBottomPageWidget;
import com.sz.ktv.view.widget.KtvBottomPageWidget.BackClickListener;

public class HomeSystemSetFragment extends BaseFragment implements
		BackClickListener, OnClickListener {

	KtvBottomPageWidget bottomPage;

	RelativeLayout sysGongbo;
	RelativeLayout sysUjiage;
	RelativeLayout sysGeHuanyuan;
	RelativeLayout sysGeShanchu;
	RelativeLayout sysGeXiugai;
	RelativeLayout sysShuchu;
	RelativeLayout sysZimu;
	RelativeLayout sysShengji;
	RelativeLayout sysChumo;
	RelativeLayout sysLogo;
	RelativeLayout sysGexingTianjia;
	RelativeLayout sysMima;
	RelativeLayout sysPingfen;
	RelativeLayout sysUboge;
	RelativeLayout sysWangluo;
	RelativeLayout setLayout;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = null;
		try {
			view = inflater.inflate(R.layout.system_set_layout, container,
					false);
		} catch (Exception e) {
			e.printStackTrace();
		}

		initView(view);
		return view;
	}

	private void initView(View view) {
		
		setLayout = (RelativeLayout)view.findViewById(R.id.set_layout);
//		mst.adjustView(setLayout);
		bottomPage = (KtvBottomPageWidget) view.findViewById(R.id.sys_back);
		bottomPage.setBackClickListener(this);
		bottomPage.setType(KtvBottomPageWidget.TYPE_NO_DOTS_NO_PAGENUM);

		sysGongbo = (RelativeLayout) view.findViewById(R.id.sys_gongbo);
		sysUjiage = (RelativeLayout) view.findViewById(R.id.sys_u_jiage);
		sysGeHuanyuan = (RelativeLayout) view
				.findViewById(R.id.sys_ge_huanyuan);
		sysGeShanchu = (RelativeLayout) view.findViewById(R.id.sys_ge_shanchu);
		sysGeXiugai = (RelativeLayout) view.findViewById(R.id.sys_ge_xiugai);
		sysShuchu = (RelativeLayout) view.findViewById(R.id.sys_shuchu);
		sysZimu = (RelativeLayout) view.findViewById(R.id.sys_zimu);
		sysShengji = (RelativeLayout) view.findViewById(R.id.sys_shengji);
		sysChumo = (RelativeLayout) view.findViewById(R.id.sys_chumo);
		sysLogo = (RelativeLayout) view.findViewById(R.id.sys_logo);
		sysGexingTianjia = (RelativeLayout) view
				.findViewById(R.id.sys_gexing_tianjia);
		sysMima = (RelativeLayout) view.findViewById(R.id.sys_mima);
		sysPingfen = (RelativeLayout) view.findViewById(R.id.sys_pingfen);
		sysUboge = (RelativeLayout) view.findViewById(R.id.sys_u_boge);
		sysWangluo = (RelativeLayout) view.findViewById(R.id.sys_wangluo);

		sysGongbo.setOnClickListener(this);
		sysUjiage.setOnClickListener(this);
		sysGeHuanyuan.setOnClickListener(this);
		sysGeShanchu.setOnClickListener(this);
		sysGeXiugai.setOnClickListener(this);
		sysShuchu.setOnClickListener(this);
		sysZimu.setOnClickListener(this);
		sysShengji.setOnClickListener(this);
		sysChumo.setOnClickListener(this);
		sysLogo.setOnClickListener(this);
		sysGexingTianjia.setOnClickListener(this);
		sysMima.setOnClickListener(this);
		sysPingfen.setOnClickListener(this);
		sysUboge.setOnClickListener(this);
		sysWangluo.setOnClickListener(this);

	}

	
	@Override
	public void onHiddenChanged(boolean hidden) {
		super.onHiddenChanged(hidden);
		 
	}
	public static boolean songUpdate = false; 
	
	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.sys_gongbo:
			EventBus.getDefault().post(
					new AsyncMessage(FragmentFactory.FRAGMENT_SET_GONGBO));
			break;
		case R.id.sys_u_jiage:
			EventBus.getDefault().post(
					new AsyncMessage(FragmentFactory.FRAGMENT_SET_U_PAN_ADD_SONG));
			break;
		case R.id.sys_ge_huanyuan:
			EventBus.getDefault().post(
					new AsyncMessage(FragmentFactory.FRAGMENT_SET_GEDAN_HUANYUAN));
			break;
		case R.id.sys_ge_shanchu:
			EventBus.getDefault().post(
					new AsyncMessage(FragmentFactory.FRAGMENT_SET_SONG_DELETE));
			break;
		case R.id.sys_ge_xiugai:
			songUpdate = true;
			gotoGeming();
			break;
		case R.id.sys_shuchu:
			EventBus.getDefault().post(
					new AsyncMessage(FragmentFactory.FRAGMENT_SET_SHUCHU));
			break;
		case R.id.sys_zimu:
			EventBus.getDefault().post(
					new AsyncMessage(FragmentFactory.FRAGMENT_SET_ZIMU));
			break;
		case R.id.sys_shengji:
			EventBus.getDefault().post(
					new AsyncMessage(FragmentFactory.FRAGMENT_SET_SHENGJI));
			break;
		case R.id.sys_chumo:

			KtvConfirmDialog jConfirm = new KtvConfirmDialog(getActivity(), "是否要进行触摸校正?"){
				@Override
				public void noClick() {
					super.noClick();
				}
				
				@Override
				public void yesClick() {
					super.yesClick();
				}
			};
			jConfirm.show();
			break;
		case R.id.sys_logo:
			EventBus.getDefault().post(
					new AsyncMessage(FragmentFactory.FRAGMENT_SET_LOGO));
			break;
		case R.id.sys_gexing_tianjia:


			KtvConfirmDialog gConfirm = new KtvConfirmDialog(getActivity(), "是否要添加歌星?请把歌星图片放入USB,\n要求：JPG文件,尺寸: 132*172!!"){
				@Override
				public void noClick() {
					super.noClick();
				}
				
				@Override
				public void yesClick() {
					super.yesClick();
				}
			};
			gConfirm.show();
			
			break;
		case R.id.sys_mima:
			EventBus.getDefault().post(
					new AsyncMessage(FragmentFactory.FRAGMENT_SET_CHANGE_PASSWORD));
			break;
		case R.id.sys_pingfen:
			EventBus.getDefault().post(
					new AsyncMessage(FragmentFactory.FRAGMENT_SET_PINGFEN));
			break;
		case R.id.sys_u_boge:
			EventBus.getDefault().post(
					new AsyncMessage(FragmentFactory.FRAGMENT_SET_U_PAN_BOGE));
			break;
		case R.id.sys_wangluo:
			EventBus.getDefault().post(
					new AsyncMessage(FragmentFactory.FRAGMENT_SET_WIFI_CONFIG));
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
