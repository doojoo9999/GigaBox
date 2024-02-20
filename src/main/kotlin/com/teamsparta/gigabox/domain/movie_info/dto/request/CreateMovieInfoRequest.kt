package com.teamsparta.gigabox.domain.movie_info.dto.request

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

@Schema(description = "영화 정보를 등록할 때 입력한 정보를 전달하는 객체")
data class CreateMovieInfoRequest (
    @field:NotBlank(message = "{NotBlank.title}")
    @field:Size(min = 1, max = 50, message = "영화 제목은 1자 이상 50자 이하입니다.")
    @Schema(description = "영화 제목", example = "영화 제목")
    val title : String,

    @field:NotBlank(message = "{NotBlank.content}")
    @field:Size(min = 1, max = 1000, message = "영화 소개문은 1자 이상 1000자 이하로 작성해주세요")
    @Schema(description = "영화 소개문", example = "영화 소개문")
    val content : String,

    @Schema(description = "영화 평점", example = "10.0")
    val grade : Float,

    @field:Size(min = 1, max = 30, message = "감독 이름은 1자 이상 30자 이하로 작성해주세요")
    @Schema(description = "영화 감독", example = "감독")
    val director : String,

    @field:Size(min = 1, max = 100, message = "출연진들 이름은 1자 이상 100자 이하로 작성해주세요")
    @Schema(description = "영화 출연진", example = "출연진")
    val actors : String,

    @field:NotBlank(message = "{NotBlank.ratings}")
    @field:Size(min = 1, max = 15, message = "관람 등급은 1자 이상 15자 이하로 작성해주세요")
    @Schema(description = "영화 관람등급", example = "전체이용가")
    val ratings : String,

    @Schema(description = "상영 시간", example = "120")
    val runtime : Int,
)