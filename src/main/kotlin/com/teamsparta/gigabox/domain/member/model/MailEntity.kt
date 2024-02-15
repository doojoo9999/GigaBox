package com.teamsparta.gigabox.domain.member.model

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@EntityListeners(AuditingEntityListener::class)
@Table(name = "mail")
class MailEntity (

    @Column(name = "email")
    val email : String,

    @Column(name = "auth_code")
    val authCode : String,
){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0L

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    lateinit var createdAt : LocalDateTime
}