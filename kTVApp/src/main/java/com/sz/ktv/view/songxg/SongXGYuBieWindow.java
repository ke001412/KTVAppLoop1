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

public class SongXGYuBieWindow extends PopupWindow implements OnClickListener {

	
	TextView tvGuoyu;
	TextView tvRiyu;
	TextView tvYueyu;
	TextView tvHanyu;
	TextView tvMinnanyu;
	TextView tvQita;
	TextView tvYingyu;
	
	 
	PopuwindowYuYanXGClickListener listener; 
	
	public void setPopuwindowYuYanClickListener(PopuwindowYuYanXGClickListener mListener){
		this.listener = mListener;
	}
	public interface PopuwindowYuYanXGClickListener{
		
		public void yuyanClick(int yuyan);
		
	}
	
	
	public SongXGYuBieWindow(Activity context) {
		super(context);
		
		 LayoutInflater inflater = (LayoutInflater) context  
	                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
	        conentView = inflater.inflate(R.layout.songxg_yubie_layout, null);  
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
	      
	        tvGuoyu = (TextView)conentView.findViewById(R.id.xiugai_guoyu);
	        tvRiyu = (TextView)conentView.findViewById(R.id.xiugai_riyu);
	        tvYueyu = (TextView)conentView.findViewById(R.id.xiugai_yueyu);
	        tvHanyu = (TextView)conentView.findViewById(R.id.xiugai_hanyu);
	        tvMinnanyu = (TextView)conentView.findViewById(R.id.xiugai_minnanyu);
	        tvQita = (TextView)conentView.findViewById(R.id.xiugai_qita);
	        tvYingyu = (TextView)conentView.findViewById(R.id.xiugai_yingyu);
	        
	        tvGuoyu.setOnClickListener(this);
	        tvRiyu.setOnClickListener(this);
	        tvYueyu.setOnClickListener(this);
	        tvHanyu.setOnClickListener(this);
	        tvMinnanyu.setOnClickListener(this);
	        tvQita.setOnClickListener(this);
	        tvYingyu.setOnClickListener(this);
	        
	}

	 
	private View conentView;  
 
	@Override
	public void onClick(View v) {

		int id = v.getId();
		switch (id) {
		case R.id.xiugai_guoyu:
			 yuyanClick(tvGuoyu.getText().toString(),DataBase.SONG_TYPE_GUOYU);
			dismiss();
			break;
		case R.id.xiugai_riyu:
			yuyanClick(tvRiyu.getText().toString(),DataBase.SONG_TYPE_RIYU);
			dismiss();
			break;
		case R.id.xiugai_yueyu:
			yuyanClick(tvYueyu.getText().toString(),DataBase.SONG_TYPE_YUEYU);
			dismiss();
			break;
		case R.id.xiugai_hanyu:
			yuyanClick(tvHanyu.getText().toString(),DataBase.SONG_TYPE_HANYU);
			dismiss();
			break;
		case R.id.xiugai_minnanyu:
			yuyanClick(tvMinnanyu.getText().toString(),DataBase.SONG_TYPE_MINNANYU);
			dismiss();
			break;
		case R.id.xiugai_qita:
			yuyanClick(tvQita.getText().toString(),DataBase.SONG_TYPE_QITA);
			dismiss();
			break;
		case R.id.xiugai_yingyu:
			yuyanClick(tvYingyu.getText().toString(),DataBase.SONG_TYPE_YINGYU);
			dismiss();
			break;
			
		default:
			break;
		}
	
	}
	
	public void yuyanClick(String showText, String yuyan)
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
