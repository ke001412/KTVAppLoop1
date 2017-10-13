package com.sz.ktv.ui.fragment.adapter;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.greenrobot.eventbus.EventBus;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hjy.cache.AsyncImageLoader;
import com.sz.ktv.R;
import com.sz.ktv.db.DataBase;
import com.sz.ktv.db.Singer;
import com.sz.ktv.db.ktv.SongUtil;
import com.sz.ktv.msg.AsyncMessage;
import com.sz.ktv.msg.SongTypeMessage;
import com.sz.ktv.ui.fragment.HomeGeMingFragment;
import com.sz.ktv.ui.fragment.adapter.AsyncImageLoader.ImageCallback;
import com.sz.ktv.util.FragmentFactory;
import com.sz.ktv.util.Global;
import com.sz.ktv.util.LogUtil;

/**
 * 新的adapter
 *
 * @author zhuxl
 */
public class GexingAdapter extends BaseAdapter {

    List<Singer> singerList = null;
    Context mContext;
    private LayoutInflater mInflater;
    int width;
    int height;
    private ExecutorService executorService = Executors.newFixedThreadPool(5);
    private AsyncImageLoader mLoadImage;
//	private AsyncImageLoader asyncImageLoader;  

    Handler handler = new Handler();
    private static final String TAG = "GexingAdapter";

    // 引入线程池来管理多线程
    private void loadImage(final String path, final ImageView imageView) {
//	       executorService.submit(new Runnable() {  
//	            public void run() {  
//	                try {  
//	                   final Drawable drawable = Drawable.createFromStream(new FileInputStream(new File(path)), "image.png");  
//	                   handler.post(new Runnable() {  
//	  
//	                        public void run() {  
//	                        	imageView.setBackgroundDrawable(drawable);
//	                        }  
//	                   });  
//	                } catch (Exception e) {  
//	                    throw new RuntimeException(e);  
//	                }  
//	            }  
//	       });  
//		   new Thread(){
//			   public void run() {
//				   try {
//					   final Drawable drawable = Drawable.createFromStream(new FileInputStream(new File(path)), "image.png");
//					   imageView.setBackgroundDrawable(drawable);
//				} catch (Exception e) {
//					 
//				}
//				   
//				   
//			   };
//		   }.start();
    }

//	   ImageDownloader mLoadImage;

    public GexingAdapter(List<Singer> songLists, Context contexts, AsyncImageLoader mDownloader) {
        singerList = songLists;
        mContext = contexts;
        mLoadImage = mDownloader;
        mInflater = LayoutInflater.from(mContext);
        WindowManager wm = (WindowManager) contexts
                .getSystemService(Context.WINDOW_SERVICE);

        width = wm.getDefaultDisplay().getWidth();
        height = wm.getDefaultDisplay().getHeight();

    }

    @Override
    public int getCount() {
        return singerList.size();
    }

    @Override
    public Object getItem(int position) {
        return singerList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        //zhangke add 20171012 for faster begin
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = Global.currentActivity.getLayoutInflater().inflate(R.layout.gexing_item,
                    parent, false);
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            layoutParams.height = (parent.getHeight() - 40) / 2;// 计算每一行的高度
            view.setLayoutParams(layoutParams);
            viewHolder.iv =(ImageView) view.findViewById(R.id.singer_img);
            viewHolder.tv = (TextView) view.findViewById(R.id.singer_name);
            view.setTag(R.id.view_tag_1, viewHolder);
            LogUtil.log(TAG, "getview 1");
        }else{
            viewHolder = (ViewHolder) view.getTag(R.id.view_tag_1);
        }
        if (null != view && view.getHeight() == 0) {// 第一次调用getView时，parent的高度还是0,所以这里需要判断一下，并且重新设置，否则第一个子项显示不出来
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            layoutParams.height = (parent.getHeight() - 40) / 2;
            view.setLayoutParams(layoutParams);
            LogUtil.log(TAG, "getview 2");
        }

        Singer singer = singerList.get(position);

        String singerName = singer.getSinger_name();
        String singerId = singer.getSinger_no();
        String filepath = DataBase.MNT_SDA_SINGER_PATH + "" + singer.getSinger_type() + "/" + singer.getSinger_type() + "" + singerId + ".jpg";
        File file = new File(filepath);
        if (file.exists()) Glide.with(mContext).load(file).into(viewHolder.iv);
//		 Bitmap bmp = mLoadImage.loadBitmap(iv, filepath, true);
//	        if(bmp == null) {
//	        	iv.setBackgroundResource(R.drawable.default_singer);
//	        } else {
//	        	iv.setBackgroundDrawable(new BitmapDrawable(bmp));
//	        }


        //		if (mLoadImage == null) {
//			mLoadImage = new ImageDownloader();  
//		    }  
//
//		
//		if (mLoadImage != null) {
//			//异步下载图片
//			mLoadImage.imageDownload(filepath, iv,filepath, new OnImageDownload() {
//						@Override
//						public void onDownloadSucc(Bitmap bitmap,
//								String c_url,ImageView mimageView) {
//							 
//								iv.setImageBitmap(bitmap);
//							  
//						}
//					});
//		}


//				loadImage(filepath, iv);
//		iv.setBackgroundDrawable(GetHeadUtil.getHead(singer.getSinger_type(),mContext, singerId));
//		iv.setImageBitmap(GetHeadUtil.getHeadBitmap(mActivity, singerId));
        viewHolder.tv.setText(singerName);

        view.setTag(R.id.view_tag_2, singer);
        view.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                final Singer singer2 = (Singer) v.getTag(R.id.view_tag_2);

                DataBase.keySong = singer2.getSinger_name();
                DataBase.keyType = SongUtil.TYPE_SONG_SINGER;
                HomeGeMingFragment.returnType = HomeGeMingFragment.RETURN_TYPE_SINGER;

                EventBus.getDefault().post(new AsyncMessage(FragmentFactory.FRAGMENT_HOME_GEMING));

//				EventBus.getDefault().post(
//						new AsyncMessage(FragmentFactory.FRAGMENT_HOME_GEMING));
//				SongTypeMessage msg = new SongTypeMessage();
//				msg.setKeyWords(singer2.getSinger_name());
//				msg.setType(HomeGeMingFragment.TYPE_SONG_SINGER);
//				msg.setReturnType(HomeGeMingFragment.RETURN_TYPE_SINGER);
//				EventBus.getDefault().post(msg);

            }
        });
        //zhangke add 20171012 for faster end
        return view;
    }

    //zhangke add 20171012 for faster begin
    private class ViewHolder {
        ImageView iv;
        TextView tv;
    }
    //zhangke add 20171012 for faster end

}
