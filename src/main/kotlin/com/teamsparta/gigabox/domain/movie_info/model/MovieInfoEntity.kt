package com.teamsparta.gigabox.domain.movie_info.model

import jakarta.persistence.*

@Entity
@Table(name = "movie_info")
class MovieInfoEntity(
    @Column(name = "title")
    var title: String,

    @Column(name = "content")
    var content: String,

    @Column(name = "grade") //평점
    var grade: Float,

    @Column(name = "director")
    var director: String,

    @Column(name = "actors")
    var actors: String,

    @Column(name = "runtime")
    var runtime: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null

    @Column(name = "ratings")
    var ratings: String = RATINGS.ALL.description
}