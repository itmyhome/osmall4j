package com.os.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.os.common.util.CommonUtils;
import com.os.dao.LoginLogDao;
import com.os.dao.UserDao;
import com.os.entity.LoginLog;
import com.os.entity.User;
import com.os.pageutil.PageInfoGrid;
import com.os.pageutil.PageUtils;
@Service
public class UserService {
    @Resource
	private UserDao userDao;
    @Resource
	private LoginLogDao loginLogDao;
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public LoginLogDao getLoginLogDao() {
		return loginLogDao;
	}

	public void setLoginLogDao(LoginLogDao loginLogDao) {
		this.loginLogDao = loginLogDao;
	}

	public User hasMatchUser(String userName, String password) {
		User user =userDao.getMatchCount(userName, password);
		return user;
	}
	
	public User findUserByCondition(User user) {
		return userDao.findUserByCondition(user);
	}
	
	private void jifen(User user){
		String lastlogin = CommonUtils.dateFormat(user.getLastVisit(), CommonUtils.DATE_YYYYMMDD_FORMAT);
		String newlogin = CommonUtils.dateFormat(new Date(), CommonUtils.DATE_YYYYMMDD_FORMAT);
		int jf = user.getCredits()%30;
		int dlcs = 0;
		if (newlogin.equals(lastlogin)) {
			dlcs = user.getContinuousLoginTimes();
			//积分增加规则
			if (jf == 0) {
				user.setCredits( 30 + user.getCredits());
			}
			if (jf <= 5 ) {
				user.setCredits( 5 + user.getCredits());
			}
			if (jf > 5 && jf <= 10) {
				user.setCredits( 9 + user.getCredits());
			}
			if (jf > 10 && jf <= 20) {
				user.setCredits( 12 + user.getCredits());
			}
			if (jf > 20 && jf < 29 ) {
				user.setCredits( 15 + user.getCredits());
			}
		} else {
			//连续登录次数为1,积分加5
			user.setCredits( 5 + user.getCredits());
		}
		user.setContinuousLoginTimes(dlcs + 1);
	}
	
	public void loginSuccess(User user) {
		jifen(user);
		LoginLog loginLog = new LoginLog();
		loginLog.setUserId(user.getUserId());
		loginLog.setIp(user.getLastIp());
		loginLog.setLoginDate(user.getLastVisit());
        userDao.updateLoginInfo(user);
        loginLogDao.insert(loginLog);
	}	
	
	public void create(User user){
		Integer userId = userDao.getUserId();
		user.setUserId(userId);
		userDao.insert(user);
		
	}
	
	public ArrayList<User> getList(PageInfoGrid pageInfoGrid){
		int count = userDao.getCount(pageInfoGrid);
		PageUtils.setTotalRows(pageInfoGrid.getPage(), count);
		ArrayList<User> userList = userDao.getPageList(pageInfoGrid);
		return userList;
	}
	
	public ArrayList<User> getList(PageInfoGrid pageInfoGrid, HashMap<String, Object> hashMap){
		int count = userDao.getCount(hashMap);
		PageUtils.setTotalRows(pageInfoGrid.getPage(), count);
		hashMap.put("startNum", pageInfoGrid.getPage().getStartRowNum());
		hashMap.put("endNum", pageInfoGrid.getPage().getEndRowNum());
		ArrayList<User> userList = userDao.getPageList(hashMap);
		return userList;
	}
	
	public void update(User user){
		userDao.update(user);
	}
	
	public void delete(User user){
		userDao.delete(user);
	}

}
