package com.sz.ktv.msg;

public class SongTypeMessage {

	private int type;
	private String keyWords;
	private String preTileText;
	private int returnType;
	
	
	
	
	/**
	 * @return the returnType
	 */
	public int getReturnType() {
		return returnType;
	}
	/**
	 * @param returnType the returnType to set
	 */
	public void setReturnType(int returnType) {
		this.returnType = returnType;
	}
	/**
	 * @return the preTileText
	 */
	public String getPreTileText() {
		return preTileText;
	}
	/**
	 * @param preTileText the preTileText to set
	 */
	public void setPreTileText(String preTileText) {
		this.preTileText = preTileText;
	}
	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}
	/**
	 * @return the keyWords
	 */
	public String getKeyWords() {
		return keyWords;
	}
	/**
	 * @param keyWords the keyWords to set
	 */
	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}
	
	
}
