package com.example.demo.config;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableCaching
public class RedisConfig {
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory){
		StringRedisTemplate template = new StringRedisTemplate(factory);
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);
		template.setValueSerializer(jackson2JsonRedisSerializer);
		template.afterPropertiesSet();
		return template;
	}
	
	@Bean
	public RedisCacheManager cacheManager(RedisTemplate<String, String> redisTemplate,RedisConnectionFactory factory) {
		RedisCacheManager redisCache = new RedisCacheManager(RedisCacheWriter.lockingRedisCacheWriter(factory), RedisCacheConfiguration.defaultCacheConfig());
		return redisCache;
	}
	
	@Bean
	public KeyGenerator simpleKey() {
		return new KeyGenerator() {
			
			@Override
			public Object generate(Object target, Method method, Object... params) {
				StringBuffer sb = new StringBuffer();
				sb.append(target.getClass().getName() + ":");
				for(Object obj : params) {
					sb.append(obj.toString());
				}
				return sb.toString();
			}
		};
	}
	
	@Bean
	public KeyGenerator objectId() {
		return new KeyGenerator() {
			
			@Override
			public Object generate(Object target, Method method, Object... params) {
				StringBuffer sb = new StringBuffer();
				sb.append(target.getClass().getName() + ":");
				try {
					sb.append(params[0].getClass().getMethod("getId", null).invoke(params[0], null).toString());
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				}
				return sb.toString();
			}
		};
	}
}

