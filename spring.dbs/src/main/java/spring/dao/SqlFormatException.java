package spring.dao;

/**
 * Created by frinder_liu on 2016/8/4.
 */
public class SqlFormatException extends Exception {

    public SqlFormatException(String sql) {
        super("the sql : [" + sql + " ] format doesn't comfort, check it please !");
    }
}
