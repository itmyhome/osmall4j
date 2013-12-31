package com.os.util;

import java.beans.BeanInfo;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.util.Assert;

public class BeanUtil {
	public static final String log001 = "为类{0}的{1}赋值出错。值为{2}";

	public static void setProperty(Object bean, String name, Object value)
			throws IllegalAccessException, InvocationTargetException {
		BeanUtils.setProperty(bean, name, value);
	}

	public static Object getProperty(Object bean, String field)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		return BeanUtils.getProperty(bean, field);
	}

	public static void setPropertyForce(Object target, String fname,
			Class ftype, Object fvalue) {
		Assert.notNull(target, "You must provide the target object to invoke.");
		Assert.hasText(fname,
				"You must provide the parameter of field name to set.");

		Assert.notNull(fvalue,
				"You must provide the parameter of field value to set.");

		Assert.isTrue(ftype.isAssignableFrom(fvalue.getClass()),
				"You must keep the value class type is a field's class type.");

		Class clazz = target.getClass();
		try {
			Method method = clazz.getDeclaredMethod(
					"set" + Character.toUpperCase(fname.charAt(0))
							+ fname.substring(1), new Class[] { ftype });

			if (!Modifier.isPublic(method.getModifiers())) {
				method.setAccessible(true);
			}

			method.invoke(target, new Object[] { fvalue });
		} catch (Exception e) {
			try {
				Field field = clazz.getDeclaredField(fname);
				if (!Modifier.isPublic(field.getModifiers())) {
					field.setAccessible(true);
				}
				field.set(target, fvalue);
			} catch (Exception fe) {
				
			}
		}
	}
}
