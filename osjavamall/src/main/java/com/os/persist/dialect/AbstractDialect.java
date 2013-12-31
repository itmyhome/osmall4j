package com.os.persist.dialect;

public abstract class AbstractDialect implements Dialect {
	public String getForUpdateString(String aliases) {
		return getForUpdateString();
	}

	public String getForUpdateString() {
		return " for update";
	}

	public boolean useMaxForLimit() {
		return false;
	}

	public boolean supportsLimit() {
		return true;
	}

	public boolean supportsLimitOffset() {
		return true;
	}

	public String getLimitString(String querySelect, int offset, int limit) {
		throw new UnsupportedOperationException("不支持分页SQL语法.");
	}
}
