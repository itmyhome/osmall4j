package com.os.pageutil;


public class PageInfo  {
	public static int DEFAULT_PAGESIZE = 12;
	private int pageSize;
	private int pageNum;
	private int totalRowNum;
	private int totalPageNum;
	private int startRowNum;
	private int endRowNum;

	public void init() {
		if (pageNum <= 0) {
			pageNum = 1;
		}
		if (pageSize <= 0) {
			pageSize = DEFAULT_PAGESIZE;
		}
		int start = 1;
		if (totalRowNum <= (pageNum - 1) * pageSize) {
			if (totalRowNum % pageSize == 0) {
				pageNum = totalRowNum / pageSize;
			} else {
				pageNum = totalRowNum / pageSize + 1;
			}
		}
		if (pageNum <= 0) {
			pageNum = 1;
		}
		start = pageSize * (pageNum - 1);
		int totalpage = 1;
		if (totalRowNum % pageSize == 0) {
			totalpage = totalRowNum / pageSize;
		} else {
			totalpage = totalRowNum / pageSize + 1;
		}
		totalPageNum = totalpage;
		this.startRowNum = start;
		this.endRowNum = start + pageSize;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getTotalRowNum() {
		return totalRowNum;
	}

	public void setTotalRowNum(int totalRowNum) {
		this.totalRowNum = totalRowNum;
	}

	public int getTotalPageNum() {
		return totalPageNum;
	}

	public void setTotalPageNum(int totalPageNum) {
		this.totalPageNum = totalPageNum;
	}

	public int getStartRowNum() {
		return startRowNum;
	}

	public void setStartRowNum(int startRowNum) {
		this.startRowNum = startRowNum;
	}

	public int getEndRowNum() {
		return endRowNum;
	}

	public void setEndRowNum(int endRowNum) {
		this.endRowNum = endRowNum;
	}

	@Override
	public String toString() {
		return "{pageSize:'" + pageSize + "', pageNum:'" + pageNum
				+ "', totalRowNum:'" + totalRowNum + "', totalPageNum:'"
				+ totalPageNum + "', startRowNum:'" + startRowNum
				+ "', endRowNum:'" + endRowNum + "'}";
	}

	
}
