package spring.runner;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import spring.yaml.YamlBean;

/**
 * Created by frinder6 on 2016/9/16.
 */
@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private YamlBean yamlBean;


    public void run(String... strings) throws Exception {
        logger.info("***********************************");
        logger.info("command line runner init !");
        logger.info("***********************************");
        logger.info(JSON.toJSONString(yamlBean.getList()));
        logger.info(JSON.toJSONString(yamlBean.getMap()));
    }
}
