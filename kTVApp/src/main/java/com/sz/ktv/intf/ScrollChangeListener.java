package com.sz.ktv.intf;

public interface ScrollChangeListener {

	public void onLeft(int prePage,int currentPage,int nextPage);
	public void onRight(int prePage,int currentPage,int nextPage);
	public void nextStart();
	public void preStart();
}
