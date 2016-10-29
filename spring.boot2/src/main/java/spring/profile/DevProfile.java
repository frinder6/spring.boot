package spring.profile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * Created on 2016/9/16.
 */
@Component
@Profile("dev")
public class DevProfile {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public DevProfile() {
        logger.info("***********************************");
        logger.info("dev config init !");
        logger.info("***********************************");
    }
}
