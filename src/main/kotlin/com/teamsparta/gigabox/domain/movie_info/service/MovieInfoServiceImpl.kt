package com.teamsparta.gigabox.domain.movie_info.service

import com.teamsparta.gigabox.domain.movie_info.dto.request.CreateMovieInfoRequest
import com.teamsparta.gigabox.domain.movie_info.dto.response.TopSearchResponse
import com.teamsparta.gigabox.domain.movie_info.dto.response.SearchResponse
import com.teamsparta.gigabox.domain.movie_info.model.KeywordEntity
import com.teamsparta.gigabox.domain.movie_info.model.MovieInfoEntity
import com.teamsparta.gigabox.domain.movie_info.repository.KeywordRepository
import com.teamsparta.gigabox.domain.movie_info.repository.MovieInfoRepository
import com.teamsparta.gigabox.infra.aop.StopWatch
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import org.springframework.data.domain.Pageable
import org.springframework.transaction.annotation.Transactional

@Service("MovieInfoServiceV1")
class MovieInfoServiceImpl(
    private val movieInfoRepository: MovieInfoRepository,
    private val keywordRepository: KeywordRepository
): MovieInfoService {

    override fun createMovieInfo(
        request: CreateMovieInfoRequest
    ) {
        movieInfoRepository.save(
            MovieInfoEntity.toEntity(request)
        )
    }

    @StopWatch
    override fun searchByKeyword(
        keyword: String,
        pageable: Pageable
    ): List<SearchResponse> {
        return movieInfoRepository
            .searchByKeyword(keyword, pageable)
    }

    @Transactional
    override fun getKeywordEntity(
        keyword: String
    ) {
        keywordRepository.findByWord(keyword)
            ?.let { updateKeywordEntity(it) } //있으면 count 증가
            ?: saveKeywordEntity(keyword) //없으면 entity 저장
    }

    override fun saveKeywordEntity(
        keyword: String
    ) {
        keywordRepository.save(KeywordEntity.toEntity(keyword))
    }

    override fun updateKeywordEntity(
        keywordEntity: KeywordEntity
    ) {
        keywordEntity.apply { increaseCount() }
    }

    override fun getTopSearched(): List<TopSearchResponse> {
        return movieInfoRepository
            .getTopSearched()
    }
}