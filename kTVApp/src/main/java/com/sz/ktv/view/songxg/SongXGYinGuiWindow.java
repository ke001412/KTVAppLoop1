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

public class SongXGYinGuiWindow extends PopupWindow implements OnClickListener {

	
	TextView tvYinguizuo;
	TextView tvYinguiyou;
	TextView tvYinguiyi;
	TextView tvYinguier;
	TextView tvYinguisan;
	 
	
	 
	PopuwindowYuYanXGClickListener listener; 
	
	public void setPopuwindowYuYanClickListener(PopuwindowYuYanXGClickListener mListener){
		this.listener = mListener;
	}
	public interface PopuwindowYuYanXGClickListener{
		
		public void yuyanClick(int yuyan);
		
	}
	
	
	public SongXGYinGuiWindow(Activity context) {
		super(context);
		
		 LayoutInflater inflater = (LayoutInflater) context  
	                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
	        conentView = inflater.inflate(R.layout.songxg_yingui_layout, null);  
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
	      
	        tvYinguizuo = (TextView)conentView.findViewById(R.id.xiugai_zuo);
	        tvYinguiyou = (TextView)conentView.findViewById(R.id.xiugai_you);
	        tvYinguiyi = (TextView)conentView.findViewById(R.id.xiugai_yingui1);
	        tvYinguier = (TextView)conentView.findViewById(R.id.xiugai_yingui2);
	        tvYinguisan = (TextView)conentView.findViewById(R.id.xiugai_yingui3);
	         
	        
	        tvYinguizuo.setOnClickListener(this);
	        tvYinguiyou.setOnClickListener(this);
	        tvYinguiyi.setOnClickListener(this);
	        tvYinguier.setOnClickListener(this);
	        tvYinguisan.setOnClickListener(this);
	        
	        
	}

	 
	private View conentView;  
 
	@Override
	public void onClick(View v) {

		int id = v.getId();
		switch (id) {
		case R.id.xiugai_zuo:
			yinguiClick(tvYinguizuo.getText().toString(),4);
			dismiss();
			break;
		case R.id.xiugai_you:
			yinguiClick(tvYinguiyou.getText().toString(),5);
			dismiss();
			break;
		case R.id.xiugai_yingui1:
			yinguiClick(tvYinguiyi.getText().toString(),1);
			dismiss();
			break;
		case R.id.xiugai_yingui2:
			yinguiClick(tvYinguier.getText().toString(),2);
			dismiss();
			break;
		case R.id.xiugai_yingui3:
			yinguiClick(tvYinguisan.getText().toString(),3);
			dismiss();
			break;
		 
		default:
			break;
		}
	
	}
	
	public void yinguiClick(String showText, int yuyan)
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
