package com.sz.ktv.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SetPasswordMangeUtil {

	public static final String KEY_MANGNE_PASSWORD = "ktv_manage_password";
	
	public static String getMangePassword()
	{
		SharedPreferences share = Global.currentActivity.getSharedPreferences("ktv_passwod",
				Context.MODE_PRIVATE);
		
		String managePassword = share.getString(KEY_MANGNE_PASSWORD, "666666");
		return managePassword;
		
	}
	public static void updateManagePassword(String newPassword)
	{
		SharedPreferences share = Global.currentActivity.getSharedPreferences("ktv_passwod",
				Context.MODE_PRIVATE);
		Editor edit = share.edit();
		edit.putString(KEY_MANGNE_PASSWORD, newPassword);
		edit.commit();
	}
}
