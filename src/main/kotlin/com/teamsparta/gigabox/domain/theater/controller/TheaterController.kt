package com.teamsparta.gigabox.domain.theater.controller

import com.teamsparta.gigabox.domain.theater.dto.request.AddNewTheaterRequest
import com.teamsparta.gigabox.domain.theater.service.TheaterService
import org.apache.coyote.Response
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/theater")
class TheaterController (
    private val theaterService: TheaterService
) {

    @PostMapping()
    fun addNewTheater(
        @RequestBody request : AddNewTheaterRequest
    ) : ResponseEntity<Unit> {

        theaterService.addNewTheater(request)

        return ResponseEntity
            .status(HttpStatus.OK)
            .build()
    }

}