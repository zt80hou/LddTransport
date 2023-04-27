package com.ldd.transport.util;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TimerUtils {

    /**
     * 将秒转换为00:00
     *
     * @param seconds
     * @return
     */
    public static String intToTime(int seconds) {
        if (seconds <= 0) {
            return "00:00:00";
        }
        String time = "";

        int hours = seconds / 3600;
        if (hours < 10) {
            time = "0" + hours + ":";
        } else {
            time = hours + ":";
        }

        int min = (seconds - hours * 3600) / 60;
        if (min < 10) {
            time = time + "0" + min + ":";
        } else {
            time = time + min + ":";
        }

        int sec = seconds - hours * 3600 - min * 60;
        if (sec < 10) {
            time = time + "0" + sec;
        } else {
            time = time + sec;
        }

        return time;
    }

    public static String douToTime(double data) {
        if (data < 0) {
            return "00:00:00";
        }

        int min = (int) (data / 60);
        int hour = min / 60;
        min = min % 60;
        int sec = (int) (data % 60);
        String secStr = "00";
        String minStr = "00";
        String hourStr = "00";
        if (hour < 10) {
            hourStr = "0" + String.valueOf(hour).substring(0, 1);
        } else {
            hourStr = String.valueOf(hour);
        }

        if (min < 10) {
            minStr = "0" + String.valueOf(min).substring(0, 1);
        } else {
            minStr = String.valueOf(min).substring(0, 2);
        }
        if (sec < 10) {
            secStr = "0" + String.valueOf(sec).substring(0, 1);
        } else {
            secStr = String.valueOf(sec).substring(0, 2);
        }
        String time = hourStr + ":" + minStr + ":" + secStr;
        return time;
    }

    /**
     * 时间转换为分钟数
     *
     * @param duration
     * @return
     */
    public static int timeToMinite(String duration) {
        try {
            String times[] = duration.split(":");
            int hours = Integer.parseInt(times[0]);
            int Minites = Integer.parseInt(times[1]);
            return hours * 60 + Minites + 1;
        } catch (Exception e) {
        }
        return 1;
    }


    /**
     * 获取当前时间前5秒
     *
     * @return time
     */
    public static String getBefore5s() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        long time = System.currentTimeMillis() - 5 * 1000;
        return format.format(time);
    }

    /**
     * 获取当前时间前3天
     *
     * @return time
     */
    public static String getBefore3Days() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        long time = System.currentTimeMillis() - 3 * 24 * 60 * 60 * 1000;
        return format.format(time);
    }


    /**
     * 获取当前时间
     *
     * @return time
     */
    public static String getFormatTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return format.format(date);
    }


    /**
     * 获取当天时间
     *
     * @return time
     */
    public static String getTodayTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();
        return format.format(date);
    }

    public static String getTimeStr(String time) {
        time = time + "000";
        Date date = new Date(Long.parseLong(time.trim()));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);
        return dateString;
    }

    public static double getTimeLeft(String startTime) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date dateStart = formatter.parse(startTime);
            Date dateNow = new Date();
            return (double) (dateStart.getTime() - dateNow.getTime()) / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
            return -1;

        }
    }

    public static long getTimeLeftLong(String startTime) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date dateStart = formatter.parse(startTime);
            Date dateNow = new Date();
            return dateStart.getTime() - dateNow.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return -1;

        }
    }

    /**
     * 返回毫秒
     *
     * @param startTime
     * @return
     */
    public static long getTodayTimeLeft(String startTime) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        SimpleDateFormat formatter2 = new SimpleDateFormat("HH:mm:ss");
        Date dateStart, nowDate;
        long timeLeft;
        String nowDateStr;
        Calendar time = Calendar.getInstance();
        nowDateStr = time.get(Calendar.HOUR_OF_DAY) + ":" + time.get(Calendar.MINUTE) + ":" + time.get(Calendar.SECOND);
        try {
            dateStart = formatter.parse(startTime);
            nowDate = formatter2.parse(nowDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return -1;
        }
        timeLeft = dateStart.getTime() - nowDate.getTime();
        return timeLeft;
    }

    public static boolean isDuringTime(String startTime, String endTime) {
        if (TextUtils.isEmpty(startTime)) {
            return false;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date dateStart = formatter.parse(startTime);
            Date dateEnd = formatter.parse(endTime);
            Date dateNow = new Date();
            return dateNow.after(dateStart) && dateNow.before(dateEnd);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isFuture(String time) {
        if (TextUtils.isEmpty(time)) {
            return false;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date dateStart = formatter.parse(time);
            Date dateNow = new Date();
            return dateNow.before(dateStart);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getHoursMinutes(String time) {
        if (TextUtils.isEmpty(time)) {
            return "";
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat formatter2 = new SimpleDateFormat("HH:mm");
        String formatTime = "";
        try {
            Date date = formatter.parse(time);
            formatTime = formatter2.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatTime;
    }

    public static String ms2Time(String ms) {
        Date date = new Date(Long.parseLong(ms));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String dateString = formatter.format(date);
        return dateString;
    }


    /**
     * 根据日期获得时分(可跨天)
     * 日期格式"yyyy-MM-dd HH:mm"
     *
     * @param time
     * @returnX
     */
    public static String getHourMinitesByDate(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date startTm = null;
        try {
            startTm = sdf.parse(time);
            Date now = new Date();
            Long interval = Math.abs(startTm.getTime() - now.getTime()) / 1000;
            long hours = interval / (60 * 60);
            long minutes = (interval - hours * (60 * 60)) / 60;
            long seconds = interval - hours * (60 * 60) - minutes * (60);
            String hourStr = "" + hours;
            String minutesStr = "" + minutes;
            String secondsStr = "" + seconds;

            if (hours < 10) {
                hourStr = "0" + hours;
            } else if (hours == 0) {
                hourStr = "00";
            }

            if (minutes < 10) {
                minutesStr = "0" + minutes;
            } else if (minutes == 0) {
                minutesStr = "00";
            }
            if (seconds < 10) {
                secondsStr = "0" + seconds;
            } else if (seconds == 0) {
                secondsStr = "00";
            }
            return hourStr + ":" + minutesStr + ":" + secondsStr;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "00:00";
    }


    /**
     * 将时分秒转为毫秒数
     */
    public static long formatToMillisecond(String time) {
        if (TextUtils.isEmpty(time)) {
            return 0;
        }
        String times[] = time.split(":");
        int hours = Integer.parseInt(times[0]);
        int minutes = Integer.parseInt(times[1]);
        int seconds = Integer.parseInt(times[2]);

        return (hours * 60 * 60 + minutes * 60 + seconds) * 1000;
    }

    public static String getFormatTime(String time) {
        double timeleft;
        Date dateStart = null, dateNow;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat formatter2 = new SimpleDateFormat("HH:mm");
        SimpleDateFormat formatter3 = new SimpleDateFormat("MM月dd日 HH:mm");
        SimpleDateFormat formatter4 = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        try {
            dateStart = formatter.parse(time);
            dateNow = new Date();
            timeleft = (dateNow.getTime() - dateStart.getTime()) / 1000;
        } catch (Exception e) {
            e.printStackTrace();
            timeleft = -1;

        }
        String timeStr;
        if (timeleft == -1) {
            timeStr = "";
        } else if (timeleft < 60) {
            timeStr = "刚刚";
        } else if (timeleft < 60 * 60) {
            timeStr = (int) (timeleft / 60) + "分钟前";
        } else if (timeleft < 60 * 60 * 24) {
            timeStr = (int) (timeleft / 60 / 60) + "小时前";
        } else if (timeleft < 60 * 60 * 24 * 2) {
            timeStr = "昨天" + formatter2.format(dateStart);
        } else if (timeleft < 60 * 60 * 24 * 365) {
            timeStr = formatter3.format(dateStart);
        } else {
            timeStr = formatter4.format(dateStart);
        }
        return timeStr;
    }

    /**
     * 获取昨天今天明天
     *
     * @param day 传入的 时间  "2016-06-28 10:10:30" "2016-06-28" 都可以
     * @return
     */
    public static String formatDay(String day) {

        try {
            Calendar pre = Calendar.getInstance();
            Date predate = new Date(System.currentTimeMillis());
            pre.setTime(predate);
            Calendar cal = Calendar.getInstance();
            Date date = null;
            date = getDateFormat().parse(day);
            cal.setTime(date);
            if (cal.get(Calendar.YEAR) == (pre.get(Calendar.YEAR))) {
                int diffDay = cal.get(Calendar.DAY_OF_YEAR) - pre.get(Calendar.DAY_OF_YEAR);
                if (diffDay == 0) {
                    return "今天";
                } else if (diffDay == -1) {
                    return "昨天";
                } else if (diffDay == 1) {
                    return "明天";
                } else {
                    return day;
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return day;
    }


    /**
     * 判断是否为今天(效率比较高)
     *
     * @param day 传入的 时间  "2016-06-28 10:10:30" "2016-06-28" 都可以
     * @return true今天 false不是
     * @throws ParseException
     */
    public static boolean isToday(String day) {

        Calendar pre = Calendar.getInstance();
        Date predate = new Date(System.currentTimeMillis());
        pre.setTime(predate);
        Calendar cal = Calendar.getInstance();
        Date date = null;
        try {
            date = getDateFormat().parse(day);
            cal.setTime(date);
            if (cal.get(Calendar.YEAR) == (pre.get(Calendar.YEAR))) {
                int diffDay = cal.get(Calendar.DAY_OF_YEAR)
                        - pre.get(Calendar.DAY_OF_YEAR);
                if (diffDay == 0) {
                    return true;
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 判断是否为昨天(效率比较高)
     *
     * @param day 传入的 时间  "2016-06-28 10:10:30" "2016-06-28" 都可以
     * @return true今天 false不是
     * @throws ParseException
     */
    public static boolean isYesterday(String day) throws ParseException {

        Calendar pre = Calendar.getInstance();
        Date predate = new Date(System.currentTimeMillis());
        pre.setTime(predate);

        Calendar cal = Calendar.getInstance();
        Date date = getDateFormat().parse(day);
        cal.setTime(date);

        if (cal.get(Calendar.YEAR) == (pre.get(Calendar.YEAR))) {
            int diffDay = cal.get(Calendar.DAY_OF_YEAR)
                    - pre.get(Calendar.DAY_OF_YEAR);

            if (diffDay == -1) {
                return true;
            }
        }
        return false;
    }


    public static String getYMD(String str) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date;
        try {
            date = format.parse(str);
            return format.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }


    public static SimpleDateFormat getDateFormat() {
        if (null == DateLocal.get()) {
            DateLocal.set(new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()));
        }
        return DateLocal.get();
    }

    private static ThreadLocal<SimpleDateFormat> DateLocal = new ThreadLocal<SimpleDateFormat>();
}
