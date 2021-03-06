package spring.config;

/**
 * Created by frinder6 on 2017/3/3.
 */
public interface RedisLockService {

    boolean lock(String prefix, String key, long timeout, int expire);

    void unlock(String prefix, String key);

}
