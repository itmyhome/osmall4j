package com.os.persist.support;

import java.lang.reflect.Array;

public class ArrayUtil
{
  public static Class<?> commonClass(Class<?> c1, Class<?> c2)
  {
    if (c1 == c2) {
      return c1;
    }

    if ((c1 == Object.class) || (c1.isAssignableFrom(c2))) {
      return c1;
    }

    if (c2.isAssignableFrom(c1)) {
      return c2;
    }

    if ((c1.isPrimitive()) || (c2.isPrimitive()))
    {
      throw new IllegalArgumentException("incompatible types " + c1 + " and " + c2);
    }

    return Object.class;
  }

  public static Object concat(Object arr1, Object arr2)
  {
    int len1 = arr1 == null ? -1 : Array.getLength(arr1);

    if (len1 <= 0) {
      return arr2;
    }

    int len2 = arr2 == null ? -1 : Array.getLength(arr2);

    if (len2 <= 0) {
      return arr1;
    }

    Class commonComponentType = commonClass(arr1.getClass().getComponentType(), arr2.getClass().getComponentType());

    Object newArray = Array.newInstance(commonComponentType, len1 + len2);
    System.arraycopy(arr1, 0, newArray, 0, len1);
    System.arraycopy(arr2, 0, newArray, len1, len2);

    return newArray;
  }
}