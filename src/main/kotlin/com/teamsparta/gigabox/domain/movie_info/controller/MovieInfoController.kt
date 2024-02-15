package com.teamsparta.gigabox.domain.movie_info.controller

import com.teamsparta.gigabox.domain.movie_info.dto.response.KeywordResponse
import com.teamsparta.gigabox.domain.movie_info.dto.response.SearchResponse
import com.teamsparta.gigabox.domain.movie_info.service.MovieInfoService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Movie Info", description = "영화 정보 API")
@RequestMapping("/api/v1/movie-info")
@RestController
class MovieInfoController(
    private val movieInfoService: MovieInfoService
) {
    @Operation(summary = "영화 제목 검색 + Paging", description = "키워드를 입력하면 영화를 검색한다.")
    @GetMapping("/search")
    fun searchByKeyword(
        @Valid
        @NotBlank
        @Size(min = 1, max = 15, message = "1자 이상 15자 이하여야 합니다.")
        @RequestParam keyword: String,

        @PageableDefault(
            page = 0,
            size = 10,
            sort = ["title"]
        ) pageable: Pageable
    ):ResponseEntity<Page<SearchResponse>>{
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(movieInfoService.searchByKeyword(keyword, pageable))
    }

    @Operation(summary = "인기 검색어 목록 조회", description = "가장 많이 검색한 영화 제목을 알려준다.")
    @GetMapping("/top-search")
    fun getTopSearched(): ResponseEntity<List<KeywordResponse>>{
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(movieInfoService.getTopSearched())
    }
}