package spring.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by frinder6 on 2016/9/20.
 */
@Repository
public class BaseDao<T> implements IDao<T> {

    @Autowired
    private NamedParameterJdbcTemplate primaryJdbcTemplate;

    public T findById(final Long id, String sql, Class<T> mappedClass) {
        Map<String, Object> params = new HashMap<String, Object>() {{
            put("id", id);
        }};
        return primaryJdbcTemplate.queryForObject(sql, params, new BeanPropertyRowMapper<T>(mappedClass));
    }

}
