package com.sinosoft.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateUtils {
    /**
     * 得到当前系统日期
     *
     * @return 当前日期的格式字符串,日期格式为"yyyy-MM-dd"
     */
    public static String getCurrentDate() {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        Date today = new Date();
        String tString = df.format(today);
        return tString;
    }

    /**
     * 得到当前系统时间
     *
     * @return 当前时间的格式字符串，时间格式为"HH:mm:ss"
     */
    public static String getCurrentTime() {
        String pattern = "HH:mm:ss";
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        Date today = new Date();
        String tString = df.format(today);
        return tString;
    }

    /**
     * 得到当前系统时间
     *
     * @return 当前时间的格式字符串，时间格式为"HH:mm:ss"
     */
    public static String getCurrentDateTime() {
        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        Date today = new Date();
        String tString = df.format(today);
        return tString;
    }

}
