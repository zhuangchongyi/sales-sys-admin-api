package com.dc.framework.aspectj;

import com.dc.common.exception.RepeatSubmitException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * @author zhuangcy
 * @date 2020/11/5 16:54
 * @description 防止重复提交请求拦截器
 */
@Slf4j
@Aspect
@Component
public class RepeatSubmitAspectj {
    // 线程安全set
    private static final Set<String> KEYS = new ConcurrentSkipListSet<>();

    @Pointcut("@annotation(com.dc.common.lang.annotation.RepeatSubmit)")
    public void repeatSubmitPointcut() {
    }

    @Around("repeatSubmitPointcut()")
    public Object doBefore(ProceedingJoinPoint joinPoint) throws Throwable {
        String key = getRepeatSubmitKey(joinPoint);
        boolean success = KEYS.add(key);
        log.info(String.format("设置重复提交key=%s", key));
        if (success) {
            try {
                return joinPoint.proceed();
            } finally {
                KEYS.remove(key);
                log.info(String.format("删除重复提交key=%s", key));
            }
        } else {
            throw new RepeatSubmitException();
        }
    }

    private String getRepeatSubmitKey(JoinPoint point) {
        StringBuilder builder = new StringBuilder();
        builder.append(point.getTarget().getClass().getName()).append(".").append(point.getSignature().getName()).append(":");
        Object[] args = point.getArgs();
        for (Object object : args) {
            if (object != null) {
                builder.append(object.getClass().toString());
                builder.append(object.toString());
            }
        }
        return builder.toString();
    }

}
