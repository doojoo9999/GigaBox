package com.teamsparta.gigabox.domain.movie_info.repository

import com.teamsparta.gigabox.domain.movie_info.model.KeywordEntity
import org.springframework.data.jpa.repository.JpaRepository

interface KeywordRepository: JpaRepository<KeywordEntity, Long> {
//    fun findByWord(keyword: String): KeywordEntity?
}