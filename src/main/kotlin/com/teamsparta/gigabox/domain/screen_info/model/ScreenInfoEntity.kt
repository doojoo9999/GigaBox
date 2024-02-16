package com.teamsparta.gigabox.domain.screen_info.model

import jakarta.persistence.*
import java.sql.Timestamp

@Entity
@Table(name = "screen_info")
class ScreenInfoEntity (
    @Column(name = "start_time")
    val startTime : Timestamp,

    @Column(name = "ticketing")
    val ticketing : Boolean,

    @Column(name = "preview")
    val preview : Boolean,

    @Column(name = "cost")
    val cost : Int,
){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0L
}