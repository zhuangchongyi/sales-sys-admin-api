package com.dc.framework.handler;

import com.dc.common.exception.RepeatSubmitException;
import com.dc.common.exception.ServiceException;
import com.dc.common.exception.UtilsException;
import com.dc.common.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    public R shiroException(Exception e, HttpServletRequest request, HttpServletResponse response) {
        log.info("请求方式：{}, 请求路径：{}, 异常信息：{}", request.getMethod(), request.getServletPath(), e.getMessage());
        if (e instanceof UnknownAccountException) {
            return R.error().code(R.AUTH_FAIL_CODE).msg("登录账号不存在");
        } else if (e instanceof IncorrectCredentialsException) {
            return R.error().code(R.AUTH_FAIL_CODE).msg("输入密码有误");
        } else if (e instanceof DisabledAccountException) {
            return R.error().code(R.AUTH_FAIL_CODE).msg("登录账号已禁用");
        } else if (e instanceof LockedAccountException) {
            return R.error().code(R.AUTH_FAIL_CODE).msg("账号已被锁定,请联系管理员");
        } else if (e instanceof ExpiredCredentialsException) {
            return R.error().code(R.UNAUTHORIZED).msg("登录已过期，请重新登录");
        } else if (e instanceof UnauthorizedException) {
            log.error("异常信息: ", e);
            return R.error().code(R.UNAUTHORIZED).msg("您的权限不足，无法访问");
        } else {
            log.error("异常信息: ", e);
            return R.error().code(R.ERROR_CODE).msg("(╥╯^╰╥) 系统出错了，请稍后处理！");
        }
    }

    @ExceptionHandler(Exception.class)
    public R exception(Exception e, HttpServletRequest request, HttpServletResponse response) {
        log.info("请求方式：{}, 请求路径：{}, 异常信息：{}", request.getMethod(), request.getServletPath(), e.getMessage());
        log.error("异常信息: ", e);
        if (e instanceof MethodArgumentNotValidException) { //字段校验异常
            return R.error().msg(e.getMessage());
        } else if (e instanceof ServiceException) { // 自定义异常
            ServiceException ex = (ServiceException) e;
            return R.error().code(ex.getCode()).msg(ex.getMessage());
        } else if (e instanceof RepeatSubmitException) {//重复提交异常
            return R.error().code(R.FAIL_CODE).msg(e.getMessage());
        } else if (e instanceof UtilsException) { //工具类处理异常
            return R.error().code(R.ERROR_CODE).msg(e.getMessage());
        } else {
            return R.error().code(R.ERROR_CODE).msg("(╥╯^╰╥) 系统出错了，请稍后处理！");
        }
    }

    @ExceptionHandler(HttpMessageConversionException.class)
    public R httpException(Exception e, HttpServletRequest request, HttpServletResponse response) {
        log.info("请求方式：{}, 请求路径：{}, 异常信息：{}", request.getMethod(), request.getServletPath(), e.getMessage());
        log.error("异常信息: ", e);
        return R.error().code(R.ERROR_CODE).msg("消息转换异常");
    }


}
