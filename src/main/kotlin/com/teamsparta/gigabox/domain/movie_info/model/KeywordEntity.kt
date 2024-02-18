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
        
        //엔티티 하나를 만드는 메서드
        fun toEntity(
            keyword: String
        ): KeywordEntity{
            return KeywordEntity(
                word = keyword,
                count = 1
            )
        }

        //여러 개의 엔티티를 만드는 메서드
        fun toEntities(
            titles: Array<String>
        ): List<KeywordEntity>{
            return titles.map {
                toEntity(it)
            }
        }
    }
}