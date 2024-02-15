package com.teamsparta.gigabox.domain.post.service

import com.teamsparta.gigabox.domain.post.dto.request.PostRequest
import com.teamsparta.gigabox.domain.post.dto.response.PostResponse

interface PostService {

    fun getListPost(): List<PostResponse>

    fun getPost(postId: Long): PostResponse

    fun createPost(request: PostRequest): PostResponse

    fun updatePost(postId: Long, request: PostRequest): PostResponse

    fun deletePost(postId: Long)
}