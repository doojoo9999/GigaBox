package com.teamsparta.gigabox.domain.movie_info.model

import com.teamsparta.gigabox.domain.movie_info.dto.request.CreateMovieInfoRequest
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

    @Column(name = "ratings")
    var ratings: String,

    @Column(name = "runtime")
    var runtime: Int
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null

    companion object{
        fun toEntity(
            request: CreateMovieInfoRequest
        ): MovieInfoEntity{

            return MovieInfoEntity(
                title = request.title,
                content = request.content,
                grade = request.grade,
                director = request.director,
                actors = request.actors,
                ratings = request.ratings,
                runtime = request.runtime
            )

        }
    }
}