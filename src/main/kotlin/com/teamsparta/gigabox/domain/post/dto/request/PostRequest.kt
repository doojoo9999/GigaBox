package com.teamsparta.gigabox.domain.post.dto.request

import org.springframework.web.multipart.MultipartFile

data class PostRequest(
    val title: String?,
    val content: String?,
    val imgUrl: List<MultipartFile>?
)

