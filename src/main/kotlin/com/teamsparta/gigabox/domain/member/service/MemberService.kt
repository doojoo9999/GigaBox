package com.teamsparta.gigabox.domain.member.service

import com.teamsparta.gigabox.domain.member.dto.request.EmailAuthRequest
import com.teamsparta.gigabox.domain.member.dto.request.SendMailRequest
import com.teamsparta.gigabox.domain.member.dto.request.SignUpRequest

interface MemberService {

    fun signUp(request : SignUpRequest)

    fun sendMail(request : SendMailRequest)

    fun emailAuth(authCode : String)
}