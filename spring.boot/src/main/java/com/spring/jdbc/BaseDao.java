package com.spring.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by frinder6 on 2016/9/12.
 */

@Repository("baseDao")
public class BaseDao<T> implements IDao<T> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    public void persist(T entity) {

    }

    public int modify(long id, T entity) {
        return 0;
    }

    public int remove(long id) {
        return 0;
    }

    public List<T> query(T entity) {
        return null;
    }
}
