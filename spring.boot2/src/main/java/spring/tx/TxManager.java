package spring.tx;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by frinder6 on 2016/9/21.
 */
@Configuration
@ImportResource("classpath*:application-tx.xml")
public class TxManager {
}
