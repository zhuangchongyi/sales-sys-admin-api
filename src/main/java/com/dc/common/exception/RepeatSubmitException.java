package com.dc.common.exception;

/**
 * @Author zhuangchongyi
 * @Description 自定义业务异常类
 * @Date 2020/8/28 17:52
 */
public class RepeatSubmitException extends RuntimeException {
    private static final long serialVersionUID = -334911415780992522L;

    public RepeatSubmitException(String message) {
        super(message);
    }

    public RepeatSubmitException() {
        super("重复提交，请稍后再试");
    }

}
