package spring.datasource;

import spring.jpa.User;

/**
 * Created on 2016/9/23.
 */
public interface UserService2 {


    User findByIdPri(final long id);

    User findByIdSec(final long id);

}
