package com.dc.framework.aspectj;


import com.dc.common.lang.annotation.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author zhuangcy
 * @date 2020/10/31 12:10
 * @description 数据权限过滤处理类
 */
@Aspect
@Component
public class LogAspectj {

    /**
     * 配置切入点
     */
    @Pointcut("@annotation(com.dc.common.lang.annotation.Log)")
    public void logPointcut() {
    }

    @AfterReturning(pointcut = "logPointcut()")
    public void doAfterReturning(JoinPoint point) {
        System.out.println("操作成功时执行");
    }

    @AfterThrowing(pointcut = "logPointcut()")
    public void doAfterThrowing(JoinPoint point) throws Throwable {
        System.out.println("操作失败时执行");
    }


    /**
     * 获取注解
     *
     * @param point
     * @return
     */
    private Log getAnnotation(JoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        if (null != method) {
            return method.getAnnotation(Log.class);
        }
        return null;
    }

}
