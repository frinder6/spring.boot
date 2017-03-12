package spring.config;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by frinder6 on 2017/3/8.
 */
@Configuration
public class ZkLockBean {

    @Bean
    public RetryPolicy retryPolicy() {
        return new ExponentialBackoffRetry(100, 9);
    }

    @Bean
    public CuratorFramework curatorFramework() {
        CuratorFramework client = CuratorFrameworkFactory.newClient("10.199.195.228:2181", retryPolicy());
        client.start();
        return client;
    }

}
