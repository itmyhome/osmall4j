package com.os.persist.ibatis;

public class IBatisExecuteContextUtil {
	private static ThreadLocal<Boolean> IS_Forced = new ThreadLocal();

	public static boolean isForced() {
		if (IS_Forced.get() != null) {
			return ((Boolean) IS_Forced.get()).booleanValue();
		}
		return false;
	}

	public static void setIsForced(Boolean isForced) {
		IS_Forced.set(isForced);
	}
}
