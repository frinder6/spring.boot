package spring.mybatis;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import spring.jpa.User;

import java.util.List;

/**
 * Created by frinder6 on 2016/9/21.
 */
@Mapper
public interface UserMapper {

    @Select("select * from user where id = #{id}")
    User findById(@Param("id") Long id);

    @Select("select * from user where name = #{name}")
    List<User> findByName(@Param("name") String name);

}
