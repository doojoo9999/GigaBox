package com.teamsparta.gigabox.domain.screen_info.service

import com.teamsparta.gigabox.domain.screen_info.dto.request.CreateScreenInfoRequest
import com.teamsparta.gigabox.domain.screen_info.model.ScreenInfoEntity
import com.teamsparta.gigabox.domain.screen_info.repository.ScreenInfoRepository
import org.springframework.stereotype.Service

@Service
class ScreenInfoServiceImpl(
    private val screenInfoRepository: ScreenInfoRepository
) : ScreenInfoService{
    override fun createScreenInfo(request: CreateScreenInfoRequest) {

        screenInfoRepository.save(ScreenInfoEntity(
            startTime = request.startTime,
            ticketing = request.ticketing,
            preview = request.preview,
            cost = request.cost
        ))

    }
}