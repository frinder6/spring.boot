package spring.service;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by frinder6 on 2017/3/3.
 */
@Service("inventoryService")
public class InventoryServiceImpl implements InventoryService {

    static Map<String, Integer> map = new HashMap() {{
        put("i0001", 1000);
        put("i0002", 1000);
    }};


    @Override
    public void reduce(String inventoryId) {
        map.put(inventoryId, map.get(inventoryId) - 1);
    }

    @Override
    public void print() {
        System.out.println(JSON.toJSONString(map));
    }
}
