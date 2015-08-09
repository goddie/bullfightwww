package com.xiaba2.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Page<T> {
	// 公共变量 //
	public static final String ASC = "asc";
	public static final String DESC = "desc";
	// 分页参数 //
	protected int pageNo = 1;
	protected int pageSize = 1;

	protected ArrayList<String[]> orders = new ArrayList<String[]>();

	protected boolean autoCount = true;

	// 返回结果 //
	protected List<T> result = Collections.emptyList();

	protected long totalCount = -1;

	public Page() {
	}

	public Page(final int pageSize) {
		setPageSize(pageSize);
	}

	public Page(final int pageSize, final boolean autoCount) {
		setPageSize(pageSize);
		setAutoCount(autoCount);
	}

	/**
	 * 加入一个排序
	 * 
	 * @param property
	 * @param order
	 */
	public void addOrder(String property, String order) {
		String[] strs = new String[] { property, order };
		orders.add(strs);
	}

	public int getFirst() {
		return ((pageNo - 1) * pageSize) + 1;
	}

	public int getNextPage() {
		if (isHasNext())
			return pageNo + 1;
		else
			return pageNo;
	}

	public ArrayList<String[]> getOrders() {
		return orders;
	}

	public int getPageNo() {
		return pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getPrePage() {
		if (isHasPre())
			return pageNo - 1;
		else
			return pageNo;
	}

	public List<T> getResult() {
		return result;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public long getTotalPages() {
		if (totalCount < 0)
			return -1;
		long count = totalCount / pageSize;
		if (totalCount % pageSize > 0) {
			count++;
		}
		return count;
	}

	public boolean isAutoCount() {
		return autoCount;
	}

	public boolean isHasNext() {
		return (pageNo + 1 <= getTotalPages());
	}

	/**
	 * 是否有上一页
	 * 
	 * @return
	 */
	public boolean isHasPre() {
		return (pageNo - 1 >= 1);
	}

	/**
	 * 是否有排序
	 * 
	 * @return
	 */
	public boolean isOrdered() {
		return (!orders.isEmpty());
	}

	public void setAutoCount(final boolean autoCount) {
		this.autoCount = autoCount;
	}

	public void setOrders(ArrayList<String[]> orders) {
		this.orders = orders;
	}

	public void setPageNo(final int pageNo) {
		this.pageNo = pageNo;
		if (pageNo < 1) {
			this.pageNo = 1;
		}
	}

	public void setPageSize(final int pageSize) {
		this.pageSize = pageSize;
		if (pageSize < 1) {
			this.pageSize = 1;
		}
	}

	public void setResult(final List<T> result) {
		this.result = result;
	}

	public void setTotalCount(final long totalCount) {
		this.totalCount = totalCount;
	}
}
