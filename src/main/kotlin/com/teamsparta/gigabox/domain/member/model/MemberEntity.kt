package com.teamsparta.gigabox.domain.member.model

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.Date

@Entity
@Table(name = "member")
class MemberEntity(
    @Column(name = "account")
    val account : String,

    @Column(name = "password")
    val password : String,

    @Column(name = "birth_date")
    val birthDate : Date,

    @Column(name = "phone_number")
    val phoneNumber : String,

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    var role : UserRole

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0L

    @Column(name = "created_at")
    lateinit var createdAt : LocalDateTime

    @Column(name = "updated_at")
    lateinit var updatedAt : LocalDateTime

}