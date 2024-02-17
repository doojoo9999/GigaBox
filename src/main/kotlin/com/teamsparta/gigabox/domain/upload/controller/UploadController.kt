package com.teamsparta.gigabox.domain.upload.controller

import com.teamsparta.gigabox.domain.upload.dto.request.UploadRequest
import com.teamsparta.gigabox.domain.upload.service.UploadService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RequestMapping("/api/v1")
@RestController
class UploadController(
    private val uploadService: UploadService
) {

    @PostMapping("/upload", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun fileUpload(
        @ModelAttribute multiFile: UploadRequest
    ): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.OK).body(uploadService.fileUpload(multiFile))
    }

    @DeleteMapping("/upload/{uploadId}")
    fun fileDelete(
        @PathVariable uploadId: Long
    ): ResponseEntity<String> {
        uploadService.fileDelete(uploadId)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }

}