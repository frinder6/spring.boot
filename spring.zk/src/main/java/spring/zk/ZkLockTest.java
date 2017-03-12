package spring.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by frinder6 on 2017/3/8.
 */
public class ZkLockTest {

    //我们用一个static的map模拟一个第三方独立缓存
    public final static Map<String, Object> CACHE = new HashMap<>();
    public static final String key = "redisKey";

    public static void main(String[] args) throws InterruptedException {
        //创建俩个对象分别模拟2个进程
        RedisProcess processA = new RedisProcess();
        RedisProcess processB = new RedisProcess();

        //每个进程别分用50个线程并发请求
        ExecutorService service = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 50; i++) {
            service.execute(processA);
            service.execute(processB);
        }

        service.shutdown();
        service.awaitTermination(30, TimeUnit.SECONDS);
    }

    public static class RedisProcess implements Runnable {
        CuratorFramework client;
        //ZK分布式锁
        InterProcessMutex distributedLock;
        //JVM内部锁
        ReentrantLock jvmLock;

        public RedisProcess() {
            client = CuratorFrameworkFactory.newClient("10.199.195.228:2181", new ExponentialBackoffRetry(1000, 3));
            client.start();
            distributedLock = new InterProcessMutex(client, "/mylock", new ZkTest.NoFairLockDriver());
            jvmLock = new ReentrantLock();
        }

        @Override
        public void run() {
            //(1)首先判断缓存内资源是否存在
            if (CACHE.get(key) == null) {
                try {

                    //这里延时1000毫秒的目的是防止线程过快的更新资源，那么其它线程在步骤(1)处就返回true了.
                    Thread.sleep(1000);

                    //获取JVM锁(同一进程内有效)
                    jvmLock.lock();

                    //(2)再次判断资源是否已经存在
                    if (CACHE.get(key) == null) {
                        System.out.println("线程:" + Thread.currentThread() + "获取到JVM锁，CACHE.get(key)为空, 准备获取ZK锁");

                        //这里延时500毫秒的目的是防止线程过快更新资源，其它线程在步骤(2)就返回true了。
                        Thread.sleep(500);
                        try {
                            //获取zk分布式锁
                            distributedLock.acquire();
                            System.out.println("线程:" + Thread.currentThread() + "获取到JVM锁，CACHE.get(key)为空, 获取到了ZK锁");

                            //再次判断,如果为空这时可以更新资源
                            if (CACHE.get(key) == null) {
                                CACHE.put(key, Thread.currentThread() + "更新了缓存");
                                System.out.println("线程:" + Thread.currentThread() + "更新了缓存");
                            } else {
                                System.out.println("线程:" + Thread.currentThread() + "当前资源已经存在，不需要更新");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            //释放ZK锁
                            try {
                                distributedLock.release();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        System.out.println("线程:" + Thread.currentThread() + "获取到JVM锁，CACHE.get(key)不为空," + CACHE.get(key));
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //释放JVM锁
                    jvmLock.unlock();
                }
            } else {
                System.out.println(CACHE.get(key));
            }
        }
    }

}
