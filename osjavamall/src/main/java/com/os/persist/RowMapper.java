package com.os.persist;

import java.sql.ResultSet;

public abstract interface RowMapper<T> {
	public abstract T mapRow(ResultSet paramResultSet, int paramInt);
}
