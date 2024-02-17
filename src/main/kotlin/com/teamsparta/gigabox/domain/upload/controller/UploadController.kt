package com.teamsparta.gigabox.domain.upload.controller

import com.teamsparta.gigabox.domain.upload.dto.request.UploadRequest
import com.teamsparta.gigabox.domain.upload.service.UploadService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RequestMapping("/api/v1")
@RestController
class UploadController(
    private val uploadService: UploadService
) {

    @Operation(summary = "파일 업로드", description = "파일을 업로드 할 수 있다.")
    @PostMapping("/upload", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun fileUpload(
        @ModelAttribute multiFile: UploadRequest
    ): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.OK).body(uploadService.fileUpload(multiFile))
    }

    @Operation(summary = "파일 삭제", description = "파일을 삭제 할 수 있다.")
    @DeleteMapping("/upload/{uploadId}")
    fun fileDelete(
        @PathVariable uploadId: Long
    ): ResponseEntity<String> {
        uploadService.fileDelete(uploadId)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }

}