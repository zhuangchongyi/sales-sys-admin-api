package com.dc.framework.annotation;

/**
 * @author zhuangcy
 * @date 2020/10/31 12:13
 * @description 数据权限过滤注解
 */
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
     * 其他表的别名
     *
     * @return
     */
    String otherAlias() default "";
}
