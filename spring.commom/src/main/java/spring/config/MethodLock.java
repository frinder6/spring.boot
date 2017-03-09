package spring.config;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by frinder6 on 2017/3/2.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MethodLock {

    /**
     * 0 即永不超时
     *
     * @return
     */
    long timeOut() default 2000; // 轮询锁的时间

    int expireTime() default 1; // key在redis里存在的时间，1000S

}
