package com.sz.ktv.util;

import java.util.ArrayList;
import java.util.List;

import com.sz.ktv.db.Singer;

public class PageSingerUtil {

	private int currentPage = 1;// 当前页数

	public int totalPages = 0;// 总页数

	private int pageSize = 0;// 每页显示数

	private int totalRows = 0;// 总数据数

	private int startNum = 0;// 开始记录

	private int nextPage = 0;// 下一页

	private int previousPage = 0;// 上一页

	private boolean hasNextPage = false;// 是否有下一页

	private boolean hasPreviousPage = false;// 是否有前一页

		List<Singer> list ;
	public PageSingerUtil(int pageSize, List<Singer> singerList)
	{
		if(null != singerList && singerList.size()>0)
		{
			this.list = singerList;
			this.totalRows = singerList.size();
			this.pageSize = pageSize;


			if ((totalRows % pageSize) == 0) {
				totalPages = totalRows / pageSize;
			} else {
				totalPages = totalRows / pageSize + 1;
			}
		}
		
		
	}
	
	public List<Singer> getAllData()
	{
		return list;
	}
	/**
	 * 获取当前下一页的数据集合
	 * @param currentPage
	 * @return
	 */
	public  List<Singer>  getNextSingerData(int currentPage) {
		if(null == list || list.size()<=0)
		{
			return null;
		}
		this.currentPage = currentPage;

		if (currentPage >= totalPages) {
			hasNextPage = false;
			currentPage = totalPages;
		} else {
			hasNextPage = true;
		}

		if (currentPage <= 1) {
			hasPreviousPage = false;
			currentPage = 1;
		} else {
			hasPreviousPage = true;
		}

		startNum = (currentPage - 1) * pageSize;

		nextPage = currentPage + 1;

		int preNum = 0;
		int nextNum = 0;
		
		if (nextPage > totalPages) {
			nextPage = 1;
			preNum=0;
			nextNum = pageSize;
		}else {
			preNum = (nextPage-1)*pageSize;
			nextNum = preNum+pageSize;
		}
		if(nextNum>totalRows)
		{
			nextNum = totalRows-1;
		}
		List<Singer>  myList = new ArrayList<Singer>();
		myList.clear();
		myList.addAll(list.subList(preNum,nextNum));
		return myList;

	}
	
	/**
	 * 获取当前上一页的数据
	 * @param currentPage
	 * @return
	 */
	public  List<Singer>  getPreSingerData(int currentPage) {

		if(null == list || list.size()<=0)
		{
			return null;
		}
		this.currentPage = currentPage;

		if (currentPage >= totalPages) {
			hasNextPage = false;
			currentPage = totalPages;
		} else {
			hasNextPage = true;
		}

		if (currentPage <= 1) {
			hasPreviousPage = false;
			currentPage = 1;
		} else {
			hasPreviousPage = true;
		}

		startNum = (currentPage - 1) * pageSize;

		previousPage = currentPage - 1;

		int preNum = 0;
		int nextNum = 0;
		
		if (previousPage < 1) {
			previousPage = totalPages;
		} 
		nextNum = (previousPage)*pageSize;
		if(nextNum >=totalRows)
		{
			nextNum = totalRows-1;
		}
		preNum = (previousPage-1)*pageSize;
		List<Singer>  myList = new ArrayList<Singer>();
		myList.clear();
		myList.addAll(list.subList(preNum,nextNum));
		return myList;

	}
	
	public  List<Object>  getList(int currentPage) {

		this.currentPage = currentPage;

		if (currentPage >= totalPages) {
			hasNextPage = false;
			currentPage = totalPages;
		} else {
			hasNextPage = true;
		}

		if (currentPage <= 1) {
			hasPreviousPage = false;
			currentPage = 1;
		} else {
			hasPreviousPage = true;
		}

		startNum = (currentPage - 1) * pageSize;

		nextPage = currentPage + 1;

		if (nextPage >= totalPages) {
			nextPage = 1;
		}

		previousPage = currentPage - 1;

		if (previousPage <= 1) {
			previousPage = totalPages;
		}
		
		List<Object>  myList = new ArrayList<Object>();
		myList.addAll(list.subList(0,3));
		return myList;

	}

	public boolean isHasNextPage() {

		return hasNextPage;

	}

	public boolean isHasPreviousPage() {

		return hasPreviousPage;

	}

	/**
	 * @return the nextPage
	 */
	public int getNextPage() {
		return nextPage;
	}

	/**
	 * @param nextPage
	 *            the nextPage to set
	 */
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	/**
	 * @return the previousPage
	 */
	public int getPreviousPage() {
		return previousPage;
	}

	/**
	 * @param previousPage
	 *            the previousPage to set
	 */
	public void setPreviousPage(int previousPage) {
		this.previousPage = previousPage;
	}

	/**
	 * @return the currentPage
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * @param currentPage
	 *            the currentPage to set
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize
	 *            the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return the totalPages
	 */
	public int getTotalPages() {
		return totalPages;
	}

	/**
	 * @param totalPages
	 *            the totalPages to set
	 */
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	/**
	 * @return the totalRows
	 */
	public int getTotalRows() {
		return totalRows;
	}

	/**
	 * @param totalRows
	 *            the totalRows to set
	 */
	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	/**
	 * @param hasNextPage
	 *            the hasNextPage to set
	 */
	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}

	/**
	 * @param hasPreviousPage
	 *            the hasPreviousPage to set
	 */
	public void setHasPreviousPage(boolean hasPreviousPage) {
		this.hasPreviousPage = hasPreviousPage;
	}

	/**
	 * @return the startNum
	 */
	public int getStartNum() {
		return startNum;
	}

	/**
	 * @param startNum
	 *            the startNum to set
	 */
	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}
}
