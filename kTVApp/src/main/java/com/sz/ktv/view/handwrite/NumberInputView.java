package com.sz.ktv.view.handwrite;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.sz.ktv.R;

public class NumberInputView extends RelativeLayout implements OnClickListener {

	
	private Context mContext;
	
	Button yi;
	Button er;
	Button san;
	Button si;
	Button wu;
	Button liu;
	Button qi;
	Button ba;
	Button duo;
	
	private NumberClickListener mListener; 
	
	public interface NumberClickListener{
		public void numberClick(String num);
	};
	
	
	public void setNumberClickListener(NumberClickListener listener)
	{
		mListener = listener;
	}
	public NumberInputView(Context context) {
		super(context);
	}

	public NumberInputView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		mContext = context;

		initView();
	}
 

	public NumberInputView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		initView();
	}

	private LayoutInflater mInflater;

	private void initView() {
		mInflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mInflater.inflate(R.layout.input_number_layout, this);
 
		yi = (Button) findViewById(R.id.search_1);
		er = (Button) findViewById(R.id.search_2);
		san = (Button) findViewById(R.id.search_3);
		si = (Button) findViewById(R.id.search_4);
		wu = (Button) findViewById(R.id.search_5);
		liu = (Button) findViewById(R.id.search_6);
		qi = (Button) findViewById(R.id.search_7);
		ba = (Button) findViewById(R.id.search_8);
		duo = (Button) findViewById(R.id.search_duo);
		
		yi.setOnClickListener(this);
		er.setOnClickListener(this);
		san.setOnClickListener(this);
		si.setOnClickListener(this);
		wu.setOnClickListener(this);
		liu.setOnClickListener(this);
		qi.setOnClickListener(this);
		ba.setOnClickListener(this);
		duo.setOnClickListener(this);
	}
	
	
	private String zishuNum = "0";
	
	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.search_1:
			zishuNum = "1";
			break;
		case R.id.search_2:
			zishuNum = "2";
			break;
		case R.id.search_3:
			zishuNum = "3";
			break;
		case R.id.search_4:
			zishuNum = "4";
			break;
		case R.id.search_5:
			zishuNum = "5";
			break;
		case R.id.search_6:
			zishuNum = "6";
			break;
		case R.id.search_7:
			zishuNum = "7";
			break;
		case R.id.search_8:
			zishuNum = "8";
			break;
		case R.id.search_duo:
			zishuNum = "9";
			break;
		default:
			break;
		}
		if(null != mListener)
		{
			mListener.numberClick(zishuNum);
		}
	}

}
