package com.sz.ktv.downloader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.sz.ktv.db.DataBase;

import android.os.Environment;
import android.util.Log;

public class FileUtil {
	private String SDPATH;
	
	public String getSDPATH() {
		return SDPATH;
	}
	
	public FileUtil() {
		SDPATH = DataBase.MNT_SDA_DIR;//Environment.getExternalStorageDirectory() + "/";
	}
	
	public boolean isFileExist(String fileName) {
		File file = new File(SDPATH + fileName);
		return file.exists();
	}
	
	public File createSDFile(String fileName) throws IOException {
		File file = new File(SDPATH + fileName);
		if(!file.exists()) {
			Boolean bSuc = file.createNewFile();
			Log.d("wuming", "createSDFile() bSuc = " + bSuc);			
		} else {
			Log.d("wuming", "createSDFile() file exist.");	
		}
		return file;
	}
	
	public File createSDDir(String dirName) {
		File file = null;
		try {
			file = new File(SDPATH + dirName + "/");
			if(!file.exists()) {
				file.mkdir();
				Log.d("wuming", "createSDDir() mkdir.");	
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return file;
	}
	
	public File write2SDFromInput(String path, String fileName, InputStream input) {
		File file = null;
		OutputStream output = null;
		try{
			createSDDir(path);
			file = createSDFile(path + "/" +fileName);
			output = new FileOutputStream(file);
			byte buffer[] = new byte[4*1024];
			while((input.read(buffer)) != -1) {
				output.write(buffer);
//				Log.d("wuming", "write2SDFromInput() rev data.");
			}
			output.flush();
		} catch(Exception e) {
			e.printStackTrace();
		} finally{
			try{
				output.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return file;
	}
}
