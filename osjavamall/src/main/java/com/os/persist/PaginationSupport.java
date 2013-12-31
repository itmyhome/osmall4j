package com.os.persist;

import java.io.Serializable;
import java.util.Collection;

public class PaginationSupport<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final int PAGESIZE = 10;
	private int pageSize = 10;
	private Collection<T> data;
	private int totalCount;
	private int startIndex = 0;

	public PaginationSupport(Collection<T> data, int totalCount) {
		this.pageSize = 10;
		this.totalCount = totalCount;
		this.data = data;
		this.startIndex = 0;
	}

	public PaginationSupport(Collection<T> data, int totalCount, int startIndex) {
		this.pageSize = 10;
		this.totalCount = totalCount;
		this.data = data;
		this.startIndex = startIndex;
	}

	public PaginationSupport(Collection<T> data, int totalCount, int pageSize,
			int startIndex) {
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		this.data = data;
		this.startIndex = startIndex;
	}

	public Collection<T> getData() {
		return this.data;
	}

	public void setData(Collection<T> data) {
		this.data = data;
	}

	public int getPageSize() {
		return this.pageSize;
	}

	public void setPageSize(int pageSize) {
		if (pageSize > 0)
			this.pageSize = pageSize;
		else
			this.pageSize = 10;
	}

	public int getTotalCount() {
		return this.totalCount;
	}

	public int getPageCount() {
		int totalPage = this.totalCount / this.pageSize;
		totalPage += (this.totalCount % this.pageSize == 0 ? 0 : 1);

		return totalPage;
	}

	public void setTotalCount(int totalCount) {
		if (totalCount > 0)
			this.totalCount = totalCount;
		else
			this.totalCount = 0;
	}

	public int getStartIndex() {
		return this.startIndex;
	}

	public void setStartIndex(int startIndex) {
		if (this.totalCount <= 0)
			this.startIndex = 0;
		else if (startIndex >= this.totalCount)
			this.startIndex = (this.pageSize * (this.totalCount / this.pageSize));
		else if (startIndex <= 0)
			this.startIndex = 0;
		else
			this.startIndex = (this.pageSize * (startIndex / this.pageSize));
	}

	public int getNextIndex() {
		int nextIndex = getStartIndex() + this.pageSize;

		if (nextIndex >= this.totalCount) {
			return getStartIndex();
		}
		return nextIndex;
	}

	public int getPreviousIndex() {
		int previousIndex = getStartIndex() - this.pageSize;

		if (previousIndex <= 1) {
			return -1;
		}
		return previousIndex;
	}

	public int getLastPageIndex() {
		return (getPageCount() - 1) * this.pageSize;
	}

	public int getCurrentPageIndex() {
		return getStartIndex() / this.pageSize + 1;
	}
}