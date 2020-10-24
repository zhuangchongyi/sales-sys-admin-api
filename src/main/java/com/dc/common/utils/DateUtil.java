package com.dc.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author zhuangcy
 * @Description 日期格式处理工具类
 * @Date 2020/9/14 16:36
 */
public class DateUtil {
    public static final String PATH_YYYY_MM = "/yyyy/MM/";
    public static final String YYYYMMDD = "yyyyMMdd";
    public static final String FORMAT_YYYYMMDDHHMMSS = "yyyy-MM-dd hh:mm:ss";
    public static final String FORMAT_YYYYMMDD = "yyyy-MM-dd";


    public static String getYYYYMMDDString(Date date) {
        return parseDateToString(YYYYMMDD, date);
    }

    public static String getYYYYMMPathString(Date date) {
        return parseDateToString(PATH_YYYY_MM, date);
    }

    public static final String parseDateToString(final String format, final Date date) {
        return new SimpleDateFormat(format).format(date);
    }

    public static final Date parseStringToDate(final String format, final String ts) {
        try {
            return new SimpleDateFormat(format).parse(ts);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据yyyyMMdd格式比较时间大小
     *
     * @param source
     * @param target
     * @return source>=target
     */
    public static boolean compareToYYYYMMDD(Date source, Date target) {
        SimpleDateFormat sdf = new SimpleDateFormat(YYYYMMDD);
        String sd = sdf.format(source);
        String td = sdf.format(target);
        if (Integer.valueOf(sd) >= Integer.valueOf(td)) {
            return true;
        }
        return false;
    }
}
