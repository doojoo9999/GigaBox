package com.teamsparta.gigabox.domain.movie_info.repository

import com.teamsparta.gigabox.domain.movie_info.model.MovieInfoEntity
import org.springframework.data.jpa.repository.JpaRepository

interface MovieInfoRepository : JpaRepository<MovieInfoEntity, Long> {
}