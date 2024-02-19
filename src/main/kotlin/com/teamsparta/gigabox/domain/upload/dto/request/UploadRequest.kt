package com.teamsparta.gigabox.domain.upload.dto.request

import org.springframework.web.multipart.MultipartFile

data class UploadRequest (
    val file: List<MultipartFile>?
)