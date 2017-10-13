package com.sz.ktv.view.dialog;

import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;

import com.sz.ktv.R;
import com.sz.ktv.util.FragmentFactory;
import com.sz.ktv.util.Global;
import com.sz.ktv.util.LanguageUtil;

public class LanguageSetDialog extends Dialog implements OnClickListener {

	 
	
	RelativeLayout yuyanJianti;
	RelativeLayout yuyanFanti;
	RelativeLayout yuyanEnglish;
	
	
PopuwindowYuYanClickListener listener; 
	
	public void setPopuwindowYuYanClickListener(PopuwindowYuYanClickListener mListener){
		this.listener = mListener;
	}
	public interface PopuwindowYuYanClickListener{
		public void yuyanJianTiClick();
		public void yuyanFanTiClick();
		public void yuyanEnglishClick();
 
		
	}
	
	public LanguageSetDialog(Context context) {
		super(context, R.style.loaddialog);
	}

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.more_yuyan_layout);
		layoutView();
	}

	private void layoutView() {

		yuyanJianti = (RelativeLayout)  findViewById(R.id.yuyan_jianti);
		yuyanFanti = (RelativeLayout)  findViewById(R.id.yuyan_fanti);
		yuyanEnglish = (RelativeLayout)  findViewById(R.id.yuyan_english);
		 
		yuyanJianti.setOnClickListener(this);
		yuyanFanti.setOnClickListener(this);
		yuyanEnglish.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {

		String currentYuYan = LanguageUtil.getCurrentSetLanguage(Global.currentActivity);
		int id = v.getId();
		switch (id) {
		case R.id.yuyan_jianti:
			if(currentYuYan.equals(LanguageUtil.CHINESE))
			{
				return ;
			}
			listener.yuyanJianTiClick();
			dismiss();
			FragmentFactory.mFragments.clear();
			LanguageUtil.restart();
			break;
		case R.id.yuyan_fanti:
			if(currentYuYan.equals(LanguageUtil.CHINSES_FANTI))
			{
				return ;
			}
			listener.yuyanFanTiClick();
			dismiss();
			FragmentFactory.mFragments.clear();
			LanguageUtil.restart();
			break;
		case R.id.yuyan_english:
			if(currentYuYan.equals(LanguageUtil.ENGLISH))
			{
				return ;
			}
			listener.yuyanEnglishClick();
			dismiss();
			FragmentFactory.mFragments.clear();
			LanguageUtil.restart();
			break;
		default:
			break;
		}

	
		 
	}

	 
	@Override
	public void dismiss() {
		super.dismiss();
	}
	
	 
}
