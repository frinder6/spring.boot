package spring.config;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;
import org.springframework.stereotype.Component;

/**
 * Created by frinder6 on 2017/3/8.
 */
@Component
public class ZkLockClient {

    public InterProcessReadWriteLock getLock(CuratorFramework client, String path) {
        return new InterProcessReadWriteLock(client, path);
    }


}
