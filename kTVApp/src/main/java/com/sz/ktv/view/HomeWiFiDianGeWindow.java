package com.sz.ktv.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.zxing.WriterException;
import com.sz.ktv.R;
import com.sz.ktv.util.Global;
import com.sz.ktv.util.code.QRCodeUtil;

public class HomeWiFiDianGeWindow extends PopupWindow implements OnClickListener {

	

	ImageView qrCodeImg;
	ImageView qrCodeIphoneImg;
	ImageView qrCodeAndroidImg;
	
	TextView qrCodeText;
	 
 
	
	public HomeWiFiDianGeWindow(Activity context) {
		super(context);
		
		 LayoutInflater inflater = (LayoutInflater) context  
	                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
	        conentView = inflater.inflate(R.layout.home_wifi_diange_layout, null);  
	        int h = context.getWindowManager().getDefaultDisplay().getHeight();  
	        int w = context.getWindowManager().getDefaultDisplay().getWidth();  
	        // 设置SelectPicPopupWindow的View  
	        this.setContentView(conentView);  
	        // 设置SelectPicPopupWindow弹出窗体的宽  
	        this.setWidth(w/2+w/4);  
	        // 设置SelectPicPopupWindow弹出窗体的高  
	        this.setHeight(h/2+h/4);  //LayoutParams.WRAP_CONTENT
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
	        
	        layoutView();
	        
	}

	private void layoutView() {

		qrCodeImg = (ImageView)  conentView.findViewById(R.id.wifi_qrcode_img);
		qrCodeAndroidImg = (ImageView)  conentView.findViewById(R.id.wifi_qrcode_android_img);
		qrCodeIphoneImg = (ImageView)  conentView.findViewById(R.id.wifi_qrcode_iphone_img);
		
		qrCodeText = (TextView)conentView.findViewById(R.id.wifi_qrcode);
		qrCodeText.setText("验证码:"+Global.qrCode);
		
		Drawable drawable;
		try {
			drawable = QRCodeUtil.createQRCode(Global.qrCode,90);
			qrCodeImg.setImageDrawable(drawable);
			drawable = QRCodeUtil.createQRCode(Global.QRCODE_IPHONE_DOWN_URL,110);
			qrCodeIphoneImg.setImageDrawable(drawable);
			drawable = QRCodeUtil.createQRCode(Global.QRCODE_ANDROID_DOWN_URL,110);
			qrCodeAndroidImg.setImageDrawable(drawable);
			
		} catch (WriterException e) {
			e.printStackTrace();
		}
	
		
	}
	private View conentView;  
	
	@Override
	public void onClick(View v) {
		 
	}
	 public void showPopupWindow(View parent) {  
	        if (!this.isShowing()) {  
	            this.showAtLocation(parent, Gravity.TOP, 60, parent.getHeight()) ;
	        } else {  
	            this.dismiss();  
	        }  
	    } 
}
