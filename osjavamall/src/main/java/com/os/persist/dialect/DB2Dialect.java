package com.os.persist.dialect;

import com.os.persist.support.ArrayUtil;

public class DB2Dialect extends AbstractDialect {
	public String getLimitString(String querySelect, int offset, int limit) {
		if (offset < 100) {
			return getLimitStringByFetch(querySelect, offset, limit);
		}
		return getLimitStringAsPaging(querySelect, offset, limit);
	}

	public Object[] getLimitedParameterArray(Object[] param, int offset,
			int limit) {
		Object[] pageParam = null;

		if (offset <= 100) {
			if (offset > 0) {
				pageParam = new Object[2];

				pageParam[0] = Integer.valueOf(offset + 1);

				pageParam[1] = Integer.valueOf(offset + limit);
			} else {
				pageParam = new Object[1];

				pageParam[0] = Integer.valueOf(limit);
			}
		} else {
			pageParam = new Object[2];

			pageParam[0] = Integer.valueOf(offset + 1);

			pageParam[1] = Integer.valueOf(offset + limit);
		}

		return (Object[]) ArrayUtil.concat(param, pageParam);
	}

	public String getLimitString(String sql, boolean hasOffset) {
		return sql;
	}

	protected String getLimitStringAsPaging(String sql, int offset, int limit) {
		int startOfSelect = sql.toLowerCase().indexOf("select");
		StringBuilder pagingSelect = new StringBuilder(sql.length() + 100);
		if (startOfSelect > 0) {
			pagingSelect.append(sql.substring(0, startOfSelect))
					.append("select * from ( select ")
					.append(getRowNumber(sql));

			if (hasDistinct(sql)) {
				pagingSelect.append(" row_.* from ( ")
						.append(sql.substring(startOfSelect))
						.append(" ) as row_");
			} else {
				pagingSelect.append(sql.substring(startOfSelect + 6));
			}

			pagingSelect.append(" ) as temp_ where rownumber_ ");

			if (offset > 0) {
				pagingSelect.append("between ? and ?");
			} else {
				pagingSelect.append("<= ?");
			}
		}

		return pagingSelect.toString();
	}

	private String getRowNumber(String sql) {
		StringBuilder rownumber = new StringBuilder(50)
				.append("rownumber() over(");

		int orderByIndex = sql.toLowerCase().indexOf("order by");

		if ((orderByIndex > 0) && (!hasDistinct(sql))) {
			rownumber.append(sql.substring(orderByIndex));
		}

		rownumber.append(") as rownumber_,");

		return rownumber.toString();
	}

	protected String getLimitStringByFetch(String sql, int offset, int limit) {
		int startOfSelect = sql.toLowerCase().indexOf("select");
		StringBuilder pagingSelect = new StringBuilder(sql.length() + 100);
		if (startOfSelect >= 0) {
			pagingSelect.append(sql.substring(0, startOfSelect))
					.append("select * from ( select ")
					.append(getRowNumber(sql));

			if (hasDistinct(sql)) {
				pagingSelect.append(" row_.* from ( ")
						.append(sql.substring(startOfSelect))
						.append(" fetch first 100 rows only")
						.append(" ) as row_");
			} else {
				pagingSelect.append(sql.substring(startOfSelect + 6)).append(
						" fetch first 100 rows only");
			}

			pagingSelect.append(" ) as temp_ where rownumber_ ");

			if (offset > 0) {
				pagingSelect.append("between ? and ?");
			} else {
				pagingSelect.append("<= ?");
			}
		}
		return pagingSelect.toString();
	}

	private static boolean hasDistinct(String sql) {
		return sql.toLowerCase().indexOf("select distinct") >= 0;
	}

	public String getForUpdateString() {
		return " for read only with rs";
	}

	private String getForReadOnlyString() {
		return " for read only with ur";
	}
}
