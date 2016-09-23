package spring.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Created by frinder6 on 2016/9/16.
 */
@Component
public class ApplicationRunnerImpl implements ApplicationRunner {

    private Logger logger = LoggerFactory.getLogger(getClass());


    public void run(ApplicationArguments applicationArguments) throws Exception {
        logger.info("***********************************");
        logger.info("application runner init !");
        logger.info("***********************************");
    }
}
