package com.teamsparta.gigabox.domain.screen_info.dto.request

import java.sql.Timestamp

data class CreateScreenInfoRequest (
    val startTime : Timestamp,
    val ticketing : Boolean,
    val preview : Boolean,
    val cost : Int,
    val movieInfo : Long,
)