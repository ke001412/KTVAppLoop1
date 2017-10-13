package com.sz.ktv.db;

import java.io.Serializable;

import android.content.ContentValues;
import android.provider.BaseColumns;

import com.sz.ktv.util.Global;

public class Song implements BaseColumns,Serializable{

	 
	public int rowid;
	public int getRowid() {
		return rowid;
	}
	public void setRowid(int rowid) {
		this.rowid = rowid;
	}

	public int song_clickTime;
	public int song_clickCount;
	
	public String id;
	public String song_no = "";
	public String song_language = "";
	public String song_name = "";
	public String song_first_name = "";
	public String song_words_count = "";
	public String song_singer_name = "";
	public String song_track = "";
	public String song_left_volume = "";
	public String song_right_volume = "";
	public String song_class = "";
	public String song_dance_type = "";
	public String song_film_type = "";
	public String song_popular_type = "";
	public String song_download_flag = "";
	public String song_time = "";
	public String song_file_name = "";
	public String song_favourite_flag="";
	/**
	 * 下载状态
	 * 1.下载中
	 * 2.下载成功
	 * 3.下载失败
	 */
	public int songDownStatus = -1;
	
	public int getSongDownStatus() {
		return songDownStatus;
	}
	public void setSongDownStatus(int songDownStatus) {
		this.songDownStatus = songDownStatus;
	}
	public int getSong_clickTime() {
		return song_clickTime;
	}
	public void setSong_clickTime(int song_clickTime) {
		this.song_clickTime = song_clickTime;
	}
	public int getSong_clickCount() {
		return song_clickCount;
	}
	public void setSong_clickCount(int song_clickCount) {
		this.song_clickCount = song_clickCount;
	}
	 

	public boolean isSong_del = false ;
	
	
	public boolean isSong_del() {
		return isSong_del;
	}
	public void setSong_del(boolean isSong_del) {
		this.isSong_del = isSong_del;
	}
	public String getSong_favourite_flag() {
		return song_favourite_flag;
	}
	public void setSong_favourite_flag(String song_favourite_flag) {
		this.song_favourite_flag = song_favourite_flag;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSong_no() {
		return song_no;
	}
	public void setSong_no(String song_no) {
		this.song_no = song_no;
	}
	public String getSong_language() {
		return song_language;
	}
	public void setSong_language(String song_language) {
		this.song_language = song_language;
	}
	public String getSong_name() {
		return song_name;
	}
	public void setSong_name(String song_name) {
		this.song_name = song_name;
	}
	public String getSong_first_name() {
		return song_first_name;
	}
	public void setSong_first_name(String song_first_name) {
		this.song_first_name = song_first_name;
	}
	public String getSong_words_count() {
		return song_words_count;
	}
	public void setSong_words_count(String song_words_count) {
		this.song_words_count = song_words_count;
	}
	public String getSong_singer_name() {
		return song_singer_name;
	}
	public void setSong_singer_name(String song_singer_name) {
		this.song_singer_name = song_singer_name;
	}
	public String getSong_track() {
		return song_track;
	}
	public void setSong_track(String song_track) {
		this.song_track = song_track;
	}
	public String getSong_left_volume() {
		return song_left_volume;
	}
	public void setSong_left_volume(String song_left_volume) {
		this.song_left_volume = song_left_volume;
	}
	public String getSong_right_volume() {
		return song_right_volume;
	}
	public void setSong_right_volume(String song_right_volume) {
		this.song_right_volume = song_right_volume;
	}
	public String getSong_class() {
		return song_class;
	}
	public void setSong_class(String song_class) {
		this.song_class = song_class;
	}
	public String getSong_dance_type() {
		return song_dance_type;
	}
	public void setSong_dance_type(String song_dance_type) {
		this.song_dance_type = song_dance_type;
	}
	 
	public String getSong_film_type() {
		return song_film_type;
	}
	public void setSong_film_type(String song_film_type) {
		this.song_film_type = song_film_type;
	}
	public String getSong_popular_type() {
		return song_popular_type;
	}
	public void setSong_popular_type(String song_popular_type) {
		this.song_popular_type = song_popular_type;
	}
	public String getSong_download_flag() {
		return song_download_flag;
	}
	public void setSong_download_flag(String song_download_flag) {
		this.song_download_flag = song_download_flag;
	}
	public String getSong_time() {
		return song_time;
	}
	public void setSong_time(String song_time) {
		this.song_time = song_time;
	}
	public String getSong_file_name() {
		return song_file_name;
	}
	public void setSong_file_name(String song_file_name) {
		this.song_file_name = song_file_name;
	}
	 
 
	public String getSongLaunguageString() {
		String str = "" ;
		if(1 == Global.getAppLanguage()) {
			if("1".equals(song_language)){
				str = "Chinese";
			}else if("2".equals(song_language)){
				str = "Cantonese";
			}else if("3".equals(song_language)){
				str = "Hokkien";
			}else if("4".equals(song_language)){
				str = "English";
			}else if("5".equals(song_language)){
				str = "Japanese";
			}else if("6".equals(song_language)){
				str = "Korean";
			}else if("7".equals(song_language)){
				str = "Others";
			}
		} else {
			if("1".equals(song_language)){
				str = "国";
			}else if("2".equals(song_language)){
				str = "粤";
			}else if("3".equals(song_language)){
				str = "闽";
			}else if("4".equals(song_language)){
				str = "英";
			}else if("5".equals(song_language)){
				str = "日";
			}else if("6".equals(song_language)){
				str = "韩";
			}else if("7".equals(song_language)){
				str = "其它";
			}
		}
		return str;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return song_no+" "+ song_name +" "+song_first_name;
	}
 
}
