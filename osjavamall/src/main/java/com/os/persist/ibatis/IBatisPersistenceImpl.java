package com.os.persist.ibatis;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.util.Assert;

import com.os.persist.BatchExecuteCallback;
import com.os.persist.PaginationSupport;
import com.os.util.BeanUtil;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.engine.execution.SqlExecutor;
import com.ibatis.sqlmap.engine.impl.ExtendedSqlMapClient;

public class IBatisPersistenceImpl extends SqlMapClientDaoSupport implements
		IBatisPersistence {

	private static final Log logger = LogFactory
			.getLog(IBatisPersistenceImpl.class);
	private SqlExecutor sqlExecutor;

	public void initialize() {
		logger.info("ibatisPersistenceImpl init");
//		if (this.sqlExecutor != null) {
			SqlMapClient sqlMapClient = getSqlMapClientTemplate()
					.getSqlMapClient();
//		}
		logger.info("ibatisPersistenceImpl init end ");
		 BeanUtil.setPropertyForce(((ExtendedSqlMapClient)sqlMapClient).getDelegate(), "sqlExecutor", this.sqlExecutor.getClass(), this.sqlExecutor);
	}

	public List<?> findList(String id, Object parameterObject) {
		return getSqlMapClientTemplate().queryForList(id, parameterObject);
	}

	public List<?> findList(String id, Object parameterObject, int start,
			int maxRows) {
		return getSqlMapClientTemplate().queryForList(id, parameterObject,
				start, maxRows);
	}

	public PaginationSupport<?> findPaginatedResult(String id,
			Object parameterObject, int start, int maxRows) {
		Assert.isTrue(maxRows != 0, "传入的结果返回数'maxRows'参数不允许为0.");
		Assert.hasText(id, "传入的SQL配置ID不能为空.");

		Integer count = null;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					id + "_COUNT", parameterObject);
		} catch (Exception e) {
			
		}

		int tmpOffset = start < 0 ? 0 : start;
		int tmpMaxRows = maxRows <= 0 ? 1 : maxRows;

		if ((count == null) || (count.intValue() <= 0)) {
			return new PaginationSupport(new LinkedList(), 0, tmpMaxRows,
					tmpOffset);
		}

		try {
			if (id.endsWith("_FORCE")) {
				IBatisExecuteContextUtil.setIsForced(Boolean.TRUE);
			}
			List resultList = getSqlMapClientTemplate().queryForList(id,
					parameterObject, tmpOffset, tmpMaxRows);

			PaginationSupport localPaginationSupport = new PaginationSupport(
					resultList, count.intValue(), tmpMaxRows, tmpOffset);
			return localPaginationSupport;
		} finally {
			IBatisExecuteContextUtil.setIsForced(Boolean.FALSE);
		}
	}

	public Object findObject(String id, Object parameterObject) {
		return getSqlMapClientTemplate().queryForObject(id, parameterObject);
	}

	public int update(String id, Object parameterObject) {
		return getSqlMapClientTemplate().update(id, parameterObject);
	}

	public int delete(String id, Object parameterObject) {
		return getSqlMapClientTemplate().delete(id, parameterObject);
	}

	public int batchExecute(BatchExecuteCallback callback) {
		return 0;
	}

	public void setSqlExecutor(SqlExecutor sqlExecutor) {
		this.sqlExecutor = sqlExecutor;
	}
}
