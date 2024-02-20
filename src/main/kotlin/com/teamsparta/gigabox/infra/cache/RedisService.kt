package com.teamsparta.gigabox.infra.cache

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.teamsparta.gigabox.domain.movie_info.dto.response.SearchResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit

@Service
class RedisService(
    private val redisTemplate: RedisTemplate<String, String>
) {
    private val movieInfoHashTableName = "MovieInfoSearchCache"
    private val movieInfoHashTableTime = 30L
    private val movieInfoTimeUnit = TimeUnit.MINUTES

    private var hashOperations = redisTemplate.opsForHash<String, String>()
    private val objectMapper = setObjectMapper()

    private fun setObjectMapper(): ObjectMapper {
        return ObjectMapper()
            .registerKotlinModule()
            .registerModule(JavaTimeModule())
            .activateDefaultTyping(
                BasicPolymorphicTypeValidator.builder()
                    .allowIfBaseType(Any::class.java).build(),
                ObjectMapper.DefaultTyping.EVERYTHING)
    }

    fun getPageFromHash(
        key: String
    ): Page<SearchResponse>? {
        return hashOperations.get(movieInfoHashTableName, key)
            ?.let { jsonToPage(it) }
    }

    fun savePageToHash(
        keyword: String,
        currentPage:Page<SearchResponse>
    ){
        hashOperations.put(
            movieInfoHashTableName,
            makeKey(keyword, currentPage.pageable.pageNumber),
            pageToJson(currentPage)
        )

        redisTemplate.expire(
            movieInfoHashTableName,
            movieInfoHashTableTime,
            movieInfoTimeUnit
        )
    }

    fun pageToJson(
        currentPage:Page<SearchResponse>
    ): String{
        return objectMapper.writeValueAsString(
            CustomPageImpl(currentPage)
        )
    }

    fun jsonToPage(
        jsonString: String
    ): Page<SearchResponse>?{
        val page: CustomPageImpl<SearchResponse> = objectMapper.readValue(jsonString)
        return page
    }

    fun makeKey(
        keyword: String,
        pageNumber: Int
    ): String{
        return StringBuilder().apply {
            append(keyword)
            append(":")
            append(pageNumber)
        }.toString()
    }
}