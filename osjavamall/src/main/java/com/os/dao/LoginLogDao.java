package com.os.dao;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.os.common.SecDaoSupport;
import com.os.domain.LoginLog;
import com.os.pageutil.PageInfoGrid;
@Component
public class LoginLogDao extends SecDaoSupport implements IBaseDao<LoginLog>{
	public void insert(LoginLog bean) {
		ibatisPersistence.update("UserInfo.addLoginLog", bean); 
	}

	public void update(LoginLog bean) {
	}

	public void delete(LoginLog bean) {
	}

	public Integer getCount(PageInfoGrid grid) {
		Map<String, Object> paramMap = grid.getSearch();
		Integer count = (Integer)ibatisPersistence.findObject("TabCaApplyInfo.getCountTabCaApplyInfo",paramMap);
		return count;
	}

	public ArrayList<LoginLog> getPageList(PageInfoGrid grid) {
		Map<String, Object> paramMap = grid.getSearch();
		  ArrayList<LoginLog> list=( ArrayList<LoginLog>) ibatisPersistence.findList(
		 "TabCaApplyInfo.getPageListTabCaApplyInfoBean", paramMap,  grid.getPage().getStartRowNum(), grid.getPage().getPageSize());
		return list;
	}

	public LoginLog getByCondition(String condition) {
		// TODO Auto-generated method stub
		return null;
	}
}