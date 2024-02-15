package com.teamsparta.gigabox.domain.movie_info.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Tag(name = "search", description = "검색 API")
@RestController
class SearchController(
) {
    @Operation(summary = "영화 제목 검색", description = "키워드를 입력하면 영화를 검색.")
    @GetMapping("/search")
    fun searchPost(
        @Valid
        @NotBlank
        @Size(min = 1, max = 15, message = "1자 이상 15자 이하여야 합니다.")
        @RequestParam keyword: String
    ){

    }
}