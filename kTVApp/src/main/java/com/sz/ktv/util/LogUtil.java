package com.sz.ktv.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

public class LogUtil {
	private static final String TAG = "KTV_DEBUG";
	public static void log (String tag, String message){
		Log.i(TAG, tag+"->"+message);
	}
}
