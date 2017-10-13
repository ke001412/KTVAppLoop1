package com.sz.ktv.ui.fragment;

import org.greenrobot.eventbus.EventBus;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sz.ktv.R;
import com.sz.ktv.msg.AsyncMessage;
import com.sz.ktv.ui.base.BaseFragment;
import com.sz.ktv.util.FragmentFactory;
import com.sz.ktv.util.SetShuchuMangeUtil;
import com.sz.ktv.view.widget.KtvBottomPageWidget;
import com.sz.ktv.view.widget.KtvBottomPageWidget.BackClickListener;

public class SetShuchuFragment extends BaseFragment implements OnClickListener,
		BackClickListener {

	KtvBottomPageWidget bottomPage;

	LinearLayout shuchu1080P;
	LinearLayout shuchu1080I;
	LinearLayout shuchu720P;
	LinearLayout shuchu480I;

	TextView shuchu1080PShow;
	TextView shuchu1080IShow;
	TextView shuchu720PShow;
	TextView shuchu480IShow;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = null;
		try {
			view = inflater.inflate(R.layout.shuchu_setting_layout, container,
					false);
		} catch (Exception e) {
			e.printStackTrace();
		}

		initView(view);
		return view;
	}

	private void initView(View view) {
		bottomPage = (KtvBottomPageWidget) view.findViewById(R.id.shuchu_back);
		bottomPage.setBackClickListener(this);
		bottomPage.setType(KtvBottomPageWidget.TYPE_NO_DOTS_NO_PAGENUM);

		shuchu1080P = (LinearLayout) view
				.findViewById(R.id.shuchu_1080p_layout);
		shuchu1080I = (LinearLayout) view
				.findViewById(R.id.shuchu_1080i_layout);
		shuchu720P = (LinearLayout) view.findViewById(R.id.shuchu_720p_layout);
		shuchu480I = (LinearLayout) view.findViewById(R.id.shuchu_480i_layout);

		shuchu1080PShow = (TextView) view.findViewById(R.id.shuchu_1080p);
		shuchu1080IShow = (TextView) view.findViewById(R.id.shuchu_1080i);
		shuchu720PShow = (TextView) view.findViewById(R.id.shuchu_720p);
		shuchu480IShow = (TextView) view.findViewById(R.id.shuchu_480i);

		shuchu1080P.setOnClickListener(this);
		shuchu1080I.setOnClickListener(this);
		shuchu720P.setOnClickListener(this);
		shuchu480I.setOnClickListener(this);

		boolean shuchu1080Pbool = SetShuchuMangeUtil.get1080Pstatus();
		boolean shuchu1080Ibool = SetShuchuMangeUtil.get1080Istatus();
		boolean shuchu720Pbool = SetShuchuMangeUtil.get720Pstatus();
		boolean shuchu480ibool = SetShuchuMangeUtil.get480Istatus();

		if (shuchu1080Pbool && !shuchu1080Ibool && !shuchu720Pbool
				&& !shuchu480ibool) {
			set1080PVisible(true);
		}  

		if (shuchu1080Ibool && !shuchu1080Pbool && !shuchu720Pbool
				&& !shuchu480ibool) {
			set1080IVisible(true);
		}  
		if (shuchu720Pbool && !shuchu1080Ibool && !shuchu1080Pbool
				&& !shuchu480ibool) {
			set720PVisible(true);
		}  
		if (shuchu480ibool && !shuchu720Pbool && !shuchu1080Ibool
				&& !shuchu1080Pbool) {
			set480IVisible(true);
		}  

	}

	public void set1080PVisible(boolean visible) {

		setAllGone();
		if (visible) {
			shuchu1080PShow.setVisibility(View.VISIBLE);
		}
	}

	public void set1080IVisible(boolean visible) {
		setAllGone();
		if (visible) {
			shuchu1080IShow.setVisibility(View.VISIBLE);
		}
	}

	public void set720PVisible(boolean visible) {
		setAllGone();
		if (visible) {
			shuchu720PShow.setVisibility(View.VISIBLE);
		}
	}

	public void set480IVisible(boolean visible) {
		setAllGone();
		if (visible) {
			shuchu480IShow.setVisibility(View.VISIBLE);
		}
	}

	public void setAllGone() {
		shuchu1080PShow.setVisibility(View.GONE);
		shuchu1080IShow.setVisibility(View.GONE);
		shuchu720PShow.setVisibility(View.GONE);
		shuchu480IShow.setVisibility(View.GONE);

	}

	@Override
	public void back() {
		EventBus.getDefault().post(
				new AsyncMessage(FragmentFactory.FRAGMENT_HOME_SYSTEM_SET));
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.shuchu_1080p_layout:
			SetShuchuMangeUtil.update1080Pstatus(true);
			SetShuchuMangeUtil.update1080Istatus(false);
			SetShuchuMangeUtil.update720Pstatus(false);
			SetShuchuMangeUtil.update480Istatus(false);
			
			set1080PVisible(true);
			break;
		case R.id.shuchu_1080i_layout:

			SetShuchuMangeUtil.update1080Pstatus(false);
			SetShuchuMangeUtil.update1080Istatus(true);
			SetShuchuMangeUtil.update720Pstatus(false);
			SetShuchuMangeUtil.update480Istatus(false);
			set1080IVisible(true);

			break;
		case R.id.shuchu_720p_layout:
			SetShuchuMangeUtil.update1080Pstatus(false);
			SetShuchuMangeUtil.update1080Istatus(false);
			SetShuchuMangeUtil.update720Pstatus(true);
			SetShuchuMangeUtil.update480Istatus(false);
			set720PVisible(true);

			break;
		case R.id.shuchu_480i_layout:
			SetShuchuMangeUtil.update1080Pstatus(false);
			SetShuchuMangeUtil.update1080Istatus(false);
			SetShuchuMangeUtil.update720Pstatus(false);
			SetShuchuMangeUtil.update480Istatus(true);
			set480IVisible(true);

			break;

		default:
			break;
		}
	}

}
