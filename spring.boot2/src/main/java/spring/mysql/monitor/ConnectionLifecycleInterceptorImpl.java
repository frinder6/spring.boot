package spring.mysql.monitor;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ConnectionLifecycleInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.Properties;

/**
 * Created by frinder6 on 2016/9/24.
 */
public class ConnectionLifecycleInterceptorImpl implements ConnectionLifecycleInterceptor {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private Connection conn;
    
    public void init(Connection conn, Properties props) throws SQLException {
        logger.debug("conn {}:init", conn);
        this.conn = conn;
    }

    public void destroy() {
        logger.debug("conn {}:destroy", conn);
    }

    public void close() throws SQLException {
        logger.debug("conn {}:close", conn);
    }

    public boolean commit() throws SQLException {
        logger.debug("conn {}:commit", conn);
        return true;
    }

    public boolean rollback() throws SQLException {
        logger.debug("conn {}:rollback", conn);
        return true;
    }

    public boolean rollback(Savepoint s) throws SQLException {
        logger.debug("conn {}:rollback Savepoint:{}", conn, s);
        return true;
    }

    public boolean setAutoCommit(boolean flag) throws SQLException {
        logger.debug("conn {}:setAutoCommit={}", conn, flag);
        return true;
    }

    public boolean setCatalog(String catalog) throws SQLException {
        logger.debug("conn {}:setCatalog={}", conn, catalog);
        return true;
    }

    public boolean transactionBegun() throws SQLException {
        logger.debug("conn {}:transactionBegun", conn);
        return true;
    }

    public boolean transactionCompleted() throws SQLException {
        logger.debug("conn {}:transactionCompleted", conn);
        return true;
    }
}
