package com.sz.ktv.view.adapter;

import java.util.ArrayList;
import java.util.List;

import org.greenrobot.eventbus.EventBus;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sz.ktv.R;
import com.sz.ktv.util.Global;
import com.sz.ktv.view.WiFiListWindow;

public class WifiAdapter extends BaseAdapter {

	Context context ;
	List<ScanResult> wifiList = new ArrayList<ScanResult>() ;
	List<ScanResult> oneList= new ArrayList<ScanResult>() ;
	List<ScanResult> twoList= new ArrayList<ScanResult>() ;
	Handler myHandler; 
	
	public WifiAdapter(Context mContext, List<ScanResult> mWifiList,Handler handler)
	{
		myHandler = handler;
		this.context = mContext;
		this.wifiList = mWifiList;
		initData();
	}
	private void initData() {
		if(null != wifiList)
		{
			int size = wifiList.size();
			
			for(int i = 0;i<size;i++)
			{
				ScanResult scanner = wifiList.get(i);
				if(i%2==0)
				{
					oneList.add(scanner);
				}else {
					twoList.add(scanner);
				}
			}
		}
	}
	@Override
	public int getCount() {
		int sizeOne = oneList.size();
		int sizeTwo = twoList.size();
		return sizeOne>=sizeTwo?sizeOne:sizeTwo;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = Global.currentActivity.getLayoutInflater().inflate(R.layout.wifi_item_layout,
				null);
		TextView wifiOne = (TextView)view.findViewById(R.id.wifi_one);
		TextView wifiTwo = (TextView)view.findViewById(R.id.wifi_two);
		
		ScanResult one = null ;
		ScanResult two = null;
		
		int oneS = oneList.size();
		int twoS = twoList.size();
		if(position<=oneS-1)
		{
			one= oneList.get(position);
		}
		
		if(position<=twoS-1)
		{
			two= twoList.get(position);
		}
		
		if(null != one)
		{
			int oneLevel = one.level;
			String levelString = calculateSignalLevel(oneLevel);
			
			wifiOne.setText(""+one.SSID+"("+levelString+")");
			
			wifiOne.setTag(one);
			wifiOne.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					ScanResult info = (ScanResult)v.getTag();
					EventBus.getDefault().post(info);
					myHandler.sendEmptyMessage(WiFiListWindow.MSG_DISMISS);
				}
			});
		}
		if(null != two)
		{
			int twoLevel = two.level;
			String levelString = calculateSignalLevel(twoLevel);
			
			wifiTwo.setText(""+two.SSID+"("+levelString+")");
			 
			wifiTwo.setTag(two);
			wifiTwo.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					ScanResult info = (ScanResult)v.getTag();
					EventBus.getDefault().post(info);
					myHandler.sendEmptyMessage(WiFiListWindow.MSG_DISMISS);
				}
			});
		}
		return view;
	}

	 public String  calculateSignalLevel(int numLevels) {
		 if (Math.abs(numLevels) > 100) {  
            return "弱";  
         } else if (Math.abs(numLevels) > 80) {  
        	 return "弱"; 
         } else if (Math.abs(numLevels) > 70) {  
        	 return "弱"; 
         } else if (Math.abs(numLevels) > 60) {  
        	 return "较强"; 
         } else if (Math.abs(numLevels) > 50) {  
        	 return "强"; 
         } else {  
        	 return "强";  
         }  
  
	    }
}
