package com.sz.ktv.util;

import java.io.File;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;
import android.widget.RelativeLayout;

public class Global {
	public static Activity currentActivity;
	
	public static final String LOAD_LIST_FINISH_ACTION = "com.ktv.load.list.finish";
	private static int appLanguage = 0; //default chinese
	private static boolean bLogin = false; 
	private static boolean bIsLoading = false;
	private static boolean bListIsCreated = false;
	private final static String SINGER_FILE = "singer.txt";
	private final static String SONG_FILE = "song.txt";
	private final static String SONG_NEW_FILE = "newsong.txt";
	private static long lastModified = 0;
	private static int iRecvFilesNum = 0;
	private static boolean bIsDownloadAndUpdate = false;
	private static int yidianNum = 0;

	public static String qrCode = "";
	public static final String QRCODE_IPHONE_DOWN_URL="http://xxxxxxx.html";
	public static final String QRCODE_ANDROID_DOWN_URL="http://xxxxxx.apk";
	
	public static RelativeLayout yidianLayout;
	public static RelativeLayout homeLayout;
	
	public static int getAppLanguage() {
		return appLanguage;
	}
	
	public static void setAppLanguage(int la) {
		appLanguage = la;
	}
	
	public static boolean getLogin() {
		return bLogin;
	}
	
	public static void setLogin(boolean login) {
		bLogin = login;
	}
	
	public static boolean getIsLoading() {
		return bIsLoading;
	}
	
	public static void setIsLoading(boolean loading) {
		bIsLoading = loading;
	}
	
	public static boolean getIsListDataCreated() {
		return bListIsCreated;
	}
	
	public static void setListDataCreated(boolean created) {
		bListIsCreated = created;
	}
	
	public static int getRecvFilesNum() {
		return iRecvFilesNum;
	}
	
	public static void createRecvFilesNum() {
		iRecvFilesNum++;
	}
	
	public static void setRecvFilesNum(int num) {
		iRecvFilesNum = num;
	}
	
	public static boolean isDownloadAndUpdate() {
		return bIsDownloadAndUpdate;
	}
	
	public static void setDownloadAndUpdate(boolean status) {
		bIsDownloadAndUpdate = status;
	}
	
	public static void setYidianNum(int num) {
		yidianNum = num;
	}
	
	public static int getYidianNum() {
		return yidianNum;
	}
	
	public static boolean checkWifiConnection(Context context) {
        final ConnectivityManager connMgr = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final android.net.NetworkInfo wifi =connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        if(wifi.isAvailable()) {
            return true;
        } else {
            return false;
        }
    }
	
	/*
	 * type = 0 newsong
	 * type = 1 singer
	 * type = 2 song
	 */
	public static boolean isFileUpdated(Context context, int type) { 
		File file = new File(getPath(context, type));
		if(file.isFile()) {
			long lastUpdateTime = file.lastModified();
			if(lastUpdateTime > lastModified) {
				Log.d("wuming", "type = " + type + " is modifed.");
				lastModified = lastUpdateTime;
				return true;
			} else {
				Log.d("wuming", "type = " + type + " is not modifed.");
				return false;
			}
		} else {
			Log.d("wuming", "type = " + type + " is not a file.");
			return false;
		}
	}

	private static String getPath(Context context, int type) {	
		String txt_path = context.getFilesDir().getParent() + "/";

		String path = "";
		if (type == 0) {
			path = txt_path + SONG_NEW_FILE;
		} else if (type == 1) {
			path = txt_path + SINGER_FILE;
		} else {
			path = txt_path + SONG_FILE;
		}
		return path;
	}
}
