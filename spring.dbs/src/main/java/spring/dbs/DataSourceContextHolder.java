package spring.dbs;

/**
 * Created by frinder6 on 2016/12/20.
 */
public class DataSourceContextHolder {

    private static final ThreadLocal<String> DATASOURCE_KEY = new ThreadLocal<>();

    public static void setDatasourceKey(String datasourceKey) {
        DATASOURCE_KEY.set(datasourceKey);
    }

    public static String getDatasourceKey() {
        return DATASOURCE_KEY.get();
    }

    public static void clearDatasourceKey() {
        DATASOURCE_KEY.remove();
    }

}
