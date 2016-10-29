package spring.profile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * Created on 2016/9/16.
 */
@Component
@Profile("prod")
public class ProdProfile {

    private Logger logger = LoggerFactory.getLogger(getClass());


    public ProdProfile() {
        logger.info("***********************************");
        logger.info("prod config init !");
        logger.info("***********************************");
    }
}
