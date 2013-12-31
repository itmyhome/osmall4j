package com.os.pageutil;

import java.util.HashMap;
import java.util.List;


public class PageInfoGrid {
	private PageInfo page;
	private List<?> data;
	private HashMap<String,Object> search;
	private String sortField;
	private String sortDesc;
	
	
	public String getSortField() {
		return sortField;
	}
	public void setSortField(String sortField) {
		this.sortField = sortField;
	}
	public String getSortDesc() {
		return sortDesc;
	}
	public void setSortDesc(String sortDesc) {
		this.sortDesc = sortDesc;
	}
	public PageInfo getPage() {
		return page;
	}
	public void setPage(PageInfo page) {
		this.page = page;
	}
	public List<?> getData() {
		return data;
	}
	public void setData(List<?> data) {
		this.data = data;
	}
	public HashMap<String, Object> getSearch() {
		return search;
	}
	public void setSearch(HashMap<String, Object> search) {
		this.search = search;
	}
	
	
}
