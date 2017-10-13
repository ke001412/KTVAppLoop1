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

public class QiFenGaoGuaiWindow extends PopupWindow implements OnClickListener {

	 
	
	PopuwindowGaoGuaiClickListener listener; 
	private ImageView gaoguai1;
	private ImageView gaoguai2;
	private ImageView gaoguai3;
	private ImageView gaoguai4;
	private ImageView gaoguai5;
	private ImageView gaoguai6;
	
	private ImageView gaoguai7;
	private ImageView gaoguai8;
	private ImageView gaoguai9;
	private ImageView gaoguai10;
	private ImageView gaoguai11;
	private ImageView gaoguai12;
	
	public void setPopuwindowGaoGuaiClickListener(PopuwindowGaoGuaiClickListener mListener){
		this.listener = mListener;
	}
	public interface PopuwindowGaoGuaiClickListener{
		public void gaoguai1();
		public void gaoguai2();
		public void gaoguai3();
		public void gaoguai4();
		public void gaoguai5();
		public void gaoguai6();
		public void gaoguai7();
		public void gaoguai8();
		public void gaoguai9();
		public void gaoguai10();
		public void gaoguai11();
		public void gaoguai12();
		
	}
	
	public QiFenGaoGuaiWindow(Activity context) {
		super(context);
		
		 LayoutInflater inflater = (LayoutInflater) context  
	                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
	        conentView = inflater.inflate(R.layout.qifen_gaoguai_layout, null);  
	        int h = context.getWindowManager().getDefaultDisplay().getHeight();  
	        int w = context.getWindowManager().getDefaultDisplay().getWidth();  
	        // 设置SelectPicPopupWindow的View  
	        this.setContentView(conentView);  
	        // 设置SelectPicPopupWindow弹出窗体的宽  
	        this.setWidth(w /5+140);  
	        // 设置SelectPicPopupWindow弹出窗体的高  
	        this.setHeight(h/5+50);  //LayoutParams.WRAP_CONTENT
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
	        gaoguai1 = (ImageView)conentView.findViewById(R.id.gaoguai_1);
	        gaoguai2 = (ImageView)conentView.findViewById(R.id.gaoguai_2);
	        gaoguai3 = (ImageView)conentView.findViewById(R.id.gaoguai_3);
	        gaoguai4 = (ImageView)conentView.findViewById(R.id.gaoguai_4);
	        gaoguai5 = (ImageView)conentView.findViewById(R.id.gaoguai_5);
	        gaoguai6= (ImageView)conentView.findViewById(R.id.gaoguai_6);
	        
	        gaoguai7 = (ImageView)conentView.findViewById(R.id.gaoguai_7);
	        gaoguai8 = (ImageView)conentView.findViewById(R.id.gaoguai_8);
	        gaoguai9 = (ImageView)conentView.findViewById(R.id.gaoguai_9);
	        gaoguai10 = (ImageView)conentView.findViewById(R.id.gaoguai_10);
	        gaoguai11 = (ImageView)conentView.findViewById(R.id.gaoguai_11);
	        gaoguai12= (ImageView)conentView.findViewById(R.id.gaoguai_12);
	        
	        
	        gaoguai1.setOnClickListener(this);
	        gaoguai2.setOnClickListener(this);
	        gaoguai3.setOnClickListener(this);
	        gaoguai4.setOnClickListener(this);
	        gaoguai5.setOnClickListener(this);
	        gaoguai6.setOnClickListener(this);
	        
	        gaoguai7.setOnClickListener(this);
	        gaoguai8.setOnClickListener(this);
	        gaoguai9.setOnClickListener(this);
	        gaoguai10.setOnClickListener(this);
	        gaoguai11.setOnClickListener(this);
	        gaoguai12.setOnClickListener(this);
	        
	}

	private View conentView;  
	
	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.gaoguai_1:
			listener.gaoguai1();
			break;
		case R.id.gaoguai_2:
			listener.gaoguai2();
			break;
		case R.id.gaoguai_3:
			listener.gaoguai3();
			break;
		case R.id.gaoguai_4:
			listener.gaoguai4();
			break;
		case R.id.gaoguai_5:
			listener.gaoguai5();
			break;
		case R.id.gaoguai_6:
			listener.gaoguai6();
			break;
		case R.id.gaoguai_7:
			listener.gaoguai7();
			break;
		case R.id.gaoguai_8:
			listener.gaoguai8();
			break;
		case R.id.gaoguai_9:
			listener.gaoguai9();
			break;
		case R.id.gaoguai_10:
			listener.gaoguai10();
			break;
		case R.id.gaoguai_11:
			listener.gaoguai11();
			break;
		case R.id.gaoguai_12:
			listener.gaoguai12();
			break;
			
		default:
			break;
		}
		
	}
	 public void showPopupWindow(View parent) {  
	        if (!this.isShowing()) {  
	            this.showAtLocation(parent, Gravity.RIGHT, 140, parent.getHeight()*2) ;
	        } else {  
	            this.dismiss();  
	        }  
	    } 
}
