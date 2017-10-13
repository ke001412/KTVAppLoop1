package com.sz.ktv.view;

import java.util.List;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.ScanResult;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.sz.ktv.R;
import com.sz.ktv.msg.WifiListMessage;
import com.sz.ktv.util.wifi.WifiManagerUtil;
import com.sz.ktv.view.adapter.WifiAdapter;

public class WiFiListWindow extends PopupWindow implements OnClickListener {

	
	ListView wifiListView;
	WifiManagerUtil wifi;
	
	 
	List<ScanResult> wifiList;
	
	
	WifiAdapter adapter ; 
	Context context ;
	 
	
	
	public WiFiListWindow(Activity mContext,WifiManagerUtil wifiManager) {
		super(mContext);
		wifi = wifiManager;
		context = mContext;
		 LayoutInflater inflater = (LayoutInflater) context  
	                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
	        conentView = inflater.inflate(R.layout.wifi_list_layout, null);  
	        int h = mContext.getWindowManager().getDefaultDisplay().getHeight();  
	        int w = mContext.getWindowManager().getDefaultDisplay().getWidth();  
	        // 设置SelectPicPopupWindow的View  
	        this.setContentView(conentView);  
	        // 设置SelectPicPopupWindow弹出窗体的宽  
	        this.setWidth(w /3+w /3);  
	        // 设置SelectPicPopupWindow弹出窗体的高  
	        this.setHeight(h/3);  //LayoutParams.WRAP_CONTENT
	        // 设置SelectPicPopupWindow弹出窗体可点击  
	        this.setFocusable(true);  
	        this.setOutsideTouchable(true);  
	        // 刷新状态  
	        this.update();  
	     // 实例化一个ColorDrawable颜色为半透明  
//	        ColorDrawable dw = new ColorDrawable(0000000000);  
	        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作  
//	        this.setBackgroundDrawable(dw);  
	        // mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);  
	        // 设置SelectPicPopupWindow弹出窗体动画效果  
//	        this.setAnimationStyle(R.style.AnimationPreview);
	        
	        wifiListView = (ListView)conentView.findViewById(R.id.wifi_list);
	        initData();
	}

	
	 
	private void initData() {
		wifiList =wifi.getWifiList();
		adapter = new WifiAdapter(context, wifiList,setWifiHandler);
		wifiListView.setAdapter(adapter);
		adapter.notifyDataSetChanged();
	}
	
	private static final int MSG_INIT_DATA = 10;
	public static final int MSG_DISMISS = 11;
	
	private Handler setWifiHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case MSG_INIT_DATA:
				initData();
				break;
			case MSG_DISMISS:
				dismiss();
				unRegisterEventBus();
				break;
			default:
				break;
			}
			
			 
		};
	};
	@Subscribe(threadMode = ThreadMode.ASYNC)
	public void onAsyncEventBus(WifiListMessage msg) {
		 
		setWifiHandler.sendEmptyMessage(MSG_INIT_DATA);
	}
	
	

	public void registerEventBus()
	{
		EventBus.getDefault().register(this);
	}

	public void unRegisterEventBus()
	{
		EventBus.getDefault().unregister(this);
	}


	private View conentView;  
	
	@Override
	public void onClick(View v) {
	
	}
	
	 public void showPopupWindow(View parent) {  
	        if (!this.isShowing()) {  
	            this.showAtLocation(parent, Gravity.TOP, 60, 3*parent.getHeight()) ;
	        } else {  
	            this.dismiss();  
	        }  
	    } 
}
