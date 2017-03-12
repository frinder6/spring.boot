package spring.config;

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
 * Created by frinder6 on 2017/3/2.
 */
@Component
@Aspect
public class RedisInterceptor {

    private static Logger logger = LoggerFactory.getLogger(RedisInterceptor.class);

    @Autowired
    private RedisLockService redisLockService;


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
        boolean locked = redisLockService.lock(lock.tableName(), lockValue.toString(), 0, lock.expireTime());
        if (!locked) {
            logger.error("锁定失败！");
            return null;
        }
        try {
            logger.info("*************************************************");
            logger.info("锁定成功，执行业务方法！");
            logger.info("*************************************************");
            return point.proceed();
        } finally {
            redisLockService.unlock(lock.tableName(), lockValue.toString());
        }
    }


}
