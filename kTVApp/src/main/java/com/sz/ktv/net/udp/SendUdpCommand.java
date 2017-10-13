package com.sz.ktv.net.udp;

import com.sz.ktv.net.service.ConnectService;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class SendUdpCommand {
	
	public static void send(Context context,String cmd){
		Intent intent =new Intent(ConnectService.CMD_ACTION);
		intent.putExtra("type", 0); //Udp 
		intent.putExtra("cmd", cmd);
		context.sendBroadcast(intent);
		Log.d("wuming", "SendUdpCommand send() cmd = " + cmd);
	}

	public static void send(Context context,String cmd, int type){
		Intent intent =new Intent(ConnectService.CMD_ACTION);
		intent.putExtra("type", 0); //Udp 
		intent.putExtra("cmd", cmd);
		intent.putExtra("code_type", type); 
		context.sendBroadcast(intent);
		Log.d("wuming", "SendUdpCommand2 send() cmd = " + cmd);
	}
}
