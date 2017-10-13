package com.sz.ktv.ui.service;

import com.sz.ktv.db.DataBase;
import com.sz.ktv.db.Song;
import com.sz.ktv.downloader.Downloader;
import com.sz.ktv.util.writeFile.Write;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class DownLoadThread extends Thread {

//	public Context mContext;
	public List<Song> songList;
	private boolean downFlag = false;
	     private static DownLoadThread instance = null;  
	     private DownLoadThread() { 
//	    	 mContext = ;
			 songList = DataBase.songDownloaddList;
	    }  
	  
	     
	     public static DownLoadThread getInstance() {  
	        if (instance == null) {  
            instance = new DownLoadThread();  
	        }  
	       return instance;  
	    }
 
	public String startDownLoad(Song song)
	{
		if(songList.size()>50)
		{
			//下载达到最大值
			return "达到下载最大数值...";
		}
		songList.add(song);
		if(!downFlag)
		{
			start();
		}
		return "正在下载...";
		
	}
	@Override
	public void run() {
		super.run();
		while (songList.size()>0) {
			downFlag = true;
			 Song song = songList.get(0);
			 String fileName = song.getSong_file_name();
			 String prePaht = fileName.substring(0,4);
				//需要初始化下载路径..
				String dowloadPath = DataBase.downPath+prePaht+"/"+fileName;
				Write.debug("下载歌曲:"+fileName +"  path:"+dowloadPath);
				Downloader download = new Downloader();
			    int result = download.downFile(dowloadPath, "song", fileName);
			    if(result == 0)
			    {
			    	song.setSongDownStatus(2);
			    	song.setSong_download_flag("1");
			    	updateSongList(song);
			    }else {
			    	//下载失败
			    	song.setSongDownStatus(3);
			    }
			    DataBase.songDownSuccessloaddList.add(song);
		    	songList.remove(0);
		    	downFlag = false;
		    	EventBus.getDefault().post(new DownLoadMsg());
		}
	}
	/**
	 * 将下载成功的歌曲加入到song里面，删去云端歌曲
	 * @param song
	 */
	private void updateSongList(Song song)
	{
		 
		List<Song> cloudSongList = DataBase.songCloudList;
		int newSongSize =cloudSongList.size();
		DataBase.getInstance().addNewSongList(song);
		for(int i=0;i<newSongSize;i++)
		{
			Song tmpSong = cloudSongList.get(i);
			if(tmpSong.getSong_no().equals(song.getSong_no()))
			{
				cloudSongList.remove(i);
				break;
			}
		}
	}
}
