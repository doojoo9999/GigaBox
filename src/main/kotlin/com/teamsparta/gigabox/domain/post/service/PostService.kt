package com.teamsparta.gigabox.domain.post.service

import com.teamsparta.gigabox.domain.post.dto.request.PostRequest
import com.teamsparta.gigabox.domain.post.dto.response.PostResponse
import org.springframework.web.multipart.MultipartFile

interface PostService {

    fun getListPost(): List<PostResponse>

    fun getPost(postId: Long): PostResponse

    fun createPost(formData: PostRequest): PostResponse

    fun updatePost(postId: Long, request: PostRequest, imgUrl: List<MultipartFile>?): PostResponse

    fun deletePost(postId: Long)
}