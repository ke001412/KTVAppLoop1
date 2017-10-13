package com.sz.ktv.db;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.util.Log;

import com.sz.ktv.util.date.DateUtil;

/**
 * 每天保存用户点击过的歌曲
 * 按照YYYY-MM-DD.txt格式保存
 * @author zhuxl
 *
 */
public class SongSortManger {
	
	/**
	 * 用户点击的歌曲按时间保存在改目录文件下
	 */
	public static final String SORT_FILE_PATH = DataBase.MNT_SDA_DIR+"/song_sort/";
	
	private static final String TAG = SongSortManger.class.getName();
	 
	/**
	 * 用户点击的歌曲写入文件
	 * @param userClickSong
	 * @return
	 */
	public static boolean writeSortSongToFile(List<Song> userClickSong)
	{
		boolean flag = false;
		//现获取当前的日期yyyy-mm-dd
		String currentDay = DateUtil.getDate(new Date());
		Log.i(TAG,"当天日期:"+ currentDay);
		String filePath = SORT_FILE_PATH+currentDay;
		File file = new File(filePath);
		//查看文件是否存在，存在则直接写入，不存在就从新创建
		return writeToFile(userClickSong,filePath);
	}

	private static boolean writeToFile(List<Song> songList,String filePath)
	{
		int size = songList.size();
		FileWriter fileWrite  = null;
		try {
			fileWrite = new FileWriter(new File(filePath), true);
			for(int i=0;i<size;i++)
			{
			
				Song songBean = songList.get(i);
				//编号|歌名|歌星|点击次数
//				String songStr = songBean.getSong_no()+"|"+songBean.getSong_name()+"|"
//				+songBean.getSong_singer_name()+"|"+songBean.getSong_click_count()+"\n";
//				fileWrite.write(songStr);
				fileWrite.flush();
			}
		} catch (Exception e) {
			 e.printStackTrace();
			 return false;
		}finally{
			try {
				fileWrite.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
	/**
	 * 获取最近一周七天的歌曲
	 * @return
	 */
	public static List<Song> getWeekSongList()
	{
		//获取今天的日期
		String nowDay = DateUtil.getDate(new Date());
		List<Song> weekList = new ArrayList<Song>();
		
		//获取
		for(int i=0;i<7;i++)
		{
			String weekDay = DateUtil.addDay(nowDay, -(i+1));
			String filePath = SORT_FILE_PATH+weekDay;
			List<Song> tempList = readFileSongList(filePath);
			weekList.addAll(tempList);
		}
		return weekList;
	}
	/**
	 * 读取文件的歌曲信息
	 * @param filePath
	 * @return
	 */
	private  static List<Song> readFileSongList(String filePath)
	{
		FileReader reader =null;
        BufferedReader br = null;
        List<Song> list = new ArrayList<Song>();
		try {
			  reader = new FileReader(filePath);
	          br = new BufferedReader(reader);
	        String str = null;
	        while((str = br.readLine()) != null) {
	        	String ary[] = str.split("\\|");
	        	Song song = new Song();
	        	//编号|歌名|歌星|点击次数
	        	song.setSong_no(ary[0]);
	        	song.setSong_name(ary[1]);
	        	song.setSong_singer_name(ary[2]);
//	        	song.setSong_click_count(Integer.parseInt(ary[3]));
	        	list.add(song);
	        }
	        
		} catch (Exception e) {
			 e.printStackTrace();
		}finally
		{
			try {
				br.close();
		        reader.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		}
return list;       

	}
}
