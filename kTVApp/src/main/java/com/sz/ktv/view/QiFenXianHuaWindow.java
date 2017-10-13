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

public class QiFenXianHuaWindow extends PopupWindow implements OnClickListener {

	 
	
	PopuwindowFlowerClickListener listener; 
	private ImageView flower1;
	private ImageView flower2;
	private ImageView flower3;
	private ImageView flower4;
 
	
	public void setPopuwindowflowerClickListener(PopuwindowFlowerClickListener mListener){
		this.listener = mListener;
	}
	public interface PopuwindowFlowerClickListener{
		public void flower1();
		public void flower2();
		public void flower3();
		public void flower4();
	 
		
	}
	
	public QiFenXianHuaWindow(Activity context) {
		super(context);
		
		 LayoutInflater inflater = (LayoutInflater) context  
	                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
	        conentView = inflater.inflate(R.layout.qifen_xianhua_layout, null);  
	        int h = context.getWindowManager().getDefaultDisplay().getHeight();  
	        int w = context.getWindowManager().getDefaultDisplay().getWidth();  
	        // 设置SelectPicPopupWindow的View  
	        this.setContentView(conentView);  
	        // 设置SelectPicPopupWindow弹出窗体的宽  
	        this.setWidth(w /5+80);  
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
	        flower1 = (ImageView)conentView.findViewById(R.id.flower_1);
	        flower2 = (ImageView)conentView.findViewById(R.id.flower_2);
	        flower3 = (ImageView)conentView.findViewById(R.id.flower_3);
	        flower4 = (ImageView)conentView.findViewById(R.id.flower_4);
	     
	        
	        
	        flower1.setOnClickListener(this);
	        flower2.setOnClickListener(this);
	        flower3.setOnClickListener(this);
	        flower4.setOnClickListener(this);
	       
	        
	}

	private View conentView;  
	
	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.flower_1:
			listener.flower1();
			break;
		case R.id.flower_2:
			listener.flower2();
			break;
		case R.id.flower_3:
			listener.flower3();
			break;
		case R.id.flower_4:
			listener.flower4();
			break;
		 
		default:
			break;
		}
		
	}
	 public void showPopupWindow(View parent) {  
	        if (!this.isShowing()) {  
	            this.showAtLocation(parent, Gravity.RIGHT, 130, parent.getHeight()*2) ;
	        } else {  
	            this.dismiss();  
	        }  
	    } 
}
