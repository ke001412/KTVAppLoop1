package com.sz.ktv.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class ZiMuUtil {

	private static final String ZIMU_KEY = "zi_mu";
	private static final String ZIMU_CONTENT = "zi_mu_content";
	
	public static  void setZimuContent(String content)
	{
		SharedPreferences share = Global.currentActivity.getSharedPreferences(ZIMU_KEY, Context.MODE_PRIVATE);
		Editor edit = share.edit();
		edit.putString(ZIMU_CONTENT, content);
		edit.commit();
	}
	
	public static String getZimuContent()
	{
		SharedPreferences share = Global.currentActivity.getSharedPreferences(ZIMU_KEY, Context.MODE_PRIVATE);
		String zimuContent = share.getString(ZIMU_CONTENT," ");
		return zimuContent;
	}
}
