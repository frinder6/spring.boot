package spring.dbs;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

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

    @Pointcut("execution(* spring.service..*(..))")
    public void datasourcePoint() {
    }

    @Around("datasourcePoint()")
    public Object handler(ProceedingJoinPoint point) throws Throwable {
        LOGGER.info("begin get datasource......");
        Object[] args = point.getArgs();
        if (null != args && args.length > 0) {
            String key = String.valueOf(args[0]);
            // 存入key
            DataSourceContextHolder.setDatasourceKey(getKey(key));
        }
        Object result = point.proceed();
        // 清除key
        DataSourceContextHolder.clearDatasourceKey();
        LOGGER.info("end get datasource......");
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
