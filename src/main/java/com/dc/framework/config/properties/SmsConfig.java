package com.dc.framework.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("sms")
public class SmsConfig {

    /**
     * 开启验证码校验
     */
    private boolean captchaCache;

    /**
     * session会话过期时间（默认30分钟）
     */
    private int timeout = 1;

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public boolean isCaptchaCache() {
        return captchaCache;
    }

    public void setCaptchaCache(boolean captchaCache) {
        this.captchaCache = captchaCache;
    }
}
