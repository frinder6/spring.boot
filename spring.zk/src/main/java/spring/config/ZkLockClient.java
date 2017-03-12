package spring.config;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;
import org.springframework.stereotype.Component;

/**
 * Created by frinder6 on 2017/3/8.
 */
@Component
public class ZkLockClient {

    /**
     * 获取 read/write lock
     *
     * @param client
     * @param path
     * @return
     */
    public InterProcessReadWriteLock getLock(CuratorFramework client, String path) {
        return new InterProcessReadWriteLock(client, path);
    }


    /**
     * 获取 read lock
     *
     * @param client
     * @param path
     * @return
     */
    public InterProcessMutex getReadLock(CuratorFramework client, String path) {
        InterProcessReadWriteLock lock = getLock(client, path);
        return lock.readLock();
    }

    /**
     * 获取 write lock
     *
     * @param client
     * @param path
     * @return
     */
    public InterProcessMutex getWriteLock(CuratorFramework client, String path) {
        InterProcessReadWriteLock lock = getLock(client, path);
        return lock.writeLock();
    }


}
