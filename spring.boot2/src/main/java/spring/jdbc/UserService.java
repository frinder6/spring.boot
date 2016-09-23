package spring.jdbc;

import spring.jpa.User;

/**
 * Created by frinder6 on 2016/9/20.
 */
public interface UserService {

    User findById(Long id);

}
