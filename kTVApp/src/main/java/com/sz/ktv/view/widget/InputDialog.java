package com.sz.ktv.view.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sz.ktv.R;

/**
 * @author wanglei
 *         <p>
 *         带有输入框的Dialog
 */
public class InputDialog extends Dialog {

    private static String title;
    private static String inputHint;
    private static String buttonText;
    private static boolean canceledOnTouchOutside = true;
    private static OnDialogButtonClickListener listener;
    private EditText input;
    private static InputDialog inputDialog;

    public static class Builder {
        private Context context;
        private int theme;

        public Builder(Context context, int theme) {
            this.context = context;
            this.theme = theme;
        }

        public Builder setTitle(String text) {
            title = text;
            return this;
        }

        public Builder setButtonText(String text) {
            buttonText = text;
            return this;
        }

        public Builder setInputHint(String hint) {
            inputHint = hint;
            return this;
        }

        public Builder setCanceledOnTouchOutside(boolean value) {
            canceledOnTouchOutside = value;
            return this;
        }

        public Builder setOnDialogButtonClickListener(OnDialogButtonClickListener listener) {
            InputDialog.listener = listener;
            return this;
        }

        public InputDialog show() {
            inputDialog = new InputDialog(context, theme);
            inputDialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
            inputDialog.show();
            return inputDialog;
        }

        public void dismiss() {
            if (inputDialog != null) {
                inputDialog.dismiss();
            }
        }
    }

    
    
    private InputDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_dialog_layout);
        TextView textView = (TextView) findViewById(R.id.input_dialog_title);
        if (!TextUtils.isEmpty(title)) {
            textView.setText(title);
        }
        input = (EditText) findViewById(R.id.input_dialog_et);
        if (!TextUtils.isEmpty(inputHint)) {
            input.setHint(inputHint);
        }
        Button button = (Button) findViewById(R.id.input_dialog_btn_ok);
        Button buttonCacle = (Button) findViewById(R.id.input_dialog_btn_cancle);
        if (!TextUtils.isEmpty(buttonText)) {
            button.setText(buttonText);
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                	String str = input.getText().toString().trim();
                	if(!TextUtils.isEmpty(str))
                	{
                		dismiss();
                    listener.onDialogButtonClick(str);
                	}else {
                		return ;
                	}
                }
                
//            	okClick(input.getText().toString().trim());
            }
        });
        
        buttonCacle.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (inputDialog != null) {
	                inputDialog.dismiss();
	            }
			}
		});
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (!canceledOnTouchOutside && (keyCode == KeyEvent.KEYCODE_BACK)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    public void okClick(String inputString)
    {
    	dismiss();
    }
    public void cancleClick()
    {
    	dismiss();
    }
    /**
     * 按钮点击的回调
     */
    public interface OnDialogButtonClickListener {
        public void onDialogButtonClick(String checkCode);
       
    }

}
