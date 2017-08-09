package com.hgsil.android.fragementtest.Utils.JsonUtils;

/**
 * Created by Administrator on 2017/5/17 0017.
 */


import android.support.annotation.NonNull;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * 反射工具类
 */

public class ReflectUtil {
    /**
     * 将object对象的名为fieldName的域赋值为value
     *
     * @param object    待赋值的对象
     * @param fieldName 域的名字
     * @param value     待赋的值
     */
    public static void setValue(@NonNull Object object, @NonNull String fieldName, Object value) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(object, value);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取cls对应的类中名为name的域
     */
    private static Field getField(@NonNull Class cls, @NonNull String FieldName) {
        try {
            return cls.getDeclaredField(FieldName);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取cls对应类中名为fieldName的域对应的类型
     */
    public static Class getFieldClass(@NonNull Class cls, @NonNull String fieldName) {
        Field f = getField(cls, fieldName);
        if (f == null)
            return null;
        Class fieldClass = f.getType();
        if (fieldClass.isAssignableFrom(List.class)) {
            //获取list的泛型类型
            Type type = f.getGenericType();
            if (type != null && type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                fieldClass = (Class) parameterizedType.getActualTypeArguments()[0];
            }
        }
        return fieldClass;
    }

    /**
     * 生成指定类型的实例
     */
    public static <T> T getInstance(@NonNull Class<T> cls) {
        try {
            return cls.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}