package com.dc.common.exception;

/**
 * @Author zhuangchongyi
 * @Description 自定义业务异常类
 * @Date 2020/8/28 17:52
 */
public class UtilException extends RuntimeException {
    private static final long serialVersionUID = -334911415780992522L;

    public UtilException() {
    }

    public UtilException(String message) {
        super(message);
    }

    public UtilException(String message, Throwable cause) {
        super(message, cause);
    }

    public UtilException(Throwable cause) {
        super(cause);
    }

    public UtilException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
