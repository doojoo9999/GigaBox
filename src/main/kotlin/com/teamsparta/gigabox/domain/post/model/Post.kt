package com.teamsparta.gigabox.domain.post.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.teamsparta.gigabox.domain.post.dto.response.PostResponse
import jakarta.persistence.*
import org.hibernate.annotations.SQLRestriction

@Entity
@SQLRestriction("deleted = false")
@Table(name = "post")
class Post(
    @Column(name = "title")
    var title: String,

    @Column(name = "content")
    var content: String,

    @Column(name = "deleted")
    var deleted: Boolean = false,

    @JsonIgnore
    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    var storage: MutableList<Storage> = mutableListOf(),

    ) : BaseTime() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    fun deletePost() {
        deleted = true
    }
    fun initImgUrl(uploadData: MutableList<Storage>) {
        storage = uploadData
    }
}

fun Post.toResponse(): PostResponse {
    return PostResponse(
        id = id!!,
        title = title,
        content = content,
        imgUrlList = storage.map { it.toResponse() },
        createdAt = this.createdAt,
        updatedAt = this.updatedAt
    )
}