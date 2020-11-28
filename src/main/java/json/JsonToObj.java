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
        String json = "{\"gender\":\"��\",\"name\":\"����\",\"age\":23}";
//        String json = "{'gender':'��','name':'����','age':23}"; // no!!! error!!!
        ObjectMapper objectMapper = new ObjectMapper();
        Person person = objectMapper.readValue(json, Person.class);
        System.out.println(person.toString());
    }
}
