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

public class SongXGZiShuWindow extends PopupWindow implements OnClickListener {

	
	TextView tvYizi;
	TextView tvErzi;
	TextView tvSanzi;
	TextView tvSizi;
	TextView tvWuzi;
	TextView tvLiuzi;
	TextView tvQizi;
	TextView tvBazi;
	TextView tvDuozi;
	 
	
	public SongXGZiShuWindow(Activity context) {
		super(context);
		
		 LayoutInflater inflater = (LayoutInflater) context  
	                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
	        conentView = inflater.inflate(R.layout.songxg_zishu_layout, null);  
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
	      
	        tvYizi = (TextView)conentView.findViewById(R.id.xiugai_yizi);
	        tvErzi = (TextView)conentView.findViewById(R.id.xiugai_erzi);
	        tvSanzi = (TextView)conentView.findViewById(R.id.xiugai_sanzi);
	        tvSizi = (TextView)conentView.findViewById(R.id.xiugai_sizi);
	        tvWuzi = (TextView)conentView.findViewById(R.id.xiugai_wuzi);
	        tvLiuzi = (TextView)conentView.findViewById(R.id.xiugai_liuzi);
	        tvQizi = (TextView)conentView.findViewById(R.id.xiugai_qizi);
	        tvBazi = (TextView)conentView.findViewById(R.id.xiugai_bazi);
	        tvDuozi = (TextView)conentView.findViewById(R.id.xiugai_duozi);
	        
	        tvYizi.setOnClickListener(this);
	        tvErzi.setOnClickListener(this);
	        tvSanzi.setOnClickListener(this);
	        tvSizi.setOnClickListener(this);
	        tvWuzi.setOnClickListener(this);
	        tvWuzi.setOnClickListener(this);
	        tvQizi.setOnClickListener(this);
	        tvBazi.setOnClickListener(this);
	        tvDuozi.setOnClickListener(this);
	        
	}

	 
	private View conentView;  
 
	@Override
	public void onClick(View v) {

		int id = v.getId();
		switch (id) {
		case R.id.xiugai_yizi:
			zishuClick(tvYizi.getText().toString(),1);
			dismiss();
			break;
		case R.id.xiugai_erzi:
			zishuClick(tvErzi.getText().toString(),2);
			dismiss();
			break;
		case R.id.xiugai_sanzi:
			zishuClick(tvSanzi.getText().toString(),3);
			dismiss();
			break;
		case R.id.xiugai_sizi:
			zishuClick(tvSizi.getText().toString(),4);
			dismiss();
			break;
		case R.id.xiugai_wuzi:
			zishuClick(tvWuzi.getText().toString(),5);
			dismiss();
			break;
		case R.id.xiugai_liuzi:
			zishuClick(tvLiuzi.getText().toString(),6);
			dismiss();
			break;
		case R.id.xiugai_qizi:
			zishuClick(tvQizi.getText().toString(),7);
			dismiss();
			break;
		case R.id.xiugai_bazi:
			zishuClick(tvBazi.getText().toString(),8);
			dismiss();
			break;
		case R.id.xiugai_duozi:
			zishuClick(tvDuozi.getText().toString(),9);
			dismiss();
			break;
		default:
			break;
		}
	
	}
	
	public void zishuClick(String showText,int yuyan)
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
