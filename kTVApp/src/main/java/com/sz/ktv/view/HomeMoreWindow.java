package com.sz.ktv.view;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.sz.ktv.R;

public class HomeMoreWindow extends PopupWindow implements OnClickListener {

	
	ImageView tiaoyin;
	ImageView wifi;
	ImageView yuyan;
	PopuwindowClickListener listener; 
	
	public void setPopuwindowClickListener(PopuwindowClickListener mListener){
		this.listener = mListener;
	}
	public interface PopuwindowClickListener{
		public void tiaoyinSet();
		public void wifiSet();
		public void yuyanSet();
	}
	
	public HomeMoreWindow(Activity context) {
		super(context);
		
		 LayoutInflater inflater = (LayoutInflater) context  
	                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
	        conentView = inflater.inflate(R.layout.home_more_layout, null);  
	        int h = context.getWindowManager().getDefaultDisplay().getHeight();  
	        int w = context.getWindowManager().getDefaultDisplay().getWidth();  
	        // 设置SelectPicPopupWindow的View  
	        this.setContentView(conentView);  
	        // 设置SelectPicPopupWindow弹出窗体的宽  
	        this.setWidth(w /5+50);  
	        // 设置SelectPicPopupWindow弹出窗体的高  
	        this.setHeight(h/5);  //LayoutParams.WRAP_CONTENT
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
	        
	        tiaoyin = (ImageView)conentView.findViewById(R.id.more_tiaoyin);
	        tiaoyin.setBackgroundResource(R.drawable.more_tiaoyin_button);
	        
	        wifi = (ImageView)conentView.findViewById(R.id.more_wifi);
	        wifi.setBackgroundResource(R.drawable.more_wifi_button);
	        yuyan = (ImageView)conentView.findViewById(R.id.more_yuyan);
	        yuyan.setBackgroundResource(R.drawable.more_yuyan_button);
	        
	        tiaoyin.setOnClickListener(this);
	        wifi.setOnClickListener(this);
	        yuyan.setOnClickListener(this);
	}

	private View conentView;  
	
	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.more_tiaoyin:
			listener.tiaoyinSet();
			dismiss();
			break;
		case R.id.more_wifi:
			listener.wifiSet();
			dismiss();
			break;
		case R.id.more_yuyan:
			listener.yuyanSet();
			dismiss();
			break;
		default:
			break;
		}
		
	}
	 public void showPopupWindow(View parent) {  
	        if (!this.isShowing()) {  
	            this.showAtLocation(parent, Gravity.RIGHT, 120, parent.getHeight()*2+20) ;
	        } else {  
	            this.dismiss();  
	        }  
	    } 
}
