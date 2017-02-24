package com.rpc.config;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created on 2016/9/16.
 */
@Component
public class ApplicationRunnerImpl implements ApplicationRunner {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ApplicationContext applicationContext;


    public void run(ApplicationArguments applicationArguments) throws Exception {
        logger.info("***********************************");
        logger.info("application runner init !");
        String[] beans = applicationContext.getBeanDefinitionNames();
        logger.info(JSON.toJSONString(beans));
        for (String bean : beans) {
            logger.info(bean);
        }
        logger.info("***********************************");

    }
}
