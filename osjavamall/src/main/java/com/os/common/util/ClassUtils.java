package com.os.common.util;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.lang3.reflect.ConstructorUtils;
import org.apache.log4j.Logger;

/**
 * 类操作工具类
 * 
 * 
 */
public class ClassUtils {
	private static final Logger LOGGER = Logger.getLogger(ClassUtils.class);
	private static final String CGLIB_CLASS_SEPARATOR = "$$";
	/** ".class"文件扩展名 */
	private static final String CLASS_FILE_SUFFIX = ".class";
	public static final String CLASSPATH_PREFIX = "classpath:";

	/**
	 * 加载类并创建class实例
	 * 
	 * @param <T>
	 * @param className
	 * @param parameters
	 * @param parameterTypes
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T createInstance(String className, Object[] parameters,
			Class<?>[] parameterTypes) {
		Class<?> clazz = null;
		T bean = null;
		try {
			clazz = org.apache.commons.lang.ClassUtils.getClass(className);
			bean = (T) ConstructorUtils.invokeConstructor(clazz, parameters,
					parameterTypes);
		} catch (ClassNotFoundException e) {
			LOGGER.error("找不到multipartParserClass参数配置的类[" + clazz + "]", e);
		} catch (NoSuchMethodException e) {
			LOGGER.error("[" + clazz.getName() + "]中没有找到指定的构造方法", e);
		} catch (IllegalAccessException e) {
			LOGGER.error("无法访问[" + clazz.getName() + "]的构造方法", e);
		} catch (InvocationTargetException e) {
			LOGGER.error("无法访问[" + clazz.getName() + "]的构造方法", e);
		} catch (InstantiationException e) {
			LOGGER.error("无法实例化[" + clazz.getName() + "]", e);
		}

		return bean;
	}

	/**
	 * 返回实例对应的用户实际定义的Class，（CGLIB生成的类是用户定义类的子类）
	 * 
	 * @param instance
	 * @return
	 */
	public static Class<?> getUserClass(Object instance) {
		Assert.notNull(instance, "实例不能为空");
		return getUserClass(instance.getClass());
	}

	/**
	 * 返回用户实际定义的Class，（CGLIB生成的类是用户定义类的子类）
	 * 
	 * @param clazz
	 * @return
	 */
	public static Class<?> getUserClass(Class<?> clazz) {
		if (clazz != null && clazz.getName().contains(CGLIB_CLASS_SEPARATOR)) {
			Class<?> superClass = clazz.getSuperclass();
			if (superClass != null && !Object.class.equals(superClass)) {
				return superClass;
			}
		}
		return clazz;
	}

	/**
	 * 通过class获取对应的.class文件名。比如java.lang.String类会返回"String.class"
	 * 
	 * @param clazz
	 * @return
	 */
	public static String getClassFileName(Class<?> clazz) {
		Assert.notNull(clazz, "Class不能为空");
		String className = clazz.getName();
		int lastDotIndex = className
				.lastIndexOf(org.apache.commons.lang.ClassUtils.PACKAGE_SEPARATOR);
		return className.substring(lastDotIndex + 1) + CLASS_FILE_SUFFIX;
	}
}