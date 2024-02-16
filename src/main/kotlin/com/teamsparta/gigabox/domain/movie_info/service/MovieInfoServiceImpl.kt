package com.teamsparta.gigabox.domain.movie_info.service

import com.teamsparta.gigabox.domain.movie_info.dto.request.CreateMovieInfoRequest
import com.teamsparta.gigabox.domain.movie_info.dto.response.TopSearchResponse
import com.teamsparta.gigabox.domain.movie_info.dto.response.SearchResponse
import com.teamsparta.gigabox.domain.movie_info.model.KeywordEntity
import com.teamsparta.gigabox.domain.movie_info.model.MovieInfoEntity
import com.teamsparta.gigabox.domain.movie_info.repository.KeywordRepository
import com.teamsparta.gigabox.domain.movie_info.repository.MovieInfoRepository
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import org.springframework.data.domain.Pageable
import org.springframework.transaction.annotation.Transactional

@Service
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

    @Transactional
    override fun searchByKeyword(
        keyword: String,
        pageable: Pageable
    ): Page<SearchResponse> {
        val myPage = movieInfoRepository.searchByKeyword(keyword, pageable)

        keywordRepository.findByWord(keyword)
            ?.apply { increaseCount() } //있으면 count 증가
            ?: keywordRepository.save(KeywordEntity.toEntity(keyword)) //없으면 entity 저장

        return myPage
    }

    override fun getTopSearched(): List<TopSearchResponse> {
        return movieInfoRepository
            .getTopSearched()
    }
}