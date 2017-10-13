package com.sz.ktv.net.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import com.sz.ktv.MainActivity;  //wuming_0301?

//import com.song.ktv.net.util.Command;
//import com.song.ktv.ui.activity.BoKongView;
//import com.song.ktv.util.Global;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class UdpThread implements Runnable {
	// private EditText etshowrdata = null;
	private String sRecvData;
	private Thread rthread = null;// 锟斤拷锟斤拷锟斤拷锟斤拷叱锟�
	private DatagramSocket sUdp = null;// 锟斤拷锟斤拷锟斤拷锟�Socket
	private DatagramSocket rUdp = null;// 锟斤拷锟斤拷锟斤拷锟�Socket
	private DatagramPacket sPacket = null;
	private DatagramPacket rPacket = null;
	private String remoteIP;
	private int remotePort, localPort;
	private String currentSCodes = "UTF-8";
	private String currentRCodes = "UTF-8";
	private byte[] rBuffer = new byte[4 * 1024];// 锟斤拷锟斤拷锟斤拷莼锟斤拷锟�1024锟街斤拷
	private byte[] fBuffer = null;
	private byte[] sBuffer = null;
	private static Context mContext = null;
//	Command command = null;
	// private Handler vhandler = new Handler() {
	// @Override
	// public void handleMessage(Message msg) {
	// // if (etshowrdata != null) {
	// if (sRecvData == null)
	// return;
	// StringBuilder sb = new StringBuilder();
	// // sb.append(etshowrdata.getText().toString().trim());
	// // sb.append("\n");
	// sb.append(sRecvData);
	// // etshowrdata.setText(sb.toString().trim());
	// // sb.delete(0, sb.length());
	// sb = null;
	// // }
	// // super.handleMessage(msg);
	// }
	// };
	// private Context mContext;

	public UdpThread(Context context) {
		mContext = context;
//		command = new Command(mContext);			
		Log.d("wuming", " UdpThread  init command ");
	}

	public void setRemoteIP(String remoteIP) {
		this.remoteIP = remoteIP;
	}

	public void setRemotePort(int remotePort) {
		this.remotePort = remotePort;
	}

	public void setLocalPort(int localPort) {
		this.localPort = localPort;
	}

	public void setCurrentSCodes(String currentSCodes) {
		this.currentSCodes = currentSCodes;
	}

	public void setCurrentRCodes(String currentRCodes) {
		this.currentRCodes = currentRCodes;
	}

	private void startThread() {
		if (rthread == null) {
			rthread = new Thread(this);
			rthread.start();
			Log.d("wuming", " start udp  thread ");
		}
	}

	public void stopThread() {
		if (rthread != null) {
//			rthread.stop();
			rthread.interrupt();
			rthread = null;
			Log.d("wuming", " stop udp  thread ");
		}
	}

	@Override
	public void run() {
		Log.d("wuming", "before udp rev.");
		while (Thread.currentThread() == rthread) {				
			Log.d("wuming", "udp rev run 0.");
			recvData();
//			try {
//				Log.d("wuming", "udp rev run 1.");
//				recvData();
//				// vhandler.sendEmptyMessage(0);
//				Thread.sleep(100);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
		}
	}

	private void recvData() {
		try {
			Log.d("wuming", " recvData(). ");
			if(rUdp != null) {
				rUdp.receive(rPacket);				
			} else {
				Log.d("wuming", "error! recvData() rUdp is null.");
				return;
			}
			// sRecvData = new String(rPacket.getData(), currentRCodes).trim();
			
			Log.d("wuming", "udp recv data: " + new String(rPacket.getData(), currentRCodes).trim());
//			command.commandAnalysis(rPacket,listener);
			
			// sRecvData = String.format("[%s:%d//%s]%s",
			// rPacket.getAddress().getHostAddress(), rPacket.getPort(),
			// sdf.format(new Date()), sRecvData);
		} catch (IOException ie) {
			Log.d("wuming", "recvdata error:" + ie.getMessage());
		}
	}

	public void SendData(String SData, int type) { //type: 1.utf-8 2.gbk
		try {
			if (sUdp == null) {
				Log.d("wuming", "UdpThread SendData() sUdp is null, init.");
				sUdp = new DatagramSocket();
			}
			if(type == 0) {// || Global.getAppLanguage() == 1
				Log.d("wuming", "UdpThread SendData() by utf-8 code type.");
				if(SData != null) {
					sBuffer = SData.getBytes(currentSCodes);
				} else {
					
				}
			} else if(type == 1){ //&& Global.getAppLanguage() == 0
				Log.d("wuming", "UdpThread SendData() by GBK code type.");
				if(SData != null) {
					sBuffer = SData.getBytes("GBK");
				} else {
					Log.d("wuming", "UdpThread SendData() by GBK code error.");
				}
			} else{
				Log.d("wuming", "UdpThread SendData() code type erro!");
			}
			if(sUdp != null) {
				// Log.d("wuming", "UdpThread SendData() 1.");
				sPacket = new DatagramPacket(sBuffer, sBuffer.length,
						InetAddress.getByName(remoteIP), remotePort);
				// Log.d("wuming", "UdpThread SendData() 2.");
//				sUdp.send(sPacket);
				if(sUdp != null) {
					sUdp.send(sPacket);
					sUdp.close();
				}
				// Log.d("wuming", "UdpThread SendData() 4.");
				sUdp = null;
				sPacket = null;
			} else {
				 Log.d("wuming", "UdpThread sUdp is null.");
			}
		} catch (IOException ie) {
			if (sUdp != null) {
				sUdp.close();
				sUdp = null;
			}
			sPacket = null;
//			Global.setLogin(false);
			Log.d("wuming", "senddata error:" + ie.getMessage());
		}
	}

	public boolean ConnectSocket() {
		boolean result = false;
		try {
			if (rUdp == null) {
				rUdp = new DatagramSocket(localPort);
			}
			if (rPacket == null) {
				rPacket = new DatagramPacket(rBuffer, rBuffer.length);
			}
			startThread();
			result = true;
		} catch (SocketException se) {
			DisConnectSocket();
			Log.d("wuming", "open udp port error:" + se.getMessage());
		}
		return result;
	}

	public void DisConnectSocket() {
		if (rUdp != null) {
			Log.d("wuming", "DisConnectSocket rUdp.close().");
			rUdp.close();
			rUdp = null;
		}
		if (rPacket != null) {
			rPacket = null;
			Log.d("wuming", "DisConnectSocket rPacket set null.");
		}
		stopThread();
//		Global.setLogin(false);
	}

	private static UdpThread udp;

	public static UdpThread newInstance() {
		if (udp == null) {
			synchronized (MainActivity.class) {
				if (udp == null) {
					udp = new UdpThread(mContext);
					Log.d("wuming", "UdpThread newInstance().");
				}
			}
		}
		return udp;
	}

	public interface ResponeListener {
		public void handPaiHangResult(String[] paihangAry);
		public void handYiDanResult(String[] yiDAry);
	}

	private ResponeListener listener;

	public void setResponeListener(ResponeListener mListener) {
		listener = mListener;
	}
}
