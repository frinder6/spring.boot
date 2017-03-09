package spring.config;

import javassist.bytecode.SignatureAttribute;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * Created by frinder6 on 2017/3/8.
 */
public class AopUtils {

    private static Logger logger = LoggerFactory.getLogger(AopUtils.class);

    /**
     * 获取需要加锁的属性
     *
     * @param point
     * @return
     */
    public static Object getLockValue(ProceedingJoinPoint point) throws Exception {
        Object[] values = point.getArgs();
        Object result = null;
        if (null == values || values.length == 0) {
            logger.error("方法参数为空，不能执行锁定操作，请确认！");
            return result;
        }
        Method method = ((MethodSignature) point.getSignature()).getMethod();
        Parameter[] parameters = method.getParameters();
        int i = 0;
        for (Parameter parameter : parameters) {
            // 简单参数
            if (parameter.isAnnotationPresent(ParameterLock.class)) {
                result = values[i];
                break;
            }
            // 复杂参数，自定义参数等
            if (parameter.isAnnotationPresent(EntityLock.class)) {
                EntityLock lock = parameter.getAnnotation(EntityLock.class);
                try {
                    result = values[i].getClass().getField(lock.field());
                } catch (NoSuchFieldException e) {
                    logger.error(String.format("类 [ %s ] 不包含属性：[ %s ]", parameter.getParameterizedType(), lock.field()));
                }
                break;
            }
            ++i;
        }
        if (null == result) {
            logger.error("未获取到执行锁定操作的 key，不能执行锁定操作，请确认！");
        }
        return result;
    }

}
