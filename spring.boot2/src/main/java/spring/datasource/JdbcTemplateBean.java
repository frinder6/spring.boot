package spring.datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

/**
 * Created on 2016/9/23.
 */
//@Configuration
public class JdbcTemplateBean {

    @Autowired
    @Qualifier("primaryDataSource")
    private DataSource primaryDataSource;

    @Autowired
    @Qualifier("secondaryDataSource")
    private DataSource secondaryDataSource;


    @Bean(name = "primaryJdbcTemplate")
    @Qualifier("primaryJdbcTemplate")
    public NamedParameterJdbcTemplate primaryJdbcTemplate(){
        return new NamedParameterJdbcTemplate(primaryDataSource);
    }

    @Bean(name = "secondaryJdbcTemplate")
    @Qualifier("secondaryJdbcTemplate")
    public NamedParameterJdbcTemplate secondaryJdbcTemplate(){
        return new NamedParameterJdbcTemplate(secondaryDataSource);
    }

}
