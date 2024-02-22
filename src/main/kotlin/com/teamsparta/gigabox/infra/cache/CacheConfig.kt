package com.teamsparta.gigabox.infra.cache

import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.concurrent.ConcurrentMapCache
import org.springframework.cache.support.SimpleCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.data.redis.serializer.StringRedisSerializer
import java.time.Duration

@EnableCaching
@Configuration
class CacheConfig {

    /* ================== MovieInfoServiceImplV2에서 사용 =====================*/
    @Bean
    fun cacheManager(): CacheManager {
        val simpleCacheManager = SimpleCacheManager()
        simpleCacheManager.setCaches(
            listOf(
                ConcurrentMapCache("pageList")
            )
        )
        return simpleCacheManager
    }

    /* ================== 사용하지 않아도 되는 코드 =====================*/

//    @Bean
//    fun cacheManager(
//        redisConnectionFactory: RedisConnectionFactory
//    ): RedisCacheManager {
//        val defaults: RedisCacheConfiguration = RedisCacheConfiguration
//            .defaultCacheConfig()
//            .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(StringRedisSerializer()))
//            .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(StringRedisSerializer()))
//            .entryTtl(Duration.ofMinutes(30)) //캐시 유효기간을 30분으로 설정
//
//        return RedisCacheManager
//            .RedisCacheManagerBuilder
//            .fromConnectionFactory(redisConnectionFactory)
//            .cacheDefaults(defaults).build()
//    }
}