package com.sz.ktv.ui.fragment;

import org.greenrobot.eventbus.EventBus;

import android.graphics.Color;
import android.graphics.LinearGradient;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.text.style.URLSpan;
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
import com.sz.ktv.util.SetPingFenMangeUtil;
import com.sz.ktv.view.widget.KtvBottomPageWidget;
import com.sz.ktv.view.widget.KtvBottomPageWidget.BackClickListener;
import com.sz.ktv.view.widget.UnderlineTextView;

public class SetPingfenFragment extends BaseFragment implements OnClickListener,
		BackClickListener {

	KtvBottomPageWidget bottomPage ;
	LinearLayout pingfenCloseTv;
	TextView pingfenCloseShow;
	
	LinearLayout pingfenOpenTv;
	TextView pingfenOpenShow;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = null;
		try {
			view = inflater.inflate(R.layout.pingfen_setting_layout, container, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		initView(view);
		return view;
	}
	
	private void initView(View view) {
		bottomPage = (KtvBottomPageWidget)view.findViewById(R.id.pingfen_back);
		bottomPage.setBackClickListener(this);
		bottomPage.setType(KtvBottomPageWidget.TYPE_NO_DOTS_NO_PAGENUM);
		
		  pingfenCloseTv = (LinearLayout)view.findViewById(R.id.pingfen_close);
		  pingfenCloseShow= (TextView)view.findViewById(R.id.pingfen_close_show);
		
		  pingfenOpenTv= (LinearLayout)view.findViewById(R.id.pingfen_open);
		  pingfenOpenShow= (TextView)view.findViewById(R.id.pingfen_open_show);
		  
		  pingfenCloseTv.setOnClickListener(this);
		  pingfenOpenTv.setOnClickListener(this);
		  
	      boolean closeBoolean = SetPingFenMangeUtil.getPingFenCloseStatus();
	      boolean openBoolean = SetPingFenMangeUtil.getPingFenOpenStatus();
	      
	      if(closeBoolean)
	      {
	    	  pingfenCloseShow.setVisibility(View.VISIBLE);
	    	  pingfenOpenShow.setVisibility(View.GONE);
	      }else if(openBoolean)
	      {
	    	  pingfenOpenShow.setVisibility(View.VISIBLE);
	    	  pingfenCloseShow.setVisibility(View.GONE);
	      }
		
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
		case R.id.pingfen_close:
			
			SetPingFenMangeUtil.updatePingFenClose(true);
			SetPingFenMangeUtil.updatePingFenOpen(false);
			
			  pingfenCloseShow.setVisibility(View.VISIBLE);
	    	  pingfenOpenShow.setVisibility(View.GONE);
	    	  
	    		EventBus.getDefault().post(
	    				new AsyncMessage(FragmentFactory.FRAGMENT_HOME_SYSTEM_SET));
			break;
		case R.id.pingfen_open:
			
			SetPingFenMangeUtil.updatePingFenClose(false);
			SetPingFenMangeUtil.updatePingFenOpen(true);
			
			 pingfenOpenShow.setVisibility(View.VISIBLE);
	    	  pingfenCloseShow.setVisibility(View.GONE);
	    	  
	    		EventBus.getDefault().post(
	    				new AsyncMessage(FragmentFactory.FRAGMENT_HOME_SYSTEM_SET));
			break;
		default:
			break;
		}
	}

}
