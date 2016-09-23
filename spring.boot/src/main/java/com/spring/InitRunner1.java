package com.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Created by frinder6 on 2016/9/2.
 */
@Component
public class InitRunner1 implements ApplicationRunner {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public void run(ApplicationArguments applicationArguments) throws Exception {
        logger.debug("begin init...");
        System.out.println("*****************************" + applicationArguments.getOptionNames());
    }

}
