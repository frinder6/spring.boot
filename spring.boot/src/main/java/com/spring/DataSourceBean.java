package com.spring;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * Created on 2016/9/12.
 */
//@Component
public class DataSourceBean {


//    @Bean
//    @ConfigurationProperties(prefix = "datasource.mine")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

}
