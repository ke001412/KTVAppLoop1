package com.sz.ktv.ui.fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.greenrobot.eventbus.EventBus;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.sz.ktv.MainActivity;
import com.sz.ktv.R;
import com.sz.ktv.db.DataBase;
import com.sz.ktv.db.ktv.SongUtil;
import com.sz.ktv.msg.AsyncMessage;
import com.sz.ktv.ui.base.BaseFragment;
import com.sz.ktv.util.FragmentFactory;
import com.sz.ktv.util.SetPasswordMangeUtil;
import com.sz.ktv.util.ViewAnimationUtil;
import com.sz.ktv.util.schdule.AutoTask;
import com.sz.ktv.util.schdule.ScheduledTask;
import com.sz.ktv.view.dialog.NumberInputDialog;

public class HomeCenterFragment extends BaseFragment implements OnClickListener {

	
	 private ViewPager viewPager;
	 private Activity activity;
	 private View homeCenterLeftView; 
      
    private List<View> viewList;//view数组  
    private ViewPagerAdapter pageAdapter;
    
    RelativeLayout viewGexing;
    RelativeLayout viewXinge;
    RelativeLayout viewLeibie;
    RelativeLayout viewPaihang;
    RelativeLayout viewGeming;
    RelativeLayout viewGaoqing;
    RelativeLayout viewShezhi;
    RelativeLayout viewRemen;
    RelativeLayout viewWuqu;
    RelativeLayout viewShoucang;
    
    RelativeLayout [] viewArrays;
    
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.home_center_layout, container, false);
		activity = getActivity();
		viewList = new ArrayList<View>();
		initView(view);
		return view;
	}

	private void initView(View view) {
			viewPager = (ViewPager)view. findViewById(R.id.home_center_viewpager);  
	        LayoutInflater inflater=activity.getLayoutInflater(); 
	        homeCenterLeftView =  inflater.inflate(R.layout.home_center_left, null);
	        viewList.add(homeCenterLeftView);
	        pageAdapter = new ViewPagerAdapter(viewList);
	        viewPager.setAdapter(pageAdapter);
	        viewPager.setOnPageChangeListener(pageChangeListener);
	        viewPager.setCurrentItem(0);
	        initView(0);
	}
	
	AutoTask animatonTash = new AutoTask() {
		
		@Override
		public void run() {
			handler.sendEmptyMessage(MSG_START_ANIM);
		}
	};
	
	private static final int MSG_START_ANIM = 10;
	private static int randomPreInt = 0;
	
	Handler handler = new Handler()
	{
		public void dispatchMessage(android.os.Message msg) {
			super.dispatchMessage(msg);
			int what = msg.what;
			switch (what) {
			case MSG_START_ANIM:
				  Random random=new Random();
				int randomInt=random.nextInt(10);
				if(randomInt == randomPreInt)
				{
					randomInt = randomInt -1; 
				}
				if(randomInt<0)
				{
					randomInt = 0;
				}
				if(randomInt >=10)
				{
					randomInt = 9;
				}
				randomPreInt = randomInt;
				View view = viewArrays[randomInt];
				ViewAnimationUtil.startViewAnimation(view);
				break;

			default:
				break;
			}
		};
	};
	private void initView(int arg0) {
		View view = viewList.get(arg0);
		viewGexing = (RelativeLayout)view.findViewById(R.id.home_center_gexing);
		viewXinge = (RelativeLayout)view.findViewById(R.id.home_center_xinge);
		viewLeibie = (RelativeLayout)view.findViewById(R.id.home_center_leibie);
		viewPaihang = (RelativeLayout)view.findViewById(R.id.home_center_paihang);
		viewGeming = (RelativeLayout)view.findViewById(R.id.home_center_geming);
		viewGaoqing = (RelativeLayout)view.findViewById(R.id.home_center_gaoqing);
		viewShezhi = (RelativeLayout)view.findViewById(R.id.home_center_shezhi);
		viewRemen = (RelativeLayout)view.findViewById(R.id.home_center_remen);
		viewWuqu = (RelativeLayout)view.findViewById(R.id.home_center_wuqu);
		viewShoucang = (RelativeLayout)view.findViewById(R.id.home_center_shoucang);
		
		viewArrays = new RelativeLayout[10];
		viewArrays[0]=viewGexing;
		viewArrays[1]=viewXinge;
		viewArrays[2]=viewLeibie;
		viewArrays[3]=viewPaihang;
		viewArrays[4]=viewGeming;
		viewArrays[5]=viewGaoqing;
		viewArrays[6]=viewShezhi;
		viewArrays[7]=viewRemen;
		viewArrays[8]=viewWuqu;
		viewArrays[9]=viewShoucang;
		
		ScheduledTask.addRateTask(animatonTash, 5*1000,5*1000);
		int size = viewArrays.length;
		for(int i=0;i<size;i++)
		{
			viewArrays[i].setOnClickListener(this);
		}
	}
	

	public void onHiddenChanged(boolean hidden) {
		 super.onHiddenChanged(hidden);
	};

	@Override
	public void onDestroy() {
		super.onDestroy();
		animatonTash.stop(true);
	}
	
	public void onResume() {
		super.onResume();
	};
	
	OnPageChangeListener pageChangeListener = new OnPageChangeListener() {
		
		@Override
		public void onPageSelected(int arg0) {
			
			initView(arg0);
		}
	

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			
		}
		
		@Override
		public void onPageScrollStateChanged(int arg0) {
			
		}
	};
	
	 
	public class ViewPagerAdapter extends PagerAdapter{

	    List<View> viewLists;
	    
	    public ViewPagerAdapter(List<View> lists)
	    {
	        viewLists = lists;
	    }

	    @Override
	    public int getCount() {                                                                 //获得size
	        // TODO Auto-generated method stub
	        return viewLists.size();
	    }

	    @Override
	    public boolean isViewFromObject(View arg0, Object arg1) {                         
	        // TODO Auto-generated method stub
	        return arg0 == arg1;
	    }
	    
	    @Override
	    public void destroyItem(View view, int position, Object object)                       //销毁Item
	    {
	        ((ViewPager) view).removeView(viewLists.get(position));
	    }
	    
	    @Override
	    public Object instantiateItem(View view, int position)                                //实例化Item
	    {
	        ((ViewPager) view).addView(viewLists.get(position), 0);
	        
	        return viewLists.get(position);
	    }
	    
	}

	@Override
	public void onClick(View v) {
		 
		int id = v.getId();
		switch (id) {
		case R.id.home_center_xinge:
			
			SongUtil.SONG_SELECT_TYPE = SongUtil.SORT_TYPE_5;
			DataBase.keySong="";
			DataBase.keyType=-1;
			
			EventBus.getDefault().post(new AsyncMessage(FragmentFactory.FRAGMENT_HOME_GEMING));
			
			break;
		case R.id.home_center_geming:
			EventBus.getDefault().post(new AsyncMessage(FragmentFactory.FRAGMENT_HOME_GEMING));
			
			break;
		case R.id.home_center_gexing:
			EventBus.getDefault().post(new AsyncMessage(FragmentFactory.FRAGMENT_HOME_GEXING));
			MainActivity.gexingHidden = false;
			
			break;
		case R.id.home_center_leibie:
			EventBus.getDefault().post(new AsyncMessage(FragmentFactory.FRAGMENT_HOME_LEIBIE));
			break;
		case R.id.home_center_paihang:
			EventBus.getDefault().post(new AsyncMessage(FragmentFactory.FRAGMENT_HOME_PAIHANG));
			break;
		case R.id.home_center_wuqu:
			EventBus.getDefault().post(new AsyncMessage(FragmentFactory.FRAGMENT_HOME_WUQU));
			break;
		case R.id.home_center_shezhi:
		
			NumberInputDialog inputDialog = new NumberInputDialog(activity,getResources().getString(R.string.ktv_shuru_mima),NumberInputDialog.TYPE_PASSWORD)
			{
				@Override
				public void enterPassword(String password) {
					super.enterPassword(password);
					String oldPass = SetPasswordMangeUtil.getMangePassword();
					if(password.equals(oldPass))
					{
						EventBus.getDefault().post(new AsyncMessage(FragmentFactory.FRAGMENT_HOME_SYSTEM_SET));
						dismiss();
					}else {
						inputError();
					}
				}
			};
			inputDialog.setCanceledOnTouchOutside(true);
			inputDialog.show();
			
			break;
			
		case R.id.home_center_remen:
			EventBus.getDefault().post(new AsyncMessage(FragmentFactory.FRAGMENT_HOME_REMEN));
			break;
		case R.id.home_center_shoucang:
			
			
			NumberInputDialog inputPhoneDialog = new NumberInputDialog(activity,getResources().getString(R.string.ktv_shoucang_mima),NumberInputDialog.TYPE_NO_PASSWORD_NUMBER)
			{
				@Override
				public void enterPassword(String password) {
					super.enterPassword(password);
					EventBus.getDefault().post(new AsyncMessage(FragmentFactory.FRAGMENT_HOME_SHOUCANG));
					dismiss();
					}
				 
			};
			inputPhoneDialog.setCanceledOnTouchOutside(true);
			inputPhoneDialog.show();
			break;
			
		default:
			break;
		}
		
	}
}
