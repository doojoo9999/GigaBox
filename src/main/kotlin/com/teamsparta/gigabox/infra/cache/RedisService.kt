package com.teamsparta.gigabox.infra.cache

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.teamsparta.gigabox.domain.movie_info.dto.response.SearchResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service

const val Movie_Info_HASH_TABLE_NAME = "MovieInfoSearchCache"

@Service
class RedisService(
    private val redisTemplate: RedisTemplate<String, String>
) {
    private var stringOperations = redisTemplate.opsForValue()
    private var hashOperations = redisTemplate.opsForHash<String, String>()
    private val zSetOperations = redisTemplate.opsForZSet()
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
        return hashOperations.get(Movie_Info_HASH_TABLE_NAME, key)
            ?.let { jsonToPage(it) }
    }

    fun savePageToHash(
        key: String,
        currentPage:Page<SearchResponse>
    ){
        hashOperations.put(
            Movie_Info_HASH_TABLE_NAME,
            key,
            pageToJson(CustomPageImpl(currentPage))
        )
    }

    fun pageToJson(
        currentPage:CustomPageImpl<SearchResponse>
    ): String{
        return objectMapper.writeValueAsString(currentPage)
    }

    fun jsonToPage(
        jsonString: String
    ): Page<SearchResponse>?{
        val page: CustomPageImpl<SearchResponse> = objectMapper.readValue(jsonString)
        return page
    }
}