package com.teamsparta.gigabox.infra.cache

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer

@Configuration
class RedisConfig(
    @Value("\${spring.data.redis.host}") val host: String,
    @Value("\${spring.data.redis.port}") val port: Int,
) {
    @Bean
    fun redisConnectionFactory(): RedisConnectionFactory{
        return LettuceConnectionFactory()
    }

    @Bean
    fun redisTemplate(
        redisConnectionFactory: RedisConnectionFactory
    ): RedisTemplate<String, String>{
        val template = RedisTemplate<String, String>()
        template.connectionFactory = redisConnectionFactory

        //String 자료구조를 위한 Serializer
        template.keySerializer = StringRedisSerializer()
        template.valueSerializer = StringRedisSerializer()
//        template.valueSerializer = GenericJackson2JsonRedisSerializer()

        //Hash 자료구조를 위한 Serializer
        template.hashKeySerializer = StringRedisSerializer()
        template.hashValueSerializer = StringRedisSerializer()
//        template.hashValueSerializer = GenericJackson2JsonRedisSerializer()

        return template
    }
}