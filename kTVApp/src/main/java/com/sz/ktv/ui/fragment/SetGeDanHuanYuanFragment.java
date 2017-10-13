package com.sz.ktv.ui.fragment;

import org.greenrobot.eventbus.EventBus;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.sz.ktv.R;
import com.sz.ktv.msg.AsyncMessage;
import com.sz.ktv.ui.base.BaseFragment;
import com.sz.ktv.util.FragmentFactory;
import com.sz.ktv.view.dialog.KtvConfirmDialog;
import com.sz.ktv.view.widget.KtvBottomPageWidget;
import com.sz.ktv.view.widget.KtvBottomPageWidget.BackClickListener;

public class SetGeDanHuanYuanFragment extends BaseFragment implements OnClickListener,
		BackClickListener {

	KtvBottomPageWidget bottomPage ;
	 
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = null;
		try {
			view = inflater.inflate(R.layout.system_gedan_huanyuan_layout, container, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		initView(view);
		initConfirm();
		return view;
	}
	
	private void initView(View view) {
		bottomPage = (KtvBottomPageWidget)view.findViewById(R.id.sys_gedan_huanyuan_back);
		bottomPage.setBackClickListener(this);
		bottomPage.setType(KtvBottomPageWidget.TYPE_NO_DOTS_NO_PAGENUM);
		
		 
		
	}
	
	private void initConfirm()
	{
		KtvConfirmDialog confirm = new KtvConfirmDialog(getActivity(), "确定要还原歌单吗?大约需要20分钟时间!"){
			@Override
			public void noClick() {
				super.noClick();
				back();
			}
			
			@Override
			public void yesClick() {
				super.yesClick();
			}
		};
		confirm.show();
	}

	@Override
	public void back() {
		EventBus.getDefault().post(
				new AsyncMessage(FragmentFactory.FRAGMENT_HOME_SYSTEM_SET));
	}

	@Override
	public void onClick(View v) { }

}
