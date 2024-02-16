package com.teamsparta.gigabox.domain.screen_info.controller

import com.teamsparta.gigabox.domain.screen_info.dto.request.CreateScreenInfoRequest
import com.teamsparta.gigabox.domain.screen_info.service.ScreenInfoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/screen_info")
class ScreenInfoController (
    private val screenInfoService: ScreenInfoService
){

    @PostMapping()
    fun createScreenInfo(
        @RequestBody createScreenInfoRequest: CreateScreenInfoRequest
    ) : ResponseEntity<Unit> {

        screenInfoService.createScreenInfo(createScreenInfoRequest)

        return ResponseEntity
            .status(HttpStatus.OK)
            .build()

    }

}