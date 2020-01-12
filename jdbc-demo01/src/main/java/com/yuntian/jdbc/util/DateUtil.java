package com.yuntian.jdbc.util;

import java.util.Date;

/**
 * @Auther: yuntian
 * @Date: 2020/1/11 0011 13:29
 * @Description:
 */
public class DateUtil {


    public static java.sql.Date  convert(Date date){
        return  new java.sql.Date(date.getTime());
    }

    public static Date  convert(java.sql.Date date){
        return  new Date(date.getTime());
    }

    /**
     * 时间戳转Unix时间戳
     * @param timestamp
     * @return
     */
    public static long toUnixTimeStamp(long timestamp){
        return timestamp/1000;
    }

    /**
     * Unix时间戳转时间戳
     * @param unixTimeStamp
     * @return
     */
    public static long toTimestamp(long unixTimeStamp){
        return unixTimeStamp*1000;
    }

}
