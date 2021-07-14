package com.bank.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import com.bank.utils.yankiUtils;



@Configuration
@EnableRedisRepositories
public class RedisConfig {

  private final Logger logger = LoggerFactory.getLogger(RedisConfig.class);
  
  @Bean
  public JedisConnectionFactory connectionFactory() {
      logger.info("Config server redis db.");
      RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
      configuration.setHostName(yankiUtils.SERVER_REDIS);
      configuration.setPort(yankiUtils.PORT_REDIS);
      return new JedisConnectionFactory(configuration);
  }

  @Bean
  public RedisTemplate<String, Object> template() {
      logger.info(" create redis template.");
      RedisTemplate<String, Object> template = new RedisTemplate<>();
      template.setConnectionFactory(connectionFactory());
      template.setKeySerializer(new StringRedisSerializer());
      template.setHashKeySerializer(new StringRedisSerializer());
      template.setHashKeySerializer(new JdkSerializationRedisSerializer());
      template.setValueSerializer(new JdkSerializationRedisSerializer());
      template.setEnableTransactionSupport(true);
      template.afterPropertiesSet();
      return template;
  }

}
 