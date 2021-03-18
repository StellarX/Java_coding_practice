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
        String s = "hello world";
        String replace = s.replace(" ", "%20");
        System.out.println(replace);
    }
}
