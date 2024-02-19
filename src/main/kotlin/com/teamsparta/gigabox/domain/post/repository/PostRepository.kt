package com.teamsparta.gigabox.domain.post.repository

import com.teamsparta.gigabox.domain.post.model.Post
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface PostRepository : JpaRepository<Post, Long> {
    fun deletePostByCreatedAtLessThanEqualAndDeleted(createdAt: LocalDateTime, isDeleted: Boolean)
}