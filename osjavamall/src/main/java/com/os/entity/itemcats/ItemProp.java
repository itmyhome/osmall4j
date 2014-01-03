package com.os.entity.itemcats;

import java.util.Date;

/**
 * 产品属性表
 * @author gina
 *
 */
public class ItemProp {
	private Integer pid;//属性 ID
	private String name;//属性名
	private Integer cid;//类目ID
	private boolean isAllowAlias;//是否允许别名
	private boolean isColorProp;//是否颜色属性
	private boolean isEnumProp;//是否是可枚举属性
	private boolean isInputProp;//是否是卖家可以自行输入的属性
	private boolean isKeyProp;//是否关键属性
	private boolean isSaleProp;//是否销售属性
	private boolean isSearchProp;//是否搜索属性
	private boolean isRequired;//是否为必选属性
	private boolean isMultiCheck;//是否可以多选
	private String status;//状态
	private Integer sortOrder;//排列序号
	private Date createdTime;//创建时间
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public boolean isAllowAlias() {
		return isAllowAlias;
	}
	public void setAllowAlias(boolean isAllowAlias) {
		this.isAllowAlias = isAllowAlias;
	}
	public boolean isColorProp() {
		return isColorProp;
	}
	public void setColorProp(boolean isColorProp) {
		this.isColorProp = isColorProp;
	}
	public boolean isEnumProp() {
		return isEnumProp;
	}
	public void setEnumProp(boolean isEnumProp) {
		this.isEnumProp = isEnumProp;
	}
	public boolean isInputProp() {
		return isInputProp;
	}
	public void setInputProp(boolean isInputProp) {
		this.isInputProp = isInputProp;
	}
	public boolean isKeyProp() {
		return isKeyProp;
	}
	public void setKeyProp(boolean isKeyProp) {
		this.isKeyProp = isKeyProp;
	}
	public boolean isSaleProp() {
		return isSaleProp;
	}
	public void setSaleProp(boolean isSaleProp) {
		this.isSaleProp = isSaleProp;
	}
	public boolean isSearchProp() {
		return isSearchProp;
	}
	public void setSearchProp(boolean isSearchProp) {
		this.isSearchProp = isSearchProp;
	}
	public boolean isRequired() {
		return isRequired;
	}
	public void setRequired(boolean isRequired) {
		this.isRequired = isRequired;
	}
	public boolean isMultiCheck() {
		return isMultiCheck;
	}
	public void setMultiCheck(boolean isMultiCheck) {
		this.isMultiCheck = isMultiCheck;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public ItemProp(Integer pid, String name, Integer cid,
			boolean isAllowAlias, boolean isColorProp, boolean isEnumProp,
			boolean isInputProp, boolean isKeyProp, boolean isSaleProp,
			boolean isSearchProp, boolean isRequired, boolean isMultiCheck,
			String status, Integer sortOrder, Date createdTime) {
		super();
		this.pid = pid;
		this.name = name;
		this.cid = cid;
		this.isAllowAlias = isAllowAlias;
		this.isColorProp = isColorProp;
		this.isEnumProp = isEnumProp;
		this.isInputProp = isInputProp;
		this.isKeyProp = isKeyProp;
		this.isSaleProp = isSaleProp;
		this.isSearchProp = isSearchProp;
		this.isRequired = isRequired;
		this.isMultiCheck = isMultiCheck;
		this.status = status;
		this.sortOrder = sortOrder;
		this.createdTime = createdTime;
	}
	public ItemProp() {
		super();
	}
}
