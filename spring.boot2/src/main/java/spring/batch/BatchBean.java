package spring.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.PlatformTransactionManager;
import spring.jpa.User;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created on 2016/10/17.
 */
@Configuration
@EnableBatchProcessing
public class BatchBean {

    @Autowired
    @Qualifier("primaryDataSource")
    private DataSource primaryDataSource;

    @Autowired
    @Qualifier("secondaryDataSource")
    private DataSource secondaryDataSource;


    @StepScope
    @Bean
    public PagingQueryProvider pagingQueryProvider() throws Exception {
        SqlPagingQueryProviderFactoryBean factoryBean = new SqlPagingQueryProviderFactoryBean();
        factoryBean.setDataSource(primaryDataSource);
        factoryBean.setSelectClause("SELECT id, `name`, age, remark, sex");
        factoryBean.setFromClause("FROM `user`");
        factoryBean.setWhereClause("WHERE (crc32(upper(`name`)) % 3) = :mod");
        factoryBean.setSortKey("id");
        return factoryBean.getObject();
    }

    @StepScope
    @Bean
    public ItemReader<User> itemReader(PagingQueryProvider pagingQueryProvider,
                                       @Value("#{jobParameters['mod']}") Long mod)
            throws Exception {
        JdbcPagingItemReader<User> reader = new JdbcPagingItemReader<>();
        reader.setDataSource(primaryDataSource);
        reader.setQueryProvider(pagingQueryProvider);
        // reader 参数传递
        reader.setParameterValues(new HashMap(){{
            put("mod", mod);
        }});
        reader.setRowMapper(new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setAge(resultSet.getInt("age"));
                user.setRemark(resultSet.getString("remark"));
                user.setSex(resultSet.getString("sex"));
                return user;
            }
        });
        reader.setPageSize(300);
        return reader;
    }

    @StepScope
    @Bean
    public ItemWriter<User> itemWriter() {
        JdbcBatchItemWriter<User> writer = new JdbcBatchItemWriter<>();
        writer.setDataSource(secondaryDataSource);
        writer.setSql("INSERT INTO `user` (`age`, `name`, `remark`, `sex`) " +
                "VALUES (:age, :name, :remark, :sex)");
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        return writer;
    }

    @StepScope
    @Bean
    public ItemProcessor<User, User> batchProcessor() {
        return new BatchProcessor();
    }


    @Bean
    public Step step(StepBuilderFactory stepBuilderFactory,
                     ItemReader<User> itemReader,
                     ItemWriter<User> itemWriter,
                     PlatformTransactionManager transactionManager) throws Exception {
        return stepBuilderFactory.get("step")
                .<User, User>chunk(100)
                .reader(itemReader)
                .processor(batchProcessor())
                .writer(itemWriter)
                .faultTolerant()
                .skip(Exception.class)
                .throttleLimit(3)
                .taskExecutor(new SimpleAsyncTaskExecutor())
                .transactionManager(transactionManager)
                .build();
    }


    @Bean
    public Job testJob(JobBuilderFactory jobs, Step step, JobRepository jobRepository) {
        return jobs.get("testJob")
                .incrementer(new RunIdIncrementer())
                .repository(jobRepository)
                .flow(step)
                .end()
                .build();
    }

}
