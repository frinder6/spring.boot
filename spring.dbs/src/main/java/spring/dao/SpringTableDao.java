package spring.dao;

import org.springframework.stereotype.Repository;
import spring.entity.SpringTable;

import java.util.List;

/**
 * Created by frinder6 on 2016/12/21.
 */
@Repository
public class SpringTableDao extends BaseDao<SpringTable> {

    private final String tableName = "spring_table";

    @Override
    public long persist(SqlEntity<SpringTable> entity) throws SqlFormatException {
        setValue(entity, tableName);
        return super.persist(entity);
    }

    @Override
    public int delete(SqlEntity<SpringTable> entity) {
        setValue(entity, tableName);
        return super.delete(entity);
    }

    @Override
    public int modify(SqlEntity<SpringTable> entity) throws SqlFormatException {
        setValue(entity, tableName);
        return super.modify(entity);
    }

    @Override
    public List<SpringTable> selectForList(SqlEntity<SpringTable> entity) throws SqlFormatException {
        setValue(entity, tableName);
        return super.selectForList(entity);
    }

    @Override
    public SpringTable selectForSingle(SqlEntity<SpringTable> entity) throws SqlFormatException {
        setValue(entity, tableName);
        return super.selectForSingle(entity);
    }
}
