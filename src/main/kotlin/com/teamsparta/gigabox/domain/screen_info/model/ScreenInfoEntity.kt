package com.teamsparta.gigabox.domain.screen_info.model

import com.teamsparta.gigabox.domain.movie_info.model.MovieInfoEntity
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

    @JoinColumn(name = "movie_info_id")
    @OneToOne
    val movieInfo : MovieInfoEntity

){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0L
}