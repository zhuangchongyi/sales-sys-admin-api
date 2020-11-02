package com.dc.common.utils;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Bean 工具类
 */
@Slf4j
public class BeanUtil extends BeanUtils {


    /**
     * Bean方法名中属性名开始的下标
     */
    private static final int BEAN_METHOD_PROP_INDEX = 3;

    /**
     * 匹配getter方法的正则表达式
     */
    private static final Pattern GET_PATTERN = Pattern.compile("get(\\p{javaUpperCase}\\w*)");
    /**
     * 匹配setter方法的正则表达式
     */
    private static final Pattern SET_PATTERN = Pattern.compile("set(\\p{javaUpperCase}\\w*)");

    /**
     * map转object
     *
     * @param bean
     * @param properties
     */
    public static void populate(Object bean, Map<String, ? extends Object> properties) throws InvocationTargetException, IllegalAccessException {
        register();
        org.apache.commons.beanutils.BeanUtils.populate(bean, properties);
    }

    /**
     * 日期转换
     */
    public static void register() {
        BeanUtilsBean.getInstance().getConvertUtils().register(false, false, 0);
        ConvertUtils.register(new Converter() {
            @Override
            public Object convert(Class clazz, Object value) {
                try {
                    if (value != null) {
                        //将String转化为date
                        String source = value.toString();
                        if (DateUtil.FORMAT_YYYYMMDD.length() == source.length()) {
                            return new SimpleDateFormat(DateUtil.FORMAT_YYYYMMDD).parse(value.toString());
                        } else if (DateUtil.FORMAT_YYYYMMDDHHMMSS.length() == source.length()) {
                            return new SimpleDateFormat(DateUtil.FORMAT_YYYYMMDDHHMMSS).parse(value.toString());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        }, Date.class);
    }

    /**
     * Bean属性复制工具方法。
     *
     * @param dest 目标对象
     * @param src  源对象
     */
    public static void copyBeanProp(Object dest, Object src) {
        try {
            copyProperties(src, dest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取对象的setter方法。
     *
     * @param obj 对象
     * @return 对象的setter方法列表
     */
    public static List<Method> getSetterMethods(Object obj) {
        // setter方法列表
        List<Method> setterMethods = new ArrayList<Method>();

        // 获取所有方法
        Method[] methods = obj.getClass().getMethods();

        // 查找setter方法

        for (Method method : methods) {
            Matcher m = SET_PATTERN.matcher(method.getName());
            if (m.matches() && (method.getParameterTypes().length == 1)) {
                setterMethods.add(method);
            }
        }
        // 返回setter方法列表
        return setterMethods;
    }

    /**
     * 获取对象的getter方法。
     *
     * @param obj 对象
     * @return 对象的getter方法列表
     */

    public static List<Method> getGetterMethods(Object obj) {
        // getter方法列表
        List<Method> getterMethods = new ArrayList<Method>();
        // 获取所有方法
        Method[] methods = obj.getClass().getMethods();
        // 查找getter方法
        for (Method method : methods) {
            Matcher m = GET_PATTERN.matcher(method.getName());
            if (m.matches() && (method.getParameterTypes().length == 0)) {
                getterMethods.add(method);
            }
        }
        // 返回getter方法列表
        return getterMethods;
    }

    /**
     * 检查Bean方法名中的属性名是否相等。<br>
     * 如getName()和setName()属性名一样，getName()和setAge()属性名不一样。
     *
     * @param m1 方法名1
     * @param m2 方法名2
     * @return 属性名一样返回true，否则返回false
     */

    public static boolean isMethodPropEquals(String m1, String m2) {
        return m1.substring(BEAN_METHOD_PROP_INDEX).equals(m2.substring(BEAN_METHOD_PROP_INDEX));
    }
}
