package com.os.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.os.common.SecDaoSupport;
import com.os.entity.User;
import com.os.pageutil.PageInfoGrid;
@Component
public class UserDao extends SecDaoSupport implements IBaseDao<User>{

	public User getMatchCount(String userName, String password) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("userName", userName);
		paramMap.put("password", password);
		User user = (User) ibatisPersistence.findObject("UserInfo.getMatchCount", paramMap);
		return user;
	}

	public User findUserByCondition(User user) {
		HashMap<String,String> params = new HashMap<String,String>();
		params.put("userId", user.getUserId());
		User userModel = new User();
		userModel = (User) ibatisPersistence.findObject("UserInfo.findUserByCondition", params);
		return userModel;
	}

	public void updateLoginInfo(User user) {
		ibatisPersistence.update("UserInfo.updateUserInfo", user);
	}

	public void insert(User user) {
		ibatisPersistence.update("UserInfo.insertUserInfo", user);
	}

	public void update(User user) {
		ibatisPersistence.update("UserInfo.updateUserInfo", user);
	}

	public void delete(User user) {
		ibatisPersistence.delete("UserInfo.deleteUserInfo", user);
	}

	public Integer getCount(PageInfoGrid grid) {
		Integer count = (Integer) ibatisPersistence.findObject(
				"UserInfo.getCountUserInfo", grid);
		return count;
	}

	public ArrayList<User> getPageList(PageInfoGrid grid) {
		ArrayList<User> userList = (ArrayList<User>) ibatisPersistence.findList("UserInfo.getUserInfoList", "",  grid.getPage().getStartRowNum(), grid.getPage().getPageSize());
		return userList;
	}

	public User getByCondition(String condition) {
		return null;
	}
	
	public String getUserId(){
		return (String) ibatisPersistence.findObject("UserInfo.getUserId", "");
	}
	
	public ArrayList<User> getPageList(HashMap<String, Object> hashMap) {
		ArrayList<User> userList = (ArrayList<User>) ibatisPersistence.findList("UserInfo.testUserInfoList",hashMap);
		return userList;
	}
	
	public Integer getCount(HashMap<String, Object> hashMap) {
		Integer count = (Integer) ibatisPersistence.findObject(
				"UserInfo.getCountUserInfo", hashMap);
		return count;
	}
	
}
