package spring.dbs;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by frinder6 on 2016/12/20.
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDatasourceKey();
    }

}
