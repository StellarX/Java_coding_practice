package space.date;

import org.junit.Test;

import java.util.Date;

public class DateTest {

    @Test
    public void test(){
        Date date = new Date();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(date);
        System.out.println(date.getTime());
        date.setTime(date.getTime()-60*60*1000);
        System.out.println(date);
        System.out.println(date.getTime());

    }
}
