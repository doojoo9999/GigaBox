package com.teamsparta.gigabox.domain.post.repository

import com.teamsparta.gigabox.domain.post.model.Storage
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface StorageRepository : JpaRepository<Storage, Long> {
    fun deleteStorageByCreatedAtLessThanAndDeleted (createdAt: LocalDateTime, isDeleted: Boolean)

    fun findByPostId(postId: Long) : List<Storage>

}