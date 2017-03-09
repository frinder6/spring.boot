package spring.config;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by frinder6 on 2017/3/2.
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EntityLock {

    String field() default ""; //含有成员变量的复杂对象中需要加锁的成员变量，如一个商品对象的商品ID

}
