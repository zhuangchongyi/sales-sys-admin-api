package com.dc.common.lang.annotation;

import java.lang.annotation.*;

/**
 * @author zhuangcy
 * @date 2020/10/31 12:13
 * @description 数据权限过滤注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataScope {
    /**
     * 部门表的别名
     */
    String deptAlias() default "";

    /**
     * 用户表的别名
     */
    String userAlias() default "";

    /**
     * 字段名
     *
     * @return
     */
    String userColumn() default "";

}
