package com.sz.ktv.view.handwrite;

import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sz.ktv.R;

public class ABCInputView extends RelativeLayout implements OnClickListener {

	private Context mContext;

	private EditText ed;
	TextView keyQ;
	TextView keyW;
	TextView keyE;
	TextView keyR;
	TextView keyT;
	TextView keyY;
	TextView keyU;
	TextView keyI;
	TextView keyO;
	TextView keyP;
	TextView keyA;
	TextView keyS;
	TextView keyD;
	TextView keyF;
	TextView keyG;
	TextView keyH;
	TextView keyJ;
	TextView keyK;
	TextView keyL;
	TextView keyZ;
	TextView keyX;
	TextView keyC;
	TextView keyV;
	TextView keyB;
	TextView keyN;
	TextView keyM;

	TextView keyClear;
	TextView keyDelte;
	
	
	public ABCInputView(Context context) {
		super(context);
	}

	public ABCInputView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		mContext = context;

		initView();
	}

	public void setEeditView(EditText edit) {
		this.ed = edit;
		ed.setInputType(InputType.TYPE_NULL);// 
	}

	public ABCInputView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		initView();
	}

	private LayoutInflater mInflater;

	private void initView() {
		mInflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mInflater.inflate(R.layout.abc_input_view, this);

		  keyClear= (TextView) findViewById(R.id.key_clear);
		  keyDelte= (TextView) findViewById(R.id.key_delete);
		  
		  keyClear.setOnClickListener(this);
		  keyDelte.setOnClickListener(this);
		  
		keyQ = (TextView) findViewById(R.id.key_q);
		keyW = (TextView) findViewById(R.id.key_w);
		keyE = (TextView) findViewById(R.id.key_e);
		keyR = (TextView) findViewById(R.id.key_r);
		keyT = (TextView) findViewById(R.id.key_t);
		keyY = (TextView) findViewById(R.id.key_y);
		keyU = (TextView) findViewById(R.id.key_u);
		keyI = (TextView) findViewById(R.id.key_i);
		keyO = (TextView) findViewById(R.id.key_o);
		keyP = (TextView) findViewById(R.id.key_p);
		keyA = (TextView) findViewById(R.id.key_a);
		keyS = (TextView) findViewById(R.id.key_s);
		keyD = (TextView) findViewById(R.id.key_d);
		keyF = (TextView) findViewById(R.id.key_f);
		keyG = (TextView) findViewById(R.id.key_g);
		keyH = (TextView) findViewById(R.id.key_h);
		keyJ = (TextView) findViewById(R.id.key_j);
		keyK = (TextView) findViewById(R.id.key_k);
		keyL = (TextView) findViewById(R.id.key_l);
		keyZ = (TextView) findViewById(R.id.key_z);
		keyX = (TextView) findViewById(R.id.key_x);
		keyC = (TextView) findViewById(R.id.key_c);
		keyV = (TextView) findViewById(R.id.key_v);
		keyB = (TextView) findViewById(R.id.key_b);
		keyN = (TextView) findViewById(R.id.key_n);
		keyM = (TextView) findViewById(R.id.key_m);

		keyQ.setOnClickListener(this);
		keyW.setOnClickListener(this);
		keyE.setOnClickListener(this);
		keyR.setOnClickListener(this);
		keyT.setOnClickListener(this);
		keyY.setOnClickListener(this);
		keyU.setOnClickListener(this);
		keyI.setOnClickListener(this);
		keyO.setOnClickListener(this);
		keyP.setOnClickListener(this);
		keyA.setOnClickListener(this);
		keyS.setOnClickListener(this);
		keyD.setOnClickListener(this);
		keyF.setOnClickListener(this);
		keyG.setOnClickListener(this);
		keyH.setOnClickListener(this);
		keyJ.setOnClickListener(this);
		keyK.setOnClickListener(this);
		keyL.setOnClickListener(this);
		keyZ.setOnClickListener(this);
		keyX.setOnClickListener(this);
		keyC.setOnClickListener(this);
		keyV.setOnClickListener(this);
		keyB.setOnClickListener(this);
		keyN.setOnClickListener(this);
		keyM.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		
		Editable editable = ed.getText();
		int start = ed.getSelectionStart();
		
		switch (id) {
		case R.id.key_delete:
		 
			if (editable != null && editable.length() > 0) {
				if (start > 0) {
					editable.delete(start - 1, start);
				}
			}
			break;
		case R.id.key_clear:
			
			ed.setText("");
			
			break;
		case R.id.key_q:
			editable.insert(start, "Q");
			break;
		case R.id.key_w:
			editable.insert(start, "W");
			break;
		case R.id.key_e:
			editable.insert(start, "E");
			break;
		case R.id.key_r:
			editable.insert(start, "R");
			break;
		case R.id.key_t:
			editable.insert(start, "T");
			break;
		case R.id.key_y:
			editable.insert(start, "Y");
			break;
		case R.id.key_u:
			editable.insert(start, "U");
			break;
		case R.id.key_i:
			editable.insert(start, "I");
			break;
		case R.id.key_o:
			editable.insert(start, "O");
			break;
		case R.id.key_p:
			editable.insert(start, "P");
			break;
		case R.id.key_a:
			editable.insert(start, "A");
			break;
		case R.id.key_s:
			editable.insert(start, "S");
			break;
		case R.id.key_d:
			editable.insert(start, "D");
			break;
		case R.id.key_f:
			editable.insert(start, "F");
			break;
		case R.id.key_g:
			editable.insert(start, "G");
			break;
		case R.id.key_h:
			editable.insert(start, "H");
			break;
		case R.id.key_j:
			editable.insert(start, "J");
			break;
		case R.id.key_k:
			editable.insert(start, "K");
			break;
		case R.id.key_l:
			editable.insert(start, "L");
			break;
		case R.id.key_z:
			editable.insert(start, "Z");
			break;
		case R.id.key_x:
			editable.insert(start, "X");
			break;
		case R.id.key_c:
			editable.insert(start, "C");
			break;
		case R.id.key_v:
			editable.insert(start, "V");
			break;
		case R.id.key_b:
			editable.insert(start, "B");
			break;
		case R.id.key_n:
			editable.insert(start, "N");
			break;
		case R.id.key_m:
			editable.insert(start, "M");
			break;
		default:
			break;
		}

	}

}
