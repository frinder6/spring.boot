package spring.dbs;

/**
 * Created by frinder6 on 2016/12/20.
 */
public class DataSourceContextHolder {

    private static final ThreadLocal<String> DATASOURCE_KEY = new ThreadLocal<>();

    /**
     * 设置 datasourceKey
     *
     * @param datasourceKey
     */
    public static void setDatasourceKey(String datasourceKey) {
        DATASOURCE_KEY.set(datasourceKey);
    }

    /**
     * 获取 datasourceKey
     *
     * @return
     */
    public static String getDatasourceKey() {
        return DATASOURCE_KEY.get();
    }

    /**
     * 删除 datasourceKey
     */
    public static void clearDatasourceKey() {
        DATASOURCE_KEY.remove();
    }

}
