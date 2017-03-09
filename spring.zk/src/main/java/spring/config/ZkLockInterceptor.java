package spring.config;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by frinder6 on 2017/3/8.
 */
@Component
@Aspect
public class ZkLockInterceptor {

    private static Logger logger = LoggerFactory.getLogger(ZkLockInterceptor.class);

    @Autowired
    private CuratorFramework client;

    @Autowired
    private ZkLockClient zkLockClient;

    @Pointcut("execution(* spring.service..*(..))")
    public void redisPointcut() {
    }

    @Around("redisPointcut()")
    public Object handle(ProceedingJoinPoint point) throws Throwable {
        Method method = ((MethodSignature) point.getSignature()).getMethod();
        MethodLock lock = method.getAnnotation(MethodLock.class);
        if (null == lock) {
            return point.proceed();
        }
        Object lockValue = AopUtils.getLockValue(point);
        if (null == lockValue) {
            logger.error("锁定失败！");
            return null;
        }
        InterProcessReadWriteLock readWriteLock;
        InterProcessMutex writeLock = null;
        try {
            String path = "/" + point.getTarget().getClass().toString() + "." + method.getName();
            readWriteLock = zkLockClient.getLock(client, path);
            writeLock = readWriteLock.writeLock();
            writeLock.acquire();
            logger.info("*************************************************");
            logger.info("锁定成功，执行业务方法！");
            logger.info("*************************************************");
            return point.proceed();
        } finally {
            if (null != writeLock) {
                writeLock.release();
            }
        }
    }


}
