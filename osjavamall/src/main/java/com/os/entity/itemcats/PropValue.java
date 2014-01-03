package com.os.entity.itemcats;

import java.util.Date;

/**
 * 属性值
 * @author gina
 *
 */
public class PropValue {
	private Integer cid;//类目ID
	private Integer pid;//属性 ID
	private String propName;//属性名
	private Integer vid;//属性值ID
	private String name;//属性值
	private String nameAlias;//属性值别名
	private boolean isParent;//是否为父类目属性
	private String status;//状态
	private Integer sortOrder;//排列序号
	private Date modifiedTime;//修改时间
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getPropName() {
		return propName;
	}
	public void setPropName(String propName) {
		this.propName = propName;
	}
	public Integer getVid() {
		return vid;
	}
	public void setVid(Integer vid) {
		this.vid = vid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNameAlias() {
		return nameAlias;
	}
	public void setNameAlias(String nameAlias) {
		this.nameAlias = nameAlias;
	}
	public boolean isParent() {
		return isParent;
	}
	public void setParent(boolean isParent) {
		this.isParent = isParent;
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
	public Date getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public PropValue(Integer cid, Integer pid, String propName, Integer vid,
			String name, String nameAlias, boolean isParent, String status,
			Integer sortOrder, Date modifiedTime) {
		super();
		this.cid = cid;
		this.pid = pid;
		this.propName = propName;
		this.vid = vid;
		this.name = name;
		this.nameAlias = nameAlias;
		this.isParent = isParent;
		this.status = status;
		this.sortOrder = sortOrder;
		this.modifiedTime = modifiedTime;
	}
	public PropValue() {
		super();
	}
	

}
