package spring.batch;

import org.springframework.batch.item.ItemProcessor;
import spring.jpa.User;

/**
 * Created on 2016/10/17.
 */
public class BatchProcessor implements ItemProcessor<User, User> {

    @Override
    public User process(User user) throws Exception {
        return user;
    }

}
