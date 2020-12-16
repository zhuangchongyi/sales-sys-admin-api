package com.dc.framework.handler;

import com.dc.common.exception.RepeatSubmitException;
import com.dc.common.exception.ServiceException;
import com.dc.common.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.core.NestedRuntimeException;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.Arrays;

/**
 * @Author zhuangchongyi
 * @Description 全局异常处理类
 * @Date 2020/8/28 17:31
 */
@RestController
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ShiroException.class)
    public R shiroException(Exception e, HttpServletRequest request) {
        log.info("请求方式：{}, 请求路径：{}, 异常信息：{}", request.getMethod(), request.getServletPath(), e.getMessage());
        if (e instanceof UnknownAccountException) {
            return R.error().code(R.AUTH_FAIL_CODE).msg("登录账号不存在");
        } else if (e instanceof IncorrectCredentialsException) {
            return R.error().code(R.AUTH_FAIL_CODE).msg("输入密码有误");
        } else if (e instanceof LockedAccountException) {
            return R.error().code(R.AUTH_FAIL_CODE).msg("账号已被锁定,请联系管理员");
        } else if (e instanceof DisabledAccountException) {
            return R.error().code(R.AUTH_FAIL_CODE).msg("登录账号已禁用");
        } else if (e instanceof ExpiredCredentialsException) {
            return R.error().code(R.UNAUTHORIZED).msg("登录已过期，请重新登录");
        } else if (e instanceof UnauthorizedException) {
            return R.error().code(R.UNAUTHORIZED).msg("您的权限不足，无法访问");
        }
        return R.error().code(R.ERROR_CODE).msg(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public R exception(Exception e, HttpServletRequest request) {
        log.info("请求方式：{}, 请求路径：{}, 异常信息：{}", request.getMethod(), request.getServletPath(), e.getMessage());
        log.error("异常信息: ", e);
        return R.error().msg(R.MESSAGE_ERROR);
    }

    @ExceptionHandler(ServiceException.class)
    public R serviceException(ServiceException e, HttpServletRequest request) {
        log.info("请求方式：{}, 请求路径：{}, 异常信息：{}", request.getMethod(), request.getServletPath(), e.getMessage());
        log.error("异常信息: ", e);
        return R.error().code(e.getCode()).msg(e.getMessage());
    }

    @ExceptionHandler(RepeatSubmitException.class)
    public R repeatSubmitException(RepeatSubmitException e, HttpServletRequest request) {
        log.info("请求方式：{}, 请求路径：{}, 异常信息：{}", request.getMethod(), request.getServletPath(), e.getMessage());
        return R.error().code(R.FAIL_CODE).msg(e.getMessage());
    }

    @ExceptionHandler(NestedRuntimeException.class)
    public R nestedRuntimeException(NestedRuntimeException e, HttpServletRequest request) {
        log.info("请求方式：{}, 请求路径：{}, 异常信息：{}", request.getMethod(), request.getServletPath(), e.getMessage());
        if (e instanceof HttpMessageConversionException) {
            return R.error().msg("类型转换异常");
        } else {
            log.error("异常信息: ", e);
            return R.error().msg(R.MESSAGE_ERROR);
        }
    }

    @ExceptionHandler(ServletException.class)
    public R servletException(ServletException e, HttpServletRequest request) {
        log.info("请求方式：{}, 请求路径：{}, 异常信息：{}", request.getMethod(), request.getServletPath(), e.getMessage());
        if (e instanceof HttpRequestMethodNotSupportedException) { 
            //请求方式处理异常
            return R.error().code(R.REQUEST_FAIL_CODE).msg("请求方式错误");
        } else {
            log.error("异常信息: ", e);
            return R.error().msg(R.MESSAGE_ERROR);
        }
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R methodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        log.info("请求方式：{}, 请求路径：{}, 异常信息：{}", request.getMethod(), request.getServletPath(), e.getMessage());
        BindingResult bindingResult = e.getBindingResult();
        StringBuilder sb = new StringBuilder("校验失败：");
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            sb.append(fieldError.getField()).append("：").append(fieldError.getDefaultMessage()).append("; ");
        }
        String msg = sb.toString();
        log.error("异常信息: {}", msg);
        return R.error().msg(msg);

    }

    @ExceptionHandler(ValidationException.class)
    public R validationException(ValidationException e, HttpServletRequest request) {
        log.info("请求方式：{}, 请求路径：{}, 异常信息：{}", request.getMethod(), request.getServletPath(), e.getMessage());
        if (e instanceof ConstraintViolationException) {
            ConstraintViolationException ex = (ConstraintViolationException) e;
            String msg = Arrays.toString(ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage).toArray());
            log.error("异常信息: {}", msg);
            return R.error().msg(msg);
        }
        log.error("异常信息: ", e);
        return R.error().msg(e.getMessage());

    }

}
