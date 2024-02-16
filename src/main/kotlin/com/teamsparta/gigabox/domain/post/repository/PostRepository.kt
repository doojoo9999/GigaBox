package com.teamsparta.gigabox.domain.post.repository

import com.teamsparta.gigabox.domain.post.model.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<Post, Long> {
}