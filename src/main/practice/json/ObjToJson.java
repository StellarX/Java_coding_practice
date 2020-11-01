package json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @comment ¶ÔÏó×ªjson×Ö·û´®
 */
public class ObjToJson {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> list = new ArrayList<>();

        list.add("javak");
        list.add("tomm");
        System.out.println(list);

        String s = objectMapper.writeValueAsString(list);
        System.out.println(s);

    }
}
