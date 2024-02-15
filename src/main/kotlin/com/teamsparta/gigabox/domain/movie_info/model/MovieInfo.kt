package com.teamsparta.gigabox.domain.movie_info.model

import jakarta.persistence.*

@Entity
@Table(name = "movie_info")
class MovieInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}