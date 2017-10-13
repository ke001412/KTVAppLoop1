package com.sz.ktv.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.zxing.WriterException;
import com.sz.ktv.R;
import com.sz.ktv.util.Global;
import com.sz.ktv.util.LanguageUtil;
import com.sz.ktv.util.code.QRCodeUtil;

public class HomeWifiDianGeDialog extends Dialog implements OnClickListener {

	 
	
	ImageView qrCodeImg;
	ImageView qrCodeIphoneImg;
	ImageView qrCodeAndroidImg;
	
	TextView qrCodeText;
	
	public HomeWifiDianGeDialog(Context context) {
		super(context, R.style.loaddialog);
	}

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_wifi_diange_layout);
		layoutView();
	}

	private void layoutView() {

		qrCodeImg = (ImageView)  findViewById(R.id.wifi_qrcode_img);
		qrCodeAndroidImg = (ImageView)  findViewById(R.id.wifi_qrcode_android_img);
		qrCodeIphoneImg = (ImageView)  findViewById(R.id.wifi_qrcode_iphone_img);
		
		qrCodeText = (TextView)findViewById(R.id.wifi_qrcode);
		qrCodeText.setText(Global.qrCode);
		
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

	@Override
	public void onClick(View v) { }

	 
	@Override
	public void dismiss() {
		super.dismiss();
	}
	
	 
}
