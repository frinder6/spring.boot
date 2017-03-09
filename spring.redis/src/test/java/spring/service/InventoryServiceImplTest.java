package spring.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spring.config.RedisClient;
import spring.test.BaseTest;

/**
 * Created by frinder6 on 2017/3/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InventoryServiceImplTest extends BaseTest {

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private RedisClient redisClient;

    @Before
    public void setup() {
        redisClient.delKey(("redis_i0001_lock").toUpperCase());
        redisClient.delKey(("redis_i0002_lock").toUpperCase());
    }


    @Test
    public void reduce() throws Exception {
        reduce(inventoryService);
    }

}