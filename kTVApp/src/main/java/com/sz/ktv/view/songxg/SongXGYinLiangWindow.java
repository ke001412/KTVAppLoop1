package com.sz.ktv.view.songxg;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.sz.ktv.R;

public class SongXGYinLiangWindow extends PopupWindow implements OnClickListener {

	
	TextView tv10;
	TextView tv20;
	TextView tv30;
	TextView tv40;
	TextView tv50;
	TextView tv60;
	TextView tv70;
	TextView tv80;
	TextView tv90;
	TextView tv100;
	
	public SongXGYinLiangWindow(Activity context) {
		super(context);
		
		 LayoutInflater inflater = (LayoutInflater) context  
	                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
	        conentView = inflater.inflate(R.layout.songxg_yinliang_layout, null);  
	        int h = context.getWindowManager().getDefaultDisplay().getHeight();  
	        int w = context.getWindowManager().getDefaultDisplay().getWidth();  
	        // 设置SelectPicPopupWindow的View  
	        this.setContentView(conentView);  
	        // 设置SelectPicPopupWindow弹出窗体的宽  
	        this.setWidth(w /5);  
	        // 设置SelectPicPopupWindow弹出窗体的高  
	        this.setHeight(h/5);  //LayoutParams.WRAP_CONTENT
	        // 设置SelectPicPopupWindow弹出窗体可点击  
	        this.setFocusable(true);  
	        this.setOutsideTouchable(true);  
	        // 刷新状态  
	        this.update();  
	      
	        tv10 = (TextView)conentView.findViewById(R.id.xiugai_10);
	        tv20 = (TextView)conentView.findViewById(R.id.xiugai_20);
	        tv30 = (TextView)conentView.findViewById(R.id.xiugai_30);
	        tv40 = (TextView)conentView.findViewById(R.id.xiugai_40);
	        tv50 = (TextView)conentView.findViewById(R.id.xiugai_50);
	        tv60 = (TextView)conentView.findViewById(R.id.xiugai_60);
	        tv70 = (TextView)conentView.findViewById(R.id.xiugai_70);
	        tv80 = (TextView)conentView.findViewById(R.id.xiugai_80);
	        tv90 = (TextView)conentView.findViewById(R.id.xiugai_90);
	        tv100 = (TextView)conentView.findViewById(R.id.xiugai_100);
	        
	        tv10.setOnClickListener(this);
	        tv20.setOnClickListener(this);
	        tv30.setOnClickListener(this);
	        tv40.setOnClickListener(this);
	        tv50.setOnClickListener(this);
	        tv60.setOnClickListener(this);
	        tv70.setOnClickListener(this);
	        tv80.setOnClickListener(this);
	        tv90.setOnClickListener(this);
	        tv100.setOnClickListener(this);
	}

	 
	private View conentView;  
 
	@Override
	public void onClick(View v) {

		int id = v.getId();
		switch (id) {
		case R.id.xiugai_10:
			yinliangClick(10);
			dismiss();
			break;
		case R.id.xiugai_20:
			yinliangClick(20);
			dismiss();
			break;
		case R.id.xiugai_30:
			yinliangClick(30);
			dismiss();
			break;
		case R.id.xiugai_40:
			yinliangClick(40);
			dismiss();
			break;
		case R.id.xiugai_50:
			yinliangClick(50);
			dismiss();
			break;
		case R.id.xiugai_60:
			yinliangClick(60);
			dismiss();
			break;
		case R.id.xiugai_70:
			yinliangClick(70);
			dismiss();
			break;
		case R.id.xiugai_80:
			yinliangClick(80);
			dismiss();
			break;
		case R.id.xiugai_90:
			yinliangClick(90);
			dismiss();
			break;
		case R.id.xiugai_100:
			yinliangClick(100);
			dismiss();
			break;
		default:
			break;
		}
	
	}
	
	public void yinliangClick(int yuyan)
	{
		
	}
	 public void showPopupWindow(View parent) {  
	        if (!this.isShowing()) {  
	            this.showAtLocation(parent, Gravity.CENTER, 60, parent.getHeight()) ;
	        } else {  
	            this.dismiss();  
	        }  
	    } 
}
