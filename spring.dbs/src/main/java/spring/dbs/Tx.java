package spring.dbs;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by frinder6 on 2016/12/20.
 */
@Configuration
@ImportResource("classpath*:application-tx.xml")
public class Tx {
}
