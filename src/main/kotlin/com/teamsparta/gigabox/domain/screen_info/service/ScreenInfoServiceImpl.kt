package com.teamsparta.gigabox.domain.screen_info.service

import com.teamsparta.gigabox.domain.movie_info.repository.MovieInfoRepository
import com.teamsparta.gigabox.domain.screen_info.dto.request.CreateScreenInfoRequest
import com.teamsparta.gigabox.domain.screen_info.model.ScreenInfoEntity
import com.teamsparta.gigabox.domain.screen_info.repository.ScreenInfoRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ScreenInfoServiceImpl(
    private val screenInfoRepository: ScreenInfoRepository,
    private val movieInfoRepository: MovieInfoRepository
) : ScreenInfoService{
    override fun createScreenInfo(request: CreateScreenInfoRequest) {

        val movieInfo = movieInfoRepository.findByIdOrNull(request.movieInfo)
            ?: throw IllegalArgumentException("MovieInfo not found")

        screenInfoRepository.save(ScreenInfoEntity(
            startTime = request.startTime,
            ticketing = request.ticketing,
            preview = request.preview,
            cost = request.cost,
            movieInfo = movieInfo
        ))

    }
}