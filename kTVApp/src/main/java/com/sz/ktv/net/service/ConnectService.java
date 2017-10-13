package com.sz.ktv.net.service;

//import java.io.IOException;
//import java.io.InputStream;
//import java.net.DatagramPacket;
//import java.net.DatagramSocket;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.net.SocketException;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
//import android.os.RemoteException;
import android.util.Log;

import com.sz.ktv.net.tcp.TcpClient;
//import com.song.ktv.net.util.Command;
import com.sz.ktv.net.udp.UdpThread;

public class ConnectService extends Service {
	private static boolean bInit = false;

	@Override
	public void onCreate() {
		super.onCreate();
		Log.d("wuming", "ConnectService onCreate().");
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		udp.DisConnectSocket();
		unregisterReceiver(sendCommandRec);
		bInit = false;
		stopSelf();
		Log.d("wuming", "ConnectService onDestroy().");
	}

	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);

	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		IntentFilter filter = new IntentFilter();
		filter.addAction(CMD_ACTION);
		filter.addAction(SET_IP_ACTION);
		registerReceiver(sendCommandRec, filter);

		Log.d("wuming ","onStartCommand init UDP  ");
		try {
			if(!bInit) {
				initUpdClient();	
//				initTcpClient();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		return super.onStartCommand(intent, flags, startId);
	}

	private UdpThread udp = null;
	private void initUpdClient() {
		if (udp != null) {
			udp.stopThread();
		}
		udp = new UdpThread(getApplicationContext());				
//		LogcatHelper.getInstance(this).start();
		/**
		 * 
		 */
		udp.setRemoteIP("255.255.255.255"); //192.168.1.100");
		udp.setRemotePort(7012);//7602
		udp.setLocalPort(7602);
		udp.ConnectSocket();
		bInit = true;
		Log.d("wuming", "initUpdClient() end.");
	}
	
	private TcpClient tcp = null;
	private void initTcpClient(String strIP) {
		tcp = new TcpClient(this);				

//		tcp.setRemoteIP("192.168.1.100");
		tcp.setRemoteIP(strIP);
		tcp.setRemotePort(7012);//7602
		tcp.setLocalPort(7602); //
		
		bInit = true;
		Log.d("wuming", "initTcpClient() end. ip = " + strIP);
	}
	
	public static final String CMD_ACTION = "com.ktv.send.command.action";
	public static final String SET_IP_ACTION = "com.ktv.send.command.set.ip";

	BroadcastReceiver sendCommandRec = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			final int iType = intent.getIntExtra("type", 0); //default udp
			final String cmd = intent.getStringExtra("cmd");
			final int iCodeType = intent.getIntExtra("code_type", 0); //default utf-8
			final int iDataType = intent.getIntExtra("data_type", -1);
			if (CMD_ACTION.equals(action)) {
				new Thread(new Runnable() {					
					@Override
					public void run() {
						if(iType == 0 && bInit) {
							if(udp != null) {
								udp.SendData(cmd, iCodeType);
							}
						} else if(iType == 1  && bInit){
							if(iDataType != -1) {
								if(tcp != null) {
									tcp.SendData(cmd, iDataType);
								}
							}
						} else{
							Log.d("wuming", "sendCommandRec type error, bInit = " + bInit);
						}
					}
				}).start();
				Log.d("wuming ", "sendData() cmd = "+ cmd);
//				if(udp != null ){
//					udp.SendData(cmd);
//				}
//				
			} else if(SET_IP_ACTION.equals(action)) {
				String strIP = intent.getStringExtra("ip");				
				if(udp != null) {
					udp.setRemoteIP(strIP);		
					udp.DisConnectSocket();
					udp.ConnectSocket();
					Log.d("wuming", "reset udp ip = " + strIP);
				}
//				if (tcp != null) {
//					tcp.setRemoteIP(strIP);		
//					tcp.DisConnectSocket();
//					tcp.ConnectSocket();
//					Log.d("wuming", "reset tcp ip = " + strIP);
//				}
				initTcpClient(strIP);
			}

		}
	};

}
