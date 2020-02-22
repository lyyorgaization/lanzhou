package com.lucien.dap.framework.core.utils;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author qinbin.wan
 * @version v1.0.0
 * @date 2017/4/23
 */
public class DateUtil {
    /**
     * 定义常量
     **/
    public static final String[] pattern = new String[]{
            "HH:mm",
            "HH:mm:ss",
            "yyyyMM",
            "yyyy-MM",
            "yyyy/MM",
            "yyyyMMdd",
            "yyyy-MM-dd",
            "yyyy/MM/dd",
            "yyyyMMddHHmmss",
            "yyyy/MM/dd HH:mm:ss",
            "yyyy-MM-dd HH:mm:ss"
    };
    public static final String DATE_FULL_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_SHORT_PATTERN = "yyyy-MM-dd";
    public static final String TIME_PATTERN_SESSION = "yyyyMMddHHmmss";

    public static final String TIMETO = " 23:59:59";

    public static final String TIME_PATTEN = "HH:mm:ss";

    public static final int EQUAL = 2;
    public static final int AFTER = 3;
    public static final int BEFORE = 1;

    /**
     * 使用预设格式提取字符串日期
     *
     * @param strDate 日期字符串
     * @return
     */
    public static Date parse(String strDate) {
        return parse(strDate, DATE_FULL_PATTERN);
    }

    /**
     * 使用用户格式提取字符串日期
     *
     * @param strDate 日期字符串
     * @param pattern 日期格式
     * @return
     */
    public static Date parse(String strDate, String pattern) {
        if (strDate == null || strDate.length() == 0 || pattern == null || pattern.length() == 0) {
            return null;
        }

        SimpleDateFormat df = new SimpleDateFormat(pattern);
        try {
            return df.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 使用用户格式提取字符串日期
     *
     * @param strDate 日期字符串
     * @return
     */
    public static Date parseString(String strDate) {
        if (strDate == null || strDate.length() == 0) {
            return null;
        }
        try {
            return DateUtils.parseDate(strDate, pattern);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getTime(Date date) {
        return toString(date, TIME_PATTEN);
    }


    /**
     * 两个时间比较
     *
     * @param date
     * @return
     */
    public static int compareDateWithNow(Date date) {
        Date date2 = new Date();
        int result = date.compareTo(date2);
        return result;
    }

    /**
     * 两个时间比较(时间戳比较)
     *
     * @param timestamp
     * @return
     */
    public static int compareDateWithNow(long timestamp) {
        long date2 = dateToUnixTimestamp();
        if (timestamp > date2) {
            return 1;
        } else if (timestamp < date2) {
            return -1;
        } else {
            return 0;
        }
    }


    /**
     * 获取系统当前时间
     *
     * @return
     */
    public static String getCurrentTime() {
        SimpleDateFormat df = new SimpleDateFormat(DATE_FULL_PATTERN);
        return df.format(new Date());
    }

    /**
     * 获取系统当前时间
     *
     * @return
     */
    public static String getCurrentTime(String type) {
        SimpleDateFormat df = new SimpleDateFormat(type);
        return df.format(new Date());
    }

    public static Date getMunthFirstDay() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天

        return c.getTime();
    }


    /**
     * 将指定的日期转换成Unix时间戳
     *
     * @param date 需要转换的日期 yyyy-MM-dd HH:mm:ss
     * @return long 时间戳
     */
    public static long dateToUnixTimestamp(String date) {
        long timestamp = 0;
        try {
            timestamp = new SimpleDateFormat(DATE_FULL_PATTERN).parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timestamp;
    }

    /**
     * 将指定的日期转换成Unix时间戳
     *
     * @param date 需要转换的日期 yyyy-MM-dd
     * @return long 时间戳
     */
    public static long dateToUnixTimestamp(String date, String dateFormat) {
        long timestamp = 0;
        try {
            timestamp = new SimpleDateFormat(dateFormat).parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timestamp;
    }

    /**
     * 将当前日期转换成Unix时间戳
     *
     * @return long 时间戳
     */
    public static long dateToUnixTimestamp() {
        long timestamp = new Date().getTime();
        return timestamp;
    }


    /**
     * 将Unix时间戳转换成日期
     *
     * @param timestamp 时间戳
     * @return String 日期字符串
     */
    public static String unixTimestampToDate(long timestamp) {
        SimpleDateFormat sd = new SimpleDateFormat(DATE_FULL_PATTERN);
        sd.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return sd.format(new Date(timestamp));
    }

    public static final Date zerolizedTime(Date fullDate) {
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(fullDate);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    /**
     * 计算连个日期间隔的天数
     *
     * @param firstDate 小的
     * @param lastDate  大的
     * @return
     */
    public static int getIntervalDays(Date firstDate, Date lastDate) {
        if (null == firstDate || null == lastDate) {
            return -1;
        }

        long intervalMilli = lastDate.getTime() - firstDate.getTime();
        return (int) (intervalMilli / (24 * 60 * 60 * 1000));
    }

    /**
     * 计算连个日期间隔的小时数
     *
     * @param firstDate 小的
     * @param lastDate  大的
     * @return
     */
    public static int getIntervalHours(Date firstDate, Date lastDate) {
        if (null == firstDate || null == lastDate) {
            return -1;
        }

        long intervalMilli = lastDate.getTime() - firstDate.getTime();
        return (int) (intervalMilli / (60 * 60 * 1000));
    }

    /**
     * 计算连个日期间隔的分数
     *
     * @param firstDate 小的
     * @param lastDate  大的
     * @return
     */
    public static int getIntervalMinutes(Date firstDate, Date lastDate) {
        if (null == firstDate || null == lastDate) {
            return -1;
        }

        long intervalMilli = lastDate.getTime() - firstDate.getTime();
        return (int) (intervalMilli / (60 * 1000));
    }

    /**
     * 计算连个日期间隔的秒数
     *
     * @param firstDate 小的
     * @param lastDate  大的
     * @return
     */
    public static int getIntervalSeconds(Date firstDate, Date lastDate) {
        if (null == firstDate || null == lastDate) {
            return -1;
        }

        long intervalMilli = lastDate.getTime() - firstDate.getTime();
        return (int) (intervalMilli / 1000);
    }


    /**
     * 比较两个日期
     *
     * @param src
     * @param desc
     * @return
     */
    public static int compareDate(Date src, Date desc) {
        if (src == null && desc == null) {
            return EQUAL;
        } else if (src == null) {
            return BEFORE;
        } else if (desc == null) {
            return AFTER;
        } else if (src.before(desc)) {
            return BEFORE;
        } else if (src.after(desc)) {
            return AFTER;
        } else {
            return EQUAL;
        }
    }

    /**
     * 比较日期是否介于两个之间
     *
     * @param date
     * @param begin
     * @param end
     * @return
     */
    public static boolean isDateBetween(Date date, Date begin, Date end) {
        int c1 = compareDate(begin, date);
        int c2 = compareDate(date, end);

        return (c1 == BEFORE && c2 == BEFORE) || c1 == EQUAL || c2 == EQUAL;
    }

    /**
     * 增加天数
     * 减少传负值
     *
     * @param date
     * @param day
     * @return
     */
    public static Date addDays(Date date, int day) {
        if (null == date) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + day);

        return calendar.getTime();
    }

    /**
     * 加分
     *
     * @param date
     * @param min
     * @return
     */
    public static Date addMinites(Date date, int min) {
        if (null == date) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + min);

        return calendar.getTime();
    }

    public static Date addSeconds(Date date, int second) {
        if (null == date) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND) + second);

        return calendar.getTime();
    }

    public static int getDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK) - 1;
    }

    public static String toShortString(Date date) {
        return toString(date, DATE_SHORT_PATTERN);
    }

    public static String toLongString(Date date) {
        return toString(date, DATE_FULL_PATTERN);
    }

    /**
     * 将日期转换成string
     *
     * @param date
     * @param sFmt
     * @return
     */
    public static String toString(Date date, String sFmt) {
        if (null == date || StringUtils.isEmpty(sFmt)) {
            return null;
        }

        SimpleDateFormat simpleDateFormat = null;
        String sRet = null;
        try {
            simpleDateFormat = new SimpleDateFormat(sFmt);
            sRet = simpleDateFormat.format(date).toString();
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        } finally {
            simpleDateFormat = null;
        }
        return sRet;
    }

    /**
     * 把后面Date里面的时间，copy到前面的那个date上去
     *
     * @param date
     * @param time
     * @return
     */
    public static Date setHHmmss(Date date, Date time) {

        Calendar calendarTime = Calendar.getInstance();
        calendarTime.setTime(time);

        Calendar calenarDate = Calendar.getInstance();
        calenarDate.setTime(date);

        calendarTime.set(calenarDate.get(Calendar.YEAR), calenarDate.get(Calendar.MONTH), calenarDate.get(Calendar.DATE));

        return calendarTime.getTime();
    }

    public static LocalDate convert(Date date) {

        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDate();
    }

    public static Date convert(LocalDate localDate) {

        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        Date date = Date.from(instant);
        return date;
    }

    /**
     * 返回小时
     *
     * @param date 日期
     * @return 返回小时
     */
    public static int getHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

}
