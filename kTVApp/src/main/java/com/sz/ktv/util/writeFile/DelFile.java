package com.sz.ktv.util.writeFile;

import java.io.File;

public class DelFile {

	public static void delFolder(String dirPath) {
		
			dAllFile(dirPath);  
			String fpath = dirPath;
			 File mPath = new File(fpath);
			boolean tempFlag = mPath.delete();  
			Write.i("tempFlag:"+tempFlag + "");
		
	}

	public static boolean dAllFile(String fPath) {
		 
		File myFile = new File(fPath);
		if (!myFile.exists()) {
			return false;
		}
		if (!myFile.isDirectory()) {
			return false;
		}
		String[] tList = myFile.list();
		File tempFile = null;
		for (int i = 0; i < tList.length; i++) {
			if (fPath.endsWith(File.separator)) {
				tempFile = new File(fPath + tList[i]);
			} else {
				tempFile = new File(fPath + File.separator + tList[i]);
			}
			if (tempFile.isFile()) {
				boolean tempFlag = tempFile.delete();
				Write.i("tempFlag:"+ tempFlag + "");
			}
			if (tempFile.isDirectory()) {
				dAllFile(fPath + "/" + tList[i]); 
				delFolder(fPath + "/" + tList[i]); 
				return  true;
			}
		}
		 return false; 
	}
}