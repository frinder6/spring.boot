package spring.zk;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.StandardLockInternalsDriver;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.util.Random;

/**
 * Created by frinder6 on 2017/3/7.
 */
public class ZkTest {

    public static void main(String[] args) throws Exception {
//        t1();
        t2();
    }

    static void t1() throws Exception {
        //操作失败重试机制 1000毫秒间隔 重试3次
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        //创建Curator客户端
        CuratorFramework client = CuratorFrameworkFactory.newClient("10.199.195.228:2181", retryPolicy);
        //开始
        client.start();

        /**
         * 这个类是线程安全的，一个JVM创建一个就好
         * mylock 为锁的根目录，我们可以针对不同的业务创建不同的根目录
         */
        final InterProcessMutex lock = new InterProcessMutex(client, "/mylock");
        try {
            //阻塞方法，获取不到锁线程会挂起。
            lock.acquire();
            System.out.println("已经获取到锁");
            Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放锁，必须要放到finally里面，已确保上面方法出现异常时也能够释放锁。
            lock.release();
        }

        Thread.sleep(10000);

        client.close();
    }


    static void t2() throws Exception {
        for (int i = 0; i < 10; i++) {
            //启动10个线程模拟多个客户端
            Jvmlock jl = new Jvmlock(i);
            new Thread(jl).start();
            //这里加上500毫秒是为了让线程按顺序启动，不然有可能4号线程比3号线程先启动了，这样测试就不准了。
            Thread.sleep(500);
        }
    }


    static class Jvmlock implements Runnable {

        private int num;

        public Jvmlock(int num) {
            this.num = num;
        }

        @Override
        public void run() {
            RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
            CuratorFramework client = CuratorFrameworkFactory.newClient("10.199.195.228:2181", retryPolicy);
            client.start();

            InterProcessMutex lock = new InterProcessMutex(client, "/mylock");
//            InterProcessMutex lock = new InterProcessMutex(client, "/mylock", new NoFairLockDriver());

            try {
                System.out.println("我是第" + num + "号线程，我开始获取锁");
                lock.acquire();
                System.out.println("我是第" + num + "号线程，我已经获取锁");
                Thread.sleep(10000);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    lock.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            client.close();
        }
    }


    static class NoFairLockDriver extends StandardLockInternalsDriver {

        /**
         * 随机数的长度
         */
        private int numLength;
        private static int DEFAULT_LENGTH = 5;

        public NoFairLockDriver() {
            this(DEFAULT_LENGTH);
        }

        public NoFairLockDriver(int numLength) {
            this.numLength = numLength;
        }

        @Override
        public String createsTheLock(CuratorFramework client, String path, byte[] lockNodeBytes) throws Exception {
            String newPath = path + getRandomSuffix();
            String ourPath;
            if (lockNodeBytes != null) {
                //原来使用的是CreateMode.EPHEMERAL_SEQUENTIAL类型的节点
                //节点名称最终是这样的_c_c8e86826-d3dd-46cc-8432-d91aed763c2e-lock-0000000025
                //其中0000000025是zook服务器端资自动生成的自增序列 从0000000000开始
                //所以每个客户端创建节点的顺序都是按照0，1，2，3这样递增的顺序排列的，所以他们获取锁的顺序与他们进入的顺序是一致的，这也就是所谓的公平锁
                //现在我们将有序的编号换成随机的数字，这样在获取锁的时候变成非公平锁了
                ourPath = client.create().creatingParentContainersIfNeeded().withProtection().withMode(CreateMode.EPHEMERAL).forPath(newPath, lockNodeBytes);
                //ourPath = client.create().creatingParentContainersIfNeeded().withProtection().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath(path, lockNodeBytes);
            } else {
                ourPath = client.create().creatingParentContainersIfNeeded().withProtection().withMode(CreateMode.EPHEMERAL).forPath(newPath);
                //ourPath = client.create().creatingParentContainersIfNeeded().withProtection().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath(path);
            }
            return ourPath;
        }

        /**
         * 获得随机数字符串
         */
        public String getRandomSuffix() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < numLength; i++) {
                sb.append(new Random().nextInt(10));
            }
            return sb.toString();
        }
    }

}
