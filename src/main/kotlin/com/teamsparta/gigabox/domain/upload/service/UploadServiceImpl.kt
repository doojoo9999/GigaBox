package com.teamsparta.gigabox.domain.upload.service

import com.teamsparta.gigabox.domain.upload.dto.request.UploadRequest
import com.teamsparta.gigabox.domain.post.exception.ModelNotFoundException
import com.teamsparta.gigabox.domain.upload.model.UploadEntity
import com.teamsparta.gigabox.domain.upload.repository.UploadRepository
import com.teamsparta.gigabox.infra.aws.AwsS3Service
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UploadServiceImpl(
    private val uploadRepository: UploadRepository,
    private val awsS3Service: AwsS3Service
) : UploadService {
    @Transactional
    override fun fileUpload(multiFile: UploadRequest): String {

        val list: MutableList<UploadEntity> = mutableListOf()
        multiFile.file?.let {
            val uploadData = awsS3Service.uploadImage(it).map { url ->
                list.add(
                    uploadRepository.save(
                        UploadEntity(
                            imageUrl = url
                        )
                    )
                )
            }
        }
        return "파일이 업로드 되었습니다"
    }

    @Transactional
    override fun fileDelete(uploadId: Long) {
        val file =
            uploadRepository.findByIdOrNull(uploadId) ?: throw ModelNotFoundException("UploadEntity", uploadId)
        val fileUrl = file.imageUrl
        uploadRepository.delete(file)
        awsS3Service.deleteImage(fileUrl)
    }
}