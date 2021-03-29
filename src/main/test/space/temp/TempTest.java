package space.temp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
public class TempTest {
    @Test
    public void test() throws ParseException {
        String start = "18:23:00";
        String end = "17:40:00";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        Date startTime = simpleDateFormat.parse(start);
        Date endTime = simpleDateFormat.parse(end);
        System.out.println(startTime);
        System.out.println(endTime);
    }

    @Test
    public void test2() {
        String s = "hello world";
        String replace = s.replace(" ", "%20");
        System.out.println(replace);
    }
}
