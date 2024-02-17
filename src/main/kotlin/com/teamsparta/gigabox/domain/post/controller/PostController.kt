package com.teamsparta.gigabox.domain.post.controller

import com.teamsparta.gigabox.domain.post.dto.request.PostRequest
import com.teamsparta.gigabox.domain.post.dto.response.PostResponse
import com.teamsparta.gigabox.domain.post.service.PostService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/v1")
@RestController
class PostController(
    private val postService: PostService
) {
    @GetMapping("/posts")
    fun getListPost(
        @PageableDefault(size = 5, sort = ["createdAt"], direction = Sort.Direction.DESC) pageable: Pageable
    ): ResponseEntity<Page<PostResponse>> {
        return ResponseEntity.status(HttpStatus.OK).body(postService.getListPost(pageable))
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

    @PatchMapping("/posts/{postId}/storages/{storageId}", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun updatePost(
        @PathVariable postId: Long,
        @PathVariable storageId: Long,
        @ModelAttribute formData: PostRequest
    ): ResponseEntity<PostResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(postService.updatePost(postId, storageId, formData))
    }

    @DeleteMapping("/posts/{postId}")
    fun deletePost(
        @PathVariable postId: Long,
    ): ResponseEntity<Unit> {
        postService.deletePost(postId)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
}