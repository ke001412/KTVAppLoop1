package com.sz.ktv.view;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sz.ktv.R;
import com.sz.ktv.view.HomeQiFenWindow.PopuwindowQiFenClickListener;

public class HomeCloudyWindow extends PopupWindow implements OnClickListener {

	
	RelativeLayout yunGequ;
	RelativeLayout yunXizai;
	RelativeLayout yunGengxin;
	 
	PopuwindowCloudyClickListener listener; 
	
	public void setPopuwindowQiFenClickListener(PopuwindowCloudyClickListener mListener){
		this.listener = mListener;
	}
	public interface PopuwindowCloudyClickListener{
		public void yunGequ();
		public void yunXizai();
		public void yunGengxin();
 
		
	}
	
	
	public HomeCloudyWindow(Activity context) {
		super(context);
		
		 LayoutInflater inflater = (LayoutInflater) context  
	                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
	        conentView = inflater.inflate(R.layout.home_cloudy_layout, null);  
	        int h = context.getWindowManager().getDefaultDisplay().getHeight();  
	        int w = context.getWindowManager().getDefaultDisplay().getWidth();  
	        // 设置SelectPicPopupWindow的View  
	        this.setContentView(conentView);  
	        // 设置SelectPicPopupWindow弹出窗体的宽  
	        this.setWidth(w /4);  
	        // 设置SelectPicPopupWindow弹出窗体的高  
	        this.setHeight(h/4);  //LayoutParams.WRAP_CONTENT
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
	        
	        yunGequ = (RelativeLayout)conentView.findViewById(R.id.yun_gequ);
	        yunXizai = (RelativeLayout)conentView.findViewById(R.id.yun_xiazai);
	        yunGengxin = (RelativeLayout)conentView.findViewById(R.id.yun_gengxin);
	        
	        yunGequ.setOnClickListener(this);
	        yunXizai.setOnClickListener(this);
	        yunGengxin.setOnClickListener(this);
	}

	 
	private View conentView;  
	
	@Override
	public void onClick(View v) {

		int id = v.getId();
		switch (id) {
		case R.id.yun_gequ:
			listener.yunGequ();
			dismiss();
			break;
		case R.id.yun_xiazai:
			listener.yunXizai();
			dismiss();
			break;
		case R.id.yun_gengxin:
			listener.yunGengxin();
			dismiss();
			break;
		default:
			break;
		}
		
	
	}
	 public void showPopupWindow(View parent) {  
	        if (!this.isShowing()) {  
	            this.showAtLocation(parent, Gravity.TOP, 60, parent.getHeight()) ;
	        } else {  
	            this.dismiss();  
	        }  
	    } 
}
