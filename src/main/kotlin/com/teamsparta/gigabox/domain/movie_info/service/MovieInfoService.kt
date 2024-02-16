package com.teamsparta.gigabox.domain.movie_info.service

import com.teamsparta.gigabox.domain.movie_info.dto.response.TopSearchResponse
import com.teamsparta.gigabox.domain.movie_info.dto.response.SearchResponse
import com.teamsparta.gigabox.domain.movie_info.dto.request.CreateMovieInfoRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface MovieInfoService {
    fun createMovieInfo(request : CreateMovieInfoRequest)

    fun searchByKeyword(keyword: String, pageable: Pageable): Page<SearchResponse>

    fun getTopSearched(): List<TopSearchResponse>

}