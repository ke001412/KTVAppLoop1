package com.sz.ktv.view.handwrite;

import android.content.Context;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sz.ktv.R;
import com.sz.ktv.view.handwrite.SelfAbsoluteLayout.HandWriteListener;

public class HandWriteView extends LinearLayout implements HandWriteListener,
		OnClickListener {

	private Context mContext;
	private SelfAbsoluteLayout handLayout;
	private TextView hanzi1;
	private TextView hanzi2;
	private TextView hanzi3;
	private TextView hanzi4;
	private TextView hanzi5;
	private TextView hanzi6;
	private TextView hanzi7;
	private TextView hanzi8;
	private TextView hanzi9;
	private TextView hanzi10;

	private EditText editText; 
	private TextView hanziDelte;
	private TextView hanziClear;
	
	public HandWriteView(Context context) {
		super(context);
		mContext = context;

		initView();
	}

	public HandWriteView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		mContext = context;

		initView();
	}

	public HandWriteView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		initView();
	}

	public void setEditTextView(EditText edit)
	{
		this.editText = edit;
	}
	private LayoutInflater mInflater;

	private void initView() {
		mInflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mInflater.inflate(R.layout.hand_write_layout, this);

		handLayout = (SelfAbsoluteLayout) findViewById(R.id.layout);
		
		SelfAbsoluteLayout.setHandWriteListener(this);
		
		hanzi1 = (TextView) findViewById(R.id.hanzi_1);
		hanzi2 = (TextView) findViewById(R.id.hanzi_2);
		hanzi3 = (TextView) findViewById(R.id.hanzi_3);
		hanzi4 = (TextView) findViewById(R.id.hanzi_4);
		hanzi5 = (TextView) findViewById(R.id.hanzi_5);
		hanzi6 = (TextView) findViewById(R.id.hanzi_6);
		hanzi7 = (TextView) findViewById(R.id.hanzi_7);
		hanzi8 = (TextView) findViewById(R.id.hanzi_8);
		hanzi9 = (TextView) findViewById(R.id.hanzi_9);
		hanzi10 = (TextView) findViewById(R.id.hanzi_10);

		hanzi1.setOnClickListener(this);
		hanzi2.setOnClickListener(this);
		hanzi3.setOnClickListener(this);
		hanzi4.setOnClickListener(this);
		hanzi5.setOnClickListener(this);
		hanzi6.setOnClickListener(this);
		hanzi7.setOnClickListener(this);
		hanzi8.setOnClickListener(this);
		hanzi9.setOnClickListener(this);
		hanzi10.setOnClickListener(this);
		
		hanziClear = (TextView)findViewById(R.id.hanzi_clear);
		hanziDelte = (TextView)findViewById(R.id.hanzi_delete);
		
		hanziClear.setOnClickListener(this);
		hanziDelte.setOnClickListener(this);
		
	}

	char[] hanziAry ;
	@Override
	public void onHandResult(char[] hanzi) {
		hanzi1.setText(hanzi[0]+"");
		hanzi2.setText(hanzi[1]+"");
		hanzi3.setText(hanzi[2]+"");
		hanzi4.setText(hanzi[3]+"");
		hanzi5.setText(hanzi[4]+"");
		hanzi6.setText(hanzi[5]+"");
		hanzi7.setText(hanzi[6]+"");
		hanzi8.setText(hanzi[7]+"");
		hanzi9.setText(hanzi[8]+"");
		hanzi10.setText(hanzi[9]+"");
		
		hanziAry = hanzi;
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		

		Editable editable = editText.getText();
		int start = editText.getSelectionStart();
		
		switch (id) {
		case R.id.hanzi_clear:
			editText.setText("");
			break;
		case R.id.hanzi_delete:
			if (editable != null && editable.length() > 0) {
				if (start > 0) {
					editable.delete(start - 1, start);
				}
			}
			break;
		case R.id.hanzi_1:
			editable.insert(start, ""+hanziAry[0]);
			handLayout.reset_recognize();
			break;
		case R.id.hanzi_2:
			editable.insert(start, ""+hanziAry[1]);
			handLayout.reset_recognize();
			break;
		case R.id.hanzi_3:
			editable.insert(start, ""+hanziAry[2]);
			handLayout.reset_recognize();
			break;
		case R.id.hanzi_4:
			editable.insert(start, ""+hanziAry[3]);
			handLayout.reset_recognize();
			break;
		case R.id.hanzi_5:
			editable.insert(start, ""+hanziAry[4]);
			handLayout.reset_recognize();
			break;
		case R.id.hanzi_6:
			editable.insert(start, ""+hanziAry[5]);
			handLayout.reset_recognize();
			break;
		case R.id.hanzi_7:
			editable.insert(start, ""+hanziAry[6]);
			handLayout.reset_recognize();
			break;
		case R.id.hanzi_8:
			editable.insert(start, ""+hanziAry[7]);
			handLayout.reset_recognize();
			break;
		case R.id.hanzi_9:
			editable.insert(start, ""+hanziAry[8]);
			handLayout.reset_recognize();
			break;
		case R.id.hanzi_10:
			editable.insert(start, ""+hanziAry[9]);
			handLayout.reset_recognize();
			break;

		default:
			break;
		}
	}

}
