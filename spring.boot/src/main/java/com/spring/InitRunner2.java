package com.spring;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by frinder6 on 2016/9/2.
 */
@Component
public class InitRunner2 implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public void run(String... strings) throws Exception {
        logger.debug("begim init...");
        System.out.println(JSON.toJSONString(strings));
    }
}
