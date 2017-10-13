package com.sz.ktv.util;

import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.util.DisplayMetrics;

import com.sz.ktv.MainActivity;
import com.sz.ktv.application.MyApplication;

public class LanguageUtil {

	public static final String ENGLISH="en";
	public static final String CHINESE="cn";
	public static final String CHINSES_FANTI = "tw";
	public static final String DEFAULT_LANGUAGE=CHINESE;
	/**
	 * 设置语言参数
	 * @param activity
	 * @param lanAtr
	 */
	public static void changeAppLanguage(Activity activity, String lanAtr) {
        Configuration config = activity.getResources().getConfiguration();
        DisplayMetrics dm = activity.getResources().getDisplayMetrics();
        if (lanAtr.equals(ENGLISH)) {
            config.locale = Locale.ENGLISH;
        } else if (lanAtr.equals(CHINESE)) {
            config.locale = Locale.SIMPLIFIED_CHINESE;
        }else if(lanAtr.equals(CHINSES_FANTI)){
        	 config.locale = Locale.TAIWAN;
        }else {
        	config.locale = Locale.ENGLISH;
        }
        activity.getResources().updateConfiguration(config, dm);
        SharedPreferences sharedPreferences =activity.getSharedPreferences("language_set", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("language", lanAtr).commit();
        
    }
	
	public static void restart() {
        Intent it = new Intent(MyApplication.getAppContext(), MainActivity.class); //MainActivity是你想要重启的activity
        it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        MyApplication.getAppContext().startActivity(it);
//        android.os.Process.killProcess(android.os.Process.myPid());  
//        System.exit(0); 
    }
	
	/**
	 * 获取当前设置的语言参数
	 * @param activity
	 * @return
	 */
	public static String getCurrentSetLanguage(Activity activity)
	{
		 SharedPreferences sharedPreferences =activity.getSharedPreferences("language_set", Context.MODE_PRIVATE);
		 String language = sharedPreferences.getString("language", DEFAULT_LANGUAGE);
		 return language;
	}
	/**
	 * 设置默认的语言参数
	 * @param activity
	 */
	public static void setDefaultLanguage(Activity activity)
	{
		 SharedPreferences sharedPreferences =activity.getSharedPreferences("language_set", Context.MODE_PRIVATE);
		 String language = sharedPreferences.getString("language", DEFAULT_LANGUAGE);
		 changeAppLanguage(activity,language);
	}
}

