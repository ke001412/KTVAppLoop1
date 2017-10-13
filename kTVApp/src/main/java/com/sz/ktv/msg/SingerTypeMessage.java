package com.sz.ktv.msg;

public class SingerTypeMessage {

	private  int singerType;
	private int returnType;
	public String msg;
	
	
	
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
	 * @return the singerType
	 */
	public int getSingerType() {
		return singerType;
	}
	/**
	 * @param singerType the singerType to set
	 */
	public void setSingerType(int singerType) {
		this.singerType = singerType;
	}
	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}
	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
