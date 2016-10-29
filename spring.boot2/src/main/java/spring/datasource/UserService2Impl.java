package spring.datasource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import spring.jpa.User;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created on 2016/9/23.
 */
//@Service("userService2")
public class UserService2Impl implements UserService2 {

    @Resource(name = "primaryJdbcTemplate")
    private NamedParameterJdbcTemplate primaryJdbcTemplate;

    @Resource(name = "secondaryJdbcTemplate")
    private NamedParameterJdbcTemplate secondaryJdbcTemplate;

    public User findByIdPri(final long id) {
        return findById(id, primaryJdbcTemplate);
    }

    public User findByIdSec(final long id) {
        return findById(id, secondaryJdbcTemplate);
    }

    private User findById(final long id, NamedParameterJdbcTemplate template) {
        Map<String, Object> params = new HashMap<String, Object>() {{
            put("id", id);
        }};
        String sql = "select * from user where id = :id";
        return template.queryForObject(sql, params, new BeanPropertyRowMapper<User>(User.class));
    }

}
