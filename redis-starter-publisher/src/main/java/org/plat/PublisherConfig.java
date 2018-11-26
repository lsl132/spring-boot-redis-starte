package org.plat;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class PublisherConfig {

    @Bean
    public StringRedisTemplate template(RedisConnectionFactory redisConnectionFactory) {
        StringRedisTemplate srt = new StringRedisTemplate();
        srt.setConnectionFactory(redisConnectionFactory);
        //srt.setHashKeySerializer(new StringRedisSerializer());
        //srt.setValueSerializer(new GenericJackson2JsonRedisSerializer(String.valueOf(Object.class)));
        return srt;
    }

}
