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

public class SongXGDianYingWindow extends PopupWindow implements OnClickListener {

	
	TextView tvDongzuo;
	TextView tvKehuan;
	TextView tvZhanzheng;
	TextView tvXiju;
	TextView tvDonghua;
	TextView tvGuzhuang;
	TextView tvFeijing;
	TextView tvQita;
	
	public SongXGDianYingWindow(Activity context) {
		super(context);
		
		 LayoutInflater inflater = (LayoutInflater) context  
	                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
	        conentView = inflater.inflate(R.layout.songxg_dianying_layout, null);  
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
	      
	        tvDongzuo = (TextView)conentView.findViewById(R.id.xiugai_dongzuo);
	        tvKehuan = (TextView)conentView.findViewById(R.id.xiugai_kehuan);
	        tvXiju= (TextView)conentView.findViewById(R.id.xiugai_xiju);
	        tvZhanzheng= (TextView)conentView.findViewById(R.id.xiugai_zhanzheng);
	        tvGuzhuang= (TextView)conentView.findViewById(R.id.xiugai_guzhuang);
	        tvDonghua= (TextView)conentView.findViewById(R.id.xiugai_donghua);
	        tvFeijing= (TextView)conentView.findViewById(R.id.xiugai_feijing);
	        tvQita= (TextView)conentView.findViewById(R.id.xiugai_qita);
	        
	        tvDongzuo.setOnClickListener(this);
	        tvKehuan.setOnClickListener(this);
	        tvXiju.setOnClickListener(this);
	        tvZhanzheng.setOnClickListener(this);
	        tvGuzhuang.setOnClickListener(this);
	        tvDonghua.setOnClickListener(this);
	        tvFeijing.setOnClickListener(this);
	        tvQita.setOnClickListener(this);
	         
	        
	}

	 
	private View conentView;  
 
	@Override
	public void onClick(View v) {

		int id = v.getId();
		switch (id) {
		case R.id.xiugai_dongzuo:
			dianyingClick(tvDongzuo.getText().toString(),DataBase.SONG_DIANYING_DZ);
			dismiss();
			break;
		case R.id.xiugai_kehuan:
			dianyingClick(tvKehuan.getText().toString(),DataBase.SONG_DIANYING_KH);
			dismiss();
			break;
		case R.id.xiugai_xiju:
			dianyingClick(tvXiju.getText().toString(),DataBase.SONG_DIANYING_XJ);
			dismiss();
			break;
		case R.id.xiugai_zhanzheng:
			dianyingClick(tvZhanzheng.getText().toString(),DataBase.SONG_DIANYING_ZZ);
			dismiss();
			break;
		case R.id.xiugai_guzhuang:
			dianyingClick(tvGuzhuang.getText().toString(),DataBase.SONG_DIANYING_GZ);
			dismiss();
			break;
		case R.id.xiugai_donghua:
			dianyingClick(tvDonghua.getText().toString(),DataBase.SONG_DIANYING_DH);
			dismiss();
			break;
		case R.id.xiugai_feijing:
			dianyingClick(tvFeijing.getText().toString(),DataBase.SONG_DIANYING_FJ);
			dismiss();
			break;
		case R.id.xiugai_qita:
			dianyingClick(tvQita.getText().toString(),DataBase.SONG_DIANYING_QT);
			dismiss();
			break;
		 
		default:
			break;
		}
	
	}
	
	public void dianyingClick(String showText,String yuyan)
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
