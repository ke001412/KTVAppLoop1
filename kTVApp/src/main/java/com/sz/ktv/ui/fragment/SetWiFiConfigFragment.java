package com.sz.ktv.ui.fragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import android.net.wifi.ScanResult;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sz.ktv.MainActivity;
import com.sz.ktv.R;
import com.sz.ktv.msg.AsyncMessage;
import com.sz.ktv.ui.base.BaseFragment;
import com.sz.ktv.util.FragmentFactory;
import com.sz.ktv.util.wifi.WifiManagerUtil;
import com.sz.ktv.view.WiFiListWindow;
import com.sz.ktv.view.widget.KtvBottomPageWidget;
import com.sz.ktv.view.widget.KtvBottomPageWidget.BackClickListener;

public class SetWiFiConfigFragment extends BaseFragment implements
		OnClickListener, BackClickListener {

	KtvBottomPageWidget bottomPage;

	Button wifiScan;
	Button wifiConnect;
	Button wifiCancle;

	WifiManagerUtil wifiManage;
	
	TextView wifiTilte;
	EditText wifiSSID;
	EditText wifiPWD;
	TextView wifiMAC;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = null;
		try {
			view = inflater.inflate(R.layout.system_wifi_config_layout,
					container, false);
		} catch (Exception e) {
			e.printStackTrace();
		}

		initView(view);
		initWifi();
		initEventBus();
		return view;
	}
	
	private static final int MSG_SHOW_WIFI = 11;
	private ScanResult currentWifi  ;
	Handler myHandler = new Handler(){
		public void dispatchMessage(Message msg) {
			switch (msg.what) {
			case MSG_SHOW_WIFI:
				ScanResult wifiInfo = (ScanResult)msg.obj;
				currentWifi = wifiInfo;
				
				wifiSSID.setText(wifiInfo.SSID);
				wifiPWD.setText("");
				
				break;
			default:
				break;
			}
		};
	};
	@Subscribe(threadMode = ThreadMode.ASYNC)
	public void onAsyncEventBus(ScanResult msg) {
		 
		Message messages = myHandler.obtainMessage();
		messages.obj = msg;
		messages.what = MSG_SHOW_WIFI;
		myHandler.sendMessage(messages);

	}


	private void initEventBus() {
		 
		EventBus.getDefault().register(this);
		
	}

	private void initWifi() {
		wifiManage = new WifiManagerUtil(getActivity());
		
	}

	private void initView(View view) {
		bottomPage = (KtvBottomPageWidget) view
				.findViewById(R.id.sys_wifi_config_back);
		bottomPage.setBackClickListener(this);
		bottomPage.setType(KtvBottomPageWidget.TYPE_NO_DOTS_NO_PAGENUM);

		wifiScan = (Button) view.findViewById(R.id.wifi_scan);
		wifiConnect = (Button) view.findViewById(R.id.wifi_connect);
		wifiCancle = (Button) view.findViewById(R.id.wifi_cancle);

		wifiScan.setOnClickListener(this);
		wifiConnect.setOnClickListener(this);
		wifiCancle.setOnClickListener(this);

		wifiTilte = (TextView)view.findViewById(R.id.wifi_title);
		wifiSSID = (EditText)view.findViewById(R.id.wifi_ssid);
		wifiPWD = (EditText)view.findViewById(R.id.wifi_pwd);
		
		wifiMAC = (TextView)view.findViewById(R.id.wifi_mac);
		String mac = WifiManagerUtil.getLocalMacAddressFromWifiInfo(getActivity());
		wifiMAC.setText(mac);
		
	}

	WiFiListWindow wifiWindow ;
	@Override
	public void back() {
		boolean homeWifiClick = MainActivity.homeWifiClick;
		if(homeWifiClick)
		{
			MainActivity.homeWifiClick = false;
			backToHome();
		}else {
		EventBus.getDefault().post(
				new AsyncMessage(FragmentFactory.FRAGMENT_HOME_SYSTEM_SET));
		}
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.wifi_cancle:
			back();
			break;
		case R.id.wifi_scan:
			
			wifiWindow= new WiFiListWindow(getActivity(), wifiManage);
			wifiWindow.showPopupWindow(wifiTilte);
			
			break;
		case R.id.wifi_connect:
			String pwd = wifiPWD.getText().toString();
			
			boolean flag = wifiManage.connectWifi(currentWifi, pwd);
			if(flag)
			{
				Toast.makeText(getActivity(), "Wifi连接成功!", 1).show();
			}else {
				Toast.makeText(getActivity(), "Wifi连接失败,请重试!", 1).show();
			}
			break;

		default:
			break;
		}

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
//		if(wifiWindow.isShowing())
//		{
//		wifiWindow.unRegisterEventBus();
//		}
		EventBus.getDefault().unregister(this);
	}
}
