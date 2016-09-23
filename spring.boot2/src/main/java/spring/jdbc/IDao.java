package spring.jdbc;

/**
 * Created by frinder6 on 2016/9/20.
 */
public interface IDao<T> {

    T findById(Long id, String sql, Class<T> mappedClass);

}
