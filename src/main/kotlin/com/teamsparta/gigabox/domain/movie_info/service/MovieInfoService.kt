package com.teamsparta.gigabox.domain.movie_info.service

import com.teamsparta.gigabox.domain.movie_info.dto.request.CreateMovieInfoRequest

interface MovieInfoService {

    fun createMovieInfo(request : CreateMovieInfoRequest)

}