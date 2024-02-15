package com.teamsparta.gigabox.domain.member.model

import jakarta.persistence.*

@Entity
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
}