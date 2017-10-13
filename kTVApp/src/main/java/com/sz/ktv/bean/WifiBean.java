package com.sz.ktv.bean;

import java.io.Serializable;
import java.util.List;

import android.net.wifi.ScanResult;

public class WifiBean implements Serializable {

	List<ScanResult> wifiList ;

	/**
	 * @return the wifiList
	 */
	public List<ScanResult> getWifiList() {
		return wifiList;
	}

	/**
	 * @param wifiList the wifiList to set
	 */
	public void setWifiList(List<ScanResult> wifiList) {
		this.wifiList = wifiList;
	}
	
	
}
