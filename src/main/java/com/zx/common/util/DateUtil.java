package com.zx.common.util;

import com.zx.contants.Contants;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author ning
 * 创建于 2017年10月18日下午4:10:37
 * //TODO 日期工具类
 */
public class DateUtil {

    public static final int JANUARY_MONTH = 1;
    public static final int FEBRUARY_MONTH = 2;
    public static final int MARCH_MONTH = 3;
    public static final int APRIL_MONTH = 4;
    public static final int MAY_MONTH = 5;
    public static final int JUNE_MONTH = 6;
    public static final int JULY_MONTH = 7;
    public static final int AUGUST_MONTH = 8;
    public static final int SEPTEMBER_MONTH = 9;
    public static final int OCTOBER_MONTH = 10;
    public static final int NOVEMBER_MONTH = 11;
    public static final int DECEMBER_MONTH = 12;
    
    public static final String DEFAULT_FORMAT_DATE = "yyyy-MM-dd";

    public static final String DEFAULT_FORMAT_DATE_TIME = "yyyy-MM-dd HH:mm:ss";

    public static final String ASSIGN_FORMAT_DATE_TIME = "yyyy/MM/dd HH:mm";

    public static final String ASSIGN_FORMAT_DATE = "yyyy/MM/dd";

    public static final String DEFAULT_FORMAT_TIME = "HH:mm:ss";

    public static final String FORMAT_TIME_MONTH = "yyyy-MM";

    public static final String FORMAT_TIME_HOUR = "yyyy-MM-dd HH";

    public static final String FORMAT_DATE_HOUR = "yyyyMMddHH";

    public static final String FORMAT_DATE_DAY = "yyyyMMdd";

    public static void main(String[] args) {

    }

    public static List<String> getDateList(Date startDate, Date endDate) {
        List<String> dateList = new ArrayList<>();
        Date bStartDate = DateUtil.getDateBegining(startDate);
        Date bEndDate = DateUtil.getDateBegining(endDate);
        if (bStartDate.getTime() == bEndDate.getTime()){
            //同一天
            dateList.add(DateUtil.formatDate(bStartDate));
        }else {
            while (bStartDate.getTime() <= bEndDate.getTime()){
                dateList.add(DateUtil.formatDate(bStartDate));
                bStartDate = DateUtil.getAterDate(bStartDate,1);
            }
        }
        return dateList;
    }

    /**
     * @param date
     * @return 获取过去一年的那天的日期
     */
    public static Date getBeforeYear(Date date){
        Calendar c = Calendar.getInstance();

        //过去一年
        c.setTime(new Date());
        c.add(Calendar.YEAR, -1);
        Date y = c.getTime();
        return y;
    }

    /**
     * @param date
     * @return 获取过去一个月的那天的日期
     */
    public static Date getBeforeMonth(Date date){
        Calendar c = Calendar.getInstance();

        //过去一月
        c.setTime(new Date());
        c.add(Calendar.MONTH, -1);
        Date m = c.getTime();
        return m;
    }

    /**
     * @param date
     * @return 查询过去七天的日期
     */
    public static Date getBeforeWeekDate(Date date){
        Calendar c = Calendar.getInstance();

        //过去七天
        c.setTime(new Date());
        c.add(Calendar.DATE, - 7);
        Date d = c.getTime();
        return d;
    }


    private static int getIntFromString(String time) {
        if (StringUtil.notEmpty(time)) {
            String[] timeArr = time.split(Contants.COLON_CHAR);
            if (timeArr != null && timeArr.length == 2) {
                int hour = Integer.valueOf(timeArr[0]);
                int min = Integer.valueOf(timeArr[1]);
                return hour * 60 + min;
            }
        }
        return 0;
    }

    private static String getTimeStamp(String time) {
        if (StringUtil.notEmpty(time)){
            String[] timeArr = time.split(Contants.COLON_CHAR);
            if (timeArr != null && timeArr.length == 2){
                int hour = Integer.valueOf(timeArr[0]);
                int min = Integer.valueOf(timeArr[1]);
                if (hour >= 24){
                    hour = hour - 24;
                    return hour + Contants.COLON_CHAR + min;
                }else {
                    return time;
                }
            }
        }
        return null;
    }

    /**
     * @param timeStr
     * @param date
     * @return 将13:45格式字符串转换成当前日期时间， 如：2018-03-20 13:05:00
     */
    public static Date getDateFromActiveTimeStr(String timeStr, Date date, String type){
        String dateStr = formatDate(date);

        String dateTimeStr = null;
        if ("start".equals(type)){
            dateTimeStr = dateStr + " " + timeStr + Contants.COLON_CHAR + "00";
        }else if ("end".equals(type)){
            dateTimeStr = dateStr + " " + timeStr + Contants.COLON_CHAR + "59";
        }
        Date date1 = parseDate(dateTimeStr,DEFAULT_FORMAT_DATE_TIME);
        return date1;
    }


    public static Set<Long> getTimeSetFromDate(Date date1, Date date2) {
        Date startDate = null;
        Date endDate = null;
        if (date1.before(date2)){
            startDate = date1;
            endDate = date2;
        }else {
            startDate = date2;
            endDate = date1;
        }
        Long s = startDate.getTime() / 1000;
        Long e = endDate.getTime() / 1000;

        Set<Long> timeSet = new HashSet<>();
        while (s <= e) {
            timeSet.add(s++);
        }
        return timeSet;
    }


    private static Set<Integer> getTimeSetFromStr(String patrolTime) {
        Set<Integer> timeSet = new HashSet<>();
        String[] activeTimeArr = patrolTime.split(Contants.LINE_CHAR);
        String startTimeStr = activeTimeArr[0];
        String endTimeStr = activeTimeArr[1];
        Integer startTimeInt = getMinFromString(startTimeStr);
        Integer endTimeInt = getMinFromString(endTimeStr);
        if (startTimeInt == null || endTimeInt == null){
            return null;
        }
        for(int i = startTimeInt; i<= endTimeInt; i++){
            timeSet.add(i);
        }
        return timeSet;
    }

    public static boolean checkPatrolTimeIsAcrossDay(String patrolTime) {
        String[] activeTimeArr = patrolTime.split(Contants.LINE_CHAR);
        String startTimeStr = activeTimeArr[0];
        String endTimeStr = activeTimeArr[1];
        String replaceStart = startTimeStr.replace(Contants.COLON_CHAR, "");
        String replaceEnd = endTimeStr.replace(Contants.COLON_CHAR, "");
        if (Integer.valueOf(replaceStart) > Integer.valueOf(replaceEnd)){
            return true;
        }

        return false;
    }

    /**
     * @return 将13:45,【小时:分钟】格式字符串转换成分钟： 13 * 60 + 45
     */
    public static Integer getMinFromString(String timeStr){
        String[] timeArr = timeStr.split(Contants.COLON_CHAR);
        if (timeArr == null || timeArr.length != 2){
            return null;
        }
        if (Validators.isDigit(timeArr[0]) && Validators.isDigit(timeArr[1])){
            return  Integer.valueOf(timeArr[0]) * 60 + Integer.valueOf(timeArr[1]);
        }
        return null;
    }

    /**
     * 功能描述：返回小
     *
     * @param date
     *            日期
     * @return 返回小时
     */
    public static int getHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 功能描述：返回分
     *
     * @param date
     *            日期
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
     * @param date
     *            Date 日期
     * @return 返回秒钟
     */
    public static int getSecond(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.SECOND);
    }

    /**
     * 功能描述：返回毫
     *
     * @param date
     *            日期
     * @return 返回毫
     */
    public static long getMillis(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getTimeInMillis();
    }


    /**
     * 获取当前日期是星期几
     *
     * @param dt
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate(Date dt) {
//        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        String[] weekDays = {"0", "1", "2", "3", "4", "5", "6"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    public static String formatDate(Date date) {
        return formatDate(date, DEFAULT_FORMAT_DATE);
    }

    public static String formatDateTime(Date date) {
        return formatDate(date, DEFAULT_FORMAT_DATE_TIME);
    }

    public static String formatTime(Date date) {
        return formatDate(date, DEFAULT_FORMAT_TIME);
    }

    public static String formatMonthDate(Date date) {
        return formatDate(date, FORMAT_TIME_MONTH);
    }

    public static String formatMonthTime(long monthTime) {
        Date date = new Date(monthTime);
        return formatDate(date, FORMAT_TIME_MONTH);
    }

    public static String formatHourTime(long hourTime) {
        Date date = new Date(hourTime);
        return formatDate(date, FORMAT_TIME_HOUR);
    }

    public static String formatHourDate(long time) {
        Date date = new Date(time);
        return formatDate(date, FORMAT_DATE_HOUR);
    }

    public static String formatDayDate(long time) {
        Date date = new Date(time);
        return formatDate(date, FORMAT_DATE_DAY);
    }

    /**
     * getDateBegin 2016年6月17日 下午8:00:05
     * 
     * @param date
     * @return TODO：获取某天的起始时间
     */
    public static Date getDateBegining(Date date) {
        String startStr = DateUtil.formatDate(date, DateUtil.DEFAULT_FORMAT_DATE);
        Date startDateTime = DateUtil.parseDateTime(startStr + " 00:00:00");
        return startDateTime;
    }

    /**
     * getDateEnding 2016年6月17日 下午8:01:16
     * 
     * @param date
     * @return TODO：获取某天的结尾时间
     */
    public static Date getDateEnding(Date date) {
        String endStr = DateUtil.formatDate(date, DateUtil.DEFAULT_FORMAT_DATE);
        Date endDateTime = DateUtil.parseDateTime(endStr + " 23:59:59");
        return endDateTime;
    }

    /**
     * Format the date object with specified pattern.
     */
    public static String formatDate(Date date, String pattern) {
        if (date == null) {
            return "";
        }

        if (pattern == null) {
            pattern = DEFAULT_FORMAT_DATE_TIME;
        }

        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }

    public static Date parseDate(String dateStr) {
        return parseDate(dateStr, DEFAULT_FORMAT_DATE);
    }

    public static Date parseDateTime(String dateTimeStr) {
        return parseDate(dateTimeStr, DEFAULT_FORMAT_DATE_TIME);
    }

    public static Date parseHourDate(String dateStr) {
        return parseDate(dateStr, FORMAT_DATE_HOUR);
    }

    public static Date parseMonthDateTime(String monthDateStr) {
        return parseDate(monthDateStr, FORMAT_TIME_MONTH);
    }

    public static Date parseDate(String dateTimeStr, String pattern) {
        if (dateTimeStr == null || dateTimeStr.length() == 0) {
            return null;
        }

        if (pattern == null) {
            pattern = DEFAULT_FORMAT_DATE_TIME;
        }

        DateFormat df = new SimpleDateFormat(pattern);
        try {
            return df.parse(dateTimeStr);
        } catch (Exception ex) {
            return null;
        }
    }

    public static long parseDayTime(String str) {
        return parseDate(str, FORMAT_DATE_DAY).getTime();
    }

    public static String getMonthValue(int month) {
        String result = "";
        if (month == JANUARY_MONTH) {
            result = "January";
        } else if (month == FEBRUARY_MONTH) {
            result = "February";
        } else if (month == MARCH_MONTH) {
            result = "March";
        } else if (month == APRIL_MONTH) {
            result = "April";
        } else if (month == MAY_MONTH) {
            result = "May";
        } else if (month == JUNE_MONTH) {
            result = "June";
        } else if (month == JULY_MONTH) {
            result = "July";
        } else if (month == AUGUST_MONTH) {
            result = "August";
        } else if (month == SEPTEMBER_MONTH) {
            result = "September";
        } else if (month == OCTOBER_MONTH) {
            result = "October";
        } else if (month == NOVEMBER_MONTH) {
            result = "November";
        } else if (month == DECEMBER_MONTH) {
            result = "December";
        }

        return result;
    }

    /**
     * getBeforeDate 2016年6月14日 上午9:25:07
     * 
     * @param currentDate
     * @return TODO：获取前一天日期
     */
    public static Date getBeforeDate(Date currentDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        Date preDate = calendar.getTime();
        return preDate;
    }

    public static Date getAterDate(Date currentDate, int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DAY_OF_YEAR, num);
        Date preDate = calendar.getTime();
        return preDate;
    }

    public static Date getAfterMonthDate(Date currentDate, int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.MONTH, num);
        Date preDate = calendar.getTime();
        return preDate;
    }

    public static String formatDateLongSeconds(long beginTime) {
        return formatDateLongSeconds(beginTime, DEFAULT_FORMAT_DATE);
    }

    public static String formatDateLongSeconds(long beginTime, String pattern) {
        Date date = new Date(beginTime * 1000);
        return formatDate(date, pattern);
    }

    /**
     * 前一天开始时间时间戳
     * @param date
     * @return
     */
    public static long getLastDayBeginTimestamp(Date date) {
        return DateUtil.getBeforeDate(date).getTime();
    }

    /**
     * 该毫秒数所在小时的开始毫秒数
     * @param time
     * @return
     */
    public static long getHourBegin(long time) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(time);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        return c.getTimeInMillis();
    }

    /*
    * 根据时间点和前后时间差，封装时间段字符串
    * 如：14:50， 前后时间各30分钟，输入结果为：14:20-15:20
    *     12:30,  前后时间各15分钟，输入结果为：12:15-12:45
    * */
    public static String getActiveTime(String str, Integer beforeTime, Integer afterTime) {
        String startTimeStr = null;
        String endTimeStr = null;

        String[] hourMinute = str.split(":");
        if(hourMinute.length != 2) {
            return null;
        } else if(hourMinute[0].length() == 2 && hourMinute[1].length() == 2) {
            int hour;
            for(hour = 0; hour < 2; ++hour) {
                if(!Character.isDigit(hourMinute[0].charAt(hour)) || !Character.isDigit(hourMinute[1].charAt(hour))) {
                    return null;
                }
            }

            hour = Integer.parseInt(hourMinute[0]);
            int min = Integer.parseInt(hourMinute[1]);
            Integer startHour = hour;
            Integer endHour = hour;
            Integer startMin = min - beforeTime;
            Integer endMin = min + afterTime;

            //验证起始分钟是否小于0，小于，说明跨天
            if (startMin < 0){
                startHour--;
                startMin = 60 + startMin;
                if (startHour < 0){
                    startHour = 23;
                }
            }

            //验证结束分钟是否大于6等于0，是，说明跨天
            if (endMin >= 60){
                endHour++;
                if (endHour >= 24){
                    endHour = endHour - 24;
                }
                endMin = endMin - 60;
            }

            startTimeStr = StringUtil.leftPad(startHour, 2, '0') + ":" + StringUtil.leftPad(startMin, 2, '0');
            endTimeStr = StringUtil.leftPad(endHour, 2, '0') + ":" + StringUtil.leftPad(endMin, 2, '0');

        }
        return  startTimeStr + "-" + endTimeStr;
    }

    public static Date convertPatrolExecuteEndDate(String activeTimeStr) {
        if (StringUtil.isEmpty(activeTimeStr)){
            return null;
        }
        String[] activeTimeArr = activeTimeStr.split(Contants.LINE_CHAR);
        if(activeTimeArr == null || activeTimeArr.length != 2){
            return null;
        }
        String endTimeStr = activeTimeArr[1];
        boolean isAccessDay = checkPatrolTimeIsAcrossDay(activeTimeStr);
        Calendar now = Calendar.getInstance();
        if (isAccessDay) {
            now.add(Calendar.DAY_OF_YEAR, 1);
        }
        String[] timeArr = endTimeStr.split(Contants.COLON_CHAR);
        now.set(Calendar.HOUR_OF_DAY, Integer.valueOf(timeArr[0]));
        now.set(Calendar.MINUTE, Integer.valueOf(timeArr[1]));
        now.set(Calendar.SECOND, 0);
        now.set(Calendar.MILLISECOND, 0);
        return now.getTime();
    }

    public static String checkTimeIsAcrossDay(String activeTimeStr, long time) {
        Date date = new Date(time);
        String[] arr = activeTimeStr.split(Contants.LINE_CHAR);
        if (arr != null && arr.length == 2) {
            String startTimeStampStr = arr[0];
            String endTimeStampStr = arr[1];
            Date now = new Date();
            String beforeDateStr = formatDate(getBeforeDate(now));
            String dateStr = formatDate(now);
            String afterDateStr = formatDate(getAterDate(now,1));
            boolean isAcrossDay = checkPatrolTimeIsAcrossDay(activeTimeStr);
            if (isAcrossDay) {
                //跨天
                String startTimeStr = dateStr + " " + startTimeStampStr + Contants.COLON_CHAR + "00";
                String endTimeStr = afterDateStr + " " + endTimeStampStr + Contants.COLON_CHAR + "59";
                Date date1 = parseDate(startTimeStr,DEFAULT_FORMAT_DATE_TIME);
                Date date2 = parseDate(endTimeStr,DEFAULT_FORMAT_DATE_TIME);
                if (time >= date1.getTime() && time <= date2.getTime()){
                    return formatDate(date);
                }else {
                    String startTimeStr1 = beforeDateStr + " " + startTimeStampStr + Contants.COLON_CHAR + "00";
                    String endTimeStr1 = dateStr + " " + endTimeStampStr + Contants.COLON_CHAR + "59";
                    Date date3 = parseDate(startTimeStr1,DEFAULT_FORMAT_DATE_TIME);
                    Date date4 = parseDate(endTimeStr1,DEFAULT_FORMAT_DATE_TIME);
                    if (time >= date3.getTime() && time <= date4.getTime()){
                        return formatDate(getBeforeDate(date));
                    }
                }
            }
        }
        return formatDate(date);
    }

    public static String checkKuaTian(String activeTime) {
        String[] arr = activeTime.split(Contants.LINE_CHAR);
        if (arr != null && arr.length == 2) {
            String startTimeStampStr = getTimeStamp(arr[0]);
            String endTimeStampStr = getTimeStamp(arr[1]);
            return startTimeStampStr + Contants.LINE_CHAR + endTimeStampStr;
        }
        return "";
    }

    private static boolean checkPatrolTimeBelong(String beforeHalfTime, String patrolTime) {
        String[] timeArr = beforeHalfTime.split(Contants.LINE_CHAR);
        String start = timeArr[0];
        String end = timeArr[1];
        int startInt = Integer.valueOf(start.replace(Contants.COLON_CHAR,""));
        int endInt = Integer.valueOf(end.replace(Contants.COLON_CHAR,""));
        int patrolTimeInt = Integer.valueOf(patrolTime.replace(Contants.COLON_CHAR,""));
        if (startInt <= patrolTimeInt && endInt >= patrolTimeInt){
            return true;
        }
        return false;
    }

    public static Date checkPatrolExecuteEndDate(String activeTimeStr, String beforeWeek, String activeWeekStr) {
        if (StringUtil.isEmpty(activeTimeStr)){
            return null;
        }
        String[] activeTimeArr = activeTimeStr.split(Contants.LINE_CHAR);
        if(activeTimeArr == null || activeTimeArr.length != 2){
            return null;
        }
        String endTimeStr = activeTimeArr[1];
        boolean isAccessDay = checkPatrolTimeIsAcrossDay(activeTimeStr);
        Calendar now = Calendar.getInstance();
        if (isAccessDay) {
            //验证上一天是否需要采集数据
            if (!activeWeekStr.contains(beforeWeek)){
                return null;
            }
        }
        String[] timeArr = endTimeStr.split(Contants.COLON_CHAR);
        now.set(Calendar.HOUR_OF_DAY, Integer.valueOf(timeArr[0]));
        now.set(Calendar.MINUTE, Integer.valueOf(timeArr[1]));
        now.set(Calendar.SECOND, 0);
        now.set(Calendar.MILLISECOND, 0);
        return now.getTime();
    }

}