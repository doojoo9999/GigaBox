package com.teamsparta.gigabox.infra.cache

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.teamsparta.gigabox.domain.movie_info.dto.response.SearchResponse
import org.springframework.data.domain.Pageable
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit

@Service
class RedisService(
    private val redisTemplate: RedisTemplate<String, String>
) {
    private val movieInfoHashTableName = "MovieInfoSearchCache"
    private val movieInfoHashTableTime = 5L
    private val movieInfoTimeUnit = TimeUnit.MINUTES
    private val tmpKey = "tmpKey"
    private val tmpValue = "tmpValue"

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
            movieInfoHashTableTime,
            movieInfoTimeUnit
        )
    }

    fun getPageFromHash(
        key: String
    ): List<SearchResponse>? {
        return hashOperations.get(movieInfoHashTableName, key)
//            ?.let { jsonToPage(it) }
            ?.let { jsonToContent(it) }
    }

    fun savePageToHash(
        keyword: String,
        currentPage:List<SearchResponse>,
        pageable: Pageable
    ){
        hashTableInit()
        hashOperations.put(
            movieInfoHashTableName,
            makeKey(keyword, pageable.pageNumber),
            contentToJson(currentPage)
        )
    }

    private fun contentToJson(
        currentPage:List<SearchResponse>
    ): String{
        return objectMapper.writeValueAsString(currentPage)
    }

    private fun jsonToContent(
        jsonString: String
    ): MutableList<SearchResponse>{
        return objectMapper.readValue(jsonString)
    }

//    private fun pageToJson(
//        currentPage:Page<SearchResponse>
//    ): String{
//        return objectMapper.writeValueAsString(
//            CustomPageImpl(currentPage)
//        )
//    }
//
//    private fun jsonToPage(
//        jsonString: String
//    ): Page<SearchResponse>?{
//        val page: CustomPageImpl<SearchResponse> = objectMapper.readValue(jsonString)
//        return page
//    }

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

    //키를 전부 반환
//    fun getMovieHashTableKey(): Set<String>{
//        return hashOperations.keys(movieInfoHashTableName)
//    }

//    fun updateAllMovieHashTable(
//        hashTableKeys: Set<String>,
//        searchList: List<SearchResponse>
//    ){
//        hashOperations.putAll(
//            movieInfoHashTableName,
//        )
//    }
//
//    fun makeMap(
//        hashTableKeys: Set<String>,
//        searchList: List<SearchResponse>
//    ): MutableMap<String, String>{
//        val map: MutableMap<String, String> = mutableMapOf()
//
//        val hashTableIterator = hashTableKeys.iterator()
//        val searchListIterator = searchList.listIterator()
//
////        while (hashTableIterator.hasNext() && searchListIterator.hasNext()){
////            map[hashTableIterator.next()] = searchListIterator.next()
////        }
//
//        return map
//    }
}