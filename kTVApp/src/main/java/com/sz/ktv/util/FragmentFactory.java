package com.sz.ktv.util;

import java.util.HashMap;
import java.util.Map;

import android.support.v4.app.Fragment;

import com.sz.ktv.ui.fragment.HomeBottomDaoHangFragment;
import com.sz.ktv.ui.fragment.HomeCenterFragment;
import com.sz.ktv.ui.fragment.HomeGeMingFragment;
import com.sz.ktv.ui.fragment.HomeGexingFragment;
import com.sz.ktv.ui.fragment.HomeLeibieFragment;
import com.sz.ktv.ui.fragment.HomePaiHangFragment;
import com.sz.ktv.ui.fragment.HomeReMenFragment;
import com.sz.ktv.ui.fragment.HomeShouCangFragment;
import com.sz.ktv.ui.fragment.HomeSystemSetFragment;
import com.sz.ktv.ui.fragment.HomeWuquFragment;
import com.sz.ktv.ui.fragment.SetChangePasswordFragment;
import com.sz.ktv.ui.fragment.SetGeDanHuanYuanFragment;
import com.sz.ktv.ui.fragment.SetGongBoFragment;
import com.sz.ktv.ui.fragment.SetLogoFragment;
import com.sz.ktv.ui.fragment.SetPingfenFragment;
import com.sz.ktv.ui.fragment.SetShengJiFragment;
import com.sz.ktv.ui.fragment.SetShuchuFragment;
import com.sz.ktv.ui.fragment.SetSongAddFragment;
import com.sz.ktv.ui.fragment.SetSongDelteFragment;
import com.sz.ktv.ui.fragment.SetSongXiuGaiFragment;
import com.sz.ktv.ui.fragment.SetUPanAddListFragment;
import com.sz.ktv.ui.fragment.SetUPanAddSongFragment;
import com.sz.ktv.ui.fragment.SetUPanBoGeFragment;
import com.sz.ktv.ui.fragment.SetWiFiConfigFragment;
import com.sz.ktv.ui.fragment.SetZimuFragment;

public class FragmentFactory {

	/**
	 * 首页
	 */
	public static final int FRAGMENT_HOME_CENTER = 0;
	/**
	 * 首页-歌星
	 */
	public static final int FRAGMENT_HOME_GEXING = 1;
	/**
	 * 首页-类别
	 */
	public static final int FRAGMENT_HOME_LEIBIE = 2;
	/**
	 *首页- 排行
	 */
	public static final int FRAGMENT_HOME_PAIHANG = 3;
	/**
	 * 首页-舞曲
	 */
	public static final int FRAGMENT_HOME_WUQU = 4; 
	/**
	 * 首页-设置
	 */
	public static final int FRAGMENT_HOME_SYSTEM_SET = 5;
	/**
	 * 首页-热门
	 */
	public static final int FRAGMENT_HOME_REMEN = 6;
	/**
	 *首页- 收藏
	 */
	public static final int FRAGMENT_HOME_SHOUCANG = 7;
	/**
	 * 首页-底部-导航
	 */
	public static final int FRAGMENT_HOME_DAOHANG =8;
	/**
	 * 设置-修改密码
	 */
	public static final int FRAGMENT_SET_CHANGE_PASSWORD =9;
	/**
	 * 设置-字幕
	 */
	public static final int FRAGMENT_SET_ZIMU =10;
	/**
	 * 设置-评分设置
	 */
	public static final int FRAGMENT_SET_PINGFEN =11;
	/**
	 * 设置-输出模式
	 */
	public static final int FRAGMENT_SET_SHUCHU =12;
	/**
	 * 设置-公播设置
	 */
	public static final int FRAGMENT_SET_GONGBO =14;
	/**
	 * 设置-U盘加歌
	 */
	public static final int FRAGMENT_SET_U_PAN_ADD_SONG =15;
	
	/**
	 * 设置-歌曲删除
	 */
	public static final int FRAGMENT_SET_SONG_DELETE =16;
	/**
	 * 设置-LOGO修改
	 */
	public static final int FRAGMENT_SET_LOGO =17;
	/**
	 * 设置-U盘播歌
	 */
	public static final int FRAGMENT_SET_U_PAN_BOGE =18;
	/**
	 * 设置-升级
	 */
	public static final int FRAGMENT_SET_SHENGJI =19;
	/**
	 * 设置-还原歌单
	 */
	public static final int FRAGMENT_SET_GEDAN_HUANYUAN =20;
	/**
	 * 设置-网络设置
	 */
	public static final int FRAGMENT_SET_WIFI_CONFIG =21;
	/**
	 * 設置-歌曲修改
	 */
	public static final int FRAGMENT_SET_SONG_XIUGAI = 22;
	/**
	 * 单曲加歌列表
	 */
	public static final int FRAGMENT_ADD_LIST = 23;
	/**
	 * 单曲加歌界面
	 */
	public static final int FRAGMENT_ADD_SONG=24;
	
	/**
	 * 首页-歌名
	 */
	public static final int FRAGMENT_HOME_GEMING =13;
	
    public static Map<Integer, Fragment> mFragments = new HashMap<Integer, Fragment>();

    public static Fragment createFragment(int position)
    {
        Fragment fragment = null;
        fragment = mFragments.get(position);  // t
        if(fragment == null)   // 
        {
           switch (position) {
		case FRAGMENT_HOME_CENTER:
			 fragment = new HomeCenterFragment();
			break;
		case FRAGMENT_HOME_GEXING:
			 fragment = new HomeGexingFragment();
			break;
		case FRAGMENT_HOME_LEIBIE:
			 fragment = new HomeLeibieFragment();
			break;
		case FRAGMENT_HOME_PAIHANG:
			 fragment = new HomePaiHangFragment();
			break;
		case FRAGMENT_HOME_WUQU:
			 fragment = new HomeWuquFragment();
			break;
		case FRAGMENT_HOME_SYSTEM_SET:
			 fragment = new HomeSystemSetFragment();
			break;
		case FRAGMENT_HOME_REMEN:
			fragment = new HomeReMenFragment();
			break;
		case FRAGMENT_HOME_SHOUCANG:
			fragment = new HomeShouCangFragment();
			break;
		case FRAGMENT_HOME_DAOHANG:
			fragment = new HomeBottomDaoHangFragment();
			break;
		case FRAGMENT_SET_CHANGE_PASSWORD:
			fragment = new SetChangePasswordFragment();
			break;
		case FRAGMENT_SET_ZIMU:
			fragment = new SetZimuFragment();
			break;
		case FRAGMENT_SET_PINGFEN:
			fragment = new SetPingfenFragment();
			break;
		case FRAGMENT_SET_SHUCHU:
			fragment = new SetShuchuFragment();
			break;
		case FRAGMENT_HOME_GEMING:
			fragment = new HomeGeMingFragment();
			break;
		case FRAGMENT_SET_U_PAN_ADD_SONG:
			fragment = new SetUPanAddSongFragment();
			break;
		case FRAGMENT_SET_SONG_DELETE:
			fragment = new SetSongDelteFragment();
			break;
		case FRAGMENT_SET_GONGBO:
			fragment = new SetGongBoFragment();
			break;
		case FRAGMENT_SET_LOGO:
			fragment = new SetLogoFragment();
			break;
		case FRAGMENT_SET_U_PAN_BOGE:
			fragment = new SetUPanBoGeFragment();
			break;
			
		case FRAGMENT_SET_SHENGJI:
			fragment = new SetShengJiFragment();
			break;
		case FRAGMENT_SET_GEDAN_HUANYUAN:
			fragment = new SetGeDanHuanYuanFragment();
			break;
		case FRAGMENT_SET_WIFI_CONFIG:
			fragment = new SetWiFiConfigFragment();
			break;
		case FRAGMENT_SET_SONG_XIUGAI:
			fragment = new SetSongXiuGaiFragment();
			break;
		case FRAGMENT_ADD_LIST:
			fragment = new SetUPanAddListFragment();
			break;
		case FRAGMENT_ADD_SONG:
			fragment = new SetSongAddFragment();
			break;
		default:
			break;
		}
//            if(fragment != null)
//            {
//                mFragments.put(position, fragment);
//            }
        }
        return fragment;

    }
}