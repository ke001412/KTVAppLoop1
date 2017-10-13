package com.sz.ktv.view.handwrite;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sz.ktv.R;
import com.sz.ktv.view.handwrite.NumberInputView.NumberClickListener;

public class KTVInput extends RelativeLayout implements OnClickListener,NumberClickListener{
	
	
	private Context mContext; 
	private LayoutInflater mInflater;
	private View main_layout;
	/**
	 * 手写
	 */
//	HandWriteView handWriteLayout;
	/**
	 * 字母输入
	 */
	ABCInputView abcLayout;
	/**
	 * 数字输入
	 */
	NumberInputView numberInput;
	
	TextView shouPin;
	TextView shouXie;
	TextView ziShu;
	EditText edit; 
	
	private KTVInputListener mListener; 
	/**
	 * 首拼
	 */
	public static final int KTV_INPUT_TYPE_SHOUPIN=1;
	/**
	 * 汉子
	 */
	public static final int KTV_INPUT_TYPE_HANZI = 2;
	/**
	 * 字数
	 */
	public static final int KTV_INPUT_TYPE_NUMBER=3;
	
	public interface KTVInputListener{
		public void ktvInput(int type,String words);
	};
	
	public void setInputListener(KTVInputListener listener)
	{
		mListener = listener;
	}
	public KTVInput(Context context) {
		super(context);
		mContext = context;
		initView();
	}

	public KTVInput(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		mContext = context;

		initView();
	}

	public KTVInput(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		initView();
	}
	
	
	private void initView() {
		try {
			mInflater = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			mInflater.inflate(R.layout.ktv_input_layout, this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		main_layout = findViewById(R.id.main_layout);
		main_layout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				HideKeyboard(edit);
			}
		});
//		handWriteLayout = (HandWriteView)findViewById(R.id.hand_write);
		abcLayout = (ABCInputView)findViewById(R.id.abc_input_layout);
		numberInput = (NumberInputView)findViewById(R.id.number_input_layout);
		numberInput.setNumberClickListener(this);
		
		shouPin = (TextView)findViewById(R.id.shoupin);
		shouXie = (TextView)findViewById(R.id.shouxie);
		ziShu = (TextView)findViewById(R.id.zishu);
		
		edit = (EditText)findViewById(R.id.edit);
	
		ziShu.setOnClickListener(this);
		shouXie.setOnClickListener(this);
		shouPin.setOnClickListener(this);
//		abcLayout.setEeditView(edit);
//		handWriteLayout.setEditTextView(edit);
		
		edit.addTextChangedListener(new MyTextWather());
		
	}

	class MyTextWather implements TextWatcher {

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {

		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {

		}

		@Override
		public void afterTextChanged(Editable s) {

			 String words = s.toString();
			 if(null != mListener)
			 {
				 mListener.ktvInput(cuttentInputType, words);
			 }
		}

	}
	
	 //隐藏虚拟键盘
    public static void HideKeyboard(View v)
    {
    	
    	
//        InputMethodManager imm = ( InputMethodManager ) v.getContext( ).getSystemService( Context.INPUT_METHOD_SERVICE );     
//      if ( imm.isActive( ) ) {     
//          imm.hideSoftInputFromWindow( v.getApplicationWindowToken( ) , 0 );   
//          
//      }    
    }
	  //显示虚拟键盘
    public static void ShowKeyboard(View v)
    {
        InputMethodManager imm = ( InputMethodManager ) v.getContext( ).getSystemService( Context.INPUT_METHOD_SERVICE );     
      
      imm.showSoftInput(v,InputMethodManager.SHOW_FORCED);    
  
    }
    
	private int cuttentInputType = KTV_INPUT_TYPE_SHOUPIN;
	
	@Override
	public void onClick(View v) {
	 
		int id = v.getId();
		switch (id) {
		case R.id.shoupin:
			cuttentInputType = KTV_INPUT_TYPE_SHOUPIN;
			
//			abcLayout.setVisibility(View.VISIBLE);
//			handWriteLayout.setVisibility(View.GONE);
			numberInput.setVisibility(View.GONE);
			
			edit.setText("");
			 
			ShowKeyboard(edit);
			break;
		case R.id.shouxie:
			cuttentInputType = KTV_INPUT_TYPE_HANZI;
			abcLayout.setVisibility(View.GONE);
//			handWriteLayout.setVisibility(View.VISIBLE);
			numberInput.setVisibility(View.GONE);
			
			edit.setText("");
			
			break;
		case R.id.zishu:
			HideKeyboard(edit);
			cuttentInputType = KTV_INPUT_TYPE_NUMBER;
			abcLayout.setVisibility(View.GONE);
//			handWriteLayout.setVisibility(View.GONE);
			numberInput.setVisibility(View.VISIBLE);
			edit.setText("");
			
			break;
		default:
			break;
		}
	}

	@Override
	public void numberClick(String num) {
		 if(null != mListener)
		 {
			 mListener.ktvInput(cuttentInputType, num);
		 }
	}
}
