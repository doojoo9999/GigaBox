package com.teamsparta.gigabox.domain.member.dto.request

import java.util.Date

data class SignUpRequest(
    val account : String,
    val email : String,
    val password : String,
    val birthDate : Date,
    val phoneNumber : String,
)

