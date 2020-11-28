package json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

@Data
class Person{
    String gender;
    String name;
    Integer age;
}

public class JsonToObj {
    public static void main(String[] args) throws JsonProcessingException {
        String json = "{\"gender\":\"男\",\"name\":\"张三\",\"age\":23}";
//        String json = "{'gender':'男','name':'张三','age':23}"; // no!!! error!!!
        ObjectMapper objectMapper = new ObjectMapper();
        Person person = objectMapper.readValue(json, Person.class);
        System.out.println(person.toString());
    }
}
