package com.dc.framework.realm;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @author zhuangcy
 * @date 2020/11/16 10:23
 * @description 自定义用户登录Token
 */
public class CustomUserToken extends UsernamePasswordToken {
    /**
     * 登录方式类型
     */
    private String loginType;

    public CustomUserToken() {
        super();
    }

    public CustomUserToken(String username, String password) {
        super(username, password);
        this.loginType = LoginType.LOGIN_TYPE_SYSTEM;
    }

    public CustomUserToken(String username, String password, String loginType) {
        super(username, password);
        this.loginType = loginType;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }
}
