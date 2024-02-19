package com.teamsparta.gigabox.domain.post.model

import com.teamsparta.gigabox.domain.post.dto.response.ImgUrlResponse
import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime

@Entity
@Table(name = "storage")
class Storage(
    @Column(name = "imageUrl")
    val imageUrl: String,

    @ManyToOne
    @JoinColumn
    @OnDelete(action = OnDeleteAction.CASCADE)
    val post: Post,

    @Column(name = "deleted")
    var deleted: Boolean = false,

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @CreatedDate
    @Column(nullable = false, updatable = false)
    var createdAt: LocalDateTime = LocalDateTime.now()

    fun deleteImg() {
        deleted = true
    }

}
fun Storage.toResponse(): ImgUrlResponse {
    return ImgUrlResponse(
        id = id!!,
        postId = post.id!!,
        imageUrl = imageUrl
    )
}
