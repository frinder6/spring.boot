package spring.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by frinder6 on 2016/9/19.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("from User u where u.name = :name")
    List<User> findByName(@Param("name") String name);

}
