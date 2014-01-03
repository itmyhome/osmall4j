package com.os.entity.itemcats;

import java.util.Date;

/**
 * 产品类别
 * @author gina
 *
 */
public class ItemCat {
	
	private Integer cid;//产品所属类别Id
	private String name;//类别名称
	private Integer parentCid;//父类别Id
	private boolean isParent;//该类目是否为父类目
	private Integer status;//状态
	private Integer sortOrder;//排列序号
	private Date modifiedTime;//修改时间
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getParentCid() {
		return parentCid;
	}
	public void setParentCid(Integer parentCid) {
		this.parentCid = parentCid;
	}
	public boolean isParent() {
		return isParent;
	}
	public void setParent(boolean isParent) {
		this.isParent = isParent;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}
	public Date getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public ItemCat(Integer cid, String name, Integer parentCid,
			boolean isParent, Integer status, Integer sortOrder,
			Date modifiedTime) {
		super();
		this.cid = cid;
		this.name = name;
		this.parentCid = parentCid;
		this.isParent = isParent;
		this.status = status;
		this.sortOrder = sortOrder;
		this.modifiedTime = modifiedTime;
	}
	public ItemCat() {
		super();
	}
	
	

}
