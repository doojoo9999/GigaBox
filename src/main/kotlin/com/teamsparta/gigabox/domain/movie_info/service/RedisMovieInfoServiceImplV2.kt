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
        val key = keyword + pageable.pageNumber

        val currentPage = redisService.getPageFromHash(key)
            ?: getFromDBAndSaveRedis(keyword, pageable)

        return currentPage
    }

    fun getFromDBAndSaveRedis(
        keyword: String,
        pageable: Pageable
    ): Page<SearchResponse>{
        val page = movieInfoRepository.searchByKeyword(keyword, pageable)

        redisService.savePageToHash(keyword, page)
        return page
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