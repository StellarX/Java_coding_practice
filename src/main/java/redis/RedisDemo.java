package redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisDemo {
    @Autowired
    private RedisTemplate redisTemplate;

    public void test() {
        //1����redis�л������ ���ݵ���ʽjson�ַ���
        
//        String userListJson = redisTemplate.boundValueOps("user.findAll").get();
//        redisTemplate.boundValueOps("user.findAll").set(userListJson);

//        System.out.println(userListJson);
    }
}
