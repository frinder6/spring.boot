package spring.dao;

import lombok.Data;

/**
 * Created by frinder_liu on 2016/8/13.
 */

@Data
public class SqlEntity<T> {

    /**
     * 表名
     */
    private String tableName;


    /**
     * 实体
     */
    private T entity;

    /**
     * 条件实体
     */
    private T whereEntity;


    /**
     * 实体类型
     */
    private Class<T> entityType;

}
