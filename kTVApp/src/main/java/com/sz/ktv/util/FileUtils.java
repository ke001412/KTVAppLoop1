package com.sz.ktv.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import android.content.Context;
import android.content.Intent;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;

import com.sz.ktv.application.MyApplication;

public class FileUtils {
	
	
	
	public static void scanSdCard(File file)
    {
        try
        {
            MediaScannerConnection.scanFile(MyApplication.getInstance().getApplicationContext(),
                    new String[] { file.getAbsolutePath() }, null, null);
        }
        catch (Exception e)
        {
             
        }
        
    }
    /**
     * 判断是否有SDcard
     **/
    public static boolean sdCardIsExsit()
    {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }
	 public static void scanFile(String dirPath)
	    {
	        MediaScannerConnection.scanFile(MyApplication.getInstance().getApplicationContext(), new String[] { dirPath },
	                null, null);
	        
	        scanPhotos(dirPath, MyApplication.getInstance().getApplicationContext());
	        MediaScannerConnection.scanFile(MyApplication.getInstance().getApplicationContext(), new String[] { dirPath },
	                null, null);
	        broadcastNewPicture(MyApplication.getInstance().getApplicationContext(), Uri.parse("file://" + dirPath));
	        
	    }
	 public static final String ACTION_NEW_PICTURE = "android.hardware.action.NEW_PICTURE";
	    public static final String CAMERA_NEW_PICTURE = "com.android.camera.NEW_PICTURE";
	    
	    public static void broadcastNewPicture(Context context, Uri uri)
	    {
	        context.sendBroadcast(new Intent(ACTION_NEW_PICTURE, uri));
	        context.sendBroadcast(new Intent(CAMERA_NEW_PICTURE, uri));
	    }
	    
	    public static void scanPhotos(String filePath, Context context)
	    {
	        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
	        Uri uri = Uri.fromFile(new File(filePath));
	        intent.setData(uri);
	        context.sendBroadcast(intent);
	    }
	    
	    public static void scanPhotos(File file, Context context)
	    {
	        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
	        Uri uri = Uri.fromFile(file);
	        intent.setData(uri);
	        context.sendBroadcast(intent);
	    }
	    
	public static void ReadData() {  
        try {  
            FileReader read = new FileReader("D:/data.txt");  
            BufferedReader br = new BufferedReader(read);  
            String row;  
            int rownum = 1;  
            FileWriter fw1 = new FileWriter("/text1.txt");  
            FileWriter fw2 = new FileWriter("/text2.txt");  
            FileWriter fw3 = new FileWriter("/text3.txt");  
            FileWriter fw4 = new FileWriter("/text4.txt");  
            while ((row = br.readLine()) != null) {  
                if (rownum % 4 == 1) {  
                    fw1.append(row + "\r\n");  
                } else if (rownum % 4 == 2) {  
                    fw2.append(row + "\r\n");  
                } else if (rownum % 4 == 3) {  
                    fw3.append(row + "\r\n");  
                } else if (rownum % 4 == 0) {  
                    fw4.append(row + "\r\n");  
                }  
                rownum++;  
            }  
            fw1.close();  
            fw2.close();  
            fw3.close();  
            fw4.close();  
  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
  
	
}
