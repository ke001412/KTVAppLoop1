package com.sz.ktv.db;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.io.SequenceInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;

import com.sz.ktv.R;
import com.sz.ktv.application.MyApplication;
import com.sz.ktv.util.Global;
import com.sz.ktv.util.writeFile.Write;

public class DataBase {

	public static String downPath = "http://www.q5vod.com/song/";

	/**
	 * 硬盘歌曲路径
	 */
	public static final String MNT_SDA_SONG_PATH = "/mnt/sda/sda2/song/";
	/**
	 * 硬盘歌曲路径
	 */
	public static final String MNT_SDA_SINGER_PATH = "/mnt/sda/sda2/singer/";

	/**
	 * 硬盘路径
	 */
	public static final String MNT_SDA_DIR = "/mnt/sda/sda2/";
	/**
	 * U盘路径
	 */
	public static final String MNT_SDA_U_PAN_DIR = "/mnt/sda/sda2/songtest/";

	public static String keySong = "";
	public static int keyType = -1;

	private final static String NEW_SONG_FILE_NAME = "new_song.txt";
	private final static String MNT_SONG_TMP_FILE_NAME = "mnt_song_tmp.txt";

	private final static String SINGER_FILE = "song_file/singer.txt";
	public final static String SONG_FILE = "song_file/song_default.txt";
	private final static String SONG_NEW_FILE = "song_file/newsong.txt";
	public final static String HEAD_FILE_DIR = "singer_head_pic/";
	public final static String SONG_GONGBO_FILE = "gongbo_song.txt";

	public static final String SONG_FILE_COPY = "song_default.txt";
	public static List<Song> songLocalList = new ArrayList<Song>();

	// private static List<Song> songLocalList = new ArrayList<Song>();
	public static List<Song> songCloudList = new ArrayList<Song>();
	/**
	 * 准备下载的歌曲
	 */
	public static List<Song> songDownloaddList = new ArrayList<Song>();
	/**
	 * 下载完成的歌曲
	 */
	public static List<Song> songDownSuccessloaddList = new ArrayList<Song>();

	public static List<Singer> singerList = new ArrayList<Singer>();

	public static List<Song> newSongList = new ArrayList<Song>();

	public static List<Song> gongboList = new ArrayList<Song>();

	public static final int SINGER_TYPE_DALU_NAN = 1;
	public static final int SINGER_TYPE_DALU_NV = 2;
	public static final int SINGER_TYPE_GANGTAI_NAN = 3;
	public static final int SINGER_TYPE_GANGTAI_NV = 4;
	public static final int SINGER_TYPE_HAIWAI_NAN = 5;
	public static final int SINGER_TYPE_HAIWAI_NV = 6;
	public static final int SINGER_TYPE_LUEDUI_ZUHE = 7;
	public static final int SINGER_TYPE_QITA = 8;
	public static final int SINGER_TYPE_ALL = -1;

	public static final String SONG_TYPE_GUOYU = "1";
	public static final String SONG_TYPE_YUEYU = "2";
	public static final String SONG_TYPE_MINNANYU = "3";
	public static final String SONG_TYPE_YINGYU = "4";
	public static final String SONG_TYPE_RIYU = "5";
	public static final String SONG_TYPE_HANYU = "6";
	public static final String SONG_TYPE_QITA = "7";

	/**
	 * 歌曲分类
	 */
	public static final String SONG_TYPE_HECHANGE = "1";
	public static final String SONG_TYPE_SHENGRI = "2";
	public static final String SONG_TYPE_DIANYING = "3";
	public static final String SONG_TYPE_XIANGSHENG = "4";
	public static final String SONG_TYPE_GEMING = "5";
	public static final String SONG_TYPE_HUAIJIU = "6";
	public static final String SONG_TYPE_XIQING = "7";
	public static final String SONG_TYPE_XIQU = "8";
	public static final String SONG_TYPE_ERTONG = "9";

	/**
	 * 舞曲类型
	 */
	public static final String SONG_WUQU_CHUANGSHAO = "1";
	public static final String SONG_WUQU_DISHIGAO = "2";
	public static final String SONG_WUQU_QIAQIA = "3";
	public static final String SONG_WUQU_PULUSHI = "4";
	public static final String SONG_WUQU_TANGE = "5";
	public static final String SONG_WUQU_HUAERZI = "6";
	public static final String SONG_WUQU_JITEBA = "7";
	public static final String SONG_WUQU_MANYAO = "8";

	/**
	 * 电影分类
	 */
	public static final String SONG_DIANYING_DZ = "1";
	public static final String SONG_DIANYING_XJ = "2";
	public static final String SONG_DIANYING_GZ = "3";
	public static final String SONG_DIANYING_FJ = "4";
	public static final String SONG_DIANYING_KH = "5";
	public static final String SONG_DIANYING_ZZ = "6";
	public static final String SONG_DIANYING_DH = "7";
	public static final String SONG_DIANYING_QT = "8";

	/**
	 * 流行分类
	 */
	public static final String SONG_LIUXING_WSGS = "1";
	public static final String SONG_LIUXING_ZGHSY = "2";
	public static final String SONG_LIUXING_ZGZQY = "3";
	public static final String SONG_LIUXING_ZGHGQ = "4";
	public static final String SONG_LIUXING_ZGYC = "5";
	public static final String SONG_LIUXING_QT = "6";
	public static final String SONG_LIUXING_LX = "7";
	public static final String SONG_LIUXING_JD = "8";
	public static final String SONG_LIUXING_ZMHS = "a";
	public static final String SONG_LIUXING_MXXDD = "b";
	public static final String SONG_LIUXING_MMGW = "c";

	/**
	 * 是否登录
	 */
	public static boolean isLoginSuccess = true;

	private static String getPath(Context context, int type) {
		String txt_path = context.getFilesDir().getParent() + "/";
		String path = "";
		if (type == 0) {
			path = txt_path + SONG_NEW_FILE;
		} else if (type == 1) {
			path = txt_path + SINGER_FILE;
		} else {
			path = txt_path + SONG_FILE;
		}
		return path;
	}

	/**
	 * 保存公播歌曲
	 * 
	 * @param context
	 * @param song
	 * @return
	 */
	public static void writeGongBoSongToFile(Context context, Song song) {
		reLoadGongBoFlag = true;
		// 歌曲no|歌曲名称
		String txt_path = context.getFilesDir().getParent() + "/"
				+ SONG_GONGBO_FILE;
		FileWriter file = null;
		String songStr = song.getSong_no() + "|" + song.getSong_name() + "\n";
		try {
			file = new FileWriter(txt_path, true);
			file.write(songStr);
			file.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != file) {
				try {
					file.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * 判断公播歌曲是否应添加
	 * 
	 * @param song
	 * @return
	 */
	public static boolean isGongBoSongExist(Context context, Song song) {
		// 歌曲no|歌曲名称
		InputStreamReader inputReader = null;
		BufferedReader bufReader = null;

		String songNo = song.getSong_no();
		try {
			String txt_path = context.getFilesDir().getParent() + "/"
					+ SONG_GONGBO_FILE;
			if (fileIsExists(txt_path)) {
				inputReader = new InputStreamReader(new FileInputStream(
						txt_path), "GBK");
			}
			bufReader = new BufferedReader(inputReader);
			String line = "";

			while ((line = bufReader.readLine()) != null) {

				if (!TextUtils.isEmpty(line)) {
					if (line.contains(songNo)) {
						return true;
					}
				}

			}
		} catch (Exception e) {

		}
		return false;
	}

	/**
	 * 第一次默认重新加载公播歌曲
	 */
	public static boolean reLoadGongBoFlag = true;

	public boolean isNewAdd = false;

	/**
	 * 添加新歌曲
	 * 
	 * @param song
	 */
	public void addNewSongList(Song song) {
		isNewAdd = true;
		newSongList.add(song);
		songLocalList.add(song);
	}

	/**
	 * 获取公播歌曲列表
	 * 
	 * @param context
	 * @return
	 */
	public static List<Song> getGongBoSongList(Context context) {
		// 歌曲no|歌曲名称
		InputStreamReader inputReader = null;
		BufferedReader bufReader = null;
		if (null == gongboList || gongboList.size() <= 0 || reLoadGongBoFlag) {

			gongboList.clear();
			gongboList = new ArrayList<Song>();

			try {
				String txt_path = context.getFilesDir().getParent() + "/"
						+ SONG_GONGBO_FILE;
				if (fileIsExists(txt_path)) {
					inputReader = new InputStreamReader(new FileInputStream(
							txt_path), "utf-8");
				}
				bufReader = new BufferedReader(inputReader);
				String line = "";

				while ((line = bufReader.readLine()) != null) {
					Song song = new Song();
					if (!TextUtils.isEmpty(line)) {
						String[] strAry = line.split("\\|");
						String songNo = strAry[0];
						String songName = strAry[1];
						song.setSong_name(songName);
						song.setSong_no(songNo);
						gongboList.add(song);
					}

				}
				reLoadGongBoFlag = false;
			} catch (Exception e) {

				reLoadGongBoFlag = true;
			}

		}

		return gongboList;

	}

	public static void zhidingGongBo(Song song) {
		reLoadGongBoFlag = true;
		if (null != gongboList && gongboList.size() > 0) {
			int size = gongboList.size();
			for (int i = 0; i < size; i++) {
				Song tmp = gongboList.get(i);
				if (tmp.getSong_no().equalsIgnoreCase(song.getSong_no())) {
					gongboList.remove(i);
					break;
				}
			}
			gongboList.add(0, song);
			writeGongBoSongListToFile(gongboList);
		}
	}

	/**
	 * 删除公播歌曲
	 * 
	 * @param song
	 * @return
	 */
	public static boolean delteGongboSong(Song song) {
		reLoadGongBoFlag = true;
		if (null == gongboList || gongboList.size() <= 0) {
			return false;

		}
		int size = gongboList.size();
		for (int i = 0; i < size; i++) {
			Song tmp = gongboList.get(i);
			String tmpNo = tmp.getSong_no();
			if (tmpNo.equalsIgnoreCase(song.getSong_no())) {
				gongboList.remove(i);

				break;
			}
		}
		return writeGongBoSongListToFile(gongboList);
	}

	public static void daluanGongboList() {
		reLoadGongBoFlag = true;
		Collections.shuffle(gongboList);
		writeGongBoSongListToFile(gongboList);
	}

	/**
	 * 保存公播歌曲
	 * 
	 * @param context
	 * @param song
	 * @return
	 */
	public static boolean writeGongBoSongListToFile(List<Song> gsongList) {

		reLoadGongBoFlag = true;
		// 歌曲no|歌曲名称
		String txt_path = Global.currentActivity.getFilesDir().getParent()
				+ "/" + SONG_GONGBO_FILE;
		FileWriter file = null;
		StringBuffer buffer = new StringBuffer();
		try {
			file = new FileWriter(txt_path, false);
			int size = gsongList.size();
			for (int i = 0; i < size; i++) {
				Song tmp = gsongList.get(i);
				String songStr = tmp.getSong_no() + "|" + tmp.getSong_name()
						+ "\n";
				buffer.append(songStr);
			}
			file.write(buffer.toString());
			file.flush();
			file.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (null != file) {
				try {
					file.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public static boolean fileIsExists(String path) {
		try {
			File f = new File(path);
			if (!f.exists()) {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static void loadTxt2List(Context context, int type) {
		Log.d("zhuxl", " ---------------- start -------------------- ");

		String defaultFileName = "";
		if (type == 0) {
			defaultFileName = SONG_NEW_FILE;
			newSongList.clear();
		} else if (type == 1) {
			defaultFileName = SINGER_FILE;
			singerList.clear();
		} else {
			defaultFileName = SONG_FILE;
			songLocalList.clear();
			songLocalList.clear();
			songCloudList.clear();
		}
		InputStreamReader inputReader = null;
		BufferedReader bufReader = null;
		try {

			String strPath = getPath(context, type);
			if (fileIsExists(strPath)) {
				inputReader = new InputStreamReader(
						new FileInputStream(strPath), "GBK");
			} else {
				inputReader = new InputStreamReader(Global.currentActivity
						.getResources().getAssets().open(defaultFileName),
						"GBK");
			}
			bufReader = new BufferedReader(inputReader);
			String line = "";

			while ((line = bufReader.readLine()) != null) {
				if (type == 0) {

					insertToNewSongList(line);

				} else if (type == 1) {

					insertToSingerList(line);

				} else if (type == 2) {

					insertToSongList(line);

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufReader != null) {
					bufReader.close();
				}
				if (inputReader != null) {
					inputReader.close();
				}
			} catch (IOException e) {

				e.printStackTrace();
			}
		}

	}

	private static String getSingerId(String singerName) {
		if (null != singerList && singerList.size() > 0) {
			int size = singerList.size();
			for (int i = 0; i < size; i++) {
				Singer singer = singerList.get(i);
				String name = singer.getSinger_name();
				String singId = singer.getSinger_no();
				if (singerName.equals(name)) {
					return singId;
				}
			}
		}
		return null;
	}

	public static Map<String, String> singerPicMap = new HashMap<String, String>();
	public static Map<String, Drawable> singerDrawableMap = new HashMap<String, Drawable>();

	public static void updateSong(Song song) {
	}

	/**
	 * 修改歌曲
	 * 
	 * @param song
	 */
	public boolean updateSongDefaultFile(Song xsong) {

		long time1 = System.currentTimeMillis();
		FileWriter fw = null;
		try {
			String fileDir = MyApplication.getAppContext().getFilesDir()
					.getParent()
					+ "/";
			File file = new File(fileDir + "" + MNT_SONG_TMP_FILE_NAME);
			if (file.exists()) {
				file.delete();
			}
			fw = new FileWriter(fileDir + MNT_SONG_TMP_FILE_NAME, true);// 表示在有此文件时不会重建该文件并且输入的数据会进行续写
			// 歌曲No号|语言|歌名|首拼|字数|歌星|音轨|左音量|右音量|歌曲分类|舞曲类型|电影分类|流行分类|下载标志位|时间|歌曲文件名称
			int size = songLocalList.size();

			for (int i = 0; i < size; i++) {
				Song song = songLocalList.get(i);
				String tmpString = "";
				if (song.getSong_no().equals(xsong.getSong_no())) {
					tmpString = xsong.getSong_no() + "|"
							+ xsong.getSong_language() + "|"
							+ xsong.getSong_name() + "|"
							+ xsong.getSong_first_name() + "|"
							+ xsong.getSong_words_count() + "|"
							+ xsong.getSong_singer_name() + "|"
							+ xsong.getSong_track() + "|"
							+ xsong.getSong_left_volume() + "|"
							+ xsong.getSong_right_volume() + "|"
							+ xsong.getSong_class() + "|"
							+ xsong.getSong_dance_type() + "|"
							+ xsong.getSong_film_type() + "|"
							+ xsong.getSong_popular_type() + "|"
							+ xsong.getSong_download_flag() + "|"
							+ xsong.getSong_time() + "|"
							+ xsong.getSong_file_name();
					tmpString = tmpString + "\n";

				} else {
					tmpString = song.getSong_no() + "|"
							+ song.getSong_language() + "|"
							+ song.getSong_name() + "|"
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
							+ song.getSong_time() + "|"
							+ song.getSong_file_name();
					tmpString = tmpString + "\n";
				}

				fw.write(tmpString + "");

			}
			long time2 = System.currentTimeMillis();
			Write.debug("文件生成花费时间： " + (time2 - time1));
			File mntFle = new File(fileDir + "" + SONG_FILE_COPY);
			if (mntFle.exists()) {
				mntFle.delete();
			}
			file.renameTo(mntFle);

			return true;
		} catch (IOException e) {
			System.out.println(e.toString());
			return false;
		} finally {
			try {
				if (fw != null) {
					fw.close();
				}
			} catch (IOException e) {
				System.out.println(e.toString());
			}

		}

	}

	// private static Map<String, List<Song>> songLanguageMap = new
	// HashMap<String, List<Song>>();

	/**
	 * 通过语言类型获取歌曲列表
	 * 
	 * @param songList2
	 * 
	 * @param language
	 * @return
	 */
	public static List<Song> getAllSongByLanguage(List<Song> songList2,
			String language) {

		List<Song> mSongList = new ArrayList<Song>();
		List<Song> tempList = new ArrayList<Song>();

		// tempList = songLanguageMap.get(language);
		// if (null != tempList && tempList.size() > 0) {
		// return tempList;
		// }
		long time1 = System.currentTimeMillis();
		if (null != songList2 && songList2.size() > 0) {
			int size = songList2.size();
			for (int i = 0; i < size; i++) {
				Song song = songList2.get(i);
				String sLanguage = song.getSong_language();
				if (sLanguage.equals(language)) {
					mSongList.add(song);
				}
			}
		}
		long time2 = System.currentTimeMillis();
		System.out.println("查询耗时: " + (time2 - time1));
		// songLanguageMap.put(language, mSongList);
		return mSongList;
	}

	// private static Map<String, List<Song>> songTypeMap = new HashMap<String,
	// List<Song>>();

	/**
	 * 通过歌曲分类获取歌曲列表
	 * 
	 * @param songList2
	 * 
	 * @param language
	 * @return
	 */
	public static List<Song> getAllSongByFenLei(List<Song> songList2,
			String language) {

		List<Song> mSongList = new ArrayList<Song>();
		List<Song> tempList = new ArrayList<Song>();

		// tempList = songTypeMap.get(language);
		if (null != tempList && tempList.size() > 0) {
			return tempList;
		}
		if (null != songList2 && songList2.size() > 0) {
			int size = songList2.size();
			for (int i = 0; i < size; i++) {
				Song song = songList2.get(i);
				String sLanguage = song.getSong_class();
				if (sLanguage.equals(language)) {
					mSongList.add(song);
				}
			}
		}
		// songTypeMap.put(language, mSongList);
		return mSongList;
	}

	// private static Map<String, List<Song>> songDianYingTypeMap = new
	// HashMap<String, List<Song>>();

	/**
	 * 通过电影分类获取歌曲列表
	 * 
	 * @param songList2
	 * 
	 * @param language
	 * @return
	 */
	public static List<Song> getAllSongByDianYingFenLei(List<Song> songList2,
			String language) {

		List<Song> mSongList = new ArrayList<Song>();
		List<Song> tempList = new ArrayList<Song>();

		// tempList = songDianYingTypeMap.get(language);
		if (null != tempList && tempList.size() > 0) {
			return tempList;
		}
		if (null != songList2 && songList2.size() > 0) {
			int size = songList2.size();
			for (int i = 0; i < size; i++) {
				Song song = songList2.get(i);
				String sLanguage = song.getSong_film_type();
				if (sLanguage.equals(language)) {
					mSongList.add(song);
				}
			}
		}
		// songDianYingTypeMap.put(language, mSongList);
		return mSongList;
	}

	// private static Map<String, List<Song>> songLiuXingTypeMap = new
	// HashMap<String, List<Song>>();

	/**
	 * 通过流行分类获取歌曲列表
	 * 
	 * @param songList2
	 * 
	 * @param language
	 * @return
	 */
	public static List<Song> getAllSongByLiuXingFenLei(List<Song> songList2,
			String language) {

		List<Song> mSongList = new ArrayList<Song>();
		List<Song> tempList = new ArrayList<Song>();

		// tempList = songLiuXingTypeMap.get(language);
		if (null != tempList && tempList.size() > 0) {
			return tempList;
		}
		if (null != songList2 && songList2.size() > 0) {
			int size = songList2.size();
			for (int i = 0; i < size; i++) {
				Song song = songList2.get(i);
				String sLanguage = song.getSong_popular_type();
				if (sLanguage.equals(language)) {
					mSongList.add(song);
				}
			}
		}
		// songLiuXingTypeMap.put(language, mSongList);
		return mSongList;
	}

	// private static Map<String, List<Song>> songWuQuTypeMap = new
	// HashMap<String, List<Song>>();

	/**
	 * 通过舞曲分类获取歌曲列表
	 * 
	 * @param songList2
	 * 
	 * @param language
	 * @return
	 */
	public static List<Song> getAllSongByWuQuFenLei(List<Song> songList2,
			String language) {

		List<Song> mSongList = new ArrayList<Song>();
		List<Song> tempList = new ArrayList<Song>();

		// tempList = songWuQuTypeMap.get(language);
		if (null != tempList && tempList.size() > 0) {
			return tempList;
		}
		if (null != songList2 && songList2.size() > 0) {
			int size = songList2.size();
			for (int i = 0; i < size; i++) {
				Song song = songList2.get(i);
				String sLanguage = song.getSong_dance_type();
				if (sLanguage.equals(language)) {
					mSongList.add(song);
				}
			}
		}
		// songWuQuTypeMap.put(language, mSongList);
		return mSongList;
	}

	public static List<Singer> getAllSingerListByShouPin(String shouPin) {
		List<Singer> list = new ArrayList<Singer>();
		shouPin = shouPin.toUpperCase();

		if (null == singerList || singerList.size() <= 0) {
			return null;
		}
		for (Singer singerBean : singerList) {
			String shoup = singerBean.getSinger_pinyin();
			if (shoup.startsWith(shouPin)) {
				list.add(singerBean);
			}
		}

		return list;
	}

	public static List<Singer> getAllSingerListByName(String name) {
		List<Singer> list = new ArrayList<Singer>();

		if (null == singerList || singerList.size() <= 0) {
			return null;
		}
		for (Singer singerBean : singerList) {
			String shoup = singerBean.getSinger_name();
			if (shoup.startsWith(name)) {
				list.add(singerBean);
			}
		}

		return list;
	}

	/**
	 * 
	 * @param type
	 *            歌手类型
	 * @return
	 */
	public static List<Singer> getAllSingerList(String type) {
		List<Singer> list = new ArrayList<Singer>();
		if (null == singerList || singerList.size() <= 0) {
			return null;
		}
		int size = singerList.size();
		for (int i = 0; i < size; i++) {
			Singer singer_item = singerList.get(i);
			if (singer_item.getSinger_type().equals(type)) {
				list.add(singer_item);
			}
		}

		return list;
	}

	/**
	 *  
	 */
	static final DataBase INSTANCE = new DataBase();

	public static DataBase getInstance() {
		return DataBase.INSTANCE;
	}

	/**
	 *  
	 */
	private DataBase() {
	}

	/**
	 * 已点歌曲
	 */
	private static LinkedList<Song> yiDianList = new LinkedList<Song>();
	/**
	 * 已播歌曲
	 */
	private static LinkedList<Song> yiBoList = new LinkedList<Song>();

	/**
	 * 添加已点的歌曲
	 * 
	 * @param song
	 */
	public void addYiDianSong(Song song) {

		yiDianList.add(song);
	}

	/**
	 * 检查歌曲是否已经点过
	 * 
	 * @param song
	 * @return
	 */
	public boolean checkSongExist(Song song) {
		if (null != yiDianList && yiDianList.size() > 0) {
			int size = yiDianList.size();
			for (int i = 0; i < size; i++) {
				Song songs = yiDianList.get(i);
				String soingsID = songs.getSong_no();
				if (song.getSong_no().equalsIgnoreCase(soingsID)) {
					return true;
				}
			}

		}

		return false;
	}

	/**
	 * 从已点歌曲列表取出一首歌曲
	 * 
	 * @return
	 */
	public Song getYiDianSong() {
		if (yiDianList.size() <= 0) {
			return null;
		}
		Song song = yiDianList.removeFirst();
		yiBoSongList.add(song);
		return song;
	}

	/**
	 * 从已点歌曲列表取出一首歌曲
	 * 
	 * @return
	 */
	public Song getGongBoSong() {
		if (gongboList.size() <= 0) {
			return null;
		}

		Song song = gongboList.get(0);
		gongboList.remove(0);
		yiBoSongList.add(song);
		return song;
	}

	/**
	 * 获取已点歌曲的数量
	 * 
	 * @return
	 */
	public int getYiDianSongListSize() {
		return yiDianList.size();
	}

	/**
	 * 获取所有的已点歌曲列表
	 * 
	 * @return
	 */
	public LinkedList<Song> getYiDianList() {

		return yiDianList;
	}

	/**
	 * 打乱已点歌曲
	 */
	public void yidianDaLuan() {
		Collections.shuffle(yiDianList);
	}

	/**
	 * 插播，置顶歌曲
	 * 
	 * @param song
	 */
	public void yiDianChaBo(Song song) {
		String songNo = song.getSong_no();
		int size = yiDianList.size();
		for (int i = 0; i < size; i++) {
			Song tempSong = yiDianList.get(i);
			String sId = tempSong.getSong_no();
			if (songNo.equalsIgnoreCase(sId)) {
				yiDianList.remove(i);
				break;
			}
		}
		yiDianList.addFirst(song);
	}

	private static LinkedList<Song> yiBoSongList = new LinkedList<Song>();

	/**
	 * 获取已经播放的歌曲列表
	 * 
	 * @return
	 */
	public LinkedList<Song> getYiBoSongList() {

		return yiBoSongList;
	}

	/**
	 * 删除一首已点的歌曲
	 * 
	 * @param song
	 */
	public void yiDianDelete(Song song) {
		String songNo = song.getSong_no();
		int size = yiDianList.size();
		for (int i = 0; i < size; i++) {
			Song tempSong = yiDianList.get(i);
			String sId = tempSong.getSong_no();
			if (songNo.equalsIgnoreCase(sId)) {
				// 添加到已经播放的列表
				yiBoSongList.add(tempSong);
				yiDianList.remove(i);
				break;
			}
		}

	}

	/**
	 * 通过歌星名称获取所有歌曲
	 * 
	 * @param songList2
	 * 
	 * @param singerName
	 * @return
	 */
	public static List<Song> getAllSongBySingerName(List<Song> songList2,
			String singerName) {

		List<Song> list = new ArrayList<Song>();

		if (null != songList2 && songList2.size() > 0) {
			int size = songList2.size();
			for (int i = 0; i < size; i++) {
				Song song = songList2.get(i);
				String singer = song.getSong_singer_name();
				if (singer.equalsIgnoreCase(singerName)) {
					list.add(song);
				}
			}

		}

		return list;
	}

	/**
	 * 通过歌曲首字母获取所有歌曲
	 * 
	 * @param songList2
	 * 
	 * @param singerName
	 * @return
	 */
	public static List<Song> getAllSongBySongShouPin(List<Song> songList2,
			String shouPin) {

		List<Song> list = new ArrayList<Song>();
		shouPin = shouPin.toUpperCase();
		if (null != songList2 && songList2.size() > 0) {
			int size = songList2.size();
			for (int i = 0; i < size; i++) {
				Song song = songList2.get(i);
				String singer = song.getSong_first_name();
				if (singer.startsWith(shouPin)) {
					list.add(song);
				}
			}

		}

		return list;
	}

	/**
	 * 通过字数获取所有歌曲
	 * 
	 * @param songList2
	 * 
	 * @param singerName
	 * @return
	 */
	public static List<Song> getAllSongByWordsCount(List<Song> songList2,
			String zishu) {
		List<Song> list = new ArrayList<Song>();
		try {
			int mZishu = Integer.parseInt(zishu);
			if (null != songList2 && songList2.size() > 0) {
				int size = songList2.size();
				for (int i = 0; i < size; i++) {
					Song song = songList2.get(i);
					String singer = song.getSong_words_count();
					int songCount = Integer.parseInt(singer);

					if (mZishu == 9 && songCount >= 9) {
						list.add(song);
					} else if (mZishu == songCount) {
						list.add(song);
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static List<Song> loadSongList(Activity context) {
		InputStreamReader inputReader = null;
		BufferedReader bufReader = null;
		try {
			String filePath = context.getFilesDir().getParent() + "/"
					+ SONG_FILE_COPY;
			File mntFle = new File(filePath);
			// 使用内存里面的歌曲
			if (mntFle.exists()) {
				inputReader = new InputStreamReader(
						new FileInputStream(mntFle), "GBK");
			} else {
				inputReader = new InputStreamReader(context.getResources()
						.getAssets().open(SONG_FILE), "GBK");
			}
			bufReader = new BufferedReader(inputReader);
			String line = "";

			while ((line = bufReader.readLine()) != null) {

				insertToSongList(line);

			}
			return songLocalList;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufReader != null) {
					bufReader.close();
				}
				if (inputReader != null) {
					inputReader.close();
				}
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 加载新歌曲
	 * 
	 * @param context
	 * @return
	 */
	public static List<Song> loadNweSongList(Activity context) {
		InputStreamReader inputReader = null;
		BufferedReader bufReader = null;
		try {
			String filePath = MNT_SDA_DIR + "/" + NEW_SONG_FILE_NAME;
			File mntFle = new File(filePath);
			// 如果硬盘存在歌曲文件，则使用硬盘的歌曲文件，否则使用默认的歌曲文件
			if (mntFle.exists()) {
				inputReader = new InputStreamReader(
						new FileInputStream(mntFle), "GBK");

			} else {
				return null;
			}
			bufReader = new BufferedReader(inputReader);
			String line = "";

			while ((line = bufReader.readLine()) != null) {

				insertToNewSongList(line);

			}
			return newSongList;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufReader != null) {
					bufReader.close();
				}
				if (inputReader != null) {
					inputReader.close();
				}
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
		return null;
	}

	public static void getAllSingerHead(Context context) {
		Bitmap bitmap = null;

		AssetManager assetManager = context.getAssets();

		if (null != singerList && singerList.size() > 0) {
			int size = singerList.size();
			String headFilePath = DataBase.HEAD_FILE_DIR + ""
					+ "default_singer" + ".png";
			InputStream is = null;

			for (int i = 0; i < size; i++) {
				Singer singer = singerList.get(i);
				String fileName = singer.getSinger_no();
				String sinerName = singer.getSinger_name();
				if (!TextUtils.isEmpty(fileName)) {
					fileName = "default_singer";
					headFilePath = DataBase.HEAD_FILE_DIR + "" + fileName
							+ ".jpg";
					try {
						is = assetManager.open(headFilePath);
						bitmap = BitmapFactory.decodeStream(is);
					} catch (Exception e) {
						//
						bitmap = BitmapFactory.decodeResource(
								context.getResources(),
								R.drawable.default_singer);

					}

					singerDrawableMap
							.put(sinerName, new BitmapDrawable(bitmap));
				}

			}
			try {
				is.close();
			} catch (Exception e) {
			}
		}

	}

	public static List<Singer> loadSingerList(Activity context) {
		InputStreamReader inputReader = null;
		BufferedReader bufReader = null;
		try {
			inputReader = new InputStreamReader(context.getResources()
					.getAssets().open(SINGER_FILE), "GBK");

			bufReader = new BufferedReader(inputReader);
			String line = "";

			while ((line = bufReader.readLine()) != null) {

				insertToSingerList(line);

			}
			return singerList;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufReader != null) {
					bufReader.close();
				}
				if (inputReader != null) {
					inputReader.close();
				}
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
		return null;
	}

	public static void delSongListByMap(Map<String, Song> delMap) {
		System.out.println("DeLMAp:::::" + delMap.toString());
		for (int i = 0; i < songLocalList.size(); i++) {
			Song song = songLocalList.get(i);
			String songNo = song.getSong_no();
			Song tmpSong = delMap.get(songNo);// getCheckDelSong(delMap,
												// songNo);
			if (null != tmpSong) {
				System.out.println("删除的歌曲: " + song.getSong_name());
				songLocalList.remove(i);
			}
		}
	}

	/**
	 * 生成删除后的文件
	 * 
	 * @param delMap
	 * @return
	 */
	public static boolean delSongAndWriteFile(Map<String, Song> delMap) {

		long time1 = System.currentTimeMillis();
		FileWriter fw = null;
		try {
			String fileDir = MyApplication.getAppContext().getFilesDir()
					.getParent()
					+ "/";

			File file = new File(fileDir + "" + MNT_SONG_TMP_FILE_NAME);
			if (file.exists()) {
				file.delete();
			}
			fw = new FileWriter(fileDir + MNT_SONG_TMP_FILE_NAME, true);// 表示在有此文件时不会重建该文件并且输入的数据会进行续写
			// 歌曲No号|语言|歌名|首拼|字数|歌星|音轨|左音量|右音量|歌曲分类|舞曲类型|电影分类|流行分类|下载标志位|时间|歌曲文件名称
			int size = songLocalList.size();

			for (int i = 0; i < size; i++) {
				Song song = songLocalList.get(i);
				// String songNo = song.getSong_no();
				// Song tmpSong = delMap.get(songNo) ;//getCheckDelSong(delMap,
				// songNo);
				// if(null ==tmpSong)
				// {
				// StringBuffer songBuffer = new StringBuffer();
				String tmpString = song.getSong_no() + "|"
						+ song.getSong_language() + "|" + song.getSong_name()
						+ "|" + song.getSong_first_name() + "|"
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
						+ song.getSong_time() + "|" + song.getSong_file_name();
				// songBuffer.append(tmpString).append("\n");
				tmpString = tmpString + "\n";
				fw.write(tmpString + "");
				// }else {
				// song.setSong_del(true);
				// songList.remove(i);
				// }
			}
			long time2 = System.currentTimeMillis();
			Write.debug("文件生成花费时间： " + (time2 - time1));
			File mntFle = new File(fileDir + "" + SONG_FILE_COPY);
			if (mntFle.exists()) {
				mntFle.delete();
			}
			file.renameTo(mntFle);

			return true;
		} catch (IOException e) {
			System.out.println(e.toString());
			return false;
		} finally {
			try {
				if (fw != null) {
					fw.close();
				}
			} catch (IOException e) {
				System.out.println(e.toString());
			}

		}
	}

	/**
	 * 生成删除后的文件
	 * 
	 * @param delMap
	 * @return
	 */
	public static boolean delSongAndWriteFileByNio(Map<String, Song> delMap) {

		long time1 = System.currentTimeMillis();
		// FileWriter fw = null ;

		int bufSize = 2048;
		File file = new File(MNT_SDA_DIR + "tmp_delete_song.txt");
		FileChannel fcout = null;
		ByteBuffer wBuffer = ByteBuffer.allocateDirect(bufSize);

		try {
			fcout = new RandomAccessFile(file, "rws").getChannel();
			if (file.exists()) {
				file.delete();
			}
			file.createNewFile();
			// fw = new FileWriter( MNT_SDA_DIR +"tmp_delete_song.txt",true)
			// ;//表示在有此文件时不会重建该文件并且输入的数据会进行续写
			// 歌曲No号|语言|歌名|首拼|字数|歌星|音轨|左音量|右音量|歌曲分类|舞曲类型|电影分类|流行分类|下载标志位|时间|歌曲文件名称
			int size = songLocalList.size();
			StringBuffer songBuffer = new StringBuffer();
			for (int i = 0; i < songLocalList.size(); i++) {
				Song song = songLocalList.get(i);
				String songNo = song.getSong_no();
				Song tmpSong = delMap.get(songNo);// getCheckDelSong(delMap,
													// songNo);
				if (null == tmpSong) {
					//
					String tmpString = song.getSong_no() + "|"
							+ song.getSong_language() + "|"
							+ song.getSong_name() + "|"
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
							+ song.getSong_time() + "|"
							+ song.getSong_file_name();
					songBuffer.append(tmpString).append("\n");
					// tmpString = tmpString +"\n";

				} else {
					song.setSong_del(true);
					songLocalList.remove(i);
				}
			}
			fcout.write(ByteBuffer.wrap(songBuffer.toString().getBytes()),
					fcout.size());
			long time2 = System.currentTimeMillis();
			Write.debug("文件生成花费时间： " + (time2 - time1));

			return true;
		} catch (IOException e) {
			System.out.println(e.toString());
			return false;
		} finally {
			try {
				if (fcout != null) {
					fcout.close();
				}
			} catch (IOException e) {
				System.out.println(e.toString());
			}

		}
	}

	public static void writeFileByLine(FileChannel fcout, String line) {
		try {
			fcout.write(ByteBuffer.wrap(line.getBytes()), fcout.size());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static Song getCheckDelSong(List<Song> list, String songNo) {
		int size = list.size();
		for (int i = 0; i < size; i++) {
			Song delSong = list.get(i);
			if (songNo.equals(delSong.getSong_no())) {
				list.remove(i);
				return delSong;
			}
		}
		return null;

	}

	private static void insertToSongList(String line) {
		// 歌曲No号|语言|歌名|首拼|字数|歌星|音轨|画面类型|左音量|右音量|歌曲分类|舞曲类型|电影分类|流行分类|歌曲文件名称
		// 0000020|3|从来不曾有这呢孤单|clbcyzngd|9|杨烈|1|#|100|100|#|#|#|6|1|V0000020.mpg
		Song song_item = new Song();

		if (!TextUtils.isEmpty(line)) {
			String[] song = line.split("\\|");
			if (song.length > 15) {

				// Log.d("wuming", "insertToSongList() song length = " +
				// song.length);
				song_item.setSong_no(song[0]);
				song_item.setSong_language(song[1]);
				song_item.setSong_name(song[2]);
				song_item.setSong_first_name(song[3]);
				song_item.setSong_words_count(song[4]);
				String singerName = song[5];
				song_item.setSong_singer_name(singerName);
				song_item.setSong_track(song[6]);
				song_item.setSong_left_volume(song[7]);
				song_item.setSong_right_volume(song[8]);
				song_item.setSong_class(song[9]);
				song_item.setSong_dance_type(song[10]);
				song_item.setSong_film_type(song[11]);
				song_item.setSong_popular_type(song[12]);// 13
				String downLoadFlag = song[13];
				song_item.setSong_download_flag(downLoadFlag);
				song_item.setSong_time(song[14]);
				song_item.setSong_file_name(song[15]);
				song_item.setSong_favourite_flag("0");

				System.out.println("S: " + song[2]);
				if ("1".equals(downLoadFlag)) {
					songLocalList.add(song_item);
				} else {
					songCloudList.add(song_item);
				}
			}

		}

	}

	private static void insertToNewSongList(String line) {
		// 歌曲No号|语言|歌名|首拼|字数|歌星|音轨|画面类型|左音量|右音量|歌曲分类|舞曲类型|电影分类|流行分类|歌曲文件名称
		// 0000020|3|从来不曾有这呢孤单|clbcyzngd|9|杨烈|1|#|100|100|#|#|#|6|1|V0000020.mpg
		Song song_item = new Song();

		if (!TextUtils.isEmpty(line)) {
			String[] song = line.split("\\|");
			if (song.length > 15) {

				// Log.d("wuming", "insertToSongList() song length = " +
				// song.length);
				song_item.setSong_no(song[0]);
				song_item.setSong_language(song[1]);
				song_item.setSong_name(song[2]);
				song_item.setSong_first_name(song[3]);
				song_item.setSong_words_count(song[4]);
				String singerName = song[5];
				song_item.setSong_singer_name(singerName);
				song_item.setSong_track(song[6]);
				song_item.setSong_left_volume(song[7]);
				song_item.setSong_right_volume(song[8]);
				song_item.setSong_class(song[9]);
				song_item.setSong_dance_type(song[10]);
				song_item.setSong_film_type(song[11]);
				song_item.setSong_popular_type(song[12]);// 13
				String downLoadFlag = song[13];
				song_item.setSong_download_flag(downLoadFlag);
				song_item.setSong_time(song[14]);
				song_item.setSong_file_name(song[15]);
				song_item.setSong_favourite_flag("0");

				System.out.println("NEWS: " + song[2]);
				newSongList.add(song_item);
			}

		}

	}

	private static void insertToSingerList(String line) {
		// 1_10000_刘德华_LDH
		Singer singer_item = new Singer();
		if (!TextUtils.isEmpty(line)) {
			String[] singer = line.split("\\|");
			if (singer.length > 3) {
				singer_item.setSinger_favourite_flag("0");
				singer_item.setSinger_type(singer[0]);
				singer_item.setSinger_no(singer[1]);
				singer_item.setSinger_name(singer[2]);
				singer_item.setSinger_pinyin(singer[3]);

				String name = singer[2];
				// Drawable drawable =
				// GetHeadUtil.getHead(singer[0],MyApplication.getAppContext(),
				// singer[1]);
				// if(null != drawable)
				// {
				// singerDrawableMap.put(name, drawable);
				// }
				String headFilePath = DataBase.MNT_SDA_SINGER_PATH + singer[0]
						+ "/" + singer[0] + "" + singer[1] + ".jpg";
				singerPicMap.put(name, headFilePath);

				singerList.add(singer_item);
			} else {
				Log.d("wuming", "insertToSingerDb() error singer, index = "
						+ singer[0]);
			}

		}

	}

	/**
	 * 根据歌名编号获取歌曲列表
	 * 
	 * @param songList2
	 * 
	 * @param songkey
	 * @return
	 */
	public static List<Song> getAllSongByNo(List<Song> songList2, String songkey) {

		List<Song> mSongList = new ArrayList<Song>();

		if (null != songList2 && songList2.size() > 0) {
			int size = songList2.size();
			for (int i = 0; i < size; i++) {
				Song song = songList2.get(i);
				String sLanguage = song.getSong_no();
				if (sLanguage.startsWith(songkey)) {
					mSongList.add(song);
				}
			}
		}

		return mSongList;

	}

	/**
	 * 歌曲名称获取song
	 * 
	 * @param songList2
	 * 
	 * @param songkey
	 * @return
	 */
	public static List<Song> getAllSongBySongName(List<Song> songList2,
			String songkey) {

		List<Song> list = new ArrayList<Song>();

		if (null != songList2 && songList2.size() > 0) {
			int size = songList2.size();
			for (int i = 0; i < size; i++) {
				Song song = songList2.get(i);
				String singer = song.getSong_name();
				if (singer.startsWith(songkey)) {
					list.add(song);
				}
			}

		}

		return list;

	}

	private static final String UPAN_SONG_TXT = "addsong.txt";

	public List<Song> loadUPanSongList() {

		InputStreamReader inputReader = null;
		BufferedReader bufReader = null;
		try {
			String filePath = MNT_SDA_U_PAN_DIR + UPAN_SONG_TXT;
			File mntFle = new File(filePath);
			// 如果硬盘存在歌曲文件，则使用硬盘的歌曲文件，否则使用默认的歌曲文件
			if (mntFle.exists()) {
				inputReader = new InputStreamReader(
						new FileInputStream(mntFle), "GBK");
			} else {
				return null;
			}
			bufReader = new BufferedReader(inputReader);
			String line = "";

			while ((line = bufReader.readLine()) != null) {

				insertUpanSongList(line);

			}
			return newSongList;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufReader != null) {
					bufReader.close();
				}
				if (inputReader != null) {
					inputReader.close();
				}
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
		return null;
	}

	private void insertUpanSongList(String line) {
		// 歌曲No号|语言|歌名|首拼|字数|歌星|音轨|画面类型|左音量|右音量|歌曲分类|舞曲类型|电影分类|流行分类|歌曲文件名称
		// 0000020|3|从来不曾有这呢孤单|clbcyzngd|9|杨烈|1|#|100|100|#|#|#|6|1|V0000020.mpg
		Song song_item = new Song();

		if (!TextUtils.isEmpty(line)) {
			String[] song = line.split("\\|");
			if (song.length > 15) {

				// Log.d("wuming", "insertToSongList() song length = " +
				// song.length);
				song_item.setSong_no(song[0]);
				song_item.setSong_language(song[1]);
				song_item.setSong_name(song[2]);
				song_item.setSong_first_name(song[3]);
				song_item.setSong_words_count(song[4]);
				String singerName = song[5];
				song_item.setSong_singer_name(singerName);
				song_item.setSong_track(song[6]);
				song_item.setSong_left_volume(song[7]);
				song_item.setSong_right_volume(song[8]);
				song_item.setSong_class(song[9]);
				song_item.setSong_dance_type(song[10]);
				song_item.setSong_film_type(song[11]);
				song_item.setSong_popular_type(song[12]);// 13
				String downLoadFlag = song[13];
				song_item.setSong_download_flag(downLoadFlag);
				song_item.setSong_time(song[14]);
				song_item.setSong_file_name(song[15]);
				song_item.setSong_favourite_flag("0");

				System.out.println("S: " + song[2]);
				addNewSongList(song_item);
			}

		}

	}

	public void saveUpanFile() {
		// 硬盘上面保存的U盘新增的批量歌曲
		String saveAddSongUpanFile = MNT_SDA_DIR + "/" + UPAN_SONG_TXT;
		// U盘中的addsong.txt
		String uPanfilePath = MNT_SDA_U_PAN_DIR + UPAN_SONG_TXT;
		String tmpPath = MNT_SDA_DIR + "/addsongtmp.txt";
		File saveAddSongFile = new File(saveAddSongUpanFile);
		File uPanFile = new File(uPanfilePath);
		File tmpFile = new File(tmpPath);
		if (saveAddSongFile.exists()) {
			// 如果硬盘上面已经有保存过u盘批量歌曲文件，则合并两个文件
			try {
				FileInputStream fis = new FileInputStream(saveAddSongFile);
				FileInputStream fis1 = new FileInputStream(uPanFile);

				InputStream sis = new SequenceInputStream(fis, fis1);

				InputStreamReader isr = new InputStreamReader(sis);

				FileOutputStream fos = new FileOutputStream(tmpFile, true);

				OutputStreamWriter osw = new OutputStreamWriter(fos);

				int c;
				while ((c = isr.read()) != -1) {
					osw.write((char) c);
				}

				fis1.close();
				isr.close();
				osw.close();
				saveAddSongFile.delete();
				tmpFile.renameTo(saveAddSongFile);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			// 否则拷贝一份到硬盘
			MyApplication.copyFile(uPanfilePath, saveAddSongUpanFile);
		}

	}

	// 将U盘的歌曲文件全部拷贝到硬盘的song目录
	public void copyUpanSongFile() {
		File file = new File(MNT_SDA_U_PAN_DIR);
		File fileAry[] = file.listFiles();
		if (null != fileAry) {
			int lenght = fileAry.length;
			for (int i = 0; i < lenght; i++) {
				File fileTmp = fileAry[i];
				String filePath = fileTmp.getPath();
				String fileName = fileTmp.getName();
				String prefix = fileName
						.substring(fileName.lastIndexOf(".") + 1);
				String toPath = MNT_SDA_SONG_PATH + "/" + fileName;
				if ("avi".equalsIgnoreCase(prefix)
						|| "mkv".equalsIgnoreCase(prefix)
						|| "mpg".equalsIgnoreCase(prefix)) {
					MyApplication.copyFile(filePath, toPath);
				}
			}
		}
	}

	private static final String MNT_NEW_TMP_SONG = "new_song_tmp.txt";

	/**
	 * 保存新歌文件
	 */
	public void saveNewSong() {

		// 如果有新增歌曲，则保存新歌文件
		FileWriter fw = null;
		if (isNewAdd) {

			try {
				int size = newSongList.size();

				String fileDir = MNT_SDA_DIR;// MyApplication.getAppContext().getFilesDir().getParent()
												// + "/";

				File file = new File(fileDir + "" + MNT_NEW_TMP_SONG);
				if (file.exists()) {
					file.delete();
				}
				fw = new FileWriter(fileDir + MNT_NEW_TMP_SONG, true);// 表示在有此文件时不会重建该文件并且输入的数据会进行续写
				// 歌曲No号|语言|歌名|首拼|字数|歌星|音轨|左音量|右音量|歌曲分类|舞曲类型|电影分类|流行分类|下载标志位|时间|歌曲文件名称

				for (int i = 0; i < size; i++) {
					Song song = newSongList.get(i);

					String tmpString = song.getSong_no() + "|"
							+ song.getSong_language() + "|"
							+ song.getSong_name() + "|"
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
							+ song.getSong_time() + "|"
							+ song.getSong_file_name();
					tmpString = tmpString + "\n";
					fw.write(tmpString + "");

				}

				File mntFle = new File(fileDir + "" + NEW_SONG_FILE_NAME);
				if (mntFle.exists()) {
					mntFle.delete();
				}
				file.renameTo(mntFle);

			} catch (Exception e) {

			} finally {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

	}

	/**
	 * 保存新歌文件
	 */
	public void saveAllSong(Context context) {

		// 如果有新增歌曲，则保存新歌文件
		FileWriter fw = null;
		if (isNewAdd) {

			try {
				List<Song> songList = new ArrayList<Song>();
				songList.addAll(songCloudList);
				songList.addAll(songLocalList);

				int size = songList.size();
				String filePath = context.getFilesDir().getParent() + "/"
						+ SONG_FILE_COPY;
				File file = new File(filePath);
				if (file.exists()) {
					file.delete();
				}
				fw = new FileWriter(filePath, true);// 表示在有此文件时不会重建该文件并且输入的数据会进行续写
				// 歌曲No号|语言|歌名|首拼|字数|歌星|音轨|左音量|右音量|歌曲分类|舞曲类型|电影分类|流行分类|下载标志位|时间|歌曲文件名称

				for (int i = 0; i < size; i++) {
					Song song = songList.get(i);

					String tmpString = song.getSong_no() + "|"
							+ song.getSong_language() + "|"
							+ song.getSong_name() + "|"
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
							+ song.getSong_time() + "|"
							+ song.getSong_file_name();
					tmpString = tmpString + "\n";
					fw.write(tmpString + "");

				}

			} catch (Exception e) {
				Write.debug("文件更新失败...");
			} finally {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

	}
}
