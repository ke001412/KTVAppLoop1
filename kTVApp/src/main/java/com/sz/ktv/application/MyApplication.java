package com.sz.ktv.application;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import com.sz.ktv.db.sort.SongSortHelpManager;
import com.sz.ktv.util.CrashHandler;
import com.sz.ktv.util.writeFile.Write;

 

public class MyApplication extends Application {

	 
	private static MyApplication myApplication;
	private static void setMyApplication(MyApplication cation){
		MyApplication.myApplication = cation;
	}
	 
	private DevMountInfo mountInfo;
	//sdCard 路径
	private String extsdcardPath;
	//内存路径
	private String extneralPath;
	
	private String savePath;
 
	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savPath) {
		this.savePath = savPath;
	}

	   

	public static synchronized MyApplication getInstance() {
		return myApplication;
	}

	 
	@Override
	public void onCreate() {
		super.onCreate();
		  CrashHandler crashHandler = CrashHandler.getInstance();
		  crashHandler.init(getApplicationContext());
		setMyApplication(this);
        initSavePath();
        SongSortHelpManager.getInstance().init(getAppContext());
	}

	private void initSavePath() {

	    mountInfo = DevMountInfo.getInstance();
        mountInfo.init(this);
        extsdcardPath = mountInfo.getExternalSDCardPath();
        extneralPath = Environment.getExternalStorageDirectory().getPath();
        
        if (!TextUtils.isEmpty(extneralPath))
        {
            
            if (new File(extneralPath).canWrite())
            {
                savePath = extneralPath;
            }
            else if (!TextUtils.isEmpty(extsdcardPath))
            {
                savePath = extsdcardPath;
            }
            else
            {
                savePath = mountInfo.getInternalSDCardPath();
               
            }
        }
        else if (!TextUtils.isEmpty(extsdcardPath))
        {
            savePath = extsdcardPath;
        }
        else
        {
            savePath = "";
        }
        if(TextUtils.isEmpty(savePath))
        {
        	savePath = "/mnt/sdcard/";
        }
        
        Write.debug("内置存储路径..."+ savePath);
	}

	/**
	 * 
	 * 拷贝assets from to
	 * **/
	public static void copyFile( String from, String to) {
		InputStream inStream  = null;
		OutputStream fs = null; 
		try {
			int byteread = 0;
			inStream= getAppContext().getResources().getAssets()
					.open(from);
			fs = new BufferedOutputStream(new FileOutputStream(to));
			byte[] buffer = new byte[2048];
			while ((byteread = inStream.read(buffer)) != -1) {
				fs.write(buffer, 0, byteread);
			}
		} catch (IOException e) {
			 
		}finally{
			 
				try {
					if(fs!=null){
						fs.close();
					}
					if(inStream != null ){
						inStream.close();
					}
					
				} catch (IOException e) {
					 
				}
		}
	}
	

    /**
     * Called when the overall system is running low on memory
     */
    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    /**
     * @return the main context of the Application
     */
    public static Context getAppContext()
    {
        return myApplication;
    }

}
