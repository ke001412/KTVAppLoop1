package com.sz.ktv.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.sz.ktv.R;

public class NumberInputDialog extends Dialog implements OnClickListener {

	Button bt0;
	Button bt1;
	Button bt2;
	Button bt3;
	Button bt4;
	Button bt5;
	Button bt6;
	Button bt7;
	Button bt8;
	Button bt9;

	TextView btDel;
	TextView btEnter;

	TextView numberTv;
	StringBuffer numberBuffer ;
	private String hintStr;
	private int  type =  -1;
	
	public static final int TYPE_PASSWORD = 1; 
	public static final int TYPE_NO_PASSWORD_NUMBER = 2; 
	
	public NumberInputDialog(Context context) {
		super(context, R.style.loaddialog);
	}

	public NumberInputDialog(Context context,String defaultHintStr,int defaultType) {
		super(context, R.style.loaddialog);
		hintStr = defaultHintStr;
		type = defaultType;
	}

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.number_key_layout);
		layoutView();
	}

	private void layoutView() {

		bt0 = (Button) findViewById(R.id.btn_0);
		bt1 = (Button) findViewById(R.id.btn_1);
		bt2 = (Button) findViewById(R.id.btn_2);
		bt3 = (Button) findViewById(R.id.btn_3);
		bt4 = (Button) findViewById(R.id.btn_4);
		bt5 = (Button) findViewById(R.id.btn_5);
		bt6 = (Button) findViewById(R.id.btn_6);
		bt7 = (Button) findViewById(R.id.btn_7);
		bt8 = (Button) findViewById(R.id.btn_8);
		bt9 = (Button) findViewById(R.id.btn_9);
		btDel = (TextView) findViewById(R.id.btn_delete);
		btEnter = (TextView) findViewById(R.id.btn_enter);

		numberTv = (TextView) findViewById(R.id.num_input);

		bt0.setOnClickListener(this);
		bt1.setOnClickListener(this);
		bt2.setOnClickListener(this);
		bt3.setOnClickListener(this);
		bt4.setOnClickListener(this);
		bt5.setOnClickListener(this);
		bt6.setOnClickListener(this);
		bt7.setOnClickListener(this);
		bt8.setOnClickListener(this);
		bt9.setOnClickListener(this);
		btDel.setOnClickListener(this);
		btEnter.setOnClickListener(this);

		numberBuffer = new StringBuffer();
		numberTv.setText("");
		numberTv.setHint(hintStr);
		
		if(type == TYPE_PASSWORD)
		{
			numberTv.setInputType(InputType.TYPE_TEXT_VARIATION_WEB_PASSWORD);
		}else if(type == TYPE_NO_PASSWORD_NUMBER)
		{
			numberTv.setInputType(InputType.TYPE_TEXT_VARIATION_NORMAL);
		}
	}

	@Override
	public void onClick(View v) {

		int id = v.getId();
		switch (id) {
		case R.id.btn_0:
			numberBuffer.append("0");
			numberTv.setText(numberBuffer.toString());
			
			break;
		case R.id.btn_1:

			numberBuffer.append("1");
			numberTv.setText(numberBuffer.toString());
			
			
			break;
		case R.id.btn_2:

			numberBuffer.append("2");
			numberTv.setText(numberBuffer.toString());
			
			
			break;
		case R.id.btn_3:
			numberBuffer.append("3");
			numberTv.setText(numberBuffer.toString());
			
			
			break;
		case R.id.btn_4:
			numberBuffer.append("4");
			numberTv.setText(numberBuffer.toString());
			
			break;
		case R.id.btn_5:
			numberBuffer.append("5");
			numberTv.setText(numberBuffer.toString());
			
			break;
		case R.id.btn_6:
			numberBuffer.append("6");
			numberTv.setText(numberBuffer.toString());
			
			break;
		case R.id.btn_7:
			numberBuffer.append("7");
			numberTv.setText(numberBuffer.toString());
			
			break;
		case R.id.btn_8:
			numberBuffer.append("8");
			numberTv.setText(numberBuffer.toString());
			
			break;
		case R.id.btn_9:
			numberBuffer.append("9");
			numberTv.setText(numberBuffer.toString());
			
			break;
		case R.id.btn_delete:
			int lenght = numberBuffer.length();
			if(lenght<=0)
			{
				return ;
			}
			String str = numberBuffer.toString().substring(0,lenght-1);
			numberBuffer = new StringBuffer(str);
			numberTv.setText(numberBuffer.toString());
			
			break;
		case R.id.btn_enter:
			enterPassword(numberBuffer.toString());
			break;
		default:
			break;
		}
	}

	public void enterPassword(String password)
	{
		
	}
	@Override
	public void dismiss() {
		super.dismiss();
	}
	
	public void inputError()
	{
		numberTv.setText("");
		numberTv.setHint("管理密码不正确");
		numberBuffer = new StringBuffer("");
	}
}
