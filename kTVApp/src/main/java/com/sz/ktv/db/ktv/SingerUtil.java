package com.sz.ktv.db.ktv;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import android.text.TextUtils;

import com.sz.ktv.db.DataBase;
import com.sz.ktv.db.Singer;
import com.sz.ktv.db.Song;
import com.sz.ktv.util.Global;
import com.sz.ktv.util.LogUtil;

public class SingerUtil {

    private static SingerUtil instance = null;
    List<Singer> songList = new ArrayList<Singer>();
    public static final int PAGE_SIZE = 12;
    private static final String TAG = "SingerUtil";

    int allSize;
//	int deleteAllSize;

    int totalPages;
//	int deleteTotalPages;

//	public int getDeleteTotalPages() {
//		return deleteTotalPages;
//	}
//
//	public void setDeleteTotalPages(int deleteTotalPages) {
//		this.deleteTotalPages = deleteTotalPages;
//	}

    String singerkey = "";
    private int pageSize = PAGE_SIZE;

    public static SingerUtil getInstance() {

        if (instance == null) { // line 12
            instance = new SingerUtil(); // line 13
        }
        return instance;
    }

    private SingerUtil() {


    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public static final int TYPE_ALL = 0;
    public static final int TYPE_SINGER_NAME = 1;
    public static final int TYPE_SINGER_SOUPIN = 2;
    public static final int TYPE_SINGER_CLASS = 3;


    public synchronized void initSingerPageByKey(int type, String key) {

        singerkey = key;
        if (type == -1) {
            type = TYPE_ALL;
            singerkey = "";
        }
        songList = new ArrayList<Singer>();
        songList.clear();
        allSize = getSingerByKeyCount(type);
        if ((allSize % pageSize) == 0) {
            totalPages = allSize / pageSize;
        } else {
            totalPages = allSize / pageSize + 1;
        }
    }

    public void initSongDeletePageByKey(int type, String key) {
    }


    public synchronized List<Singer> getCurrentSingerPage(int currentPage) {

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
        List<Singer> myList = new ArrayList<Singer>();
//		prePageList = getSongByKeyList(type,(currentPage - 1) * pageSize);
        myList.clear();
        myList.addAll(songList.subList(startNum, endNum));
        return myList;

    }

//	private static HashMap<Integer,SongPage> songPageMap = new HashMap<Integer, SongPage>();


    public SongPage getDefaultSingerList(int size) {
        SongPage songPage = new SongPage();
        List<List<Singer>> defaultList = new ArrayList<List<Singer>>();

//		int pre = size/2;
//		
//		if(totalPages >size)
//		{
//			pre = size/2;
//			
//			for(int i=0;i<pre;i++)
//			{
//				int page = i+1;
//				List<Song> tempList = getCurrentSongPage(page);
//				defaultList.add(tempList);
//			}
//			for(int i=pre-1;i>=0;i--)
//			{
//				int page =totalPages-i;
//				List<Song> tempList = getCurrentSongPage(page);
//				defaultList.add(tempList);
//			}
//			
//		}else {
//			pre = size;
//			
//			for(int i=0;i<pre;i++)
//			{
//				int page = i+1;
//				List<Song> tempList = getCurrentSongPage(page);
//				defaultList.add(tempList);
//			}
//		}

//		
        for (int i = 0; i < size; i++) {
            int page = i + 1;
            List<Singer> tempList = getCurrentSingerPage(page);
            if (null != tempList && tempList.size() > 0) {
                defaultList.add(tempList);
            }
        }

        songPage.setDefaultSingerList(defaultList);
        return songPage;
    }

    /**
     * 通过类型，页码获取歌曲
     *
     * @param type
     * @param currentPage
     * @return
     */
    public synchronized SongPage getSingerPage(int currentPage) {

        long time1 = System.currentTimeMillis();
        SongPage songPage = new SongPage();
        List<Singer> prePageList = new ArrayList<Singer>();
        List<Singer> currentPageList = new ArrayList<Singer>();
        List<Singer> nextPageList = new ArrayList<Singer>();
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

        prePageList = getCurrentSingerPage(prePage);
        currentPageList = getCurrentSingerPage(currentPage);
        nextPageList = getCurrentSingerPage(nextPage);

        songPage.setCurrentSingerPageList(currentPageList);
        songPage.setNextSingerPageList(nextPageList);
        songPage.setPreSingerPageList(prePageList);

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
    public int getSingerByKeyCount(int type) {
        int count = 0;
        try {
            songList = new ArrayList<Singer>();
            songList.clear();

            if (TextUtils.isEmpty(singerkey)) {

                songList.addAll(DataBase.singerList);

                count = songList.size();
            } else {
                switch (type) {
                    case TYPE_SINGER_NAME:
                        songList.addAll(DataBase.getAllSingerListByName(singerkey));
                        count = songList.size();
                        break;
                    case TYPE_SINGER_SOUPIN:
                        songList.addAll(DataBase.getAllSingerListByShouPin(singerkey));
                        count = songList.size();
                        break;
                    case TYPE_SINGER_CLASS:
                        songList.addAll(DataBase.getAllSingerList(singerkey));
                        count = songList.size();
                        break;
                    default:
                        break;
                }

            }

            LogUtil.log(TAG, "getSingerByKeyCount： " + count);
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

    }
}
