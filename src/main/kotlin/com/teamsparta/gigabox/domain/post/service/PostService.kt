package com.teamsparta.gigabox.domain.post.service

import com.teamsparta.gigabox.domain.post.dto.request.PostRequest
import com.teamsparta.gigabox.domain.post.dto.response.PostResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface PostService {

    fun getListPost(pageable: Pageable): Page<PostResponse>

    fun getPost(postId: Long): PostResponse

    fun createPost(formData: PostRequest): PostResponse

    fun updatePost(postId: Long, storageId: Long, formData: PostRequest): PostResponse

    fun deletePost(postId: Long)
}