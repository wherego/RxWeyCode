
package cn.wey.basicframe.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 日期工具类
 * Created by wey on 2016/2/16.
 */
public class DateUtils {

    public final static SimpleDateFormat dateFormater = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");
    public final static SimpleDateFormat dateFormater2 = new SimpleDateFormat(
            "yyyy-MM-dd");

    /**
     * 功能描述：格式化输出日期
     *
     * @param date   Date 日期
     * @param format String 格式
     * @return 返回字符型日期
     */
    public static String format(Date date, String format) {
        String result = "";
        try {
            if (date != null) {
                SimpleDateFormat dateFormat = new SimpleDateFormat(format);
                result = dateFormat.format(date);
            }
        } catch (Exception e) {
        }
        return result;
    }

    /**
     * 功能描述：返回年份
     *
     * @param date Date 日期
     * @return 返回年份
     */
    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 功能描述：返回月份
     *
     * @param date Date 日期
     * @return 返回月份
     */
    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 功能描述：返回日份
     *
     * @param date Date 日期
     * @return 返回日份
     */
    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 功能描述：返回小时
     *
     * @param date 日期
     * @return 返回小时
     */
    public static int getHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 功能描述：返回分钟
     *
     * @param date 日期
     * @return 返回分钟
     */
    public static int getMinute(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }

    /**
     * 返回秒钟
     *
     * @param date Date 日期
     * @return 返回秒钟
     */
    public static int getSecond(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.SECOND);
    }

    /**
     * 功能描述：返回毫秒
     *
     * @param date 日期
     * @return 返回毫秒
     */
    public static long getMillis(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getTimeInMillis();
    }

    /**
     * 日期转换成字符串
     *
     * @param date
     * @return string
     */
    public static String DateToString(Date date) {
        return dateFormater.format(date);
    }

    /**
     * yyyy-MM-dd HH:mm:ss
     * 返回间隔秒数
     *
     * @param oldDate
     * @param newDate
     * @return
     */
    public static long getIntervalTime(String oldDate, String newDate) {
        try {
            if (!StringUtils.isEmpty(oldDate) && !StringUtils.isEmpty(newDate)) {
                Date date1 = dateFormater.parse(oldDate);
                Date date2 = dateFormater.parse(newDate);
                long date = (date2.getTime() - date1.getTime()) / 1000;
                return date;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 字符串转换成日期.
     *
     * @param dateString 日期字符
     * @param pattern    格式化.
     * @return
     */
    public static Date parseToDate(String dateString, String pattern) {
        if (pattern == null || "".equals(pattern)) {
            pattern = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat formatter = new SimpleDateFormat(pattern, Locale.getDefault());
        try {
            return formatter.parse(dateString);
        } catch (ParseException e) {
        }
        return new Date();
    }

    /**
     * 日期字符串转换为日期
     *
     * @param date    日期字符串
     * @param pattern 格式
     * @return 日期
     */
    public static Date formatStringByFormat(String date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 功能描述：以指定的格式来格式化日期
     *
     * @param date   Date 日期
     * @param format String 格式
     * @return String 日期字符串
     */
    public static String formatDateByFormat(Date date, String format) {
        String result = "";
        if (date != null) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                result = sdf.format(date);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    public static String getFriendlyTime(String d) {
        Date date = formatStringByFormat(d, "yyyy-MM-dd HH:mm:ss");
        Calendar now = Calendar.getInstance();
        now.setTime(new Date());
        int nowYear = now.get(Calendar.YEAR);
        int nowMonth = now.get(Calendar.MONTH);
        int nowWeek = now.get(Calendar.WEEK_OF_MONTH);
        int nowDay = now.get(Calendar.DAY_OF_WEEK);
        int nowHour = now.get(Calendar.HOUR_OF_DAY);
        int nowMinute = now.get(Calendar.MINUTE);

        Calendar ca = Calendar.getInstance();
        if (date != null)
            ca.setTime(date);
        else
            ca.setTime(new Date());
        int year = ca.get(Calendar.YEAR);
        int month = ca.get(Calendar.MONTH);
        int week = ca.get(Calendar.WEEK_OF_MONTH);
        int day = ca.get(Calendar.DAY_OF_WEEK);
        int hour = ca.get(Calendar.HOUR_OF_DAY);
        int minute = ca.get(Calendar.MINUTE);
        if (year != nowYear) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            //不同年份
            return sdf.format(date);
        } else {
            if (month != nowMonth) {
                //不同月份
                SimpleDateFormat sdf = new SimpleDateFormat("M月dd日");
                return sdf.format(date);
            } else {
                if (week != nowWeek) {
                    //不同周
                    SimpleDateFormat sdf = new SimpleDateFormat("M月dd日");
                    return sdf.format(date);
                } else if (day != nowDay) {
                    if (day + 1 == nowDay) {
                        return "昨天" + formatDateByFormat(date, "HH:mm");
                    }
                    if (day + 2 == nowDay) {
                        return "前天" + formatDateByFormat(date, "HH:mm");
                    }
                    //不同天
                    SimpleDateFormat sdf = new SimpleDateFormat("M月dd日");
                    return sdf.format(date);
                } else {
                    //同一天
                    int hourGap = nowHour - hour;
                    if (hourGap == 0)//1小时内
                    {
                        if (nowMinute - minute < 1) {
                            return "刚刚";
                        } else {
                            return (nowMinute - minute) + "分钟前";
                        }
                    } else if (hourGap >= 1 && hourGap <= 12) {
                        return hourGap + "小时前";
                    } else {
                        SimpleDateFormat sdf = new SimpleDateFormat("今天 HH:mm");
                        return sdf.format(date);
                    }
                }
            }
        }
    }

    public static String getFriendlyTime(Date d) {
        String date = format(d, "yyyy-MM-dd HH:mm:ss");
        return getFriendlyTime(date);
    }
}