package com.teamsparta.gigabox.infra.cache

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.teamsparta.gigabox.domain.movie_info.dto.response.SearchResponse
import org.springframework.data.domain.Page
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit

@Service
class RedisService(
    private val redisTemplate: RedisTemplate<String, String>
) {
    private val movieInfoHashTableName = "MovieInfoSearchCache"
    private val movieInfoHashTableTimeOut = 1L
    private val movieInfoTimeUnit = TimeUnit.HOURS
    private val tmpKey = "tmpKey"
    private val tmpValue = "tmpValue"

    private val topSearchedZSetName = "Recently-Viewed-Posts"
    private val topSearchedZSetTimeOut = 1L //1일

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

    //redis에 key가 없을 때 임시 데이터 넣고 <- 이거 없으면 TTL 설정이 안 됨
    //TTL 설정해주는 코드
    private fun hashTableInit(){
        if(!checkHashTableHasKey()){
            hashOperations.put(
                movieInfoHashTableName,
                tmpKey,
                tmpValue
            )
            setHashTableExpire()
        }
    }

    fun checkHashTableHasKey(): Boolean{
        return redisTemplate.hasKey(movieInfoHashTableName)
    }

    private fun setHashTableExpire(){
        redisTemplate.expire(
            movieInfoHashTableName,
            movieInfoHashTableTimeOut,
            movieInfoTimeUnit
        )
    }

    fun getPageFromHash(
        key: String
    ): Page<SearchResponse>? {
        return hashOperations.get(movieInfoHashTableName, key)
            ?.let { jsonToPage(it) }
//            ?.let { jsonToContent(it) }
    }

    fun savePageToHash(
        keyword: String,
        currentPage:Page<SearchResponse>
    ){
        hashTableInit()
        hashOperations.put(
            movieInfoHashTableName,
            makeKey(keyword, currentPage.number),
            pageToJson(currentPage)
        )
    }

//    fun saveContentToHash(
//        keyword: String,
//        currentPage:List<SearchResponse>,
//        pageable: Pageable
//    ){
//        hashTableInit()
//        hashOperations.put(
//            movieInfoHashTableName,
//            makeKey(keyword, pageable.pageNumber),
//            contentToJson(currentPage)
//        )
//    }
//
//    private fun contentToJson(
//        currentPage:List<SearchResponse>
//    ): String{
//        return objectMapper.writeValueAsString(currentPage)
//    }
//
//    private fun jsonToContent(
//        jsonString: String
//    ): MutableList<SearchResponse>{
//        return objectMapper.readValue(jsonString)
//    }

    private fun pageToJson(
        currentPage:Page<SearchResponse>
    ): String{
        return objectMapper.writeValueAsString(
            CustomPageImpl(currentPage)
        )
    }

    private fun jsonToPage(
        jsonString: String
    ): Page<SearchResponse>{
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

    //
    fun saveKeywordEntityToZSet(){

    }





}