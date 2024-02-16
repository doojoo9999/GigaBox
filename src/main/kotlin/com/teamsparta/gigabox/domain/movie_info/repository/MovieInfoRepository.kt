package com.teamsparta.gigabox.domain.movie_info.repository

import com.teamsparta.gigabox.domain.movie_info.model.MovieInfoEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query

interface MovieInfoRepository: JpaRepository<MovieInfoEntity, Long>, CustomRepository {
//    @Modifying(clearAutomatically = true)
//    @Query("UPDATE KeywordEntity k SET k.count = k.count+1")
//    fun incrementCountOfAllKeyword()


}