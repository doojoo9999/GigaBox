package com.teamsparta.gigabox.domain.member.dto.request

data class LoginRequest(
    val account: String,
    val password: String,
    val role: String
)
