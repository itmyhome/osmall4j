package com.os.persist.dialect;

import com.os.persist.support.ArrayUtil;

public class InformixDialect extends AbstractDialect
{
  public boolean useMaxForLimit()
  {
    return true;
  }

  public boolean supportsLimitOffset()
  {
    return false;
  }

  public String getLimitString(String querySelect, int offset, int limit) {
    if (offset > 0)
      throw new UnsupportedOperationException("Informix不支持offset.");
    return new StringBuffer(querySelect.length() + 8).append(querySelect).insert(querySelect.toUpperCase().indexOf("SELECT") + "SELECT".length(), " first " + limit).toString();
  }

  public Object[] getLimitedParameterArray(Object[] param, int offset, int limit)
  {
    return (Object[])ArrayUtil.concat(param, null);
  }
}