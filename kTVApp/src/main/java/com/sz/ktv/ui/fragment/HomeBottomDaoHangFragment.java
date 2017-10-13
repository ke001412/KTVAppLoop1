package com.sz.ktv.ui.fragment;

import org.greenrobot.eventbus.EventBus;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.sz.ktv.R;
import com.sz.ktv.db.DataBase;
import com.sz.ktv.msg.AsyncMessage;
import com.sz.ktv.msg.SingerTypeMessage;
import com.sz.ktv.msg.SongTypeMessage;
import com.sz.ktv.ui.base.BaseFragment;
import com.sz.ktv.util.FragmentFactory;
import com.sz.ktv.view.widget.KtvBottomPageWidget.BackClickListener;

public class HomeBottomDaoHangFragment extends BaseFragment implements OnClickListener,
		BackClickListener {

	private Button gemingGuoyu;
	private Button gemingMinnanyu;
	private Button gemingRiyu;
	private Button gemingQita;
	private Button gemingYueyu;
	private Button gemingHanyu;
	private Button gemingQuanbu;
	private Button gegmingYingyu;

	private Button gexingDalunan;
	private Button gexingGangtainan;
	private 	Button gexingHaiwaiNan;
	private Button gexingYueduizuhe;
	private Button gexingDalunv;
	private Button gexingGangtainv;
	private Button gexingHaiwainv;
	private Button gexingQuanbu;

	private Button paihangZong;
	private Button paihangMinnanyu;
	private Button paihangGuoyu;
	private Button paihangXinge;
	private Button paihangyue;
	private Button paihangZhou;
	private Button paihangYueyu;

	private Button remenZGHSY;
	private Button remenWSGS;
	private Button remenZMHS;
	private Button remenZGZQY;
	private Button remenZGHGQ;
	private Button remenZGYC;
	private Button remenMXXDD;
	private Button remenMMGW;

	private Button quzhongSRGQ;
	private Button quzhongGMGQ;
	private Button quzhongHJGQ;
	private Button quzhongHCGQ;
	private Button quzhongXQXS;
	private Button quzhongETGQ;
	private Button quzhongXQGQ;
	private Button quzhongXSXP;

	private Button wuquMY;
	private Button wuquHEZ;
	private Button wuquDSG;
	private Button wuquCS;
	private Button wuquQQ;
	private Button wuquPLS;
	private Button wuquJTB;
	private Button wuquTG;

	private Button dianyingDZ;
	private Button dianyingXJ;
	private Button dianyingGZ;
	private Button dianyingFJ;
	private Button dianyingKH;
	private Button dianyingZZ;
	private Button dianyingDH;
	private Button dianyingQT;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = null;
		try {
			view = inflater.inflate(R.layout.home_daohang_layout, container,
					false);
		} catch (Exception e) {
			e.printStackTrace();
		}

		initView(view);
		return view;
	}

	private void initView(View view) {

		gemingGuoyu = (Button) view.findViewById(R.id.daohang_geming_guoyu);
		gemingMinnanyu = (Button) view
				.findViewById(R.id.daohang_geming_minnanyu);
		gemingRiyu = (Button) view.findViewById(R.id.daohang_geming_riyu);
		gemingQita = (Button) view.findViewById(R.id.daohang_geming_qita);
		gemingYueyu = (Button) view.findViewById(R.id.daohang_geming_yueyu);
		gemingHanyu = (Button) view.findViewById(R.id.daohang_geming_hanyu);
		gemingQuanbu = (Button) view.findViewById(R.id.daohang_geming_quanbu);
		gegmingYingyu = (Button) view.findViewById(R.id.daohang_geming_yingyu);
		gegmingYingyu.setOnClickListener(this);
		gemingGuoyu.setOnClickListener(this);
		gemingMinnanyu.setOnClickListener(this);
		gemingRiyu.setOnClickListener(this);
		gemingQita.setOnClickListener(this);
		gemingYueyu.setOnClickListener(this);
		gemingHanyu.setOnClickListener(this);
		gemingQuanbu.setOnClickListener(this);

		gexingDalunan = (Button) view
				.findViewById(R.id.daohang_gexing_dalu_nan);
		gexingGangtainan = (Button) view
				.findViewById(R.id.daohang_gexing_gangtai_nan);
		gexingHaiwaiNan = (Button) view
				.findViewById(R.id.daohang_gexing_haiwai_nan);
		gexingYueduizuhe = (Button) view
				.findViewById(R.id.daohang_gexing_yuedui_zuhe);
		gexingDalunv = (Button) view.findViewById(R.id.daohang_gexing_dalu_nv);
		gexingGangtainv = (Button) view
				.findViewById(R.id.daohang_gexing_gangtai_nv);
		gexingHaiwainv = (Button) view
				.findViewById(R.id.daohang_gexing_haiwai_nv);
		gexingQuanbu = (Button) view.findViewById(R.id.daohang_gexing_quanbu);
		gexingDalunan.setOnClickListener(this);
		gexingGangtainan.setOnClickListener(this);
		gexingHaiwaiNan.setOnClickListener(this);
		gexingYueduizuhe.setOnClickListener(this);
		gexingDalunv.setOnClickListener(this);
		gexingGangtainv.setOnClickListener(this);
		gexingHaiwainv.setOnClickListener(this);
		gexingQuanbu.setOnClickListener(this);

		paihangZong = (Button) view.findViewById(R.id.daohang_paihang_zong);
		paihangMinnanyu = (Button) view
				.findViewById(R.id.daohang_paihang_minnanyu);
		paihangGuoyu = (Button) view.findViewById(R.id.daohang_paihang_guoyu);
		paihangXinge = (Button) view.findViewById(R.id.daohang_paihang_xinge);
		paihangyue = (Button) view.findViewById(R.id.daohang_paihang_yue);
		paihangZhou = (Button) view.findViewById(R.id.daohang_paihang_zhou);
		paihangYueyu = (Button) view.findViewById(R.id.daohang_paihang_yueyu);
		paihangZong.setOnClickListener(this);
		paihangMinnanyu.setOnClickListener(this);
		paihangGuoyu.setOnClickListener(this);
		paihangXinge.setOnClickListener(this);
		paihangyue.setOnClickListener(this);
		paihangZhou.setOnClickListener(this);
		paihangYueyu.setOnClickListener(this);

		remenZGHSY = (Button) view.findViewById(R.id.daohang_remen_zghsy);
		remenWSGS = (Button) view.findViewById(R.id.daohang_remen_wsgs);
		remenZMHS = (Button) view.findViewById(R.id.daohang_remen_zmhs);
		remenZGZQY = (Button) view.findViewById(R.id.daohang_remen_zgzqy);
		remenZGHGQ = (Button) view.findViewById(R.id.daohang_remen_zghgq);
		remenZGYC = (Button) view.findViewById(R.id.daohang_remen_zgyc);
		remenMXXDD = (Button) view.findViewById(R.id.daohang_remen_mxxdd);
		remenMMGW = (Button) view.findViewById(R.id.daohang_remen_mmgw);
		remenZGHSY.setOnClickListener(this);
		remenWSGS.setOnClickListener(this);
		remenZMHS.setOnClickListener(this);
		remenZGZQY.setOnClickListener(this);
		remenZGHGQ.setOnClickListener(this);
		remenZGYC.setOnClickListener(this);
		remenMXXDD.setOnClickListener(this);
		remenMMGW.setOnClickListener(this);

		quzhongSRGQ = (Button) view.findViewById(R.id.daohang_quzhong_srgq);
		quzhongGMGQ = (Button) view.findViewById(R.id.daohang_quzhong_gmgq);
		quzhongHJGQ = (Button) view.findViewById(R.id.daohang_quzhong_hjgq);
		quzhongHCGQ = (Button) view.findViewById(R.id.daohang_quzhong_hcgq);
		quzhongXQXS = (Button) view.findViewById(R.id.daohang_quzhong_xqxs);
		quzhongETGQ = (Button) view.findViewById(R.id.daohang_quzhong_etgq);
		quzhongXQGQ = (Button) view.findViewById(R.id.daohang_quzhong_xqgq);
		quzhongXSXP = (Button) view.findViewById(R.id.daohang_quzhong_xsxp);
		quzhongSRGQ.setOnClickListener(this);
		quzhongGMGQ.setOnClickListener(this);
		quzhongHJGQ.setOnClickListener(this);
		quzhongHCGQ.setOnClickListener(this);
		quzhongXQXS.setOnClickListener(this);
		quzhongETGQ.setOnClickListener(this);
		quzhongXQGQ.setOnClickListener(this);
		quzhongXSXP.setOnClickListener(this);

		wuquMY = (Button) view.findViewById(R.id.daohang_wuqu_my);
		wuquHEZ = (Button) view.findViewById(R.id.daohang_wuqu_hrz);
		wuquDSG = (Button) view.findViewById(R.id.daohang_wuqu_dsg);
		wuquCS = (Button) view.findViewById(R.id.daohang_wuqu_cs);
		wuquQQ = (Button) view.findViewById(R.id.daohang_wuqu_qq);
		wuquPLS = (Button) view.findViewById(R.id.daohang_wuqu_pls);
		wuquJTB = (Button) view.findViewById(R.id.daohang_wuqu_jtb);
		wuquTG = (Button) view.findViewById(R.id.daohang_wuqu_tg);
		wuquMY.setOnClickListener(this);
		wuquHEZ.setOnClickListener(this);
		wuquDSG.setOnClickListener(this);
		wuquCS.setOnClickListener(this);
		wuquQQ.setOnClickListener(this);
		wuquPLS.setOnClickListener(this);
		wuquJTB.setOnClickListener(this);
		wuquTG.setOnClickListener(this);

		dianyingDZ = (Button) view.findViewById(R.id.daohang_dianying_dz);
		dianyingXJ = (Button) view.findViewById(R.id.daohang_dianying_xj);
		dianyingGZ = (Button) view.findViewById(R.id.daohang_dianying_gz);
		dianyingFJ = (Button) view.findViewById(R.id.daohang_dianying_fj);
		dianyingKH = (Button) view.findViewById(R.id.daohang_dianying_kh);
		dianyingZZ = (Button) view.findViewById(R.id.daohang_dianying_zz);
		dianyingDH = (Button) view.findViewById(R.id.daohang_dianying_dh);
		dianyingQT = (Button) view.findViewById(R.id.daohang_dianying_qt);
		dianyingDZ.setOnClickListener(this);
		dianyingXJ.setOnClickListener(this);
		dianyingGZ.setOnClickListener(this);
		dianyingFJ.setOnClickListener(this);
		dianyingKH.setOnClickListener(this);
		dianyingZZ.setOnClickListener(this);
		dianyingDH.setOnClickListener(this);
		dianyingQT.setOnClickListener(this);
	}

	@Override
	public void back() {

	}

	@Override
	public void onClick(View v) {

//		String keyString = "";
//		String preTitle="";
		
		
		int id = v.getId();
		switch (id) {
		case R.id.daohang_geming_guoyu:
			
			EventBus.getDefault().post(
					new AsyncMessage(FragmentFactory.FRAGMENT_HOME_GEMING));
			SongTypeMessage guoyu = new SongTypeMessage();
			guoyu.setType(HomeGeMingFragment.TYPE_SONG_GUOYU);
			guoyu.setReturnType(HomeGeMingFragment.RETURN_TYPE_DAOHANG);
			EventBus.getDefault().post(guoyu);
			
			break;
		case R.id.daohang_geming_minnanyu:
			EventBus.getDefault().post(
					new AsyncMessage(FragmentFactory.FRAGMENT_HOME_GEMING));
			SongTypeMessage minnanyu = new SongTypeMessage();
			minnanyu.setType(HomeGeMingFragment.TYPE_SONG_MINNANYU);
			minnanyu.setReturnType(HomeGeMingFragment.RETURN_TYPE_DAOHANG);
			EventBus.getDefault().post(minnanyu);
			
			break;
		case R.id.daohang_geming_riyu:
			EventBus.getDefault().post(
					new AsyncMessage(FragmentFactory.FRAGMENT_HOME_GEMING));
			SongTypeMessage riyu = new SongTypeMessage();
			riyu.setType(HomeGeMingFragment.TYPE_SONG_RIYU);
			riyu.setReturnType(HomeGeMingFragment.RETURN_TYPE_DAOHANG);
			EventBus.getDefault().post(riyu);
			
			break;
		case R.id.daohang_geming_qita:

			EventBus.getDefault().post(
					new AsyncMessage(FragmentFactory.FRAGMENT_HOME_GEMING));
			SongTypeMessage qita = new SongTypeMessage();
			qita.setType(HomeGeMingFragment.TYPE_SONG_QITA);
			qita.setReturnType(HomeGeMingFragment.RETURN_TYPE_DAOHANG);
			EventBus.getDefault().post(qita);
			
			
			break;
		case R.id.daohang_geming_yueyu:

			EventBus.getDefault().post(
					new AsyncMessage(FragmentFactory.FRAGMENT_HOME_GEMING));
			SongTypeMessage yueyu = new SongTypeMessage();
			yueyu.setType(HomeGeMingFragment.TYPE_SONG_YUEYU);
			yueyu.setReturnType(HomeGeMingFragment.RETURN_TYPE_DAOHANG);
			EventBus.getDefault().post(yueyu);
			
			break;
		case R.id.daohang_geming_hanyu:

			EventBus.getDefault().post(
					new AsyncMessage(FragmentFactory.FRAGMENT_HOME_GEMING));
			SongTypeMessage hanyu = new SongTypeMessage();
			hanyu.setType(HomeGeMingFragment.TYPE_SONG_HANYU);
			hanyu.setReturnType(HomeGeMingFragment.RETURN_TYPE_DAOHANG);
			EventBus.getDefault().post(hanyu);
			
			break;
		case R.id.daohang_geming_quanbu:
			EventBus.getDefault().post(
					new AsyncMessage(FragmentFactory.FRAGMENT_HOME_GEMING));
			SongTypeMessage quanbu = new SongTypeMessage();
			quanbu.setType(HomeGeMingFragment.TYPE_SONG_QUANBU);
			quanbu.setReturnType(HomeGeMingFragment.RETURN_TYPE_DAOHANG);
			EventBus.getDefault().post(quanbu);

			break;
		case R.id.daohang_geming_yingyu:
			EventBus.getDefault().post(
					new AsyncMessage(FragmentFactory.FRAGMENT_HOME_GEMING));
			SongTypeMessage yingyu = new SongTypeMessage();
			yingyu.setType(HomeGeMingFragment.TYPE_SONG_YINGYU);
			yingyu.setReturnType(HomeGeMingFragment.RETURN_TYPE_DAOHANG);
			EventBus.getDefault().post(yingyu);

			break;
		case R.id.daohang_gexing_dalu_nan:

			EventBus.getDefault().post(
					new AsyncMessage(FragmentFactory.FRAGMENT_HOME_GEXING));
			SingerTypeMessage dalunan = new SingerTypeMessage();
			dalunan.setSingerType(HomeGexingFragment.TYPE_SINGER_DALUNAN);
			dalunan.setReturnType(HomeGexingFragment.RETURN_TYPE_DAOHANG);
			EventBus.getDefault().post(dalunan);

			
			break;
		case R.id.daohang_gexing_dalu_nv:
			EventBus.getDefault().post(
					new AsyncMessage(FragmentFactory.FRAGMENT_HOME_GEXING));
			SingerTypeMessage dalunv = new SingerTypeMessage();
			dalunv.setSingerType(HomeGexingFragment.TYPE_SINGER_DALUNV);
			dalunv.setReturnType(HomeGexingFragment.RETURN_TYPE_DAOHANG);
			EventBus.getDefault().post(dalunv);
			break;
		case R.id.daohang_gexing_gangtai_nan:

			EventBus.getDefault().post(
					new AsyncMessage(FragmentFactory.FRAGMENT_HOME_GEXING));
			SingerTypeMessage gangtainan = new SingerTypeMessage();
			gangtainan.setSingerType(HomeGexingFragment.TYPE_SINGER_GANGYAINAN);
			gangtainan.setReturnType(HomeGexingFragment.RETURN_TYPE_DAOHANG);
			EventBus.getDefault().post(gangtainan);
			
			break;
		case R.id.daohang_gexing_gangtai_nv:

			EventBus.getDefault().post(
					new AsyncMessage(FragmentFactory.FRAGMENT_HOME_GEXING));
			SingerTypeMessage gangtainv = new SingerTypeMessage();
			gangtainv.setSingerType(HomeGexingFragment.TYPE_SINGER_GANGTAINV);
			gangtainv.setReturnType(HomeGexingFragment.RETURN_TYPE_DAOHANG);
			EventBus.getDefault().post(gangtainv);
			
			break;
		case R.id.daohang_gexing_haiwai_nan:


			EventBus.getDefault().post(
					new AsyncMessage(FragmentFactory.FRAGMENT_HOME_GEXING));
			SingerTypeMessage haiwainan = new SingerTypeMessage();
			haiwainan.setSingerType(HomeGexingFragment.TYPE_SINGER_HAIWAINAN);
			haiwainan.setReturnType(HomeGexingFragment.RETURN_TYPE_DAOHANG);
			EventBus.getDefault().post(haiwainan);
			
			break;
		case R.id.daohang_gexing_haiwai_nv:

			EventBus.getDefault().post(
					new AsyncMessage(FragmentFactory.FRAGMENT_HOME_GEXING));
			SingerTypeMessage haiwainv = new SingerTypeMessage();
			haiwainv.setSingerType(HomeGexingFragment.TYPE_SINGER_HAIWAINV);
			haiwainv.setReturnType(HomeGexingFragment.RETURN_TYPE_DAOHANG);
			EventBus.getDefault().post(haiwainv);
			
			break;
		case R.id.daohang_gexing_yuedui_zuhe:

			EventBus.getDefault().post(
					new AsyncMessage(FragmentFactory.FRAGMENT_HOME_GEXING));
			SingerTypeMessage yueduizuhe = new SingerTypeMessage();
			yueduizuhe.setSingerType(HomeGexingFragment.TYPE_SINGER_LUEDUI);
			yueduizuhe.setReturnType(HomeGexingFragment.RETURN_TYPE_DAOHANG);
			EventBus.getDefault().post(yueduizuhe);
			
			break;
		case R.id.daohang_gexing_quanbu:
			EventBus.getDefault().post(
					new AsyncMessage(FragmentFactory.FRAGMENT_HOME_GEXING));
			SingerTypeMessage quanbugexing = new SingerTypeMessage();
			quanbugexing.setSingerType(HomeGexingFragment.TYPE_SINGER_QUANBU);
			quanbugexing.setReturnType(HomeGexingFragment.RETURN_TYPE_DAOHANG);
			EventBus.getDefault().post(quanbugexing);
			
			
			break;
		case R.id.daohang_paihang_zong:

			break;
		case R.id.daohang_paihang_yue:

			break;
		case R.id.daohang_paihang_zhou:

			break;
		case R.id.daohang_paihang_yueyu:

			break;
		case R.id.daohang_paihang_minnanyu:

			break;
		case R.id.daohang_paihang_guoyu:

			break;
		case R.id.daohang_paihang_xinge:

			break;
		case R.id.daohang_remen_zghsy:
			HomeReMenFragment.gotoGeming(HomeGeMingFragment.TYPE_LIUXING_ZGHSY);
			break;
		case R.id.daohang_remen_wsgs:
			HomeReMenFragment.gotoGeming(HomeGeMingFragment.TYPE_LIUXING_WSGS);

			break;
		case R.id.daohang_remen_zmhs:
			HomeReMenFragment.gotoGeming(HomeGeMingFragment.TYPE_LIUXING_ZMHS);

			break;
		case R.id.daohang_remen_zgzqy:
			HomeReMenFragment.gotoGeming(HomeGeMingFragment.TYPE_LIUXING_ZGZQY);

			break;
		case R.id.daohang_remen_zghgq:
			HomeReMenFragment.gotoGeming(HomeGeMingFragment.TYPE_LIUXING_ZGHGQ);

			break;
		case R.id.daohang_remen_zgyc:
			HomeReMenFragment.gotoGeming(HomeGeMingFragment.TYPE_LIUXING_ZGYC);

			break;
		case R.id.daohang_remen_mxxdd:
			HomeReMenFragment.gotoGeming(HomeGeMingFragment.TYPE_LIUXING_MXXDD);

			break;
		case R.id.daohang_remen_mmgw:
			HomeReMenFragment.gotoGeming(HomeGeMingFragment.TYPE_LIUXING_MMGW);

			break;
		case R.id.daohang_quzhong_srgq:
			HomeLeibieFragment.gotoGeming(HomeGeMingFragment.TYPE_LEIBIE_SRGQ);

			break;
		case R.id.daohang_quzhong_gmgq:
			HomeLeibieFragment.gotoGeming(HomeGeMingFragment.TYPE_LEIBIE_GMGQ);

			break;
		case R.id.daohang_quzhong_hjgq:
			HomeLeibieFragment.gotoGeming(HomeGeMingFragment.TYPE_LEIBIE_HJGQ);
			break;
		case R.id.daohang_quzhong_hcgq:
			HomeLeibieFragment.gotoGeming(HomeGeMingFragment.TYPE_LEIBIE_HCGQ);
			break;
		case R.id.daohang_quzhong_xqxs:
			HomeLeibieFragment.gotoGeming(HomeGeMingFragment.TYPE_LEIBIE_XQXS);
			break;
		case R.id.daohang_quzhong_etgq:
			HomeLeibieFragment.gotoGeming(HomeGeMingFragment.TYPE_LEIBIE_ETGQ);
			break;
		case R.id.daohang_quzhong_xqgq:
			HomeLeibieFragment.gotoGeming(HomeGeMingFragment.TYPE_LEIBIE_XQGQ);
			break;
		case R.id.daohang_quzhong_xsxp:
			HomeLeibieFragment.gotoGeming(HomeGeMingFragment.TYPE_LEIBIE_XSXP);
			break;
		case R.id.daohang_wuqu_my:
			HomeWuquFragment.gotoGeming(HomeGeMingFragment.TYPE_WUQU_MY);
			break;
		case R.id.daohang_wuqu_hrz:
			HomeWuquFragment.gotoGeming(HomeGeMingFragment.TYPE_WUQU_HEZ);
			break;
		case R.id.daohang_wuqu_dsg:
			HomeWuquFragment.gotoGeming(HomeGeMingFragment.TYPE_WUQU_DSG);
			break;
		case R.id.daohang_wuqu_cs:
			HomeWuquFragment.gotoGeming(HomeGeMingFragment.TYPE_WUQU_CS);
			break;
		case R.id.daohang_wuqu_qq:
			HomeWuquFragment.gotoGeming(HomeGeMingFragment.TYPE_WUQU_QQ);
			break;
		case R.id.daohang_wuqu_pls:
			HomeWuquFragment.gotoGeming(HomeGeMingFragment.TYPE_WUQU_PLS);
			break;
		case R.id.daohang_wuqu_jtb:
			HomeWuquFragment.gotoGeming(HomeGeMingFragment.TYPE_WUQU_JTB);
			break;
		case R.id.daohang_wuqu_tg:
			HomeWuquFragment.gotoGeming(HomeGeMingFragment.TYPE_WUQU_TG);
			break;
		case R.id.daohang_dianying_dz:
			gotoGeming(HomeGeMingFragment.TYPE_DY_DZ);
			break;
		case R.id.daohang_dianying_xj:
			gotoGeming(HomeGeMingFragment.TYPE_DY_XJ);
			break;
		case R.id.daohang_dianying_gz:
			gotoGeming(HomeGeMingFragment.TYPE_DY_GZ);
			break;
		case R.id.daohang_dianying_fj:
			gotoGeming(HomeGeMingFragment.TYPE_DY_FJ);
			break;
		case R.id.daohang_dianying_kh:
			gotoGeming(HomeGeMingFragment.TYPE_DY_KH);
			break;
		case R.id.daohang_dianying_zz:
			gotoGeming(HomeGeMingFragment.TYPE_DY_ZZ);
			break;
		case R.id.daohang_dianying_dh:
			gotoGeming(HomeGeMingFragment.TYPE_DY_DH);
			break;
		case R.id.daohang_dianying_qt:
			gotoGeming(HomeGeMingFragment.TYPE_DY_QT);
			break;
			
		default:
			break;
		}
	}

	
public static void gotoGeming(int type) {
		
		String keyString = "";
		String preTitle="";
		
		switch (type) {
		case HomeGeMingFragment.TYPE_DY_DZ:
			keyString=DataBase.SONG_DIANYING_DZ;
			preTitle="点歌->电影->动作->";
			break;
		case HomeGeMingFragment.TYPE_DY_XJ:
			keyString=DataBase.SONG_DIANYING_XJ;
			preTitle="点歌->电影->喜剧->";
			break;
		case HomeGeMingFragment.TYPE_DY_GZ:
			keyString=DataBase.SONG_DIANYING_GZ;
			preTitle="点歌->电影->古装->";
			break;
		case HomeGeMingFragment.TYPE_DY_FJ:
			keyString=DataBase.SONG_DIANYING_FJ;
			preTitle="点歌->电影->匪警->";
			break;
		case HomeGeMingFragment.TYPE_DY_KH:
			keyString=DataBase.SONG_DIANYING_KH;
			preTitle="点歌->电影->科幻->";
			break;
		case HomeGeMingFragment.TYPE_DY_ZZ:
			keyString=DataBase.SONG_DIANYING_ZZ;
			preTitle="点歌->电影->战争->";
			break;
		case HomeGeMingFragment.TYPE_DY_DH:
			keyString=DataBase.SONG_DIANYING_DH;
			preTitle="点歌->电影->动画->";
			break;
		case HomeGeMingFragment.TYPE_DY_QT:
			keyString=DataBase.SONG_DIANYING_QT;
			preTitle="点歌->电影->其他->";
			break;
		default:
			break;
		}
		SongTypeMessage message = new SongTypeMessage();
		message.setType(type);
		message.setKeyWords(keyString);
		message.setPreTileText(preTitle);
		message.setReturnType(HomeGeMingFragment.RETURN_TYPE_DAOHANG);
		
		EventBus.getDefault().post(new AsyncMessage(FragmentFactory.FRAGMENT_HOME_GEMING));
		EventBus.getDefault().post(message);
	}
}
