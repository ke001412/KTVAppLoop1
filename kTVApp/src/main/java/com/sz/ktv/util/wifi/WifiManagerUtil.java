package com.sz.ktv.util.wifi;

import java.util.ArrayList;
import java.util.List;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

public class WifiManagerUtil {
	 
	private Context mContext;
	public WifiManager wifiManager;
	private LinkWifi linkWifi;
	 
	private List<ScanResult> wifiList = new ArrayList<ScanResult>();
 

	public List<ScanResult> getWifiList()
	{
		return wifiList;
	}
	/**
	 * wifi适配器
	 */
	public WifiManagerUtil(Context context) {
		mContext = context;
		init();
	}

	private void init() {
		 

		linkWifi = new LinkWifi(mContext);
		wifiManager = (WifiManager) mContext
				.getSystemService(Service.WIFI_SERVICE);
		regWifiReceiver();
		scanAndGetResult();
	}
  

	/**
	 * 注册wifi广播
	 */
	private void regWifiReceiver() {
		IntentFilter labelIntentFilter = new IntentFilter();
		labelIntentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
		labelIntentFilter.addAction("android.net.wifi.STATE_CHANGE"); // ConnectivityManager.CONNECTIVITY_ACTION);
		labelIntentFilter.setPriority(1000); // 设置优先级，最高为1000
		mContext.registerReceiver(wifiResultChange, labelIntentFilter);

	}

	/**
	 * 开始扫描
	 */
	private void scanAndGetResult() {
		// 开始扫描
		wifiManager.startScan();
	}

	/**
	 * wifi广播
	 */
	private final BroadcastReceiver wifiResultChange = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if (action.equals(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)) {
				showWifiList();
			} else if (action.equals("android.net.wifi.STATE_CHANGE")) {
				// 刷新状态显示
				showWifiList();
			}
		}
	};

	private void showWifiList() {
		// 剔除ssid中的重复项，只保留相同ssid中信号最强的哪一个
				List<ScanResult> wifList = wifiManager.getScanResults();
				List<ScanResult> newWifList = new ArrayList<ScanResult>();
				boolean isAdd = true;

				if (wifList != null) {
					for (int i = 0; i < wifList.size(); i++) {
						isAdd = true;
						for (int j = 0; j < newWifList.size(); j++) {
							if (newWifList.get(j).SSID.equals(wifList.get(i).SSID)) {
								isAdd = false;
								if (newWifList.get(j).level < wifList.get(i).level) {
									// ssid相同且新的信号更强
									newWifList.remove(j);
									newWifList.add(wifList.get(i));
									break;
								}
							}
						}
						if (isAdd)
							newWifList.add(wifList.get(i));
					}
				}
				wifiList.clear();
				wifiList.addAll(newWifList);
			 
	}
	

	//根据Wifi信息获取本地Mac
    public static String getLocalMacAddressFromWifiInfo(Context context){
        WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);  
        WifiInfo info = wifi.getConnectionInfo();  
        return info.getMacAddress().toUpperCase(); 
    }
    
	public boolean connectWifi(final ScanResult wifiinfo,String ipPwd)
	{
		boolean flag = false;
		String capabilities = "";

		if (wifiinfo.capabilities.contains("WPA2-PSK")) {
			// WPA-PSK加密
			capabilities = "psk2";
		} else if (wifiinfo.capabilities.contains("WPA-PSK")) {
			// WPA-PSK加密
			capabilities = "psk";
		} else if (wifiinfo.capabilities.contains("WPA-EAP")) {
			// WPA-EAP加密
			capabilities = "eap";
		} else if (wifiinfo.capabilities.contains("WEP")) {
			// WEP加密
			capabilities = "wep";
		} else {
			// 无密码
			capabilities = "";
		}

		if (!capabilities.equals("")) {
			 
					// 此处加入连接wifi代码
					int netID = linkWifi.createWifiInfo2(wifiinfo, ipPwd);
					flag = linkWifi.connectToNetID(netID);

		} else {
			// 无密码
					int netID = linkWifi.createWifiInfo2(wifiinfo, "");
					flag = linkWifi.connectToNetID(netID);
			 

		}
		return flag; 
	}
	 
    /** Determine whether the wifi is available */
    public static boolean isWifiAvailable(Context context)
    {
        boolean isWifiAvailable = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null && activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI)
        {
            isWifiAvailable = true;
        }
        return isWifiAvailable;
    }
    
	private void configWifiRelay(final ScanResult wifiinfo) {
 

		String capabilities = "";

		if (wifiinfo.capabilities.contains("WPA2-PSK")) {
			// WPA-PSK加密
			capabilities = "psk2";
		} else if (wifiinfo.capabilities.contains("WPA-PSK")) {
			// WPA-PSK加密
			capabilities = "psk";
		} else if (wifiinfo.capabilities.contains("WPA-EAP")) {
			// WPA-EAP加密
			capabilities = "eap";
		} else if (wifiinfo.capabilities.contains("WEP")) {
			// WEP加密
			capabilities = "wep";
		} else {
			// 无密码
			capabilities = "";
		}

		if (!capabilities.equals("")) { } else { }
	}
}
