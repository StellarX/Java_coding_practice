package space.temp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import net.jodah.expiringmap.ExpirationPolicy;
import net.jodah.expiringmap.ExpiringMap;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

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
//        String s1 = "hello";
//        String s2 = new String("hello");
//        System.out.println(s1 == s2);//false
//        System.out.println(s1.equals(s2));//true
//        System.out.println(s1 == s2.intern());//true
//        System.out.println(s1 == s1.intern());//true
//        System.out.println(s2 == s2.intern());//false
//
//        String s3 = new String("1") + new String("1");//在字符串常量池中生成“1” ，并在堆空间中生成s3引用指向的对象（内容为"11"）。注意此时常量池中是没有 “11”对象的
//        System.out.println(s3);
//        s3.intern();
//        System.out.println(s3 == s3.intern());//false

        String s = new String("abc");
        String s1 = "abc";
        String s2 = new String("abc");
        System.out.println(s == s1.intern());//false
        System.out.println(s == s2.intern());//false
        System.out.println(s1 == s2.intern());//true
    }
}
