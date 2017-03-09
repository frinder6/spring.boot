package spring.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spring.test.BaseTest;

/**
 * Created by frinder6 on 2017/3/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InventoryServiceImplTest extends BaseTest {

    @Autowired
    private InventoryService inventoryService;

    @Test
    public void reduce() throws Exception {
        reduce(inventoryService);
    }

}