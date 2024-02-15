package com.teamsparta.gigabox.domain.post.repository

import com.teamsparta.gigabox.domain.post.model.Storage
import org.springframework.data.jpa.repository.JpaRepository

interface StorageRepository : JpaRepository<Storage, Long> {
}