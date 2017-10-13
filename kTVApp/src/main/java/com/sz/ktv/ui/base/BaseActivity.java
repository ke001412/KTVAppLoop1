package com.sz.ktv.ui.base;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.sz.ktv.ui.tool.MultiScreenTool;
import com.sz.ktv.util.Global;
import com.sz.ktv.util.LanguageUtil;

public class BaseActivity extends Activity {
	
	protected MultiScreenTool mst = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Global.currentActivity =this;
		LanguageUtil.setDefaultLanguage(this);
        if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
        {
            mst = MultiScreenTool.singleTonHolizontal();
        }
        else
        {
            mst = MultiScreenTool.singleTonVertical();
        }
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
}
