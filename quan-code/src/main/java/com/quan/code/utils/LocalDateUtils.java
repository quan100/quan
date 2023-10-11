package com.quan.code.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 *
 * @author: wangquan
 * @date: 18-11-25 16:14
 * @description:
 */
public class LocalDateUtils {

    /**
     * 标准的日期字符串格式
     */
    public static final String STANDARD_DATE_FORMAT = "yyyy-MM-dd";

    /**
     * 标准的日期分钟字符串格式
     */
    public static final String STANDARD_DATEMINUTE_FORMAT = "yyyy-MM-dd HH:mm";

    /**
     * 标准的日期时间字符串格式
     */
    public static final String STANDARD_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String STANDARD_DATETIME_T_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

    public static final String STANDARD_DATETIME_NONE_FORMAT = "yyyyMMddHHmmss";


    /**
     * 获取当前时间
     * 默认时区
     *
     * @return
     */
    public static LocalDateTime getLocalDateTime() {
        return LocalDateTime.now();
    }

    /**
     * 获取当前时间
     * 指定时区
     *
     * @return
     */
    public static LocalDateTime getLocalDateTime(ZoneId zone) {
        return LocalDateTime.now(zone);
    }

    /**
     * 日期增加天数
     *
     * @param localDateTime 当前待增加的时间
     * @param days          增加天数
     * @return
     */
    public static LocalDateTime plusDate(LocalDateTime localDateTime, int days) {
        Period period = Period.ofDays(days);
        LocalDateTime plusDateTime = localDateTime.plus(period);
        return plusDateTime;
    }

    /**
     * date 转换为 LocalDateTime
     *
     * @param date
     * @return
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * {@link LocalDateTime} 转换为 {@link Date}
     *
     * @param localDateTime
     * @return
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static Date now() {
        return Calendar.getInstance().getTime();
    }

    /**
     * 根据时间格式返回对应的String类型的时间
     *
     * @param format
     * @return
     */
    public static String getCurDateTime(String format) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        String dataTime = now.format(dateTimeFormatter);
        return dataTime;
    }

    /**
     * 日期转字符串
     *
     * @return String
     */
    public static String parseDateToString(Date thedate, String format) {
        if (thedate != null) {
            Instant instant = thedate.toInstant();
            ZoneId zone = ZoneId.systemDefault();
            LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
            return localDateTime.format(dateTimeFormatter);
        }
        return null;
    }

    /**
     * 日期转字符串
     * 默认时间格式：yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String parseDateToString(Date date) {
        return parseDateToString(date, STANDARD_DATETIME_FORMAT);
    }

    /**
     * 字符串转日期
     * 默认时间格式：yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static Date parseStringToDate(String date) {
        return parseStringToDate(date, STANDARD_DATETIME_FORMAT);
    }


    /**
     * 字符串转日期
     *
     * @param date   时间
     * @param format 时间格式
     * @return
     */
    public static Date parseStringToDate(String date, String format) {
        DateFormat df = new SimpleDateFormat(format);
        Date parseDate = null;
        try {
            parseDate = df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parseDate;
    }

    /**
     * 比较两个时间格式大小
     * 默认时间格式：yyyy-MM-dd HH:mm:ss
     * ex： 2018-11-22 15:00:12 < 2018-11-23 15:00:12 return true
     *
     * @param beginTimeStr 开始时间，日期靠前的时间
     * @param endTimeStr   结束时间，日期靠后的时间
     * @return beginTime < endTime ? true : false
     */
    public static boolean isBefore(String beginTimeStr, String endTimeStr) {
        return isBefore(beginTimeStr, endTimeStr, STANDARD_DATETIME_FORMAT);
    }

    /**
     * 比较两个时间格式大小
     * ex： 2018-11-22 15:00:12 < 2018-11-23 15:00:12 return true
     *
     * @param beginTimeStr 开始时间，日期靠前的时间
     * @param endTimeStr   结束时间，日期靠后的时间
     * @param format       日期格式
     * @return beginTime < endTime ? true : false
     */
    public static boolean isBefore(String beginTimeStr, String endTimeStr, String format) {
        Date beginTime = parseStringToDate(beginTimeStr, format);
        Date endTime = parseStringToDate(endTimeStr, format);
        return beginTime.before(endTime);
    }

    /**
     * 比较两个时间格式大小
     * ex： 2018-11-22 15:00:12 < 2018-11-23 15:00:12 return true
     *
     * @param beginTime 开始时间，日期靠前的时间
     * @param endTime   结束时间，日期靠后的时间
     * @return beginTime < endTime ? true : false
     */
    public static boolean isBefore(Date beginTime, Date endTime) {
        return beginTime.before(endTime);
    }

    /**
     * 计算两个时间相差的分钟数
     *
     * @param beginTime 开始时间，日期靠前的时间
     * @param endTime   结束时间，日期靠后的时间
     * @return
     */
    public static Long dateToMinutes(LocalDateTime beginTime, LocalDateTime endTime) {
        Duration duration = Duration.between(beginTime, endTime);
        // 获取相差的分钟数
        Long minutes = duration.toMinutes();
        return minutes;
    }

}
