package com.dc.common.utils;

import java.math.BigDecimal;

/**
 * @Author zhuangcy
 * @Description 精确的浮点数运算工具类
 * @Date 2020/9/14 16:33
 */
public class BigDecimalUtil {

    /**
     * 默认除法运算精度
     */
    private static final int DEF_DIV_SCALE = 10;

    public static final BigDecimal ZERO = new BigDecimal(0);


    /**
     * String转BigDecimal，四舍五入保留${scale}位
     *
     * @param decimal
     * @param scale
     * @return
     */
    public static BigDecimal getBigDecimal(String decimal, int scale) {
        return getBigDecimal(decimal).setScale(scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * String转BigDecimal
     *
     * @param decimal
     * @return
     */
    private static BigDecimal getBigDecimal(String decimal) {
        if (decimal == null)
            return ZERO;
        return new BigDecimal(decimal);
    }

    /**
     * 加法
     *
     * @param num1
     * @param num2
     * @return
     */
    public static BigDecimal add(BigDecimal num1, BigDecimal num2) {
        return num1.add(num2);
    }

    public static BigDecimal add(int num1, int num2) {
        return BigDecimal.valueOf(num1 + num2);
    }

    /**
     * 减法
     *
     * @param num1
     * @param num2
     * @return
     */
    public static BigDecimal sub(BigDecimal num1, BigDecimal num2) {
        return num1.subtract(num2);
    }

    /**
     * 乘法
     *
     * @param num1
     * @param num2
     * @return
     */
    public static BigDecimal mul(BigDecimal num1, BigDecimal num2) {
        return num1.multiply(num2);
    }

    public static BigDecimal mul(BigDecimal num1, int num2) {
        return num1.multiply(BigDecimal.valueOf(num2));
    }

    /**
     * 除法
     *
     * @param num1 被除数
     * @param num2 除数
     * @return
     */
    public static BigDecimal div(BigDecimal num1, BigDecimal num2) {
        return num1.divide(num2, DEF_DIV_SCALE);
    }

    /**
     * 除法
     *
     * @param num1  被除数
     * @param num2  除数
     * @param scale 保留几位小数
     * @return
     */
    public static BigDecimal div(BigDecimal num1, BigDecimal num2, int scale) {
        return num1.divide(num2, scale);
    }

    public static BigDecimal div(BigDecimal num1, int num2, int scale) {
        return num1.divide(BigDecimal.valueOf(num2), scale);
    }

    /**
     * 格式化保留多少位
     *
     * @param decimal
     * @param scale   保留几位小数
     * @return
     */
    public BigDecimal formatter(BigDecimal decimal, int scale) {
        return decimal.setScale(scale);
    }


    /**
     * 比较大小
     *
     * @param num1
     * @param num2
     * @return -1=num1小于num2, 0=num1等于num2, 1=num1大于num2
     */
    public static int compareTo(BigDecimal num1, BigDecimal num2) {
        return num1.compareTo(num2);
    }


}
