package com.sz.ktv.view;

import org.greenrobot.eventbus.EventBus;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.sz.ktv.MainActivity;
import com.sz.ktv.R;
import com.sz.ktv.msg.SingerTypeMessage;
import com.sz.ktv.msg.SongTypeMessage;
import com.sz.ktv.ui.fragment.HomeGeMingFragment;
import com.sz.ktv.ui.fragment.HomeGexingFragment;

public class CopyOfHomeSearchWindow extends PopupWindow implements OnClickListener {

	EditText searchEdit;
	Button btShouPin;
	Button btShouXie;
	Button btZiShu;

	Button yi;
	Button er;
	Button san;
	Button si;
	Button wu;
	Button liu;
	Button qi;
	Button ba;
	Button duo;

	LinearLayout layout;
	
	public CopyOfHomeSearchWindow(Activity context) {
		super(context);

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		conentView = inflater.inflate(R.layout.home_search_dialog, null);
		int h = context.getWindowManager().getDefaultDisplay().getHeight();
		int w = context.getWindowManager().getDefaultDisplay().getWidth();
		// 设置SelectPicPopupWindow的View
		this.setContentView(conentView);
		// 设置SelectPicPopupWindow弹出窗体的宽
		this.setWidth(w / 2);
		// 设置SelectPicPopupWindow弹出窗体的高
		this.setHeight(h / 2); // LayoutParams.WRAP_CONTENT
		// 设置SelectPicPopupWindow弹出窗体可点击
		this.setFocusable(true);
		this.setOutsideTouchable(true);
		// 刷新状态
		this.update();

		searchEdit = (EditText) conentView.findViewById(R.id.search_text);
		btShouPin = (Button) conentView.findViewById(R.id.search_shoupin);
		btShouXie = (Button) conentView.findViewById(R.id.search_shouxie);
		btZiShu = (Button)conentView.findViewById(R.id.search_zishu);
		
		yi = (Button) conentView.findViewById(R.id.search_1);
		er = (Button) conentView.findViewById(R.id.search_2);
		san = (Button) conentView.findViewById(R.id.search_3);
		si = (Button) conentView.findViewById(R.id.search_4);
		wu = (Button) conentView.findViewById(R.id.search_5);
		liu = (Button) conentView.findViewById(R.id.search_6);
		qi = (Button) conentView.findViewById(R.id.search_7);
		ba = (Button) conentView.findViewById(R.id.search_8);
		duo = (Button) conentView.findViewById(R.id.search_duo);

		btShouPin.setOnClickListener(this);
		btShouXie.setOnClickListener(this);
		btZiShu.setOnClickListener(this);
		
		yi.setOnClickListener(this);
		er.setOnClickListener(this);
		san.setOnClickListener(this);
		si.setOnClickListener(this);
		wu.setOnClickListener(this);
		liu.setOnClickListener(this);
		qi.setOnClickListener(this);
		ba.setOnClickListener(this);
		duo.setOnClickListener(this);

		flag  =true;
		btZiShu.setBackgroundColor(Color.WHITE);
		btShouPin.setBackgroundColor(Color.DKGRAY);
		
		layout = (LinearLayout)conentView.findViewById(R.id.search_zishu_layout);
		
		searchEdit.addTextChangedListener(new MyTextWather());
	}

	class MyTextWather implements TextWatcher {

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {

		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {

		}

		@Override
		public void afterTextChanged(Editable s) {

			if (!MainActivity.gexingHidden && flag) {
				String str = s.toString();
				SingerTypeMessage msg = new SingerTypeMessage();
				msg.setMsg(str);
				msg.setSingerType(HomeGexingFragment.TYPE_SINGER_SHOUPIN);
				EventBus.getDefault().post(msg);
			} else {
				if(flag)
				{
				String str = s.toString();
				SongTypeMessage msg = new SongTypeMessage();
				msg.setKeyWords(str);
				msg.setType(HomeGeMingFragment.TYPE_SONG_SHOUPIN);
				EventBus.getDefault().post(msg);
				}
			}
		}

	}

	private View conentView;
	private String zishuNum = "0";
	private boolean flag = false; 
	
	private void gotoGemingZiShu()
	{
		 
			 
			SongTypeMessage msg = new SongTypeMessage();
			msg.setKeyWords(zishuNum);
			msg.setType(HomeGeMingFragment.TYPE_SONG_ZISHU);
			EventBus.getDefault().post(msg);
		 
	}
	@Override
	public void onClick(View v) {

		int id = v.getId();
		switch (id) {
		case R.id.search_1:
			zishuNum = "1";
			gotoGemingZiShu();
			break;
		case R.id.search_2:
			zishuNum = "2";
			gotoGemingZiShu();
			break;
		case R.id.search_3:
			zishuNum = "3";
			gotoGemingZiShu();
			break;
		case R.id.search_4:
			zishuNum = "4";
			gotoGemingZiShu();
			break;
		case R.id.search_5:
			zishuNum = "5";
			gotoGemingZiShu();
			break;
		case R.id.search_6:
			zishuNum = "6";
			gotoGemingZiShu();
			break;
		case R.id.search_7:
			
			zishuNum = "7";
			gotoGemingZiShu();
			break;
		case R.id.search_8:
		
			zishuNum = "8";
			gotoGemingZiShu();
			break;
		case R.id.search_duo:
			zishuNum = "9";
			gotoGemingZiShu();
			break;
		case R.id.search_shouxie:

			break;
		case R.id.search_zishu:
			if(!MainActivity.gexingHidden)
			{
				return ;
			}
			flag = false;
			layout.setVisibility(View.VISIBLE);
			
			btZiShu.setBackgroundColor(Color.DKGRAY);
			btShouPin.setBackgroundColor(Color.WHITE);
			
			break;
		case R.id.search_shoupin:
			flag  =true;
			layout.setVisibility(View.GONE);
			btZiShu.setBackgroundColor(Color.WHITE);
			btShouPin.setBackgroundColor(Color.DKGRAY);
			
			break;
		default:
			break;
		}

	}

	public void showPopupWindow(View parent) {
		if (!this.isShowing()) {
			this.showAtLocation(parent, Gravity.TOP, 30, parent.getHeight());
		} else {
			this.dismiss();
		}
	}
}
