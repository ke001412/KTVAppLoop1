package com.sz.ktv.ui.fragment.adapter;

import java.io.File;
import java.util.List;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sz.ktv.R;

public class FileAdapter extends BaseAdapter {

	
	List<File> fileList;
	Activity mContext;
	
	public FileAdapter(List<File> mFileList,Activity context)
	{
		fileList = mFileList;
		mContext = context;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		
		return fileList.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return fileList.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		final View view = mContext.getLayoutInflater().inflate(R.layout.file_item,
				null);
		TextView filenameTv = (TextView)view.findViewById(R.id.filename);
		TextView filepathTv = (TextView)view.findViewById(R.id.filepath);
		File file = fileList.get(arg0);
		String filename = file.getName();
		String filepath = file.getPath();
		
		filenameTv.setText(filename);
		filepathTv.setText(filepath);
		
		return view;
	}
 

}
