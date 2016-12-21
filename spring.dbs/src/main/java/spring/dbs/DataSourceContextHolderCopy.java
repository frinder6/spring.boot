package spring.dbs;

/**
 * Created by frinder6 on 2016/12/20.
 */
public class DataSourceContextHolderCopy {

    private static final ThreadLocal<String> DATASOURCE_KEY = new ThreadLocal<>();

    public static void setDatasourceKey(String datasourceKey) {
        datasourceKey = Thread.currentThread().getName();
        System.out.println(datasourceKey + "  " + System.currentTimeMillis());
        DATASOURCE_KEY.set(datasourceKey);
    }

    public static String getDatasourceKey() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
        return DATASOURCE_KEY.get();
    }

    public static void clearDatasourceKey() {
        DATASOURCE_KEY.remove();
    }


    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            new Thread(() -> t()).start();
        }

    }

    public static void t() {
        setDatasourceKey("");
        System.out.println(getDatasourceKey());
        clearDatasourceKey();
    }

}
