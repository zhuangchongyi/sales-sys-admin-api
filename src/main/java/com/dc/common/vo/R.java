package com.dc.common.vo;

import java.io.Serializable;

/**
 * @Author zhuangchongyi
 * @Description 请求响应返回实体对象
 * @Date 2020/9/3 10:05
 */
public class R implements Serializable {
    private static final long serialVersionUID = 3454479085696313081L;
    private int code;
    private boolean success;
    private String message;
    private Object data;
    /**
     * 成功code
     */
    public static final int SUCCESS_CODE = 200;
    /**
     * 错误code
     */
    public static final int ERROR_CODE = 500;
    /**
     * 失败code
     */
    public static final int FAIL_CODE = 400;
    /**
     * 未认证code
     */
    public static final int AUTH_FAIL_CODE = 401;
    /**
     * 未授权code
     */
    public static final int UNAUTHORIZED = 403;

    private R(int code, boolean success, String message) {
        this.code = code;
        this.success = success;
        this.message = message;
    }

    public static R success() {
        return new R(SUCCESS_CODE, true, "操作成功");
    }

    public static R error() {
        return new R(ERROR_CODE, false, "操作失败");
    }

    public R code(int code) {
        this.code = code;
        return this;
    }

    public R msg(String message) {
        this.message = message;
        return this;
    }

    public R data(Object data) {
        this.data = data;
        return this;
    }

    public int getCode() {
        return code;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }
}
