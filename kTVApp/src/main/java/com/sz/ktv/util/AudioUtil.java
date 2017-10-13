package com.sz.ktv.util;

import android.content.Context;
import android.media.AudioManager;

public class AudioUtil {
	
	private static AudioManager mAudioManager;
	
	static{
		mAudioManager = (AudioManager) Global.currentActivity.getSystemService(Context.AUDIO_SERVICE);    
	}
	public static int getCurrentVolume()
	{
		//音量控制,初始化定义    
		   
		//当前音量    
		int currentVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
		return currentVolume;
	}

	public static int getCurrentMaxVolume()
	{

		//最大音量    
		int maxVolume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC); 
		return maxVolume;
	}
	public static void addVolume()
	{
		int currentV = getCurrentVolume();
		if(currentV>=getCurrentMaxVolume())
		{
			return ;
		}
		else {
			mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, currentV+1, 0); //tempVolume:音量绝对值
		}
	}
	public static void reduceVolume()
	{
		int curent = getCurrentVolume();
		if(curent<=0)
		{
			return ;
		}else {
			mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, curent-1, 0); //tempVolume:音量绝对值
		}
	}
}
