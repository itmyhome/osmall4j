package com.os.common;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.os.persist.ibatis.IBatisPersistence;
import com.os.persist.jdbc.JdbcPersistence;
@Component
public class SecDaoSupport {
	@Resource
	protected IBatisPersistence ibatisPersistence;
	protected JdbcPersistence jdbcPersistence;
	
	public IBatisPersistence getIbatisPersistence() {
		return ibatisPersistence;
	}

	public void setIbatisPersistence (IBatisPersistence ibatisPersistence) {
		this.ibatisPersistence = ibatisPersistence;
	}
	
	public JdbcPersistence getJdbcPersistence() {
		return jdbcPersistence;
	}
	
	public void setJdbcPersistence(JdbcPersistence jdbcPersistence) {
		this.jdbcPersistence = jdbcPersistence;
	}
}
