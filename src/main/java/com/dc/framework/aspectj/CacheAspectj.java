package com.dc.framework.aspectj;


import com.dc.common.lang.annotation.Cache;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author zhuangcy
 * @date 2020/11/11 14:43
 * @description 缓存处理切面类
 */
@Aspect
@Component
@Slf4j
public class CacheAspectj {
    @Autowired
    private EhCacheManager cacheManager;

    @Pointcut("@annotation(com.dc.common.lang.annotation.Cache)")
    public void cachePointcut() {
    }

    @AfterReturning(pointcut = "cachePointcut()")
    public void doAfterReturning(JoinPoint point) {
        Cache cacheAnn = getAnnotation(point);
        if (cacheAnn != null) {
            log.info(cacheAnn.cacheName());
        }
    }

    private Cache getAnnotation(JoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        if (null != method) {
            return method.getAnnotation(Cache.class);
        }
        return null;
    }

}
