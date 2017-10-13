//package com.sz.ktv.view;
//
//import android.app.Activity;
//import android.content.Context;
//import android.provider.MediaStore.Audio;
//import android.view.Gravity;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.ImageView;
//import android.widget.PopupWindow;
//import android.widget.ProgressBar;
//import android.widget.RelativeLayout;
//import android.widget.Toast;
//
//import com.sz.ktv.R;
//import com.sz.ktv.util.AudioUtil;
//import com.sz.ktv.util.Global;
//import com.sz.ktv.view.HomeQiFenWindow.PopuwindowQiFenClickListener;
//
//public class MoreYuYanWindow extends PopupWindow implements OnClickListener {
//
//	RelativeLayout yuyanJianti;
//	RelativeLayout yuyanFanti;
//	RelativeLayout yuyanEnglish;
//
//
//	
//	PopuwindowYuYanClickListener listener; 
//	
//	public void setPopuwindowYuYanClickListener(PopuwindowYuYanClickListener mListener){
//		this.listener = mListener;
//	}
//	public interface PopuwindowYuYanClickListener{
//		public void yuyanJianTiClick();
//		public void yuyanFanTiClick();
//		public void yuyanEnglishClick();
// 
//		
//	}
//	
//	public MoreYuYanWindow(Activity context) {
//		super(context);
//
//		LayoutInflater inflater = (LayoutInflater) context
//				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//		conentView = inflater.inflate(R.layout.more_yuyan_layout, null);
//		int h = context.getWindowManager().getDefaultDisplay().getHeight();
//		int w = context.getWindowManager().getDefaultDisplay().getWidth();
//		// 设置SelectPicPopupWindow的View
//		this.setContentView(conentView);
//		// 设置SelectPicPopupWindow弹出窗体的宽
//		this.setWidth(w / 2 - 80);
//		// 设置SelectPicPopupWindow弹出窗体的高
//		this.setHeight(h / 3); // LayoutParams.WRAP_CONTENT
//		// 设置SelectPicPopupWindow弹出窗体可点击
//		this.setFocusable(true);
//		this.setOutsideTouchable(true);
//		// 刷新状态
//		this.update();
//		yuyanJianti = (RelativeLayout) conentView.findViewById(R.id.yuyan_jianti);
//		yuyanFanti = (RelativeLayout) conentView.findViewById(R.id.yuyan_fanti);
//		yuyanEnglish = (RelativeLayout) conentView.findViewById(R.id.yuyan_english);
//
//		 
//		 
//		yuyanJianti.setOnClickListener(this);
//		yuyanFanti.setOnClickListener(this);
//		yuyanEnglish.setOnClickListener(this);
//		
//	}
//
//	private View conentView;
//
//	@Override
//	public void onClick(View v) {
//		int id = v.getId();
//		switch (id) {
//		case R.id.yuyan_jianti:
//			listener.yuyanJianTiClick();
//			dismiss();
//			break;
//		case R.id.yuyan_fanti:
//			listener.yuyanFanTiClick();
//			dismiss();
//			break;
//		case R.id.yuyan_english:
//			listener.yuyanEnglishClick();
//			dismiss();
//			break;
//		default:
//			break;
//		}
//
//	}
//
//	public void showPopupWindow(View parent) {
//		if (!this.isShowing()) {
//			this.showAtLocation(parent, Gravity.RIGHT, 60,
//					parent.getHeight() - 80);
//		} else {
//			this.dismiss();
//		}
//	}
//}
