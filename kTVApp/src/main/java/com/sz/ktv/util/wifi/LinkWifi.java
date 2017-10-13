package com.sz.ktv.util.wifi;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import android.app.Service;
import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;

public class LinkWifi {

	private WifiManager wifiManager;

	/** 定义几种加密方式，一种是WEP，一种是WPA/WPA2，还有没有密码的情况 */
	public enum WifiCipherType {
		WIFI_CIPHER_WEP, WIFI_CIPHER_WPA_EAP, WIFI_CIPHER_WPA_PSK, WIFI_CIPHER_WPA2_PSK, WIFI_CIPHER_NO
	}

	public LinkWifi(Context context) {
		wifiManager = (WifiManager) context
				.getSystemService(Service.WIFI_SERVICE);
	}

	/**
	 * 检测wifi状态 opened return true;
	 */
	public boolean checkWifiState() {
		boolean isOpen = true;
		int wifiState = wifiManager.getWifiState();

		if (wifiState == WifiManager.WIFI_STATE_DISABLED
				|| wifiState == WifiManager.WIFI_STATE_DISABLING
				|| wifiState == WifiManager.WIFI_STATE_UNKNOWN
				|| wifiState == WifiManager.WIFI_STATE_ENABLING) {
			isOpen = false;
		}

		return isOpen;
	}

	public boolean connectToNetID(int netID) {
		return wifiManager.enableNetwork(netID, true);
	}

	/** 查看以前是否也配置过这个网络 */
	public WifiConfiguration isExsits(String sSID) {
		List<WifiConfiguration> existingConfigs = wifiManager
				.getConfiguredNetworks();
		if(null!=existingConfigs){
			for (WifiConfiguration existingConfig : existingConfigs) {
				
				if (existingConfig.SSID.equals("\"" + sSID + "\"")) {
					return existingConfig;
				}
			}
		}
		return null;
	}

	public int createWifiInfo2(ScanResult wifiinfo, String pwd) {
		WifiCipherType type;

		if (wifiinfo.capabilities.contains("WPA2-PSK")) {
			// WPA-PSK加密
			type = WifiCipherType.WIFI_CIPHER_WPA2_PSK;
		} else if (wifiinfo.capabilities.contains("WPA-PSK")) {
			// WPA-PSK加密
			type = WifiCipherType.WIFI_CIPHER_WPA_PSK;
		} else if (wifiinfo.capabilities.contains("WPA-EAP")) {
			// WPA-EAP加密
			type = WifiCipherType.WIFI_CIPHER_WPA_EAP;
		} else if (wifiinfo.capabilities.contains("WEP")) {
			// WEP加密
			type = WifiCipherType.WIFI_CIPHER_WEP;
		} else {
			// 无密码
			type = WifiCipherType.WIFI_CIPHER_NO;
		}

		WifiConfiguration config = createWifiInfo(wifiinfo.SSID,
				wifiinfo.BSSID, pwd, type);
		if (config != null) {
			return wifiManager.addNetwork(config);
		} else {
			return -1;
		}
	}

	public WifiConfiguration setMaxPriority(WifiConfiguration config) {
		int priority = getMaxPriority() + 1;
		if (priority > 99999) {
			priority = shiftPriorityAndSave();
		}

		config.priority = priority; 

		wifiManager.updateNetwork(config);

		// 本机之前配置过此wifi热点，直接返回
		return config;
	}

	/** 配置一个连接 */
	public WifiConfiguration createWifiInfo(String sSID, String bSSID,
			String pWord, WifiCipherType type) {

		int priority;

		WifiConfiguration config = this.isExsits(sSID);
		if (config != null) {
			// 本机之前配置过此wifi热点，调整优先级后，直接返回
			return setMaxPriority(config);
		}

		config = new WifiConfiguration();
		/* 清除之前的连接信息 */
		config.allowedAuthAlgorithms.clear();
		config.allowedGroupCiphers.clear();
		config.allowedKeyManagement.clear();
		config.allowedPairwiseCiphers.clear();
		config.allowedProtocols.clear();
		config.SSID = "\"" + sSID + "\"";
		config.status = WifiConfiguration.Status.ENABLED;

		priority = getMaxPriority() + 1;
		if (priority > 99999) {
			priority = shiftPriorityAndSave();
		}

		config.priority = priority; // 2147483647;
		/* 各种加密方式判断 */
		if (type == WifiCipherType.WIFI_CIPHER_NO) {
			config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
			config.wepTxKeyIndex = 0;
		} else if (type == WifiCipherType.WIFI_CIPHER_WEP) {
			config.preSharedKey = "\"" + pWord + "\"";

			config.allowedAuthAlgorithms
					.set(WifiConfiguration.AuthAlgorithm.SHARED);
			config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
			config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
			config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
			config.allowedGroupCiphers
					.set(WifiConfiguration.GroupCipher.WEP104);
			config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
			config.wepTxKeyIndex = 0;
		} else if (type == WifiCipherType.WIFI_CIPHER_WPA_EAP) {
			config.preSharedKey = "\"" + pWord + "\"";
			config.hiddenSSID = true;
			config.status = WifiConfiguration.Status.ENABLED;
			config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
			config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
			config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_EAP);

			config.allowedPairwiseCiphers
					.set(WifiConfiguration.PairwiseCipher.TKIP);
			config.allowedPairwiseCiphers
					.set(WifiConfiguration.PairwiseCipher.CCMP);
			config.allowedProtocols.set(WifiConfiguration.Protocol.RSN
					| WifiConfiguration.Protocol.WPA);

		} else if (type == WifiCipherType.WIFI_CIPHER_WPA_PSK) {
			config.preSharedKey = "\"" + pWord + "\"";
			config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
			config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
			config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);

			config.allowedPairwiseCiphers
					.set(WifiConfiguration.PairwiseCipher.TKIP);
			config.allowedPairwiseCiphers
					.set(WifiConfiguration.PairwiseCipher.CCMP);
			config.allowedProtocols.set(WifiConfiguration.Protocol.RSN
					| WifiConfiguration.Protocol.WPA);

		} else if (type == WifiCipherType.WIFI_CIPHER_WPA2_PSK) {
			config.preSharedKey = "\"" + pWord + "\"";
			config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
			config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);

			config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);

			config.allowedPairwiseCiphers
					.set(WifiConfiguration.PairwiseCipher.TKIP);
			config.allowedPairwiseCiphers
					.set(WifiConfiguration.PairwiseCipher.CCMP);
			config.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
			config.allowedProtocols.set(WifiConfiguration.Protocol.WPA);

		} else {
			return null;
		}

		return config;
	}

	private int getMaxPriority() {
		List<WifiConfiguration> localList = this.wifiManager
				.getConfiguredNetworks();
		int i = 0;
		if(null!=localList){
			Iterator<WifiConfiguration> localIterator = localList.iterator();
			while (true) {
				if (!localIterator.hasNext()){return i;}
				
				WifiConfiguration localWifiConfiguration = (WifiConfiguration) localIterator
						.next();
				if (localWifiConfiguration.priority <= i){continue;}
				
				i = localWifiConfiguration.priority;
			}
		}
		return i;
	}

	private int shiftPriorityAndSave() {
		List<WifiConfiguration> localList = this.wifiManager
				.getConfiguredNetworks();
		if(null!=localList){
			sortByPriority(localList);
			int i = localList.size();
			for (int j = 0;; ++j) {
				if (j >= i) {
					this.wifiManager.saveConfiguration();
					return i;
				}
				WifiConfiguration localWifiConfiguration = (WifiConfiguration) localList
						.get(j);
				localWifiConfiguration.priority = j;
				this.wifiManager.updateNetwork(localWifiConfiguration);
			}
		}
		return 0;
	}

	private void sortByPriority(List<WifiConfiguration> paramList) {
		if(null!=paramList){
			Collections.sort(paramList, new SjrsWifiManagerCompare());
		}
	}

	private static class SjrsWifiManagerCompare implements Comparator<WifiConfiguration> ,Serializable{
		 
		private static final long serialVersionUID = 1L;

		public int compare(WifiConfiguration paramWifiConfiguration1,
				WifiConfiguration paramWifiConfiguration2) {
			return paramWifiConfiguration1.priority
					- paramWifiConfiguration2.priority;
		}
	}
}
