package com.sz.ktv.ui.base;

import org.greenrobot.eventbus.EventBus;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sz.ktv.msg.AsyncMessage;
import com.sz.ktv.ui.tool.MultiScreenTool;
import com.sz.ktv.util.FragmentFactory;
import com.sz.ktv.util.Global;
import com.sz.ktv.util.LanguageUtil;

public class BaseFragment extends Fragment {

//	protected MultiScreenTool mst = null;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			Global.currentActivity = getActivity();
//	        if (getActivity().getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
//	        {
//	            mst = MultiScreenTool.singleTonHolizontal();
//	        }
//	        else
//	        {
//	            mst = MultiScreenTool.singleTonVertical();
//	        }
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	@Override
	public void onResume() {
		super.onResume();
		LanguageUtil.setDefaultLanguage(getActivity());
	}
	
	public void backToHome()
	{
		EventBus.getDefault().post(
				new AsyncMessage(FragmentFactory.FRAGMENT_HOME_CENTER));
	}
	
	public void gotoGongBoSetting()
	{
		EventBus.getDefault().post(
				new AsyncMessage(FragmentFactory.FRAGMENT_SET_GONGBO));
	}
	public void gotoSetting()
	{
		EventBus.getDefault().post(
				new AsyncMessage(FragmentFactory.FRAGMENT_HOME_SYSTEM_SET));
	}
	
	public void gotoGeming()
	{
		EventBus.getDefault().post(
				new AsyncMessage(FragmentFactory.FRAGMENT_HOME_GEMING));
	}
	public void gotoGeXing()
	{
		EventBus.getDefault().post(
				new AsyncMessage(FragmentFactory.FRAGMENT_HOME_GEXING));
	}
}
