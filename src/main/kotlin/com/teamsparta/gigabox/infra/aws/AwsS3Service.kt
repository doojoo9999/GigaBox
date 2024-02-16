package com.teamsparta.gigabox.infra.aws

import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.DeleteObjectRequest
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import io.ktor.utils.io.errors.*
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.server.ResponseStatusException
import java.util.*


@Service
class AwsS3Service(
    private val amazonS3Client: AmazonS3Client
) {
    @Value("\${cloud.aws.s3.bucket}")
    lateinit var bucket: String

    @Throws(IOException::class)
    fun uploadImage(multipartFile: List<MultipartFile>?): List<String> {
        val fileNameList = mutableListOf<String>()

        multipartFile!!.forEach { file ->
            val fileName = UUID.randomUUID().toString() + "-" + file.originalFilename
            val objectMetadata = ObjectMetadata().apply {
                contentLength = file.size
                contentType = file.contentType
            }

            try {
                file.inputStream.use { inputStream ->
                    amazonS3Client.putObject(
                        PutObjectRequest(bucket, fileName, inputStream, objectMetadata)
                            .withCannedAcl(CannedAccessControlList.PublicRead)
                    )
                }
            } catch (e: IOException) {
                throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "이미지 업로드에 실패했습니다.")
            }


            fileNameList.add("https://gigaboxbucket.s3.ap-northeast-2.amazonaws.com/$fileName")
        }

        return fileNameList
    }

    fun deleteImage(fileName: String?) {
        amazonS3Client.deleteObject(DeleteObjectRequest(bucket, fileName))
    }
}