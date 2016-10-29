package spring.jdbc;

import spring.jpa.User;

/**
 * Created on 2016/9/20.
 */
public interface UserService {

    User findById(Long id);

}
