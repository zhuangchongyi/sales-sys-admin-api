package com.dc.common.lang.annotation;

import java.lang.annotation.*;

/**
 * @author zhuangcy
 * @date 2020/11/5 16:20
 * @description 自定义注解防止表单重复提交
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RepeatSubmit {
}
