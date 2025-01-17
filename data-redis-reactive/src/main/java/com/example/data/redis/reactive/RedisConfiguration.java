package com.example.data.redis.reactive;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration(proxyBeanMethods = false)
class RedisConfiguration {

	@Bean
	ReactiveRedisTemplate<String, Person> redisOperations(ObjectMapper objectMapper,
			ReactiveRedisConnectionFactory connectionFactory) {
		Jackson2JsonRedisSerializer<Person> serializer = new Jackson2JsonRedisSerializer<>(objectMapper, Person.class);
		RedisSerializationContext.RedisSerializationContextBuilder<String, Person> builder = RedisSerializationContext
				.newSerializationContext(new StringRedisSerializer());
		RedisSerializationContext<String, Person> context = builder.value(serializer).build();
		return new ReactiveRedisTemplate<>(connectionFactory, context);
	}

}
