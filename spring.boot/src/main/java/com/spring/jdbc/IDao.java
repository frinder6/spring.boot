package com.spring.jdbc;

import java.util.List;

/**
 * Created by frinder6 on 2016/9/12.
 */
public interface IDao<T> {

    void persist(T entity);

    int modify(long id, T entity);

    int remove(long id);

    List<T> query(T entity);

}
