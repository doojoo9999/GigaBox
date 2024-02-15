package com.teamsparta.gigabox.domain.movie_info.repository

import com.teamsparta.gigabox.domain.movie_info.model.MovieInfo
import org.springframework.data.jpa.repository.JpaRepository

interface MovieInfoRepository: JpaRepository<MovieInfo, Long> {

}