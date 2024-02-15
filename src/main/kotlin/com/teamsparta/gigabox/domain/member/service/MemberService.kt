package com.teamsparta.gigabox.domain.member.service

import com.teamsparta.gigabox.domain.member.dto.request.SignUpRequest

interface MemberService {

    fun signUp(request : SignUpRequest)

}