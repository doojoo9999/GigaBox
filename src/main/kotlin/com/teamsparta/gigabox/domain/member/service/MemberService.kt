package com.teamsparta.gigabox.domain.member.service

import com.teamsparta.gigabox.domain.member.dto.request.LoginRequest
import com.teamsparta.gigabox.domain.member.dto.request.SendMailRequest
import com.teamsparta.gigabox.domain.member.dto.request.SignUpRequest
import com.teamsparta.gigabox.domain.member.dto.response.LoginResponse

interface MemberService {
    fun login(request: LoginRequest): LoginResponse

    fun signUp(request : SignUpRequest)

    fun sendMail(request : SendMailRequest)

    fun emailAuth(authCode : String)
}