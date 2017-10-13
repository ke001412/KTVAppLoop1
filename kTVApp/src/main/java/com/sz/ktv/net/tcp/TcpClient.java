package com.sz.ktv.net.tcp;

import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.EditText;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;

//import com.song.ktv.bean.SongBean;
//import com.song.ktv.db.DB;
//import com.song.ktv.db.DB.OnPaiHangDBListener;
//import com.song.ktv.net.util.Command.OnYiDianDataListener;
//import com.song.ktv.ui.activity.MainActivity;
//import com.song.ktv.util.Global;

public class TcpClient
{
//	private String sRecvData;
//	private ServerSocket rSocket = null;  
	private String remoteIP;
	private int remotePort, localPort;
	private String currentSCodes = "UTF-8";
	private String currentRCodes = "UTF-8";
//	private static Socket mTcpSocket;
	private File file;
	private Context mContext;
	private final static String SINGER_FILE = "singer.txt";
	private final static String SONG_FILE = "song.txt";
	private final static String SONG_NEW_FILE = "newsong.txt";
	private static String TAG = "wuming";
	
	public TcpClient(Context context) {
		mContext = context;
	}
	
	public void setRemoteIP(String remoteIP)
	{
		this.remoteIP = remoteIP;
	}
	
	public void setRemotePort(int remotePort)
	{
		this.remotePort = remotePort;
	}
	
	public void setLocalPort(int localPort)
	{
		this.localPort = localPort;
	}
	
	public void setCurrentSCodes(String currentSCodes)
	{
		this.currentSCodes = currentSCodes;
	}

	public void setCurrentRCodes(String currentRCodes)
	{
		this.currentRCodes = currentRCodes;
	}
	
	public synchronized void SendData(String strData, int iType) {
		Log.d("wuming", "TcpClient SendData data = " + strData + " , iType = " + iType);
		final String data = strData;
		final int type = iType;
//        new Thread(new Runnable() {
//
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
				Socket mTcpSocket = null;
				try {
					if(mTcpSocket == null) {
						mTcpSocket = new Socket(remoteIP, remotePort);
						//���缃�缂���插�哄ぇ灏�
						mTcpSocket.setSendBufferSize(1024*64);
						mTcpSocket.setReceiveBufferSize(1024*64);
						Log.d(TAG, "new socket in thread: " + Thread.currentThread().getId()
								+ " , socket id: " + mTcpSocket.getPort());
					}
				} catch (UnknownHostException e) {
					Log.d("wuming" , "socket need close1..");
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					Log.d("wuming" , "socket need close 2 ..");
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

                sendTcpData(mTcpSocket, data, type);							
//			}	
//     	   
//        }).start();
	}


	private synchronized void sendTcpData(Socket socket, String str, int iType) {	
        BufferedOutputStream bos = null;
        BufferedInputStream bis = null;
        if(socket == null) {
        	Log.d("wuming", "sendTcpData() return, socket is null.");
        	return;
        }
        
        try {
			bos = new BufferedOutputStream(socket.getOutputStream());
	        bis = new BufferedInputStream(socket.getInputStream());

	        if(bos != null && bis != null) {
		        bos.write(str.getBytes());
		        bos.flush();
		        Log.d("wuming", "sendTcpData() send." );
		        recvData(bis, iType);//bos, 
	        } else {
	        	Log.d("wuming", "sendTcpData() return, bos/bis is null.");
	        	return;
	        }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		      Log.d(TAG, "socket is finally");
		      try {
		        if (bos != null) {
		          bos.close();
		        }
		      } catch (IOException e2) {
		        // TODO: handle exception
		        e2.printStackTrace();
		      }
		      try {
		        if (bis != null) {
		          bis.close();
		        }
		      } catch (IOException e2) {
		        // TODO: handle exception
		        e2.printStackTrace();
		      }
		      try {		
		    	  if(socket != null) {
		    		  socket.close();	
		    		  socket = null;
		    		  Log.d(TAG, "socket close. thread id = " + Thread.currentThread().getId());
		    	  }
		        
		      } catch (IOException e2) {
		        // TODO: handle exception
		        e2.printStackTrace();
		      }
		    }
	}
	
	private synchronized void recvData(BufferedInputStream bis, int iType) {// BufferedOutputStream bos,
		Log.d(TAG, "TcpClient recvData().");

		byte[] readBuffer = new byte[1024*4];
	    byte[] tempdata = null;	    
	    int lenth;
	    FileOutputStream fos = null;
	    try {
			fos = new FileOutputStream(getPath(iType));
			Log.d(TAG, "receive data, create file path = " + getPath(iType) + " , iType = " + iType);
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	    
	    try {
	    	Log.d(TAG, "receive data, before while.");
	      while((lenth = bis.read(readBuffer)) != -1){
//	    	  Log.d(TAG, "receive data, length = " + lenth);
	        if (lenth > 0) {
	        	tempdata = new byte[lenth];
	        	System.arraycopy(readBuffer, 0, tempdata, 0, lenth);	          	             
//				Log.d(TAG, "receive data, length = " + tempdata.length);
				fos.write(tempdata);
				fos.flush();
	        }
	      }
	      fos.close();
	      Log.d(TAG, "receive data, close fos.");
//	      //update txt into List
////	      if(Global.isFileUpdated(mContext, 0) ) {
////	    	  Global.setNewsongModified(true);
////	      } else if(Global.isFileUpdated(mContext, 1)) {
////	    	  Global.setSingerModified(true);
////	      } else if(Global.isFileUpdated(mContext, 2)) {
////	    	  Global.setSongModified(true);
////	      }
//	      
//	      
//	      Global.createRecvFilesNum();
//	      fileReceiveListener.fileReceived(Global.getRecvFilesNum());
//	    	
//	      Log.d("wuming", "TcpClient recv data, getRecvFilesNum() = " + Global.getRecvFilesNum());
//	      if(Global.getRecvFilesNum() > 2){
//	    	  mOnUpdateDBListener.dbUpdateFinished();
//	    	  Log.d("wuming", "TcpClient recv data, start create List.");
//		      new Thread(new Runnable() {
//					@Override
//					public void run() {
//						
//						DB.newInstance().loadTxt2List(mContext, 0);
//						DB.newInstance().loadTxt2List(mContext, 1);
//						DB.newInstance().loadTxt2List(mContext, 2);
//						Global.setListDataCreated(true);
////						mOnUpdateDBListener.dbUpdateFinished();
//						Global.setDownloadAndUpdate(false);
//						Global.setRecvFilesNum(0);
//						Log.d("wuming", "TcpClient recv data, create List, send callback.");
//					}
//				}).start();
//	      }

	    } catch (IOException e1) {
	      e1.printStackTrace();
	    } 
	    
	    
//	    try { 
//	    	if(mTcpSocket != null) {
//		      mTcpSocket.close();
//		      mTcpSocket = null;
//		      Log.d(TAG, "close socket.");
//	    	}
//	    } catch (IOException e) {
//	      // TODO: handle exception
//	    }
	}
	
	private String getPath(int type) {	
		String txt_path = mContext.getFilesDir().getParent() + "/";

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
	
	//data callback
		public interface OnUpdateDBListener {
			void dbUpdateFinished();
		}

		private static OnUpdateDBListener mOnUpdateDBListener;

		public void setUpdateDBFinishListener(
				OnUpdateDBListener listener) {			
			this.mOnUpdateDBListener = listener;
		}
	
		//receive callback
		public interface OnReceiveFileListener {
			void fileReceived(int num);
		}

		private static OnReceiveFileListener fileReceiveListener;

		public void setReceiveFileListener(
				OnReceiveFileListener listener) {
			this.fileReceiveListener = listener;
		}
}
