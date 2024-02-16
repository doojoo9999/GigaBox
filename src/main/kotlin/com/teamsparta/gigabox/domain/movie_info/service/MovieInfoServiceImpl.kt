package com.teamsparta.gigabox.domain.movie_info.service

import com.teamsparta.gigabox.domain.movie_info.dto.request.CreateMovieInfoRequest
import com.teamsparta.gigabox.domain.movie_info.dto.response.TopSearchResponse
import com.teamsparta.gigabox.domain.movie_info.dto.response.SearchResponse
import com.teamsparta.gigabox.domain.movie_info.model.MovieInfoEntity
import com.teamsparta.gigabox.domain.movie_info.repository.MovieInfoRepository
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import org.springframework.data.domain.Pageable

@Service
class MovieInfoServiceImpl(
    private val movieInfoRepository: MovieInfoRepository,
): MovieInfoService {
    override fun createMovieInfo(
        request: CreateMovieInfoRequest
    ) {
        movieInfoRepository.save(
            MovieInfoEntity.toEntity(request)
        )

    }

    override fun searchByKeyword(
        keyword: String,
        pageable: Pageable
    ): Page<SearchResponse> {
        val myPage = movieInfoRepository.searchByKeyword(keyword, pageable)

        return myPage
    }

    override fun getTopSearched(): List<TopSearchResponse> {
        return movieInfoRepository
            .getTopSearched()
    }
}