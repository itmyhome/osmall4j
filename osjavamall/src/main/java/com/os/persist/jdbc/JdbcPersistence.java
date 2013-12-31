package com.os.persist.jdbc;

import java.sql.Connection;
import java.util.List;

import com.os.persist.PaginationSupport;
import com.os.persist.RowMapper;

public abstract interface JdbcPersistence
{
  public abstract <T> List<T> findList(String paramString, RowMapper<T> paramRowMapper, int paramInt1, int paramInt2, Object[] paramArrayOfObject);

  public abstract <T> PaginationSupport<T> findPaginatedResult(String paramString, RowMapper<T> paramRowMapper, int paramInt1, int paramInt2, Object[] paramArrayOfObject);

  public abstract <T> List<T> findList(String paramString, RowMapper<T> paramRowMapper, Object[] paramArrayOfObject);

  public abstract <T> T findObject(String paramString, RowMapper<T> paramRowMapper, Object[] paramArrayOfObject);

  public abstract int update(String paramString, Object[] paramArrayOfObject);

  public abstract int delete(String paramString, Object[] paramArrayOfObject);

  public abstract Connection currentConnection();
}
