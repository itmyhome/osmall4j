package com.os.entity;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable{
	private Integer userId;

	private String userName;

	private String password;

	private int credits;
	
	private String nickName;
	
	private String mobilePhone;
	
	private String realName;
	
	private String address;
	
	private String postCard;

	private String lastIp;

	private Date lastVisit;
	
	private String userImg;
	
	private Integer continuousLoginTimes;
	
	


	public Integer getContinuousLoginTimes() {
		return continuousLoginTimes;
	}

	public void setContinuousLoginTimes(Integer continuousLoginTimes) {
		this.continuousLoginTimes = continuousLoginTimes;
	}

	public String getUserImg() {
		return userImg;
	}

	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}

	public String getLastIp() {
		return lastIp;
	}

	public void setLastIp(String lastIp) {
		this.lastIp = lastIp;
	}

	public Date getLastVisit() {
		return lastVisit;
	}

	public void setLastVisit(Date lastVisit) {
		this.lastVisit = lastVisit;
	}

	

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostCard() {
		return postCard;
	}

	public void setPostCard(String postCard) {
		this.postCard = postCard;
	}
	
	
}
