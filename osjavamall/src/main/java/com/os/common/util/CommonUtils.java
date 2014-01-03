package com.os.common.util;

import java.text.SimpleDateFormat;

public class CommonUtils {
	public static final String DATE_YYYYMMDD_FORMAT = "yyyyMMdd";
	public static final String DATE_YYYYMMDD_HHMMSS_FORMAT = "yyyyMMdd";
	/**
	 * 格式化日期
	 * @param formatStr
	 * @return
	 */
	public static String dateFormat(Object obj, String formatStr){
		SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
		return sdf.format(obj);
	}
	

}
