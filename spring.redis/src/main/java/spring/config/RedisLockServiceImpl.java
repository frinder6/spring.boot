package spring.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Random;

/**
 * Created by frinder6 on 2017/3/2.
 */
@Service("redisLockService")
public class RedisLockServiceImpl implements RedisLockService {

    private Logger logger = LoggerFactory.getLogger(RedisLockServiceImpl.class);

    public static final long MILLI_NANO_TIME = 1000 * 1000L;

    public static volatile long error_count = 0;

    private ThreadLocal<Boolean> _lock = new ThreadLocal<>();


    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisClient redisClient;

    @Override
    public boolean lock(String prefix, String key, long timeout, int expire) {
        long nanoTime = System.nanoTime();
        timeout *= MILLI_NANO_TIME;
        key = getKey(prefix, key);
        try {
            long count = 0;
            while (timeout == 0 || (System.nanoTime() - nanoTime) < timeout) {
                if (redisClient.setnx(key, "TRUE") == 1) {
                    redisClient.expire(key, expire);
                    _lock.set(true);
                    logger.info(String.format("根据 [ %s ] 锁定成功！", key));
                    return true;
                }
//                ++error_count;
                ++count;
                logger.warn(String.format("根据 [ %s ] 锁定失败, 这是第 [ %s ] 次尝试！", key, count));
                Thread.sleep(3, new Random().nextInt(30));
            }
        } catch (Exception e) {
            logger.error(String.format("根据 [ %s ] 锁定失败！", key), e);
        }
        return false;
    }


    /**
     * 构建 key
     *
     * @param key
     * @return
     */
    private String getKey(String prefix, String key) {
        return ("redis_" + prefix + "_" + key + "_lock").toUpperCase();
    }


    /**
     * 释放锁
     *
     * @param key
     */
    @Override
    public void unlock(String prefix, String key) {
        if (_lock.get()) {
            key = getKey(prefix, key);
            redisClient.delKey(key);
            _lock.remove();
        }
    }

    /**
     * 加锁
     *
     * @param key
     * @param timeout
     * @param expire
     * @return
     */
    @Deprecated
    public boolean _lock(String prefix, String key, long timeout, int expire) {
        long nanoTime = System.nanoTime();
        long _timeout = timeout * MILLI_NANO_TIME;
        RedisSerializer serializer = redisTemplate.getKeySerializer();
        byte[] k = serializer.serialize(getKey(prefix, key));
        // 锁不存在的话，设置锁并设置锁过期时间，即加锁
        List<Object> results = redisTemplate.executePipelined((RedisConnection connection) -> {
            byte[] v = redisTemplate.getValueSerializer().serialize("TRUE");
            long count = 0;
            // 在timeout的时间范围内不断轮询锁
            try {
                while ((System.nanoTime() - nanoTime) < _timeout) {
                    boolean result = connection.setNX(k, v);
                    if (result) {
                        connection.expire(k, expire);
                        _lock.set(true);
                        return "1";
                    }
                    ++error_count;
                    ++count;
                    logger.warn(String.format("根据 [ %s ] 锁定失败, 这是第 [ %s ] 次尝试！", key, count));
                    Thread.sleep(5);
                }
            } catch (InterruptedException e) {
                logger.error(String.format("根据 [ %s ] 锁定失败！", key));
            }
            return "0";
        });
        if (!CollectionUtils.isEmpty(results)) {
            if ("1".equalsIgnoreCase(String.valueOf(results.get(0)))) {
                logger.info(String.format("根据 [ %s ] 锁定成功！", key));
                return true;
            }
        }
        return false;
    }

}
