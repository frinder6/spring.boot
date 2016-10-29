package spring.jdbc;

/**
 * Created on 2016/9/20.
 */
public interface IDao<T> {

    T findById(Long id, String sql, Class<T> mappedClass);

}
