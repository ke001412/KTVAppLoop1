package com.sz.ktv.downloader;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.util.Log;

/**
 * 文件下载类
 *
 */
public class Downloader {

	public Downloader() {
		
	}
	
	private URL url = null;
	
	/**
	 * 返回下载的string
	 * @param urlStr
	 * @return
	 */
	public String download(String urlStr) {
		StringBuilder sb = new StringBuilder();
		String line = null;
		BufferedReader buffer = null;
		try{
			url = new URL(urlStr);
			HttpURLConnection urlConn = (HttpURLConnection)url.openConnection();
			buffer = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
			while((line = buffer.readLine()) != null) {
				sb.append(line);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try{
				buffer.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return urlStr;
		
	}
	/**
	 * 文件下载
	 * @param urlStr  下载地址
	 * @param dirName 保存目录
	 * @param fileName 保存文件名
	 * @return  返回0下载成功
	 */
	public int downFile(String urlStr, String path, String fileName) {
		InputStream inputStream = null;
		try {
			FileUtil fileUtil = new FileUtil();
			if(fileUtil.isFileExist(path + "/" + fileName)) {
				Log.d("wuming", "Downloader downFile() file exist.");
			}
			
			inputStream = getInputStreamFromUrl(urlStr);
			File resultFile = fileUtil.write2SDFromInput(path, fileName, inputStream);
			if(resultFile == null) {
				Log.d("wuming", "Downloader downFile() resultFile is null.");
				return -1;
			}
		} catch(Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			try {
				inputStream.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	private InputStream getInputStreamFromUrl(String urlStr) throws MalformedURLException, IOException {
		// TODO Auto-generated method stub
		url = new URL(urlStr);
		HttpURLConnection urlCon = (HttpURLConnection)url.openConnection();
		InputStream inputStream = urlCon.getInputStream();
		
		return inputStream;
	}

}
