package com.os.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.os.dao.LoginLogDao;
import com.os.dao.UserDao;
import com.os.entity.LoginLog;
import com.os.entity.User;
import com.os.pageutil.PageInfoGrid;
import com.os.pageutil.PageUtils;
import com.os.web.UserControllerAnnotation;
@Service
public class UserService {
    @Resource
	private UserDao userDao;
    @Resource
	private LoginLogDao loginLogDao;
    
    private Log log = LogFactory.getLog(UserService.class);
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
	
	public void loginSuccess(User user) {
		user.setCredits( 5 + user.getCredits());
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
		long exeuteBefore = System.currentTimeMillis();
		log.info(System.currentTimeMillis());
		int count = userDao.getCount(pageInfoGrid);
		long exeuteAfter = System.currentTimeMillis();
		log.info("[count-executeTime]"+ (exeuteAfter - exeuteBefore));
		
		PageUtils.setTotalRows(pageInfoGrid.getPage(), count);
		long exeuteGetPageListBefore = System.currentTimeMillis();
		log.info(System.currentTimeMillis());
		ArrayList<User> userList = userDao.getPageList(pageInfoGrid);
		long exeuteGetPageListAfter = System.currentTimeMillis();
		log.info("[getPageList-executeTime]"+ (exeuteGetPageListAfter - exeuteGetPageListBefore));
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
