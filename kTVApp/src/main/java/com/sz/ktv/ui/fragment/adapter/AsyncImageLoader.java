package com.sz.ktv.ui.fragment.adapter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.lang.ref.SoftReference;
import java.util.HashMap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;

public class AsyncImageLoader {

     private HashMap<String, SoftReference<Drawable>> imageCache;
     public AsyncImageLoader() {
             imageCache = new HashMap<String, SoftReference<Drawable>>();
         }
      
     public Drawable loadDrawable(final String imageUrl, final ImageCallback imageCallback) {
             if (imageCache.containsKey(imageUrl)) {
                 SoftReference<Drawable> softReference = imageCache.get(imageUrl);
                 Drawable drawable = softReference.get();
                 if (drawable != null) {
                     return drawable;
                 }
             }
             final Handler handler = new Handler() {
                 public void handleMessage(Message message) {
                     imageCallback.imageLoaded((Drawable) message.obj, imageUrl);
                 }
             };
             new Thread() {
                 @Override
                 public void run() {
                	 System.out.println("加载图片...");
                     Drawable drawable = loadImageFromUrl(imageUrl);
                     imageCache.put(imageUrl, new SoftReference<Drawable>(drawable));
                     Message message = handler.obtainMessage(0, drawable);
                     handler.sendMessage(message);
                 }
             }.start();
             return null;
         }
       
    public static Drawable loadImageFromUrl(String url) {
//    	/**
//    	 * 加载网络图片
//    	 */
//            URL m;
//            InputStream i = null;
//            try {
//                m = new URL(url);
//                i = (InputStream) m.getContent();
//            } catch (MalformedURLException e1) {
//                e1.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            Drawable d = Drawable.createFromStream(i, "src");
    	
    	/**
    	 * 加载内存卡图片
    	 */
    	    Options options=new Options();
    	    options.inSampleSize=2;
    	    Bitmap bitmap=BitmapFactory.decodeFile(url, options);
    	    Drawable drawable=new BitmapDrawable(bitmap);
//    	Drawable drawable = null;
//		try {
//			InputStream input = new FileInputStream(new File(url));
////			drawable = Drawable.createFromStream(, "image.png");
//			Bitmap bitmap = compressImage(BitmapFactory.decodeStream(input, null, null));
//			drawable=new BitmapDrawable(bitmap);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
            return drawable;
        }
    private static Bitmap compressImage(Bitmap image) {  
    	  
    	ByteArrayOutputStream baos = new ByteArrayOutputStream();
    	//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中  
    	image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
    	int options = 50;  
    	//循环判断如果压缩后图片是否大于100kb,大于继续压缩 
     
    		//重置baos即清空baos 
    		baos.reset();
    		//这里压缩options%，把压缩后的数据存放到baos中  
    		image.compress(Bitmap.CompressFormat.JPEG, options, baos);
    		 
    	   
    	//把压缩后的数据baos存放到ByteArrayInputStream中  
    	ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
    	//把ByteArrayInputStream数据生成图片  
    	Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);
    	return bitmap;  
    }
    public interface ImageCallback {
             public void imageLoaded(Drawable imageDrawable, String imageUrl);
             
         }
}