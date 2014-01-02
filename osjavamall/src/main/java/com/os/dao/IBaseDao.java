package com.os.dao;

import java.util.ArrayList;

import com.os.pageutil.PageInfoGrid;

/**
 * 公共的DAO接口
 * <p>功能说明：主要提供每个DAO实现中都会出现的一些常用的方法（DAO中私有的方法请在各自的DAO接口中定义）</p>
 * @author weitao.co
 * @param <T>T为数据库交互对象 
 * @version 1.00                                       
 */ 
public interface IBaseDao<T> {

	/**
	 * 公共的保存方法
	 * @param bean
	 */
	public void insert(T bean);
	/**
	 * 公共的修改方法
	 * @param bean
	 */
	public void update(T bean);
	/**
	 * 公共的根据ID删除对象方法
	 * @param id
	 */
	public void delete(T bean);
	/**
	 * 公共的获取总记录数的方法
	 * @param grid
	 * @return
	 */
	public Integer getCount(PageInfoGrid grid);
	/**
	 * 公共的获取分页数据的方法
	 * @param grid
	 * @return
	 */
	public ArrayList<T> getPageList(PageInfoGrid grid);
	/**
	 * 公共的根据一个条件获取单独对象的方法
	 * @param condition（一般为ID 或者唯一对象，具体实现看各代码情况而定）
	 * @return
	 */
	public T getByCondition(String condition);
}
