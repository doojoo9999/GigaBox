package com.teamsparta.gigabox.domain.movie_info.repository

import com.teamsparta.gigabox.domain.movie_info.dto.response.KeywordResponse
import com.teamsparta.gigabox.domain.movie_info.dto.response.SearchResponse
import com.teamsparta.gigabox.domain.movie_info.model.KeywordEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface CustomRepository {
    fun searchByMovieName(keyword: String, pageable: Pageable): Page<SearchResponse>

    fun getTopSearched(): List<KeywordResponse>
}