package com.sz.ktv.net.util;

import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.sz.ktv.net.util.CommandStr;
import com.sz.ktv.net.service.ConnectService;
import com.sz.ktv.net.udp.UdpThread;
import com.sz.ktv.net.udp.UdpThread.ResponeListener;
import com.sz.ktv.util.Global;

public class Command {
	private static UdpThread ut = null;
	private static Context mContext = null;
	
	public Command(Context context) {
		mContext = context;
	}

	public void commandAnalysis(DatagramPacket data,
			ResponeListener listener) {
		Log.d("wuming", "command length = " + data.getLength());
		// head
		String commandHead = new String(data.getData(), data.getOffset(), 5);
		
		if (commandHead.equals(CommandStr.CMD_YINLIANG)) { 
			String commandBody = new String(data.getData(), data.getOffset() + 5, 4);
			Log.d("wuming", "yinliang commandBody = " + commandBody);
			if(commandBody.equals("0001")) {
				Log.d("wuming", "yinliang jia.");
			}else if(commandBody.equals("0002")) {
				Log.d("wuming", "yinliang jian.");
			}
		} else if (commandHead.equals(CommandStr.CMD_YUANCHANG)) { 
			Log.d("wuming", "yuan chang.");
		} else if (commandHead.equals(CommandStr.CMD_BOFANG)) { 
			Log.d("wuming", "bo fang.");
		} else if (commandHead.equals(CommandStr.CMD_ZANTING)) { 
			Log.d("wuming", "zan ting.");
		} else if (commandHead.equals(CommandStr.CMD_CHONGCHANG)) { 
			Log.d("wuming", "chong chang.");
		} else if (commandHead.equals(CommandStr.CMD_XIASHOU)) { 
			Log.d("wuming", "xia yi shou.");
		} else if (commandHead.equals(CommandStr.CMD_BANCHANG)) { 
			Log.d("wuming", "ban chang.");
		} else if (commandHead.equals(CommandStr.CMD_JINGYIN)) { 
			Log.d("wuming", "jing yin.");
		} else if (commandHead.equals(CommandStr.CMD_BANCHANG)) { 
			Log.d("wuming", "ban chang.");
		} else if (commandHead.equals(CommandStr.CMD_HUANYING_CLOSE)) { 
			Log.d("wuming", "close huan ying.");
		}
		
	}

	//paihang data callback
	public interface OnPaiHangDataListener {
		void dataReceived(String[] strMusic);
	}

	private static OnPaiHangDataListener paihangListener;

	public void setPaiHangListener(
			OnPaiHangDataListener listener) {
		Log.d("wuming", "setPaiHangListener()");
		this.paihangListener = listener;
	}
	
	//yibo data callback
	public interface OnYiBoDataListener {
		void dataReceived(String[] strMusic);
	}

	private static OnYiBoDataListener yiboListener;

	public void setYiBoListener(
			OnYiBoDataListener listener) {
		Log.d("wuming", "setYiBoListener()");
		this.yiboListener = listener;
	}
	
	//yidian data callback
	public interface OnYiDianDataListener {
		void dataReceived(String[] strMusic);
	}

	private static OnYiDianDataListener yidianListener;

	public void setYiDianListener(
			OnYiDianDataListener listener) {
		Log.d("wuming", "setYiDianListener()");
		this.yidianListener = listener;
	}
	
//	public static void sendUdpCommand(String command) {
//		String message = command;
//		ut.SendData(message);
//	}
	
}
