package com.teamsparta.gigabox.domain.post.model

import com.teamsparta.gigabox.domain.post.dto.response.ImgUrlResponse
import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction

@Entity
@Table(name = "post_storage")
class Storage(
    @Column(name = "imageUrl")
    var imageUrl: String?,

    @ManyToOne
    @JoinColumn
    @OnDelete(action = OnDeleteAction.CASCADE)
    val post: Post,

    @Column(name = "deleted")
    var deleted: Boolean = false,

    ) : BaseTime() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    fun deleteImg() {
        deleted = true
    }

}

fun Storage.toResponse(): ImgUrlResponse {
    return ImgUrlResponse(
        id = id!!,
        postId = post.id!!,
        imageUrl = imageUrl!!
    )
}
