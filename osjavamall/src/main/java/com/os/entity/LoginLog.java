package com.os.entity;
import java.io.Serializable;
import java.util.Date;

public class LoginLog implements Serializable{
	private int loginLogId; 

	private String userId;

	private String ip;

	private Date loginDate;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public int getLoginLogId() {
		return loginLogId;
	}

	public void setLoginLogId(int loginLogId) {
		this.loginLogId = loginLogId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}


}
