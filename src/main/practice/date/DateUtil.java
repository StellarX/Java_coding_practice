package date;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Pattern;

public class DateUtil {

    public static final String CHINA_DATE_FORMAT = "yyyy年MM月dd日";
    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String TIME_FORMAT = "HH:mm:ss";
    public static final String YEAR_FORMAT = "yyyy";

    public static Date getCurrentDateTime() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_FORMAT);
            return sdf.parse(sdf.format(new Date()));
        } catch (Exception ex) {
            return null;
        }
    }

    public static Date getToday() {
        try {
            Calendar now = Calendar.getInstance();
            now.set(Calendar.HOUR, 0);
            now.set(Calendar.MINUTE, 0);
            now.set(Calendar.SECOND, 0);
            now.set(Calendar.HOUR_OF_DAY, 0);
            return now.getTime();

        } catch (Exception ex) {
            return null;
        }
    }


    public static Date setTodaySlot(Date target) {
        try {
            Calendar now = Calendar.getInstance();
            Calendar tar_cal = Calendar.getInstance();
            tar_cal.setTime(target);

            now.set(Calendar.HOUR_OF_DAY, tar_cal.get(Calendar.HOUR_OF_DAY));
            now.set(Calendar.MINUTE, tar_cal.get(Calendar.MINUTE));
            now.set(Calendar.SECOND, tar_cal.get(Calendar.SECOND));
            now.set(Calendar.HOUR, tar_cal.get(Calendar.HOUR));
            return now.getTime();

        } catch (Exception ex) {
            return null;
        }
    }

    public static int getCurrentYear(){
//        SimpleDateFormat format = new SimpleDateFormat("yyyy");
//        Date now = new Date();
//        return Integer.parseInt(format.format(now));
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.YEAR);
    }

    public static int getCurrentMonth(){
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.MONTH)+1;
    }

    public static int getCurrentDay(){
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.DAY_OF_MONTH);
    }

    public static int getCurrentHour(){
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.HOUR_OF_DAY);
    }


    // Return term string for query
    public static String getTerm(){
        try {
            String term = "";
            Calendar now = Calendar.getInstance();

            if (now.get(Calendar.MONTH) + 1 <= 8){
                term = Integer.toString(now.get(Calendar.YEAR) - 1) + "-" +
                        Integer.toString(now.get(Calendar.YEAR)) + "-2";
            }
            else {
                term = Integer.toString(now.get(Calendar.YEAR)) + "-" +
                        Integer.toString(now.get(Calendar.YEAR) + 1) + "-1";
            }
            return term;
        } catch (Exception ex) {
            return null;
        }
    }
    //----------------判断-----------------------------------------------

    /**
     * 是否是年    *
     * @param yearNum
     * @return
     */
    public static boolean isLeapYear(int yearNum) {
        boolean isLeep = false;
        if ((yearNum % 4 == 0) && (yearNum % 100 != 0))
            isLeep = true;
        else if (yearNum % 400 == 0)
            isLeep = true;
        else {
            isLeep = false;
        }
        return isLeep;
    }

    /**
     * 判断是否是日期
     *
     * @param date
     * @return
     */
    public static boolean isDate(String date) {
        //判断年月日的正则表达式，接受输入格式为2010-12-24，可接受平年闰年的日期
        String regex = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(date).matches();
    }

    /**
     * 验证是不是生日
     *
     * @param birthday
     * @return
     */
    public static boolean verifyBirthDay(String birthday) {
        if (StringUtil.isEmpty(birthday)) return false;
        if (!birthday.contains("-")) return false;
        String[] arr = birthday.split("-");
        if (null == arr || arr.length != 3 || arr[0].length() != 4 || arr[1].length() != 2 || arr[2].length() != 2)
            return false;
        int year = getYear(new Date());
        int birthYear = Integer.parseInt(arr[0]);
        if (birthYear <= 1900 || birthYear > year) return false;
        String curDate = formatDate(new Date(), DATE_FORMAT);
        if (birthday.compareTo(curDate) > 0) return false;
        return isDate(birthday);
    }

    //-------------------自动转化--------------------------------------------

    /**
     * 把字符串自动转化为时间格式
     *
     * @param dateStr
     * @return
     */
    public static Date parseDateByAuto(String dateStr) {
        if (StringUtil.isEmpty(dateStr)) {
            return null;
        }
        String format = DATE_FORMAT;
        if (dateStr.indexOf("/") > -1) {
            format = format.replace("-", "/");
        }
        if (dateStr.indexOf(":") != -1) {
            format += " HH:mm";
        }
        //存在秒
        if (dateStr.indexOf(":") != dateStr.lastIndexOf(":")) {
            format += ":ss";
        }
        return parseDate(dateStr, format);
    }

    //------------当前日期与时间 --------------------------------

    /**
     * 取当前日期
     */
    public static Date getCurrDate() {
        return parseDate(formatDate(new Date()));
    }

    /**
     * 取当前时间
     */
    public static Date getCurrDateTime() {
        return parseDateTime(formatDateTime(new Date()));
    }

    /**
     * 取当前日期
     */
    public static String getCurrDateStr() {
        return formatDate(new Date());
    }

    /**
     * 取当前时间
     */
    public static String getCurrDateTimeStr() {
        return formatDate(new Date(), DATETIME_FORMAT);
    }

    public static String formatCurrDate() {
        return formatDate(new Date(), DATE_FORMAT);
    }

    public static String formatCurrDateTime() {
        return formatDate(new Date(), DATETIME_FORMAT);
    }

    public static String formatCurrDateToS(String strFormat) {
        return formatDate(new Date(), strFormat);
    }
    //-----------时间计算--------------------------------------------

    /**
     * 时间相减
     *
     * @param strDateBegin
     * @param strDateEnd
     * @param iType
     * @return
     */
    public static int getDiffDate(String strDateBegin, String strDateEnd, int iType) {
        Calendar calBegin = Calendar.getInstance();
        calBegin.setTime(parseDate(strDateBegin, DATETIME_FORMAT));
        Calendar calEnd = Calendar.getInstance();
        calBegin.setTime(parseDate(strDateEnd, DATETIME_FORMAT));
        long lBegin = calBegin.getTimeInMillis();
        long lEnd = calEnd.getTimeInMillis();
        if (iType == Calendar.SECOND)
            return (int) ((lEnd - lBegin) / 1000L);
        if (iType == Calendar.MINUTE)
            return (int) ((lEnd - lBegin) / 60000L);
        if (iType == Calendar.HOUR)
            return (int) ((lEnd - lBegin) / 3600000L);
        if (iType == Calendar.DAY_OF_MONTH) {
            return (int) ((lEnd - lBegin) / 86400000L);
        }
        return -1;
    }

    /**
     * 添加天数或月份或年得到新的时间
     *
     * @param strDate
     * @param count
     * @param dayType Calendar.YEAR
     * @return
     */
    public static String getAddDateTime(String strDate, int count, int dayType) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(parseDate(strDate));
        cal.add(dayType, count);
        SimpleDateFormat sdf = null;
        if ((dayType == Calendar.YEAR) || (dayType == Calendar.MONTH) || (dayType == Calendar.DAY_OF_MONTH))
            sdf = new SimpleDateFormat(DATE_FORMAT);
        else
            sdf = new SimpleDateFormat(DATETIME_FORMAT);
        return sdf.format(cal.getTime());
    }

    /**
     * 日期增加天数
     *
     * @param date
     * @param iCount
     * @return
     */
    public static Date getAddDate(Date date, int iCount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, iCount);
        return cal.getTime();
    }

    /**
     * 比较日期
     *
     * @param dateStr1
     * @param dateStr2
     * @return
     */
    public static int compareDate(String dateStr1, String dateStr2) {
        Date date1 = parseDate(dateStr1);
        Date date2 = parseDate(dateStr2);
        if (date1.getTime() > date2.getTime())
            return -1;
        else if (date1.getTime() < date2.getTime())
            return 1;
        else
            return 0;
    }

    public static int compareDate(Date date1, Date date2) {
        if (date1.getTime() > date2.getTime())
            return -1;
        else if (date1.getTime() < date2.getTime())
            return 1;
        else
            return 0;
    }

    /**
     * 时间差
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static int getDiffDays(Date startDate, Date endDate) {
        int days = 0;
        if (startDate.after(endDate)) {
            Date temp = startDate;
            startDate = endDate;
            endDate = temp;
        }
        days = (int) (endDate.getTime() - startDate.getTime()) / 1000 * 60 * 60 * 24;
        return days;
    }

    /**
     * 当前日期的后几天
     *
     * @param date
     * @param n
     * @return
     */
    public static Date getAfterDay(Date date, int n) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, n);
        return c.getTime();
    }

    //---------获取时间天数----------------------------------------------

    /**
     * 获取当前月的最后一天
     *
     * @param dateStr
     * @return
     */
    public static String getMonthEnd(String dateStr) {
        //当前第一天
        Date date = parseDate(getMonthBegin(dateStr));
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, 1);
        c.add(Calendar.DAY_OF_YEAR, -1);
        return formatDate(c.getTime());
    }

    public static String getMonthEnd(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DATE, c.getActualMaximum(Calendar.DATE));
        return formatDate(c.getTime());
    }

    /**
     * 获得当前日期的月份第一天
     *
     * @param dateStr
     * @return
     */
    public static String getMonthBegin(String dateStr) {
        Date date = parseDate(dateStr);
        return formatDate(date, "yyyy-MM") + 01;
    }

    public static String getMonthBegin(Date date) {
        return formatDate(date, "yyyy-MM") + 01;
    }


    //-----------------格式化字符串为日期--------------------------------------

    /**
     * 格式化字符串为日期
     *
     * @param date
     * @param format
     * @return
     */
    public static Date parseDate(String date, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
            return sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date parseDate(String date) {
        return parseDate(date, DATE_FORMAT);
    }

    public static Date parseChinaDate(String date) {
        return parseDate(date, CHINA_DATE_FORMAT);
    }

    public static Date parseDateTime(String date) {
        return parseDate(date, DATETIME_FORMAT);
    }

    public static Date parseTime(String date) {
        return parseDate(date, TIME_FORMAT);
    }


    //---获取年月日时分秒----------------------------------------------------

    /**
     * 获取年份
     *
     * @param date
     * @return
     */
    public static int getYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.YEAR);
    }

    /**
     * 获取月份
     *
     * @param date
     * @return
     */
    public static int getMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取日
     *
     * @param date
     * @return
     */
    public static int getDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取星期
     *
     * @param date
     * @return
     */
    public static int getWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获取时间
     *
     * @param date
     * @return
     */
    public static int getHour(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取分种
     *
     * @param date
     * @return
     */
    public static int getMinute(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MINUTE);
    }

    /**
     * 获取秒
     *
     * @param date
     * @return
     */
    public static int getSecond(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.SECOND);
    }

    //--------------获取星期几---------------------------------------------------

    /**
     * 获取星期几
     *
     * @param strDate
     * @return
     */
    public static String getWeekDayName(String strDate) {
        String[] mName = {"日", "一", "二", "三", "四", "五", "六"};
        Date date = parseDate(strDate);
        int week = getWeek(date);
        return "星期" + mName[week];
    }

    public static String getWeekDayName(Date date) {
        String[] mName = {"日", "一", "二", "三", "四", "五", "六"};
        int week = getWeek(date);
        return "星期" + mName[week];
    }

    /**
     * 一年中的星期几
     *
     * @return
     */
    public static int getWeekNumOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    public static int getWeekNumOfYear(String date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(parseDate(date, DATE_FORMAT));
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 获取本周星期一的日期
     *
     * @param yearNum
     * @param weekNum
     * @return
     * @throws ParseException
     */
    public static String getYearWeekFirstDay(int yearNum, int weekNum) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, yearNum);
        cal.set(Calendar.WEEK_OF_YEAR, weekNum);
        cal.set(Calendar.DAY_OF_WEEK, 2);
        String tempYear = Integer.toString(yearNum);
        String tempMonth = Integer.toString(cal.get(Calendar.MONTH) + 1);
        String tempDay = Integer.toString(cal.get(Calendar.DAY_OF_MONTH) - 1);
        return tempYear + "-" + tempMonth + "-" + tempDay;
    }

    /**
     * 获取本周星期天的日期
     *
     * @param yearNum
     * @param weekNum
     * @return
     * @throws ParseException
     */
    public static String getYearWeekEndDay(int yearNum, int weekNum) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, yearNum);
        cal.set(Calendar.WEEK_OF_YEAR, weekNum + 1);
        cal.set(Calendar.DAY_OF_WEEK, 1);

        String tempYear = Integer.toString(yearNum);
        String tempMonth = Integer.toString(cal.get(Calendar.MONTH) + 1);
        String tempDay = Integer.toString(cal.get(Calendar.DAY_OF_MONTH) - 1);
        return tempYear + "-" + tempMonth + "-" + tempDay;
    }


    //--------------格式化日期-----------------------------------------

    /**
     * 格式化日期为字符串
     *
     * @param date
     * @param format
     * @return
     */
    public static String formatDate(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static String formatDate(Date date) {
        return formatDate(date, DATE_FORMAT);
    }

    public static String formatChinaDate(Date date) {
        return formatDate(date, CHINA_DATE_FORMAT);
    }

    public static String formatDateTime(Date date) {
        return formatDate(date, DATETIME_FORMAT);
    }

    public static String formatTime(Date date) {
        return formatDate(date, TIME_FORMAT);
    }


    //--------------获取天数---------------------------------------------------

    /**
     * 获取某年某月的第一天
     *
     * @param yearNum
     * @param monthNum
     * @return
     */
    public static Date getYearMonthFirstDay(int yearNum, int monthNum) {
        Calendar cal = Calendar.getInstance();
        cal.set(yearNum, monthNum - 1, 1, 0, 0, 0);
        cal.set(14, 0);
        return cal.getTime();
    }

    /**
     * 获取某年下个月的第一天
     *
     * @param yearNum
     * @param monthNum
     * @return
     */
    public static Date getNextYearMonthFirstDay(int yearNum, int monthNum) {
        Calendar cal = Calendar.getInstance();
        cal.set(yearNum, monthNum, 1, 0, 0, 0);
        cal.set(14, 0);
        return cal.getTime();
    }

    /**
     * 获取某年某月的最后一天
     *
     * @param yearNum
     * @param monthNum
     * @return
     */
    public static Date getYearMonthEndDay(int yearNum, int monthNum) {
        Calendar cal = Calendar.getInstance();
        cal.set(yearNum, monthNum, 0, 0, 0, 0);
        cal.set(14, 0);
        return cal.getTime();
    }

    /**
     * 获取某月的第一天
     *
     * @param date
     * @return
     */
    public static Date getYearMonthFirstDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(5, 1);
        cal.set(11, 0);
        cal.set(12, 0);
        cal.set(13, 0);
        cal.set(14, 0);
        return cal.getTime();
    }

    /**
     * 获取下一年的第一天
     *
     * @param date
     * @return
     */
    public static Date getNextYearMonthFirstDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(2, 1);
        cal.set(5, 1);
        cal.set(11, 0);
        cal.set(12, 0);
        cal.set(13, 0);
        cal.set(14, 0);
        return cal.getTime();
    }

    /**
     * 获取当前月的最后一天
     *
     * @param date
     * @return
     */
    public static Date getYearMonthEndDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(5, cal.getActualMaximum(5));
        cal.set(11, 0);
        cal.set(12, 0);
        cal.set(13, 0);
        cal.set(14, 0);
        return cal.getTime();
    }

    /**
     * 获取当年的第一天
     *
     * @param yearNum
     * @return
     */
    public static Date getYearFirstDay(int yearNum) {
        Calendar cal = Calendar.getInstance();
        cal.set(yearNum, 0, 1, 0, 0, 0);
        cal.set(14, 0);
        return cal.getTime();
    }

    /**
     * 获取下一年的第一天
     *
     * @param yearNum
     * @return
     */
    public static Date getNextYearFirstDay(int yearNum) {
        Calendar cal = Calendar.getInstance();
        cal.set(yearNum, 12, 1, 0, 0, 0);
        cal.set(14, 0);
        return cal.getTime();
    }

    /**
     * 获取当年的最后一天
     *
     * @param yearNum
     * @return
     */
    public static Date getYearEndDay(int yearNum) {
        Calendar cal = Calendar.getInstance();
        cal.set(yearNum, 12, 0, 0, 0, 0);
        cal.set(14, 0);
        return cal.getTime();
    }

    /**
     * 获取当前星期
     *
     * @param strDate
     * @param weekNum
     * @return
     */
    public static String getWeek(String strDate, int weekNum) {
        Calendar c = Calendar.getInstance();
        c.setTime(parseDate(strDate));
        if (weekNum == 1)
            c.set(7, 2);
        else if (weekNum == 2)
            c.set(7, 3);
        else if (weekNum == 3)
            c.set(7, 4);
        else if (weekNum == 4)
            c.set(7, 5);
        else if (weekNum == 5)
            c.set(7, 6);
        else if (weekNum == 6)
            c.set(7, 7);
        else if (weekNum == 0)
            c.set(7, 1);
        return formatDate(c.getTime());
    }

    public static Date getWeek(Date date, int weekNum) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        if (weekNum == 1)
            c.set(7, 2);
        else if (weekNum == 2)
            c.set(7, 3);
        else if (weekNum == 3)
            c.set(7, 4);
        else if (weekNum == 4)
            c.set(7, 5);
        else if (weekNum == 5)
            c.set(7, 6);
        else if (weekNum == 6)
            c.set(7, 7);
        else if (weekNum == 0)
            c.set(7, 1);
        return c.getTime();
    }

    /**
     * 下个月日期
     *
     * @param date
     * @return
     */
    public static Date getNextMonday(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        do
            c.add(Calendar.DAY_OF_MONTH, 1);
        while (c.get(Calendar.DAY_OF_WEEK) != 2);
        return c.getTime();
    }

    /**
     * 获得某一日期的前一天
     */
    public static Date getPreviousDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -1);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return getSqlDate(calendar.getTime());
    }

    /**
     * 获得某年某月最后一天的日期
     */
    public static Date getLastDayOfMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DATE, 1);
        return getPreviousDate(getSqlDate(calendar.getTime()));
    }

    /**
     * 获取一个月的天数
     *
     * @param year
     * @param month
     * @return
     */
    public static int getDaysInMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);// Java月份才0开始算
        return cal.getActualMaximum(Calendar.DATE);
    }

    /**
     * 由java.util.Date到java.sql.Date的类型转换
     */
    public static Date getSqlDate(Date date) {
        return new Date(date.getTime());
    }


    public static Date getDateTimeByString(String dateStr) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(dateStr);
        ParsePosition pos = new ParsePosition(8);
        Date currentTime_2 = formatter.parse(dateString, pos);
        return currentTime_2;
    }


    /*
     * 获取当前系统的时间戳
     * */
    public static String getCurrentTimestamp() {

        long timeStamp = new Date().getTime();
        return String.valueOf(timeStamp);
    }

    public static String getCurrentTimestamp10() {

        long timeStamp = new Date().getTime() / 1000;
        String timestr = String.valueOf(timeStamp);
        return timestr;
    }

    public static String getTimeStamp() {
        int time = (int) (System.currentTimeMillis() / 1000);
        return String.valueOf(time);
    }


    /**
     * @Description: 通过入学年份获取当前年级
     * @return
     */
    public static int getGradeByEnterYear(Integer enterYear){
        if (enterYear == null ||enterYear <= 0)return 0;
        int currentYear = getCurrentYear();
        int currentMonth = getCurrentMonth();
        int grade = 0;
        if (currentMonth > 8){
            grade = currentYear - enterYear + 1;
        }else {
            grade = currentYear - enterYear;
        }
        return grade;
    }

    /**
     * @Description: 通过入学年份获取当前年级(初中)
     * @return
     */
    public static int getGradeByEnterYear_middleSchool(Integer enterYear){
        if (enterYear == null ||enterYear <= 0)return 0;
        int currentYear = getCurrentYear();
        int currentMonth = getCurrentMonth();
        int grade = 0;
        if (currentMonth > 8){
            grade = currentYear - enterYear + 1 +6;
        }else {
            grade = currentYear - enterYear + 6;
        }
        return grade;
    }


    /**
     * @Description: 通过年级获得入学年份
     * @param grade
     * @return
     */

    public static Integer getEnterYearByGrade(Integer grade){
        if (grade == null || grade <= 0 || grade > 9){
            return null;
        }
        int currentYear = getCurrentYear();
        int currentMonth = getCurrentMonth();
        int enterYear = 0;
        if (currentMonth > 8){
            enterYear = currentYear - grade + 1;
        }else {
            enterYear = currentYear - grade;
        }
        return enterYear;
    }

    /**
     * @Description: 通过年级获得入学年份（初中）
     * @param grade
     * @return
     */

    public static Integer getEnterYearByGrade_middleSchool(Integer grade){
        if (grade == null || grade <= 0 || grade > 9){
            return null;
        }
        int currentYear = getCurrentYear();
        int currentMonth = getCurrentMonth();
        int enterYear = 0;
        if (currentMonth > 8){
            enterYear = currentYear - grade + 1 + 6;
        }else {
            enterYear = currentYear - grade + 6;
        }
        return enterYear;
    }

    /**
     * @description: 获取当前星期日，国外的第一天从sunday开始
     */
    public static int getCurrentWeekDay(){
        Calendar cal = Calendar.getInstance();
        if (cal.get(Calendar.DAY_OF_WEEK) == 1)return 7;
        return cal.get(Calendar.DAY_OF_WEEK)-1;
    }

    /**
     * @description: 获取昨天的日期
     */
    public static Date getYesterday() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
    }

    /**
     * @description: 根据hh:mm:ss获取到dateTime
     */
    public static Date getDateByTime(String time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date nowDate = DateUtil.getCurrDateTime();
        String date = formatter.format(nowDate);
        String dateTime = date + " " + time;
        return parseDateTime(dateTime);
    }

    public static Date getDateByTimestamp(String timestamp) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(DATETIME_FORMAT);
        String date = formatter.format(timestamp); // 1970年开头的日期
        return formatter.parse(date); //当前日期
    }

    public static String getStringByDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        return format.format(date);
    }

    /**
     *
     * @param from
     * @param to
     * @description: 获取to日期在from日期基准上是第几周,除了1月是特殊情况，其他月份，from和to都在同一年
     */
    public static int getWeekNum(Date from, Date to) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(from);
        int currentYear = calendar.get(Calendar.YEAR);
        int weekDay_from = calendar.get(Calendar.DAY_OF_WEEK);
        if (weekDay_from == 1) {
            weekDay_from = 7;
        } else {
            weekDay_from = weekDay_from - 1;
        }
        int dayOfMonth_from = calendar.get(Calendar.DAY_OF_MONTH);
        int month_from = calendar.get(Calendar.MONTH);
        int daysUtilEnd_from = calendar.getActualMaximum(Calendar.DAY_OF_MONTH) - dayOfMonth_from;
        calendar.setTime(to);
        int weekDay_to = calendar.get(Calendar.DAY_OF_WEEK);
        if (weekDay_to == 1) {
            weekDay_to = 7;
        } else {
            weekDay_to = weekDay_to - 1;
        }
        int dayOfMonth_to = calendar.get(Calendar.DAY_OF_MONTH);
        int month_to = calendar.get(Calendar.MONTH);
//        int daysUtilStart_to = dayOfMonth_to;
        if (month_from > month_to) return 0;
        String dateString = null;
        int daysOfMonth = 0;
        int totalDayCount = 0;
        Date date = null;
        float float1 = 0;
        int weekNum = 0;
        if (month_from < month_to-1){
            for (int i = month_from+1; i < month_to; i++) {
                if (i < 10) {
                    dateString = currentYear + "-0" + i;
                } else {
                    dateString = currentYear + "-" + i;
                }
                try {
                    date = format.parse(dateString);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                calendar.setTime(date);
                daysOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
                totalDayCount += daysOfMonth;

            }
            totalDayCount += daysUtilEnd_from + dayOfMonth_to;
            if (weekDay_from != 1) {
                totalDayCount = totalDayCount - (7 - weekDay_from);
            }


        } else if (month_from == month_to-1) {
            totalDayCount = daysUtilEnd_from + dayOfMonth_to;
        } else if (month_from == month_to) {
            totalDayCount = dayOfMonth_to - dayOfMonth_from;
        }
        if (totalDayCount > 7) {
            float1 = (float) totalDayCount / 7;
            if (float1 > (int) float1) {
                weekNum = (int) float1 + 2;
            } else {
                weekNum = (int) float1 + 1;
            }
        } else {
            weekNum = 1;
        }

        return weekNum;
    }

    public static int getWeekDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int weekNum = calendar.get(Calendar.DAY_OF_WEEK);
        if (weekNum == 1) {
            return 7;
        } else {
            return weekNum-1;
        }
    }

    /**
     *
     * @param to
     * @description: 获取to日期在from日期基准上是第几周,除了1月是特殊情况，其他月份，from和to都在同一年
     */
    public static int getWeekNumByCalendar(Date to) {
        int currentMonth = getMonth(to);
        Date from = null;
        int currentYear = getYear(to);
        Date end = null;

        if (currentMonth == 1) {
            from = parseDate(currentYear - 1 + "-09-01");
            end = parseDate(currentYear - 1 + "-12-31");
            if (getWeekDay(end) == 7) { //去年最后一天是星期日
                return getWeekNumOfYear(end) - getWeekNumOfYear(from) + getWeekNumOfYear(to);
            } else {
                return getWeekNumOfYear(end) - getWeekNumOfYear(from) + getWeekNumOfYear(to) - 1;
            }
        } else if (currentMonth >= 9) { //9月到12月
            from = parseDate(currentYear - 1 + "-09-01");
            return getWeekNumOfYear(to) - getWeekNumOfYear(from);
        } else { //2月到8月
            from = parseDate(currentYear - 1 + "-02-18");
            return getWeekNumOfYear(to) - getWeekNumOfYear(from);
        }
    }

    /**
     *
     * @param to
     * @description: 获取to日期在from日期基准上是第几周,除了1月是特殊情况，其他月份，from和to都在同一年
     */
    public static int getWeekNumByCalendar(Date from, Date to) {
        int currentMonth = getMonth(to);
        int currentYear = getYear(to);
        Date end = null;
        int fromYear = getYear(from);
        int toYear = getYear(to);
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        if (fromYear == toYear - 1) {
//            System.out.println(fromYear + "-12-31");
//            end = parseDate(fromYear + "-12-31");
//            if (getWeekDay(end) == 7) { //去年最后一天是星期日
//                return getWeekNumOfYear(end) - getWeekNumOfYear(from) + getWeekNumOfYear(to);
//            } else {
//                System.out.println(getWeekNumOfYear(end)+ "xxx" +getWeekNumOfYear(from)+ "xx"+ getWeekNumOfYear(to));
//                return getWeekNumOfYear(end) - getWeekNumOfYear(from) + getWeekNumOfYear(to) - 1;
//            }
            calendar.setTime(from);
            System.out.println("getWeekNumOfYear(from): "+ getWeekNumOfYear(from));
            if (getWeekNumOfYear(from) == 1) {
                calendar.add(Calendar.DAY_OF_MONTH, -7);
                System.out.println("calendar.getTime():"+calendar.getTime());
                if (getYear(calendar.getTime()) == fromYear) { //from的日期在年末
                    System.out.println("getWeekNumOfYear(to)"+getWeekNumOfYear(to));
                    return getWeekNumOfYear(to);
                } else { //from的日期在年初
                    return calendar.getActualMaximum(Calendar.WEEK_OF_YEAR) + getWeekNumOfYear(to);
                }
            } else {
                return calendar.getActualMaximum(Calendar.WEEK_OF_YEAR) - getWeekNumOfYear(from) + getWeekNumOfYear(to) + 1;
            }
//            System.out.println(calendar.getActualMaximum(Calendar.WEEK_OF_YEAR)+ "xxx" + getWeekNumOfYear(from)+ "xx"+ getWeekNumOfYear(to));
//            return calendar.getActualMaximum(Calendar.WEEK_OF_YEAR) - getWeekNumOfYear(from) + getWeekNumOfYear(to);
        } else if (fromYear == toYear) {
            return getWeekNumOfYear(to) - getWeekNumOfYear(from) + 1; //在from得第几周，所以+1
        } else { //2019 - 2021的情况，一学期跨度没有那么大
            return 0;
        }

        //todo 每年的最后几天和第二年的开始几天是一周的话，getWeekNumOfYear都是1
    }

    public static int getCurrentWeekNum() {
        int currentMonth = DateUtil.getCurrentMonth();
        int currentYear = DateUtil.getCurrentYear();
        Date startDate = null;
        if (currentMonth >= 9) { //第一学期
            startDate = DateUtil.parseDate(currentYear + "-09-01"); //第一学期开学日期
            return DateUtil.getWeekNumByCalendar(startDate, new Date());
        } else if (currentMonth == 1) {
            startDate = DateUtil.parseDate(currentYear - 1 + "-09-01");
            return DateUtil.getWeekNumByCalendar(startDate, new Date());
        } else { //2-9月
            startDate = DateUtil.parseDate(currentYear + "-02-18"); //第二学期开学日期
            return DateUtil.getWeekNumByCalendar(startDate, new Date());
        }
    }

    //获取本学期有多少周
    public static int getTotalWeekOfTerm() {
        int currentYear = getCurrentYear();
        int currentMonth = getCurrentMonth();
        Date startDate = null;
        Date endDate = null;
        String dateString = null;
        if (currentMonth >= 2 && currentMonth <= 8) {
            startDate = parseDate(currentYear + "02-18");
            endDate = parseDate(currentYear + "7-16");
        } else if (currentMonth == 1){
            startDate = parseDate(currentYear - 1 + "-09-01");
            endDate = parseDate(currentYear + "-01-18");
        } else {
            startDate = parseDate(currentYear + "-09-01");
            endDate = parseDate(currentYear + 1 + "-01-18");
        }
        return getWeekNumByCalendar(startDate, endDate);
    }

    public static Date getStartDateOfTerm() {
        int currentYear = getCurrentYear();
        int currentMonth = getCurrentMonth();
        if (currentMonth >= 2 && currentMonth <= 8) {
            return parseDate(currentYear + "02-18");
        } else if (currentMonth == 1){
            return parseDate(currentYear - 1 + "-09-01");
        } else {
            return parseDate(currentYear + "-09-01");
        }
    }

    public static Date getEndDateOfTerm() {
        int currentYear = getCurrentYear();
        int currentMonth = getCurrentMonth();
        if (currentMonth >= 2 && currentMonth <= 8) {
            return parseDate(currentYear + "07-16");
        } else if (currentMonth == 1){
            return parseDate(currentYear + "-01-18");
        } else {
            return parseDate(currentYear + 1 + "-01-18");
        }
    }
}