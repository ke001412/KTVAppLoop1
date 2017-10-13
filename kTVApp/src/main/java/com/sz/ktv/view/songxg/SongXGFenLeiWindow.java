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

public class SongXGFenLeiWindow extends PopupWindow implements OnClickListener {

	
	TextView tvHechang;
	TextView tvShengri;
	TextView tvDianying;
	TextView tvXiangsheng;
	TextView tvGeming;
	TextView tvHuaijiu;
	TextView tvXiqing;
	TextView tvXiqu;
	TextView tvErtong;
	
	public SongXGFenLeiWindow(Activity context) {
		super(context);
		
		 LayoutInflater inflater = (LayoutInflater) context  
	                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
	        conentView = inflater.inflate(R.layout.songxg_fenlei_layout, null);  
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
	      
	        tvHechang = (TextView)conentView.findViewById(R.id.xiugai_hechang);
	        tvShengri = (TextView)conentView.findViewById(R.id.xiugai_shengri);
	        tvDianying= (TextView)conentView.findViewById(R.id.xiugai_dianying);
	        tvXiangsheng= (TextView)conentView.findViewById(R.id.xiugai_xiangsheng);
	        tvGeming= (TextView)conentView.findViewById(R.id.xiugai_geming);
	        tvHuaijiu= (TextView)conentView.findViewById(R.id.xiugai_huaijiu);
	        tvXiqing= (TextView)conentView.findViewById(R.id.xiugai_xiqing);
	        tvXiqu= (TextView)conentView.findViewById(R.id.xiugai_xiqu);
	        tvErtong = (TextView)conentView.findViewById(R.id.xiugai_ertong);
	        
	        tvHechang.setOnClickListener(this);
	        tvShengri.setOnClickListener(this);
	        tvDianying.setOnClickListener(this);
	        tvXiangsheng.setOnClickListener(this);
	        tvXiangsheng.setOnClickListener(this);
	        tvHuaijiu.setOnClickListener(this);
	        tvXiqing.setOnClickListener(this);
	        tvXiqu.setOnClickListener(this);
	        tvErtong.setOnClickListener(this);
	        tvGeming.setOnClickListener(this);
	        
	}

	 
	private View conentView;  
 
	@Override
	public void onClick(View v) {

		int id = v.getId();
		switch (id) {
		case R.id.xiugai_hechang:
			fenleiClick(tvHechang.getText().toString(),DataBase.SONG_TYPE_HECHANGE);
			dismiss();
			break;
		case R.id.xiugai_shengri:
			fenleiClick(tvShengri.getText().toString(),DataBase.SONG_TYPE_SHENGRI);
			dismiss();
			break;
		case R.id.xiugai_dianying:
			fenleiClick(tvDianying.getText().toString(),DataBase.SONG_TYPE_DIANYING);
			dismiss();
			break;
		case R.id.xiugai_xiangsheng:
			fenleiClick(tvXiangsheng.getText().toString(),DataBase.SONG_TYPE_XIANGSHENG);
			dismiss();
			break;
		case R.id.xiugai_geming:
			fenleiClick(tvGeming.getText().toString(),DataBase.SONG_TYPE_GEMING);
			dismiss();
			break;
		case R.id.xiugai_huaijiu:
			fenleiClick(tvHuaijiu.getText().toString(),DataBase.SONG_TYPE_HUAIJIU);
			dismiss();
			break;
		case R.id.xiugai_xiqing:
			fenleiClick(tvXiqing.getText().toString(),DataBase.SONG_TYPE_XIQING);
			dismiss();
			break;
		case R.id.xiugai_xiqu:
			fenleiClick(tvXiqu.getText().toString(),DataBase.SONG_TYPE_XIQU);
			dismiss();
			break;
		case R.id.xiugai_ertong:
			fenleiClick(tvErtong.getText().toString(),DataBase.SONG_TYPE_ERTONG);
			dismiss();
			break;
		 
		default:
			break;
		}
	
	}
	
	public void fenleiClick(String showText,String yuyan)
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
