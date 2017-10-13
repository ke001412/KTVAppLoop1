package com.sz.ktv.db.sort;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.sz.ktv.db.DataBase;
import com.sz.ktv.db.Song;
import com.sz.ktv.db.sort.DaoMaster.DevOpenHelper;
import com.sz.ktv.db.sort.SongSortDao.Properties;
import com.sz.ktv.util.date.DateUtil;

import de.greenrobot.dao.query.QueryBuilder;

public class SongSortHelpManager {


	private SQLiteDatabase db;
	private DaoMaster daoMaster;
	private DaoSession daoSession;
	/**
	 * PersonDAO 类
	 */
	private SongSortDao mPersonDao;

	/**
	 * 数据库名称及路径
	 */
	private static String databasesPath = DataBase.MNT_SDA_DIR+ "/sortSong/sort_song.db";
	
	 private static SongSortHelpManager instance = null;  
	    
	    public synchronized static SongSortHelpManager getInstance() {  
	        if (instance == null) {  
	             instance = new SongSortHelpManager();  
	             
	         }  
	        return instance;  
	     } 
	 public  void init(Context context){

		 	databasesPath = context.getFilesDir().getParent()+"/sortSong/sort_song.db";
			DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, databasesPath,
					null);
			db = helper.getWritableDatabase();
			daoMaster = new DaoMaster(db);
			daoSession = daoMaster.newSession();
			mPersonDao = daoSession.getPersonDao();
	 }
	    private SongSortHelpManager()
	    {
	    		
	    }
	    
	    /**
	     * 根据song_no 获取歌曲信息
	     * @param song
	     * @return
	     */
	    public Song getSortSongBySongNo(Song song)
	    {
	    	String songNo = song.getSong_no();
	    	QueryBuilder<Song> qb = mPersonDao.queryBuilder();
			qb.where(Properties._SONG_NO.eq(songNo));
			List<Song> mPersons = qb.list();

			if (mPersons != null && mPersons.size()>0) {
				return mPersons.get(0);
			}
			return null;
	    }
	    /**
	     * 保存点击的歌曲信息
	     * @param song
	     */
	   public void  saveOrUpdateSortSong(Song song)
	   {
		   int tmpSongClickCount =0;
		 //获取今天的日期
			String nowDay = DateUtil.getDateFromatYYYYMMDD(new Date());
		   int clickTime = Integer.parseInt(nowDay);
			song.setSong_clickTime(clickTime);
			
		   Song tmpSong = getSortSongBySongNo(song);
		   if(null != tmpSong)
		   {
			   tmpSongClickCount = tmpSong.getSong_clickCount();
			   tmpSongClickCount = tmpSongClickCount+1;
			   tmpSong.setSong_clickCount(tmpSongClickCount);
			   tmpSong.setSong_clickTime(song.getSong_clickTime());
			   mPersonDao.insertOrReplace(tmpSong);
		   }else {
			   song.setSong_clickCount(1);
			   mPersonDao.insertOrReplace(song);
		   }
		  
	   }
	   
	   /**
	    * 获取周排行歌曲列表
	    * @return
	    */
	   public List<Song> getMonthSortSong()
	   {
		   List<Song>  list = new ArrayList<Song>();
			String nowDay = DateUtil.getDateFromatYYYYMMDD(new Date());
		    String monthkDay = DateUtil.addDay(nowDay, -30);
		    int nowDayInt = Integer.parseInt(nowDay);
		    int weekDayInt = Integer.parseInt(monthkDay);
		    
	    	QueryBuilder<Song> qb = mPersonDao.queryBuilder();
			qb.where(Properties._SONG_CLICK_TIME.between(weekDayInt, nowDayInt)).build();
			list = qb.list();

			 return list;
		   
	   }
	   
	   
	   /**
	    * 获取总排行歌曲列表
	    * @return
	    */
	   public List<Song> getAllSortSong()
	   {
		   List<Song>  list = new ArrayList<Song>();
		    
	    	QueryBuilder<Song> qb = mPersonDao.queryBuilder();
			list = qb.list();

			 return list;
		   
	   }
	   
	   /**
	    * 获取周排行歌曲列表
	    * @return
	    */
	   public List<Song> getWeekSortSong()
	   {
		   List<Song>  list = new ArrayList<Song>();
			String nowDay = DateUtil.getDateFromatYYYYMMDD(new Date());
		    String weekDay = DateUtil.addDay(nowDay, -7);
		    int nowDayInt = Integer.parseInt(nowDay);
		    int weekDayInt = Integer.parseInt(weekDay);
		    
	    	QueryBuilder<Song> qb = mPersonDao.queryBuilder();
			qb.where(Properties._SONG_CLICK_TIME.between(weekDayInt, nowDayInt)).build();
			list = qb.list();

			 return list;
		   
	   }
}
