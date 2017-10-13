package com.sz.ktv.view;

import org.greenrobot.eventbus.EventBus;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.PopupWindow;

import com.sz.ktv.MainActivity;
import com.sz.ktv.R;
import com.sz.ktv.db.DataBase;
import com.sz.ktv.msg.SingerTypeMessage;
import com.sz.ktv.msg.SongTypeMessage;
import com.sz.ktv.ui.fragment.HomeGeMingFragment;
import com.sz.ktv.ui.fragment.HomeGexingFragment;
import com.sz.ktv.view.handwrite.KTVInput;
import com.sz.ktv.view.handwrite.KTVInput.KTVInputListener;

public class HomeSearchWindow extends PopupWindow implements OnClickListener,
		KTVInputListener {

	KTVInput ktvInput;

	public HomeSearchWindow(Activity context) {
		super(context);

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		conentView = inflater.inflate(R.layout.home_search_dialog, null);
		int h = context.getWindowManager().getDefaultDisplay().getHeight();
		int w = context.getWindowManager().getDefaultDisplay().getWidth();
		// 设置SelectPicPopupWindow的View
		this.setContentView(conentView);
		// 设置SelectPicPopupWindow弹出窗体的宽
		this.setWidth(w / 2);
		// 设置SelectPicPopupWindow弹出窗体的高
		this.setHeight(h / 2); // LayoutParams.WRAP_CONTENT
		// 设置SelectPicPopupWindow弹出窗体可点击
		this.setFocusable(true);
		this.setOutsideTouchable(true);
		// 刷新状态
		this.update();

		ktvInput = (KTVInput) conentView.findViewById(R.id.ktv_input);
		ktvInput.setInputListener(this);

		flag = true;

		// searchEdit.addTextChangedListener(new MyTextWather());
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

			if (!MainActivity.gexingHidden && flag) {
				String str = s.toString();
				SingerTypeMessage msg = new SingerTypeMessage();
				msg.setMsg(str);
				msg.setSingerType(HomeGexingFragment.TYPE_SINGER_SHOUPIN);
				EventBus.getDefault().post(msg);
			} else {
				if (flag) {
					String str = s.toString();
					SongTypeMessage msg = new SongTypeMessage();
					msg.setKeyWords(str);
					msg.setType(HomeGeMingFragment.TYPE_SONG_SHOUPIN);
					EventBus.getDefault().post(msg);
				}
			}
		}

	}

	private View conentView;
	private boolean flag = false;

	private void gotoGemingZiShu(String zishuNum) {

		DataBase.keyType = HomeGeMingFragment.TYPE_SONG_ZISHU;
		DataBase.keySong = zishuNum;
		
		SongTypeMessage msg = new SongTypeMessage();
		msg.setKeyWords(zishuNum);
		msg.setType(HomeGeMingFragment.TYPE_SONG_ZISHU);
		EventBus.getDefault().post(msg);

	}

	@Override
	public void onClick(View v) {
	}

	public void showPopupWindow(View parent) {
		if (!this.isShowing()) {
			this.showAtLocation(parent, Gravity.TOP, 30, parent.getHeight());
		} else {
			this.dismiss();
		}
	}

	@Override
	public void ktvInput(int type, String words) {
		switch (type) {
		case KTVInput.KTV_INPUT_TYPE_NUMBER:
			gotoGemingZiShu(words);
			break;
		case KTVInput.KTV_INPUT_TYPE_HANZI:
			SongTypeMessage msg = new SongTypeMessage();
			msg.setKeyWords(words);
			msg.setType(HomeGeMingFragment.TYPE_SONG_SHOUPIN);
			EventBus.getDefault().post(msg);
			break;
		case KTVInput.KTV_INPUT_TYPE_SHOUPIN:
			if (!MainActivity.gexingHidden) {

				
				SingerTypeMessage msg2 = new SingerTypeMessage();
				msg2.setMsg(words);
				msg2.setSingerType(HomeGexingFragment.TYPE_SINGER_SHOUPIN);
				EventBus.getDefault().post(msg2);
				
			}else {
				DataBase.keyType = HomeGeMingFragment.TYPE_SONG_SHOUPIN;
				DataBase.keySong = words;
				
				SongTypeMessage msg3 = new SongTypeMessage();
				msg3.setKeyWords(words);
				msg3.setType(HomeGeMingFragment.TYPE_SONG_SHOUPIN);
				EventBus.getDefault().post(msg3);
			}
			break;

		default:
			break;
		}

	}
}
