package com.dc.common.exception;

import com.dc.common.vo.R;

/**
 * @Author zhuangchongyi
 * @Description 自定义业务异常类
 * @Date 2020/8/28 17:52
 */
public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = -334911415780992522L;
    private int code = R.FAIL_CODE;

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException() {
        super("操作失败");
    }

    public ServiceException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
