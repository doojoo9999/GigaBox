package com.teamsparta.gigabox.domain.post.service

import com.teamsparta.gigabox.domain.post.dto.request.PostRequest
import com.teamsparta.gigabox.domain.post.dto.request.UpdatePostRequest
import com.teamsparta.gigabox.domain.post.dto.response.PostResponse
import com.teamsparta.gigabox.domain.post.exception.ModelNotFoundException
import com.teamsparta.gigabox.domain.post.model.Post
import com.teamsparta.gigabox.domain.post.model.Storage
import com.teamsparta.gigabox.domain.post.model.toResponse
import com.teamsparta.gigabox.domain.post.repository.StorageRepository
import com.teamsparta.gigabox.domain.post.repository.PostRepository
import com.teamsparta.gigabox.infra.aws.AwsS3Service
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
@Service
class PostServiceImpl(
    private val postRepository: PostRepository,
    private val storageRepository: StorageRepository,
    private val awsS3Service: AwsS3Service
) : PostService {
    override fun getListPost(): List<PostResponse> {
        return postRepository.findAll().map { it.toResponse() }
    }

    override fun getPost(postId: Long): PostResponse {
        val post = postRepository.findByIdOrNull(postId) ?: throw ModelNotFoundException("post", postId)
        return post.toResponse()
    }

    @Transactional
    override fun createPost(formData: PostRequest): PostResponse {

        if (formData.imgUrl != null) {
            val post = postRepository.save(
                Post(
                    title = formData.title!!,
                    content = formData.content!!
                )
            )
            val uploadData = awsS3Service.uploadImage(formData.imgUrl)

            uploadData.forEach { url ->
                storageRepository.save(
                    Storage(
                        post = post,
                        imageUrl = url
                    )
                )
            }
            return post.toResponse()

        } else {
            val post = postRepository.save(
                Post(
                    title = formData.title!!,
                    content = formData.content!!
                )
            )
            return post.toResponse()
        }
    }

    @Transactional
    override fun updatePost(postId: Long, request: UpdatePostRequest): PostResponse {
        val post = postRepository.findByIdOrNull(postId) ?: throw ModelNotFoundException("post", postId)
        post.title = request.title ?: post.title
        post.content = request.content ?: post.content

        return post.toResponse()
    }

    @Transactional
    override fun deletePost(postId: Long) {
        val post = postRepository.findByIdOrNull(postId) ?: throw ModelNotFoundException("post", postId)
        post.deletePost()
        postRepository.save(post)
    }
}