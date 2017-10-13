package com.sz.ktv.ui.service;

import java.io.File;

import org.greenrobot.eventbus.EventBus;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.sz.ktv.application.MyApplication;
import com.sz.ktv.db.DataBase;
import com.sz.ktv.msg.LoadMessage;
import com.sz.ktv.util.Global;
import com.sz.ktv.util.code.RandomCode;
import com.sz.ktv.util.writeFile.Write;

public class LoadService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	private LoadThread loadThread;
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		loadThread = new LoadThread();
		loadThread.start();
		
		return super.onStartCommand(intent, flags, startId);
	}
	
	private class LoadThread extends Thread{
		@Override
		public void run() {
			super.run();
			load();
		}
	}
	
	
	private void load()
	{
		try {
			/**
			 * 首先加载歌星
			 */
			DataBase.loadSingerList(Global.currentActivity);
			DataBase.loadSongList(Global.currentActivity);
			DataBase.loadNweSongList(Global.currentActivity);
			EventBus.getDefault().post(new LoadMessage(1));
			 
			String qrcode = RandomCode.getSecurityCode().toUpperCase();
			Global.qrCode = qrcode;
			String txt_path =   getFilesDir().getParent() + "/"
					+ DataBase.SONG_FILE_COPY;
			File file  = new File(txt_path);
			if(!file.exists())
			{
				Write.debug("写入本地存储....");
			MyApplication.copyFile(DataBase.SONG_FILE, txt_path);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
