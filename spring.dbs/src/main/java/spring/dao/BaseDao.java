package spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created on 2016/8/1.
 */

@Repository
public class BaseDao<T> implements IDao<T> {

    @Autowired
    private NamedParameterJdbcTemplate dynamicJdbcTemplate;

    /**
     * @param entity
     * @return
     * @throws SqlFormatException
     */
    @Override
    public long persist(SqlEntity<T> entity) throws SqlFormatException {
        String sql = JdbcUtil.SingleFactory.SINGLE.getInsertSql(entity);
        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(entity.getEntity());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        this.dynamicJdbcTemplate.update(sql, paramSource, keyHolder);
        return keyHolder.getKey().longValue();
    }


    /**
     * @param entity
     * @return
     */
    @Override
    public int delete(SqlEntity<T> entity) {
        String sql = JdbcUtil.SingleFactory.SINGLE.getDeleteSql(entity.getTableName());
        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(entity.getEntity());
        return this.dynamicJdbcTemplate.update(sql, paramSource);
    }


    /**
     * @param entity
     * @return
     */
    @Override
    public int deleteWheres(SqlEntity<T> entity) throws SqlFormatException {
        String sql = JdbcUtil.SingleFactory.SINGLE.getWheresDeleteSql(entity);
        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(entity.getEntity());
        return this.dynamicJdbcTemplate.update(sql, paramSource);
    }


    /**
     * @param entity
     * @return
     * @throws SqlFormatException
     */
    @Override
    public int modify(SqlEntity<T> entity) throws SqlFormatException {
        String sql = JdbcUtil.SingleFactory.SINGLE.getUpdateSql(entity);
        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(entity.getEntity());
        return this.dynamicJdbcTemplate.update(sql, paramSource);
    }


    /**
     * @param entity
     * @return
     * @throws SqlFormatException
     */
    @Override
    public List<T> selectForList(SqlEntity<T> entity) throws SqlFormatException {
        String sql = JdbcUtil.SingleFactory.SINGLE.getSelectSql(entity);
        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(entity.getEntity());
        return (List<T>) this.dynamicJdbcTemplate.query(sql, paramSource, new BeanPropertyRowMapper(entity.getEntity().getClass()));
    }


    /**
     * @param entity
     * @return
     * @throws SqlFormatException
     */
    @Override
    public T selectForSingle(SqlEntity<T> entity) throws SqlFormatException {
        String sql = JdbcUtil.SingleFactory.SINGLE.getSelectSql(entity);
        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(entity.getEntity());
        return (T) this.dynamicJdbcTemplate.queryForObject(sql, paramSource, new BeanPropertyRowMapper(entity.getEntity().getClass()));
    }


    /**
     * @param entity
     * @param tableName
     */
    public void setValue(SqlEntity<T> entity, String tableName) {
        entity.setTableName(tableName);
    }

}
