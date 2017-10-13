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
import com.sz.ktv.db.DataBase;

public class SongXGLiuXingWindow extends PopupWindow implements OnClickListener {

	
	TextView tvZghsy;
	TextView tvZghgq;
	TextView tvWsgs;
	TextView tvZgyc;
	TextView tvZmhs;
	TextView tvMxxdd;
	TextView tvZgzqy;
	TextView tvMmgw;
	
	public SongXGLiuXingWindow(Activity context) {
		super(context);
		
		 LayoutInflater inflater = (LayoutInflater) context  
	                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
	        conentView = inflater.inflate(R.layout.songxg_liuxing_layout, null);  
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
	      
	        tvZghsy = (TextView)conentView.findViewById(R.id.xiugai_zghsy);
	        tvZghgq = (TextView)conentView.findViewById(R.id.xiugai_zghgq);
	        tvWsgs= (TextView)conentView.findViewById(R.id.xiugai_wsgs);
	        tvZgyc= (TextView)conentView.findViewById(R.id.xiugai_zgyc);
	        tvZmhs= (TextView)conentView.findViewById(R.id.xiugai_zmhs);
	        tvMxxdd= (TextView)conentView.findViewById(R.id.xiugai_mxxdd);
	        tvZgzqy= (TextView)conentView.findViewById(R.id.xiugai_zgzqy);
	        tvMmgw= (TextView)conentView.findViewById(R.id.xiugai_mmgw);
	        
	        tvZghsy.setOnClickListener(this);
	        tvZghgq.setOnClickListener(this);
	        tvWsgs.setOnClickListener(this);
	        tvWsgs.setOnClickListener(this);
	        tvZgyc.setOnClickListener(this);
	        tvMxxdd.setOnClickListener(this);
	        tvZgzqy.setOnClickListener(this);
	        tvMmgw.setOnClickListener(this);
	         
	        
	}

	 
	private View conentView;  
 
	@Override
	public void onClick(View v) {

		int id = v.getId();
		switch (id) {
		case R.id.xiugai_zghsy:
			liuxingClick(tvZghsy.getText().toString(),DataBase.SONG_LIUXING_ZGHSY);
			dismiss();
			break;
		case R.id.xiugai_zghgq:
			liuxingClick(tvZghgq.getText().toString(),DataBase.SONG_LIUXING_ZGHGQ);
			dismiss();
			break;
		case R.id.xiugai_wsgs:
			liuxingClick(tvWsgs.getText().toString(),DataBase.SONG_LIUXING_WSGS);
			dismiss();
			break;
		case R.id.xiugai_zgyc:
			liuxingClick(tvZgyc.getText().toString(),DataBase.SONG_LIUXING_ZGYC);
			dismiss();
			break;
		case R.id.xiugai_zmhs:
			liuxingClick(tvZmhs.getText().toString(),DataBase.SONG_LIUXING_ZMHS);
			dismiss();
			break;
		case R.id.xiugai_mxxdd:
			liuxingClick(tvMxxdd.getText().toString(),DataBase.SONG_LIUXING_MXXDD);
			dismiss();
			break;
		case R.id.xiugai_zgzqy:
			liuxingClick(tvZgzqy.getText().toString(),DataBase.SONG_LIUXING_ZGZQY);
			dismiss();
			break;
		case R.id.xiugai_mmgw:
			liuxingClick(tvMmgw.getText().toString(),DataBase.SONG_LIUXING_MMGW);
			dismiss();
			break;
		 
		default:
			break;
		}
	
	}
	
	public void liuxingClick(String showText, String yuyan)
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
