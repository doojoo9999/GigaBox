package com.teamsparta.gigabox.domain.movie_info.service

import com.teamsparta.gigabox.domain.movie_info.dto.request.CreateMovieInfoRequest
import com.teamsparta.gigabox.domain.movie_info.dto.response.SearchResponse
import com.teamsparta.gigabox.domain.movie_info.dto.response.TopSearchResponse
import com.teamsparta.gigabox.domain.movie_info.model.KeywordEntity
import com.teamsparta.gigabox.domain.movie_info.repository.KeywordRepository
import com.teamsparta.gigabox.domain.movie_info.repository.MovieInfoRepository
import org.hibernate.annotations.Cache
import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service("MovieInfoServiceV2")
class MovieInfoServiceImplV2(
    private val movieInfoRepository: MovieInfoRepository
): MovieInfoService {
    override fun createMovieInfo(
        request: CreateMovieInfoRequest
    ) {
        TODO("Not yet implemented")
    }

    @Cacheable(cacheNames = ["pageList"], key = "#keyword")
    override fun searchByKeyword(
        keyword: String,
        pageable: Pageable
    ): Page<SearchResponse> {
        return movieInfoRepository
            .searchByKeyword(keyword, pageable)
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