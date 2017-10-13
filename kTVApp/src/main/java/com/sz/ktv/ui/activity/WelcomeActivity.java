package com.sz.ktv.ui.activity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;

import com.sz.ktv.MainActivity;
import com.sz.ktv.R;
import com.sz.ktv.msg.LoadMessage;
import com.sz.ktv.ui.base.BaseActivity;
import com.sz.ktv.ui.service.LoadService;
import com.sz.ktv.util.Global;
import com.sz.ktv.util.LanguageUtil;

public class WelcomeActivity extends BaseActivity implements OnClickListener {

	
	private static final int MSG_LOAD_FINISH = 10;
 
	Handler myHandler = new Handler(){
		public void dispatchMessage(android.os.Message msg) {
			
			int what = msg.what;
			switch (what) {
			case MSG_LOAD_FINISH:
				Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
				startActivity(intent);
				finish();
				break;

			default:
				break;
			}
		};
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome_layout);
		Global.currentActivity =this;
		 
	}
	
	private void initEventBus()
	{
		EventBus.getDefault().register(this);
	}
	@Subscribe(threadMode = ThreadMode.ASYNC)
	public void onAsyncEventBus(LoadMessage msg) {
		myHandler.sendEmptyMessage(MSG_LOAD_FINISH);

	}
	
	@Override
	protected void onResume() {
		super.onResume();
		initEventBus();
		Intent intent = new Intent(this,LoadService.class);
		startService(intent);
	}
	
	 
	@Override
	protected void onDestroy() {
		super.onDestroy();
		EventBus.getDefault().unregister(this);
		Intent intent = new Intent(this,LoadService.class);
		stopService(intent);
	}
	
	
	@Override
	public void onClick(View v) {

	}

}
