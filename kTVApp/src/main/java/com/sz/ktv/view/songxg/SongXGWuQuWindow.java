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

public class SongXGWuQuWindow extends PopupWindow implements OnClickListener {

	
	TextView tvManyao;
	TextView tvQiaqia;
	TextView tvHuaerzi;
	TextView tvPulushi;
	TextView tvDishigao;
	TextView tvJiteba;
	TextView tvChuanshao;
	TextView tvTange;
	
	public SongXGWuQuWindow(Activity context) {
		super(context);
		
		 LayoutInflater inflater = (LayoutInflater) context  
	                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
	        conentView = inflater.inflate(R.layout.songxg_wuqu_layout, null);  
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
	      
	        tvManyao = (TextView)conentView.findViewById(R.id.xiugai_manyao);
	        tvQiaqia = (TextView)conentView.findViewById(R.id.xiugai_qiaqia);
	        tvHuaerzi= (TextView)conentView.findViewById(R.id.xiugai_huaerzi);
	        tvPulushi= (TextView)conentView.findViewById(R.id.xiugai_pulushi);
	        tvDishigao= (TextView)conentView.findViewById(R.id.xiugai_dishigao);
	        tvJiteba= (TextView)conentView.findViewById(R.id.xiugai_jiteba);
	        tvChuanshao= (TextView)conentView.findViewById(R.id.xiugai_chuanshao);
	        tvTange= (TextView)conentView.findViewById(R.id.xiugai_tange);
	        
	        tvManyao.setOnClickListener(this);
	        tvQiaqia.setOnClickListener(this);
	        tvHuaerzi.setOnClickListener(this);
	        tvPulushi.setOnClickListener(this);
	        tvDishigao.setOnClickListener(this);
	        tvJiteba.setOnClickListener(this);
	        tvChuanshao.setOnClickListener(this);
	        tvTange.setOnClickListener(this);
	         
	        
	}

	 
	private View conentView;  
 
	@Override
	public void onClick(View v) {

		int id = v.getId();
		switch (id) {
		case R.id.xiugai_qiaqia:
			wuquClick(tvQiaqia.getText().toString(),DataBase.SONG_WUQU_QIAQIA);
			dismiss();
			break;
		case R.id.xiugai_manyao:
			wuquClick(tvManyao.getText().toString(),DataBase.SONG_WUQU_MANYAO);
			dismiss();
			break;
		case R.id.xiugai_qita:
//			wuquClick(tvQiaqia.getText().toString(),DataBase.SONG_WUQU_QIAQIA);
//			dismiss();
			break;
		case R.id.xiugai_huaerzi:
			wuquClick(tvHuaerzi.getText().toString(),DataBase.SONG_WUQU_HUAERZI);
			dismiss();
			break;
		case R.id.xiugai_pulushi:
			wuquClick(tvPulushi.getText().toString(),DataBase.SONG_WUQU_PULUSHI);
			dismiss();
			break;
		case R.id.xiugai_dishigao:
			wuquClick(tvDishigao.getText().toString(),DataBase.SONG_WUQU_PULUSHI);
			dismiss();
			break;
		case R.id.xiugai_jiteba:
			wuquClick(tvJiteba.getText().toString(),DataBase.SONG_WUQU_JITEBA);
			dismiss();
			break;
		case R.id.xiugai_chuanshao:
			wuquClick(tvChuanshao.getText().toString(),DataBase.SONG_WUQU_CHUANGSHAO);
			dismiss();
			break;
		case R.id.xiugai_tange:
			wuquClick(tvTange.getText().toString(),DataBase.SONG_WUQU_TANGE);
			dismiss();
			break;
		 
		default:
			break;
		}
	
	}
	
	public void wuquClick(String showText,String yuyan)
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
