package com.sz.ktv.db.ktv;

import java.util.List;

import com.sz.ktv.db.Singer;
import com.sz.ktv.db.Song;

public class SongPage {

	
	
	List<Song> prePageList;
	List<Song> currentPageList;
	List<Song> nextPageList;
	
	List<Singer> preSingerPageList;
	List<Singer> currentSingerPageList;
	List<Singer> nextSingerPageList;
	
	List< List<Song>> defaultSongList ;
	List< List<Singer>>  defaultSingerList;
	

	public List< List<Singer>> getDefaultSingerList() {
		return defaultSingerList;
	}

	public void setDefaultSingerList(List< List<Singer>> defaultSingerList) {
		this.defaultSingerList = defaultSingerList;
	}

	public List<List<Song>> getDefaultSongList() {
		return defaultSongList;
	}

	public void setDefaultSongList(List<List<Song>> defaultSongList) {
		this.defaultSongList = defaultSongList;
	}

	public List<Singer> getPreSingerPageList() {
		return preSingerPageList;
	}

	public void setPreSingerPageList(List<Singer> preSingerPageList) {
		this.preSingerPageList = preSingerPageList;
	}

	public List<Singer> getCurrentSingerPageList() {
		return currentSingerPageList;
	}

	public void setCurrentSingerPageList(List<Singer> currentSingerPageList) {
		this.currentSingerPageList = currentSingerPageList;
	}

	public List<Singer> getNextSingerPageList() {
		return nextSingerPageList;
	}

	public void setNextSingerPageList(List<Singer> nextSingerPageList) {
		this.nextSingerPageList = nextSingerPageList;
	}

	public List<Song> getPrePageList() {
		return prePageList;
	}

	public void setPrePageList(List<Song> prePageList) {
		this.prePageList = prePageList;
	}

	public List<Song> getCurrentPageList() {
		return currentPageList;
	}

	public void setCurrentPageList(List<Song> currentPageList) {
		this.currentPageList = currentPageList;
	}

	public List<Song> getNextPageList() {
		return nextPageList;
	}

	public void setNextPageList(List<Song> nextPageList) {
		this.nextPageList = nextPageList;
	}

	 
}
