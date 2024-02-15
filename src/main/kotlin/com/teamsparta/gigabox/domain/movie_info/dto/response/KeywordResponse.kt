package com.teamsparta.gigabox.domain.movie_info.dto.response

import com.teamsparta.gigabox.domain.movie_info.model.KeywordEntity

data class KeywordResponse(
    val word: String,
    val count: Int
)
