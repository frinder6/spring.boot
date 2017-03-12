package spring.dao;

import com.google.common.base.CaseFormat;
import org.dozer.DozerBeanMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created on 2016/8/1.
 */
public class JdbcTemplate<T> {

    private JdbcTemplate() {
    }

    public static class SingleFactory {
        public static final JdbcTemplate SINGLE = new JdbcTemplate();
    }


    /**
     * 下划线转驼峰格式
     *
     * @param resource
     * @return
     */
    public String convert2Camel(String resource) {
        return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, resource);
    }


    /**
     * @param entity
     * @return
     * @throws SqlFormatException
     */
    public String getInsertSql(SqlEntity<T> entity) throws SqlFormatException {
        String insertSql = "INSERT INTO %s ( %s ) VALUES ( %s )";
        StringBuilder insertCols = new StringBuilder();
        StringBuilder insertValues = new StringBuilder();
        Set<String> feilds = getFeilds(entity.getEntity(), true);
        if (!feilds.isEmpty()) {
            int i = 0;
            for (String col : feilds) {
                if (i == 0) {
                    insertCols.append(escape(col));
                    insertValues.append(":" + convert2Camel(col));
                } else {
                    insertCols.append(", ".concat(escape(col)));
                    insertValues.append(", :" + convert2Camel(col));
                }
                ++i;
            }
            // insertCols.append(", create_time");
            // insertValues.append(", NOW()");
        } else {
            throw new SqlFormatException(insertSql);
        }
        return String.format(insertSql, entity.getTableName(), insertCols, insertValues);
    }


    /**
     * @param tableName
     * @return
     */
    public String getDeleteSql(String tableName) {
        String deleteSql = "DELETE FROM %s WHERE id = :id";
        return String.format(deleteSql, tableName);
    }

    /**
     * @param entity
     * @return
     */
    public String getWheresDeleteSql(SqlEntity<T> entity) {
        String deleteSql = "DELETE FROM %s WHERE 1 != 1 %s";
        return String.format(deleteSql, entity.getTableName(), getWheres(entity.getWhereEntity()));
    }

    /**
     * @param entity
     * @return
     * @throws SqlFormatException
     */
    public String getUpdateSql(SqlEntity<T> entity) throws SqlFormatException {
        String updateSql = "UPDATE %s SET %s WHERE id = :id";
        StringBuilder updateValues = new StringBuilder();
        Set<String> feilds = getFeilds(entity.getEntity(), true);
        if (!feilds.isEmpty()) {
            int i = 0;
            for (String col : feilds) {
                if ("id".equalsIgnoreCase(col)) {
                    continue;
                }
                if (i == 0) {
                    updateValues.append(escape(col) + " = :" + convert2Camel(col));
                } else {
                    updateValues.append(", " + escape(col) + " = :" + convert2Camel(col));
                }
                ++i;
            }
        } else {
            throw new SqlFormatException(updateSql);
        }
        return String.format(updateSql, entity.getTableName(), updateValues);
    }


    /**
     * @param entity
     * @return
     */
    public String getSelectSql(SqlEntity<T> entity) {
        String selectSql = "SELECT * FROM %s WHERE 1 != 1 %s";
        StringBuilder selectValues = new StringBuilder();
        Set<String> feilds = getFeilds(entity.getEntity(), true);
        if (!feilds.isEmpty()) {
            int i = 0;
            for (String col : feilds) {
                if (i == 0) {
                    selectValues.append(escape(col) + " AS " + escape(convert2Camel(col)));
                } else {
                    selectValues.append(", ".concat(escape(col)) + " AS " + escape(convert2Camel(col)));
                }
                ++i;
            }
        }
        return String.format(selectSql, entity.getTableName(), getWheres(entity.getWhereEntity()));
    }


    /**
     * @param entity
     * @return
     */
    public String getWheres(T entity) {
        StringBuilder whereValues = new StringBuilder();
        Set<String> feilds = getFeilds(entity, true);
        if (!feilds.isEmpty()) {
            whereValues.append(" OR ( ");
            int i = 0;
            for (String col : feilds) {
                if (i == 0) {
                    whereValues.append(escape(col) + " = :" + convert2Camel(col));
                } else {
                    whereValues.append(" AND " + escape(col) + " = :" + convert2Camel(col));
                }
                ++i;
            }
            whereValues.append(" )");
        } else {
            whereValues.append(" OR ( 1 = 1 )");
        }
        return whereValues.toString();
    }


    /**
     * 类转换为Map
     *
     * @param entity
     * @param excludeNull
     * @return
     */
    private Map<String, Object> convert2Map(T entity, boolean excludeNull) {
        DozerBeanMapper beanMapper = new DozerBeanMapper();
        Map<String, Object> resultMap = beanMapper.map(entity, Map.class);
        if (excludeNull) {
            Map<String, Object> copyMap = new HashMap<>(resultMap);
            copyMap.entrySet().forEach((Map.Entry<String, Object> e) -> {
                if (null == e.getValue()) {
                    resultMap.remove(e.getKey());
                }
            });
        }
        return resultMap;
    }

    /**
     * 获取类属性
     *
     * @param entity
     * @param excludeNull
     * @return
     */
    private Set<String> getFeilds(T entity, boolean excludeNull) {
        return convert2Map(entity, excludeNull).keySet();
    }


    /**
     * 列转义
     *
     * @param col
     * @return
     */
    private String escape(String col) {
        return "`".concat(col).concat("`");
    }

}