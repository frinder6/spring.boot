package spring.dbs;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.nio.charset.StandardCharsets;
import java.util.zip.CRC32;

/**
 * Created by frinder6 on 2016/12/20.
 */
@Component
@Aspect
public class DatasourceHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatasourceHandler.class);

    private static final String[] DATASOURCES = new String[]{"primaryDataSource", "secondaryDataSource", "thirdlyDataSource"};

    private static final String STRING = "java.lang.String";

    @Pointcut("execution(* spring.service..*(..))")
    public void datasourcePoint() {
    }

    @Around("datasourcePoint()")
    public Object handler2(ProceedingJoinPoint point) throws Throwable {
        LOGGER.info("begin get datasource !");
        /**
         * 获取当前执行的方法，并获取参数，根据注解判断出 datasource key
         */
        Method method = ((MethodSignature) point.getSignature()).getMethod();
        Parameter[] parameters = method.getParameters();
        if (null != parameters && parameters.length > 0) {
            Object[] args = point.getArgs();
            String aValue = "", sValue = "";
            int i = 0;
            for (Parameter parameter : parameters) {
                LOGGER.info("this arg's type is : " + parameter.getType());
                if (parameter.isAnnotationPresent(DatasourceKey.class)) {
                    aValue = String.valueOf(args[i]);
                    break;
                } else if (STRING.equalsIgnoreCase(parameter.getParameterizedType().getTypeName())) {
                    if (StringUtils.isEmpty(sValue)) {
                        sValue = String.valueOf(args[i]);
                    }
                } else {
                    LOGGER.info("but this arg is not datasource key !");
                }
                ++i;
            }
            String key;
            if (!StringUtils.isEmpty(aValue)) {
                key = aValue;
            } else if (!StringUtils.isEmpty(sValue)) {
                key = sValue;
            } else {
                key = "a"; // default 0
            }
            DataSourceContextHolder.setDatasourceKey(getKey(key));
        }
        Object result;
        try {
            result = point.proceed();
        } finally {
            // 清除key
            DataSourceContextHolder.clearDatasourceKey();
        }
        LOGGER.info("end get datasource !");
        return result;
    }

    /**
     * 获取key方法
     *
     * @param key
     * @return
     */
    private String getKey(String key) {
        LOGGER.info("this key is : [ " + key + " ]!");
        CRC32 crc = new CRC32();
        crc.update(key.getBytes(StandardCharsets.UTF_8));
        long result = crc.getValue();
        LOGGER.info("this key's crc32 is : [ " + result + " ]!");
        int index = (int) (result % 3);
        LOGGER.info("this key's module is : [ " + index + " ]!");
        int len = DATASOURCES.length;
        if (index > (len - 1)) {
            index = len - 1;
        }
        LOGGER.info("this key's result module is : [ " + index + " ]!");
        String datasourceKey = DATASOURCES[index];
        LOGGER.info("this datasource key is : [ " + datasourceKey + " ]!");
        return datasourceKey;
    }

    public static void main(String[] args) {
        System.out.println(new DatasourceHandler().getKey("abc"));
    }

}
