package spring.service;

import spring.dao.SqlFormatException;

import java.util.List;

/**
 * Created by frinder6 on 2016/12/20.
 */
public interface TestService<SpringTable> {

    long persist(String key, SpringTable entity) throws SqlFormatException;

    int delete(String key, SpringTable entity) throws SqlFormatException;

    int modify(String key, SpringTable entity, SpringTable whereEntity) throws SqlFormatException;

    List<SpringTable> selectForList(String key, SpringTable entity) throws SqlFormatException;

    SpringTable selectForSingle(String key, SpringTable entity) throws SqlFormatException;

}
