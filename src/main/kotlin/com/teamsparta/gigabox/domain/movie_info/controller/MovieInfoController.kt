package com.teamsparta.gigabox.domain.movie_info.controller

import com.teamsparta.gigabox.domain.movie_info.dto.request.CreateMovieInfoRequest
import com.teamsparta.gigabox.domain.movie_info.service.MovieInfoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/movie-info")
class MovieInfoController (
    private val movieInfoService : MovieInfoService
){

    fun createMovieInfo(
        @RequestBody request : CreateMovieInfoRequest
    ) : ResponseEntity <Unit> {

        movieInfoService.createMovieInfo(request)

        return ResponseEntity
            .status(HttpStatus.OK)
            .build()
    }

}