package com.sz.ktv.bean;

import java.io.Serializable;

import com.sz.ktv.util.Global;



public class SongBean implements Serializable{
	
	//歌曲No号|语言|歌名|首拼|字数|歌星|音轨|画面类型|左音量|右音量|歌曲分类|舞曲类型|电影分类|流行分类|歌曲文件名称
	private int id;
	private String songNo;//歌曲No号
	private String songLaunguageType;//语言
	private String songName;//歌名
	private String songFirst;//首拼
	private String songCount;//字数
	private String songSinger;//歌星
	private String songSingerFirt;//歌星首字母拼音
	private String songTrack;//音轨
	private String songPicType;//画面类型
	private String songLeftVolum;//左音量
	/**
	 * @return the songLaunguageType
	 */
	public String getSongLaunguageType() {
		return songLaunguageType;
	}


	/**
	 * @param songLaunguageType the songLaunguageType to set
	 */
	public void setSongLaunguageType(String songLaunguageType) {
		this.songLaunguageType = songLaunguageType;
	}
	private String songRightVolum;//右音量
	private String songType;//歌曲分类
	private String songDanceType;//舞曲类型
	private String songFilmType;//电影分类
	private String songPopularType;//流行分类
	private String songFileName;//歌曲文件名称
	private int store;
	//歌曲的歌手编号
	private String songSingerId;
	//云端或者本地歌曲
	private String songLocalCloudy;
	//记录整条歌曲
	private String songLineString;
	
	
	/**
	 * @return the songSingerId
	 */
	public String getSongSingerId() {
		return songSingerId;
	}


	/**
	 * @param songSingerId the songSingerId to set
	 */
	public void setSongSingerId(String songSingerId) {
		this.songSingerId = songSingerId;
	}


	/**
	 * @return the songLocalCloudy
	 */
	public String getSongLocalCloudy() {
		return songLocalCloudy;
	}


	/**
	 * @param songLocalCloudy the songLocalCloudy to set
	 */
	public void setSongLocalCloudy(String songLocalCloudy) {
		this.songLocalCloudy = songLocalCloudy;
	}


	/**
	 * @return the songLineString
	 */
	public String getSongLineString() {
		return songLineString;
	}


	/**
	 * @param songLineString the songLineString to set
	 */
	public void setSongLineString(String songLineString) {
		this.songLineString = songLineString;
	}


	public int getStore() {
		return store;
	}


	public void setStore(int store) {
		this.store = store;
	}


	public int getId() {
		return id;
	}
	
	
	public String getSongSingerFirt() {
		return songSingerFirt;
	}


	public void setSongSingerFirt(String songSingerFirt) {
		this.songSingerFirt = songSingerFirt;
	}


	public void setId(int id) {
		this.id = id;
	}
	public String getSongNo() {
		return songNo;
	}
	public void setSongNo(String songNo) {
		this.songNo = songNo;
	}
	 
	public String getSongLaunguageString() {
		String str = "" ;
		if(1 == Global.getAppLanguage()) {
			if("1".equals(songLaunguageType)){
				str = "Chinese";
			}else if("2".equals(songLaunguageType)){
				str = "Cantonese";
			}else if("3".equals(songLaunguageType)){
				str = "Hokkien";
			}else if("4".equals(songLaunguageType)){
				str = "English";
			}else if("5".equals(songLaunguageType)){
				str = "Japanese";
			}else if("6".equals(songLaunguageType)){
				str = "Korean";
			}else if("7".equals(songLaunguageType)){
				str = "Others";
			}
		} else {
			if("1".equals(songLaunguageType)){
				str = "国语";
			}else if("2".equals(songLaunguageType)){
				str = "粤语";
			}else if("3".equals(songLaunguageType)){
				str = "闽南语";
			}else if("4".equals(songLaunguageType)){
				str = "英语";
			}else if("5".equals(songLaunguageType)){
				str = "日语";
			}else if("6".equals(songLaunguageType)){
				str = "韩语";
			}else if("7".equals(songLaunguageType)){
				str = "其他";
			}
		}
		return str;
	}
	 
	public String getSongName() {
		return songName;
	}
	public void setSongName(String songName) {
		this.songName = songName;
	}
	public String getSongFirst() {
		return songFirst;
	}
	public void setSongFirst(String songFirst) {
		this.songFirst = songFirst;
	}
	public String getSongCount() {
		return songCount;
	}
	public void setSongCount(String songCount) {
		this.songCount = songCount;
	}
	public String getSongSinger() {
		return songSinger;
	}
	public void setSongSinger(String songSinger) {
		this.songSinger = songSinger;
	}
	public String getSongTrack() {
		return songTrack;
	}
	public void setSongTrack(String songTrack) {
		this.songTrack = songTrack;
	}
	public String getSongPicType() {
		return songPicType;
	}
	public void setSongPicType(String songPicType) {
		this.songPicType = songPicType;
	}
	public String getSongLeftVolum() {
		return songLeftVolum;
	}
	public void setSongLeftVolum(String songLeftVolum) {
		this.songLeftVolum = songLeftVolum;
	}
	public String getSongRightVolum() {
		return songRightVolum;
	}
	public void setSongRightVolum(String songRightVolum) {
		this.songRightVolum = songRightVolum;
	}
	public String getSongType() {
		return songType;
	}
	public void setSongType(String songType) {
		this.songType = songType;
	}
	public String getSongDanceType() {
		return songDanceType;
	}
	public void setSongDanceType(String songDanceType) {
		this.songDanceType = songDanceType;
	}
	public String getSongFilmType() {
		return songFilmType;
	}
	public void setSongFilmType(String songFilmType) {
		this.songFilmType = songFilmType;
	}
	public String getSongPopularType() {
		return songPopularType;
	}
	public void setSongPopularType(String songPopularType) {
		this.songPopularType = songPopularType;
	}
	public String getSongFileName() {
		return songFileName;
	}
	public void setSongFileName(String songFileName) {
		this.songFileName = songFileName;
	}
	 
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return  songFileName+"  "+ songName;
	}
	
}
