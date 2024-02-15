package com.teamsparta.gigabox.domain.post.controller

import com.teamsparta.gigabox.domain.post.dto.request.PostRequest
import com.teamsparta.gigabox.domain.post.dto.request.UpdatePostRequest
import com.teamsparta.gigabox.domain.post.dto.response.PostResponse
import com.teamsparta.gigabox.domain.post.service.PostService
import com.teamsparta.gigabox.infra.aws.AwsS3Service
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/v1")
@RestController
class PostController(
    private val postService: PostService,
    private val awsS3Service: AwsS3Service
) {
    @GetMapping("/posts")
    fun getListPost(): ResponseEntity<List<PostResponse>> {
        return ResponseEntity.status(HttpStatus.OK).body(postService.getListPost())
    }

    @GetMapping("/posts/{postId}")
    fun getPost(
        @PathVariable postId: Long
    ): ResponseEntity<PostResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(postService.getPost(postId))
    }

    @PostMapping("/posts", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun createPost(
        @ModelAttribute formData: PostRequest
    ): ResponseEntity<PostResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.createPost(formData))
    }

    @PatchMapping("/posts/{postId}")
    fun updatePost(
        @PathVariable postId: Long,
        @RequestBody request: UpdatePostRequest
    ): ResponseEntity<PostResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(postService.updatePost(postId, request))
    }

    @DeleteMapping("/posts/{postId}")
    fun deletePost(
        @PathVariable postId: Long,
    ): ResponseEntity<Unit> {
        postService.deletePost(postId)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(postService.deletePost(postId))
    }
}