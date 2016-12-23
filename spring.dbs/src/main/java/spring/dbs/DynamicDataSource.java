package spring.dbs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by frinder6 on 2016/12/20.
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    private static final Logger LOGGER = LoggerFactory.getLogger(DynamicDataSource.class);


    @Override
    protected Object determineCurrentLookupKey() {
        String key = DataSourceContextHolder.getDatasourceKey();
        LOGGER.info("*********************************");
        LOGGER.info("datasource key is : " + key);
        LOGGER.info("*********************************");
        return key;
    }

}
