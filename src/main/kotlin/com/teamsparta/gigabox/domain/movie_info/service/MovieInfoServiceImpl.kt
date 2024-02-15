package com.teamsparta.gigabox.domain.movie_info.service

import com.teamsparta.gigabox.domain.movie_info.dto.request.CreateMovieInfoRequest
import com.teamsparta.gigabox.domain.movie_info.model.MovieInfoEntity
import com.teamsparta.gigabox.domain.movie_info.repository.MovieInfoRepository
import org.springframework.stereotype.Service

@Service
class MovieInfoServiceImpl(
    private val movieInfoRepository: MovieInfoRepository
) : MovieInfoService {
    override fun createMovieInfo(request: CreateMovieInfoRequest) {

        movieInfoRepository.save(
            MovieInfoEntity(
                title = request.title,
                content = request.content,
                grade = request.grade,
                director = request.director,
                actors = request.actors,
                ratings = request.ratings,
                runtime = request.runtime
            )
        )

    }


}