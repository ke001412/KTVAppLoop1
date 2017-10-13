package com.sz.ktv.view.listener;

import android.app.Activity;

import com.sz.ktv.util.LanguageUtil;
import com.sz.ktv.view.dialog.LanguageSetDialog.PopuwindowYuYanClickListener;

public class YuYanListener implements PopuwindowYuYanClickListener {
	
	private Activity activity;
	
	public YuYanListener(Activity mActivity)
	{
		this.activity = mActivity;
	}

	@Override
	public void yuyanJianTiClick() {
		LanguageUtil.changeAppLanguage(activity,LanguageUtil.CHINESE);
	}

	@Override
	public void yuyanFanTiClick() {
		LanguageUtil.changeAppLanguage(activity,LanguageUtil.CHINSES_FANTI);
	}

	@Override
	public void yuyanEnglishClick() {
		LanguageUtil.changeAppLanguage(activity,LanguageUtil.ENGLISH);
	}

	 
	 
}
