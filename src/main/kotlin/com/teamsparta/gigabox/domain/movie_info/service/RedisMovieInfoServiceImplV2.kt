package com.teamsparta.gigabox.domain.movie_info.service

import com.teamsparta.gigabox.domain.movie_info.dto.request.CreateMovieInfoRequest
import com.teamsparta.gigabox.domain.movie_info.dto.response.SearchResponse
import com.teamsparta.gigabox.domain.movie_info.dto.response.TopSearchResponse
import com.teamsparta.gigabox.domain.movie_info.model.KeywordEntity
import com.teamsparta.gigabox.domain.movie_info.repository.MovieInfoRepository
import com.teamsparta.gigabox.infra.aop.StopWatch
import com.teamsparta.gigabox.infra.cache.RedisService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service("RedisMovieInfoServiceV2")
class RedisMovieInfoServiceImplV2(
    private val movieInfoRepository: MovieInfoRepository,
    private val redisService: RedisService
): MovieInfoService  {
    override fun createMovieInfo(request: CreateMovieInfoRequest) {
        TODO("Not yet implemented")
    }

    @StopWatch
    override fun searchByKeyword(
        keyword: String,
        pageable: Pageable
    ): Page<SearchResponse> {
        val key = redisService.makeKey(keyword, pageable.pageNumber)

        //redis에서 가져오고, 없으면 DB에서 가져온 후, redis에 저장한다.
        return redisService.getPageFromHash(key)
            ?: getFromDBAndSaveRedis(keyword, pageable)
    }

    fun getFromDBAndSaveRedis(
        keyword: String,
        pageable: Pageable
    ): Page<SearchResponse> {
        //DB에서 검색 결과를 가져온다.
        val page = movieInfoRepository.searchByKeyword(keyword, pageable)

        //검색 결과가 있을 때만 Cache 저장
        if(page.content.isNotEmpty())
            redisService.savePageToHash(keyword, page)

        //DB에서 가져온 결과를 반환한다.
//        return pageList

        val key = redisService.makeKey(keyword, pageable.pageNumber)

        //redis에 저장한 결과를 가져온다.
        return redisService.getPageFromHash(key)
            ?: throw IllegalStateException("redis에서 오류가 생겼어용")
    }

    override fun getTopSearched(): List<TopSearchResponse> {
        TODO("Not yet implemented")
    }

    override fun getKeywordEntity(keyword: String) {
        TODO("Not yet implemented")
    }

    override fun saveKeywordEntity(keyword: String) {
        TODO("Not yet implemented")
    }

    override fun updateKeywordEntity(keywordEntity: KeywordEntity) {
        TODO("Not yet implemented")
    }
}