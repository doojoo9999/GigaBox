package com.teamsparta.gigabox.domain.post.service

import com.teamsparta.gigabox.domain.post.dto.request.PostRequest
import com.teamsparta.gigabox.domain.post.dto.response.PostResponse
import com.teamsparta.gigabox.domain.post.exception.ModelNotFoundException
import com.teamsparta.gigabox.domain.post.model.Post
import com.teamsparta.gigabox.domain.post.model.toResponse
import com.teamsparta.gigabox.domain.post.repository.PostRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class PostServiceImpl(
    private val postRepository: PostRepository
) : PostService {
    override fun getListPost(): List<PostResponse> {
        return postRepository.findAll().map { it.toResponse() }
    }

    override fun getPost(postId: Long): PostResponse {
        val post = postRepository.findByIdOrNull(postId) ?: throw ModelNotFoundException("post", postId)
        return post.toResponse()
    }

    override fun createPost(request: PostRequest): PostResponse {
        val post = postRepository.save(
            Post(
                title = request.title,
                content = request.content,
                imageUrl = request.imageUrl
            )
        )
        return post.toResponse()
    }

    override fun updatePost(postId: Long, request: PostRequest): PostResponse {
        val post = postRepository.findByIdOrNull(postId) ?: throw ModelNotFoundException("post", postId)
        post.title = request.title
        post.content = request.content
        post.imageUrl = request.imageUrl

        return post.toResponse()
    }

    override fun deletePost(postId: Long) {
        val post = postRepository.findByIdOrNull(postId) ?: throw ModelNotFoundException("post", postId)
        if (!post.isDeleted) {
            postRepository.save(post)
        }
    }
}