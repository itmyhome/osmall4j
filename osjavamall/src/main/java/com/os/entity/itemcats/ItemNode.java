package com.os.entity.itemcats;

import java.util.List;

public class ItemNode {
	
	private Integer id;
	private String text;
	private String parentId;
	private String state;
	private List<ItemNode> children;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<ItemNode> getChildren() {
		return children;
	}
	public void setChildren(List<ItemNode> children) {
		this.children = children;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	
	

}
