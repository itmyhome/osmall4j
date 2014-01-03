package com.os.entity.itemcats;

import java.util.Date;

/**
 * 品牌系列表
 * @author gina
 *
 */
public class Brand {
	
	private Integer bid;//品牌ID
	private Integer cid;//商品所属类目ID
	private String name;//品牌中文名
	private String ename;//品牌英文名
	private String desc;//描述
	private String bLogoUrl;//品牌LOGO
	private String status;//状态
	private Date createdTime;//创建时间
	public Integer getBid() {
		return bid;
	}
	public void setBid(Integer bid) {
		this.bid = bid;
	}
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
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getbLogoUrl() {
		return bLogoUrl;
	}
	public void setbLogoUrl(String bLogoUrl) {
		this.bLogoUrl = bLogoUrl;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public Brand(Integer bid, Integer cid, String name, String ename,
			String desc, String bLogoUrl, String status, Date createdTime) {
		super();
		this.bid = bid;
		this.cid = cid;
		this.name = name;
		this.ename = ename;
		this.desc = desc;
		this.bLogoUrl = bLogoUrl;
		this.status = status;
		this.createdTime = createdTime;
	}
	public Brand() {
		super();
	}
	
	

}
