package spring.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.MapPropertySource;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created on 2016/10/14.
 */
@Configuration
public class RedisClusterBean {

    @Value("${spring.redis.cluster.nodes}")
    private String nodes;

    @Value("${spring.redis.cluster.max-redirects}")
    private int redirects;

    @Bean
    RedisClusterConfiguration redisClusterConfiguration(){
        Map<String, Object> map = new HashMap(){{
            put("spring.redis.cluster.nodes", nodes);
            put("spring.redis.cluster.max-redirects", redirects);
        }};
        return new RedisClusterConfiguration(new MapPropertySource("RedisClusterConfiguration", map));
    }

    @Bean
    JedisConnectionFactory clusterJedisConnectionFactory() {
        return new JedisConnectionFactory(redisClusterConfiguration());
    }

    @Bean
    public RedisTemplate<String, Object> clusterRedisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(clusterJedisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new Jackson2JsonRedisSerializer(Object.class));
        return template;
    }


}
