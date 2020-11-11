package com.dc.common.lang.annotation;

import java.lang.annotation.*;

/**
 * @author zhuangcy
 * @date 2020/11/11 14:43
 * @description 缓存注解
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Cache {
    /**
     * 缓存名称
     */
    public String cacheName() default "";

}
