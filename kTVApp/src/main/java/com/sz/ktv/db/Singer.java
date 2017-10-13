package com.sz.ktv.db;

import android.content.ContentValues;
import android.provider.BaseColumns;

public class Singer implements BaseColumns {

	private String id;
	private String singer_type;// 歌星类型.如大陆歌星.
	private String singer_no;// 编号. 编号必须：5位数字组成.
	private String singer_name;// 歌星名字
	private String singer_pinyin;
	private String singer_favourite_flag;// 歌星拼音

	// 3|03091|刘德华|LDH
	/* The field of the table */
	public static final String _SINGER_TYPE = "singer_type";
	public static final String _SINGER_NO = "singer_no";
	public static final String _SINGER_NAME = "singer_name";
	public static final String _SINGER_PINYIN = "singer_pinyin";
	public static final String _SINGER_FAVOURITE_FLAG = "singer_favourite_flag";
	/**
	 * 表名称
	 */
	public static final String KTV_TABLE_SINGER_NAME = "singer";
	public static final String KTV_TABLE_SINGER_FTS = "singer_fts";

	public final static String SQL_CREATE_TABLE_SINGER = String
			.format("create table  IF NOT EXISTS %s(%s integer primary key autoincrement,%s char(2),%s char(10),%s char(50),%s char(50)"
					+ ",%s char(1))", KTV_TABLE_SINGER_NAME, _ID, _SINGER_TYPE,
					_SINGER_NO, _SINGER_NAME, _SINGER_PINYIN,
					_SINGER_FAVOURITE_FLAG);

	public static final String SQL_DROP_TABLE = String.format(
			"drop table if exists %s", KTV_TABLE_SINGER_NAME);

	/**
	 * 创建检索虚拟表FTS3
	 */
	public final static String SQL_CREATE_TABLE_SINGER_FTS = String
			.format("create virtual table IF NOT EXISTS %s  using fts3(%s integer primary key autoincrement,%s char(2),%s char(10),%s char(50),%s char(50)"
					+ ",%s char(1))", KTV_TABLE_SINGER_FTS, _ID, _SINGER_TYPE,
					_SINGER_NO, _SINGER_NAME, _SINGER_PINYIN,
					_SINGER_FAVOURITE_FLAG);

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSinger_type() {
		return singer_type;
	}

	public void setSinger_type(String singer_type) {
		this.singer_type = singer_type;
	}

	public String getSinger_no() {
		return singer_no;
	}

	public void setSinger_no(String singer_no) {
		this.singer_no = singer_no;
	}

	public String getSinger_name() {
		return singer_name;
	}

	public void setSinger_name(String singer_name) {
		this.singer_name = singer_name;
	}

	public String getSinger_pinyin() {
		return singer_pinyin;
	}

	public void setSinger_pinyin(String singer_pinyin) {
		this.singer_pinyin = singer_pinyin;
	}

	public String getSinger_favourite_flag() {
		return singer_favourite_flag;
	}

	public void setSinger_favourite_flag(String singer_favourite_flag) {
		this.singer_favourite_flag = singer_favourite_flag;
	}

	public ContentValues getContentValues() {

		ContentValues values = new ContentValues();
		values.put(_SINGER_TYPE, singer_type);
		values.put(_SINGER_NO, singer_no);
		values.put(_SINGER_NAME, singer_name);
		values.put(_SINGER_PINYIN, singer_pinyin);
		values.put(_SINGER_FAVOURITE_FLAG, singer_favourite_flag);

		return values;

	}

	public ContentValues getContentValuesFTS() {

		ContentValues values = new ContentValues();
		values.put(_SINGER_TYPE, singer_type);
		values.put(_SINGER_NO, singer_no);
		values.put(_SINGER_NAME, singer_name);
		values.put(_SINGER_PINYIN, singer_pinyin);
		values.put(_SINGER_FAVOURITE_FLAG, singer_favourite_flag);
		return values;

	}

}
