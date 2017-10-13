package com.sz.ktv.ui.fragment.adapter;

import java.io.File;
import java.util.List;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sz.ktv.R;
import com.sz.ktv.db.DataBase;
import com.sz.ktv.db.Singer;
import com.sz.ktv.intf.GridAdatper;
import com.sz.ktv.util.GetHeadUtil;

public class AdapterGexing implements GridAdatper {

	
	private List<Singer> list;
	private Activity mActivity ;
	
	public AdapterGexing(Activity activity , List<Singer> mList){
		this.list = mList;
		mActivity = activity;
	}
	
	public void setData( List<Singer> mList)
	{
		this.list = mList;
	}
	@Override
	public View getView(int index) {
		View view = mActivity.getLayoutInflater().inflate(R.layout.gexing_item,
				null);
		ImageView iv = (ImageView) view.findViewById(R.id.singer_img);
		TextView tv = (TextView) view.findViewById(R.id.singer_name);
		Singer singer = list.get(index);
		
		String singerName = singer.getSinger_name();
		String singerId  = singer.getSinger_no();
		String headFilePath = DataBase.MNT_SDA_SINGER_PATH+singer.getSinger_type()+"/"+singer.getSinger_type()+""+singerId+".jpg";
		File file = new File(headFilePath);
		System.out.println("图片路径:"+ headFilePath);
		Glide.with(mActivity).load(file).into(iv);
		//		iv.setBackgroundDrawable(GetHeadUtil.getHead(singer.getSinger_type(),mActivity, singerId));
//		iv.setImageBitmap(GetHeadUtil.getHeadBitmap(mActivity, singerId));
		tv.setText(singerName);
		view.setTag(singer);
		
		return view;
	}

	@Override
	public int getCount() {
		if(null == list || list.size() <=0)
		{
			return 0;
		}
		return list.size();
	}

	@Override
	public void update() {

	}

}
