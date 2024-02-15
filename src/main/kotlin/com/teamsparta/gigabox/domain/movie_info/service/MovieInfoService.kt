package com.teamsparta.gigabox.domain.movie_info.service

import com.teamsparta.gigabox.domain.movie_info.dto.response.KeywordResponse
import com.teamsparta.gigabox.domain.movie_info.dto.response.SearchResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface MovieInfoService {
    fun searchByKeyword(keyword: String, pageable: Pageable): Page<SearchResponse>

    fun getTopSearched(): List<KeywordResponse>
}