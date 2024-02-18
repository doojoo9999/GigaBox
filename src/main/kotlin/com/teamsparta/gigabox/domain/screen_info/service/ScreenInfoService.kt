package com.teamsparta.gigabox.domain.screen_info.service

import com.teamsparta.gigabox.domain.screen_info.dto.request.CreateScreenInfoRequest

interface ScreenInfoService {

    fun createScreenInfo (request: CreateScreenInfoRequest)

}