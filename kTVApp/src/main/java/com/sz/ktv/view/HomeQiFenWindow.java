package com.sz.ktv.view;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.sz.ktv.R;

public class HomeQiFenWindow extends PopupWindow implements OnClickListener {

	
	 
	private RelativeLayout qiFenZhangshen;
	private RelativeLayout qiFenKoushao;
	private RelativeLayout qiFenXianhua;
	private RelativeLayout qiFenHuanhu;
	private RelativeLayout qiFenZhufuyu;
	private RelativeLayout qiFenGaoguai;
	
	PopuwindowQiFenClickListener listener; 
	
	public void setPopuwindowQiFenClickListener(PopuwindowQiFenClickListener mListener){
		this.listener = mListener;
	}
	public interface PopuwindowQiFenClickListener{
		public void qiFenZhangShengClick();
		public void qiFenKouShaoClick();
		public void qiFenXianHuaClick();
		public void qiFenHuanHuClick();
		public void qiFenZhuFuYuClick();
		public void qiFenGaoGuaiClick();
		
	}
	
	public HomeQiFenWindow(Activity context) {
		super(context);
		
		 LayoutInflater inflater = (LayoutInflater) context  
	                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
	        conentView = inflater.inflate(R.layout.home_qifen_layout, null);  
	        int h = context.getWindowManager().getDefaultDisplay().getHeight();  
	        int w = context.getWindowManager().getDefaultDisplay().getWidth();  
	        // 设置SelectPicPopupWindow的View  
	        this.setContentView(conentView);  
	        // 设置SelectPicPopupWindow弹出窗体的宽  
	        this.setWidth(w /5+100);  
	        // 设置SelectPicPopupWindow弹出窗体的高  
	        this.setHeight(h/5+60);  //LayoutParams.WRAP_CONTENT
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
	        qiFenZhangshen = (RelativeLayout)conentView.findViewById(R.id.qifen_zhangsheng);
	        qiFenKoushao = (RelativeLayout)conentView.findViewById(R.id.qifen_koushao);
	        qiFenXianhua = (RelativeLayout)conentView.findViewById(R.id.qifen_xianhua);
	        qiFenHuanhu = (RelativeLayout)conentView.findViewById(R.id.qifen_huanhu);
	        qiFenZhufuyu = (RelativeLayout)conentView.findViewById(R.id.qifen_zhufuyu);
	        qiFenGaoguai= (RelativeLayout)conentView.findViewById(R.id.qifen_gaoguai);
	        
	        qiFenZhangshen.setOnClickListener(this);
	        qiFenKoushao.setOnClickListener(this);
	        qiFenXianhua.setOnClickListener(this);
	        qiFenHuanhu.setOnClickListener(this);
	        qiFenZhufuyu.setOnClickListener(this);
	        qiFenGaoguai.setOnClickListener(this);
	        
	}

	private View conentView;  
	
	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.qifen_zhangsheng:
			listener.qiFenZhangShengClick();
			break;
		case R.id.qifen_koushao:
			listener.qiFenKouShaoClick();
			break;
		case R.id.qifen_xianhua:
			listener.qiFenXianHuaClick();
			break;
		case R.id.qifen_huanhu:
			listener.qiFenHuanHuClick();
			break;
		case R.id.qifen_zhufuyu:
			listener.qiFenZhuFuYuClick();
			break;
		case R.id.qifen_gaoguai:
			listener.qiFenGaoGuaiClick();
			break;
		default:
			break;
		}
		
	}
	 public void showPopupWindow(View parent) {  
	        if (!this.isShowing()) {  
	            this.showAtLocation(parent, Gravity.RIGHT, 160, parent.getHeight()*2) ;
	        } else {  
	            this.dismiss();  
	        }  
	    } 
}
