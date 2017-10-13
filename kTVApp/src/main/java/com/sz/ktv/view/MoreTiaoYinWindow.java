package com.sz.ktv.view;

import android.app.Activity;
import android.content.Context;
import android.provider.MediaStore.Audio;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.sz.ktv.R;
import com.sz.ktv.util.AudioUtil;
import com.sz.ktv.util.Global;

public class MoreTiaoYinWindow extends PopupWindow implements OnClickListener {

	ImageView yinjian;
	ImageView yinjia;

	ProgressBar progress;

	public MoreTiaoYinWindow(Activity context) {
		super(context);

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		conentView = inflater.inflate(R.layout.more_tiaoyin_layout, null);
		int h = context.getWindowManager().getDefaultDisplay().getHeight();
		int w = context.getWindowManager().getDefaultDisplay().getWidth();
		// 设置SelectPicPopupWindow的View
		this.setContentView(conentView);
		// 设置SelectPicPopupWindow弹出窗体的宽
		this.setWidth(w / 5 + 100);
		// 设置SelectPicPopupWindow弹出窗体的高
		this.setHeight(h / 5); // LayoutParams.WRAP_CONTENT
		// 设置SelectPicPopupWindow弹出窗体可点击
		this.setFocusable(true);
		this.setOutsideTouchable(true);
		// 刷新状态
		this.update();
		yinjian = (ImageView) conentView.findViewById(R.id.yinjian);
		yinjia = (ImageView) conentView.findViewById(R.id.yinjia);
		progress = (ProgressBar) conentView.findViewById(R.id.progress);

		final int currentAudio = AudioUtil.getCurrentVolume();
		final int max = AudioUtil.getCurrentMaxVolume();

		 
		progress.setMax(max);
		progress.setProgress(currentAudio);
		 
		yinjian.setOnClickListener(this);
		yinjia.setOnClickListener(this);
	}

	private View conentView;

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.yinjian:
			AudioUtil.reduceVolume();
			final int currentAudio1 = AudioUtil.getCurrentVolume();
			progress.setProgress(currentAudio1);
			break;
		case R.id.yinjia:
			AudioUtil.addVolume();
			final int currentAudio2 = AudioUtil.getCurrentVolume();
			progress.setProgress(currentAudio2);
			break;
		default:
			break;
		}

	}

	public void showPopupWindow(View parent) {
		if (!this.isShowing()) {
			this.showAtLocation(parent, Gravity.RIGHT, 220,
					parent.getHeight()*2+30);
		} else {
			this.dismiss();
		}
	}
}
