package spring.dbs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

/**
 * Created by frinder6 on 2016/12/20.
 */
@Configuration
public class JdbcTemplateBean {

    @Autowired
    @Qualifier("dynamicDataSource")
    private DataSource dynamicDataSource;

    @Bean(name = "dynamicJdbcTemplate")
    @Qualifier("dynamicJdbcTemplate")
    public NamedParameterJdbcTemplate primaryJdbcTemplate() {
        return new NamedParameterJdbcTemplate(dynamicDataSource);
    }


}
