package com.teamsparta.gigabox.domain.post.model

import com.teamsparta.gigabox.domain.post.dto.response.PostResponse
import jakarta.persistence.*
import org.hibernate.annotations.SQLRestriction

@Entity
@SQLRestriction("is_deleted = false")
@Table(name = "post")
class Post(
    @Column(name = "title")
    var title: String,

    @Column(name = "content")
    var content: String,

    @Column(name = "img_url")
    var imageUrl: String,

    @Column(name = "is_deleted")
    var isDeleted: Boolean = false

) : BaseTime() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}

fun Post.toResponse(): PostResponse {
    return PostResponse(
        id = id!!,
        title = title,
        content = content,
        imageUrl = imageUrl,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt
    )
}