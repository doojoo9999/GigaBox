package com.teamsparta.gigabox.infra.cashe

import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.concurrent.ConcurrentMapCache
import org.springframework.cache.support.SimpleCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@EnableCaching
@Configuration
class CacheConfig {
    @Bean
    fun cacheManager(): CacheManager{
        val simpleCacheManager = SimpleCacheManager()
        simpleCacheManager.setCaches(
            listOf(
                ConcurrentMapCache("pageList"),
                ConcurrentMapCache("topSearched")
            )
        )
        return simpleCacheManager
    }
}