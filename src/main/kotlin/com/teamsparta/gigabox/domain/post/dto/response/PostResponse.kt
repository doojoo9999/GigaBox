package com.teamsparta.gigabox.domain.post.dto.response

import java.time.LocalDateTime

data class PostResponse(
    val id: Long,
    val title: String,
    val content: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val imgUrlList: List<ImgUrlResponse>
)