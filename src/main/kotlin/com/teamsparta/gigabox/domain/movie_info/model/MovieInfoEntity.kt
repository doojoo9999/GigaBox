package com.teamsparta.gigabox.domain.movie_info.model

import jakarta.persistence.*

@Entity
@Table(name = "movie_info")
class MovieInfoEntity (
    @Column(name = "title")
    val title : String,

    @Column(name = "content")
    val content : String,

    @Column(name = "grade")
    val grade : Double,

    @Column(name = "director")
    val director : String,

    @Column(name = "actors")
    val actors : String,

    @Column(name = "ratings")
    val ratings : String,

    @Column(name = "runtime")
    val runtime : Int,
){

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0L

}