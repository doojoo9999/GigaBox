package com.teamsparta.gigabox.domain.upload.repository

import com.teamsparta.gigabox.domain.upload.model.UploadEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UploadRepository : JpaRepository<UploadEntity, Long> {
}