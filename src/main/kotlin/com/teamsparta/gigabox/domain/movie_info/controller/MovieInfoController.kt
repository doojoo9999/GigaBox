package com.teamsparta.gigabox.domain.movie_info.controller

import com.teamsparta.gigabox.domain.movie_info.dto.request.CreateMovieInfoRequest
import com.teamsparta.gigabox.domain.movie_info.dto.response.TopSearchResponse
import com.teamsparta.gigabox.domain.movie_info.dto.response.SearchResponse
import com.teamsparta.gigabox.domain.movie_info.service.MovieInfoService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "Movie Info", description = "영화 정보 API")
@RequestMapping("/api/v1/movie-info")
@RestController
class MovieInfoController(
    @Qualifier("MovieInfoServiceV1")
    private val movieInfoService: MovieInfoService
) {
    @Operation(summary = "영화 등록", description = "영화를 등록합니다.")
    @PostMapping
    fun createMovieInfo(
        @Valid @RequestBody request: CreateMovieInfoRequest
    ): ResponseEntity<Unit> {

        movieInfoService.createMovieInfo(request)

        return ResponseEntity
            .status(HttpStatus.OK)
            .build()
    }

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
    ): ResponseEntity<Page<SearchResponse>> {
        val myPage = movieInfoService.searchByKeyword(keyword, pageable)
        movieInfoService.getKeywordEntity(keyword)
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(myPage)
    }

    @Operation(summary = "인기 검색어 목록 조회", description = "가장 많이 검색한 영화 제목을 알려준다.")
    @GetMapping("/top-search")
    fun getTopSearched(): ResponseEntity<List<TopSearchResponse>>{
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(movieInfoService.getTopSearched())
    }
}