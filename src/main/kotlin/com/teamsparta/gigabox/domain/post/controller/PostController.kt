package com.teamsparta.gigabox.domain.post.controller

import com.teamsparta.gigabox.domain.post.dto.request.PostRequest
import com.teamsparta.gigabox.domain.post.dto.response.PostResponse
import com.teamsparta.gigabox.domain.post.service.PostService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/v1")
@RestController
class PostController(
    private val postService: PostService
) {
    @GetMapping("/posts")
    fun getListPost(): ResponseEntity<List<PostResponse>>{
        return ResponseEntity.status(HttpStatus.OK).body(postService.getListPost())
    }

    @GetMapping("/posts/{postId}")
    fun getPost(
        @PathVariable postId: Long
    ): ResponseEntity<PostResponse>{
        return ResponseEntity.status(HttpStatus.OK).body(postService.getPost(postId))
    }

    @PostMapping("/posts")
    fun createPost(
        @RequestBody request: PostRequest
    ): ResponseEntity<PostResponse>{
        return ResponseEntity.status(HttpStatus.OK).body(postService.createPost(request))
    }

    @PatchMapping("/posts/{postId}")
    fun updatePost(
        @PathVariable postId: Long,
        @RequestBody request: PostRequest
    ): ResponseEntity<PostResponse>{
        return ResponseEntity.status(HttpStatus.OK).body(postService.updatePost(postId, request))
    }

    @DeleteMapping("/posts/{postId}")
    fun deletePost(
        @PathVariable postId: Long,
    ): ResponseEntity<Unit>{
        postService.deletePost(postId)
        return ResponseEntity.status(HttpStatus.OK).body(postService.deletePost(postId))
    }
}