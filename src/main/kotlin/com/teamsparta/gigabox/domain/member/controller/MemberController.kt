package com.teamsparta.gigabox.domain.member.controller

import com.teamsparta.gigabox.domain.member.dto.request.SignUpRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/api/v1/member")
class MemberController {

    @PostMapping("/signup")
    fun singUp(
        @RequestBody request : SignUpRequest
    ) : ResponseEntity<Unit>{
        return ResponseEntity
            .status(HttpStatus.OK)
            .build()
    }
}