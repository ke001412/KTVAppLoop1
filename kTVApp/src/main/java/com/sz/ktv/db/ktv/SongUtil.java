package com.sz.ktv.db.ktv;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.text.TextUtils;

import com.sz.ktv.db.DataBase;
import com.sz.ktv.db.Song;
import com.sz.ktv.db.SongSortManger;
import com.sz.ktv.db.sort.SongSortHelpManager;
import com.sz.ktv.util.Global;

public class SongUtil {

	private static SongUtil instance = null;
	public static final int SONG_PAGE_SIZE = 9;
	public static final int SONG_DELETE_PAGE_SIZE = 6;
	List<Song> songList = new ArrayList<Song>();

	int allSize;

	int totalPages;

	String songkey = "";
	private int pageSize = SONG_PAGE_SIZE;

	public static SongUtil getInstance() {

		if (instance == null) { // line 12
			instance = new SongUtil(); // line 13
		}
		return instance;
	}

	private SongUtil() {

	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public static final int TYPE_ALL = 0;
	public static final int TYPE_SONG_NAME = 1;
	public static final int TYPE_SONG_LANGUAGE = 2;
	public static final int TYPE_SONG_SINGER = 3;
	public static final int TYPE_SONG_POPULAR = 4;
	public static final int TYPE_SONG_DANCE = 5;
	public static final int TYPE_SONG_CLASS = 6;
	public static final int TYPE_SONG_FILEM = 7;
	public static final int TYPE_SONG_COUNT = 8;
	public static final int TYPE_SONG_FRIST_NAME = 9;
	public static final int TYPE_SONG_NO = 10;

	public synchronized void initSongPageByKey(int type, String key) {

		pageSize = SONG_PAGE_SIZE;
		songkey = key;
		if (type == -1) {
			type = TYPE_ALL;
		}
//		songList = new ArrayList<Song>();
//		songList.clear();
		allSize = getSongByKeyCount(type);
		if ((allSize % SONG_PAGE_SIZE) == 0) {
			totalPages = allSize / SONG_PAGE_SIZE;
		} else {
			totalPages = allSize / SONG_PAGE_SIZE + 1;
		}
	}

	public void initSongDeletePageByKey(int type, String key) {

		pageSize = SONG_DELETE_PAGE_SIZE;
		songkey = key;

//		songList = new ArrayList<Song>();
//		songList.clear();

		allSize = getSongByKeyCount(type);
		if ((allSize % SONG_DELETE_PAGE_SIZE) == 0) {
			totalPages = allSize / SONG_DELETE_PAGE_SIZE;
		} else {
			totalPages = allSize / SONG_DELETE_PAGE_SIZE + 1;
		}

	}

	public List<Song> getDeleteSongList(int currentPage) {

		if (currentPage < 0) {
			currentPage = 1;
		}
		if (currentPage > totalPages) {
			currentPage = totalPages;
		}
		List<Song> prePageList = new ArrayList<Song>();
		prePageList = getCurrentSongPage(currentPage);

		return prePageList;

	}

	public synchronized List<Song> getCurrentSongPage(int currentPage) {

		if (allSize <= 0) {
			return null;
		}
		if (currentPage < 0) {
			currentPage = 1;
		}
		if (currentPage > totalPages) {
			currentPage = totalPages;
		}
		int startNum = 0;
		int endNum = 0;

		startNum = (currentPage - 1) * pageSize;
		endNum = startNum + pageSize;
		if (endNum > allSize) {
			endNum = allSize;
		}
//		List<Song> myList = new ArrayList<Song>();
		// prePageList = getSongByKeyList(type,(currentPage - 1) * pageSize);
		// myList.clear();
		// myList.addAll(DataBase.songList.subList(startNum,endNum));
		return songList.subList(startNum, endNum);// DataBase.songList;

	}

	/**
	 * 所有本地歌曲
	 */
	public static final int SORT_TYPE_1 = 1;
	/**
	 * 总排行
	 */
	public static final int SORT_TYPE_2 = 2;
	/**
	 * 月排行
	 */
	public static final int SORT_TYPE_3 = 3;
	/**
	 * 周排行
	 */
	public static final int SORT_TYPE_4 = 4;
	/**
	 * 新歌
	 */
	public static final int SORT_TYPE_5 = 5;
	/**
	 * 云端歌曲
	 */
	public static final int SORT_TYPE_6=6;
	
	public static int SONG_SELECT_TYPE = 1;
	/**
	 * 根据不同的类型获取所有的歌曲;
	 * 
	 * @param type
	 *            type=1 所有本地歌曲; type= 2总排行 type=3 获取月排行 type=4 周排行 type=5 新歌排行,type =6 云端歌曲
	 * @return
	 */
	private List<Song> getAllSong() {
		List<Song> list = new ArrayList<Song>();
		
		switch (SONG_SELECT_TYPE) {
		case SORT_TYPE_1:
			list =  DataBase.songLocalList;
			break;
		case SORT_TYPE_2:
			list = SongSortHelpManager.getInstance().getAllSortSong();
			break;
		case SORT_TYPE_3:
			list = SongSortHelpManager.getInstance().getMonthSortSong();
			break;
		case SORT_TYPE_4:
			list = SongSortHelpManager.getInstance().getWeekSortSong();
			break;
		case SORT_TYPE_5:
			list =  DataBase.newSongList;
			break;
		case SORT_TYPE_6:
			list =  DataBase.songCloudList;
			break;
		default:
			list =  DataBase.songLocalList;
			break;
		}
		return list;
	}

	/**
	 * 获取所有的歌曲
	 * 
	 * @return
	 */
	public List<Song> getAllSongList() {
		return DataBase.songLocalList;
	}

	public SongPage getDefaultSongList(int size) {
		SongPage songPage = new SongPage();
		List<List<Song>> defaultList = new ArrayList<List<Song>>();

		// int pre = size/2;
		//
		// if(totalPages >size)
		// {
		// pre = size/2;
		//
		// for(int i=0;i<pre;i++)
		// {
		// int page = i+1;
		// List<Song> tempList = getCurrentSongPage(page);
		// defaultList.add(tempList);
		// }
		// for(int i=pre-1;i>=0;i--)
		// {
		// int page =totalPages-i;
		// List<Song> tempList = getCurrentSongPage(page);
		// defaultList.add(tempList);
		// }
		//
		// }else {
		// pre = size;
		//
		// for(int i=0;i<pre;i++)
		// {
		// int page = i+1;
		// List<Song> tempList = getCurrentSongPage(page);
		// defaultList.add(tempList);
		// }
		// }

		//
		if(allSize<=pageSize){
			defaultList.add(songList);
			songPage.setDefaultSongList(defaultList);
		}else {
			for (int i = 0; i < size; i++) {
				int page = i + 1;
				List<Song> tempList = getCurrentSongPage(page);
				defaultList.add(tempList);
			}
		}
	

		songPage.setDefaultSongList(defaultList);
		return songPage;
	}

	// private static HashMap<Integer,SongPage> songPageMap = new
	// HashMap<Integer, SongPage>();

	/**
	 * 通过类型，页码获取歌曲
	 * 
	 * @param type
	 * @param currentPage
	 * @return
	 */
	public synchronized SongPage getSongPage(int currentPage) {

		long time1 = System.currentTimeMillis();
		SongPage songPage = new SongPage();
		List<Song> prePageList = new ArrayList<Song>();
		List<Song> currentPageList = new ArrayList<Song>();
		List<Song> nextPageList = new ArrayList<Song>();
		int prePage = 0;

		int nextPage = 0;

		prePage = currentPage - 1;
		nextPage = currentPage + 1;

		if (prePage <= 0) {
			prePage = totalPages;
		}
		if (nextPage > totalPages) {
			nextPage = 1;
		} else {
			nextPage = currentPage;
		}

		prePageList = getCurrentSongPage(prePage);
		currentPageList = getCurrentSongPage(currentPage);
		nextPageList = getCurrentSongPage(nextPage);

		songPage.setCurrentPageList(currentPageList);
		songPage.setNextPageList(nextPageList);
		songPage.setPrePageList(prePageList);

		long time2 = System.currentTimeMillis();
		System.out.println(" 分页查询时间 ;" + (time2 - time1));
		return songPage;
	}
 
	/**
	 * 根据歌名或者首拼获取歌曲总数
	 * 
	 * @param key
	 * @return
	 */
	public int getSongByKeyCount(int type) {
		int count = 0;
		try {
//			songList = new ArrayList<Song>();
//			songList.clear();
			songList = getAllSong();
			 
			if (TextUtils.isEmpty(songkey)) {
				count = songList.size();
			} else {
				switch (type) {
				case TYPE_SONG_NO:
					songList = DataBase.getAllSongByNo(songList,songkey);
					count = songList.size();
					break;
				case TYPE_SONG_FRIST_NAME:
					songList = DataBase.getAllSongBySongShouPin(songList,songkey);
					count = songList.size();
					break;
				case TYPE_SONG_DANCE:
					songList = DataBase.getAllSongByWuQuFenLei(songList,songkey);
					count = songList.size();
					break;
				case TYPE_SONG_SINGER:
					songList = DataBase.getAllSongBySingerName(songList,songkey);
					count = songList.size();
					break;
				case TYPE_SONG_LANGUAGE:
					songList = DataBase.getAllSongByLanguage(songList,songkey);
					count = songList.size();
					break;
				case TYPE_SONG_NAME:
					songList = DataBase.getAllSongBySongName(songList,songkey);
					count = songList.size();
					break;
				case TYPE_SONG_FILEM:
					songList = DataBase.getAllSongByDianYingFenLei(songList,songkey);
					count = songList.size();
					break;
				case TYPE_SONG_CLASS:
					songList = DataBase.getAllSongByFenLei(songList,songkey);
					count = songList.size();
					break;
				case TYPE_SONG_COUNT:
					songList = DataBase.getAllSongByWordsCount(songList,songkey);
					count = songList.size();
					break;
				case TYPE_SONG_POPULAR:
					songList = DataBase.getAllSongByLiuXingFenLei(songList,songkey);
					count = songList.size();
					break;
				default:
					break;
				}

			}

			System.out.println("song 大小： " + count);
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}

	private static final String SONG_FILE_TEXT = "song_update.txt";

	public boolean updateSongDataFile(Song tmpSong) {

		// 歌曲No号|语言|歌名|首拼|字数|歌星|音轨|左音量|右音量|歌曲分类|舞曲类型|电影分类|流行分类|下载标志位|时间|歌曲文件名称
		try {
			String dbPath = Global.currentActivity.getFilesDir().getParent()
					+ "/" + SONG_FILE_TEXT;
			File file = new File(dbPath);
			if (file.exists()) {
				file.delete();
			}
			FileWriter writer = new FileWriter(dbPath, true);
			List<Song> list = DataBase.songLocalList;
			StringBuffer buffer = new StringBuffer();
			int size = list.size();
			for (int i = 0; i < size; i++) {
				Song song = list.get(i);
				String content = song.getSong_no() + "|"
						+ song.getSong_language() + "|"
						+ song.getSong_file_name() + "|"
						+ song.getSong_first_name() + "|"
						+ song.getSong_words_count() + "|"
						+ song.getSong_singer_name() + "|"
						+ song.getSong_track() + "|"
						+ song.getSong_left_volume() + "|"
						+ song.getSong_right_volume() + "|"
						+ song.getSong_class() + "|"
						+ song.getSong_dance_type() + "|"
						+ song.getSong_film_type() + "|"
						+ song.getSong_popular_type() + "|"
						+ song.getSong_download_flag() + "|"
						+ song.getSong_time() + "|" + song.getSong_file_name()
						+ "\n";
				if (tmpSong.getSong_no().equals(song.getSong_no())) {
					String tmp = tmpSong.getSong_no() + "|"
							+ tmpSong.getSong_language() + "|"
							+ tmpSong.getSong_file_name() + "|"
							+ tmpSong.getSong_first_name() + "|"
							+ tmpSong.getSong_words_count() + "|"
							+ tmpSong.getSong_singer_name() + "|"
							+ tmpSong.getSong_track() + "|"
							+ tmpSong.getSong_left_volume() + "|"
							+ tmpSong.getSong_right_volume() + "|"
							+ tmpSong.getSong_class() + "|"
							+ tmpSong.getSong_dance_type() + "|"
							+ tmpSong.getSong_film_type() + "|"
							+ tmpSong.getSong_popular_type() + "|"
							+ tmpSong.getSong_download_flag() + "|"
							+ tmpSong.getSong_time() + "|"
							+ tmpSong.getSong_file_name() + "\n";
					buffer.append(tmp);
				} else {
					buffer.append(content);
				}
			}
			writer.write(buffer.toString());
			writer.close();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public void clear() {
//if(null != songList){
//		songList.clear();
//		songList = null;
//}
	}
	/**
	 * 获取下一个文件名称
	 * @return
	 */
public String getNextSongName()
{
	File file = new File(DataBase.MNT_SDA_SONG_PATH);
	String fAry[] = file.list();
	List<Integer> list = new ArrayList<Integer>();
	for (String string : fAry) {
		if(string.startsWith("C"))
		{
			string = string.substring(0,string.lastIndexOf("."));
			String num = string.substring(1, string.length());
			int numint = Integer.parseInt(num);
			list.add(numint);
		}
	}
//	int ar[] = (int[])list.toArray(new int[]);
	int size = list.size();
	int[] ar = new int[size];
	for(int i = 0;i<size;i++){
		ar[i] = list.get(i);
	}
	int max = getMax(ar);
	max = max+1;
	String str = String.format("C%1$07d", max);
	return str;
}
public   int getMax(int[] values)
{
    int tmp=0;
     
    if(null!=values)
    {
        tmp=values[0];
        for(int i=0; i<values.length; i++)
        {
            if(tmp<values[i])
            {
                tmp = values[i];
            }
        }
    }
     
    return tmp;     
}

}
