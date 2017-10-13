package com.sz.ktv.ui.base;

import org.greenrobot.eventbus.EventBus;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;

import com.sz.ktv.msg.AsyncMessage;
import com.sz.ktv.ui.tool.MultiScreenTool;
import com.sz.ktv.util.FragmentFactory;
import com.sz.ktv.util.Global;

public class BaseFragmentActivity extends FragmentActivity implements
		OnClickListener {

	protected MultiScreenTool mst = null;
	public static int preFragmentId;
	public static int currentFragmentId;
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		
		Global.currentActivity = this;
//        if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
//        {
//            mst = MultiScreenTool.singleTonHolizontal();
//        }
//        else
//        {
            mst = MultiScreenTool.singleTonVertical();
//        }
	}
	
	@Override
	protected void onResume() {
		super.onResume();
	}
	
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
	
	
	@Override
	public void onClick(View v) {

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
