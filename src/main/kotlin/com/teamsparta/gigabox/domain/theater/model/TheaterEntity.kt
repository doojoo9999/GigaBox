package com.teamsparta.gigabox.domain.theater.model

import jakarta.persistence.*

@Entity
@Table(name = "theater")
class TheaterEntity (
    @Column(name = "seat_count")
    val seatCount : Int
){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0L
}