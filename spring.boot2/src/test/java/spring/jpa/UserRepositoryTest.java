package spring.jpa;

import com.alibaba.fastjson.JSON;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spring.datasource.UserService2;
import spring.jdbc.UserService;
import spring.mybatis.UserMapper;

import java.util.List;

/**
 * Created on 2016/9/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
//@SpringApplicationConfiguration(App.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

//    @Autowired
    private UserService2 userService2;


    @Test
    public void test() {
        User user = new User("name", "M", 28, "hihi...");
        userRepository.save(user);
//        List<User> users = userRepository.findByName("name");
//        Assert.assertNotNull(users);
//        System.out.println(JSON.toJSONString(users));
//        user = userService.findById(1L);
//        Assert.assertNotNull(user);
//        System.out.println(JSON.toJSONString(user));
//        users = userMapper.findByName("name");
//        Assert.assertNotNull(users);
//        System.out.println(JSON.toJSONString(users));
//        user = userMapper.findById(1L);
//        Assert.assertNotNull(user);
//        System.out.println(JSON.toJSONString(user));

        user = userService.findById(100L);
        Assert.assertNotNull(user);
        System.out.println(JSON.toJSONString(user));

        List<User> users = userMapper.findByIds("1,2,3");
        Assert.assertNotNull(users);
        System.out.println(JSON.toJSONString(users));

//        user = userService2.findByIdPri(1L);
//        Assert.assertNotNull(user);
//        System.out.println(JSON.toJSONString(user));
//
//        user = userService2.findByIdSec(1L);
//        Assert.assertNotNull(user);
//        System.out.println(JSON.toJSONString(user));



    }

}