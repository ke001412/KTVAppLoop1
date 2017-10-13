package com.sz.ktv.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SetShuchuMangeUtil {

	public static final String KEY_MANGNE_SHUCHU_1080P = "ktv_shuchu_1080p";
	public static final String KEY_MANGNE_SHUCHU_1080I = "ktv_shuchu_1080i";
	public static final String KEY_MANGNE_SHUCHU_720P = "ktv_shuchu_720p";
	public static final String KEY_MANGNE_SHUCHU_480I = "ktv_shuchu_480i";
	
	public static final String KEY_SHUCHU = "ktv_shuchu_set";
	
	public static boolean get1080Pstatus()
	{
		SharedPreferences share = Global.currentActivity.getSharedPreferences(KEY_SHUCHU,
				Context.MODE_PRIVATE);
		
		boolean managePassword = share.getBoolean(KEY_MANGNE_SHUCHU_1080P, false);
		return managePassword;
		
	}
	public static void update1080Pstatus(boolean status)
	{
		SharedPreferences share = Global.currentActivity.getSharedPreferences(KEY_SHUCHU,
				Context.MODE_PRIVATE);
		Editor edit = share.edit();
		edit.putBoolean(KEY_MANGNE_SHUCHU_1080P, status);
		edit.commit();
	}
	public static boolean get1080Istatus()
	{
		SharedPreferences share = Global.currentActivity.getSharedPreferences(KEY_SHUCHU,
				Context.MODE_PRIVATE);
		
		boolean managePassword = share.getBoolean(KEY_MANGNE_SHUCHU_1080I, false);
		return managePassword;
		
	}
	public static void update1080Istatus(boolean status)
	{
		SharedPreferences share = Global.currentActivity.getSharedPreferences(KEY_SHUCHU,
				Context.MODE_PRIVATE);
		Editor edit = share.edit();
		edit.putBoolean(KEY_MANGNE_SHUCHU_1080I, status);
		edit.commit();
	}
	public static boolean get720Pstatus()
	{
		SharedPreferences share = Global.currentActivity.getSharedPreferences(KEY_SHUCHU,
				Context.MODE_PRIVATE);
		
		boolean managePassword = share.getBoolean(KEY_MANGNE_SHUCHU_720P, false);
		return managePassword;
		
	}
	public static void update720Pstatus(boolean status)
	{
		SharedPreferences share = Global.currentActivity.getSharedPreferences(KEY_SHUCHU,
				Context.MODE_PRIVATE);
		Editor edit = share.edit();
		edit.putBoolean(KEY_MANGNE_SHUCHU_720P, status);
		edit.commit();
	}
	public static boolean get480Istatus()
	{
		SharedPreferences share = Global.currentActivity.getSharedPreferences(KEY_SHUCHU,
				Context.MODE_PRIVATE);
		
		boolean managePassword = share.getBoolean(KEY_MANGNE_SHUCHU_480I, false);
		return managePassword;
		
	}
	public static void update480Istatus(boolean status)
	{
		SharedPreferences share = Global.currentActivity.getSharedPreferences(KEY_SHUCHU,
				Context.MODE_PRIVATE);
		Editor edit = share.edit();
		edit.putBoolean(KEY_MANGNE_SHUCHU_480I, status);
		edit.commit();
	}
}
