package com.teamsparta.gigabox.domain.member.repository

import com.teamsparta.gigabox.domain.member.model.MailEntity
import org.springframework.data.jpa.repository.JpaRepository

interface MailRepository : JpaRepository <MailEntity, Long> {
}