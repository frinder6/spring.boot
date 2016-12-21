package spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.dao.IDao;
import spring.dao.SqlEntity;
import spring.dao.SqlFormatException;
import spring.entity.SpringTable;
import spring.service.TestService;

import java.util.List;

/**
 * Created by frinder6 on 2016/12/20.
 */
@Service("testService")
public class TestServiceImpl implements TestService<SpringTable> {

    @Autowired
    private IDao<SpringTable> springTableIDao;

    @Override
    public long persist(String key, SpringTable entity) throws SqlFormatException {
        return springTableIDao.persist(setEntity(entity, entity));
    }

    @Override
    public int delete(String key, SpringTable whereEntity) throws SqlFormatException {
        return springTableIDao.delete(setEntity(whereEntity, whereEntity));
    }

    @Override
    public int modify(String key, SpringTable entity, SpringTable whereEntity) throws SqlFormatException {
        return springTableIDao.modify(setEntity(entity, whereEntity));
    }

    @Override
    public List<SpringTable> selectForList(String key, SpringTable whereEntity) throws SqlFormatException {
        return springTableIDao.selectForList(setEntity(whereEntity, whereEntity));
    }

    @Override
    public SpringTable selectForSingle(String key, SpringTable whereEntity) throws SqlFormatException {
        return springTableIDao.selectForSingle(setEntity(whereEntity, whereEntity));
    }

    /**
     * @param entity
     * @return
     */
    private SqlEntity<SpringTable> setEntity(SpringTable entity, SpringTable whereEntity) {
        SqlEntity<SpringTable> sqlEntity = new SqlEntity<>();
        sqlEntity.setEntity(entity);
        sqlEntity.setWhereEntity(whereEntity);
        sqlEntity.setEntityType(SpringTable.class);
        return sqlEntity;
    }
}
