package date;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static void main(String[] args) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy��MM��dd��HH:mm:ss");
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy��MM��dd��");
        Date date = new Date();
        String str = simpleDateFormat.format(date);
        String str1 = simpleDateFormat1.format(date);
        System.out.println(str);
        System.out.println(str1);
//        System.out.println(date.getMonth());
    }
}
