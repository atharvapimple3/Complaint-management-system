package com.capgemini.complaint_management_system.entities;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;

@Configuration
@EnableCaching
public class CacheConfig {

	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
		return new JedisConnectionFactory(); 
	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(jedisConnectionFactory());
		return template;
	}

	@Bean
	public CacheManager cacheManager(RedisTemplate<String, Object> redisTemplate) {
		RedisCacheConfiguration cacheConfig = RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(
				RedisSerializationContext.SerializationPair.fromSerializer(redisTemplate.getValueSerializer()));

		return RedisCacheManager.builder(jedisConnectionFactory()).cacheDefaults(cacheConfig).build();
	}
}
