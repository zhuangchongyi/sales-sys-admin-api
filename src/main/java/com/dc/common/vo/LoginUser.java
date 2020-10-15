package com.dc.common.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginUser implements Serializable {
    private static final long serialVersionUID = 5485891567517773874L;
    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 验证码
     */
    private String code;

    /**
     * 唯一标识
     */
    private String uuid = "";

    /**
     * 记住我
     */
    private boolean rememberMe;
}
