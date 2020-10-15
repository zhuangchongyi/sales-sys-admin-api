package com.dc.common.utils;

import com.dc.common.exception.UtilsException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 该工具类解决对象转化中的try-catch问题
 * 1.对象转JSON toJSON
 * 2.JSON转对象    toObject
 */
public class ObjectMapperUtil {
    //定义成员变量时不允许修改
    private static final ObjectMapper mapper = new ObjectMapper();

    public static String toJSON(Object obj) {
        String json = null;
        try {
            json = mapper.writeValueAsString(obj);
        } catch (Exception e) {
            e.printStackTrace();
            //如果报错需要转化为运行时异常
            throw new UtilsException("对象转换json出现异常");
        }
        return json;
    }

    //用户传入class 返回该类型的对象
    public static <T> T toObject
    (String json, Class<T> target) {
        T t = null;
        try {
            t = mapper.readValue(json, target);
        } catch (Exception e) {
            e.printStackTrace();
            throw new UtilsException("json转换对象出现异常");
        }
        return t;
    }
}