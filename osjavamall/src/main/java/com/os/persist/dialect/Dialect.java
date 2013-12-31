package com.os.persist.dialect;

public abstract interface Dialect
{
  public abstract String getForUpdateString(String paramString);

  public abstract String getForUpdateString();

  public abstract boolean useMaxForLimit();

  public abstract boolean supportsLimit();

  public abstract boolean supportsLimitOffset();

  public abstract String getLimitString(String paramString, int paramInt1, int paramInt2);

  public abstract Object[] getLimitedParameterArray(Object[] paramArrayOfObject, int paramInt1, int paramInt2);
}