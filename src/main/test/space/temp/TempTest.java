package space.temp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Slf4j
public class TempTest {
    @Test
    public void test(){
        String s = "vgdecoderesult={\"invitationId\":140,\"arrivalDate\":\"2021-01-20 23:13:00\",\"leaveDate\":\"2021-01-21 02:00:00\",\"memberNum\":0}";
        String[] split = s.split("=");
        System.out.println(split[1]);
        JSONObject jsonObject = JSON.parseObject(split[1]);
        String invitationId = jsonObject.getString("invitationId");
        System.out.println(invitationId);
        String arrivalDate = jsonObject.getString("arrivalDate");
        System.out.println(arrivalDate);
        String leaveDate = jsonObject.getString("leaveDate");
        System.out.println(leaveDate);

    }

    @Test
    public void uploadCourseInfo() throws UnsupportedEncodingException {
        String path = "C:\\Users\\Administrator\\Desktop\\a.csv";
        String[] fileHeader = new String[]{"课程名称", "年级", "星期几", "开始时间", "结束时间", "教师姓名", "描述", "学生名单"};

        InputStreamReader isr = null;
        try {
            isr = new InputStreamReader(new FileInputStream(path), "GB2312");
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            log.error("read path: {} error: {}", path, e.getMessage());
            return;
        }
        CSVFormat format = CSVFormat.DEFAULT.withHeader(fileHeader).withFirstRecordAsHeader().withIgnoreHeaderCase()
                .withTrim();
        CSVParser csvParser = null;
        try {
            csvParser = new CSVParser(isr, format);
        } catch (IOException e) {
            log.error("init csvParser error: {}", e.getMessage());
        }
        List<CSVRecord> records = null;
        try {
            assert csvParser != null;
            records = csvParser.getRecords();
        } catch (IOException e) {
            log.error("csvParser get records error : {}", e.getMessage());
            return;
        }
        String courseName = null;
        String grade = null;
        String dayOfWeek = null;
        Date startTime = null;
        Date endTime = null;
        String teacherName = null;
        String description = null;
        String[] studentListArray = null;

        String studentList = null;
        String start = null;
        String end = null;
        for (CSVRecord record : records){
            if (record == null) continue;
            //"课程名称", "年级", "星期几", "开始时间", "结束时间", "教师姓名", "描述", "学生名单"
            courseName = record.get(0).trim();
            grade = record.get(1).trim();
            dayOfWeek = record.get(2).trim();
            start = record.get(3).trim();
            end = record.get(4).trim();
            teacherName = record.get(5).trim();
            description = record.get(6).trim();
            studentList = record.get(7).trim();

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
            try {
                startTime = simpleDateFormat.parse(start);
                endTime = simpleDateFormat.parse(end);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            studentListArray = studentList.split(";");
            log.debug("courseName: {}, grade: {}, dayOfWeek: {}, startTime: {}, endTime: {}, teacherName: {}, studentListArray: {}",
                    courseName, grade, dayOfWeek, startTime, endTime, teacherName, studentListArray);
            // TODO: 2021/1/26 新增course、对应的student 
        }
    }
}
