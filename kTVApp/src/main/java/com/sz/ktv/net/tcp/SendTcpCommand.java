package com.sz.ktv.net.tcp;

import com.sz.ktv.net.service.ConnectService;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class SendTcpCommand {
	
	public static void send(Context context,String cmd){
		Intent intent =new Intent(ConnectService.CMD_ACTION);
		intent.putExtra("type", 1); //Tcp 
		intent.putExtra("cmd", cmd);
		context.sendBroadcast(intent);
		Log.d("wuming", "SendTcpCommand send() cmd = " + cmd);
	}
	
	public static void send(Context context,String cmd, int dataType){
		Intent intent =new Intent(ConnectService.CMD_ACTION);
		intent.putExtra("type", 1); //Tcp 
		intent.putExtra("cmd", cmd);
		intent.putExtra("data_type", dataType);
		context.sendBroadcast(intent);
		Log.d("wuming", "SendTcpCommand send() cmd = " + cmd);
	}

}
