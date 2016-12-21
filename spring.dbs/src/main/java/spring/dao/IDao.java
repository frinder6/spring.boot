package spring.dao;

import java.util.List;

/**
 * Created on 2016/8/1.
 */
public interface IDao<T> {

    long persist(SqlEntity<T> entity) throws SqlFormatException;

    int delete(SqlEntity<T> entity) throws SqlFormatException;

    int deleteWheres(SqlEntity<T> entity) throws SqlFormatException;

    int modify(SqlEntity<T> entity) throws SqlFormatException;

    List<T> selectForList(SqlEntity<T> entity) throws SqlFormatException;

    T selectForSingle(SqlEntity<T> entity) throws SqlFormatException;

}
