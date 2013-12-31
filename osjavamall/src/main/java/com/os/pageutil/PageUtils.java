package com.os.pageutil;


public class PageUtils {

	/**
	 * 在Page设置总行数
	 * 
	 * @param pagedResult
	 * @param totalRows
	 */
	public static void setTotalRows(PageInfo page, int totalRows) {
		page.setTotalRowNum(totalRows);
		page.init();
	}

	/**
	 * 获取增加分页功能的SQL语句
	 * 
	 * @param pagedResult
	 * @param query
	 * @return
	 */
	public static String getPagedSql(String sql, int start, int end) {
		StringBuffer newSql = new StringBuffer();

		newSql.append(" SELECT ROWALL.*,ROWNUM FROM ");
		newSql.append(" (SELECT ROW_.*, ROWNUM AS NUM_  FROM ( ");
		newSql.append(sql);
		newSql.append(" ) ROW_ WHERE ROWNUM <=" + end + ") ROWALL ");
		newSql.append(" WHERE NUM_ > " + start);

		return newSql.toString();

	}

	/**
	 * 获取增加mySql分页功能的SQL语句
	 * 
	 * @param pagedResult
	 * @param query
	 * @return
	 */
	public static String getMysqlPagedSql(String sql, int pagesize, int end) {
		StringBuffer newSql = new StringBuffer();
		newSql.append(" limit ");
		newSql.append(end);
		newSql.append(",");
		newSql.append(pagesize);
		return sql + newSql.toString();
	}

}
