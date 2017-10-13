package com.sz.ktv.ui.fragment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.greenrobot.eventbus.EventBus;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.sz.ktv.R;
import com.sz.ktv.db.DataBase;
import com.sz.ktv.msg.AsyncMessage;
import com.sz.ktv.ui.base.BaseFragment;
import com.sz.ktv.ui.fragment.adapter.FileAdapter;
import com.sz.ktv.util.FragmentFactory;
import com.sz.ktv.view.widget.KtvBottomPageWidget;
import com.sz.ktv.view.widget.KtvBottomPageWidget.BackClickListener;

/**
 * 单曲加歌界面
 * 
 * @author zhuxl
 * 
 */
public class SetUPanAddListFragment extends BaseFragment implements
		OnClickListener, BackClickListener {

	KtvBottomPageWidget bottomPage;
	ListView fileListView;
	private static final int MSG_FILE_LIST = 1;
	FileAdapter adapter;
	List<File> fileList = new ArrayList<File>();

	Handler handler = new Handler() {
		public void dispatchMessage(android.os.Message msg) {
			int what = msg.what;
			switch (what) {
			case MSG_FILE_LIST:

				adapter = new FileAdapter(fileList, getActivity());
				fileListView.setAdapter(adapter);

				break;

			default:
				break;
			}
		};

	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = null;
		try {
			view = inflater.inflate(R.layout.system_upan_add_list_layout,
					container, false);
		} catch (Exception e) {
			e.printStackTrace();
		}

		initView(view);
		new Thread() {
			public void run() {
				getFiles(DataBase.MNT_SDA_U_PAN_DIR, true, new String[]{"mkv","avi","mpg"});
				handler.sendEmptyMessage(MSG_FILE_LIST);
			};
		}.start();
		return view;
	}

	public void getFiles(String path, boolean isIterative, String... extension) // 搜索目录，扩展名，是否进入子文件夹
	{

		File[] files = new File(path).listFiles();
		if(null == files)
		{
			return;
		}
		int length = files.length;
		for (int i = 0; i < length ; i++)

		{
			File f = files[i];
			if (f.isFile()) {
				String fileName = f.getName();
				String prefix = fileName
						.substring(fileName.lastIndexOf(".") + 1);
				 
				if (prefix.equalsIgnoreCase(extension[0]) || prefix.equalsIgnoreCase(extension[1])
						|| prefix.equalsIgnoreCase(extension[2])) // 判断扩展名
					fileList.add(f);
				if (!isIterative)
					break;
			} else if (f.isDirectory() && f.getPath().indexOf("/.") == -1) // 忽略点文件（隐藏文件/文件夹）
				getFiles(f.getPath(), isIterative, extension);

		}

	}

	public static File currentFile;
	
	private void initView(View view) {
		bottomPage = (KtvBottomPageWidget) view
				.findViewById(R.id.sys_u_addsong_back);
		bottomPage.setBackClickListener(this);
		bottomPage.setType(KtvBottomPageWidget.TYPE_NO_DOTS_NO_PAGENUM);

		fileListView = (ListView) view.findViewById(R.id.file_list);
		fileListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				currentFile = fileList.get(arg2);
				EventBus.getDefault().post(
						new AsyncMessage(FragmentFactory.FRAGMENT_ADD_SONG));
			}
		});
	}

	@Override
	public void back() {
		EventBus.getDefault().post(
				new AsyncMessage(FragmentFactory.FRAGMENT_SET_U_PAN_ADD_SONG));
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.u_pan_add_danqu:

			break;
		case R.id.u_pan_add_piliang:

			break;
		default:
			break;
		}
	}

}
