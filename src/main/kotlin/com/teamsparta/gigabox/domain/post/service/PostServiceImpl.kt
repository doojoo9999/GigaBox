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
import java.time.LocalDateTime
import org.springframework.data.repository.findByIdOrNull
import org.springframework.scheduling.annotation.Scheduled
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
        val post = postRepository.save(
            Post(
                title = formData.title!!,
                content = formData.content!!
            )
        )

        val list: MutableList<Storage> = mutableListOf()
        formData.imgUrl?.let {
            val uploadData = awsS3Service.uploadImage(it).map { url ->
                list.add(
                    storageRepository.save(
                        Storage(
                            post = post,
                            imageUrl = url
                        )
                    )
                )
            }
            post.initImgUrl(list)
        }

        return post.toResponse()
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
        val storageList = storageRepository.findByPostId(postId)
        for (storage in storageList) {
            storage.deleteImg()
        }
        post.deletePost()

        postRepository.save(post)
//        awsS3Service.deleteImage(storageList[0].imageUrl)
    }


    @Scheduled(cron = "0 0 12 * * ?")
    @Transactional
    fun deleteData() {
        val dataBefore90days = LocalDateTime.now()
        storageRepository.deleteStorageByCreatedAtLessThanAndDeleted(dataBefore90days, true)
    }

//    @Transactional
//    @Scheduled(cron = "0 59 19 * * ?")
//    fun deletePostData() {
//        val dataPostBefore90days = LocalDateTime.now()
//        postRepository.deletePostByCreatedAtLessThanAndDeleted(dataPostBefore90days,true)
//    }
}