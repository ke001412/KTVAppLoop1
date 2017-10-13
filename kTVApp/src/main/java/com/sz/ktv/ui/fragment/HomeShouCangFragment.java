package com.sz.ktv.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.sz.ktv.R;
import com.sz.ktv.ui.base.BaseFragment;
import com.sz.ktv.view.widget.KtvBottomPageWidget;
import com.sz.ktv.view.widget.KtvBottomPageWidget.BackClickListener;

public class HomeShouCangFragment extends BaseFragment implements BackClickListener,
		OnClickListener {

	KtvBottomPageWidget bottomPage ;
	
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = null;
		try {
			view = inflater.inflate(R.layout.system_set_layout, container, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		initView(view);
		return view;
	}
	
	private void initView(View view) {
		bottomPage = (KtvBottomPageWidget)view.findViewById(R.id.sys_back);
		bottomPage.setBackClickListener(this);
		bottomPage.setType(KtvBottomPageWidget.TYPE_NO_DOTS_NO_PAGENUM);
	}
	@Override
	public void onClick(View v) {

	}

	@Override
	public void back() {
		backToHome();
	}

}
