package com.sz.ktv.util;

import java.io.File;
import java.io.InputStream;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;

import com.sz.ktv.R;
import com.sz.ktv.db.DataBase;

public class GetHeadUtil {

	
	public static Drawable getHead(String singerType, Context context,String fileName){
		Bitmap bitmap = null; 
		 
		String headFilePath = DataBase.MNT_SDA_SINGER_PATH+singerType+"/"+singerType+""+fileName+".jpg";
		try {
			File mFile=new File(headFilePath);
	        //若该文件存在
	        if (mFile.exists()) {
	              bitmap=BitmapFactory.decodeFile(headFilePath);
	        }else {
	        	return null;
	        }
		    } catch (Exception e) {
		    	return null;
		    }
		return new BitmapDrawable(bitmap);
	}
	
	public static Bitmap getHeadBitmap(Context context,String fileName){
		Bitmap bitmap = null; 
		if(TextUtils.isEmpty(fileName)){
			fileName = "default_singer";
		}
		String headFilePath = DataBase.HEAD_FILE_DIR+""+fileName+".jpg";
		try {
		      AssetManager assetManager = context.getAssets();
		      InputStream is = assetManager.open(headFilePath);
		      bitmap = BitmapFactory.decodeStream(is);
		     
		    } catch (Exception e) {
//		      System.out.println("异常信息:" + e.toString());
		      bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.default_singer);
		      return    bitmap;
		    }
		return bitmap;
	}
	
	public static Drawable getSongSinerHead(Context context,String fileName){
		Bitmap bitmap = null; 
		if(TextUtils.isEmpty(fileName)){
			fileName = "default_singer";
		}
		String headFilePath = DataBase.HEAD_FILE_DIR+""+fileName+".jpg";
		try {
		      AssetManager assetManager = context.getAssets();
		      InputStream is = assetManager.open(headFilePath);
		      bitmap = BitmapFactory.decodeStream(is);
		     
		    } catch (Exception e) {
//		      System.out.println("异常信息:" + e.toString());
		      bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.mtv);
		      return  new BitmapDrawable(bitmap);
		    }
		return new BitmapDrawable(bitmap);
	}
}
