package spring.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.jpa.User;

/**
 * Created on 2016/9/20.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private IDao<User> baseDao;

    public User findById(Long id) {
        String sql = "select * from user where id = :id";
        return baseDao.findById(id, sql, User.class);
    }

}
