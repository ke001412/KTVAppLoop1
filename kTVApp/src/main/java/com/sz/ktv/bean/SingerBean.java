package com.sz.ktv.bean;

import java.io.Serializable;

public class SingerBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	// 1.大陆男星
	// 2.大陆女星
	// 3.港台男星
	// 4.港台女星
	// 5.海外男星
	// 6.海外女星
	// 7.乐队组合
	// 8.其他
	// 1_10000_刘德华_LDH
	private int singerType;// 歌星类型.如大陆歌星.
	private String singerId;// 编号. 编号必须：5位数字组成.
	private String singerName;// 歌星名字
	private String singerPinYin;// 歌星拼音
	private int favStatus;
	
	
	
	public int getFavStatus() {
		return favStatus;
	}

	public void setFavStatus(int favStatus) {
		this.favStatus = favStatus;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSingerType() {
		return singerType;
	}

	public void setSingerType(int singerType) {
		this.singerType = singerType;
	}

	public String getSingerId() {
		return singerId;
	}

	public void setSingerId(String singerId) {
		this.singerId = singerId;
	}

	public String getSingerName() {
		return singerName;
	}

	public void setSingerName(String singerName) {
		this.singerName = singerName;
	}

	public String getSingerPinYin() {
		return singerPinYin;
	}

	public void setSingerPinYin(String singerPinYin) {
		this.singerPinYin = singerPinYin;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "singerType +" + singerType + " singerName+" + singerName
				+ " singerPinYin+ " + singerPinYin;
	}

}
