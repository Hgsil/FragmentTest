package com.hgsil.android.fragementtest.Utils.JsonUtils;

import android.support.annotation.NonNull;

import com.hgsil.android.fragementtest.Utils.JsonUtils.ReflectUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2017/2/9 0009.
 */

public class JsonUtil {
    /**
     * 将json解析为T类型的对象
     */
    public static <T> T getObject(@NonNull String json, @NonNull Class<T> cls) {
        T object = null;
        try {
            object = cls.newInstance();
            JSONObject jsonObject = new JSONObject(json);
            Iterator<String> iterator = jsonObject.keys();
            while (iterator.hasNext()) {
                String key = iterator.next();
                String value = jsonObject.getString(key);

                //判断value是值、类还是数组
                if (value.startsWith("{")) {
                    Object innerObject = parseInnerClass(value, key, cls);
                    ReflectUtil.setValue(object, key, innerObject);
                } else if (value.startsWith("[")) {
                    List<Object> objects = parseList(jsonObject.getJSONArray(key), key, cls);
                    ReflectUtil.setValue(object, key, objects);
                } else {
                    if (value.equals("null"))
                        ReflectUtil.setValue(object, key, null);
                    else
                        ReflectUtil.setValue(object, key, jsonObject.get(key));
                }
            }
        } catch (IllegalAccessException | JSONException | InstantiationException e) {
            e.printStackTrace();
        }
        return object;
    }

    /**
     * 解析嵌套的json
     *
     * @param json 内层json字符串
     * @param key  域名
     * @param cls  外层json的类型
     * @return 解析出的对象
     */
    private static Object parseInnerClass(String json, String key, Class cls) {
        Class innerCls = ReflectUtil.getFieldClass(cls, key);
        if (innerCls != null)
            return getObject(json, innerCls);
        return null;
    }

    /**
     * 解析json数组
     *
     * @param array json数组
     * @param key   域名
     * @param cls   外层json的类型
     * @return 解析出来的数组
     */
    private static List<Object> parseList(JSONArray array, String key, Class cls) {
        Class fieldClass = ReflectUtil.getFieldClass(cls, key);
        if (fieldClass == null)
            return null;
        List<Object> list = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            try {
                String object = array.getString(i);
                if (object.startsWith("{")) {
                    //判断是否是对象类型
                    list.add(getObject(object, fieldClass));
                } else {
                    //基础类型直接放入（包括String）
                    list.add(object);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

}
