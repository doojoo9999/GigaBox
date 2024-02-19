package com.teamsparta.gigabox.domain.upload.service

import com.teamsparta.gigabox.domain.upload.dto.request.UploadRequest

interface UploadService {
    fun fileUpload(multiFile: UploadRequest): String

    fun fileDelete(uploadId: Long)
}