package com.os.persist.ibatis;

import java.util.List;

import com.os.persist.BatchExecuteCallback;
import com.os.persist.PaginationSupport;

public abstract interface IBatisPersistence
{
  public abstract List<?> findList(String paramString, Object paramObject);

  public abstract List<?> findList(String paramString, Object paramObject, int paramInt1, int paramInt2);

  public abstract PaginationSupport<?> findPaginatedResult(String paramString, Object paramObject, int paramInt1, int paramInt2);
  
  public abstract Object findObject(String paramString, Object paramObject);

  public abstract int update(String paramString, Object paramObject);

  public abstract int delete(String paramString, Object paramObject);

  public abstract int batchExecute(BatchExecuteCallback paramBatchExecuteCallback);
}