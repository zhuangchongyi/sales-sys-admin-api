package com.dc.common.vo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@Component
@PropertySource("ftp.properties")
@ConfigurationProperties(prefix = "ftp")
public class FtpEntity implements Serializable {
    private static final long serialVersionUID = 5485891567517773874L;
    /**
     * 主机地址
     */
    private String host = "127.0.0.1";

    /**
     * 端口
     */
    private Integer port = 21;
    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 存放路径
     */
    private String path;

    /**
     * url访问前缀
     */
    private String urlPrefix;


}
