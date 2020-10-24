package com.dc.common.utils;

import java.util.Date;
import java.util.UUID;

/**
 * @Author zhuangcy
 * @Description 编码生成工具类
 * @Date 2020/9/14 16:41
 */
public class CodeUtil {
    private static SnowflakeUtil snowflake = SnowflakeUtil.getInstance();

    public static String randomUUIDNotRail() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String getCode(String type) {
        return new StringBuilder()
                .append(type)
                .append(snowflake.nextId()).toString();
    }

    public static String getCode(Date date, String type) {
        return new StringBuilder()
                .append(type)
                .append(DateUtil.getYYYYMMDDString(date))
                .append(snowflake.nextId()).toString();
    }

    /**
     * @return 生成6位数的随机数
     */
    public static String randomCode() {
        return randomCode(6);
    }

    /**
     * 生成随机数
     *
     * @param digit 位数
     * @return
     */
    public static String randomCode(int digit) {
        StringBuilder sb = new StringBuilder("1");
        int len = digit - 1;
        for (int i = 0; i < len; i++) {
            sb.append("0");
        }
        return String.valueOf((int) ((Math.random() * 9 + 1) * Integer.valueOf(sb.toString())));
    }
}
