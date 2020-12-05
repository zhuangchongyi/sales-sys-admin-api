package com.dc.common.utils;

import com.dc.common.exception.UtilException;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author zhuangcy
 * @date 2020/12/4
 * @description 生成树工具类
 */
public class TreeUtil {

    /**
     *
     * @param list 菜单集合
     * @param map 菜单集合转map（mid，obj）
     * @param <T>
     * @return
     */
    public static <T> List<T> getTree(List<T> list, Map<Integer, T> map) {
        List<T> treeList = new ArrayList<T>();
        if (null != list && !list.isEmpty()) {
            try {
                for (T t : list) {
                    Class<?> tClass = t.getClass();
                    Field parentId = tClass.getDeclaredField("parentId");
                    parentId.setAccessible(true);
                    Object pid = parentId.get(t);
                    // 如果id是父级的话就放入tree中treeList
                    if (null == map.get(pid)) {
                        treeList.add(t);
                    } else {
                        // 子级通过父id获取到父级的类型
                        T parent = map.get(pid);
                        // 父级获得子级，再将子级放到对应的父级中
                        Class<?> aClass = parent.getClass();
                        Field children = aClass.getDeclaredField("children");
                        children.setAccessible(true);
                        List<T> childrens = (List<T>) children.get(t);
                        childrens.add(t);
                    }

                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new UtilException(e);
            }
        }
        return treeList;
    }
}
