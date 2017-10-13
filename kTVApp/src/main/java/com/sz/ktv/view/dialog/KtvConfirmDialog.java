package com.sz.ktv.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.sz.ktv.R;

public class KtvConfirmDialog extends Dialog implements OnClickListener {

	 
	
	TextView tvMsg;
	Button buttonYes;
	Button buttonNo;
 
	String showMsg = "";
	
	public KtvConfirmDialog(Context context,String msg) {
		super(context, R.style.loaddialog);
		showMsg = msg;
	}

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.confirm_dialog);
		layoutView();
	}

	private void layoutView() {

		buttonYes = (Button)  findViewById(R.id.confirm_yes);
		buttonNo = (Button)  findViewById(R.id.confirm_no);
		tvMsg = (TextView)  findViewById(R.id.confirm_msg);
		 
		buttonYes.setOnClickListener(this);
		buttonNo.setOnClickListener(this);
		
		tvMsg.setText(showMsg);
	}

	
	@Override
	public void onClick(View v) {
 
		int id = v.getId();
		switch (id) {
		case R.id.confirm_yes:
			yesClick();
			dismiss();
			break;
		case R.id.confirm_no:
			 noClick();
			dismiss();
			break;
		 
		default:
			break;
		}

	
		 
	}

	
public void yesClick()
{
	}

public void noClick()
{
	}
	 
	@Override
	public void dismiss() {
		super.dismiss();
	}
	
	 
}
