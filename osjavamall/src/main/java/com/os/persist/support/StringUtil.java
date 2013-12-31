package com.os.persist.support;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

public class StringUtil
{
  private static final Log log = LogFactory.getLog(StringUtil.class);
  public static final String log001 = "无法获取类字段名,字段名：{0}";
  public static final String log002 = "无法获取类字段相应的get方法";
  public static final String log003 = "方法调用失败";
  public static final String log004 = "转码出错,将源字符串返回";

  public static String toString(Object obj)
  {
    if (obj == null)
      return null;
    Class objClass = obj.getClass();
    if (objClass.getName().startsWith("java.lang")) {
      return obj.toString();
    }
    StringBuilder result = new StringBuilder();
    if (isSubClassOf(objClass, "Collection")) {
      result.append(processIterator(((Collection)obj).iterator(), objClass));
    }
    else if (isSubClassOf(objClass, "Map")) {
      result.append(processMap((Map)obj, objClass));
    } else if (isSubClassOf(objClass, "Iterator")) {
      result.append(processIterator((Iterator)obj, objClass));
    } else if (isSubClassOf(objClass, "Enumeration")) {
      result.append(processEnumeration((Enumeration)obj, objClass));
    } else if (objClass.isAssignableFrom(Object.class)) {
      Object[] array = (Object[])obj;

      result.append('[');
      for (int i = 0; i < array.length; i++) {
        result.append(array[i]).append(':').append(array[i] != null ? array[i].getClass().getName() : "NULL");

        if (i < array.length - 1) {
          result.append(',');
        }
      }
      result.append(']');
    } else {
      Method[] methods = null;
      Field[] fields = objClass.getDeclaredFields();
      if ((!objClass.getName().startsWith("java")) && (fields.length > 0)) {
        result.append(obj.getClass().getName()).append(":[");
        for (int i = 0; i < fields.length; i++) {
          result.append(fields[i].getName()).append(':');
          if (fields[i].isAccessible()) {
            try {
              result.append(toString(fields[i].get(obj)));
            } catch (IllegalAccessException iae) {
              log.error(iae);
            }
          } else {
            if (methods == null) {
              methods = objClass.getMethods();
            }
            for (int j = 0; j < methods.length; j++) {
              if (("get" + fields[i].getName()).equalsIgnoreCase(methods[j].getName())) {
                try
                {
                  result.append(toString(methods[j].invoke(obj, null)));
                }
                catch (IllegalAccessException iae) {
                  log.error(iae);
                } catch (InvocationTargetException ite) {
                  log.error(ite);
                }
              }
            }
          }
          result.append("; ");
        }
        result.append(']');
      } else {
        result.append(obj);
        return result.toString();
      }
    }
    return result.toString();
  }

  private static boolean isSubClassOf(Class objClass, String className) {
    do {
      if (isClassOrInterface(objClass, className)) {
        return true;
      }
      objClass = objClass.getSuperclass();
    }while (!objClass.equals(objClass = Object.class));

    return false;
  }

  private static boolean isClassOrInterface(Class objClass, String className) {
    if (objClass.getClass().getName().equals(className))
      return true;
    Class[] classes = objClass.getInterfaces();
    for (int i = 0; i < classes.length; i++) {
      if (("java.util." + className).equals(classes[i].getName()))
        return true;
    }
    return false;
  }

  private static String processEnumeration(Enumeration enumeration, Class objClass)
  {
    StringBuilder result = new StringBuilder();
    result.append(objClass.getName());
    result.append('{');
    while (enumeration.hasMoreElements()) {
      result.append(toString(enumeration.nextElement()));
      result.append("; ");
    }
    result.append('}');
    return result.toString();
  }

  private static String processIterator(Iterator iterator, Class objClass) {
    StringBuilder result = new StringBuilder();
    result.append(objClass.getName());
    result.append('{');
    while (iterator.hasNext()) {
      result.append(toString(iterator.next()));
      result.append("; ");
    }
    result.append('}');
    return result.toString();
  }

  private static String processMap(Map map, Class objClass) {
    StringBuilder result = new StringBuilder();
    Collection keys = map.keySet();
    Iterator iterator = keys.iterator();
    result.append(objClass.getName());
    result.append('{');
    while (iterator.hasNext()) {
      Object obj = iterator.next();
      result.append(obj).append('=').append(toString(map.get(obj))).append("; ");
    }

    result.append('}');
    return result.toString();
  }

  public static String convertEncode(String strIn, String encoding, String targetEncoding)
  {
    String strOut = strIn;

    if (!StringUtils.hasText(strIn))
      return strOut;
    try
    {
      if ((encoding != null) && (targetEncoding != null)) {
        if (!encoding.equalsIgnoreCase(targetEncoding))
          strOut = new String(strIn.getBytes(encoding), targetEncoding);
      }
      else if (encoding != null)
        strOut = new String(strIn.getBytes(encoding));
      else if (targetEncoding != null)
        strOut = new String(strIn.getBytes(), targetEncoding);
      else
        return strOut;
    }
    catch (UnsupportedEncodingException e)
    {
      log.error(e);
      return strIn;
    }
    return strOut;
  }
}