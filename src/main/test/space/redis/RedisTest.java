package space.redis;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Calendar;
import java.util.Date;

public class RedisTest {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Test
    public void testRedis(){
        System.out.println("test");
//        redisTemplate.boundValueOps("name").set("tom");
//        String name = redisTemplate.boundValueOps("name").get();
//        System.out.println(name);

    }
}
