package com.teamsparta.gigabox.domain.movie_info.model

import com.teamsparta.gigabox.infra.auditing.BaseEntity
import jakarta.persistence.*
import java.time.LocalDateTime
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener

@Entity
@Table(name = "keyword")
class KeywordEntity private constructor(
    @Column(name = "word")
    val word: String,

    @Column(name = "count")
    var count: Int
):BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    fun increaseCount(){
        count++
    }

    companion object{
        fun toEntity(keyword: String): KeywordEntity{
            return KeywordEntity(
                word = keyword,
                count = 1
            )
        }
    }
}