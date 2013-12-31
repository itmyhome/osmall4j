package com.os.persist.dialect;

import com.os.persist.support.ArrayUtil;

public class OracleDialect extends AbstractDialect
{
  public boolean useMaxForLimit()
  {
    return true;
  }

  public String getLimitString(String sql, int offset, int limit) {
    sql = sql.trim();
    boolean isForUpdate = false;
    if (sql.toLowerCase().endsWith(" for update")) {
      sql = sql.substring(0, sql.length() - 11);
      isForUpdate = true;
    }

    StringBuilder pagingSelect = new StringBuilder(sql.length() + 100);
    pagingSelect.append("select * from ( select row_.*, rownum rownum_ from ( ");

    pagingSelect.append(sql);
    if (offset > 0)
    {
      pagingSelect.append(" ) row_ ) where rownum_ <= ? and rownum_ > ?");
    }
    else
    {
      pagingSelect.append(" ) row_ ) where rownum_ <= ?");
    }

    if (isForUpdate) {
      pagingSelect.append(" for update");
    }
    return pagingSelect.toString();
  }

  public Object[] getLimitedParameterArray(Object[] param, int offset, int limit)
  {
    Object[] pageParam = null;

    if (offset > 0) {
      pageParam = new Object[2];

      pageParam[0] = Integer.valueOf(offset + limit);

      pageParam[1] = Integer.valueOf(offset);
    } else {
      pageParam = new Object[1];

      pageParam[0] = Integer.valueOf(offset + limit);
    }

    return (Object[])ArrayUtil.concat(param, pageParam);
  }
}