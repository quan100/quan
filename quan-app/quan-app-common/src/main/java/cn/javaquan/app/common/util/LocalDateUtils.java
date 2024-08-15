package cn.javaquan.app.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类.
 *
 * @author wangquan
 * @since 1.0.0
 */
public final class LocalDateUtils {

    /**
     * 私有构造方法.
     */
    private LocalDateUtils() {
    }

    /**
     * 标准的日期字符串格式.
     */
    public static final String STANDARD_DATE_FORMAT = "yyyy-MM-dd";

    /**
     * 标准的日期时间字符串格式.
     */
    public static final String STANDARD_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 获取当前时间.
     * <p>
     * 默认时区
     * @return 使用系统时钟和默认时区的当前日期时间，不为空
     */
    public static LocalDateTime getLocalDateTime() {
        return LocalDateTime.now();
    }

    /**
     * 获取当前时间.
     * <p>
     * 指定时区
     * @param zone 要使用的时区ID
     * @return 使用系统时钟的当前日期时间，不为空
     */
    public static LocalDateTime getLocalDateTime(ZoneId zone) {
        return LocalDateTime.now(zone);
    }

    /**
     * 日期增加天数.
     * @param localDateTime 当前待增加的时间
     * @param days 增加天数
     * @return 一个基于该日期时间的LocalDateTime，添加天数，不为空
     */
    public static LocalDateTime plusDate(LocalDateTime localDateTime, int days) {
        Period period = Period.ofDays(days);
        return localDateTime.plus(period);
    }

    /**
     * date 转换为 LocalDateTime.
     * @param date 待转换的日期
     * @return localDateTime
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * 将{@link LocalDateTime} 转换为 {@link Date}.
     * @param localDateTime 当前待转换的日期时间
     * @return date
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取当前时间.
     * @return 当前时间
     */
    public static Date now() {
        return Calendar.getInstance().getTime();
    }

    /**
     * 根据时间格式返回对应的String类型的时间.
     * @return 当前时间
     */
    public static String getCurDateTime() {
        return getCurDateTime(STANDARD_DATETIME_FORMAT);
    }

    /**
     * 根据时间格式返回对应的String类型的时间.
     * @param format 自定义的时间格式
     * @return 格式化的日期-时间字符串
     */
    public static String getCurDateTime(String format) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        String dataTime = now.format(dateTimeFormatter);
        return dataTime;
    }

    /**
     * 日期转字符串.
     * @param date 待转换的日期
     * @param format 自定义的时间格式
     * @return 格式化的日期-时间字符串
     */
    public static String parseDateToString(Date date, String format) {
        if (date == null) {
            return null;
        }
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        return localDateTime.format(dateTimeFormatter);
    }

    /**
     * 日期转字符串 默认时间格式：yyyy-MM-dd HH:mm:ss.
     * @param date 待转换的日期
     * @return 格式化的日期-时间字符串
     */
    public static String parseDateToString(Date date) {
        return parseDateToString(date, STANDARD_DATETIME_FORMAT);
    }

    /**
     * 字符串转日期 默认时间格式：yyyy-MM-dd HH:mm:ss.
     * @param date 待转换的字符串格式日期
     * @return 格式化的日期
     */
    public static Date parseStringToDate(String date) {
        return parseStringToDate(date, STANDARD_DATETIME_FORMAT);
    }

    /**
     * 字符串转日期.
     * @param date 待转换的字符串格式日期
     * @param format 自定义的时间格式
     * @return 格式化的日期
     */
    public static Date parseStringToDate(String date, String format) {
        DateFormat df = new SimpleDateFormat(format);
        Date parseDate = null;
        try {
            parseDate = df.parse(date);
        }
        catch (ParseException ex) {
            ex.printStackTrace();
        }
        return parseDate;
    }

    /**
     * 比较两个时间格式大小.
     * <p>
     * 默认时间格式：yyyy-MM-dd HH:mm:ss
     * {@code ex： 2018-11-22 15:00:12 < 2018-11-23 15:00:12 return true}
     * @param beginTimeStr 开始时间，日期靠前的时间
     * @param endTimeStr 结束时间，日期靠后的时间
     * @return {@code beginTime < endTime ? true : false}
     */
    public static boolean isBefore(String beginTimeStr, String endTimeStr) {
        return isBefore(beginTimeStr, endTimeStr, STANDARD_DATETIME_FORMAT);
    }

    /**
     * 比较两个时间格式大小.
     * <p>
     * {@code ex： 2018-11-22 15:00:12 < 2018-11-23 15:00:12 return true}
     * @param beginTimeStr 开始时间，日期靠前的时间
     * @param endTimeStr 结束时间，日期靠后的时间
     * @param format 日期格式
     * @return {@code beginTime < endTime ? true : false}
     */
    public static boolean isBefore(String beginTimeStr, String endTimeStr, String format) {
        Date beginTime = parseStringToDate(beginTimeStr, format);
        Date endTime = parseStringToDate(endTimeStr, format);
        return beginTime.before(endTime);
    }

    /**
     * 比较两个时间格式大小.
     * <p>
     * {@code ex： 2018-11-22 15:00:12 < 2018-11-23 15:00:12 return true}
     * @param beginTime 开始时间，日期靠前的时间
     * @param endTime 结束时间，日期靠后的时间
     * @return {@code beginTime < endTime ? true : false}
     */
    public static boolean isBefore(Date beginTime, Date endTime) {
        return beginTime.before(endTime);
    }

    /**
     * 计算两个时间相差的分钟数.
     * @param beginTime 开始时间，日期靠前的时间
     * @param endTime 结束时间，日期靠后的时间
     * @return 两个时间相差的分钟数
     */
    public static Long dateToMinutes(LocalDateTime beginTime, LocalDateTime endTime) {
        Duration duration = Duration.between(beginTime, endTime);
        // 获取相差的分钟数
        return duration.toMinutes();
    }

}
