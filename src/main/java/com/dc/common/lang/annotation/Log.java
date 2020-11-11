package com.dc.common.lang.annotation;

import com.dc.common.lang.enums.BusinessType;
import com.dc.common.lang.enums.OperatorType;

import java.lang.annotation.*;
/**
 * @author zhuangcy
 * @date 2020/11/9 18:09
 * @description 自定义操作日志记录注解
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
    /**
     * 模块
     */
    public String title() default "";

    /**
     * 功能
     */
    public BusinessType businessType() default BusinessType.OTHER;

    /**
     * 操作人类别
     */
    public OperatorType operatorType() default OperatorType.MANAGE;

    /**
     * 是否保存请求的参数
     */
    public boolean isSaveRequestData() default true;
}
