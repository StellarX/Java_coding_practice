package space.time;

import java.util.Calendar;
import java.util.Date;

/**
 * @author space
 * @description practice for class Calendar
 */
public class CalendarDemo {
    public static void main(String[] args) {
//        获取基本时间元素();
//        System.out.println(new Date().getTime() / 1000);
        改变一周的开始星期();
//        设置时间();

//        System.out.println(cal.getCalendarType());
    }
    public static void 获取基本时间元素(){
        // 使用默认时区和语言环境获得一个日历
        Calendar cal = Calendar.getInstance();
        // 赋值时年月日时分秒常用的6个值，注意月份下标从0开始，所以取月份要+1
        System.out.println("年:" + cal.get(Calendar.YEAR));
        System.out.println("月:" + (cal.get(Calendar.MONTH) + 1));
        System.out.println("日:" + cal.get(Calendar.DAY_OF_MONTH));//or Calendar.DATE
        System.out.println("时:" + cal.get(Calendar.HOUR_OF_DAY));
        System.out.println("分:" + cal.get(Calendar.MINUTE));
        System.out.println("秒:" + cal.get(Calendar.SECOND));
        System.out.println("星期:" + cal.get(Calendar.DAY_OF_WEEK));
    }
    public static void 改变一周的开始星期(){
        Calendar cal = Calendar.getInstance();
        System.out.println("一周的第一天:" + cal.getFirstDayOfWeek());//默认是周日的编号，即1
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        System.out.println("一周的第一天:" + cal.getFirstDayOfWeek());//变成周一的编号了，即2

        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        System.out.println(dayWeek);
        if (dayWeek == 1) {
            dayWeek = 8;
        }
        Date time = cal.getTime();
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - dayWeek);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        Date mondayDate = cal.getTime();
        long l = cal.getTimeInMillis() / 1000;//1619405306
//
//        System.out.println(cal.get(Calendar.DAY_OF_WEEK));
    }
    public static void 设置时间(){
        Calendar cal = Calendar.getInstance();
        System.out.println(cal.getTime());

        // 如果想设置为某个日期，可以一次性设置年月日时分秒（也可以单独设置），由于月份下标从0开始赋值月份要-1
        // cal.set(year, month, date, hourOfDay, minute, second);
        cal.set(2018, Calendar.JANUARY, 15, 23, 59, 59);
        System.out.println(cal.getTime());

        cal.add(Calendar.SECOND, 1);//add 一秒， 会自动变成下一个正确的时间
        System.out.println(cal.getTime());

        cal.add(Calendar.DAY_OF_MONTH, -1);
        System.out.println("date - 1 = " + cal.getTime());

        cal.set(2018, Calendar.FEBRUARY, 31, 23, 59, 59);//设置不存在的时间会报错
        System.out.println(cal);
    }
}
