package com.teamsparta.gigabox.domain.movie_info.dto.request

data class CreateMovieInfoRequest (
    val title : String,
    val content : String,
    val grade : Double,
    val director : String,
    val actors : String,
    val ratings : String,
    val runtime : Int,
)