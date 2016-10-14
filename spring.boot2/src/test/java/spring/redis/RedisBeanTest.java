package spring.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spring.jpa.User;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * Created by frinder6 on 2016/9/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RedisBeanTest {

//    @Autowired
//    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private RedisTemplate<String, Object> clusterRedisTemplate;

//    @Autowired
//    @Resource
//    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void test(){
        String key = "user1";
        User user = new User("name", "M", 28, "hihi...");
//        redisTemplate.opsForValue().set(key, user);
//        System.out.println(redisTemplate.opsForValue().get(key));

        clusterRedisTemplate.opsForValue().set(key, user);
        System.out.println(clusterRedisTemplate.opsForValue().get(key));

//        stringRedisTemplate.opsForValue().set("aaa", "user111");
//        System.out.println(stringRedisTemplate.opsForValue().get("aaa"));
    }

}