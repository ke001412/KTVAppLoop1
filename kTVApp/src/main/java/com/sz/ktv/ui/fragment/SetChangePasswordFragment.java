package com.sz.ktv.ui.fragment;

import org.greenrobot.eventbus.EventBus;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.sz.ktv.R;
import com.sz.ktv.msg.AsyncMessage;
import com.sz.ktv.ui.base.BaseFragment;
import com.sz.ktv.util.FragmentFactory;
import com.sz.ktv.util.SetPasswordMangeUtil;
import com.sz.ktv.view.widget.KtvBottomPageWidget;
import com.sz.ktv.view.widget.KtvBottomPageWidget.BackClickListener;

public class SetChangePasswordFragment extends BaseFragment implements
		OnClickListener, BackClickListener {

	KtvBottomPageWidget bottomPage;

	Button key0;
	Button key1;
	Button key2;
	Button key3;
	Button key4;
	Button key5;
	Button key6;
	Button key7;
	Button key8;
	Button key9;

	Button keyDelete;
	Button keyEnter;

	TextView tvShow;

	StringBuffer password = new StringBuffer();
	 
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = null;
		try {
			view = inflater.inflate(R.layout.system_password_layout, container,
					false);
		} catch (Exception e) {
			e.printStackTrace();
		}

		initView(view);
		return view;
	}

	private void initView(View view) {
		bottomPage = (KtvBottomPageWidget) view
				.findViewById(R.id.sys_password_back);
		bottomPage.setBackClickListener(this);
		bottomPage.setType(KtvBottomPageWidget.TYPE_NO_DOTS_NO_PAGENUM);

		key0 = (Button) view.findViewById(R.id.key_0);
		key1 = (Button) view.findViewById(R.id.key_1);
		key2 = (Button) view.findViewById(R.id.key_2);
		key3 = (Button) view.findViewById(R.id.key_3);
		key4 = (Button) view.findViewById(R.id.key_4);
		key5 = (Button) view.findViewById(R.id.key_5);
		key6 = (Button) view.findViewById(R.id.key_6);
		key7 = (Button) view.findViewById(R.id.key_7);
		key8 = (Button) view.findViewById(R.id.key_8);
		key9 = (Button) view.findViewById(R.id.key_9);
		keyDelete = (Button) view.findViewById(R.id.key_delete);
		keyEnter = (Button) view.findViewById(R.id.key_enter);
		tvShow = (TextView) view.findViewById(R.id.key_show);

		key1.setOnClickListener(this);
		key2.setOnClickListener(this);
		key3.setOnClickListener(this);
		key4.setOnClickListener(this);
		key5.setOnClickListener(this);
		key6.setOnClickListener(this);
		key7.setOnClickListener(this);
		key8.setOnClickListener(this);
		key9.setOnClickListener(this);
		key0.setOnClickListener(this);

		keyDelete.setOnClickListener(this);
		keyEnter.setOnClickListener(this);

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
		case R.id.key_0:
			password.append(0);
			tvShow.setText(password.toString());
			break;
		case R.id.key_1:
			password.append(1);
			tvShow.setText(password.toString());
			break;
		case R.id.key_2:
			password.append(2);
			tvShow.setText(password.toString());
			break;
		case R.id.key_3:
			password.append(3);
			tvShow.setText(password.toString());
			break;
		case R.id.key_4:
			password.append(4);
			tvShow.setText(password.toString());
			break;
		case R.id.key_5:
			password.append(5);
			tvShow.setText(password.toString());
			break;
		case R.id.key_6:
			password.append(6);
			tvShow.setText(password.toString());
			break;
		case R.id.key_7:
			password.append(7);
			tvShow.setText(password.toString());
			break;
		case R.id.key_8:
			password.append(8);
			tvShow.setText(password.toString());
			break;
		case R.id.key_9:
			password.append(9);
			tvShow.setText(password.toString());
			break;

		case R.id.key_delete:

			int lenght = password.length();
			if(lenght<=0)
			{
				return ;
			}
			String str = password.toString().substring(0,lenght-1);
			password = new StringBuffer(str);
			tvShow.setText(password.toString());
			
			break;
		case R.id.key_enter:
			String passs = tvShow.getText().toString();
			SetPasswordMangeUtil.updateManagePassword(passs);
			tvShow.setText("");
			
			EventBus.getDefault().post(
					new AsyncMessage(FragmentFactory.FRAGMENT_HOME_SYSTEM_SET));
			
			break;

		default:
			break;
		}
	}

}
