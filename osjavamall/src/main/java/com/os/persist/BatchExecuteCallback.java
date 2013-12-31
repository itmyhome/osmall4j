package com.os.persist;

public abstract interface BatchExecuteCallback
{
  public abstract int doInBatch();
}