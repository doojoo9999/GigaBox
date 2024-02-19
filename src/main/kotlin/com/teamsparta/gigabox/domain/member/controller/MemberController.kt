package com.teamsparta.gigabox.domain.member.controller

import com.teamsparta.gigabox.domain.member.dto.request.LoginRequest
import com.teamsparta.gigabox.domain.member.dto.request.SendMailRequest
import com.teamsparta.gigabox.domain.member.dto.request.SignUpRequest
import com.teamsparta.gigabox.domain.member.dto.response.LoginResponse
import com.teamsparta.gigabox.domain.member.service.MemberService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/member")
class MemberController(
    private val memberService: MemberService
) {

    @PostMapping("/login")
    fun signIn(
        @RequestBody loginRequest: LoginRequest
    ): ResponseEntity<LoginResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(memberService.login(loginRequest))
    }

    @PostMapping("/signup")
    fun singUp(
        @RequestBody request : SignUpRequest
    ) : ResponseEntity<Unit>{

        memberService.signUp(request)

        return ResponseEntity
            .status(HttpStatus.OK)
            .build()
    }

    @PostMapping("/sendmail")
    fun sendMail(
        @RequestBody request : SendMailRequest
    ) : ResponseEntity<Unit> {
        memberService.sendMail(request)

        return ResponseEntity
            .status(HttpStatus.OK)
            .build()

    }

    @GetMapping("/emailAuth")
    fun emailAuth(
        @RequestParam ("authCode") authCode : String
    ) : ResponseEntity<Unit> {

        memberService.emailAuth(authCode)

        return ResponseEntity
            .status(HttpStatus.OK)
            .build()
    }


}