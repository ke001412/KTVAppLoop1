package com.sz.ktv.ui.fragment;

import org.greenrobot.eventbus.EventBus;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.sz.ktv.R;
import com.sz.ktv.msg.AsyncMessage;
import com.sz.ktv.msg.ZimuMessage;
import com.sz.ktv.ui.base.BaseFragment;
import com.sz.ktv.util.FragmentFactory;
import com.sz.ktv.util.ZiMuUtil;
import com.sz.ktv.view.widget.KtvBottomPageWidget;
import com.sz.ktv.view.widget.KtvBottomPageWidget.BackClickListener;

public class SetZimuFragment extends BaseFragment implements OnClickListener,
		BackClickListener {

	KtvBottomPageWidget bottomPage ;
	Button zimuOk;
	Button zimuCancle;
	EditText zimuText;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = null;
		try {
			view = inflater.inflate(R.layout.zimu_setting_layout, container, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		initView(view);
		return view;
	}
	
	private void initView(View view) {
		
		bottomPage = (KtvBottomPageWidget)view.findViewById(R.id.zimu_back);
		bottomPage.setBackClickListener(this);
		bottomPage.setType(KtvBottomPageWidget.TYPE_NO_DOTS_NO_PAGENUM);
		
		zimuOk = (Button)view.findViewById(R.id.zimu_ok);
		zimuCancle = (Button)view.findViewById(R.id.zimu_cancle);
		
		zimuText = (EditText)view.findViewById(R.id.zimu_text);
		
		zimuOk.setOnClickListener(this);
		zimuCancle.setOnClickListener(this);
		
	}

	@Override
	public void back() {
		EventBus.getDefault().post(
				new AsyncMessage(FragmentFactory.FRAGMENT_HOME_SYSTEM_SET));
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.zimu_ok:
			
			String zizmu = zimuText.getText().toString();
			if(!TextUtils.isEmpty(zizmu))
			{
				ZimuMessage msg = new ZimuMessage();
				msg.setMessage(zizmu);
				EventBus.getDefault().post(msg
						);
			}
			zimuText.setText("");
			
			EventBus.getDefault().post(
					new AsyncMessage(FragmentFactory.FRAGMENT_HOME_SYSTEM_SET));
			ZiMuUtil.setZimuContent(zizmu);
			
			break;
		case R.id.zimu_cancle:
			EventBus.getDefault().post(
					new AsyncMessage(FragmentFactory.FRAGMENT_HOME_SYSTEM_SET));
			break;
		case R.id.zimu_text:
	
	break;
		default:
			break;
		}
	}

}
