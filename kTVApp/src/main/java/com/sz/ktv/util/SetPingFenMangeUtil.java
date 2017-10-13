package com.sz.ktv.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SetPingFenMangeUtil {

	public static final String KEY_MANGNE_PINGFEN_CLOSE = "ktv_pingfen_close";
	public static final String KEY_MANGNE_PINGFEN_OPEN = "ktv_pingfen_open";
	public static final String KEY_PINGFEN = "ktv_pingfen_set";
	
	public static boolean getPingFenCloseStatus()
	{
		SharedPreferences share = Global.currentActivity.getSharedPreferences(KEY_PINGFEN,
				Context.MODE_PRIVATE);
		
		boolean managePassword = share.getBoolean(KEY_MANGNE_PINGFEN_CLOSE, false);
		return managePassword;
		
	}
	public static void updatePingFenClose(boolean status)
	{
		SharedPreferences share = Global.currentActivity.getSharedPreferences(KEY_PINGFEN,
				Context.MODE_PRIVATE);
		Editor edit = share.edit();
		edit.putBoolean(KEY_MANGNE_PINGFEN_CLOSE, status);
		edit.commit();
	}
	
	public static boolean getPingFenOpenStatus()
	{
		SharedPreferences share = Global.currentActivity.getSharedPreferences(KEY_PINGFEN,
				Context.MODE_PRIVATE);
		
		boolean managePassword = share.getBoolean(KEY_MANGNE_PINGFEN_OPEN, false);
		return managePassword;
		
	}
	public static void updatePingFenOpen(boolean status)
	{
		SharedPreferences share = Global.currentActivity.getSharedPreferences(KEY_PINGFEN,
				Context.MODE_PRIVATE);
		Editor edit = share.edit();
		edit.putBoolean(KEY_MANGNE_PINGFEN_OPEN, status);
		edit.commit();
	}
}
