package com.os.common.util;

public class ClassLoaderUtils {
	/**
	 * 获取类加载器
	 * 
	 * @return
	 */
	public static ClassLoader getClassLoader() {
		ClassLoader contextCL = Thread.currentThread().getContextClassLoader();
		ClassLoader loader = contextCL == null ? ClassLoaderUtils.class.getClassLoader()
				: contextCL;
		return loader;
	}
}
